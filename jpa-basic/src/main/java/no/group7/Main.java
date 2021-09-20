package no.group7;

import no.group7.dao.AccountDao;
import no.group7.dao.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "tables";

    private static Dao<Account> userAccDao;

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        userAccDao = new AccountDao(em);

        /*
          Create some accounts.
         */
        Account acc1 = new Account();
        acc1.setFirstName("John");
        acc1.setLastName("Doe");
        acc1.setUsername("CoolKid47");
        acc1.setPassword("hashed:BigOlPassword");
        em.persist(acc1);

        /* Associate voter entity with account */
        VoteEntity voter1 = new VoteEntity();
        voter1.setEntityType("Human");
        voter1.setRegistered(true);
        voter1.setAccount(acc1);

        /*
          Create some polls.
         */
        Collection<Poll> polls = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Poll poll = new Poll();
            poll.setTitle("Is the number " + (i + 1) + " even?");
            poll.setDeadline(LocalDateTime.of(2020 + i, Month.APRIL, 2 + i, 13 + i, 11));
            poll.setPublic(true);
            poll.setUserAcc(acc1);
            em.persist(poll);

            polls.add(poll);
        }

        Poll poll = (Poll) polls.toArray()[0];

        /* Add some votes */
        Vote vote1 = new Vote();
        vote1.setFrom(voter1);
        vote1.setNumNo(1);
        vote1.setNumYes(0);

        List<Vote> votes = new ArrayList<>();
        votes.add(vote1);

        // add votes to account and poll
        voter1.setVotes(votes);
        poll.setVotes(votes);
        em.persist(voter1);

        em.persist(voter1);
        em.persist(acc1);
        em.persist(poll);
        em.getTransaction().commit();

        Query q = em.createQuery("select t from Poll t");
        List result = q.getResultList();

        for (Object ua : result) {
            System.out.println(ua);
        }
        System.out.println(getUser(1L));
        em.close();
    }

    private static Account getUser(Long id) {
        Optional<Account> user = userAccDao.get(id);

        return user.orElseGet(Account::new);
    }
}
