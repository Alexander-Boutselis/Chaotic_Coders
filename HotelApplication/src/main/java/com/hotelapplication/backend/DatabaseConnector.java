package com.hotelapplication.backend;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;

public class DatabaseConnector {
    private static final String JDBC_URL = "jdbc:h2:~/HotelApplicationDatabase;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1";
    private static final String JDBC_USER = "dbUser";
    private static final String JDBC_PASSWORD = "";
    private static Jdbi databaseConnector;
    private static Handle handle;

    /****************************************************************
     *                          Connections                         *
     ****************************************************************/
    /********************************
    *         Create Database       *
    ********************************/
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

    /********************************
     *           Connect            *
     ********************************/
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

    /********************************    
     *          Disconnect          *
     ********************************/
    public static void disconnect() {
        if (handle != null) {
            handle.close();
            System.out.println("Disconnected from database.");
        }
    }


    /****************************************************************
     *                            Tables                            *
     ****************************************************************/
    public static void createTables(){
        createHotelTable();
        createRoomsTable();
    }

    /********************************
     *          Hotel Table         *
     ********************************/
    public static void createHotelTable() {
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

    /********************************
     *          Rooms Table         *
     ********************************/
    private static void createRoomsTable() {
        try {
            String createRoomsTable = "CREATE TABLE IF NOT EXISTS Rooms ("
                                    + "room_id INT PRIMARY KEY AUTO_INCREMENT, "
                                    + "hotel_id INT, "
                                    + "room_number INT, "
                                    + "bed_type VARCHAR(255), "
                                    + "num_of_beds INT, "
                                    + "price_per_night DECIMAL(10, 2), "
                                    + "reservation_id INT, "
                                    + "FOREIGN KEY (hotel_id) REFERENCES Hotels(hotel_id), "
                                    + "FOREIGN KEY (reservation_id) REFERENCES Reservations(reservation_id))";
            handle.execute(createRoomsTable);
            System.out.println("Rooms table created successfully (if it did not exist).");
        } catch (Exception e) {
            System.err.println("Rooms table creation failed: " + e.getMessage());
        }
    }

    /********************************
     *           Drop Table         *
     ********************************/
    public static void dropTable(String tableName) {
        try {
            String dropTableSQL = "DROP TABLE IF EXISTS " + tableName;
            handle.execute(dropTableSQL);
            System.out.println("Table '" + tableName + "' dropped successfully (if it existed).");
        } catch (Exception e) {
            System.err.println("Failed to drop table '" + tableName + "': " + e.getMessage());
        }
    }

    /****************************************************************
     *                             Add                              *
     ****************************************************************/
    public static void addOrUpdateHotel(Hotel hotel) {
        try {
            // Check if the hotel already exists
            String selectSQL = "SELECT COUNT(*) FROM Hotels WHERE hotel_id = :hotel_id";
            int count = handle.createQuery(selectSQL)
                    .bind("hotel_id", hotel.getHotelID())
                    .mapTo(int.class)
                    .one();
            
            if (count > 0) {
                // Update existing hotel
                String updateSQL = "UPDATE Hotels SET name = :name, address = :address WHERE hotel_id = :hotel_id";
                handle.createUpdate(updateSQL)
                        .bind("hotel_id", hotel.getHotelID())
                        .bind("name", hotel.getHotelName())
                        .bind("address", hotel.getHotelAddress())
                        .execute();
                System.out.println("Hotel updated successfully.\n");
            } else {
                // Insert new hotel
                String insertSQL = "INSERT INTO Hotels (hotel_id, name, address) VALUES (:hotel_id, :name, :address)";
                handle.createUpdate(insertSQL)
                        .bind("hotel_id", hotel.getHotelID())
                        .bind("name", hotel.getHotelName())
                        .bind("address", hotel.getHotelAddress())
                        .execute();
                System.out.println("New hotel added successfully.\n");
            }
        } catch (Exception e) {
            System.err.println("Failed to add or update hotel: " + e.getMessage());
        }
    }

    /****************************************************************
     *                            Delete                            *
     ****************************************************************/
    /********************************
     *         Remove Hotel         *
     ********************************/
    public static void removeHotel(int hotelID) {
        try {
            String deleteSQL = "DELETE FROM Hotels WHERE hotel_id = :hotel_id";
            handle.createUpdate(deleteSQL)
                  .bind("hotel_id", hotelID)
                  .execute();
            System.out.println("Hotel with ID " + hotelID + " removed successfully.\n");
        } catch (Exception e) {
            System.err.println("Failed to remove hotel with ID " + hotelID + ": " + e.getMessage());
        }
    }

    /****************************************************************
     *                            Query                             *
     ****************************************************************/


    /****************************************************************
     *                            Print                             *
     ****************************************************************/
    //Pint the Hotels table in the terminal
    public static void printHotelsTable() {
        try {
            String querySQL = "SELECT * FROM Hotels";
            Query query = handle.createQuery(querySQL);
            System.out.println("\n---------------------------------------------------");
            System.out.println(String.format("%-10s %-20s %-30s", "Hotel ID", "Name", "Address"));
            System.out.println("---------------------------------------------------");
            query.map((rs, ctx) -> {
                int hotelId = rs.getInt("hotel_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                System.out.println(String.format("%-10d %-20s %-30s", hotelId, name, address));
                return null;
            }).list();
            System.out.println("---------------------------------------------------\n");
        } catch (Exception e) {
            System.err.println("Failed to print Hotels table: " + e.getMessage());
        }
    }


    /****************************************************************
     *                             End                              *
     ****************************************************************/
}//End of DatabaseConnector Class
