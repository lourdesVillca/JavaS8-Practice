package org.facturacion.resources;

import org.facturacion.models.ClienteStats;
import org.facturacion.models.CantidadProductoStats;
import org.facturacion.models.VentaProductoStats;
import org.facturacion.services.Facade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/api/v1/stats")
@Produces(MediaType.APPLICATION_JSON)
public class StatsController {

    @GET
    @Path("/productos")
    public Collection<CantidadProductoStats> getProductosVendidos(){
        return Facade.INSTANCE.getStatsService().getProductosVendidos();
    }

    @GET
    @Path("/ventas")
    public Collection<VentaProductoStats> getVentas(){
        return Facade.INSTANCE.getStatsService().getVentas();
    }

    @GET
    @Path("/clientes")
    public Collection<ClienteStats> getClientes(){
        return Facade.INSTANCE.getStatsService().getClientes();
    }
}
