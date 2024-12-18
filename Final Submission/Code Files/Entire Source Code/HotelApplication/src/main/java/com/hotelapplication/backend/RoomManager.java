//RoomManager.java


package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * The RoomManager class provides methods to create, manage, and remove rooms
 * in a hotel. It interacts with the Room and HotelManager classes for room management
 * tasks such as setting room attributes, calculating prices, and managing reservations.
 * 
 * @author Alexander Boutselis
 * 
 */
public class RoomManager {

    // Private constructor to prevent instantiation
    private RoomManager() {}

    /****************************************************************
     *                  Create/Destroy Room                         *
     ****************************************************************/
    /**
     * Creates a new room with the specified attributes.
     *
     * @param numOfBeds The number of beds in the room.
     * @param bedType The type of bed in the room.
     * @param description A description of the room.
     */
    public static Room createRoom(int numOfBeds, String bedType, String description) {
        String roomDescription = description;
        int roomNumber = getNextRoomNumber();  // Get next room number
        double pricePerNight = calcPricePerNight(roomNumber, numOfBeds, bedType);  // Calculate price per night
        int roomID = HotelManager.getHotelID(HotelManager.getCurrentHotel()) * 1000 + roomNumber;

        // If no Description generate description
        if (roomDescription.equals("")) {
            roomDescription = generateRoomDescription(numOfBeds, bedType, roomNumber, pricePerNight);
        } 

        // Create Room
        Room newRoom = new Room(roomID, roomNumber, numOfBeds, bedType, pricePerNight, roomDescription);

        // Add Room to Current Hotel
        HotelManager.addRoomToHotel(newRoom);
        DatabaseConnector.addRoom(newRoom);
        return newRoom;
    }

    public static String generateRoomDescription(int numOfBeds, String bedType, int roomNumber, double pricePerNight){

        StringBuilder receipt = new StringBuilder();
        String capitalizedBedType = bedType.substring(0, 1).toUpperCase() + bedType.substring(1);
        String formatedPricePerNight = String.format("%.2f", pricePerNight);

        receipt.append("This room has " + numOfBeds + " " + capitalizedBedType + " sized bed(s).\n");
        int roomFloor = roomNumber / 100;
        if (roomFloor == 0) {
            receipt.append("It is on the ground floor.\n");
        } else {
            receipt.append("It is on floor " + roomFloor + ".\n");
        }
        receipt.append("This room costs " + formatedPricePerNight + " per night.");
        return receipt.toString();
    
    }


    /**
     * Removes a room from the current hotel.
     *
     * @param roomNumber The number of the room to be removed.
     */
    public static void removeRoom(int roomNumber) {
        Room roomToRemove = getHotelRoom(roomNumber);
        int indexOfRoom = HotelManager.getAllHotelRooms().indexOf(roomToRemove);
        HotelManager.getAllHotelRooms().set(indexOfRoom, null);
    }

    /****************************************************************
     *               Add/Remove Reservation To Room                 *
     ****************************************************************/

    /**
     * Adds a reservation ID to the specified room.
     *
     * @param room          The room to update.
     * @param reservationID The reservation ID to add.
     */
    public static void addReservationToRoom(Room room, int reservationID) {
        room.addReservationNumber(reservationID);
    }

    /**
     * Removes a reservation ID from the specified room.
     *
     * @param room          The room to update.
     * @param reservationID The reservation ID to remove.
     */
    public static void removeReservationFromRoom(Room room, int reservationID) {
        room.removeReservationNumber(reservationID);
    }

    /****************************************************************
     *                          Getters                             *
     ****************************************************************/
    /**
     * Gets the room number of the specified room.
     *
     * @param room The room object.
     * @return The room number.
     */
    public static int getRoomNumber(Room room) {
        return room.getRoomNumber();
    }

    /**
     * Gets the next available room number for a new room.
     *
     * @return The next available room number.
     */
    public static int getNextRoomNumber() {
        int nextRoomNumber = 1;
        for (Room room : HotelManager.getAllHotelRooms()) {
            int roomNumber = getRoomNumber(room);
            if (roomNumber <= nextRoomNumber) {
                nextRoomNumber = roomNumber + 1;
            }
        }
        return nextRoomNumber;
    }

    /**
     * Gets the next available room number for a new room.
     *
     * @param hotel
     * @return The next available room number.
     */
    public static int getNextRoomNumber(Hotel hotel) {
        int nextRoomNumber = 1;
        for (Room room : HotelManager.getAllHotelRooms(hotel)) {
            int roomNumber = getRoomNumber(room);
            if (roomNumber <= nextRoomNumber) {
                nextRoomNumber = roomNumber + 1;
            }
        }
        return nextRoomNumber;
    }

    /**
     * Gets the bed type of the specified room.
     *
     * @param room The room object.
     * @return The bed type.
     */
    public static String getBedType(Room room) {
        return room.getBedType();
    }

    /**
     * Gets the number of beds in the specified room.
     *
     * @param room The room object.
     * @return The number of beds.
     */
    public static int getNumberOfBeds(Room room) {
        return room.getNumberOfBeds();
    }

    /**
     * Gets the price per night for the specified room.
     *
     * @param room The room object.
     * @return The price per night.
     */
    public static double getPricePerNight(Room room) {
        return room.getPricePerNight();
    }

    /**
     * Gets the description of the specified room.
     *
     * @param room The room object.
     * @return The room description.
     */
    public static String getRoomDescription(Room room) {
        return room.getRoomDescription();
    }

    /**
     * Gets a room from the current hotel by its room number.
     *
     * @param searchRoomNumber The number of the room to search for.
     * @return The Room object if found, otherwise null.
     */
    public static Room getHotelRoom(int searchRoomNumber) {
        for (Room room : HotelManager.getAllHotelRooms()) {
            if (room.getRoomNumber() == searchRoomNumber) {
                return room;
            }
        }
        return null;
    }

    /**
     * Gets all reservation numbers for the specified room.
     *
     * @param room The room object.
     * @return A list of all reservation numbers.
     */
    public static ArrayList<Integer> getAllReservationNumbers(Room room) {
        return room.getAllreservationNumbers();
    }

    /**
     * Gets the ID of the specified room.
     *
     * @param room The room object.
     * @return The room ID.
     */
    public static int getRoomID(Room room) {
        return room.getRoomID();
    }

    /****************************************************************
     *                          Setters                             *
     ****************************************************************/
    /**
     * Sets the room number for the specified room.
     *
     * @param room The room object.
     * @param roomNumber The new room number.
     */
    public static void setRoomNumber(Room room, int roomNumber) {
        room.setRoomNumber(roomNumber);
    }

    /**
     * Sets the bed type for the specified room.
     *
     * @param room The room object.
     * @param bedType The new bed type.
     */
    public static void setBedType(Room room, String bedType) {
        room.setBedType(bedType.toLowerCase());
    }

    /**
     * Sets the number of beds in the specified room.
     *
     * @param room The room object.
     * @param numOfBeds The new number of beds.
     */
    public static void setNumberOfBeds(Room room, int numOfBeds) {
        room.setNumberOfBeds(numOfBeds);
    }

    /**
     * Sets the price per night for the specified room manually.
     *
     * @param room The room object.
     * @param pricePerNight The new price per night.
     */
    public static void setPricePerNight(Room room, double pricePerNight) {
        room.setPricePerNight(pricePerNight);
    }

    /**
     * Sets the price per night for the specified room by calculating it.
     *
     * @param room The room object.
     */
    public static void setPricePerNight(Room room) {
        int roomNumber = room.getRoomNumber();
        String bedType = room.getBedType();
        int numOfBeds = room.getNumberOfBeds();
        room.setPricePerNight(calcPricePerNight(roomNumber, numOfBeds, bedType));
    }

    /**
     * Sets the description for the specified room.
     *
     * @param room The room object.
     * @param roomDescription The new room description.
     */
    public static void setRoomDescription(Room room, String roomDescription) {
        room.setRoomDescription(roomDescription);
    }

    /****************************************************************
     *                      Calculate Price                         *
     ****************************************************************/
    /**
     * Calculates the price per night for a room based on its attributes.
     *
     * @param roomNumber The room number.
     * @param numOfBeds The number of beds in the room.
     * @param bedType The type of bed in the room.
     * @return The calculated price per night.
     */
    public static double calcPricePerNight(int roomNumber, int numOfBeds, String bedType) {
        double basePrice;

        // Set base price based on bed type
        switch (bedType) {
            case "twin":
                basePrice = 60;
                break;
            case "full":
                basePrice = 80;
                break;
            case "queen":
                basePrice = 100;
                break;
            case "king":
                basePrice = 150;
                break;
            case "suite":
                basePrice = 200;
                break;
            default:
                basePrice = 666;  // Invalid bed type fallback
                break;
        }

        // Add additional price based on number of beds and room floor
        double bedAddition = numOfBeds * 20;
        double roomAddition = (roomNumber / 100) * 15.0;

        // Calculate final price per night
        return basePrice + bedAddition + roomAddition;
    }

    /****************************************************************
     *                         Prompts                              *
     ****************************************************************/
    /**
     * Prompts the user to enter information to create a new hotel room.
     *
     * @param scanner The Scanner object for user input.
     */
    public static void createAHotelRoom(Scanner scanner) {
        String bedType = "";
        int numOfBeds;
        String roomDescription;

        System.out.println("\n---Create A Room---");
        System.out.println("What type of Bed will this room have?");
        System.out.println("1. Twin");
        System.out.println("2. Full");
        System.out.println("3. Queen");
        System.out.println("4. King");
        System.out.println("5. Suite");
        System.out.println("6. Cancel");
        System.out.print("\nSelect Type of Bed: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        // Set bed type based on user choice
        switch (choice) {
            case 1:
                bedType = "twin";
                break;
            case 2:
                bedType = "full";
                break;
            case 3:
                bedType = "queen";
                break;
            case 4:
                bedType = "king";
                break;
            case 5:
                bedType = "suite";
                break;
            case 6:
                return;
            default:
                break;
        }

        System.out.println("\nHow many beds will there be?");
        System.out.print("Number of Beds: ");
        numOfBeds = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nEnter Room Description: ");
        roomDescription = scanner.nextLine();

        createRoom(numOfBeds, bedType, roomDescription);
    }

    /**
     * Prompts the user to remove a hotel room.
     *
     * @param scanner The Scanner object for user input.
     */
    public static void removeAHotelRoom(Scanner scanner) {
        System.out.println(HotelManager.getAllCurrentHotelRoomsInfo());
        System.out.println("Which Room would you like to remove?");
        System.out.print("Room Number: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();
        DatabaseConnector.removeItemFromDatabase(getHotelRoom(roomNumber));
        removeRoom(roomNumber);
    }

    /****************************************************************
     *                           Print                              *
     ****************************************************************/
    /**
     * Gets the information of a room by its number.
     *
     * @param roomNumber The room number.
     * @return A string containing information about the room.
     */
    public static String getRoomInfo(int roomNumber) {
        return HotelManager.getCurrentHotel().getRoom(roomNumber).getRoomInfo();
    }

    /**
     * Gets the information of the specified room.
     *
     * @param room The room object.
     * @return A string containing information about the room.
     */
    public static String getRoomInfo(Room room) {
        return room.getRoomInfo();
    }

    /**
     * Prints information about a room by its number.
     *
     * @param roomNumber The room number.
     */
    public static void printRoomInfo(int roomNumber) {
        System.out.println(getRoomInfo(roomNumber));
    }

    /****************************************************************
     *                           End                                *
     ****************************************************************/
}//End of RoomManager Class