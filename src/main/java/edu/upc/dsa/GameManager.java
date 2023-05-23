package edu.upc.dsa;

import edu.upc.dsa.models.User;
import edu.upc.dsa.models.VOCredentials;
import edu.upc.dsa.models.Item;

import java.util.List;

public interface GameManager {
    public User addUser(String  mail, String username, String password);
    public User addUser(User u);
    public User getUser (int id);
    public int sizeItems();

    public Item addItem(Item i);
    public Item addItem(String name, String description, int price, int damage, int health, String image);
    public Item addItem(String name, String description, int price, int damage, int health);
    public Item getItem (int id);
    public List<Item> getAllItems();

    public User loginUsuario(VOCredentials credenciales);

    //public VOCredentials getCredentials(User u);
    public List<User> getUsers();
    public User deleteUser(int id);
    public User updateUser(User u);
    public User authentification(String mail, String password);

    public int size();

}
