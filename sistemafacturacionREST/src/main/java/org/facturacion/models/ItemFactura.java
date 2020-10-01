package org.facturacion.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ItemFactura {
    private int id;
    private int cantidad;
    private Producto producto;

    public ItemFactura() {

    }

    public ItemFactura(int id, int cantidad, Producto producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.producto = producto;
    }

    @JsonProperty
    public final double getPrecioTotal() {
        return cantidad * producto.getPrecioUnitario();
    }

    @JsonProperty
    public final Producto getProducto() {
        return producto;
    }


    @JsonProperty
    public int getCantidad() {
        return cantidad;
    }


}
