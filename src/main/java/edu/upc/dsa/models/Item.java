package edu.upc.dsa.models;
import edu.upc.dsa.util.RandomUtils;
public class Item {
    //String id;
    String name;
    int price;
    int damage;
    int health;
    int nObjects;
    String description;
    String image;

    public Item(){}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Item(String name, String description, int price, int damage, int health, String image){
        this();
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
        this.setDamage(damage);
        this.setHealth(health);
        this.setImage(image);

    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getnObjects() {
        return nObjects;
    }

    public void setnObjects(int nObjects) {
        this.nObjects = nObjects;
    }
}
