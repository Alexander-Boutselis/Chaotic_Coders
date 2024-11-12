package com.hotelapplication.backend;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

public class DatabaseConnector {
    private static final String JDBC_URL = "jdbc:h2:~/HotelApplicationDatabase;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1";
    private static final String JDBC_USER = "dbUser";
    private static final String JDBC_PASSWORD = "";
    private static Jdbi databaseConnector;
    private static Handle handle;

    //Initialize the JDBI instance and connect to database
    public static void connect() {
        try {
            databaseConnector = Jdbi.create(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            handle = databaseConnector.open();
            System.out.println("Connection to H2 database successful!");
        } catch (Exception e) {
            System.err.println("Connection failed: " + e.getMessage());
            createDatabase();  // Create the database if the connection fails
        }
    }

    //Create a new H2 database if it does not exist
    public static void createDatabase() {
        try {
            databaseConnector = Jdbi.create(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            handle = databaseConnector.open();
            System.out.println("New H2 database 'HotelApplicationDatabase' created successfully!");
            createTables();
        } catch (Exception e) {
            System.err.println("Database creation failed: " + e.getMessage());
        }
    }

    //Create tables in the new database
    public static void createTables() {
        try {
            String createHotelsTable = "CREATE TABLE IF NOT EXISTS Hotels ("
                                     + "hotel_id INT PRIMARY KEY AUTO_INCREMENT, "
                                     + "name VARCHAR(255), "
                                     + "address VARCHAR(255))";
            handle.execute(createHotelsTable);
            System.out.println("Hotels table created successfully (if it did not exist).");
        } catch (Exception e) {
            System.err.println("Table creation failed: " + e.getMessage());
        }
    }

    //Disconnect from database
    public static void disconnect() {
        if (handle != null) {
            handle.close();
            System.out.println("Disconnected from database.");
        }
    }

    //Remove or delete a table if it exists
    public static void dropTable(String tableName) {
        try {
            connect();
            String dropTableSQL = "DROP TABLE IF EXISTS " + tableName;
            handle.execute(dropTableSQL);
            System.out.println("Table '" + tableName + "' dropped successfully (if it existed).");
        } catch (Exception e) {
            System.err.println("Failed to drop table '" + tableName + "': " + e.getMessage());
        } finally {
            disconnect();
        }
    }
}
