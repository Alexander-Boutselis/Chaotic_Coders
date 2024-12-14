//DatabaseManager.java


package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.util.ArrayList;

/**
* The DatabaseManager class provides static methods to interact with the Database class.
* It is responsible for managing hotel, user, and room data and provides utility methods
* for adding, removing, retrieving, and updating information.
* 
* @author Alexander Boutselis
* 
*/
public class DatabaseManager {

    //Single Instance of Database
    private static Database database = new Database();

    //Private constructor to prevent instantiation
    private DatabaseManager() {}

    
    /****************************************************************
     *                           Hotel Data                         *
     ****************************************************************/
    /**
    * Adds a hotel to the database.
    *
    * @param hotel The Hotel object to be added.
    */
    public static void addHotel(Hotel hotel) {
        database.addHotel(hotel);
    }

    /**
    * Removes a hotel from the database by its name.
    *
    * @param hotel The name of the hotel to be removed.
    */
    public static void removeHotel(String hotel) {
        database.removeHotel(hotel);
    }

    /**
    * Retrieves a hotel from the database by its name.
    *
    * @param searchHotel The name of the hotel to be searched.
    * @return The Hotel object if found, otherwise null.
    */
    public static Hotel getHotel(String searchHotel) {
        return database.getHotel(searchHotel);
    }

    /**
    * Retrieves a hotel from the database by its ID.
    *
    * @param hotelID The ID of the hotel to be searched.
    * @return The Hotel object if found, otherwise null.
    */
    public static Hotel getHotel(int hotelID) {
        return database.getHotel(hotelID);
    }

    /**
    * Retrieves all hotels from the database.
    *
    * @return A list of all Hotel objects.
    */
    public static ArrayList<Hotel> getAllHotels() {
        return database.getAllHotels();
    }

    /**
    * Adds or updates a hotel in the database.
    * If the hotel already exists, it is updated; otherwise, it is added.
    *
    * @param hotel The Hotel object to be added or updated.
    */
    public static void addOrUpdateHotel(Hotel hotel) {
        if (database.getHotel(hotel.getHotelName()) != null) {
            //DatabaseConnector.updateHotel(hotel);
        } else {
            DatabaseConnector.addHotel(hotel);
        }
    }


    /****************************************************************
     *                           User Data                          *
     ****************************************************************/
    /**
    * Adds a user to the database.
    *
    * @param newUser The User object to be added.
    */
    public static void addUser(User newUser) {
        database.addUser(newUser);
    }

    /**
    * Removes a user from the database by its username.
    *
    * @param username The username of the user to be removed.
    */
    public static void removeUser(String username) {
        database.removeUser(username);
    }

    /**
    * Retrieves a user from the database by its username.
    *
    * @param searchUsername The username of the user to be searched.
    * @return The User object if found, otherwise null.
    */
    public static User getUser(String searchUsername) {
        return database.getUser(searchUsername);
    }

    /**
    * Retrieves a user from the database by its username.
    *
    * @param userID The ID of the user to be searched.
    * @return The User object if found, otherwise null.
    */
    public static User getUser(int userID) {
        return database.getUser(userID);
    }

    /**
    * Retrieves all users from the database.
    *
    * @return A list of all User objects.
    */
    public static ArrayList<User> getAllUsers() {
        return database.getAllUsers();
    }

    /**
    * Adds or updates a user in the database.
    * If the user already exists, it is updated; otherwise, it is added.
    *
    * @param user The User object to be added or updated.
    */
    public static void addOrUpdateUser(User user) {
        if (database.getUser(user.getUsername()) != null) {
            //DatabaseConnector.updateUser(user);
        } else {
           // DatabaseConnector.addUser(user);
        }
    }

    /**
    * Gets the next unused employee number.
    *
    * @return The next available employee number.
    */
    public static int nextEmployeeNumber() {
        int largestEmployeeNumber = 0;
        for (User manager : database.getAllUsers()) {
            if (manager instanceof Manager) {
                int managerEmployeeNumber = ((Manager) manager).getEmployeeNumber();
                if (largestEmployeeNumber < managerEmployeeNumber) {
                    largestEmployeeNumber = managerEmployeeNumber;
                }
            }
        }
        return largestEmployeeNumber + 1;
    }


    /****************************************************************
     *                         Reservation                          *
     ****************************************************************/

    public static void addReservation(Reservation reservation){
        database.addReservation(reservation);
    }

    public static void removeReservation(Reservation reservation){
        database.removeReservation(ReservationManager.getReservationID(reservation));
        DatabaseConnector.removeItemFromDatabase(reservation);
    }

    public static ArrayList<Reservation> getAllReservations(){
        return database.getAllReservations();
    }


    /****************************************************************
     *                           Current                            *
     ****************************************************************/
    /**
    * Retrieves the current hotel being managed.
    *
    * @return The current Hotel object.
    */
    public static Hotel getCurrentHotel() {
        return database.getCurrentHotel();
    }

    /**
    * Sets the current hotel being managed.
    *
    * @param hotel The Hotel object to set as the current hotel.
    */
    public static void setCurrentHotel(Hotel hotel) {
        database.setCurrentHotel(hotel);
    }

    /**
    * Signs in a user.
    *
    * @param user The User object to sign in.
    */
    public static void signIn(User user) {
        database.setCurrentUser(user);
        database.setSignedInStatus(true);
    }

    /**
    * Signs out the current user.
    */
    public static void signOut() {
        database.setCurrentUser(null);
        database.setSignedInStatus(false);
    }

    /**
    * Retrieves the current user that is signed in.
    *
    * @return The current User object.
    */
    public static User getCurrentUser() {
        return database.getCurrentUser();
    }

    /**
    * Checks if a user is signed in.
    *
    * @return True if a user is signed in, otherwise false.
    */
    public static boolean isSignedIn() {
        return database.isSignedIn();
    }



    /****************************************************************
     *                           Prints                             *
     ****************************************************************/
    /**
    * Prints both the objects in the app and the database to compare. Used for Debugging.
    */
    public static void testPrints(){

        System.out.println("\n-----Printing Objects in Applicaiton-----");
        System.out.println("\t---Printing Users---");
        //Get all Users Objects
        ArrayList<User> allUsers = DatabaseManager.getAllUsers();

        //Loop through and print each account
        for (User user : allUsers){
        AccountManager.printAccountInfo(user);
        }

        System.out.println("-----Printing Objects in Database-----");
        System.out.println("\t---Printing Users---");
        DatabaseConnector.printUsersTable();


        System.out.println("-----Printing Objects in Applicaiton-----");
        System.out.println("\t---Printing Hotels and Rooms---");
        //Get all Hotel Objects
        ArrayList<Hotel> allHotels = DatabaseManager.getAllHotels();
        ArrayList<Reservation> allReservations = new ArrayList<Reservation>();
        //Get all Reservation Objects

        //Loop through and print each Hotel
        for (Hotel hotel : allHotels){
        System.out.println(HotelManager.getHotelAndRoomsInfo(hotel));
        allReservations.addAll(HotelManager.getAllReservations(hotel));
        }
        System.out.println("-----Printing Objects in Database-----");
        System.out.println("\t---Printing Hotels and Rooms---");
        DatabaseConnector.printHotelsTable();
        DatabaseConnector.printRoomsTable();

        System.out.println("-----Printing Objects in Applicaiton-----");
        System.out.println("\t---Printing Reservations---");

        //Loop through and print each account
        for (Reservation reservation : allReservations){
        ReservationManager.printReservation(reservation);
        }
        
        System.out.println("-----Printing Objects in Database-----");
        System.out.println("\t---Printing Reservations---");
        DatabaseConnector.printReservationsTable();


    }


    /****************************************************************
     *                           End                                *
     ****************************************************************/
}//End of Database Manager
