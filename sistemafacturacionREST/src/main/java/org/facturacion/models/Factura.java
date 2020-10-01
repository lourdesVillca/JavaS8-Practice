package org.facturacion.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;

public class Factura {
    private int id;
    private int numero;
    private String fecha;
    private Cliente cliente;
    private Collection<ItemFactura> items;

    public Factura() {

    }

    public Factura(int id, int numero, String fecha, Cliente cliente) {
        this.id = id;
        this.numero = numero;
        this.cliente = cliente;
        this.fecha = fecha;
        this.items = new ArrayList<>();
    }

    @JsonProperty
    public int getNumero() {
        return numero;
    }

    @JsonProperty
    public double getTotal() {
        return items.stream().mapToDouble(i -> i.getPrecioTotal()).sum();
    }

    @JsonProperty
    public Cliente getCliente() {
        return cliente;
    }

    @JsonProperty
    public Collection<ItemFactura> getItems() {
        return items;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public String getFecha() {
        return fecha;
    }

    public void setItems(Collection<ItemFactura> items) {
        this.items = items;
    }


}
