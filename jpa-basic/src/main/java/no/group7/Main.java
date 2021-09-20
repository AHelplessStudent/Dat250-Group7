package no.group7;

import no.group7.dao.Dao;
import no.group7.dao.UserAccDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.*;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "tables";
    private static EntityManagerFactory factory;

    private static Dao<Account> UserAccDao;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        // read the existing entries and write to console

        em.getTransaction().begin();

        Account user = new Account();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("CoolKid47");
        user.setPassword("BigOlPassword");

        Collection<Poll> polls = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Poll poll = new Poll();
            poll.setTitle("202" +i+ " Election");
            poll.setDeadline(new Date(2020+i, Calendar.DECEMBER, 2+i));
            poll.setPublic(true);
            poll.setUserAcc(user);
            em.persist(poll);

            polls.add(poll);
        }


        Account voterAcc = new Account();
        voterAcc.setFirstName("gaming" );
        voterAcc.setLastName("god");
        voterAcc.setUsername("gaminggod" );
        voterAcc.setPassword("poopi");
        em.persist(voterAcc);

        Poll poll = (Poll) polls.toArray()[0];

        Voter voter = new Voter();
        voter.setAccountType("Human");
        voter.setFirstName("Gary the Gamer");
        voter.setAccount(voterAcc);

        Voter voter2 = new Voter();
        voter2.setAccountType("Human");
        voter2.setFirstName("Hugo the Human");

        Vote vote = new Vote();
        vote.setPoll(poll);
        vote.setVoter(voter);
        vote.setType(true);
        em.persist(vote);

        Vote vote2 = new Vote();
        vote2.setPoll(poll);
        vote2.setVoter(voter2);
        vote2.setType(false);
        em.persist(vote2);



        List<Vote> votes= new ArrayList<>();
        votes.add(vote);
        voter.setVotes(votes);
        em.persist(voter);
        voterAcc.setVoter(voter);
        em.persist(voterAcc);
        votes.add(vote2);
        voter2.setVotes(new ArrayList<>(List.of(vote2)));
        em.persist(voter2);
        poll.setVotes(votes);
        em.persist(poll);

        user.setPolls(polls);

        em.persist(user);

        em.getTransaction().commit();

        Query q = em.createQuery("select t from Account t");
        List result = q.getResultList();

        for (Object ua : result) {
            System.out.println(ua);
            System.out.println();
        }

        UserAccDao = new UserAccDao(em);
        Account user1 = getUser(1L);
        System.out.println("Dao Test");
        System.out.println(user1);
        System.out.println("Dao Test");

        List<Account> users = UserAccDao.getAll();

        for (Account u: users) {
            System.out.println(u);
        }


        em.close();
    }

    private static Account getUser(Long id) {
        Optional<Account> user = UserAccDao.get(id);

        return user.orElseGet(
                () -> new Account());
    }
}
