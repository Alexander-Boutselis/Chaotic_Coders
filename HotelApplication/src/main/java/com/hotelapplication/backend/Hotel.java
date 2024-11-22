//Hotel.java


package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.util.ArrayList;

/**
 * The Hotel class represents a hotel with attributes like hotel ID, name, rooms,
 * address, and reservations. It provides methods to manage hotel information,
 * rooms, and reservations.
 * 
 * @author Alexander Boutselis
 * 
 */
public class Hotel {

    // Private Fields
    private Integer hotelID;
    private String hotelName;
    private ArrayList<Room> hotelRooms;
    private String address; 
    private ArrayList<Reservation> allReservations;

    /**
     * Constructs a new Hotel object with the given ID, name, and address.
     *
     * @param hotelID The ID of the hotel.
     * @param hotelName The name of the hotel.
     * @param address The address of the hotel.
     */
    public Hotel(int hotelID, String hotelName, String address) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.address = address;
        hotelRooms = new ArrayList<>();
        allReservations = new ArrayList<>();
    }

    /**
     * Constructs a new Hotel object with the given name and address.
     *
     * @param hotelName The name of the hotel.
     * @param address The address of the hotel.
     */
    public Hotel(String hotelName, String address) {
        this.hotelName = hotelName;
        this.address = address;
        hotelRooms = new ArrayList<>();
        allReservations = new ArrayList<>();
    }

    /****************************************************************
     *                      Get/Set Hotel Info                      *
     ****************************************************************/
    /**
     * Gets the name of the hotel.
     *
     * @return The name of the hotel.
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * Gets the ID of the hotel.
     *
     * @return The ID of the hotel.
     */
    public int getHotelID() {
        return hotelID;
    }

    /**
     * Gets the address of the hotel.
     *
     * @return The address of the hotel.
     */
    public String getHotelAddress() {
        return address;
    }

    /**
     * Gets the total number of rooms in the hotel.
     *
     * @return The total number of rooms.
     */
    public int getNumOfRooms() {
        return hotelRooms.size();
    }

    /**
     * Sets the name of the hotel.
     *
     * @param newHotelName The new name of the hotel.
     */
    public void setHotelName(String newHotelName) {
        hotelName = newHotelName;
    }

    /**
     * Sets the ID of the hotel.
     *
     * @param hotelID The new ID of the hotel.
     */
    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    /**
     * Sets the address of the hotel.
     *
     * @param address The new address of the hotel.
     */
    public void setHotelAddress(String address) {
        this.address = address;
    }

    /**
     * Gets a string containing detailed information about the hotel.
     *
     * @return A string representing hotel information.
     */
    public String getHotelInfo() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("\n");
        receipt.append("Hotel ID: " + hotelID);
        receipt.append("\n");
        receipt.append("Hotel: " + hotelName);
        receipt.append("\n");
        receipt.append("Address: " + address);
        receipt.append("\n");
        receipt.append("Total rooms: " + getNumOfRooms()); 
        return receipt.toString();
    }

    /**
     * Prints detailed information about the hotel.
     */
    public void printHotel() {
        System.out.println(getHotelInfo());
    }

    /****************************************************************
     *                        Hotel Room(s)                         *
     ****************************************************************/
    /**
     * Gets a room in the hotel by room number.
     *
     * @param searchRoomNumber The room number to search for.
     * @return The Room object if found, otherwise null.
     */
    public Room getRoom(int searchRoomNumber) {
        for (Room room : hotelRooms) {
            if (room.getRoomNumber() == searchRoomNumber) {
                return room;
            }
        }
        return null; // Failed to find Room with matching Room Number
    }

    /**
     * Gets all rooms in the hotel.
     *
     * @return A list of all rooms in the hotel.
     */
    public ArrayList<Room> getAllRooms() {
        return hotelRooms;
    }

    /**
     * Adds a room to the hotel.
     *
     * @param newRoom The room object to be added.
     */
    public void addRoom(Room newRoom) {
        hotelRooms.add(newRoom);
    }

    /**
     * Removes a room from the hotel by searching for its room number.
     *
     * @param searchRoomNumber The room number of the room to be removed.
     */
    public void removeRoom(int searchRoomNumber) {
        for (Room room : hotelRooms) {
            if (room.getRoomNumber() == searchRoomNumber) {
                hotelRooms.remove(room);
                break;
            }
        }
    }

    /****************************************************************
     *                      Reservation Methods                     *
     ****************************************************************/
    /**
     * Gets a reservation based on the reservation number.
     *
     * @param searchReservationNumber The reservation number to search for.
     * @return The Reservation object if found, otherwise null.
     */
    public Reservation getReservation(int searchReservationNumber) {
        for (Reservation reservation : allReservations) {
            if (reservation.getReservationNumber() == searchReservationNumber) {
                return reservation;
            }
        }
        return null; // No Reservation found
    }

    /**
     * Gets all reservations in the hotel.
     *
     * @return A list of all reservations in the hotel.
     */
    public ArrayList<Reservation> getAllReservations() {
        return allReservations;
    }

    /**
     * Adds a reservation to the hotel.
     *
     * @param reservation The reservation object to be added.
     */
    public void addReservation(Reservation reservation) {
        allReservations.add(reservation);
    }

    /**
     * Removes a reservation from the hotel by matching with another reservation.
     *
     * @param reservation The reservation object to be removed.
     */
    public void removeReservation(Reservation reservation) {
        for (int i = 0; i < allReservations.size(); i++) {
            if (allReservations.get(i).isEqualTo(reservation)) {
                allReservations.remove(i);
                break; // Stop after removing the first match
            }
        }
    }

    /****************************************************************
     *                       Boolean Methods                        *
     ****************************************************************/
    /**
     * Checks if this hotel is equal to another hotel by comparing names.
     *
     * @param otherHotel The other hotel to compare.
     * @return True if the hotels have matching names, otherwise false.
     */
    public boolean isEqualTo(Hotel otherHotel) {
        return hotelName.equals(otherHotel.getHotelName());
    }

    /****************************************************************
     *                               End                             *
     ****************************************************************/
} // End of Hotel Class