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

    //Create Database
    //Create Hotels Table
    //Create Rooms Table
    //Create Users Table
    //Create Reservaitons Table

    //Connect/Disconnect to/from Database

    //Method to Translate Hotel for Database
    //Method to Translate Room for Database
    //Method to Translate User for Database
    //Method to Translate Reservation for Database
    
    //Method to Translate Hotel to Object
    //Method to Translate Room to Object
    //Method to Translate User to Object
    //Method to Translate Reservation to Object






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
        createUsersTable();
    }

    /********************************
     *          Hotel Table         *
     ********************************/
    public static void createHotelTable() {
        try {
            StringBuilder createHotelsTable = new StringBuilder();
            createHotelsTable.append("CREATE TABLE IF NOT EXISTS Hotels (");
            createHotelsTable.append("hotel_id INT PRIMARY KEY AUTO_INCREMENT, ");
            createHotelsTable.append("name VARCHAR(255), ");
            createHotelsTable.append("address VARCHAR(255))");
            handle.execute(createHotelsTable.toString());
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
            StringBuilder createRoomsTable = new StringBuilder();
            createRoomsTable.append("CREATE TABLE IF NOT EXISTS Rooms (");
            createRoomsTable.append("room_id INT PRIMARY KEY AUTO_INCREMENT, ");
            createRoomsTable.append("hotel_id INT, ");
            createRoomsTable.append("room_number INT, ");
            createRoomsTable.append("bed_type VARCHAR(255), ");
            createRoomsTable.append("num_of_beds INT, ");
            createRoomsTable.append("price_per_night DECIMAL(10, 2), ");
            createRoomsTable.append("reservation_id INT, ");
            createRoomsTable.append("FOREIGN KEY (hotel_id) REFERENCES Hotels(hotel_id), ");
            createRoomsTable.append("FOREIGN KEY (reservation_id) REFERENCES Reservations(reservation_id))");
            handle.execute(createRoomsTable.toString());
            System.out.println("Rooms table created successfully (if it did not exist).");
        } catch (Exception e) {
            System.err.println("Rooms table creation failed: " + e.getMessage());
        }
    }

    /********************************
     *          Users Table         *
     ********************************/
    private static void createUsersTable() {
        try {
            StringBuilder createUsersTable = new StringBuilder();
            createUsersTable.append("CREATE TABLE IF NOT EXISTS Users (");
            createUsersTable.append("user_id INT PRIMARY KEY AUTO_INCREMENT, ");
            createUsersTable.append("username VARCHAR(255), ");
            createUsersTable.append("password VARCHAR(255), ");
            createUsersTable.append("is_active BOOLEAN)");
            handle.execute(createUsersTable.toString());
            System.out.println("Users table created successfully (if it did not exist).");
        } catch (Exception e) {
            System.err.println("Users table creation failed: " + e.getMessage());
        }
    }

    /********************************
     *           Drop Table         *
     ********************************/
    public static void dropTable(String tableName) {
        try {
            StringBuilder dropTableSQL = new StringBuilder();
            dropTableSQL.append("DROP TABLE IF EXISTS ").append(tableName);
            handle.execute(dropTableSQL.toString());
            System.out.println("Table '" + tableName + "' dropped successfully (if it existed).");
        } catch (Exception e) {
            System.err.println("Failed to drop table '" + tableName + "': " + e.getMessage());
        }
    }

    /****************************************************************
     *                             Add                              *
     ****************************************************************/
    public static void addHotel(Hotel hotel) {
        try {
            StringBuilder insertSQL = new StringBuilder();
            insertSQL.append("INSERT INTO Hotels (hotel_id, name, address) VALUES (:hotel_id, :name, :address)");
            handle.createUpdate(insertSQL.toString())
                    .bind("hotel_id", hotel.getHotelID())
                    .bind("name", hotel.getHotelName())
                    .bind("address", hotel.getHotelAddress())
                    .execute();
            System.out.println("New hotel added successfully.\n");
        } catch (Exception e) {
            System.err.println("Failed to add hotel: " + e.getMessage());
        }
    }

            /*
    public static void addRoom(Room room) {
        try {
            if (room.getHotelID() == null) {
                System.out.println("Invalid hotel ID provided. Assigning default hotel ID.");
                int defaultHotelID = 0; // Example: default hotel ID is set to 0 (must exist in the database)
                room.setHotelID(defaultHotelID);
            }

            StringBuilder selectHotelSQL = new StringBuilder();
            selectHotelSQL.append("SELECT COUNT(*) FROM Hotels WHERE hotel_id = :hotel_id");
            int hotelCount = handle.createQuery(selectHotelSQL.toString())
                    .bind("hotel_id", room.getHotelID())
                    .mapTo(int.class)
                    .one();

            if (hotelCount == 0) {
                System.err.println("Cannot add room: Hotel with ID " + room.getHotelID() + " does not exist.\n");
                return;
            }

            StringBuilder insertSQL = new StringBuilder();
            insertSQL.append("INSERT INTO Rooms (hotel_id, room_number, bed_type, num_of_beds, price_per_night, reservation_id) ");
            insertSQL.append("VALUES (:hotel_id, :room_number, :bed_type, :num_of_beds, :price_per_night, :reservation_id)");
            handle.createUpdate(insertSQL.toString())
                    .bind("hotel_id", room.getHotelID())
                    .bind("room_number", room.getRoomNumber())
                    .bind("bed_type", room.getBedType())
                    .bind("num_of_beds", room.getNumOfBeds())
                    .bind("price_per_night", room.getPricePerNight())
                    .bind("reservation_id", room.getReservationID())
                    .execute();
            System.out.println("New room added successfully.\n");
        } catch (Exception e) {
            System.err.println("Failed to add room: " + e.getMessage());
        }
    }

    public static void addUser(User user) {
        try {
            StringBuilder insertSQL = new StringBuilder();
            insertSQL.append("INSERT INTO Users (user_id, username, password, is_active) VALUES (:user_id, :username, :password, :is_active)");
            handle.createUpdate(insertSQL.toString())
                    .bind("user_id", user.getUserID())
                    .bind("username", user.getUsername())
                    .bind("password", user.getPassword())
                    .bind("is_active", user.isActive())
                    .execute();
            System.out.println("New user added successfully.\n");
        } catch (Exception e) {
            System.err.println("Failed to add user: " + e.getMessage());
        }
        

    }

    /****************************************************************
     *                            Update                            *
     ****************************************************************/
    public static void updateHotel(Hotel hotel) {
        try {
            StringBuilder updateSQL = new StringBuilder();
            updateSQL.append("UPDATE Hotels SET name = :name, address = :address WHERE hotel_id = :hotel_id");
            handle.createUpdate(updateSQL.toString())
                    .bind("hotel_id", hotel.getHotelID())
                    .bind("name", hotel.getHotelName())
                    .bind("address", hotel.getHotelAddress())
                    .execute();
            System.out.println("Hotel updated successfully.\n");
        } catch (Exception e) {
            System.err.println("Failed to update hotel: " + e.getMessage());
        }
    }
/*
    public static void updateRoom(Room room) {
        try {
            StringBuilder updateSQL = new StringBuilder();
            updateSQL.append("UPDATE Rooms SET hotel_id = :hotel_id, room_number = :room_number, bed_type = :bed_type, ");
            updateSQL.append("num_of_beds = :num_of_beds, price_per_night = :price_per_night, reservation_id = :reservation_id ");
            updateSQL.append("WHERE room_id = :room_id");
            handle.createUpdate(updateSQL.toString())
                    .bind("room_id", room.getRoomID())
                    .bind("hotel_id", room.getHotelID())
                    .bind("room_number", room.getRoomNumber())
                    .bind("bed_type", room.getBedType())
                    .bind("num_of_beds", room.getNumOfBeds())
                    .bind("price_per_night", room.getPricePerNight())
                    .bind("reservation_id", room.getReservationID())
                    .execute();
            System.out.println("Room updated successfully.\n");
        } catch (Exception e) {
            System.err.println("Failed to update room: " + e.getMessage());
        }
    }

    public static void updateUser(User user) {
        try {
            StringBuilder updateSQL = new StringBuilder();
            updateSQL.append("UPDATE Users SET username = :username, password = :password, is_active = :is_active WHERE user_id = :user_id");
            handle.createUpdate(updateSQL.toString())
                    .bind("user_id", user.getUserID())
                    .bind("username", user.getUsername())
                    .bind("password", user.getPassword())
                    .bind("is_active", user.isActive())
                    .execute();
            System.out.println("User updated successfully.\n");
        } catch (Exception e) {
            System.err.println("Failed to update user: " + e.getMessage());
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
            StringBuilder deleteSQL = new StringBuilder();
            deleteSQL.append("DELETE FROM Hotels WHERE hotel_id = :hotel_id");
            handle.createUpdate(deleteSQL.toString())
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
    // Print the Hotels table in the terminal
    public static void printHotelsTable() {
        try {
            StringBuilder querySQL = new StringBuilder();
            querySQL.append("SELECT * FROM Hotels");
            Query query = handle.createQuery(querySQL.toString());
            System.out.println("\n---------------------------------------------------");
            System.out.println(String.format("%-10s %-20s %-30s", "Hotel ID", "Name", "Address"));
            System.out.println("---------------------------------------------------");
            query.map((rs, ctx) -> {
                StringBuilder receipt = new StringBuilder();
                receipt.append("Hotel ID: " + rs.getInt("hotel_id")).append("\n");
                receipt.append("Name: " + rs.getString("name")).append("\n");
                receipt.append("Address: " + rs.getString("address")).append("\n");
                System.out.println(receipt.toString());
                return null;
            }).list();
            System.out.println("---------------------------------------------------\n");
        } catch (Exception e) {
            System.err.println("Failed to print Hotels table: " + e.getMessage());
        }
    }

    // Print the Rooms table in the terminal
    public static void printRoomsTable() {
        try {
            StringBuilder querySQL = new StringBuilder();
            querySQL.append("SELECT * FROM Rooms");
            Query query = handle.createQuery(querySQL.toString());
            System.out.println("\n---------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-10s %-10s %-15s %-15s %-15s %-15s %-15s", "Room ID", "Hotel ID", "Room Number", "Bed Type", "Num of Beds", "Price per Night", "Reservation ID"));
            System.out.println("---------------------------------------------------------------------------------------------");
            query.map((rs, ctx) -> {
                StringBuilder receipt = new StringBuilder();
                receipt.append("Room ID: " + rs.getInt("room_id")).append("\n");
                receipt.append("Hotel ID: " + rs.getInt("hotel_id")).append("\n");
                receipt.append("Room Number: " + rs.getInt("room_number")).append("\n");
                receipt.append("Bed Type: " + rs.getString("bed_type")).append("\n");
                receipt.append("Num of Beds: " + rs.getInt("num_of_beds")).append("\n");
                receipt.append("Price per Night: " + rs.getBigDecimal("price_per_night")).append("\n");
                receipt.append("Reservation ID: " + rs.getInt("reservation_id")).append("\n");
                System.out.println(receipt.toString());
                return null;
            }).list();
            System.out.println("---------------------------------------------------------------------------------------------\n");
        } catch (Exception e) {
            System.err.println("Failed to print Rooms table: " + e.getMessage());
        }
    }


    /****************************************************************
     *                             End                              *
     ****************************************************************/
}//End of DatabaseConnector Class
