package com.hotelapplication.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnecter {
    
    private static final String URL = "jdbc:mysql://localhost:3306/hotelApplicationDatabase";
    private static final String USER = "hotel_app";
    private static final String PASSWORD = "HotelPassword";
    
    private Connection connection;

    // Method to establish a connection
    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection to database successful!");
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }

    // Method to disconnect
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Disconnected from database.");
            } catch (SQLException e) {
                System.err.println("Failed to disconnect: " + e.getMessage());
            }
        }
    }

    // Method to execute a simple query (for example, SELECT)
    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Query execution failed: " + e.getMessage());
        }
        return resultSet;
    }

    // Method to execute an update (for example, INSERT, UPDATE, DELETE)
    public int executeUpdate(String update) {
        int rowsAffected = 0;
        try {
            Statement statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(update);
        } catch (SQLException e) {
            System.err.println("Update execution failed: " + e.getMessage());
        }
        return rowsAffected;
    }
}
