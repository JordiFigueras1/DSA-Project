package edu.upc.dsa;

import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.VOCredentials;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
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

    @Test
    public void upadateTest() {
        GameManager gm = GameManagerImpl.getInstance();
        User u = new User("clement@gmail.com", "1234", "cacaboudin");
        User u1 = new User("clertyu", "1234", "cacaboudin");
        User user = gm.updateUser(u);
        User user1 = gm.updateUser(u1);
        Assert.assertEquals(user, u);
        Assert.assertEquals(user1, null);
    }


    //////////////////////////////////////////////// Items ////////////////////////////////////////////////

    @Test
    public void addItemTest() {
        GameManager gm = GameManagerImpl.getInstance();
        Item i = new Item("épée de fou", "", 1000, 1, 100,"", "");
        Item item = gm.addItem(i);
        Item item1 = gm.addItem(i);
        Item item2 = gm.addItem(new Item("épée de fou", "zdhzked", 0, 0, 0,"", ""));
        Assert.assertEquals(item, i);
        Assert.assertEquals(item1, null);
        Assert.assertEquals(item2, null);
    }

    @Test
    public void addInventoryTest() throws SQLException {
        GameManager gm = GameManagerImpl.getInstance();
        Inventory inv;
        Inventory inv1;
        Inventory inv2;
        Inventory inv3;
        Inventory inv4;
        Inventory inv5;
        Inventory inv6;
        Item i = gm.getItem(11);
        Item i1 = gm.getItem(12);
        Item i2 = gm.getItem(13);
        Item i3 = gm.getItem(14);
        Item item = new Item("épée de fou", "", 1000, 1, 100,"", "");
        User u = gm.getUser(82);
        User u1 = new User("clertyu", "1234", "cacaboudin");
        inv = gm.addInInventory(u, i);
        inv1 = gm.addInInventory(u, i1);
        inv2 = gm.addInInventory(u, i2);
        inv3 = gm.addInInventory(u, i3);
        inv4 = gm.addInInventory(u, item);
        inv5 = gm.addInInventory(u1, i);
        inv6 = gm.addInInventory(u, item);
        Assert.assertEquals(inv4, null);
        Assert.assertEquals(inv5, null);
        Assert.assertEquals(inv6, null);
    }

    @Test
    public void deleteInventoryTest() throws SQLException, NoSuchMethodException {
        GameManager gm = GameManagerImpl.getInstance();
        Inventory inv;
        Inventory inv1;
        Inventory inv2;
        Inventory inv3;

        Item i1 = gm.getItem(12);
        User u = gm.getUser(82);

        Item item = new Item("épée de fou", "", 1000, 1, 100,"", "");
        User u1 = new User("clertyu", "1234", "cacaboudin");

        inv = gm.deleteInInventory(u, i1);
        inv1 = gm.deleteInInventory(u, item);
        inv2 = gm.deleteInInventory(u1, i1);
        inv3 = gm.deleteInInventory(u1, item);

        Assert.assertEquals(inv1, null);
        Assert.assertEquals(inv2, null);
        Assert.assertEquals(inv3, null);
    }
}
