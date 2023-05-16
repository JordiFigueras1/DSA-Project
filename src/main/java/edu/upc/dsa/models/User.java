package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class User {

    String id;
    String mail;
    String username;
    String password;
    int lifePoint;
    int coins;


    public int getLifePoint() {
        return lifePoint;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    public User() {this.id = RandomUtils.getId();}
    public User(String mail, String username, String password) {
        this();
        this.setMail(mail);
        this.setUsername(username);
        this.setPassword(password);
        this.setLifePoint(100);
        this.setCoins(100);
    }

    public String getMail() {
        return this.mail;
    }
    public void setMail(String mail) {
        this.mail=mail;
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username=username;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password=password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "User [mail=" + mail + ", username=" + username +", password=" + password + ", life points=" + lifePoint + "]";
    }

}
