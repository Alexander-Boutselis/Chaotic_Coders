//HotelManager.java


package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.util.ArrayList;
import java.util.Scanner;
import org.jdbi.v3.core.Handle;

/**
 * The HotelManager class provides static methods to manage hotels, rooms,
 * reservations, and various hotel information. It interacts with the DatabaseManager
 * and DatabaseConnector to store and retrieve data.
 * 
 * @author Alexander Boutselis
 * 
 */
public class HotelManager {
    
    //Private constructor to prevent instantiation
    private HotelManager() {}

    /****************************************************************
     *                     Create/Destroy Hotel                     *
     ****************************************************************/
    /**
     * Creates a new hotel with the given name and address.
     *
     * @param hotelName The name of the hotel.
     * @param hotelAddress The address of the hotel.
     * 
     * @return newHotel Object
     */
    public static Hotel createHotel(String hotelName, String hotelAddress) {
        try {
            Hotel newHotel = new Hotel(hotelName, hotelAddress);
            DatabaseManager.addHotel(newHotel);
            DatabaseConnector.addHotel(newHotel);
            return newHotel;
        } catch (Exception e) {
            System.out.println("Failed to create Hotel and Add to Database");
            return null;
        }
    }

    /****************************************************************
     *                        Get Hotel Info                        *
     ****************************************************************/
    /**
     * Gets the name of the current hotel.
     *
     * @return The name of the current hotel.
     */
    public static String getHotelName() {
        return DatabaseManager.getCurrentHotel().getHotelName();
    }

    /**
     * Gets the name of the specified hotel.
     *
     * @param hotel The hotel object.
     * @return The name of the hotel.
     */
    public static String getHotelName(Hotel hotel) {
        return hotel.getHotelName();
    }

    /**
     * Gets the ID of the current hotel.
     *
     * @return The ID of the current hotel.
     */
    public static int getHotelID() {
        return DatabaseManager.getCurrentHotel().getHotelID();
    }

    /**
     * Gets the ID of the specified hotel.
     *
     * @param hotel The hotel object.
     * @return The ID of the hotel.
     */
    public static int getHotelID(Hotel hotel) {
        return hotel.getHotelID();
    }

    /**
     * Gets the address of the current hotel.
     *
     * @return The address of the current hotel.
     */
    public static String getHotelAddress() {
        return DatabaseManager.getCurrentHotel().getHotelAddress();
    }

    /**
     * Gets the address of the specified hotel.
     *
     * @param hotel The hotel object.
     * @return The address of the hotel.
     */
    public static String getHotelAddress(Hotel hotel) {
        return hotel.getHotelAddress();
    }

    /**
     * Gets the total number of rooms in the current hotel.
     *
     * @return The total number of rooms in the current hotel.
     */
    public static int getTotalNumberOfRooms() {
        return getAllHotelRooms().size();
    }

    /**
     * Gets the total number of rooms in the specified hotel.
     *
     * @param hotel The hotel object.
     * @return The total number of rooms in the hotel.
     */
    public static int getTotalNumberOfRooms(Hotel hotel) {
        return getAllHotelRooms(hotel).size();
    }

    /****************************************************************
     *                        Set Hotel Info                        *
     ****************************************************************/
    /**
     * Sets the name of the current hotel.
     *
     * @param newHotelName The new name of the hotel.
     */
    public static void setHotelName(String newHotelName) {
        DatabaseManager.getCurrentHotel().setHotelName(newHotelName);
        DatabaseConnector.updateHotelInDatabase(DatabaseManager.getCurrentHotel());
    }

    /**
     * Sets the name of the specified hotel.
     *
     * @param hotel The hotel object.
     * @param newHotelName The new name of the hotel.
     */
    public static void setHotelName(Hotel hotel, String newHotelName) {
        hotel.setHotelName(newHotelName);
        DatabaseConnector.updateHotelInDatabase(hotel);
    }

    /**
     * Sets the ID of the current hotel.
     *
     * @param hotelID The new ID of the hotel.
     */
    public static void setHotelID(int hotelID) {
        DatabaseManager.getCurrentHotel().setHotelID(hotelID);
        DatabaseConnector.updateHotelInDatabase(DatabaseManager.getCurrentHotel());
    }

    /**
     * Sets the ID of the specified hotel.
     *
     * @param hotel The hotel object.
     * @param hotelID The new ID of the hotel.
     */
    public static void setHotelID(Hotel hotel, int hotelID) {
        hotel.setHotelID(hotelID);
        DatabaseConnector.updateHotelInDatabase(hotel);
    }

    /**
     * Sets the address of the current hotel.
     *
     * @param hotelAddress The new address of the hotel.
     */
    public static void setHotelAddress(String hotelAddress) {
        DatabaseManager.getCurrentHotel().setHotelAddress(hotelAddress);
        DatabaseConnector.updateHotelInDatabase(DatabaseManager.getCurrentHotel());
    }

    /**
     * Sets the address of the specified hotel.
     *
     * @param hotel The hotel object.
     * @param hotelAddress The new address of the hotel.
     */
    public static void setHotelAddress(Hotel hotel, String hotelAddress) {
        hotel.setHotelAddress(hotelAddress);
        DatabaseConnector.updateHotelInDatabase(hotel);
    }

    /****************************************************************
     *                          Hotels and Rooms                    *
     ****************************************************************/
    /**
     * Gets a hotel by its name.
     *
     * @param searchHotelName The name of the hotel to search for.
     * @return The Hotel object if found, otherwise null.
     */
    public static Hotel getHotel(String searchHotelName) {
        return DatabaseManager.getHotel(searchHotelName);
    }

    /**
     * Gets a hotel by its ID.
     *
     * @param hotelID The ID of the hotel to search for.
     * @return The Hotel object if found, otherwise null.
     */
    public static Hotel getHotel(int hotelID) {
        return DatabaseManager.getHotel(hotelID);
    }

    /**
     * Gets all hotels in the database.
     *
     * @return A list of all hotels.
     */
    public static ArrayList<Hotel> getAllHotels() {
        return DatabaseManager.getAllHotels();
    }

    /**
     * Gets the current hotel.
     *
     * @return The current Hotel object.
     */
    public static Hotel getCurrentHotel() {
        return DatabaseManager.getCurrentHotel();
    }

    /**
     * Sets the current hotel.
     *
     * @param hotel The hotel object to set as the current hotel.
     */
    public static void setCurrentHotel(Hotel hotel) {
        DatabaseManager.setCurrentHotel(hotel);
    }

    /**
     * Gets all rooms from the current hotel.
     *
     * @return A list of all rooms in the current hotel.
     */
    public static ArrayList<Room> getAllHotelRooms() {
        return DatabaseManager.getCurrentHotel().getAllRooms();
    }

    /**
     * Gets all rooms from the specified hotel.
     *
     * @param hotel The hotel object.
     * @return A list of all rooms in the hotel.
     */
    public static ArrayList<Room> getAllHotelRooms(Hotel hotel) {
        return hotel.getAllRooms();
    }

    /**
     * Gets a Room Object from a Hotel.
     *
     * @param roomID The Room ID integer.
     * @return A Room Object.
     */
    public static Room getRoom(int roomID) {
        return DatabaseConnector.translateRoomFromDatabase(roomID);
    }


    /**
     * Gets a 
     *
     * @param hotel 
     * @return 
     */
    public static double calcHotelEarnings() {
        double earnings = 0;
        for (Reservation reservation : getAllReservations()) {
           earnings += ReservationManager.getTotalPrice(reservation);
        }
        ;
        return earnings;
    }



    /****************************************************************
     *                            Adders                            *
     ****************************************************************/
    /**
     * Adds a hotel to the database.
     *
     * @param newHotel The hotel object to be added.
     */
    public static void addHotel(Hotel newHotel) {
        DatabaseManager.addHotel(newHotel);
    }

    /**
     * Adds a room to the current hotel.
     *
     * @param newRoom The room object to be added.
     */
    public static void addRoomToHotel(Room newRoom) {
        DatabaseManager.getCurrentHotel().addRoom(newRoom);
    }

    /**
     * Adds a room to the specified hotel.
     *
     * @param hotel The hotel object.
     * @param newRoom The room object to be added.
     */
    public static void addRoomToHotel(Hotel hotel, Room newRoom) {
        hotel.addRoom(newRoom);
    }

    /****************************************************************
     *                            Removers                          *
     ****************************************************************/
    /**
     * Removes a hotel from the database by its name.
     *
     * @param searchHotelName The name of the hotel to be removed.
     */
    public static void removeHotel(String searchHotelName) {
        DatabaseManager.removeHotel(searchHotelName);
    }

    /**
     * Removes a hotel from the database by its name.
     *
     */
    public static void removeHotel() {
        String searchHotelName = HotelManager.getHotelName(DatabaseManager.getCurrentHotel());
        DatabaseManager.setCurrentHotel(null);
        DatabaseManager.removeHotel(searchHotelName);
    }

    /**
     * Removes a room from the current hotel by its room number.
     *
     * @param newRoom The room object to be removed.
     */
    public static void removeRoomHotel(Room newRoom) {
        for (Integer reservationID : RoomManager.getAllReservationNumbers(newRoom)){
           ReservationManager.cancelReservation(ReservationManager.getReservation(reservationID));
        }

        //Remove Room from hotel
        DatabaseManager.getCurrentHotel().removeRoom(newRoom.getRoomNumber());

        //Remove Room from Database
        DatabaseConnector.removeItemFromDatabase(newRoom);
    }

    /**
     * Removes a room from the specified hotel by its room number.
     *
     * @param hotel The hotel object.
     * @param newRoom The room object to be removed.
     */
    public static void removeRoomHotel(Hotel hotel, Room newRoom) {
        hotel.removeRoom(newRoom.getRoomNumber());
    }

    /****************************************************************
     *                          Booleans                            *
     ****************************************************************/
    /**
     * Gets the signed-in status.
     *
     * @return True if a user is signed in, otherwise false.
     */
    public static boolean getSignedInStatus() {
        return DatabaseManager.isSignedIn();
    }

    /**
     * Checks if a hotel has a unique name.
     *
     * @param checkName The name to be checked.
     * @return True if the name is unique, otherwise false.
     */
    public static boolean isUniqueName(String checkName) {
        for (Hotel hotel : getAllHotels()) {
            if (hotel.getHotelName().equals(checkName)) {
                return false;
            }
        }
        return true;
    }

    /****************************************************************
     *                          Reservation Data                    *
     ****************************************************************/
    
    /**
     * Retrieves all reservations from the specified hotel.
     *
     * @param hotel The hotel from which to retrieve reservations.
     * @return A list of all reservations in the hotel.
     */
    public static ArrayList<Reservation> getAllReservations(Hotel hotel) {
        return hotel.getAllReservations();
    }

    /**
     * Retrieves all reservations from the specified hotel.
     *
     * @return A list of all reservations in the hotel.
     */
    public static ArrayList<Reservation> getAllReservations() {
        return DatabaseManager.getCurrentHotel().getAllReservations();
    }
    
    /**
     * Adds a reservation to the specified hotel.
     *
     * @param hotel The hotel object.
     * @param reservation The reservation object to be added.
     */
    public static void addReservation(Hotel hotel, Reservation reservation) {
        hotel.addReservation(reservation);
    }

    /**
     * Removes a reservation from the specified hotel.
     *
     * @param hotel The hotel object.
     * @param reservation The reservation object to be removed.
     */
    public static void removeReservation(Hotel hotel, Reservation reservation) {
        hotel.removeReservation(reservation);
    }

    /**
     * Retrieves a specific reservation from the specified hotel by its ID.
     *
     * @param hotel The hotel containing the reservation.
     * @param reservationID The ID of the reservation to retrieve.
     * @return The reservation with the specified ID, or null if not found.
     */
    public static Reservation getReservation(Hotel hotel, int reservationID) {
        return hotel.getReservation(reservationID);
    }

    /****************************************************************
     *                     Print Hotel Info                         *
     ****************************************************************/
    /**
     * Gets the information of all rooms in the current hotel.
     *
     * @return A string containing information about all rooms.
     */
    public static String getAllCurrentHotelRoomsInfo() {
        StringBuilder receipt = new StringBuilder();
        for (Room room : getAllHotelRooms()) {
            try {
                receipt.append(room.getRoomInfo());
            } catch (Exception e) {
                receipt.append("\n***********************\n");
                receipt.append("Room #: null");
                receipt.append("\n");
                receipt.append("Price per Night: null");
                receipt.append("\n");
                receipt.append("Description: null\n");
            }
        }
        return receipt.toString();
    }

    /**
     * Gets the information of all rooms in the given hotel.
     *
     * @return A string containing information about all rooms.
     */
    public static String getAllHotelRoomsInfo(Hotel hotel) {
        StringBuilder receipt = new StringBuilder();
        for (Room room : getAllHotelRooms(hotel)) {
            try {
                receipt.append(room.getRoomInfo());
            } catch (Exception e) {
                receipt.append("\n***********************\n");
                receipt.append("Room #: null");
                receipt.append("\n");
                receipt.append("Price per Night: null");
                receipt.append("\n");
                receipt.append("Description: null\n");
            }
        }
        return receipt.toString();
    }

    /**
     * Gets the information of the current hotel.
     *
     * @return A string containing information about the current hotel.
     */
    public static String getCurrentHotelInfo() {
        return getCurrentHotel().getHotelInfo();
    }

    /**
     * Gets the information of the specified hotel.
     *
     * @param hotel The hotel object.
     * @return A string containing information about the hotel.
     */
    public static String getHotelInfo(Hotel hotel) {
        return hotel.getHotelInfo();
    }

    /**
     * Gets the information of the current hotel and all its rooms.
     *
     * @return A string containing information about the hotel and its rooms.
     */
    public static String getCurrentHotelAndRoomsInfo() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("\n--------------------------------\n");
        receipt.append(getCurrentHotelInfo());
        receipt.append(getAllCurrentHotelRoomsInfo());
        receipt.append("\n--------------------------------\n");
        return receipt.toString();
    }

    /**
     * Gets the information of the passed hotel and all its rooms.
     *
     * @return A string containing information about the hotel and its rooms.
     */
    public static String getHotelAndRoomsInfo(Hotel hotel) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("\n--------------------------------\n");
        receipt.append(getHotelInfo(hotel));
        receipt.append(getAllHotelRoomsInfo(hotel));
        receipt.append("\n--------------------------------\n");
        return receipt.toString();
    }

    /**
     * Prints information about the current hotel.
     */
    public static void printCurrentHotelInfo() {
        System.out.println(getCurrentHotelInfo());
    }

    /**
     * Prints information about the given hotel.
     */
    public static void printHotelInfo(Hotel hotel) {
        System.out.println(getHotelInfo(hotel));
    }

    /****************************************************************
     *                              End                             *
     ****************************************************************/
}//End of HotelManager