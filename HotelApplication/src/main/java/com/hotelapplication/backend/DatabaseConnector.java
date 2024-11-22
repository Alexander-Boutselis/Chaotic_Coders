package com.hotelapplication.backend;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import java.util.List;
import java.sql.Date;
import java.math.BigDecimal;
import java.util.Calendar;
import java.time.LocalDate;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;



public class DatabaseConnector {
    private static final String JDBC_URL = "jdbc:h2:~/HotelApplicationDatabase;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1";
    private static final String JDBC_USER = "dbUser";
    private static final String JDBC_PASSWORD = "";
    private static Jdbi databaseConnector;
    private static Handle handle;
    

        
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
            }

        } catch (Exception e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }

    /********************************    
     *          Disconnect          *
     ********************************/
    public static void disconnect() {
        //Clear Tables --- Temporary
        /*
        emptyTable("Rooms");
        emptyTable("Hotels");
        emptyTable("Users");
        emptyTable("Reservations");
        */

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
     *                Initialize Application Method                *
     ****************************************************************/
    public static void initializeApplication() {
        try{
            connect();



            boolean hasData = handle.createQuery("SELECT COUNT(*) AS total FROM Hotels")
                    .mapTo(Integer.class)
                    .one() > 0;

            if (!hasData) {
                System.out.println("Database is empty. Initializing default data...");
            } else {
                System.out.println("Database has existing data.");
                //Call database connector translate database
                translateFromDatabase();
            }           
        }catch(Exception e){
            System.err.println("Failed to Initialize Applicaiton: " + e.getMessage());
            e.printStackTrace(); //Print the stack trace for better debugging
        }
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
            createRoomsTable.append("room_description VARCHAR(255)"); // Added room_description column
            createRoomsTable.append(")");
            getHandle().execute(createRoomsTable.toString());
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
            getHandle().execute(createUsersTable.toString());
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
    // Add Room to database
    public static void addRoom(Room room) {
        try {
            // Insert the room into the database
            String insertSQL = "INSERT INTO Rooms (room_id, hotel_id, room_number, bed_type, num_of_beds, price_per_night, room_description) VALUES (:room_id, :hotel_id, :room_number, :bed_type, :num_of_beds, :price_per_night, :room_description)";
            
            // Extract hotel ID from the first 2 digits of roomID
            int hotelID = RoomManager.getRoomID(room) / 1000;
            getHandle().createUpdate(insertSQL)
                    .bind("room_id", RoomManager.getRoomID(room))
                    .bind("hotel_id", hotelID)
                    .bind("room_number", RoomManager.getRoomNumber(room))
                    .bind("bed_type", RoomManager.getBedType(room))
                    .bind("num_of_beds", RoomManager.getNumberOfBeds(room))
                    .bind("price_per_night", RoomManager.getPricePerNight(room))
                    .bind("room_description", RoomManager.getRoomDescription(room)) // Binding room_description
                    .execute();
            System.out.println("New room added successfully");
        } catch (Exception e) {
            System.err.println("Failed to add room: " + e.getMessage());
        }
    }

    /********************************
     *           Add Account        *
     ********************************/
    //Add user to the database
    public static void addAccount(User user) {
        try {
            String insertSQL = "INSERT INTO Users (first_name, last_name, username, password, birthday, employee_num, start_date) VALUES (:first_name, :last_name, :username, :password, :birthday, :employee_num, :start_date)";
            
            int userID = handle.createUpdate(insertSQL)
                    .bind("first_name", AccountManager.getFirstName(user))
                    .bind("last_name", AccountManager.getLastName(user))
                    .bind("username", AccountManager.getUsername(user))
                    .bind("password", AccountManager.getPassword(user))
                    .bind("birthday", new Date(AccountManager.getBirthday(user).getTimeInMillis()))
                    .bind("employee_num", (user instanceof Manager) ? AccountManager.getEmployeeNumber((Manager) user) : null)
                    .bind("start_date", (user instanceof Manager) ? new Date(AccountManager.getEmployeeStartDate((Manager) user).getTimeInMillis()) : null)
                    .executeAndReturnGeneratedKeys("user_id").mapTo(int.class).one();
            AccountManager.setUserID(user, userID);
            System.out.println("User added successfully to the database with ID: " + userID);
        } catch (Exception e) {
            System.err.println("Failed to add user to the database: " + e.getMessage());
        }
    }

    /********************************
     *        Add Reservation       *
     ********************************/
    // Add reservation to the database
    public static void addReservation(Reservation reservation) {
        try {
            AccountManager.printAccountInfo(ReservationManager.getAssignedUser(reservation));
            String insertSQL = "INSERT INTO Reservations (user_id, room_id, hotel_id, check_in_date, check_out_date, total_cost) VALUES (:user_id, :room_id, :hotel_id, :check_in_date, :check_out_date, :total_cost)";
            getHandle().createUpdate(insertSQL)
                    .bind("user_id", AccountManager.getUserID(ReservationManager.getAssignedUser(reservation)))
                    .bind("room_id", RoomManager.getRoomID(ReservationManager.getRoom(reservation)))
                    .bind("hotel_id", HotelManager.getHotelID(ReservationManager.getHotel()))
                    .bind("check_in_date", Date.valueOf(ReservationManager.getStartDate(reservation)))
                    .bind("check_out_date", Date.valueOf(ReservationManager.getEndDate(reservation)))
                    .bind("total_cost", ReservationManager.getTotalPrice(reservation))
                    .execute();
            System.out.println("Reservation added successfully to the database.");
        } catch (Exception e) {
            System.err.println("Failed to add reservation to the database: " + e.getMessage());
        }
    }

   /****************************************************************
     *                    Update Object in Database                *
     ****************************************************************/
    /********************************
     *         Update Hotel         *
     ********************************/
    public static boolean updateHotelInDatabase(Hotel hotel) {
        try {
            Handle handle = getHandle();
            String updateSQL = "UPDATE Hotels SET name = :name, address = :address WHERE hotel_id = :hotel_id";
            handle.createUpdate(updateSQL)
                    .bind("hotel_id", HotelManager.getHotelID(hotel))
                    .bind("name", HotelManager.getHotelName(hotel))
                    .bind("address", HotelManager.getHotelAddress(hotel))
                    .execute();
            System.out.println("Hotel with ID " + HotelManager.getHotelID(hotel) + " updated successfully.");
            return true;
        } catch (Exception e) {
            System.err.println("Failed to update hotel in database: " + e.getMessage());
            return false;
        }
    }

    /********************************
     *         Update Room          *
     ********************************/
    public static boolean updateRoomInDatabase(Room room) {
        try {
            Handle handle = getHandle();
            String updateSQL = "UPDATE Rooms SET hotel_id = :hotel_id, room_number = :room_number, bed_type = :bed_type, num_of_beds = :num_of_beds, price_per_night = :price_per_night, room_description = :room_description WHERE room_id = :room_id";
            int hotelId = RoomManager.getRoomID(room) / 1000; // Calculate hotel_id from room_id
            handle.createUpdate(updateSQL)
                    .bind("room_id", RoomManager.getRoomID(room))
                    .bind("hotel_id", RoomManager.getRoomID(room) / 1000)
                    .bind("room_number", RoomManager.getRoomNumber(room))
                    .bind("bed_type", RoomManager.getBedType(room))
                    .bind("num_of_beds", RoomManager.getNumberOfBeds(room))
                    .bind("price_per_night", RoomManager.getPricePerNight(room))
                    .bind("room_description", RoomManager.getRoomDescription(room))
                    .execute();
            System.out.println("Room with ID " + RoomManager.getRoomID(room) + " updated successfully.");
            return true;
        } catch (Exception e) {
            System.err.println("Failed to update room in database: " + e.getMessage());
            return false;
        }
    }

    /********************************
     *         Update User          *
     ********************************/
    public static boolean updateUserInDatabase(User user) {
        try {
            Handle handle = getHandle();
            String updateSQL = "UPDATE Users SET first_name = :first_name, last_name = :last_name, username = :username, password = :password, birthday = :birthday, start_date = :start_date, employee_num = :employee_num WHERE user_id = :user_id";
            handle.createUpdate(updateSQL)
                    .bind("user_id", AccountManager.getUserID(user))
                    .bind("first_name", AccountManager.getFirstName(user))
                    .bind("last_name", AccountManager.getLastName(user))
                    .bind("username", AccountManager.getUsername(user))
                    .bind("password", AccountManager.getPassword(user))
                    .bind("birthday", AccountManager.getBirthday(user))
                    .bind("start_date", user instanceof Manager ? AccountManager.getEmployeeStartDate((Manager) user) : null)
                    .bind("employee_num", user instanceof Manager ? AccountManager.getEmployeeNumber((Manager) user) : null)
                    .execute();
            System.out.println("User with ID " + AccountManager.getUserID(user) + " updated successfully.");
            return true;
        } catch (Exception e) {
            System.err.println("Failed to update user in database: " + e.getMessage());
            return false;
        }
    }

    /********************************
     *     Update Reservation       *
     ********************************/
    public static boolean updateReservationInDatabase(Reservation reservation) {
        try {
            Handle handle = getHandle();
            String updateSQL = "UPDATE Reservations SET user_id = :user_id, room_id = :room_id, hotel_id = :hotel_id, check_in_date = :check_in_date, check_out_date = :check_out_date, total_cost = :total_cost WHERE reservation_id = :reservation_id";
            handle.createUpdate(updateSQL)
                    .bind("reservation_id", reservation.getReservationNumber())
                    .bind("user_id", ReservationManager.getAssignedUser(reservation).getUserID())
                    .bind("room_id", ReservationManager.getRoom(reservation).getRoomID())
                    .bind("hotel_id", ReservationManager.getHotelFromReservation(reservation).getHotelID())
                    .bind("check_in_date", ReservationManager.getStartDate(reservation))
                    .bind("check_out_date", ReservationManager.getEndDate(reservation))
                    .bind("total_cost", ReservationManager.getTotalPrice(reservation))
                    .execute();
            System.out.println("Reservation with ID " + reservation.getReservationNumber() + " updated successfully.");
            return true;
        } catch (Exception e) {
            System.err.println("Failed to update reservation in database: " + e.getMessage());
            return false;
        }
    }
    

    /****************************************************************
     *                            Query                             *
     ****************************************************************/
    /********************************
     *     Get Number of Items      *
     ********************************/
    public static int getNumberOfItemsInTable(String tableName) {
        String sqlQuery = "SELECT COUNT(*) FROM " + tableName;
        return handle.createQuery(sqlQuery)
                .mapTo(Integer.class)
                .one();
    }

    /********************************
     *      Get List of IDs         *
     ********************************/
    public static List<Integer> getAllIdsFromTable(String tableName, String idColumnName) {
        String sqlQuery = "SELECT " + idColumnName + " FROM " + tableName;
        return handle.createQuery(sqlQuery)
                .mapTo(Integer.class)
                .list();
    }

    /********************************
     *     Translate From Database  *
     ********************************/
    public static void translateFromDatabase() {
        //Translate Users
        if (getNumberOfItemsInTable("Users") > 0) {
            List<Integer> userIDs = getAllIdsFromTable("Users", "user_id");
            for (int userID : userIDs) {
                AccountManager.addAccount(translateUserFromDatabase(userID));
            }
        }

        //Translate Hotels
        if (getNumberOfItemsInTable("Hotels") > 0) {
            List<Integer> hotelIDs = getAllIdsFromTable("Hotels", "hotel_id");
            for (int hotelID : hotelIDs) {
                HotelManager.addHotel(translateHotelFromDatabase(hotelID));
            }
        }

        //Translate Rooms
        if (getNumberOfItemsInTable("Rooms") > 0) {
            List<Integer> roomIDs = getAllIdsFromTable("Rooms", "room_id");
            for (int roomID : roomIDs) {
                int hotelID = roomID / 1000;
                HotelManager.addRoomToHotel(HotelManager.getHotel(hotelID),translateRoomFromDatabase(roomID));
            }
        }

        //Translate Reservations
        if (getNumberOfItemsInTable("Reservations") > 0) {
            List<Integer> reservationIDs = getAllIdsFromTable("Reservations", "reservation_id");
            for (int reservationID : reservationIDs) {
                ReservationManager.createReservationGivenReservation(translateReservationFromDatabase(reservationID));
            }
        }
    }

    /********************************
     *    Translate Hotel Object    *
     ********************************/
    public static Hotel translateHotelFromDatabase(int hotelID) {
        String sqlQuery = "SELECT * FROM Hotels WHERE hotel_id = :hotelId";
        return handle.createQuery(sqlQuery)
                .bind("hotelId", hotelID)
                .map((resultSet, statementContext) -> new Hotel(
                        resultSet.getInt("hotel_id"),
                        resultSet.getString("name"),
                        resultSet.getString("address")
                )).one();
    }

    /********************************
     *     Translate Room Object    *
     ********************************/
    public static Room translateRoomFromDatabase(int roomId) {
        String sqlQuery = "SELECT * FROM Rooms WHERE room_id = :roomId";
        return getHandle().createQuery(sqlQuery)
                .bind("roomId", roomId)
                .map((resultSet, statementContext) -> new Room(
                        resultSet.getInt("room_id"),
                        resultSet.getInt("room_number"),
                        resultSet.getInt("num_of_beds"),
                        resultSet.getString("bed_type"),
                        resultSet.getBigDecimal("price_per_night").doubleValue(),
                        resultSet.getString("room_description") != null ? resultSet.getString("room_description") : ""
                ))
                .one();
    }

    /********************************
     *    Translate User Object     *
     ********************************/
    public static User translateUserFromDatabase(int userId) {
        String sqlQuery = "SELECT * FROM Users WHERE user_id = :userId";
        return getHandle().createQuery(sqlQuery)
                .bind("userId", userId)
                .map((resultSet, statementContext) -> {
                    if (resultSet.getObject("employee_num") != null) {
                        return new Manager(
                                resultSet.getInt("employee_num"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                Calendar.getInstance(),
                                resultSet.getString("username"),
                                resultSet.getString("password")
                        );
                    } else {
                        return new User(
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                Calendar.getInstance(), 
                                resultSet.getString("username"),
                                resultSet.getString("password")
                        );
                    }
                }).one();
    }

    /********************************
     * Translate Reservation Object *
     ********************************/
    public static Reservation translateReservationFromDatabase(int reservationId) {
        String sqlQuery = "SELECT reservation.*, user.first_name, user.last_name, user.username, user.password, user.employee_num, room.room_number, room.num_of_beds, room.bed_type, room.price_per_night, room.room_description " +
                          "FROM Reservations reservation " +
                          "JOIN Users user ON reservation.user_id = user.user_id " +
                          "JOIN Rooms room ON reservation.room_id = room.room_id " +
                          "WHERE reservation.reservation_id = :reservationId";
        return getHandle().createQuery(sqlQuery)
                .bind("reservationId", reservationId)
                .map((resultSet, statementContext) -> new Reservation(
                        translateUserFromDatabase(resultSet.getInt("user_id")),
                        resultSet.getInt("reservation_id"),
                        resultSet.getDouble("total_cost"),
                        new Room(
                                resultSet.getInt("room_id"),
                                resultSet.getInt("room_number"),
                                resultSet.getInt("num_of_beds"),
                                resultSet.getString("bed_type"),
                                resultSet.getBigDecimal("price_per_night").doubleValue(),
                                resultSet.getString("room_description") != null ? resultSet.getString("room_description") : ""
                        ),
                        resultSet.getDate("check_in_date").toLocalDate(),
                        resultSet.getDate("check_out_date").toLocalDate()
                )).one();
    }

    
    /****************************************************************
     *                            Delete                            *
     ****************************************************************/
    /********************************
     *       Delete Database File   *
     ********************************/
    public static void deleteDatabaseFile() {
        try {
            String databaseFilePath = System.getProperty("user.home") + "/HotelApplicationDatabase.mv.db";
            Path dbPath = Paths.get(databaseFilePath);
            Files.deleteIfExists(dbPath);
            System.out.println("Database file deleted successfully.");
        } catch (IOException e) {
            System.err.println("Failed to delete database file: " + e.getMessage());
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
            // Delete all rows from the specified table
            String deleteSQL = "DELETE FROM " + tableName;
            handle.execute(deleteSQL);

            // Reset auto-increment for Hotels, Users, Rooms, and Reservations table if necessary
            if ("Hotels".equalsIgnoreCase(tableName)) {
                handle.execute("ALTER TABLE Hotels ALTER COLUMN hotel_id RESTART WITH 1");
            } else if ("Users".equalsIgnoreCase(tableName)) {
                handle.execute("ALTER TABLE Users ALTER COLUMN user_id RESTART WITH 1");
            } else if ("Reservations".equalsIgnoreCase(tableName)) {
                handle.execute("ALTER TABLE Reservations ALTER COLUMN reservation_id RESTART WITH 1");
            }
            System.out.println("All items removed from table: " + tableName);
        } catch (Exception e) {
            System.err.println("Failed to empty table '" + tableName + "': " + e.getMessage());
        }
    }

    /********************************
     *       Empty Entire Database  *
     ********************************/
    public static void emptyDatabase() {
        try {
            // Disable foreign key checks
            handle.execute("SET REFERENTIAL_INTEGRITY FALSE");
            
            // Delete all rows from all tables
            handle.execute("DELETE FROM Reservations");
            handle.execute("DELETE FROM Rooms");
            handle.execute("DELETE FROM Users");
            handle.execute("DELETE FROM Hotels");

            // Reset auto-increment for all tables
            handle.execute("ALTER TABLE Hotels ALTER COLUMN hotel_id RESTART WITH 1");
            handle.execute("ALTER TABLE Users ALTER COLUMN user_id RESTART WITH 1");
            handle.execute("ALTER TABLE Rooms ALTER COLUMN room_id RESTART WITH 1");
            handle.execute("ALTER TABLE Reservations ALTER COLUMN reservation_id RESTART WITH 1");

            System.out.println("Database cleared successfully.");

        } catch (Exception e) {
            System.err.println("Failed to clear the database: " + e.getMessage());
        } finally {
            // Re-enable foreign key checks
            handle.execute("SET REFERENTIAL_INTEGRITY TRUE");
        }
    }
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


    /********************************
     *         Print Rooms Table    *
     ********************************/
    //Print the Rooms table in the terminal
    public static void printRoomsTable() {
        try {
            StringBuilder querySQL = new StringBuilder();
            querySQL.append("SELECT room_id, hotel_id, room_number, bed_type, num_of_beds, price_per_night, room_description FROM Rooms");
            Query query = getHandle().createQuery(querySQL.toString());
            System.out.println("\n------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-10s %-10s %-15s %-15s %-15s %-15s %-15s", "Room ID", "Hotel ID", "Room Number", "Bed Type", "Num of Beds", "Price per Night", "Room Description"));
            System.out.println("------------------------------------------------------------------------------------------------------");
            
            query.map((rs, ctx) -> {
                System.out.println(String.format("%-10d %-10d %-15d %-15s %-15d %-15.2f %-15s",
                        rs.getInt("room_id"),
                        rs.getInt("hotel_id"),
                        rs.getInt("room_number"),
                        rs.getString("bed_type"),
                        rs.getInt("num_of_beds"),
                        rs.getBigDecimal("price_per_night"),
                        rs.getString("room_description") != null ? rs.getString("room_description") : ""));
                return null;
            }).list();
            
            System.out.println("------------------------------------------------------------------------------------------------------\n");
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


    //Print the Reservations table in the terminal
    public static void printReservationsTable() {
        try {
            StringBuilder querySQL = new StringBuilder();
            querySQL.append("SELECT * FROM Reservations");
            Query query = handle.createQuery(querySQL.toString());

            //Print the table header
            System.out.println("\n-----------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-15s %-10s %-10s %-10s %-15s %-15s %-15s", "Reservation ID", "User ID", "Room ID", "Hotel ID", "Check-In Date", "Check-Out Date", "Total Cost"));
            System.out.println("-----------------------------------------------------------------------------------------------------");

            // Print each row in the Reservations table
            query.map((rs, ctx) -> {
                String reservationRow = String.format("%-15d %-10d %-10d %-10d %-15s %-15s %-15.2f",
                        rs.getInt("reservation_id"),
                        rs.getInt("user_id"),
                        rs.getInt("room_id"),
                        rs.getInt("hotel_id"),
                        rs.getDate("check_in_date"),
                        rs.getDate("check_out_date"),
                        rs.getBigDecimal("total_cost"));
                System.out.println(reservationRow);
                return null;
            }).list();

            System.out.println("-----------------------------------------------------------------------------------------------------\n");
        } catch (Exception e) {
            System.err.println("Failed to print Reservations table: " + e.getMessage());
        }
    }
    /****************************************************************
     *                             End                              *
     ****************************************************************/
}//End of DatabaseConnector Class
