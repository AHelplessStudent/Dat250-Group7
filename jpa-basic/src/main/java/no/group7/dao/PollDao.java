package no.group7.dao;

import no.group7.Poll;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class PollDao implements Dao<Poll> {

    private final EntityManager entityManager;


    public PollDao(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Optional<Poll> get(long id) {
        return Optional.ofNullable(entityManager.find(Poll.class, id));
    }

    @Override
    public List<Poll> getAll() {
        Query query = entityManager.createQuery("SELECT p FROM Poll p");
        return query.getResultList();
    }

    @Override
    public void save(Poll poll) {
        executeInsideTransaction(entityManager -> entityManager.persist(poll));
    }

    @Override
    public void update(Poll poll, String[] params) {
        poll.setTitle(Objects.requireNonNull(params[0], "Title cannot be null"));
        poll.setDeadline(stringToLocalDateTime(Objects.requireNonNull(params[0], "LocalDateTime cannot be null")));
        poll.setPublic(Boolean.parseBoolean(Objects.requireNonNull(params[0], "Title cannot be null")));

        executeInsideTransaction(entityManager -> entityManager.merge(poll));
    }

    private LocalDateTime stringToLocalDateTime(String date) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("mm:HH:yyyy:dd:MM"));
    }

    @Override
    public void delete(Poll poll) {
        executeInsideTransaction(entityManager -> entityManager.remove(poll));
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
