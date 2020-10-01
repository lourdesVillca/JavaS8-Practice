package org.facturacion.services;

import org.facturacion.db.FacturaRepo;
import org.facturacion.models.Factura;
import org.facturacion.models.ItemFactura;

import java.util.*;
import java.util.stream.Collectors;

public class FacturaService {
    private FacturaRepo facturaRepo;

    public FacturaService() {
        facturaRepo = new FacturaRepo();
    }

    public Collection<Factura> getFacturas() {
        return facturaRepo.getFacturas();
    }

    public void addFactura(Factura factura) {
        facturaRepo.addFactura(factura);
    }

    public Factura getFacturaById(int id) {
        return facturaRepo.getFacturaById(id);
    }

    public Collection<ItemFactura> getItemsFactura(int id) {
        return facturaRepo.getItemsFactura(id);
    }

    public boolean deleteFactura(int id) {
        return facturaRepo.deleteFactura(id);
    }

    public Collection<Factura> getFacturaByNit(long arg) {
        return facturaRepo.getFacturas().stream().filter(f -> f.getCliente().getNit() == arg).collect(Collectors.toList());
    }

    public Collection<Factura> getFacturaByFecha(String fecha) {
        return facturaRepo.getFacturas().stream().filter(f -> f.getFecha().equals(fecha)).collect(Collectors.toList());
    }

    public Collection<Factura> getFacturaByItems(List<String> filterItems) {
        Map<Integer, Factura> facturas = new HashMap<>();
        for (Factura factura : facturaRepo.getFacturas()) {
            for (ItemFactura item : factura.getItems()) {
                if (filterItems.stream().anyMatch(str -> item.getProducto().toString().toLowerCase().contains(str.toLowerCase()))) {
                    facturas.put(factura.getId(), factura);
                }
            }
        }
        return facturas.values();
    }
}
