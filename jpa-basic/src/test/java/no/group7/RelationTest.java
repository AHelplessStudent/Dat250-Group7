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

        Account user = new Account();
        user.setUsername("User1");
        user.setPassword("secret1");
        user.setFirstName("Carl");
        user.setLastName("Davids");
        em.persist(user);

        user = new Account();
        user.setUsername("User2");
        user.setPassword("secret2");
        user.setFirstName("David");
        user.setLastName("Carls");
        em.persist(user);

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void checkIfUserAccountIsCreated() {
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select u from Account u");

        assertTrue(q.getResultList().size() >= 1);
        em.close();
    }

    @Test
    public void checkFetchByUsername() {
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select u from Account u where u.username='User1'");

        assertTrue(q.getResultList().size() >= 1);
        em.close();
    }

    @Test
    public void checkFetchByFirstName() {
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select u from Account u where u.firstName='David'");

        assertTrue(q.getResultList().size() >= 1);
        em.close();
    }

    @Test
    public void checkFetchByLastName() {
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select u from Account u where u.lastName='Carls'");

        assertTrue(q.getResultList().size() >= 1);
        em.close();
    }
}
