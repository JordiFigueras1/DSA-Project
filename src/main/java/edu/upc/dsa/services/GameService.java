package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.VOCredentials;
import edu.upc.dsa.models.Item;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/", description = "Endpoint to User Service")
@Path("/")

public class GameService {
    private GameManager gm;

    public GameService() {
        this.gm = GameManagerImpl.getInstance();
        if (gm.size()==0) {
            this.gm.addUser("jordi@gmail.com", "Jordi", "1234");
            this.gm.addUser("bryan@gmail.com", "Bryan", "1234");
            this.gm.addUser("clement@gmail.com", "Clement", "1234");
            this.gm.addItem("ESPADA", "espada de doble mano forjada por herreros de rivendel", 15, 10, 0, "https://espadasdetoledo.com/images/stories/virtuemart/product/Battle_ready_sword.jpg");
            this.gm.addItem("POCION", "Pocion curativa", 20, 0, 10,"");
            this.gm.addItem("ESCUDO", "Proporciona defensa", 10, 0, 50, "");
            this.gm.addItem("BASTON", "Pega ostias", 50, 100, 0, "");
        }
    }
    @GET
    @ApiOperation(value = "get all Users", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> users = this.gm.getUsers();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build();

    }
    @GET
    @ApiOperation(value = "get an User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
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
    @Path("/users/{mail}&{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authentificate(@PathParam("mail") String mail, @PathParam("password") String password) {

        User user = this.gm.authentification(mail, password);

        if (user == null) {return Response.status(404).build();}
        else {return Response.status(201).entity(user).build();}
    }

    @POST
    @ApiOperation(value = "login", notes = "Realitzar el login")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/users/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logIn(VOCredentials credentials) {
        System.out.println("-----LOGIN-----");
        System.out.println("Mail: "+ credentials.getMail());
        User u = this.gm.loginUsuario(credentials);
        if (u==null)
            return Response.status(500).build();
        else
            return Response.status(201).entity(u).build();
    }

    @DELETE
    @ApiOperation(value = "delete an User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/users/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        User t = this.gm.deleteUser(id);

        if (t == null) {return Response.status(404).build();}
        else {return Response.status(201).build();}
    }


    @PUT
    @ApiOperation(value = "update an User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/users")
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

    @Path("/users/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newUser(User u) {

        User t = this.gm.addUser(u);

        if (t == null) {return Response.status(400).build();}
        else {return Response.status(201).entity(u).build();}
    }

    @GET
    @ApiOperation(value = "get all Items", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
    })
    @Path("/items")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjects() {
        List<Item> listItems = this.gm.getAllItems();
        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(listItems){};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "get an Item", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class),
            @ApiResponse(code = 404, message = "Item not found")
    })
    @Path("/items/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") int id) {
        Item item = this.gm.getItem(id);

        if (item == null) {return Response.status(404).build();}
        else  {return Response.status(201).entity(item).build();}
    }

    @POST
    @ApiOperation(value = "create a new Item", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Item.class),
            @ApiResponse(code = 400, message = "item already exist for this name")

    })

    @Path("/items/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newItem(Item item) {

        Item i = this.gm.addItem(item);

        if (i == null) {return Response.status(400).build();}
        else {return Response.status(201).entity(i).build();}
    }
}
