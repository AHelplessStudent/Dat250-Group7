package no.group7.dao;

import no.group7.VoteEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class VoteEntityDao implements Dao<VoteEntity> {

    private final EntityManager entityManager;


    public VoteEntityDao(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Optional<VoteEntity> get(long id) {
        return Optional.ofNullable(entityManager.find(VoteEntity.class, id));
    }

    @Override
    public List<VoteEntity> getAll() {
        Query query = entityManager.createQuery("SELECT ve FROM VoteEntity ve");
        return query.getResultList();
    }

    @Override
    public void save(VoteEntity voteEntity) {
        executeInsideTransaction(entityManager -> entityManager.persist(voteEntity));
    }

    @Override
    public void update(VoteEntity voteEntity, String[] params) {
        voteEntity.setType((Objects.requireNonNull(params[0], "LocalDateTime cannot be null")));
        voteEntity.setRegistered(Boolean.parseBoolean(Objects.requireNonNull(params[0], "Title cannot be null")));
        executeInsideTransaction(entityManager -> entityManager.merge(voteEntity));
    }

    @Override
    public void delete(VoteEntity voteEntity) {
        executeInsideTransaction(entityManager -> entityManager.remove(voteEntity));
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
