package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.dto.CarDTO;
import facades.FacadeCar;
import utils.EMF_Creator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("cars")
public class CarResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3306/CA1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final FacadeCar FACADE =  FacadeCar.getFacadeCar(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllCars() {
        List<CarDTO> groupMembers = FACADE.getAllCarsDTO();
        if(groupMembers.size() > 0){
            return Response
                    .status(Response.Status.OK)
                    .entity(GSON.toJson(groupMembers))
                    .type(MediaType.APPLICATION_JSON)
                    .build();                    
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{\"msg\":\"Cars not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}
