package edu.upc.dsa;

import edu.upc.dsa.models.Track;
import edu.upc.dsa.models.User;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

public interface GameManager {
    public User addUser(String  mail, String username, String password);
    public User addUser(User u);
    public User getUser (String id);
    public List<User> findAll();
    public User deleteUser(String id);
    public User updateUser(User u);
    public User authentification(String mail, String password);

    public int size();

}
