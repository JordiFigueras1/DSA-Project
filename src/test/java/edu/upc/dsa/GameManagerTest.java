package edu.upc.dsa;

import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.VOCredentials;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameManagerTest {

    //////////////////////////////////////////////// Users ////////////////////////////////////////////////

    @Test
    public void addUserTest() {
        GameManager gm = GameManagerImpl.getInstance();
        User u = new User("mama@gmail.com", "oool", "123");
        User user = gm.addUser(u);
        User sameUser1 = gm.addUser(u);
        User sameUser2 = gm.addUser(new User("mama@gmail.com", "zdhzked", "gejhdu"));
        Assert.assertEquals(user, u);
        Assert.assertEquals(sameUser1, null);
        Assert.assertEquals(sameUser2, null);
    }

    @Test
    public void getUserByIDTest() {
        GameManager gm = GameManagerImpl.getInstance();
        User user1;
        User user2;
        user1 = gm.getUser(47);
        user2 = gm.getUser(10);
        System.out.println(user1);
        Assert.assertEquals(user2, null);
    }

    @Test
    public void authentificationTest() {
        GameManager gm = GameManagerImpl.getInstance();
        VOCredentials v0 = new VOCredentials("mama@gmail.com", "oool");
        VOCredentials v1 = new VOCredentials("mama@gmail.com", "zedfghj");
        VOCredentials v2 = new VOCredentials("ooausi@gmail.com", "azertyui");
        User u = gm.loginUsuario(v0);
        User f1 = gm.loginUsuario(v1);
        User f2 = gm.loginUsuario(v2);
        Assert.assertEquals(f1, null);
        Assert.assertEquals(f2, null);
        Assert.assertEquals(u.getMail(), "mama@gmail.com");
        Assert.assertEquals(u.getPassword(), "oool");
        Assert.assertEquals(u.getUsername(), "123");
        Assert.assertEquals(u.getLifePoint(), 100);
        Assert.assertEquals(u.getCoins(), 100);
    }

    @Test
    public void findAllTest() {
        GameManager gm = GameManagerImpl.getInstance();
        List<User> users;
        users = gm.getUsers();
        System.out.println(users);
    }


    //////////////////////////////////////////////// Items ////////////////////////////////////////////////

    @Test
    public void addItemTest() {
        GameManager gm = GameManagerImpl.getInstance();
        Item i = new Item("épée de fou", "", 1000, 1, 100, "");
        Item item = gm.addItem(i);
        Item item1 = gm.addItem(i);
        Item item2 = gm.addItem(new Item("épée de fou", "zdhzked", 0, 0, 0, ""));
        Assert.assertEquals(item, i);
        Assert.assertEquals(item1, null);
        Assert.assertEquals(item2, null);
    }
}
