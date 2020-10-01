package org.facturacion.db;

public enum ConnectionSingleton {
    CONNECTION_DB;
    private ConnectionDB connectionDB;
    ConnectionSingleton(){
        connectionDB = new ConnectionDB();

    }
    public ConnectionDB getConnectionDB() {
        return connectionDB;
    }
}
