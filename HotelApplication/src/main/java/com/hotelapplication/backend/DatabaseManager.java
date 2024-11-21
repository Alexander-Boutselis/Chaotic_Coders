package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.util.ArrayList;

public class DatabaseManager {

    //Single Instance of Database
    private static Database database = new Database();

    //Private constructor to prevent instantiation
    private DatabaseManager() {}


    //static {
        // Register a shutdown hook to ensure database disconnection
    //    Runtime.getRuntime().addShutdownHook(new Thread(() -> DatabaseConnector.disconnect()));
    //}

    /****************************************************************
     *                    Initialize       *WIP                     *
     ****************************************************************/
    public static void initializeDatabase() {
        //Try to connect to database, if it does not exist, create it
        DatabaseConnector.connect();
    }

    /****************************************************************
     *                    Hotel Data                               *
     ****************************************************************/

    // Static method to add a hotel
    public static void addHotel(Hotel hotel) {
        database.addHotel(hotel);
    }

    // Static method to remove a hotel
    public static void removeHotel(String hotel) {
        database.removeHotel(hotel);
    }

    // Static method to get a hotel by name
    public static Hotel getHotel(String searchHotel) {
        return database.getHotel(searchHotel);
    }

    // Static method to get a hotel by ID
    public static Hotel getHotel(int hotelID) {
        return database.getHotel(hotelID);
    }

    public static ArrayList<Hotel> getAllHotels(){
        return database.getAllHotels();
    }

    // Static method to check if hotel exists, update if it does, otherwise add it
    public static void addOrUpdateHotel(Hotel hotel) {
        if (database.getHotel(hotel.getHotelName()) != null) {
            //DatabaseConnector.updateHotel(hotel);
        } else {
            DatabaseConnector.addHotel(hotel);
        }
    }


    /****************************************************************
     *                    Room Data                                *
     ****************************************************************/
    // Static method to check if room exists, update if it does, otherwise add it
    public static void addOrUpdateRoom(Room room) {
        //if (database.getRoom(room.getRoomID()) != null) {
           // DatabaseConnector.updateRoom(room);
        //} else {
            //DatabaseConnector.addRoom(room);
        //}
    }


    /****************************************************************
     *                    User Data                                *
     ****************************************************************/
    // Static method to add a user
    public static void addUser(User newUser) {
        database.addUser(newUser);
    }

    // Static method to remove a user
    public static void removeUser(String username) {
        database.removeUser(username);
    }

    // Static method to get a user
    public static User getUser(String searchUsername) {
        return database.getUser(searchUsername);
    }

    //Get all Users
    public static ArrayList<User> getAllUsers(){
        return database.getAllUsers();
    }

    // Static method to check if user exists, update if it does, otherwise add it
    public static void addOrUpdateUser(User user) {
        if (database.getUser(user.getUsername()) != null) {
            // Assuming you have a method like DatabaseConnector.updateUser(user)
            //DatabaseConnector.updateUser(user);
        } else {
           // DatabaseConnector.addUser(user);
        }
    }

   //Get the next unused Employee Number
   public static int nextEmployeeNumber(){
    int largestEmployeeNumber = 0;
    for (User manager : database.getAllUsers()){
        if (manager instanceof Manager){
            int managerEmployeeNumber = ((Manager) manager).getEmployeeNumber();
            if(largestEmployeeNumber < managerEmployeeNumber){
                largestEmployeeNumber = managerEmployeeNumber;
            }
        }
    }
    return largestEmployeeNumber + 1;
   }


    /****************************************************************
     *                            Current                           *
     ****************************************************************/
    //Get Current Hotel
    public static Hotel getCurrentHotel(){
        return database.getCurrentHotel();
    }

    //Set Current Hotel
    public static void setCurrentHotel(Hotel hotel){
        database.setCurrentHotel(hotel);
    }

    //Sign in User
    public static void signIn(User user){
        database.setCurrentUser(user);
        database.setSignedInStatus(true);
    }

    //Sign out User
    public static void signOut(){
        database.setCurrentUser(null);
        database.setSignedInStatus(false);
    }

    public static User getCurrentUser(){
        return database.getCurrentUser();
    }

    public static boolean isSignedIn(){
        return database.isSignedIn();
    }


    /****************************************************************
     *                           End                                *
     ****************************************************************/
}//End of DatabaseManager Class
