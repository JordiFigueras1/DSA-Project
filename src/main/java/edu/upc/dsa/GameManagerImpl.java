package edu.upc.dsa;

import edu.upc.dsa.models.Track;
import edu.upc.dsa.models.Usuario;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
public class GameManagerImpl implements GameManager{
    private static GameManager instance;
    protected List<Usuario> users;
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

    public Usuario addUser(Usuario u) {
        logger.info("new Track " + u);

        this.users.add (u);
        logger.info("new Track added");
        return u;
    }

    public Usuario addUser(String mail, String username, String password) {
        return this.addUser(new Usuario(mail, username, password));
    }

    public Usuario getUser(String id) {
        logger.info("getTrack("+id+")");

        for (Usuario u: this.users) {
            if (u.getId().equals(id)) {
                logger.info("getTrack("+id+"): "+u);

                return u;
            }
        }

        logger.warn("not found " + id);
        return null;
    }
    public List<Usuario> findAll() {
        return this.users;
    }

    @Override
    public void deleteUser(String id) {

        Usuario u = this.getUser(id);
        if (u==null) {
            logger.warn("not found " + u);
        }
        else logger.info(u+" deleted ");

        this.users.remove(u);

    }
    @Override
    public Usuario updateUser(Usuario p) {
        Usuario t = this.getUser(p.getId());

        if (t!=null) {
            logger.info(p+" rebut!!!! ");

            t.setMail(p.getMail());
            t.setUsername(p.getUsername());
            t.setPassword(p.getPassword());

            logger.info(t+" updated ");
        }
        else {
            logger.warn("not found "+p);
        }

        return t;
    }
}
