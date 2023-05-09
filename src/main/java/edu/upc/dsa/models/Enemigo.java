package edu.upc.dsa.models;

public class Enemigo {
    public String idenemigo;
    public Integer velmov;
    public Integer velad;

    public  Enemigo (){}

    public Enemigo (String idenemigo, Integer velmov, Integer velad){
        this.setIdenemigo(idenemigo);
        this.setVelmov(velmov);
        this.setVelad(velad);
    }
    public String getIdenemigo(){
        return idenemigo;
    }
    public void setIdenemigo(String  idenemigo){
        this.idenemigo=idenemigo;
    }
    public Integer getVelmov(){
        return velmov;
    }
    public void setVelmov(Integer velmov){
        this.velmov=velmov;
    }
    public Integer getVelad(){
        return velad;
    }
    public void setVelad(Integer velad){
        this.velad= velad;
    }

}
