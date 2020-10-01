package org.facturacion.db;

import org.facturacion.models.Cliente;
import org.facturacion.models.Producto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ProductoRepo {
    private final Map<Integer, Producto> productos;

    public ProductoRepo() {
        productos = new HashMap<>();

    }

    public Map<Integer, Producto> getProductos() {
        ConnectionDB connectionDB = ConnectionSingleton.CONNECTION_DB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {

            try (Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM Producto")) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String descripcion = rs.getString("descripcion");
                        double precioUnitario = rs.getDouble("precio_unitario");
                        productos.put(id, new Producto(id, descripcion, precioUnitario));
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return productos;
    }

    public Producto getProductoById(int id) {
        return getProductos().get(id);
    }
}
