package no.group7;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RelationTest {

    // use a separate database "testdb".
    private static final String PERSISTENCE_UNIT_NAME = "test";
    private static EntityManagerFactory factory;

    @BeforeAll
    public static void setUp() {
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
