package no.group7;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.*;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "tables";
    private static EntityManagerFactory factory;

    public static <vote> void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        // read the existing entries and write to console

        em.getTransaction().begin();

        UserAcc user = new UserAcc();
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


        VoterAcc voter = new VoterAcc();
        voter.setFirstName("gaming" );
        voter.setLastName("god");
        voter.setUsername("gaminggod" );
        voter.setPassword("poopi");
        em.persist(voter);

        VoterAcc voter2 = new VoterAcc();
        voter2.setFirstName("gaming2" );
        voter2.setLastName("god");
        voter2.setUsername("gaminggod2" );
        voter2.setPassword("poopi4");
        em.persist(voter2);

        Poll poll = (Poll) polls.toArray()[0];

        Vote vote = new Vote();
        vote.setPoll(poll);
        vote.setVoterAcc(voter);
        vote.setType(true);
        em.persist(vote);

        Vote vote2 = new Vote();
        vote2.setPoll(poll);
        vote2.setVoterAcc(voter2);
        vote2.setType(false);
        em.persist(vote2);



        List<Vote> votes= new ArrayList<>();
        votes.add(vote);
        voter.setVotes(votes);
        votes.add(vote2);
        voter2.setVotes(new ArrayList<>(List.of(vote2)));
        poll.setVotes(votes);
        em.persist(poll);

        user.setPolls(polls);

        em.persist(user);

        em.getTransaction().commit();

        Query q = em.createQuery("select t from UserAcc t");
        List result = q.getResultList();

        for (Object ua : result) {
            System.out.println(ua);
            System.out.println();
        }


        em.close();
    }
}
