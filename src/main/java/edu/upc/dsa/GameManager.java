package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.sql.SQLException;
import java.util.List;

public interface GameManager {
    public User addUser(String  mail, String username, String password);
    public User addUser(User u);
    public User getUser (int id);
    public int sizeItems();

    public Item addItem(Item i);
    public Item addItem(String name, String description, int price, int damage, int health, String type, String image);
    public Item addItem(String name, String description, int price, int damage, int health);
    public Item getItem (int id);
    public List<Item> getAllItems();

    public User loginUsuario(VOCredentials credenciales);

    //public VOCredentials getCredentials(User u);
    public List<User> getUsers();
    public User deleteUser(String mail, String password);
    public User updateUser(User u);
    public User authentification(String mail, String password);

    public int size();
    public Inventory getInventory (int id);
    public Inventory addInInventory(User user, Item item) throws SQLException;
    public Inventory deleteInInventory(User user, Item item) throws NoSuchMethodException;

    public Item buyItem(User user, Item item);
    public Item sellItem(User user, Item item);
    public List<Item> getItemInInventory(String mail, String password);

    public Question addQuestion(Question q);
    public List<Question> getQuestions(String mail, String password);
}
