package org.facturacion.db;

import org.facturacion.models.Cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ClienteRepo {
    private final Map<Integer, Cliente> clientes;

    public ClienteRepo() {
        clientes = new HashMap<>();

    }

    public Map<Integer, Cliente> getClientes() {
        ConnectionDB connectionDB = ConnectionSingleton.CONNECTION_DB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {

            try (Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM Cliente")) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        String apellido = rs.getString("apellido");
                        int nit = rs.getInt("nit");
                        clientes.put(id, new Cliente(id, nombre, apellido, nit));
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return clientes;
    }

    public Cliente getClienteById(int id) {
        return getClientes().get(id);
    }
}
