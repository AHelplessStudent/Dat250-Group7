package no.group7.dao;

import no.group7.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class AccountDao implements Dao<Account> {

    private final EntityManager entityManager;

    public AccountDao(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Optional<Account> get(long id) {
        return Optional.ofNullable(entityManager.find(Account.class, id));
    }

    @Override
    public List<Account> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Account e");
        return query.getResultList();
    }

    @Override
    public void save(Account account) {
        executeInsideTransaction(entityManager -> entityManager.persist(account));
    }

    @Override
    public void update(Account account, String[] params) {
        account.setFirstName(Objects.requireNonNull(params[0], "First name cannot be null"));
        account.setLastName(Objects.requireNonNull(params[1], "Last name cannot be null"));
        account.setUsername(Objects.requireNonNull(params[2], "Username cannot be null"));
        account.setPassword(Objects.requireNonNull(params[3], "Password cannot be null"));

        executeInsideTransaction(entityManager -> entityManager.merge(account));
    }

    @Override
    public void delete(Account account) {
        executeInsideTransaction(entityManager -> entityManager.remove(account));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
