//Room.java

package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.util.ArrayList;

/**
 * The Room class represents a hotel room with attributes such as room ID,
 * room number, bed type, description, price per night, and reservations.
 * It provides methods to manage room information and reservations.
 *
 * @author Alexander Boutselis
 * 
 */
public class Room {

    // Private Fields
    private Integer roomID;
    private int roomNumber;
    private int numOfBeds;
    private String bedType;
    private String roomDescription;
    private double pricePerNight;
    private ArrayList<Integer> reservationNumbers;

    /**
     * Constructs a new Room object with the given parameters.
     *
     * @param roomID The ID of the room.
     * @param roomNumber The number of the room.
     * @param numOfBeds The number of beds in the room.
     * @param bedType The type of bed in the room.
     * @param pricePerNight The price per night of the room.
     * @param roomDescription The description of the room.
     */
    public Room(int roomID, int roomNumber, int numOfBeds, String bedType, double pricePerNight, String roomDescription) {
        this.roomID = roomID;
        this.roomNumber = roomNumber;
        this.bedType = bedType;
        this.numOfBeds = numOfBeds;
        this.pricePerNight = pricePerNight;
        this.roomDescription = roomDescription;
        reservationNumbers = new ArrayList<>();
    }

    /****************************************************************
     *                      Room Info Methods                       *
     ****************************************************************/
    /**
     * Sets the ID of the room.
     *
     * @param roomID The new ID of the room.
     */
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    /**
     * Gets the ID of the room.
     *
     * @return The ID of the room.
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * Sets the room number.
     *
     * @param roomNumber The new room number.
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Gets the room number.
     *
     * @return The room number.
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the number of beds in the room.
     *
     * @param numOfBeds The number of beds.
     */
    public void setNumberOfBeds(int numOfBeds) {
        this.numOfBeds = numOfBeds;
    }

    /**
     * Gets the number of beds in the room.
     *
     * @return The number of beds.
     */
    public int getNumberOfBeds() {
        return numOfBeds;
    }

    /**
     * Sets the type of bed in the room.
     *
     * @param bedType The new bed type.
     */
    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    /**
     * Gets the type of bed in the room.
     *
     * @return The bed type.
     */
    public String getBedType() {
        return bedType;
    }

    /**
     * Sets the description of the room.
     *
     * @param roomDescription The new description of the room.
     */
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    /**
     * Gets the description of the room.
     *
     * @return The description of the room.
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    /**
     * Sets the price per night for the room.
     *
     * @param pricePerNight The new price per night.
     */
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    /**
     * Gets the price per night for the room.
     *
     * @return The price per night.
     */
    public double getPricePerNight() {
        return pricePerNight;
    }

    /**
     * Gets detailed information about the room in a formatted string.
     *
     * @return A string representing room information.
     */
    public String getRoomInfo() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("\n***********************\n");
        receipt.append("Room #: " + roomNumber);
        receipt.append("\n");
        String capitalizedBedType = bedType.substring(0, 1).toUpperCase() + bedType.substring(1);
        receipt.append(numOfBeds + " " + capitalizedBedType); 
        receipt.append("\n");
        String formatedPricePerNight = String.format("%.2f", pricePerNight);
        receipt.append("Price per Night: $" + formatedPricePerNight);
        receipt.append("\n");
        receipt.append("Description: \n");

        // If no Description generate description
        if (roomDescription.equals("")) {
            receipt.append("This room has " + numOfBeds + " " + capitalizedBedType + " sized bed(s).\n");
            int roomFloor = roomNumber / 100;
            if (roomFloor == 0) {
                receipt.append("It is on the ground floor.\n");
            } else {
                receipt.append("It is on the " + roomFloor + " floor.\n");
            }
            receipt.append("This room costs " + formatedPricePerNight + " per night.");
        } else {
            receipt.append(roomDescription);
        }
        receipt.append("\n***********************\n");
        return receipt.toString();
    }

    /**
     * Prints detailed information about the room.
     */
    public void printRoomInfo() {
        System.out.println(getRoomInfo());
    }

    /****************************************************************
     *                     Reservation Methods                      *
     ****************************************************************/
    /**
     * Gets all reservation numbers for the room.
     *
     * @return A list of all reservation numbers.
     */
    public ArrayList<Integer> getAllreservationNumbers() {
        return reservationNumbers;
    }

    /**
     * Checks if the room has a specific reservation number.
     *
     * @param searchReservationNumber The reservation number to search for.
     * @return True if the room has the reservation number, otherwise false.
     */
    public boolean hasReservationNumber(int searchReservationNumber) {
        for (int reservationNumber : reservationNumbers) {
            if (reservationNumber == searchReservationNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a reservation number to the room.
     *
     * @param newReservationNumber The new reservation number to be added.
     */
    public void addReservationNumber(int newReservationNumber) {
        reservationNumbers.add(newReservationNumber);
    }

    /**
     * Removes a reservation number from the room.
     *
     * @param roomNumberToDelete The reservation number to be removed.
     */
    public void removeReservationNumber(int roomNumberToDelete) {
        for (int i = 0; i < reservationNumbers.size(); i++) {
            if (reservationNumbers.get(i) == roomNumberToDelete) {
                reservationNumbers.remove(i);
                return;
            }
        }
    }

    /****************************************************************
     *                          End                                 *
     ****************************************************************/
} // End of Room Class