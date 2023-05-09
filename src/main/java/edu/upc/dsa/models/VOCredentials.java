package edu.upc.dsa.models;

public class VOCredentials {

    private String mail, password;

    public VOCredentials(){}
    public VOCredentials(String mail , String password){
        this();
        this.setMail(mail);
        this.setPassword(password);
    }

    public VOCredentials(User u) {
        this();
        this.setPassword(u.getPassword());
        this.setMail(u.getMail());
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
