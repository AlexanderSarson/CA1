package facades;

import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeJoke {

    private static FacadeJoke instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private FacadeJoke() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeJoke getFacadeJoke(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeJoke();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Joke> getAllJokes() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Joke> tp = em.createQuery("SELECT j FROM Joke j", Joke.class);
            return tp.getResultList();
        } finally {
            em.close();
        }
    }

    public Joke getJokeById(long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Joke.class, id);
        } finally {
            em.close();
        }
    }

    public int getCountOfJokes() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Joke> tp = em.createQuery("SELECT j FROM Joke j", Joke.class);
            return tp.getResultList().size();
        } finally {
            em.close();
        }
    }

    public Joke getRandomJoke() throws IllegalArgumentException {
        Random rand = new Random();
        int numberOfJokes = getCountOfJokes();
        int randomNumber = rand.nextInt(numberOfJokes);
        return getAllJokes().get(randomNumber);
    }

    public static void main(String[] args) {
        EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3306/CA1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        Joke r1, r2, r3, r4;
        r1 = new Joke("Chuck Norris har talt til uendeligt – To gange endda", "Chuck Norris", "Sjov");
        r2 = new Joke("Teenage Mutant Ninja Turtles er baseret på en sand historie, Chuck Norris slugte engang en hel skildpadde, og da den kom ud igen var den 2 meter høj og havde lært karate", "Chuck Norris", "Sjov");
        r3 = new Joke("Da Gud sagde “Lad der blive lys!”, lød Chuck Norris`stemme fra jorden\n"
                + "\n"
                + "\"Sig be`om!\".", "Chuck Norris", "Sjov");
        r4 = new Joke("Når Chuck Norris skal have kaffe, så kværner han selv bønnerne med sine tænder, og varmer vandet med sin vrede.", "Chuck Norris", "Sjov");
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(r1);
            em.persist(r2);
            em.persist(r3);
            em.persist(r4);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
