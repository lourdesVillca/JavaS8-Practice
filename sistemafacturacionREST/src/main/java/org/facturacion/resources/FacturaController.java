package org.facturacion.resources;


import org.facturacion.models.Factura;
import org.facturacion.models.ItemFactura;
import org.facturacion.services.Facade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

@Path("/api/v1/facturas")
@Produces(MediaType.APPLICATION_JSON)
public class FacturaController {
    @GET
    public Collection<Factura> getFacturas(@QueryParam("nit") long nit, @QueryParam("fecha") String fecha,
                                           @QueryParam("item") final List<String> items) {
        if (!items.isEmpty()) {
            return Facade.INSTANCE.getFacturaService().getFacturaByItems(items);
        } else if (nit != 0) {
            return Facade.INSTANCE.getFacturaService().getFacturaByNit(nit);
        } else if (fecha != null) {
            return Facade.INSTANCE.getFacturaService().getFacturaByFecha(fecha);
        } else return Facade.INSTANCE.getFacturaService().getFacturas();
    }

    @GET
    @Path("/{id}")
    public Response getFactura(@PathParam("id") int id) {
        Factura factura = Facade.INSTANCE.getFacturaService().getFacturaById(id);
        if (factura == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(factura).build();
    }

    @GET
    @Path("/{id}/items")
    public Response getItemsFactura(@PathParam("id") int id) {
        Collection<ItemFactura> itemsFactura = Facade.INSTANCE.getFacturaService().getItemsFactura(id);
        if(itemsFactura.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(itemsFactura).build();
    }

    @POST
    public Response crearFactura(Factura factura) {
        Facade.INSTANCE.getFacturaService().addFactura(factura);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteFactura(@PathParam("id") int id) {
        Facade.INSTANCE.getFacturaService().deleteFactura(id);
        return Response.ok().build();
    }
}
