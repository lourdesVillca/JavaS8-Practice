package org.facturacion.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VentaProductoStats extends Producto {
    private double ventaTotal;

    public VentaProductoStats() {

    }

    public VentaProductoStats(int id, String descripcion, double precioUnitario, double ventaTotal) {
        super(id, descripcion, precioUnitario);
        this.ventaTotal = ventaTotal;
    }

    @JsonProperty
    public double getVentaTotal() {
        return ventaTotal;
    }

}
