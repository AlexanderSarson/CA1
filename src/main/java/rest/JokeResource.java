/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Joke;
import facades.FacadeJoke;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author root
 */
@Path("jokes")
public class JokeResource {
    
    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("pu"); 
    private final FacadeJoke FACADE =  FacadeJoke.getFacadeJoke(EMF);
    private final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllJokes() {
        List<Joke> groupMembers = FACADE.getAllJokes();
        if(groupMembers.size() > 0){
            return Response
                    .status(Response.Status.OK)
                    .entity(GSON.toJson(groupMembers))
                    .type(MediaType.APPLICATION_JSON)
                    .build();                    
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{\"msg\":\"Jokes not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
    
    @Path("id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJoke(@PathParam("id") long id){
        Joke joke = FACADE.getJokeById(id);
        if(joke != null){
            return Response
                    .status(Response.Status.OK)
                    .entity(GSON.toJson(joke))
                    .type(MediaType.APPLICATION_JSON)
                    .build();                    
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{\"msg\":\"Joke not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
    
    @Path("/random")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandomJoke(){
        try {
            Joke joke = FACADE.getRandomJoke();
            return Response
                    .status(Response.Status.OK)
                    .entity(GSON.toJson(joke))
                    .type(MediaType.APPLICATION_JSON)
                    .build();  
        } catch (IllegalArgumentException e){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{\"msg\":\"Joke not found\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}
