package edu.upc.dsa;

import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.VOCredentials;
import edu.upc.dsa.models.Item;

import java.util.List;

import edu.upc.dsa.util.ObjectHelper;
import org.apache.log4j.Logger;
public class GameManagerImpl implements GameManager{
    private static GameManager instance;
    final static Logger logger = Logger.getLogger(GameManagerImpl.class);

    private GameManagerImpl() {
    }

    public static GameManager getInstance() {
        if (instance==null) instance = new GameManagerImpl();
        return instance;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////// USERS ////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

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

    @Override
    public User updateUser(User u) {

        Session session = null;
        User user = null;
        boolean isUpdate = false;

        try {
            session = FactorySession.openSession();
            isUpdate = session.update(u);
            if (isUpdate) {
                user = u;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public User deleteUser(String mail, String password) {

        Session session = null;
        User user = null;
        Inventory inv = null;
        int id = 0;

        try {
            session = FactorySession.openSession();
            user = this.authentification(mail, password);
            id = session.getID(user);
            inv = this.getInventory(id);

            if (id == 0) {
                logger.warn("user isn't exist in database");
                return null;
            } else {
                session.delete(user);
                session.delete(inv);
                logger.info("user : "+ user +" deleted");
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////// ITEMS ////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Item addItem(Item i) {

        logger.info("new item : " + i + " should be add");
        Session session = null;
        Item item = null;
        int userID = 0;

        try {
            session = FactorySession.openSession();
            userID = session.getID(i);

            if (userID == 0) {
                item = i;
                session.save(item);
                logger.info("new item " + i + " added");
            } else {
                logger.warn("item is already existing for this mail");
            }
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return item;
    }

    public Item addItem(String name, String description, int price, int damage, int health, String type, String image) {
        return this.addItem(new Item(name, description, price, damage, health, type, image));
    }

    public Item addItem(String name, String description, int price, int damage, int health) {
        return addItem(name, description, price, damage, health, "", null);
    }

    public Item getItem(int id) {

        logger.info("we want to get item associated to id " + id);

        Session session = null;
        Item item = null;

        try {
            session = FactorySession.openSession();
            item = (Item) session.getByID(Item.class, id);

        } catch (Exception e) {
        } finally {
            session.close();
        }
        if (item != null) {
            logger.info("the item is " + item);
        } else {
            logger.info("item not found");
        }
        return item;
    }

    @Override
    public List<Item> getAllItems() {

        Session session = null;
        List<Item> items = null;

        try {
            session = FactorySession.openSession();
            items = session.findAll(Item.class);
            logger.info("items are : " + items);


        } catch (Exception e) {
        } finally {
            session.close();
        }
        return items;
    }

    @Override
    public int sizeItems() {
        int ret = this.getAllItems().size();
        logger.info("size " + ret);

        return ret;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////// INVENTORY ////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Inventory getInventory (int id) {

        logger.info("we want to get inventory associated to id " + id);

        Session session = null;
        Inventory inv = null;

        try {
            session = FactorySession.openSession();
            inv = (Inventory) session.getByID(Inventory.class, id);

        } catch (Exception e) {
        } finally {
            session.close();
        }
        if (inv != null) {
            logger.info("the inventory is " + inv);
        } else {
            logger.info("inventory not found");
        }
        return inv;
    }

    @Override
    public Inventory addInInventory(User user, Item item) {

        Session session = null;
        Inventory inventory;
        Inventory myInventory;

        try {
            session = FactorySession.openSession();
            int idUser = session.getID(user);
            int idItem = session.getID(item);
            inventory = (Inventory) session.getByID(Inventory.class, idUser);

            if ((inventory == null) && (idItem != 0) && (idUser != 0)) {
                myInventory = new Inventory(idUser);
                myInventory.setItem1(idItem);
                session.save(myInventory);
                logger.info("Creation of the inventory & add of the item : " + item + " in the inventory of the user : " + user);
                return myInventory;
            } else if ((idItem != 0) && (idUser != 0)){
                String[] fields = ObjectHelper.getFields(inventory);
                int n = fields.length;
                int i = 1;
                while (i<n) {
                    int idI = (int) ObjectHelper.getter(inventory, fields[i]);
                    if (idI == 0) {
                        ObjectHelper.setter(inventory, fields[i], idItem);
                        session.update(inventory);
                        logger.info("Add of the item : " + item + " in the inventory of the user : " + user);
                        return inventory;
                    } else if (idI == idItem) {
                        logger.warn("you already have this item");
                        return null;
                    }
                    i ++;
                }
                logger.warn("Inventory is full");
                return null;
            } else {
                logger.warn("User or item don't exist anymore");
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        logger.warn("User or item don't exist anymore");
        return null;
    }

    @Override
    public Inventory deleteInInventory(User user, Item item) throws NoSuchMethodException {

        Session session = null;
        Inventory inventory;

        try {
            session = FactorySession.openSession();
            int idUser = session.getID(user);
            if (idUser == 0) {
                logger.warn("user isn't existing");
                return null;
            }
            int idItem = session.getID(item);
            if (idItem == 0) {
                logger.warn("user isn't existing");
                return null;
            }
            inventory = (Inventory) session.getByID(Inventory.class, idUser);

            if (inventory == null) {
                logger.warn("Inventory is empty !");
                return null;
            } else {
                String[] fields = ObjectHelper.getFields(inventory);
                int n = fields.length;
                int i = 1;
                while (i < n) {
                    int idI = (int) ObjectHelper.getter(inventory, fields[i]);
                    if (idI == idItem) {
                        ObjectHelper.setter(inventory, fields[i], 0);
                        session.update(inventory);
                        logger.info("item deleted to the inventory");
                        return inventory;
                    }
                    i++;
                }
                logger.warn("item isn't in the inventory");
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Item buyItem(User user, Item item) {

        int price = item.getPrice();
        int budget = user.getCoins();
       try {
           if ((budget - price) < 0) {
               logger.warn("you don't have enough money");
               return null;
           } else {
               user.setCoins(budget - price);
               this.addInInventory(user, item);
               this.updateUser(user);
               return item;
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
    }

    @Override
    public Item sellItem(User user, Item item) {

        int price = item.getPrice();
        int budget = user.getCoins();
        try {
            user.setCoins(budget + price);
            this.deleteInInventory(user, item);
            this.updateUser(user);
            return item;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
     ///////////////////////////////// After this line, none database implemented ////////////////////////////
      ///////////////////////////////////////////////////////////////////////////////////////////////////////
}
