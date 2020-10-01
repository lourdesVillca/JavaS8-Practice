package org.facturacion.db;

import org.facturacion.models.Cliente;
import org.facturacion.models.Factura;
import org.facturacion.models.ItemFactura;
import org.facturacion.models.Producto;

import java.sql.*;
import java.util.*;

public class FacturaRepo {
    private final Map<Integer, Factura> facturas;

    public FacturaRepo() {
        facturas = new HashMap<>();
    }

    public Collection<Factura> getFacturas() {
        ConnectionDB connectionDB = ConnectionSingleton.CONNECTION_DB.getConnectionDB();
        try (Connection connection = connectionDB.getConnection()) {

            try (Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM Factura")) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        int numero = rs.getInt("numero");
                        String fecha = rs.getString("fecha");
                        int cliente_id = rs.getInt("cliente_id");
                        ClienteRepo clienteRepo = new ClienteRepo();
                        Cliente cliente = clienteRepo.getClienteById(cliente_id);
                        ItemFacturaRepo itemFacturaRepo = new ItemFacturaRepo();
                        Collection<ItemFactura> items = itemFacturaRepo.getItemsFactura(id);
                        Factura factura = new Factura(id, numero, fecha, cliente);
                        factura.setItems(items);
                        facturas.put(id, factura);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return facturas.values();
    }

    public void addFactura(Factura factura) {
        ConnectionDB connectionDB = ConnectionSingleton.CONNECTION_DB.getConnectionDB();
        String query = "INSERT INTO Factura(numero, fecha, cliente_id) VALUES (?, ?, ?);";
        try (Connection connection = connectionDB.getConnection()) {
            try (PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                pst.setInt(1, factura.getNumero());
                pst.setString(2, factura.getFecha());
                pst.setInt(3, factura.getCliente().getId());
                int affectedRows = pst.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating Factura Fails, no rows affected.");
                }
                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        ItemFacturaRepo itemFacturaRepo = new ItemFacturaRepo();
                        itemFacturaRepo.addItemsFactura(generatedKeys.getInt(1), factura.getItems());
                    }
                }

            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public Factura getFacturaById(int id) {
        getFacturas();  // se realiza esta llamada por que nos aseguramos de tener datos en el hashmap
        return facturas.get(id);
    }

    public Collection<ItemFactura> getItemsFactura(int id) {
        ItemFacturaRepo itemFacturaRepo = new ItemFacturaRepo();
        return itemFacturaRepo.getItemsFactura(id);
    }

    public boolean deleteFactura(int id) {
        ConnectionDB connectionDB = ConnectionSingleton.CONNECTION_DB.getConnectionDB();
        String query = "DELETE FROM Factura WHERE id=?";
        ItemFacturaRepo itemFacturaRepo = new ItemFacturaRepo();
        if (itemFacturaRepo.deleteItemFactura(id)) {
            try (Connection connection = connectionDB.getConnection()) {
                try (PreparedStatement pst = connection.prepareStatement(query)) {
                    pst.setInt(1, id);
                    int affectedRows = pst.executeUpdate();
                    if (affectedRows == 0) {
                        throw new SQLException("Creating Factura Fails, no rows affected.");
                    }

                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        facturas.remove(id);
        return true;
    }
}
