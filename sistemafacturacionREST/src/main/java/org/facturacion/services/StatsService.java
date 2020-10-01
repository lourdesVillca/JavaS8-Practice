package org.facturacion.services;

import org.facturacion.db.StatsRepo;
import org.facturacion.models.ClienteStats;
import org.facturacion.models.CantidadProductoStats;
import org.facturacion.models.VentaProductoStats;

import java.util.Collection;

public class StatsService {
    private org.facturacion.db.StatsRepo StatsRepo;

    public StatsService() {
        StatsRepo = new StatsRepo();
    }

    public Collection<CantidadProductoStats> getProductosVendidos() {
        return StatsRepo.getProductosVendidos();
    }

    public Collection<VentaProductoStats> getVentas() {
        return StatsRepo.getVentas();
    }

    public Collection<ClienteStats> getClientes() {
        return StatsRepo.getClientes();
    }
}
