package org.facturacion.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CantidadProductoStats extends Producto {
    private int cantidadProductoVendidos;

    public CantidadProductoStats() {

    }

    public CantidadProductoStats(int id, String descripcion, double precioUnitario, int cantidadProductoVendidos) {
        super(id, descripcion, precioUnitario);
        this.cantidadProductoVendidos = cantidadProductoVendidos;
    }

    @JsonProperty
    public int getCantidadProductoVendidos() {
        return cantidadProductoVendidos;
    }

}
