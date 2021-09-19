package no.group7.dao;

import no.group7.Poll;
import no.group7.UserAcc;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class UserAccDao implements Dao<UserAcc> {

    private EntityManager entityManager;


    public UserAccDao(EntityManager em) {
        this.entityManager = em;
    }
    // standard constructors

    @Override
    public Optional<UserAcc> get(long id) {
        return Optional.ofNullable(entityManager.find(UserAcc.class, id));
    }

    @Override
    public List<UserAcc> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM UserAcc e");
        return query.getResultList();
    }

    @Override
    public void save(UserAcc userAcc) {
        executeInsideTransaction(entityManager -> entityManager.persist(userAcc));
    }

    @Override
    public void update(UserAcc userAcc, String[] params) {
        userAcc.setFirstName(Objects.requireNonNull(params[0], "First name cannot be null"));
        userAcc.setLastName(Objects.requireNonNull(params[1], "Last name cannot be null"));
        userAcc.setUsername(Objects.requireNonNull(params[2], "Username cannot be null"));
        userAcc.setPassword(Objects.requireNonNull(params[3], "Password cannot be null"));

        executeInsideTransaction(entityManager -> entityManager.merge(userAcc));
    }

    public void updatePolls(UserAcc userAcc, Collection<Poll> polls) {

    }

    @Override
    public void delete(UserAcc userAcc) {
        executeInsideTransaction(entityManager -> entityManager.remove(userAcc));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
