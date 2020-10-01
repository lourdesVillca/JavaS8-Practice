package org.facturacion.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClienteStats extends Cliente {
    private double montoTotalCompras;

    public ClienteStats() {

    }

    public ClienteStats(int id, String nombre, String apellido, long nit, double montoTotalCompras) {
        super(id, nombre, apellido, nit);
        this.montoTotalCompras = montoTotalCompras;
    }

    @JsonProperty
    public double getMontoTotalCompras() {
        return montoTotalCompras;
    }
}
