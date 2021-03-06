package facades;

import entities.GroupMember;
import entities.dto.GroupDTO;
import java.util.List;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class FacadeGroupTest {

    private static EntityManagerFactory emf;
    private static FacadeGroup facade;
    private static GroupMember r1, r2;

    public FacadeGroupTest() {
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
       facade = FacadeGroup.getFacadeGroup(emf);
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        r1 = new GroupMember("Alexander Sarson", "cph-as485", "red");
        r2 = new GroupMember("Oscar Laurberg", "cph-ol38", "red");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();
            em.persist(r1);
            em.persist(r2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testGetAllGroupMembers() {
        List<GroupDTO> groupMembers = facade.getAllGroupMembersDTO();
        int expectedNumberOfMembers = 2;
        int resultNumberOfMembers = groupMembers.size();
        assertEquals(expectedNumberOfMembers, resultNumberOfMembers);
    }

}
