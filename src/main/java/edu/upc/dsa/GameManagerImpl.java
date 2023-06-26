package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.upc.dsa.util.ObjectHelper;
import edu.upc.dsa.util.PasswordSecurity;
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

        LocalDate date = LocalDate.now();
        Message info = null;
        List<Item> items = null;
        logger.info("new user : " + u + " should be add");
        Session session = null;
        User user = null;
        int userID = 0;

        try {
            session = FactorySession.openSession();
            userID = session.getID(u);

            if (userID == 0) {
                user = u;
                items = this.getAllItems();
                user.setPassword(PasswordSecurity.encrypt(user.getPassword()));
                session.save(user);
                userID = session.getID(user);

                Level lvl = new Level(userID);
                session.save(lvl);
                for (Item i : items) {
                    info = new Message(session.getID(user), date + " : new item " + i.getName() + " is available for " + i.getPrice() + " coins.");
                    session.save(info);
                }
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

        System.out.println("mail : " + mail);
        System.out.println("password : " + password);

        Session session = null;
        User user = new User(mail, password, "Zidane");
        System.out.println(user);
        User u = null;
        int id = 0;

        try {
            session = FactorySession.openSession();
            id = session.getID(user);
            System.out.println(id);

            if (id == 0) {
                logger.info("user not found");
            } else {
                u = this.getUser(id);

                System.out.println(PasswordSecurity.decrypt(u.getPassword()));
                System.out.println(password);

                if (!(PasswordSecurity.decrypt(u.getPassword()).equals(password))) {
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
        LocalDate date = LocalDate.now();
        Message info = null;
        User user = null;
        User test = null;
        boolean isUpdate = false;

        try {
            session = FactorySession.openSession();
            int i = session.getID(u);
            if (i == 0) {
                logger.warn("User doesn't exist");
                return null;
            }
            test = getUser(i);
            u.setPassword(PasswordSecurity.encrypt(u.getPassword()));
            if ((test.getPassword().equals(u.getPassword())) && test.getUsername().equals(u.getUsername())) {

                logger.warn("you don't change anything");
                return null;
            }

            isUpdate = session.update(u);
            if (isUpdate) {
                info = new Message(i, date + " : your profile is updated !");
                session.save(info);
                user = u;
                user.setPassword(PasswordSecurity.decrypt(user.getPassword()));
                logger.info("User updated");
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
        List<User> users = null;
        Message info = null;
        Session session = null;
        Item item = null;
        int itemID = 0;
        LocalDate date = LocalDate.now();

        try {
            session = FactorySession.openSession();
            itemID = session.getID(i);

            if (itemID == 0) {
                item = i;
                session.save(item);
                users = this.getUsers();
                for (User u : users) {
                    info = new Message(session.getID(u), date + " : new item " + i.getName() + " is available for " + i.getPrice() + " coins.");
                    session.save(info);
                }
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

        LocalDate date = LocalDate.now();
        Message info = null;
        Session session = null;
        boolean isUpdate;
        int price = item.getPrice();
        int budget = user.getCoins();
       try {
           session = FactorySession.openSession();
           if ((budget - price) < 0) {
               logger.warn("you don't have enough money");
               return null;
           } else {
               user.setCoins(budget - price);
               int id = session.getID(user);
               info = new Message(id, date + " : you bought item " + item.getName() + " for " + price + " coins.");
               session.save(info);
               Inventory inv = this.addInInventory(user, item);
               if (inv != null) {
                   isUpdate = session.update(user);
                   if (isUpdate) {
                       logger.info("substraction of the money for the deal");
                   }
                   return item;
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
    }

    @Override
    public Item sellItem(User user, Item item) {

        LocalDate date = LocalDate.now();
        Message info = null;
        Session session = null;
        boolean isUpdate;
        int price = item.getPrice();
        int budget = user.getCoins();
        try {
            session = FactorySession.openSession();
            user.setCoins(budget + price);
            int id = session.getID(user);
            info = new Message(id, date + " : you sold item " + item.getName() + " for " + item.getPrice() + " coins.");
            session.save(info);
            this.deleteInInventory(user, item);
            isUpdate = session.update(user);
            if (isUpdate) {
                logger.info("Addition of the money for the sell");
            }
            return item;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Item> getItemInInventory(String mail, String password) {

        User user = this.authentification(mail, password);
        Session session = null;
        List<Item> items = new ArrayList<>();
        int userID = 0;
        int itemId = 0;
        Inventory inv = null;
        Item item = null;

        try {
            session = FactorySession.openSession();
            userID = session.getID(user);
            if (userID == 0) {
                logger.warn("user is not exist");
                return null;
            }
            inv = (Inventory) session.getByID(Inventory.class, userID);
            if (inv == null) {
                return items;
            } else {
                String[] fields = ObjectHelper.getFields(inv);
                int n = fields.length;
                for (int i = 1; i < n; i++) {
                    itemId = (int) ObjectHelper.getter(inv, fields[i]);
                    if (itemId != 0) {
                        item = (Item) session.getByID(Item.class, itemId);
                        items.add(item);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return items;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////// QUESTION ////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Question questionRequestToQuestion(QuestionRequest qr) {

        Session session = null;
        Question q = null;
        try {
            session = FactorySession.openSession();
            int sender = session.getID(new User(qr.getSender(), "", ""));
            q = new Question(sender, qr.getDate(), qr.getTitle(), qr.getMessage());

        } catch (Exception e) {
        } finally {
            session.close();
        }
        return q;
    }
    public QuestionRequest questionToQuestionRequest(Question q) {

        Session session = null;
        QuestionRequest qr = null;
        try {
            session = FactorySession.openSession();
            User u = (User) session.getByID(User.class, q.getSender());
            qr = new QuestionRequest(u.getMail(), q.getDate(), q.getTitle(), q.getMessage());

        } catch (Exception e) {
        } finally {
            session.close();
        }
        return qr;
    }

    public QuestionRequest addQuestion(QuestionRequest qr) {

        Message info = null;
        LocalDate date = LocalDate.now();
        logger.info("new question : " + qr + " should be add");
        Question q = this.questionRequestToQuestion(qr);
        Session session = null;
        Question question = null;
        List<String> args = new ArrayList<>();
        args.add("sender");
        args.add("title");

        try {
            session = FactorySession.openSession();
            List<Question> qs = session.findAll(q, args);
            int n = qs.size();
            if (n == 0) {
                question = q;
                session.save(q);
                info = new Message(q.getSender(), date + " : you asked for a new question.");
                session.save(info);
                logger.info("new question " + q + " added");

            } else {
                logger.warn("question is already existing for this title : you have to change the title");
            }

        } catch (Exception e) {
        } finally {
            session.close();
        }
        return this.questionToQuestionRequest(q);
    }

    public List<QuestionRequest> getQuestions(String mail, String password) {

        User u = this.authentification(mail, password);
        Session session = null;
        List<Question> questions = new ArrayList<>();
        List<QuestionRequest> qrs = new ArrayList<>();
        List<String> args = new ArrayList<>();

        args.add("sender");

        try {
            session = FactorySession.openSession();
            int id = session.getID(u);
            Question question = new Question(id, "", "", "");
            questions = session.findAll(question, args);
            logger.info("questions are : "+ questions);
        } catch (Exception e) {
        } finally {
            session.close();
        }
        for (int i = 0; i < questions.size(); i++) {
            qrs.add(this.questionToQuestionRequest(questions.get(i)));
        }
        return qrs;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
     ///////////////////////////////// After this line, none database implemented ////////////////////////////
      ///////////////////////////////////////////////////////////////////////////////////////////////////////

    public Message messageRequestToMessage(MessageRequest mr) {

        Session session = null;
        Message m = null;
        try {
            session = FactorySession.openSession();
            int receiver = session.getID(new User(mr.getReceiver(), "", ""));
            m = new Message(receiver, mr.getMessage());

        } catch (Exception e) {
        } finally {
            session.close();
        }
        return m;
    }
    public MessageRequest messageToMessageRequest(Message m) {

        Session session = null;
        MessageRequest mr = null;
        try {
            session = FactorySession.openSession();
            User u = (User) session.getByID(User.class, m.getReceiver());
            mr = new MessageRequest(u.getMail(), m.getMessage());

        } catch (Exception e) {
        } finally {
            session.close();
        }
        return mr;
    }
    public List<MessageRequest> getMessages(String mail, String password) {

        User u = this.authentification(mail, password);
        Session session = null;
        List<Message> messages = new ArrayList<>();
        List<MessageRequest> msgs = new ArrayList<>();
        List<String> args = new ArrayList<>();

        args.add("receiver");

        try {
            session = FactorySession.openSession();
            int id = session.getID(u);
            Message message = new Message(id, "");
            messages = session.findAll(message, args);
            logger.info("messages are : "+ messages);
        } catch (Exception e) {
        } finally {
            session.close();
        }
        int n = messages.size();
        for (int i = n-1; i >= 0; i--) {
            msgs.add(this.messageToMessageRequest(messages.get(i)));
        }
        return msgs;
    }


    public Level getLevel(User user) {

        int id;
        Session session = null;
        Level level = null;

        try {
            session = FactorySession.openSession();
            id = session.getID(user);
            logger.info("we want to get the levels of " + user.getUsername());
            level = (Level) session.getByID(level.getClass(), id);

        } catch (Exception e) {
        } finally {
            session.close();
        }
        if (user == null) {
            logger.info("user does not exist !");
        }
        return level;
    }
    public Level updateLevel(User user, int nlevel, int score) {

        Session session = null;
        LocalDate date = LocalDate.now();
        Message info = null;
        Level lvl = null;
        boolean isUpdate = false;

        try {
            session = FactorySession.openSession();
            lvl = this.getLevel(user);
            String property = "level" + nlevel;
            System.out.println(property);
            System.out.println(lvl);
            int actualScore = (int) ObjectHelper.getter(lvl, property);

            if (score > actualScore) {
                ObjectHelper.setter(lvl, property, score);
                isUpdate = session.update(lvl);
            }
            if (isUpdate) {
                info = new Message(lvl.getId(), date + " : your levels are updated !");
                session.save(info);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lvl;

    }
    public List<Score> getScores() {

        List<Score> scores = new ArrayList<>();
        List<User> users = new ArrayList<>();
        Level lvl = null;

        users = this.getUsers();
        for (User u : users) {
            lvl = this.getLevel(u);
            scores.add(new Score(lvl));
        }
        Collections.sort(scores, Collections.reverseOrder(new ScoreComparator()));

        return scores;
    }
}
