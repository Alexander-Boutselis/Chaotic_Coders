package com.hotelapplication.backend;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnecter {
    // JDBC URL, username, and password of your AWS RDS MySQL database
    private static final String DB_URL = "hotelmanagerapplication.cj6cayiuukax.us-east-2.rds.amazonaws.com";
    private static final String USER = "admin";
    private static final String PASSWORD = "HotelPassword";

    // Database connection instance
    private static Connection connection;

    // Method to connect to the AWS RDS database
   public static void connect() {
    try {
        // Load the MySQL JDBC driver (optional for some versions)
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish the connection
        connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        System.out.println("Connected to AWS RDS successfully!");
    } catch (ClassNotFoundException e) {
        System.err.println("MySQL JDBC Driver not found.");
        e.printStackTrace();
    } catch (SQLException e) {
        System.err.println("Failed to connect to AWS RDS.");
        e.printStackTrace();
    }
}


    // Method to disconnect from the AWS RDS database
    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Disconnected from AWS RDS successfully!");
            } catch (SQLException e) {
                System.err.println("Failed to disconnect from AWS RDS.");
                e.printStackTrace();
            }
        }
    }

    // Get the connection instance for other classes to use
    public static Connection getConnection() {
        return connection;
    }
}
