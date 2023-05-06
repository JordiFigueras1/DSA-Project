package edu.upc.dsa.models;

public class User {

    // String id;
    String mail;
    String username;
    String password;

    //public Usuario() {this.id = RandomUtils.getId();}

    public User(String mail, String username, String password) {
        //this();
        this.setMail(mail);
        this.setUsername(username);
        this.setPassword(password);
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

    @Override
    public String toString() {
        return "Usuario [mail=" + mail + ", username=" + username +", password=" + password+"]";
    }

}
