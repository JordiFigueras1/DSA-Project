package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Usuario {

    String id;
    String mail;
    String username;
    String password;

    public Usuario() {
        this.id = RandomUtils.getId();
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id=id;
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
        return "Usuario [id="+id+", mail=" + mail + ", username=" + username +", password=" + password+"]";
    }

}
