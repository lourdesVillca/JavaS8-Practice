package org.facturacion.db;

import org.facturacion.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class StatsRepo {
    private final List<CantidadProductoStats> productos;
    private final List<VentaProductoStats> ventas;
    private final List<ClienteStats> clientes;

    public StatsRepo() {
        productos = new ArrayList<>();
        clientes = new ArrayList<>();
        ventas = new ArrayList<>();
    }

    public List<CantidadProductoStats> getProductosVendidos() {
        ConnectionDB connectionDB = ConnectionSingleton.CONNECTION_DB.getConnectionDB();
        String query = "SELECT sum(cantidad) as total_vendido, p.id as producto_id, descripcion, precio_unitario " +
                "FROM factura as f, Item_Factura as if, Producto as p " +
                "WHERE f.id=if.factura_id and if.producto_id=p.id group by producto_id order by total_vendido DESC";
        try (Connection connection = connectionDB.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(query)) {
                    while (rs.next()) {
                        int id = rs.getInt("producto_id");
                        String descripcion = rs.getString("descripcion");
                        double precioUnitario = rs.getDouble("precio_unitario");
                        int totalVendido = rs.getInt("total_vendido");

                        productos.add(new CantidadProductoStats(id, descripcion, precioUnitario,totalVendido));
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return productos;
    }

    public Collection<VentaProductoStats> getVentas() {
        ConnectionDB connectionDB = ConnectionSingleton.CONNECTION_DB.getConnectionDB();
        String query = "SELECT sum(cantidad*precio_unitario) as total_vendido,  p.id as producto_id, descripcion, precio_unitario FROM factura as f, Item_Factura as if, Producto as p where f.id=if.factura_id and if.producto_id=p.id group by producto_id order by total_vendido DESC";
        try (Connection connection = connectionDB.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(query)) {
                    while (rs.next()) {
                        int id = rs.getInt("producto_id");
                        String descripcion = rs.getString("descripcion");
                        double precioUnitario = rs.getDouble("precio_unitario");
                        int totalVendido = rs.getInt("total_vendido");
                        ventas.add(new VentaProductoStats(id, descripcion, precioUnitario,totalVendido));
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return ventas;
    }

    public Collection<ClienteStats> getClientes() {
        ConnectionDB connectionDB = ConnectionSingleton.CONNECTION_DB.getConnectionDB();
        String query = "SELECT sum(if.cantidad*p.precio_unitario) as total_comprado,  c.id as cliente_id, c.nombre, c.apellido, c.nit \n" +
                "FROM Factura as f, Item_Factura as if, Producto as p, Cliente as c \n" +
                "WHERE f.id=if.factura_id and if.producto_id=p.id and c.id=f.cliente_id group by cliente_id order by total_comprado DESC;";
        try (Connection connection = connectionDB.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(query)) {
                    while (rs.next()) {
                        int id = rs.getInt("cliente_id");
                        String nombre = rs.getString("nombre");
                        String apellido = rs.getString("apellido");
                        int nit = rs.getInt("nit");
                        double totalComprado = rs.getDouble("total_comprado");

                        clientes.add(new ClienteStats(id, nombre, apellido, nit, totalComprado));
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return clientes;
    }
}
