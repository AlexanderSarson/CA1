package facades;

import entities.GroupMember;
import entities.dto.GroupDTO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeGroup {

    private static FacadeGroup instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private FacadeGroup() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeGroup getFacadeGroup(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeGroup();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<GroupDTO> getAllGroupMembersDTO() {
        EntityManager em = getEntityManager();
        List<GroupDTO> groupMembersDTO = new ArrayList<>();
        try {
            TypedQuery<GroupMember> tp = em.createQuery("SELECT g FROM GroupMember g", GroupMember.class);
            tp.getResultList().forEach((member) -> groupMembersDTO.add(new GroupDTO(member)));
            return groupMembersDTO;
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3306/CA1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        GroupMember r1, r2, r3, r4;
        r1 = new GroupMember("Alexander Sarson", "cph-as485", "red");
        r2 = new GroupMember("Oscar Laurberg", "cph-ol38", "red");
        r3 = new GroupMember("Mads Brandt", "cph-ol38", "red");
        r4 = new GroupMember("Benjamin Paprika", "cph-ol38", "red");
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
