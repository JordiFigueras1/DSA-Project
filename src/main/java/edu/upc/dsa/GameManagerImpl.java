package edu.upc.dsa;

import edu.upc.dsa.models.Track;
import edu.upc.dsa.models.User;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
public class GameManagerImpl implements GameManager{
    private static GameManager instance;
    protected List<User> users;
    final static Logger logger = Logger.getLogger(TracksManagerImpl.class);

    private GameManagerImpl() {
        this.users = new LinkedList<>();
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

        if (this.users.contains(u)) {
            logger.warn("user is already existing");
            return null;
        } else {
            this.users.add(u);
            logger.info("new user added");
            return u;
        }
    }

    public User addUser(String mail, String username, String password) {
        return this.addUser(new User(mail, username, password));
    }

    public User getUser(String mail, String password) {

        logger.info("we want to get user associated to " + mail);

        for (User u: this.users) {
            if (u.getMail().equals(mail)) {
                if (u.getPassword().equals(password)) {
                    logger.info("the user is " + u);
                    return u;
                } else {
                    logger.warn("password is wrong");
                    return null;
                }
            }
        }
        logger.warn("none user associated to : " + mail);
        return null;
    }
    public List<User> findAll() {
        return this.users;
    }

    @Override
    public User deleteUser(String mail, String password) {

        User u = this.getUser(mail, password);

        if (u==null) {
            logger.warn("none user associated to : " + mail);
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

        User t = this.getUser(u.getMail(), u.getPassword());

        if (t!=null) {
            logger.info(u + " rebut !!!! ");

            t.setUsername(u.getUsername());

            logger.info(t + " updated ");
        }
        else {
            logger.warn("none user associated to : " + u);
        }

        return t;
    }
}
