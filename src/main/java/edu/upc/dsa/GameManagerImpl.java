package edu.upc.dsa;

import edu.upc.dsa.models.User;
import edu.upc.dsa.models.VOCredentials;
import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.Tienda;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
public class GameManagerImpl implements GameManager{
    private static GameManager instance;
    protected List<User> users;
    protected List<Objeto> objetos;
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    private GameManagerImpl() {
        this.users = new LinkedList<>();
        this.objetos = new LinkedList<>();
    }

    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.users.size();
        logger.info("size " + ret);

        return ret;
    }

    public User addUser(User u) {

        logger.info("new user : " + u + " should be add");

        for (User a : this.users) {
            if (a.getMail().equals(u.getMail())){
                logger.warn("user is already existing for this mail");
                return null;
            }
        }
        this.users.add(u);
        logger.info("new user " + u + " added");
        return u;
    }

    public Objeto addObjeto(Objeto o) {

        logger.info("new objeto : " + o + " should be add");

        this.objetos.add(o);

        logger.info("new object " + o + " added");
        return o;
    }

    public Objeto addObjeto(String nombre, String descripcion, int precio, int damage, int health, String image) {
        return this.addObjeto(new Objeto(nombre, descripcion, precio, damage, health, image));
    }

    public Objeto addObjeto(String nombre, String descripcion, int precio, int damage, int health) {
      return addObjeto(nombre, descripcion, precio, damage, health, null);
    }

    public User addUser(String mail, String username, String password) {
        return this.addUser(new User(mail, username, password));
    }

    public User getUser(String id) {

        logger.info("we want to get user associated to " + id);

        for (User u: this.users) {
            if (u.getId().equals(id)) {
                    logger.info("the user is " + u);
                    return u;
            }
        }
        logger.warn("none user associated to : " + id);
        return null;
    }

   @Override
    public User loginUsuario(VOCredentials credenciales) {
       logger.info("login: " + credenciales.getMail());

       for (User u : this.users) {
           if (u.getMail().equals(credenciales.getMail()) && u.getPassword().equals(credenciales.getPassword())) {
               logger.info("user loggeado satisfactoriamente");
               return u;
           }
       }

       logger.warn("user not found or password is incorrect");
       return null;
    }

    @Override
    public int sizeObjects() {
        logger.info("El tamaño de la lista de objetos es :" + objetos.size());
        return this.objetos.size();
    }

    @Override
    public List<Objeto> getAllObjects() {
        return this.objetos;
    }
    @Override
    public List<User> findAll() {
        return this.users;
    }

    @Override
    public User deleteUser(String id) {

        User u = this.getUser(id);

        if (u==null) {
            logger.warn("none user associated to : " + id);
        }
        else {
            logger.info(u +" deleted ");
            this.users.remove(u);
        }
        return u;
    }

    // We will  need to do a function  to update only the password
    // Here the function is used to only change username (for the moment)
    @Override
    public User updateUser(User u) {

        User t = this.getUser(u.getId());

        if (t!=null) {
            logger.info(u + " rebut !!!! ");

            t.setUsername(u.getUsername());
            t.setMail(u.getMail());
            t.setPassword(u.getPassword());

            logger.info(t + " updated ");
        }
        else {
            logger.warn("none user associated to : " + u);
        }

        return t;
    }

    @Override
    public User authentification(String mail, String password) {

        for (User c: this.users) {
            if (c.getMail().equals(mail)) {
                if (c.getPassword().equals(password)) {
                    logger.info("user found");
                    return c;
                } else {
                    logger.warn("Password wrong");
                    return null;
                }
            }
        }
        logger.warn("user not found");
        return null;
    }
}
