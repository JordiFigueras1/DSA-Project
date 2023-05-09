package edu.upc.dsa.models;

public class Tienda {
    private String descripcion;
    private Integer precio;

    private String nombreObjeto;

    public Tienda(){}
    public Tienda(String nombreObjeto, String descripcion, Integer precio ){
        this.precio=precio;
        this.setDescripcion(descripcion);
        this.setNombreObjeto(nombreObjeto);
    }

    public String getNombreObjeto(){
        return nombreObjeto;
    }
    public void setNombreObjeto(String nombreObjeto){
        this.nombreObjeto=nombreObjeto;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }

    public Integer getPrecio() {
        return precio;
    }
    public  void setPrecio(Integer precio) {
        this.precio=precio;
    }
}
