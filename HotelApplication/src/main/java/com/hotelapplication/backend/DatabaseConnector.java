//DatabaseConnector.java

package com.hotelapplication.backend;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.time.LocalDate;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.Random;

/**
 * The DatabaseConnector class is responsible for managing the connection
 * to the H2 database, initializing tables, and handling operations related to
 * Hotels, Rooms, Users, and Reservations.
 * 
 * @author Alexander Boutselis
 * 
 */
public class DatabaseConnector {
    private static final String JDBC_URL = "jdbc:h2:~/HotelApplicationDatabase;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1";
    private static final String JDBC_USER = "dbUser";
    private static final String JDBC_PASSWORD = "";
    private static Jdbi databaseConnector;
    private static Handle handle;

    /****************************************************************
     *                          Connections                         *
     ****************************************************************/
    /**
     * Connects to the H2 database and creates the necessary tables
     * if they do not exist.
     */
    public static void connect() {
        try {
            // Create/Connect to Database
            databaseConnector = Jdbi.create(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            // Verify Database exists
            handle = databaseConnector.open();
            System.out.println("Connection to H2 database successful!");

            // Check if Database is missing any of the 4 tables
            if (handle.createQuery("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME IN ('Hotels', 'Rooms', 'Users', 'Reservations')").mapTo(Integer.class).one() != 4) {
                createTables();
            }

        } catch (Exception e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
    }

    /**
     * Disconnects from the H2 database.
     */
    public static void disconnect() {
        if (handle != null) {
            handle.close();
            System.out.println("Disconnected from database.");
        }
    }

    /**
     * Gets the current Handle for database operations. Connects to the database
     * if the handle is null.
     *
     * @return The Handle object for executing database operations.
     */
    public static Handle getHandle() {
        if (handle == null) {
            connect();
        }
        return handle;
    }

    /****************************************************************
     *                Initialize Application Method                *
     ****************************************************************/
    /**
     * Initializes default data for the application on the first app run.
     * 
     * @param numOfRooms number of rooms to create by default.
     */
    public static void generateDefaultState(int numOfRooms) {

        Calendar birthday = Calendar.getInstance();
        AccountManager.createUser("Default", "User", birthday, "User", "Password");
        AccountManager.createManager("Default", "Manager", birthday, "Manager", "Password");
        AccountManager.signOut();
        Hotel defaultHotel = HotelManager.createHotel("Default Hotel", "Default Address, City, State");
        DatabaseManager.setCurrentHotel(defaultHotel);
        String bedType = "";
        Random random = new Random();
        int randomNumber;
        for (int i = 0; i < numOfRooms; i++) {
            randomNumber = random.nextInt(5)+1;
            switch (randomNumber) {
            case 1:
                bedType = "twin";
                randomNumber = random.nextInt(4)+1;
                RoomManager.createRoom(randomNumber, bedType,"");
                break;
            case 2:
                bedType = "full";
                randomNumber = random.nextInt(4)+1;
                RoomManager.createRoom(randomNumber, bedType,"");
                break;
            case 3:
                bedType = "queen";
                randomNumber = random.nextInt(4)+1;
                RoomManager.createRoom(randomNumber, bedType,"");
                break;
            case 4:
                bedType = "king";
                randomNumber = random.nextInt(4)+1;
                RoomManager.createRoom(randomNumber, bedType,"");
                break;
            case 5:
                bedType = "suite";
                randomNumber = random.nextInt(4)+1;
                RoomManager.createRoom(randomNumber, bedType,"");
                break;
            default:
                break;
            }
        }


        DatabaseManager.testPrints();


    }
    /**
     * Initializes the application by connecting to the database and checking if
     * the database contains any data. If no data is found, it initializes default data.
     */
    public static void initializeApplication() {
        try {
            connect();

            boolean hasData = handle.createQuery("SELECT COUNT(*) AS total FROM Hotels")
                    .mapTo(Integer.class)
                    .one() > 0;

            if (!hasData) {
                System.out.println("Database is empty. Initializing default data...");
                generateDefaultState(150);
            } else {
                System.out.println("Database has existing data.");
                // Call database connector to translate database
                translateFromDatabase();
            }
        } catch (Exception e) {
            System.err.println("Failed to Initialize Application: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for better debugging
        }
    }



    /****************************************************************
     *                            Tables                            *
     ****************************************************************/
    /**
     * Creates all the required tables in the H2 database if they do not exist.
     */
    public static void createTables() {
        try {
            createHotelsTable();
            createUsersTable();
            createRoomsTable();
            createReservationsTable();

            // Check that all tables were created successfully
            if (handle.createQuery("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME IN ('Hotels', 'Rooms', 'Users', 'Reservations')").mapTo(Integer.class).one() == 4) {
                System.out.println("All tables created successfully!");
            }
        } catch (Exception e) {
            System.out.println("Error: Tables NOT created! " + e.getMessage());
        }
    }

    /**
     * Creates the Hotels table in the database if it does not exist.
     */
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

    /**
     * Creates the Rooms table in the database if it does not exist.
     */
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

    /**
     * Creates the Users table in the database if it does not exist.
     */
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

    /**
     * Creates the Reservations table in the database if it does not exist.
     */
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
     *                        Add to Database                       *
     ****************************************************************/
    /**
     * Adds a new hotel to the database.
     *
     * @param hotel The hotel object to be added.
     */
    public static void addHotel(Hotel hotel) {
        try {
            // Insert hotel without specifying hotel_id as it is auto-incremented
            String insertSQL = "INSERT INTO Hotels (name, address) VALUES (:name, :address)";

            int hotelID = handle.createUpdate(insertSQL)
                    .bind("name", HotelManager.getHotelName(hotel))
                    .bind("address", HotelManager.getHotelAddress(hotel))
                    .executeAndReturnGeneratedKeys("hotel_id")
                    .mapTo(int.class)
                    .one();
            
            // Set the hotelID to the hotel object
            HotelManager.setHotelID(hotel, hotelID);

            // Log success
            System.out.println("New hotel added successfully with Hotel ID: " + hotelID + "\n");
        } catch (Exception e) {
            System.err.println("Failed to add hotel: " + e.getMessage());
        }
    }

    /**
     * Adds a new room to the database.
     *
     * @param room The room object to be added.
     */
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
                    .bind("room_description", RoomManager.getRoomDescription(room))
                    .execute();
            System.out.println("New room added successfully");
        } catch (Exception e) {
            System.err.println("Failed to add room: " + e.getMessage());
        }
    }

    /**
     * Adds a new user account to the database.
     *
     * @param user The user object to be added.
     */
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

    /**
     * Adds a new reservation to the database.
     *
     * @param reservation The reservation object to be added.
     */
    public static void addReservation(Reservation reservation) {
        try {
            AccountManager.printAccountInfo(ReservationManager.getAssignedUser(reservation));
            String insertSQL = "INSERT INTO Reservations (user_id, room_id, hotel_id, check_in_date, check_out_date, total_cost) VALUES (:user_id, :room_id, :hotel_id, :check_in_date, :check_out_date, :total_cost)";
            int reservationID = handle.createUpdate(insertSQL)
                    .bind("user_id", AccountManager.getUserID(ReservationManager.getAssignedUser(reservation)))
                    .bind("room_id", RoomManager.getRoomID(ReservationManager.getRoom(reservation)))
                    .bind("hotel_id", HotelManager.getHotelID(ReservationManager.getHotel()))
                    .bind("check_in_date", Date.valueOf(ReservationManager.getStartDate(reservation)))
                    .bind("check_out_date", Date.valueOf(ReservationManager.getEndDate(reservation)))
                    .bind("total_cost", ReservationManager.getTotalPrice(reservation))
                    .executeAndReturnGeneratedKeys("reservation_id").mapTo(int.class).one();
            ReservationManager.setReservationID(reservation, reservationID);
            System.out.println("Reservation added successfully to the database.");
        } catch (Exception e) {
            System.err.println("Failed to add reservation to the database: " + e.getMessage());
        }
    }

    /****************************************************************
     *                        Update Database                       *
     ****************************************************************/
    /**
     * Updates an existing hotel in the database.
     *
     * @param hotel The hotel object containing updated information.
     * @return true if the update was successful, false otherwise.
     */
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

    /**
     * Updates an existing room in the database.
     *
     * @param room The room object containing updated information.
     * @return true if the update was successful, false otherwise.
     */
    public static boolean updateRoomInDatabase(Room room) {
        try {
            Handle handle = getHandle();
            String updateSQL = "UPDATE Rooms SET hotel_id = :hotel_id, room_number = :room_number, bed_type = :bed_type, num_of_beds = :num_of_beds, price_per_night = :price_per_night, room_description = :room_description WHERE room_id = :room_id";
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

    /**
     * Updates an existing user in the database.
     *
     * @param user The user object containing updated information.
     * @return true if the update was successful, false otherwise.
     */
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
                    .bind("birthday", user.getBirthday() != null ? new Date(user.getBirthday().getTimeInMillis()) : null)
                    .bind("start_date", user instanceof Manager && ((Manager) user).getStartDate() != null ? new Timestamp(((Manager) user).getStartDate().getTimeInMillis()) : null)
                    .bind("employee_num", user instanceof Manager ? AccountManager.getEmployeeNumber((Manager) user) : null)
                    .execute();
            System.out.println("User with ID " + AccountManager.getUserID(user) + " updated successfully.");
            return true;
        } catch (NullPointerException e) {
            System.err.println("Failed to update user in database: User ID is null.");
            return false;
        } catch (Exception e) {
            System.err.println("Failed to update user in database: " + e.getMessage());
            return false;
        }
    }

    /**
     * Updates an existing reservation in the database.
     *
     * @param reservation The reservation object containing updated information.
     * @return true if the update was successful, false otherwise.
     */
    public static boolean updateReservationInDatabase(Reservation reservation) {
        try {
            Handle handle = getHandle();
            String updateSQL = "UPDATE Reservations SET user_id = :user_id, room_id = :room_id, hotel_id = :hotel_id, check_in_date = :check_in_date, check_out_date = :check_out_date, total_cost = :total_cost WHERE reservation_id = :reservation_id";
            handle.createUpdate(updateSQL)
                    .bind("reservation_id", reservation.getReservationID())
                    .bind("user_id", ReservationManager.getAssignedUser(reservation).getUserID())
                    .bind("room_id", ReservationManager.getRoom(reservation).getRoomID())
                    .bind("hotel_id", ReservationManager.getRoom(reservation).getRoomID() / 1000)
                    .bind("check_in_date", ReservationManager.getStartDate(reservation))
                    .bind("check_out_date", ReservationManager.getEndDate(reservation))
                    .bind("total_cost", ReservationManager.getTotalPrice(reservation))
                    .execute();
            System.out.println("Reservation with ID " + reservation.getReservationID() + " updated successfully.");
            return true;
        } catch (Exception e) {
            System.err.println("Failed to update reservation in database: " + e.getMessage());
            return false;
        }
    }


    /****************************************************************
     *                            Query                             *
     ****************************************************************/
    /**
     * Gets the number of items in the specified table.
     *
     * @param tableName the name of the table to query
     * @return the number of items in the table
     */
    public static int getNumberOfItemsInTable(String tableName) {
        String sqlQuery = "SELECT COUNT(*) FROM " + tableName;
        return handle.createQuery(sqlQuery)
                .mapTo(Integer.class)
                .one();
    }

    /********************************
     *      Get List of IDs         *
     ********************************/
    /**
     * Gets a list of IDs from the specified table.
     *
     * @param tableName the name of the table to query
     * @param idColumnName the name of the column containing the IDs
     * @return a list of IDs from the table
     */
    public static List<Integer> getAllIdsFromTable(String tableName, String idColumnName) {
        String sqlQuery = "SELECT " + idColumnName + " FROM " + tableName;
        return handle.createQuery(sqlQuery)
                .mapTo(Integer.class)
                .list();
    }

    /********************************
     *     Translate From Database  *
     ********************************/
    /**
     * Translates data from the database into application objects.
     */
    public static void translateFromDatabase() {
        // Translate Users
        if (getNumberOfItemsInTable("Users") > 0) {
            List<Integer> userIDs = getAllIdsFromTable("Users", "user_id");
            for (int userID : userIDs) {
                AccountManager.addAccount(translateUserFromDatabase(userID));
            }
        }

        // Translate Hotels
        if (getNumberOfItemsInTable("Hotels") > 0) {
            List<Integer> hotelIDs = getAllIdsFromTable("Hotels", "hotel_id");
            for (int hotelID : hotelIDs) {
                HotelManager.addHotel(translateHotelFromDatabase(hotelID));
            }
        }

        // Translate Rooms
        if (getNumberOfItemsInTable("Rooms") > 0) {
            List<Integer> roomIDs = getAllIdsFromTable("Rooms", "room_id");
            for (int roomID : roomIDs) {
                int hotelID = roomID / 1000;
                HotelManager.addRoomToHotel(HotelManager.getHotel(hotelID), translateRoomFromDatabase(roomID));
            }
        }

        // Translate Reservations
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
    /**
     * Translates a hotel from the database into a Hotel object.
     *
     * @param hotelID the ID of the hotel to translate
     * @return the Hotel object corresponding to the hotelID
     */
    public static Hotel translateHotelFromDatabase(int hotelID) {
        try {
            String sqlQuery = "SELECT * FROM Hotels WHERE hotel_id = :hotelId";
            return handle.createQuery(sqlQuery)
                    .bind("hotelId", hotelID)
                    .map((resultSet, statementContext) -> new Hotel(
                            resultSet.getInt("hotel_id"),
                            resultSet.getString("name"),
                            resultSet.getString("address")
                    )).one();
        } catch (Exception e) {
            return null;
        }
    }

    /********************************
     *     Translate Room Object    *
     ********************************/
    /**
     * Translates a room from the database into a Room object.
     *
     * @param roomId the ID of the room to translate
     * @return the Room object corresponding to the roomId
     */
    public static Room translateRoomFromDatabase(int roomId) {
        try {
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
        } catch (Exception e) {
            return null;
        }
    }

    /********************************
     *    Translate User Object     *
     ********************************/
    /**
     * Translates a user from the database into a User object.
     *
     * @param userId the ID of the user to translate
     * @return the User object corresponding to the userId
     */
    public static User translateUserFromDatabase(int userId) {
        try {    
            String sqlQuery = "SELECT * FROM Users WHERE user_id = :userId";
            return getHandle().createQuery(sqlQuery)
                    .bind("userId", userId)
                    .map((resultSet, statementContext) -> {
                        if (resultSet.getObject("employee_num") != null) {
                            return new Manager(
                                    userId,
                                    resultSet.getInt("employee_num"),
                                    resultSet.getString("first_name"),
                                    resultSet.getString("last_name"),
                                    Calendar.getInstance(),
                                    resultSet.getString("username"),
                                    resultSet.getString("password"),
                                    Calendar.getInstance(),
                                    Calendar.getInstance(),
                                    true);
                        } else {
                            return new User(
                                    userId,
                                    resultSet.getString("first_name"),
                                    resultSet.getString("last_name"),
                                    Calendar.getInstance(),
                                    resultSet.getString("username"),
                                    resultSet.getString("password"),
                                    true);
                        }
                    }).one();
        } catch (Exception e) {
            return null;
        }
    }

    /********************************
     * Translate Reservation Object *
     ********************************/
    /**
     * Translates a reservation from the database into a Reservation object.
     *
     * @param reservationId the ID of the reservation to translate
     * @return the Reservation object corresponding to the reservationId
     */
    public static Reservation translateReservationFromDatabase(int reservationId) {
        try {
            String sqlQuery = "SELECT * FROM Reservations WHERE reservation_id = :reservationId";
            return getHandle().createQuery(sqlQuery)
                    .bind("reservationId", reservationId)
                    .map((resultSet, statementContext) -> new Reservation(
                            resultSet.getInt("reservation_id"),
                            resultSet.getInt("user_id"),
                            resultSet.getInt("room_id"),
                            resultSet.getInt("hotel_id"),
                            resultSet.getDate("check_in_date").toLocalDate(),
                            resultSet.getDate("check_out_date").toLocalDate(),
                            resultSet.getDouble("total_cost")
                    )).one();
        } catch (Exception e) {
            return null;
        }
    }


    /****************************************************************
     *                            Delete                             *
     ****************************************************************/
    /**
     * Deletes the database file from the user's home directory.
     */
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
    /**
     * Drops the specified table from the database.
     *
     * @param tableName the name of the table to drop
     */
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
    /**
     * Deletes all rows from the specified table and resets its auto-increment value.
     *
     * @param tableName the name of the table to empty
     */
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
    /**
     * Deletes all data from all tables in the database and resets auto-increment values.
     */
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
     *  Remove Object from Database *
     ********************************/
    /**
     * Removes a specific object (Hotel, Room, User, Manager, or Reservation) from the database by its ID and resets the auto-increment value.
     *
     * @param itemType the type of the item to remove (can be "Hotel", "Room", "User", "Manager", or "Reservation")
     * @param id the ID of the item to remove
     */
    public static void removeObjectFromDatabase(String itemType, int id) {
        try {
            StringBuilder deleteSQL = new StringBuilder();
            String resetSQL;
            itemType = itemType.toLowerCase();

            switch (itemType) {
                case "hotel":
                    deleteSQL.append("DELETE FROM Hotels WHERE hotel_id = :id");
                    handle.createUpdate(deleteSQL.toString())
                          .bind("id", id)
                          .execute();
                    resetSQL = "ALTER TABLE Hotels ALTER COLUMN hotel_id RESTART WITH (SELECT COALESCE(MAX(hotel_id), 0) + 1 FROM Hotels)";
                    handle.execute(resetSQL);
                    System.out.println("Hotel with ID " + id + " removed successfully.\n");
                    break;
                case "room":
                    deleteSQL.append("DELETE FROM Rooms WHERE room_id = :id");
                    handle.createUpdate(deleteSQL.toString())
                          .bind("id", id)
                          .execute();
                    System.out.println("Room with ID " + id + " removed successfully.\n");
                    break;
                case "user":
                case "manager":
                    deleteSQL.append("DELETE FROM Users WHERE user_id = :id");
                    handle.createUpdate(deleteSQL.toString())
                          .bind("id", id)
                          .execute();
                    resetSQL = "ALTER TABLE Users ALTER COLUMN user_id RESTART WITH (SELECT COALESCE(MAX(user_id), 0) + 1 FROM Users)";
                    handle.execute(resetSQL);
                    System.out.println(itemType + " with ID " + id + " removed successfully.\n");
                    break;
                case "reservation":
                    deleteSQL.append("DELETE FROM Reservations WHERE reservation_id = :id");
                    handle.createUpdate(deleteSQL.toString())
                          .bind("id", id)
                          .execute();
                    resetSQL = "ALTER TABLE Reservations ALTER COLUMN reservation_id RESTART WITH (SELECT COALESCE(MAX(reservation_id), 0) + 1 FROM Reservations)";
                    handle.execute(resetSQL);
                    System.out.println("Reservation with ID " + id + " removed successfully.\n");
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported item type: " + itemType);
            }
        } catch (Exception e) {
            System.err.println("Failed to remove item by ID: " + e.getMessage());
        }
    }

   

/********************************
 *   Remove Item from Database  *
 ********************************/
/**
 * Removes a specific item (Hotel, Room, User, Manager, or Reservation) from the database.
 *
 * @param item the item to remove (can be Hotel, Room, User, Reservation, or Manager)
 */
public static void removeItemFromDatabase(Object item) {
    try {
        StringBuilder deleteSQL = new StringBuilder();
        String resetSQL;
        if (item instanceof Hotel) {
            Hotel hotel = (Hotel) item;
            int hotelID = HotelManager.getHotelID(hotel);
            deleteSQL.append("DELETE FROM Hotels WHERE hotel_id = :hotel_id");
            handle.createUpdate(deleteSQL.toString())
                  .bind("hotel_id", hotelID)
                  .execute();
            resetSQL = "ALTER TABLE Hotels ALTER COLUMN hotel_id RESTART WITH (SELECT COALESCE(MAX(hotel_id), 0) + 1 FROM Hotels)";
            handle.execute(resetSQL);
            System.out.println("Hotel with ID " + hotelID + " removed successfully.\n");
        } else if (item instanceof Room) {
            Room room = (Room) item;
            int roomID = RoomManager.getRoomID(room);
            deleteSQL.append("DELETE FROM Rooms WHERE room_id = :room_id");
            handle.createUpdate(deleteSQL.toString())
                  .bind("room_id", roomID)
                  .execute();
            System.out.println("Room with ID " + roomID + " removed successfully.\n");
        } else if (item instanceof Manager) {
            Manager manager = (Manager) item;
            int managerID = AccountManager.getUserID(manager);
            deleteSQL.append("DELETE FROM Users WHERE user_id = :user_id");
            handle.createUpdate(deleteSQL.toString())
                  .bind("user_id", managerID)
                  .execute();
            resetSQL = "ALTER TABLE Users ALTER COLUMN user_id RESTART WITH (SELECT COALESCE(MAX(user_id), 0) + 1 FROM Users)";
            handle.execute(resetSQL);
            System.out.println("Manager with ID " + managerID + " removed successfully.\n");
        } else if (item instanceof User) {
            User user = (User) item;
            int userID = AccountManager.getUserID(user);
            deleteSQL.append("DELETE FROM Users WHERE user_id = :user_id");
            handle.createUpdate(deleteSQL.toString())
                  .bind("user_id", userID)
                  .execute();
            resetSQL = "ALTER TABLE Users ALTER COLUMN user_id RESTART WITH (SELECT COALESCE(MAX(user_id), 0) + 1 FROM Users)";
            handle.execute(resetSQL);
            System.out.println("User with ID " + userID + " removed successfully.\n");
        } else if (item instanceof Reservation) {
            Reservation reservation = (Reservation) item;
            int reservationID = ReservationManager.getReservationID(reservation);
            deleteSQL.append("DELETE FROM Reservations WHERE reservation_id = :reservation_id");
            handle.createUpdate(deleteSQL.toString())
                  .bind("reservation_id", reservationID)
                  .execute();
            resetSQL = "ALTER TABLE Reservations ALTER COLUMN reservation_id RESTART WITH (SELECT COALESCE(MAX(reservation_id), 0) + 1 FROM Reservations)";
            handle.execute(resetSQL);
            System.out.println("Reservation with ID " + reservationID + " removed successfully.\n");
        } else {
            throw new IllegalArgumentException("Unsupported item type: " + item.getClass().getName());
        }
    } catch (Exception e) {
        System.err.println("Failed to remove item: " + e.getMessage());
    }
}




    /****************************************************************
     *                            Print                             *
     ****************************************************************/
    /********************************
     *        Print all Tables      *
     ********************************/
    /**
     * Prints all Tables in the database
     */
    public static void printAllTables() {
        try {
            printHotelsTable();
            printRoomsTable();
            printUsersTable();
            printReservationsTable();
        } catch (Exception e) {
            System.err.println("Failed to print tables: " + e.getMessage());
        }
    }

    /********************************
     *     Print the Hotels table   *
     ********************************/
    /**
     * Prints all rows of the Hotels table in a formatted manner to the terminal.
     */
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
     *      Print Rooms Table       *
     ********************************/
    /**
     * Prints all rows of the Rooms table in a formatted manner to the terminal.
     */
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
    
    /********************************
     *      Print Users Table       *
     ********************************/
    /**
     * Prints all rows of the Users table in a formatted manner to the terminal.
     */
    public static void printUsersTable() {
        try {
            // Make sure the database handle is initialized
            if (handle == null) {
                throw new IllegalStateException("Database handle is not initialized.");
            }

            StringBuilder querySQL = new StringBuilder();
            querySQL.append("SELECT user_id, first_name, last_name, username, birthday, employee_num FROM Users");
            Query query = handle.createQuery(querySQL.toString());

            // Print the table header
            System.out.println("\n--------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-10s %-15s %-15s %-15s %-15s %-15s %-15s", "User ID", "First Name", "Last Name", "Username", "Birthday", "Account Type", "Employee Number"));
            System.out.println("--------------------------------------------------------------------------------------------------------------");

            // Print each row in the Users table
            query.map((rs, ctx) -> {
                try {
                    String role;
                    String employeeNumber = rs.getString("employee_num");
                    if (employeeNumber == null) {
                        role = "User";
                        employeeNumber = "N/A";
                    } else {
                        role = "Manager";
                    }
                    String userRow = String.format("%-10d %-15s %-15s %-15s %-15s %-15s %-15s",
                            rs.getInt("user_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("username"),
                            rs.getDate("birthday"),
                            role,
                            employeeNumber);
                    System.out.println(userRow);
                } catch (Exception e) {
                    System.err.println("Error processing row: " + e.getMessage());
                }
                return null;
            }).list();

            System.out.println("--------------------------------------------------------------------------------------------------------------\n");
        } catch (Exception e) {
            System.err.println("Failed to print Users table: " + e.getMessage());
            e.printStackTrace(); // Print the full stack trace for better debugging
        }
    }




    /********************************
     *    Print Reservations Table  *
     ********************************/
    /**
     * Prints all rows of the Reservations table in a formatted manner to the terminal.
     */
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