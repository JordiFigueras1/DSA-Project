package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/users", description = "Endpoint to User Service")
@Path("/users")

public class GameService {
    private GameManager gm;

    public GameService() {
        this.gm = GameManagerImpl.getInstance();
        if (gm.size()==0) {
            this.gm.addUser("jordi@gmail.com", "Jordi", "1234");
            this.gm.addUser("bryan@gmail.com", "Bryan", "1234");
            this.gm.addUser("clement@gmail.com", "Clement", "1234");
        }


    }
    @GET
    @ApiOperation(value = "get all Users", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> users = this.gm.findAll();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "get a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{mail}{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("mail") String mail, @PathParam("password") String password) {
        User u = this.gm.getUser(mail, password);

        if (u == null) {return Response.status(404).build();}
        else  {return Response.status(201).entity(u).build();}
    }

    @DELETE
    @ApiOperation(value = "delete a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{mail}{password}")
    public Response deleteUser(@PathParam("mail") String mail, @PathParam("password") String password) {
        User t = this.gm.deleteUser(mail, password);

        if (t == null) {return Response.status(404).build();}
        else {return Response.status(201).build();}
    }

    @PUT
    @ApiOperation(value = "update a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{mail}{password}{username}")
    public Response updateTrack(@PathParam("mail") String mail, @PathParam("password") String password, @PathParam("username") String newUsername) {

        User u = new User(mail, password, newUsername);
        User newU = this.gm.updateUser(u);

        if (newU == null) {return Response.status(404).build();}
        else {return Response.status(201).build();}
    }

    @POST
    @ApiOperation(value = "create a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/{mail}{password}{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTrack(@PathParam("mail") String mail, @PathParam("password") String password, @PathParam("username") String username) {

        User u = this.gm.addUser(new User(mail, password, username));

        if (u == null) {return Response.status(500).entity(u).build();}
        else {return Response.status(201).entity(u).build();}
    }
}
