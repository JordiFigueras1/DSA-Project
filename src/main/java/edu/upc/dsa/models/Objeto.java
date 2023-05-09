package edu.upc.dsa.models;
import edu.upc.dsa.util.RandomUtils;
public class Objeto {
    String id;
    String nombre;
    int precio;
    int damage;
    int health;
    int nobjetos;
    String descripcion;

    String image;
    public Objeto(){this.id =RandomUtils.getId();}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Objeto(String nombre, String descripcion, int precio, int damage, int health, String image){
        this();
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setPrecio(precio);
        this.setDamage(damage);
        this.setHealth(health);
        this.setImage(image);

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


    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
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

    public int getNobjetos() {
        return nobjetos;
    }

    public void setNobjetos(int nobjetos) {
        this.nobjetos = nobjetos;
    }
}
