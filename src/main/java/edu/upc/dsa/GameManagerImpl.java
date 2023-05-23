package edu.upc.dsa;

import edu.upc.dsa.models.User;
import edu.upc.dsa.models.VOCredentials;
import edu.upc.dsa.models.Item;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
public class GameManagerImpl implements GameManager{
    private static GameManager instance;
    protected List<Item> objects;
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    private GameManagerImpl() {
        this.objects = new LinkedList<>();
    }

    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.getUsers().size();
        logger.info("size " + ret);

        return ret;
    }

    public User addUser(User u) {

        logger.info("new user : " + u + " should be add");
        Session session = null;
        User user = null;
        int userID = 0;

        try {
            session = FactorySession.openSession();
            userID = session.getID(u);

            if (userID == 0) {
                user = u;
                session.save(user);
                logger.info("new user " + u + " added");
            } else {
                logger.warn("user is already existing for this mail");
            }
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return user;
    }

    public User addUser(String mail, String username, String password) {
        return this.addUser(new User(mail, password, username));
    }

    public User getUser(int id) {

        logger.info("we want to get user associated to id " + id);

        Session session = null;
        User user = null;

        try {
            session = FactorySession.openSession();
            user = (User) session.getByID(User.class, id);

        } catch (Exception e) {
        } finally {
            session.close();
        }
        if (user != null) {
            logger.info("the user is " + user);
        } else {
            logger.info("user not found");
        }
        return user;
    }

    @Override
    public User authentification(String mail, String password) {

        Session session = null;
        User user = new User(mail, password, "Zidane");
        User u = null;
        int id = 0;

        try {
            session = FactorySession.openSession();
            id = session.getID(user);

            if (id == 0) {
                logger.info("user not found");
            } else {
                u = this.getUser(id);

                if (!(u.getPassword().equals(password))) {
                    logger.warn("Password wrong");
                    return null;
                } else {
                    logger.warn("user found");
                    return u;
                }
            }
        } catch (Exception e) {
        } finally {
            session.close();
        }

        return null;
    }

    @Override
    public User loginUsuario(VOCredentials credenciales) {
        return this.authentification(credenciales.getMail(), credenciales.getPassword());
    }

    @Override
    public List<User> getUsers() {
        Session session = null;
        List<User> users = null;

        try {
            session = FactorySession.openSession();
            users = session.findAll(User.class);
            logger.info("users are : " + users);


        } catch (Exception e) {
        } finally {
            session.close();
        }
        return users;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
     ///////////////////////////////// After this line, none database implemented ////////////////////////////
      ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public Item addObject(Item o) {

        logger.info("new objeto : " + o + " should be add");

        this.objects.add(o);

        logger.info("new object " + o + " added");
        return o;
    }

    public Item addObject(String name, String description, int price, int damage, int health, String image) {
        return this.addObject(new Item(name, description, price, damage, health, image));
    }

    public Item addObject(String name, String description, int price, int damage, int health) {
      return addObject(name, description, price, damage, health, null);
    }

    @Override
    public int sizeObjects() {
        logger.info("El tamaño de la lista de objects es :" + objects.size());
        return this.objects.size();
    }

    @Override
    public List<Item> getAllObjects() {
        return this.objects;
    }

    @Override
    public User deleteUser(int id) {

        User u = this.getUser(id);

        if (u==null) {
            logger.warn("none user associated to : " + id);
        }
        else {
            logger.info(u +" deleted ");
        }
        return null;
    }

    // We will  need to do a function  to update only the password
    // Here the function is used to only change username (for the moment)
    @Override
    public User updateUser(User u) {
        return null;
    }
}
