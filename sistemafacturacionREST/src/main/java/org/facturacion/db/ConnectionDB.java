package org.facturacion.db;

import org.sqlite.SQLiteConfig;

import java.sql.*;

class ConnectionDB {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        SQLiteConfig config = new SQLiteConfig(); //I add this configuration
        return DriverManager.getConnection("jdbc:sqlite:D:/courses/java8/javaS8_repo/JavaS8-Practice/sistemafacturacionREST/src/main/java/org/facturacion/db/facturacion.db");
    }
}
