package org.facturacion.db;

import org.facturacion.models.ItemFactura;
import org.facturacion.models.Producto;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ItemFacturaRepo {
    private Map<Integer, ItemFactura> facturaItems;

    public ItemFacturaRepo(){
        facturaItems = new HashMap<>();
    }
    public Collection<ItemFactura> getItemsFactura(int idFactura){
        ConnectionDB connectionDB = ConnectionSingleton.CONNECTION_DB.getConnectionDB();
        String query = "SELECT if.id as item_id, p.id as producto_id, descripcion, cantidad, precio_unitario FROM factura as f, Item_Factura as if, Producto as p where f.id=if.factura_id and if.producto_id=p.id and f.id=?";
        try(Connection connection= connectionDB.getConnection()){
            try(PreparedStatement pst= connection.prepareStatement(query)) {
                pst.setInt(1,idFactura);
                try(ResultSet rs =pst.executeQuery()){
                    while(rs.next()){
                        int itemId= rs.getInt("item_id");
                        int productoId = rs.getInt("producto_id");
                        String descripcion = rs.getString("descripcion");
                        int cantidad = rs.getInt("cantidad");
                        double precioUnitario = rs.getDouble("precio_unitario");
                        ProductoRepo productoRepo = new ProductoRepo();
                        Producto producto = productoRepo.getProductoById(productoId);
                        facturaItems.put(itemId,new ItemFactura(itemId,cantidad, producto));
                    }
                }
            }
        }
        catch (SQLException |  ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return facturaItems.values();
    }

    public void addItemsFactura(int facturaId, Collection<ItemFactura> items) {
        ConnectionDB connectionDB = ConnectionSingleton.CONNECTION_DB.getConnectionDB();
        String query = "INSERT INTO Item_Factura(cantidad, producto_id, factura_id) VALUES (?, ?, ?);";
        try(Connection connection= connectionDB.getConnection()){
            try(PreparedStatement pst= connection.prepareStatement(query)) {
                for(ItemFactura item: items){
                    pst.setInt(1,item.getCantidad());
                    pst.setInt(2,item.getProducto().getId());
                    pst.setInt(3, facturaId);
                    int affectedRows = pst.executeUpdate();
                    if (affectedRows == 0) {
                        throw new SQLException("Creating Factura Fails, no rows affected.");
                    }
                }
            }
        }

        catch (SQLException |  ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
    public boolean deleteItemFactura(int id) {
        ConnectionDB connectionDB = ConnectionSingleton.CONNECTION_DB.getConnectionDB();
        String query = "DELETE FROM Item_Factura WHERE factura_id=?";
        try (Connection connection = connectionDB.getConnection()) {
            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setInt(1, id);
                int affectedRows = pst.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Deleting Factura Fails, no rows affected.");
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
