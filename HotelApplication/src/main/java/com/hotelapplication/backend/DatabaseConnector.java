package com.hotelapplication.backend;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import java.util.List;
import java.sql.Date;



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
     *           Connect            *
     ********************************/
    public static void connect() {
        try {
            //Create/Connect to Database
            databaseConnector = Jdbi.create(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            //Verify Database exists
            handle = databaseConnector.open();
            System.out.println("Connection to H2 database successful!");

            //Check if Database is missing any of the 4 tables
            if(handle.createQuery("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME IN ('Hotels', 'Rooms', 'Users', 'Reservations')").mapTo(Integer.class).one() != 4){
                createTables();
            }else{
                System.out.println("Oops shouldnt be here");
            }
        } catch (Exception e) {
            System.err.println("Connection failed: " + e.getMessage());
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

    /********************************
     *         Get Handle           *
     ********************************/
    public static Handle getHandle() {
        if (handle == null){
            connect();
        }
        return handle;
    }


/****************************************************************
 *                            Tables                            *
 ****************************************************************/
    public static void createTables() {
        try {
            createHotelsTable();
            createUsersTable();
            createReservationsTable();
            createRoomsTable();

            // After creating all tables, add the foreign key constraints
            addForeignKeys();

            // Check that all tables were created successfully
            if (handle.createQuery("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME IN ('Hotels', 'Rooms', 'Users', 'Reservations')").mapTo(Integer.class).one() == 4) {
                System.out.println("All tables created successfully!");
            }
        } catch (Exception e) {
            System.out.println("Error: Tables NOT created! " + e.getMessage());
        }
    }

    /********************************
     *          Hotel Table         *
     ********************************/
    public static void createHotelsTable() {
        try {
            StringBuilder createHotelsTable = new StringBuilder();
            createHotelsTable.append("CREATE TABLE IF NOT EXISTS Hotels (");
            createHotelsTable.append("hotel_id INT PRIMARY KEY AUTO_INCREMENT, ");
            createHotelsTable.append("name VARCHAR(255) NOT NULL, ");
            createHotelsTable.append("address VARCHAR(255) NOT NULL)");
            handle.execute(createHotelsTable.toString());
            System.out.println("Hotels table created/found.");
        } catch (Exception e) {
            System.err.println("Hotels table creation failed: " + e.getMessage());
        }
    }

    /********************************
     *          Rooms Table         *
     ********************************/
    private static void createRoomsTable() {
        try {
            StringBuilder createRoomsTable = new StringBuilder();
            createRoomsTable.append("CREATE TABLE IF NOT EXISTS Rooms (");
            createRoomsTable.append("room_id INT PRIMARY KEY, ");
            createRoomsTable.append("hotel_id INT, ");
            createRoomsTable.append("room_number INT, ");
            createRoomsTable.append("bed_type VARCHAR(50), ");
            createRoomsTable.append("num_of_beds INT, ");
            createRoomsTable.append("price_per_night DECIMAL(10, 2), ");
            createRoomsTable.append("reservation_id INT)"); 
            handle.execute(createRoomsTable.toString());
            System.out.println("Rooms table created/found.");
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
            createUsersTable.append("first_name VARCHAR(100), ");
            createUsersTable.append("last_name VARCHAR(100), ");
            createUsersTable.append("username VARCHAR(100) UNIQUE NOT NULL, ");
            createUsersTable.append("password VARCHAR(255) NOT NULL, ");
            createUsersTable.append("birthday DATE, ");
            createUsersTable.append("start_date DATE, ");
            createUsersTable.append("employee_num INT)");
            handle.execute(createUsersTable.toString());
            System.out.println("Users table created/found.");
        } catch (Exception e) {
            System.err.println("Users table creation failed: " + e.getMessage());
        }
    }

    /********************************
     *      Reservations Table      *
     ********************************/
    private static void createReservationsTable() {
        try {
            StringBuilder createReservationsTable = new StringBuilder();
            createReservationsTable.append("CREATE TABLE IF NOT EXISTS Reservations (");
            createReservationsTable.append("reservation_id INT PRIMARY KEY AUTO_INCREMENT, ");
            createReservationsTable.append("user_id INT, ");
            createReservationsTable.append("room_id INT, ");
            createReservationsTable.append("hotel_id INT, ");
            createReservationsTable.append("check_in_date DATE, ");
            createReservationsTable.append("check_out_date DATE, ");
            createReservationsTable.append("total_cost DECIMAL(10, 2))");
            handle.execute(createReservationsTable.toString());
            System.out.println("Reservations table created/found.");
        } catch (Exception e) {
            System.err.println("Reservations table creation failed: " + e.getMessage());
        }
    }

    /********************************
     *     Add Foreign Keys Later   *
     ********************************/
    private static void addForeignKeys() {
        try {
            // Add foreign key to Rooms table for hotel_id
            StringBuilder addFKToRoomsHotel = new StringBuilder();
            addFKToRoomsHotel.append("ALTER TABLE Rooms ");
            addFKToRoomsHotel.append("ADD FOREIGN KEY (hotel_id) REFERENCES Hotels(hotel_id)");
            handle.execute(addFKToRoomsHotel.toString());
            System.out.println("Foreign key added to Rooms for hotel_id.");

            // Add foreign key to Rooms table for reservation_id
            StringBuilder addFKToRoomsReservation = new StringBuilder();
            addFKToRoomsReservation.append("ALTER TABLE Rooms ");
            addFKToRoomsReservation.append("ADD FOREIGN KEY (reservation_id) REFERENCES Reservations(reservation_id)");
            handle.execute(addFKToRoomsReservation.toString());
            System.out.println("Foreign key added to Rooms for reservation_id.");

            // Add foreign key to Reservations table for user_id
            StringBuilder addFKToReservationsUser = new StringBuilder();
            addFKToReservationsUser.append("ALTER TABLE Reservations ");
            addFKToReservationsUser.append("ADD FOREIGN KEY (user_id) REFERENCES Users(user_id)");
            handle.execute(addFKToReservationsUser.toString());
            System.out.println("Foreign key added to Reservations for user_id.");

            // Add foreign key to Reservations table for room_id
            StringBuilder addFKToReservationsRoom = new StringBuilder();
            addFKToReservationsRoom.append("ALTER TABLE Reservations ");
            addFKToReservationsRoom.append("ADD FOREIGN KEY (room_id) REFERENCES Rooms(room_id)");
            handle.execute(addFKToReservationsRoom.toString());
            System.out.println("Foreign key added to Reservations for room_id.");

            // Add foreign key to Reservations table for hotel_id
            StringBuilder addFKToReservationsHotel = new StringBuilder();
            addFKToReservationsHotel.append("ALTER TABLE Reservations ");
            addFKToReservationsHotel.append("ADD FOREIGN KEY (hotel_id) REFERENCES Hotels(hotel_id)");
            handle.execute(addFKToReservationsHotel.toString());
            System.out.println("Foreign key added to Reservations for hotel_id.");
            
        } catch (Exception e) {
            System.err.println("Failed to add foreign keys: " + e.getMessage());
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

    /********************************
     *       Empty Table Method     *
     ********************************/
    public static void emptyTable(String tableName) {
        try {
            if ("Hotels".equalsIgnoreCase(tableName)) {
                // Delete all rows from the Rooms table associated with this hotel
                String deleteRoomsSQL = "DELETE FROM Rooms WHERE hotel_id IN (SELECT hotel_id FROM Hotels)";
                handle.execute(deleteRoomsSQL);
                System.out.println("All rooms removed for the specified hotel.");
            }
            // Delete all rows from the specified table
            String deleteSQL = "DELETE FROM " + tableName;
            handle.execute(deleteSQL);

            // Reset auto-increment for Hotels and Users table if necessary
            if ("Hotels".equalsIgnoreCase(tableName)) {
                handle.execute("ALTER TABLE Hotels ALTER COLUMN hotel_id RESTART WITH 1");
            } else if ("Users".equalsIgnoreCase(tableName)) {
                handle.execute("ALTER TABLE Users ALTER COLUMN user_id RESTART WITH 1");
            }
            System.out.println("All items removed from table: " + tableName);
        } catch (Exception e) {
            System.err.println("Failed to empty table '" + tableName + "': " + e.getMessage());
        }
    }




     /****************************************************************
     *                             Add                              *
     ****************************************************************/
    /********************************
     *           Add Hotel          *
     ********************************/
    public static void addHotel(Hotel hotel) {
        try {
            //Insert hotel without specifying hotel_id as it is auto-incremented
            String insertSQL = "INSERT INTO Hotels (name, address) VALUES (:name, :address)";


            
            int hotelID = handle.createUpdate(insertSQL)
                    .bind("name", HotelManager.getHotelName(hotel))
                    .bind("address", HotelManager.getHotelAddress(hotel))
                    .executeAndReturnGeneratedKeys("hotel_id")
                    .mapTo(int.class)
                    .one();
            
            //Set the hotelID to the hotel object
            HotelManager.setHotelID(hotel, hotelID);

            //Log success
            System.out.println("New hotel added successfully with Hotel ID: " + hotelID + "\n");
        } catch (Exception e) {
            System.err.println("Failed to add hotel: " + e.getMessage());
        }
    }

    /********************************
     *           Add Room           *
     ********************************/
    //Add Room to databas
    public static void addRoom(Room room) {
        try {

            //Insert the room into the database
            String insertSQL = "INSERT INTO Rooms (room_id, hotel_id, room_number, bed_type, num_of_beds, price_per_night) VALUES (:room_id, :hotel_id, :room_number, :bed_type, :num_of_beds, :price_per_night)";
            
            //Extract hotel ID from the first 2 digits of roomID
            int hotelID = RoomManager.getRoomID(room) / 1000; 
            handle.createUpdate(insertSQL)
                    .bind("room_id", RoomManager.getRoomID(room))
                    .bind("hotel_id", hotelID)
                    .bind("room_number", RoomManager.getRoomNumber(room))
                    .bind("bed_type", RoomManager.getBedType(room))
                    .bind("num_of_beds", RoomManager.getNumberOfBeds(room))
                    .bind("price_per_night", RoomManager.getPricePerNight(room))
                    .execute();
            System.out.println("New room added successfully");
        } catch (Exception e) {
            System.err.println("Failed to add room: " + e.getMessage());
        }
    }

    /********************************
     *           Add Account          *
     ********************************/
    //Add user to the database
    public static void addAccount(User user) {
        try {
            String insertSQL = "INSERT INTO Users (first_name, last_name, username, password, birthday, employee_num, start_date) VALUES (:first_name, :last_name, :username, :password, :birthday, :employee_num, :start_date)";
            handle.createUpdate(insertSQL)
                    .bind("first_name", AccountManager.getFirstName(user))
                    .bind("last_name", AccountManager.getLastName(user))
                    .bind("username", AccountManager.getUsername(user))
                    .bind("password", AccountManager.getPassword(user))
                    .bind("birthday", new Date(AccountManager.getBirthday(user).getTimeInMillis()))
                    .bind("employee_num", (user instanceof Manager) ? AccountManager.getEmployeeNumber((Manager) user) : null)
                    .bind("start_date", (user instanceof Manager) ? new Date(AccountManager.getEmployeeStartDate((Manager) user).getTimeInMillis()) : null)
                    .execute();
            System.out.println("User added successfully to the database.");
        } catch (Exception e) {
            System.err.println("Failed to add user to the database: " + e.getMessage());
        }
    }
    /****************************************************************
     *                            Update                            *
     ****************************************************************/
    
    /****************************************************************
     *                            Delete                            *
     ****************************************************************/
    /********************************
     *         Remove Hotel         *
     ********************************/
    public static void removeHotel(Hotel hotel) {
            int hotelID = HotelManager.getHotelID(hotel);
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
    //Print the Hotels table in the terminal
    public static void printHotelsTable() {
        try {
            StringBuilder querySQL = new StringBuilder();
            querySQL.append("SELECT * FROM Hotels");
            Query query = handle.createQuery(querySQL.toString());

            // Print the table header
            System.out.println("\n-------------------------------------------------------------");
            System.out.println(String.format("%-10s %-20s %-30s", "Hotel ID", "Name", "Address"));
            System.out.println("-------------------------------------------------------------");

            // Print each row in the Hotels table
            query.map((rs, ctx) -> {
                String hotelRow = String.format("%-10d %-20s %-30s", 
                    rs.getInt("hotel_id"), 
                    rs.getString("name"), 
                    rs.getString("address"));
                System.out.println(hotelRow);
                return null;
            }).list();

            System.out.println("-------------------------------------------------------------\n");
        } catch (Exception e) {
            System.err.println("Failed to print Hotels table: " + e.getMessage());
        }
    }


    //Print the Rooms table in the terminal
    public static void printRoomsTable() {
        try {
            StringBuilder querySQL = new StringBuilder();
            querySQL.append("SELECT * FROM Rooms");
            Query query = handle.createQuery(querySQL.toString());
            System.out.println("\n---------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-10s %-10s %-15s %-15s %-15s %-15s %-15s", "Room ID", "Hotel ID", "Room Number", "Bed Type", "Num of Beds", "Price per Night", "Reservation ID"));
            System.out.println("---------------------------------------------------------------------------------------------");
            
            query.map((rs, ctx) -> {
                System.out.println(String.format("%-10d %-10d %-15d %-15s %-15d %-15.2f %-15d",
                        rs.getInt("room_id"),
                        rs.getInt("hotel_id"),
                        rs.getInt("room_number"),
                        rs.getString("bed_type"),
                        rs.getInt("num_of_beds"),
                        rs.getBigDecimal("price_per_night"),
                        rs.getInt("reservation_id")));
                return null;
            }).list();
            
            System.out.println("---------------------------------------------------------------------------------------------\n");
        } catch (Exception e) {
            System.err.println("Failed to print Rooms table: " + e.getMessage());
        }
    }

    //Print the Users table in the terminal
    public static void printUsersTable() {
        try {
            StringBuilder querySQL = new StringBuilder();
            querySQL.append("SELECT * FROM Users");
            Query query = handle.createQuery(querySQL.toString());

            // Print the table header
            System.out.println("\n-------------------------------------------------------------");
            System.out.println(String.format("%-10s %-15s %-15s %-15s %-20s", "User ID", "First Name", "Last Name", "Username", "Birthday"));
            System.out.println("-------------------------------------------------------------");

            //Print each row in the Users table
            query.map((rs, ctx) -> {
                String userRow = String.format("%-10d %-15s %-15s %-15s %-20s",
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("username"),
                        rs.getDate("birthday"));
                System.out.println(userRow);
                return null;
            }).list();

            System.out.println("-------------------------------------------------------------\n");
        } catch (Exception e) {
            System.err.println("Failed to print Users table: " + e.getMessage());
        }
    }

    /****************************************************************
     *                             End                              *
     ****************************************************************/
}//End of DatabaseConnector Class
