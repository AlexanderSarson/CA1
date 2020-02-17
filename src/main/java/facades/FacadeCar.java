package facades;

import entities.Car;
import entities.dto.CarDTO;
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
public class FacadeCar {

    private static FacadeCar instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private FacadeCar() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeCar getFacadeCar(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeCar();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<CarDTO> getAllCarsDTO() {
        EntityManager em = getEntityManager();
        List<CarDTO> CarsDTO = new ArrayList<>();
        try {
            TypedQuery<Car> tp = em.createQuery("SELECT c FROM Car c", Car.class);
            tp.getResultList().forEach((car) -> CarsDTO.add(new CarDTO(car)));
            return CarsDTO;
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
        Car r1, r2, r3, r4,r5;
        r1 = new Car(1997, "Ford", "E350", 3000, "Alexander");
        r2 = new Car(1999, "Chevy", "Venture", 4900, "Alexander");
        r3 = new Car(2000, "Chevy", "Venture", 5000, "Alexander");
        r4 = new Car(1996, "Jeep", "Grand Cherokee", 4799, "Alexander");
        r5 = new Car(2005, "Volvo", "V70", 44799, "Alexander");
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(r1);
            em.persist(r2);
            em.persist(r3);
            em.persist(r4);
            em.persist(r5);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
