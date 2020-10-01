package org.facturacion.models;

public class Producto {
    private int id;
    private String descripcion;
    private double precioUnitario;

    public Producto() {

    }

    public Producto(int id, String descripcion, double precioUnitario) {
        this.id = id;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
    }


    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
