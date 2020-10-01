package org.facturacion.services;

import org.facturacion.models.Factura;

import java.util.Collection;
import java.util.List;

public enum Facade {
    INSTANCE;
    private FacturaService facturaService;
    private StatsService statsService;

    Facade() {
        facturaService = new FacturaService();
        statsService = new StatsService();
    }

    public FacturaService getFacturaService() {
        return facturaService;
    }
    public StatsService getStatsService(){
        return statsService;
    }

}
