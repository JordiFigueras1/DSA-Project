package edu.upc.dsa.models;

public class User {

    //String id;
    String mail;
    String password;
    String username;
    int lifePoint;
    int coins;


    public int getLifePoint() {
        return lifePoint;
    }

    public void setLifePoint(Integer lifePoint) {
        this.lifePoint = lifePoint;
    }

    public User() {}
    public User(String mail, String password, String username) {
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

    public int getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "User [mail=" + mail + ", username=" + username +", password=" + password + ", life points=" + lifePoint + "]";
    }

}
