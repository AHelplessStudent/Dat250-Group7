package no.group7;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CorrectValueTest {

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
        user.setPassword("hashed:secret13");
        user.setFirstName("Carl");
        user.setLastName("Davids");
        em.persist(user);

        user = new Account();
        user.setUsername("User2");
        user.setPassword("hashed:secret2");
        user.setFirstName("John");
        user.setLastName("Cohen");
        em.persist(user);

        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void checkGetCorrectFirstName() {
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select u from Account u where u.username='User1'");
        Account account = (Account) q.getResultList().get(0);
        assertEquals("Carl", account.getFirstName());
        em.close();
    }

    @Test
    public void checkGetCorrectLastName() {
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select u from Account u where u.username='User1'");
        Account account = (Account) q.getResultList().get(0);
        assertEquals("Davids", account.getLastName());
        em.close();
    }

    @Test
    public void checkGetCorrectPassword() {
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select u from Account u where u.username='User1'");
        Account account = (Account) q.getResultList().get(0);
        System.out.println(account);
        assertEquals("hashed:secret13", account.getPassword());
        em.close();
    }
}
