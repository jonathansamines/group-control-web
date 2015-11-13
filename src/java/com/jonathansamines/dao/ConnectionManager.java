package com.jonathansamines.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionManager, handles every connection to MYSQL server
 to make it easier to handling connection resources.
 * @author Jonathan Samines
 */
public class ConnectionManager {
    
    private static final String connectionUri = "jdbc:mysql://localhost:3306";
    private static final String connectionClassName = "com.mysql.jdbc.Driver";
    private static final String databaseName = "catalog-management";
    private static final String username = "root";
    private static final String password = "r00t";
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(connectionClassName);

            return DriverManager.getConnection(connectionUri + "/" + databaseName, username, password);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        return null;
    }
}
