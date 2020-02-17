package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.FacadeGroup;
import utils.EMF_Creator;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
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
@Path("groupmembers")
public class GroupResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3306/CA1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final FacadeGroup FACADE =  FacadeGroup.getFacadeGroup(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    /*
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllMovies() {
        List<MovieDTO> movieList = new ArrayList<>();
        FACADE.getAllMovies().forEach((movie) -> movieList.add(new MovieDTO(movie)));     
        if(movieList.size() > 0){
            return Response
                    .status(Response.Status.OK)
                    .entity(GSON.toJson(movieList))
                    .type(MediaType.APPLICATION_JSON)
                    .build();                    
        } else {
            return Response
                    .status(Response.Status.OK)
                    .entity("{\"msg\":\"Movies not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
    
    @Path("/count")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieCount(){
        return Response
                .status(Response.Status.OK)
                .entity("{\"count\": " + GSON.toJson(FACADE.getMovieCount()) + "}")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
            
    @Path("/name/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieName(@PathParam("name") String name){
        List<Movie> listOfMoviesByName = FACADE.getMoviesByName(name);
        if(listOfMoviesByName.size() > 0){
            return Response
                    .status(Response.Status.OK)
                    .entity(GSON.toJson(listOfMoviesByName))
                    .type(MediaType.APPLICATION_JSON)
                    .build();                    
        } else {
            return Response
                    .status(Response.Status.OK)
                    .entity("{\"msg\":\"Movie not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieByID(@PathParam("id") long id){
        Movie movie = FACADE.getMovieByID(id);
        if(movie != null){
            return Response
                    .status(Response.Status.OK)
                    .entity(GSON.toJson(movie))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else {
            return Response
                    .status(Response.Status.OK)
                    .entity("{\"msg\":\"Movie not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
*/
 
}
