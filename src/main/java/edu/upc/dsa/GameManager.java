package edu.upc.dsa;

import edu.upc.dsa.models.User;
import edu.upc.dsa.models.VOCredentials;

import java.util.List;

public interface GameManager {
    public User addUser(String  mail, String username, String password);
    public User addUser(User u);
    public User getUser (String id);
    public int sizeObjects();

    public User loginUsuario(VOCredentials credenciales);

    //public VOCredentials getCredentials(User u);
    public List<User> findAll();
    public User deleteUser(String id);
    public User updateUser(User u);
    public User authentification(String mail, String password);

    public int size();

}
