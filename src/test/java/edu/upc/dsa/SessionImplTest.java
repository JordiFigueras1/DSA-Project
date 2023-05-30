package edu.upc.dsa;

import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionImplTest {

    @Test
    public void getIDTest1() throws SQLException {
        Session session = null;

        User u = new User("arin@gmail.com", "mdolle", "119");
        int id = -1;

        try {
            session = FactorySession.openSession();
            id = session.getID(u);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        Assert.assertEquals(id, 0);
    }

    @Test
    public void getIDTest2() throws SQLException {
        Session session = null;

        User u = new User("marin@gmail.com", "mdolle", "119");
        int id = -1;

        try {
            session = FactorySession.openSession();
            id = session.getID(u);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        Assert.assertEquals(id, 1);
    }

    @Test
    public void saveTest() throws SQLException {
        Session session = null;

        User u = new User("marin1444@gmail.com", "mdolle", "119");

        try {
            session = FactorySession.openSession();
            session.save(u);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void findAllTest() throws SQLException {
        Session session = null;

        User u = new User("marin1444@gmail.com", "mdolle", "119");
        User t = new User("marin1@gmail.com", "mdolle", "119");
        List<User> users1 = new ArrayList<>();
        List<User> users2 = new ArrayList<>();
        List<User> users3 = new ArrayList<>();
        List<User> users4 = new ArrayList<>();
        users3.add(u);
        users4.add(u);
        users4.add(t);

        try {
            session = FactorySession.openSession();
            session.save(u);
            session.save(t);
            users2 = session.findAll(User.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        //Assert.assertEquals(users1, users3);
        Assert.assertEquals(users2, users4);
    }

    @Test
    public void updateTest() throws SQLException {
        Session session = null;

        User u = new User("jordi@gmail.com", "1234", "119");
        User t = new User("marO@gmail.com", "mdolle", "119");

        try {
            session = FactorySession.openSession();
            boolean herve = session.update(u);
            boolean daniel = session.update(t);
            session.save(t);
            t.setUsername("OlivierGiroud");
            boolean sarah = session.update(t);

            Assert.assertTrue(herve);
            Assert.assertFalse(daniel);
            Assert.assertTrue(sarah);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void createInventoryTest() throws SQLException {

        Session session = null;

        try {
            session = FactorySession.openSession();
            session.createInventory(Item.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Test
    public void deleteTest() {
        Session session = null;
        User user = new User("jordi@gmail.com", "1234", "Jordi");

        try {
            session = FactorySession.openSession();
            session.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
