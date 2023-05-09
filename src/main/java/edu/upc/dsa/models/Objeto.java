package edu.upc.dsa.models;
import edu.upc.dsa.util.RandomUtils;
public class Objeto {
    String id;
    String nombre;
    Integer precio;
    Integer damage;
    Integer health;
    Integer nobjetos;
    String descripcion;
    public Objeto(){this.id =RandomUtils.getId();}

    public Objeto(String nombre, String descripcion){
        this();
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setPrecio(precio);
        this.setDamage(damage);
        this.setHealth(health);
        this.setNobjetos(nobjetos);

    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    public  int getPrecio(){
        return precio;
    }
    public void setPrecio(Integer precio){
        this.precio = precio;
    }
    public  int getDamage(){
        return damage;
    }
    public void setDamage(Integer damage){
        this.damage = damage;
    }

    public int getHealth(){
        return health;
    }
    public void setHealth(Integer health){
        this.health=health;
    }

    public int getNobjetos(){
        return nobjetos;
    }
    public void setNobjetos(Integer nobjetos){
        this.nobjetos=nobjetos;
    }

}
