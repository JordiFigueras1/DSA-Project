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
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        User u = this.gm.getUser(id);

        if (u == null) {return Response.status(404).build();}
        else  {return Response.status(201).entity(u).build();}
    }

    @GET
    @ApiOperation(value = "Authenticate User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{mail}&{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authentificate(@PathParam("mail") String mail, @PathParam("password") String password) {

        User user = this.gm.authentification(mail, password);

        if (user == null) {return Response.status(404).build();}
        else {return Response.status(201).entity(user).build();}
    }

    @DELETE
    @ApiOperation(value = "delete a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        User t = this.gm.deleteUser(id);

        if (t == null) {return Response.status(404).build();}
        else {return Response.status(201).build();}
    }


    @PUT
    @ApiOperation(value = "update a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/")
    public Response updateUser(User u) {

        User user = this.gm.updateUser(u);

        if (user == null) {return Response.status(404).build();}
        else {return Response.status(201).build();}
    }

    @POST
    @ApiOperation(value = "create a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 400, message = "User already exist for this mail")

    })

    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User u) {
        User t = this.gm.addUser(u);

        if (t == null) {return Response.status(400).build();}
        else {return Response.status(201).entity(u).build();}
    }
}
