package no.group7.dao;

import no.group7.Vote;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class VoteDao implements Dao<Vote> {

    private final EntityManager entityManager;


    public VoteDao(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Optional<Vote> get(long id) {
        return Optional.ofNullable(entityManager.find(Vote.class, id));
    }

    @Override
    public List<Vote> getAll() {
        Query query = entityManager.createQuery("SELECT v FROM Vote v");
        return query.getResultList();
    }

    @Override
    public void save(Vote vote) {
        executeInsideTransaction(entityManager -> entityManager.persist(vote));
    }

    @Override
    public void update(Vote vote, String[] params) {
        vote.setNumYes(Integer.parseInt(Objects.requireNonNull(params[0], "Title cannot be null")));
        vote.setNumNo(Integer.parseInt(Objects.requireNonNull(params[1], "Title cannot be null")));

        executeInsideTransaction(entityManager -> entityManager.merge(vote));
    }

    private LocalDateTime stringToLocalDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("mm:HH:yyyy:dd:MM"));
    }

    @Override
    public void delete(Vote vote) {
        executeInsideTransaction(entityManager -> entityManager.remove(vote));
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
