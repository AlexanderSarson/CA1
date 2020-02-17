package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.dto.CarDTO;
import facades.FacadeCar;
import utils.EMF_Creator;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("cars")
public class CarResource {

    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("pu"); 
    private final FacadeCar FACADE =  FacadeCar.getFacadeCar(EMF);
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
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
