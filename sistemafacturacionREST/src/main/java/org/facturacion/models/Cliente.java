package org.facturacion.models;

public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private long nit;
    public Cliente(){

    }
    public Cliente(int id, String nombre, String apellido, long nit){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nit = nit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getNit() {
        return nit;
    }

    public void setNit(long nit) {
        this.nit = nit;
    }
}
