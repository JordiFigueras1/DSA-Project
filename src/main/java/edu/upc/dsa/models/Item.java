package edu.upc.dsa.models;

import edu.upc.dsa.util.ObjectHelper;

public class Item {
    //String id;
    String name;
    int price;
    int damage;
    int health;
    String type;
    String description;
    String image;

    public Item(){}

    public Item(String name, String description, int price, int damage, int health, String type, String image){
        this();
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
        this.setDamage(damage);
        this.setHealth(health);
        this.setType(type);
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

    public void setDamage(Integer damage) {
        this.damage = damage;
    }
    public int getDamage() {
        return damage;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isEquals(Item i) throws NoSuchMethodException {
        for (String field : ObjectHelper.getFields(this)){
            try {
                if (!(ObjectHelper.getter(this, field) == ObjectHelper.getter(i, field))) {
                    return false;
                }
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
