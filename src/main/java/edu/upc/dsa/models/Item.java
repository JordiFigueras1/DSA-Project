package edu.upc.dsa.models;

public class Item {
    //String id;
    String name;
    int price;
    int damage;
    int health;
    int nItems;
    String description;
    String image;

    public Item(){}

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

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public int getNItems() {
        return nItems;
    }

    public void setNItems(Integer nObjects) {
        this.nItems = nObjects;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
