package no.group7;

import static org.junit.Assert.assertTrue;

import javax.persistence.*;

import org.junit.Before;
import org.junit.Test;

public class RelationTests {

    // use a separate database "testdb".
    private static final String PERSISTENCE_UNIT_NAME = "test";
    private EntityManagerFactory factory;

    @Before
    public void setUp() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        UserAcc user = new UserAcc();
        user.setUsername("gamer");
        user.setPassword("dakgjf");
        user.setFirstName("carl");
        user.setLastName("davids");
        em.persist(user);

        em.getTransaction().commit();

        em.close();

    }

    @Test
    public void checkIfUserAccountIsCreated() {

        EntityManager em = factory.createEntityManager();

        Query q = em.createQuery("select u from UserAcc u");

        assertTrue(q.getResultList().size() >= 1);

        em.close();

    }
}
