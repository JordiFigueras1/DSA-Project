package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.GameManagerImpl;
import edu.upc.dsa.models.*;

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
            this.gm.addItem("Sword", "double handed sword forged by smiths of rivendell", 15, 10, 0,"" , "https://www.vhv.rs/dpng/d/540-5408508_pe-de-pirate-hd-png-download.png");
            this.gm.addItem("Potion", "healing potion", 20, 0, 10, "", "https://w7.pngwing.com/pngs/1019/181/png-transparent-red-potion-thumbnail.png");
            this.gm.addItem("Shield", "provide defence", 10, 0, 50, "", "https://fortnite-api.com/images/cosmetics/br/bid_975_journeymentor_nff9c/icon.png");
            this.gm.addItem("Stick", "hit ennemy", 12, 100, 0, "", "https://as2.ftcdn.net/v2/jpg/00/55/95/49/1000_F_55954917_wBRjXVoOqi0tCo1hBFZv0H3Ef2WkIvu5.jpg");
            this.gm.addItem("Nunchaku", "japan arm", 12, 43, 0, "", "https://w7.pngwing.com/pngs/22/992/png-transparent-weapon-nunchaku-hapkido-combat-sport-martial-arts-weapon-lung-scale-models-wikipedia.png");
            this.gm.addItem("AK47", "Shot head ball", 23, 100, 0,"" , "https://e7.pngegg.com/pngimages/640/729/png-clipart-ak-47-assault-rifle-gun-automatic-rifle-ak-47-kalashnikov-airsoft-desktop-wallpaper.png");
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////// GET ////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    @ApiOperation(value = "get all Items in the inventory", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/users/inventory/{mail}&{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemInInventory(@PathParam("mail") String mail, @PathParam("password") String password) {
        List<Item> items = this.gm.getItemInInventory(mail, password);
        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items){};
        if (items == null) {return Response.status(404).build();}
        else {return Response.status(201).entity(entity).build();}
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


    @GET
    @ApiOperation(value = "get all message", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Message.class, responseContainer="List"),
    })
    @Path("/users/posts/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessages() {

        List<Message> messages = new ArrayList<>();
        Message msg = new Message("Hello it's the message list !!!");
        messages.add(msg);

        GenericEntity<List<Message>> entity = new GenericEntity<List<Message>>(messages) {};

        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get all questions", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Question.class, responseContainer="List"),
    })
    @Path("/users/questions/{mail}{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestions(@PathParam("mail") String mail, @PathParam("password") String password) {


        List<Question> questions = this.gm.getQuestions(mail, password);

        GenericEntity<List<Question>> entity = new GenericEntity<List<Question>>(questions) {};

        return Response.status(201).entity(entity).build();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////// POST /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @POST
    @ApiOperation(value = "login", notes = "Realitzar el login")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/users/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logIn(VOCredentials credentials) {
        System.out.println("-----LOGIN-----");
        System.out.println("Mail: "+ credentials.getMail());
        User u = this.gm.loginUsuario(credentials);
        if (u==null)
            return Response.status(500).build();
        else
            return Response.status(201).entity(u).build();
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

    @POST
    @ApiOperation(value = "create a question", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Question.class),
            @ApiResponse(code = 400, message = "Message is unreachable")

    })

    @Path("/users/question")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newQuestion(Question question) {

        Question q = this.gm.addQuestion(question);

        if (q == null) {return Response.status(400).build();}
        else {return Response.status(201).entity(q).build();}
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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////// PUT /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PUT
    @ApiOperation(value = "update an User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",  response= User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User u) {

        User user = this.gm.updateUser(u);

        if (user == null) {return Response.status(404).build();}
        else {return Response.status(201).entity(u).build();}
    }

    @PUT
    @ApiOperation(value = "Buy an item", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Item.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/users/shop/buy/{mail}&{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyItem(Item i, @PathParam("mail") String mail, @PathParam("password") String password) {

        User u = this.gm.authentification(mail, password);
        Item item = this.gm.buyItem(u, i);

        if (item == null) {return Response.status(404).build();}
        else {return Response.status(201).entity(i).build();}
    }

    @PUT
    @ApiOperation(value = "Sell an item", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Item.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/users/shop/sell/{mail}&{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sellItem(Item i, @PathParam("mail") String mail, @PathParam("password") String password) {

        User u = this.gm.authentification(mail, password);
        Item item = this.gm.sellItem(u, i);

        if (item == null) {return Response.status(404).build();}
        else {return Response.status(201).entity(i).build();}
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////// DELETE /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DELETE
    @ApiOperation(value = "delete an User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/users/delete/{mail}&{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("mail") String mail, @PathParam("password") String password) {


        User t = this.gm.deleteUser(mail, password);

        if (t == null) {return Response.status(404).build();}
        else {return Response.status(201).entity(t).build();}
    }

}
