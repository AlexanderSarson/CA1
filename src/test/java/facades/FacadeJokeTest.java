package facades;

import entities.Joke;
import java.util.List;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeJokeTest {

    private static EntityManagerFactory emf;
    private static FacadeJoke facade;
    private static Joke r1, r2;

    public FacadeJokeTest() {
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = FacadeJoke.getFacadeJoke(emf);
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        r1 = new Joke("Chuck Norris har talt til uendeligt – To gange endda", "Chuck Norris", "Sjov");
        r2 = new Joke("Teenage Mutant Ninja Turtles er baseret på en sand historie, Chuck Norris slugte engang en hel skildpadde, og da den kom ud igen var den 2 meter høj og havde lært karate", "Chuck Norris", "Sjov");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(r1);
            em.persist(r2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testGetAllJokes() {
        List<Joke> jokeList = facade.getAllJokes();
        int expectedNumberOfJokes = 2;
        int resultNumberOfJokes = jokeList.size();
        assertEquals(expectedNumberOfJokes, resultNumberOfJokes);
    }

    @Test
    public void testGetJokeByID(){
        long expectedId = r1.getId();
        long resultId = facade.getJokeById(expectedId).getId();
        assertEquals(expectedId, resultId);
    }
    
    @Test
    public void testGetCountOfJokes(){
        int expectedCount = 2;
        int resultCount = facade.getCountOfJokes();
        assertEquals(expectedCount, resultCount);
    }
    
    @Test
    public void testGetRandomJoke(){
        Joke joke = facade.getRandomJoke();
        assertFalse(joke == null);
    }
    
    @Test
    public void testGetRandomJokeNotFoundException(){
         EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        assertThrows(IllegalArgumentException.class, () -> {facade.getRandomJoke();});
    }
}
