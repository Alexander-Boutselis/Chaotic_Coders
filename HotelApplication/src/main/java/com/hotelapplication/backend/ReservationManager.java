//ReservationManager.java

package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.util.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

/**
 * The ReservationManager class provides static utility methods for managing
 * hotel reservations. This includes creating, modifying, canceling reservations,
 * assigning users, and performing various related calculations.
 *
 * @author Ethan Mojahedi
 * 
 */
public class ReservationManager {

	/**
     * Private constructor to prevent instantiation of the utility class.
     */
	private ReservationManager() {}

	/****************************************************************
 	*                  	Functions 	               		   	 	    *
 	****************************************************************/

	/**
     * Creates a new reservation and updates associated entities such as the hotel,
     * user, room, and the database.
     * 
     * @param hotel The hotel where the reservation is made.
     * @param user The user making the reservation.
     * @param reservationNumber The unique ID for the reservation.
     * @param totalPrice The total price of the reservation.
     * @param room The room being reserved.
     * @param startDate The start date of the reservation.
     * @param endDate The end date of the reservation.
     * @return The created Reservation object.
     */
	public static Reservation createReservation(Hotel hotel, User user, int reservationNumber, double totalPrice, Room room, LocalDate startDate, LocalDate endDate) {
		Reservation newReservation = new Reservation(reservationNumber, AccountManager.getUserID(user), RoomManager.getRoomID(room), HotelManager.getHotelID(hotel), startDate, endDate, totalPrice);
		HotelManager.addReservation(hotel, newReservation);
		AccountManager.addReservationToUser(user, reservationNumber);
		RoomManager.addReservationToRoom(room, reservationNumber);
		DatabaseConnector.addReservation(newReservation);
		return newReservation;
	}

	/**
     * Creates a reservation using an existing Reservation object and updates associated entities.
     * 
     * @param reservation The Reservation object to be added.
     */
	public static void createReservationGivenReservation(Reservation reservation) {
		HotelManager.addReservation(getHotelFromReservation(reservation), reservation);
		User user = DatabaseConnector.translateUserFromDatabase(getAssignedUserID(reservation));
		AccountManager.addReservationToUser(user, reservation.getReservationID());
		RoomManager.addReservationToRoom(getRoom(reservation), reservation.getReservationID());
	}

	/**
     * Cancels a reservation and updates associated entities such as the hotel, user, and room.
     * 
     * @param hotel The hotel from which the reservation is being canceled.
     * @param user The user who made the reservation.
     * @param reservation The reservation to be canceled.
     */
	public static void cancelReservation(Hotel hotel, User user, Reservation reservation) {
		HotelManager.removeReservation(hotel, reservation);
		AccountManager.removeReservationFromUser(user, reservation.getReservationID());
		RoomManager.removeReservationFromRoom(getRoom(reservation), reservation.getReservationID());
	}

	/**
     * Assigns a user to a reservation by creating a new reservation with the updated user
     * and replacing the old reservation in the hotel and user records.
     * 
     * @param hotel The hotel where the reservation exists.
     * @param user The new user to be assigned to the reservation.
     * @param reservation The existing reservation to be updated.
     */
	public static void assignUser(Hotel hotel, User user, Reservation reservation) {		
		Reservation newReservation = new Reservation(reservation);
		HotelManager.removeReservation(hotel, reservation);
		AccountManager.removeReservationFromUser(user, reservation.getReservationID());
		newReservation.setAssignedUserID(AccountManager.getUserID(user));
		HotelManager.addReservation(hotel, newReservation);
		AccountManager.addReservationToUser(user, newReservation.getReservationID());
	}

	/**
     * Retrieves the next unused reservation number for a hotel.
     * 
     * @param hotel The hotel for which to find the next reservation number.
     * @return The next available reservation number.
     */
	public static int getNextUnusedNumber(Hotel hotel) {
		ArrayList<Reservation> reservations = HotelManager.getAllReservations(hotel);
		ArrayList<Integer> reservationNumbers = new ArrayList<>();
		for (Reservation reservation : reservations) {
			reservationNumbers.add(reservation.getReservationID());
		}
		Collections.sort(reservationNumbers);
		int nextNumber = 0;
		for (int number : reservationNumbers) {
			if (nextNumber == number) {
				nextNumber++;
			} else {
				if (number > nextNumber) {
					break;
				}
			}
		}
		return nextNumber;
	}

	/**
     * Validates whether a given date string (format: YYYY/MM/DD) is a valid and future date.
     * 
     * @param date The date string to validate.
     * @return true if the date is valid and in the future; false otherwise.
     */
	public static boolean isValidDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		try {
			LocalDate datePassed = LocalDate.parse(date, formatter);
			if (!datePassed.isAfter(LocalDate.now())) {
				System.out.println("The chosen date must be after today's date.");
				return false;
			}
			return true;
		} catch (DateTimeParseException exc) {
			return false;
		}
	}

	/**
	 * Cleans up expired reservations in the hotel system.
	 * 
	 * This method iterates through all reservations in the hotel and identifies those 
	 * with an end date before the current date. It removes these expired reservations 
	 * from the user's reservation list and the room's reservation list, leaving the 
	 * reservation only stored in the hotel's records for archival purposes.
	 * 
	 * @param hotel The hotel object containing reservations and associated data.
	 */
	public static void cleanupExpiredReservations(Hotel hotel) {
        LocalDate today = LocalDate.now();
		ArrayList<Reservation> allReservations = HotelManager.getAllReservations(hotel);
        Iterator<Reservation> iterator = allReservations.iterator();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (getEndDate(reservation).isBefore(today)) {
				AccountManager.removeReservationFromUser(getAssignedUser(reservation), reservation.getReservationID());
				RoomManager.removeReservationFromRoom(getRoom(reservation), reservation.getReservationID());
            }
        }
    }

	/****************************************************************
 	*                  		Getters 	                   		    *
 	****************************************************************/

	/**
     * Get a reservation from list of reservations.
     * 
     * @param reservationID The ID of the reservation we want.
     * @return The Reservation object.
     */
	public static Reservation getReservation(int reservationID) {
		return DatabaseConnector.translateReservationFromDatabase(reservationID);
	}

	/**
     * Retrieves the reservation ID of a reservation.
     * 
     * @param reservation The reservation to retrieve the ID number from.
     * @return The ID number of the reservation.
     */
	public static int getReservationID(Reservation reservation) {
		return reservation.getReservationID();
	}

	/**
     * Calculates the duration of a reservation in nights.
     * 
     * @param reservation The reservation for which to calculate the duration.
     * @return The duration in nights.
     */
	public static long getDurationInNights(Reservation reservation) {
		return reservation.calculateNights(reservation.getStartDate(), reservation.getEndDate());
	}

	/**
     * Calculates the duration of a reservation in days.
     * 
     * @param reservation The reservation for which to calculate the duration.
     * @return The duration in days.
     */
	public static long getDurationInDays(Reservation reservation) {
		return reservation.calculateDays(reservation.getStartDate(), reservation.getEndDate());
	}

	/**
     * Retrieves the total price of a reservation.
     * 
     * @param reservation The reservation to retrieve the total price from.
     * @return The total price of the reservation.
     */
	public static double getTotalPrice(Reservation reservation) {
		return reservation.getTotalPrice();
	}

	/**
     * Retrieves the room associated with a reservation.
     * 
     * @param reservation The reservation to retrieve the room from.
     * @return The Room object associated with the reservation.
     */
	public static Room getRoom(Reservation reservation) {
		return DatabaseConnector.translateRoomFromDatabase(reservation.getRoomID());
	}

	/**
     * Retrieves the start date of a reservation.
     * 
     * @param reservation The reservation to retrieve the start date from.
     * @return The start date of the reservation.
     */
	public static LocalDate getStartDate(Reservation reservation) {
		return reservation.getStartDate();
	}

	/**
     * Retrieves the end date of a reservation.
     * 
     * @param reservation The reservation to retrieve the end date from.
     * @return The end date of the reservation.
     */
	public static LocalDate getEndDate(Reservation reservation) {
		return reservation.getEndDate();
	}

	/**
     * Retrieves the current hotel.
     * 
     * @return The current hotel.
     */
	public static Hotel getHotel() {
		return DatabaseManager.getCurrentHotel();
	}

	/**
     * Retrieves the user ID assigned to a reservation.
     * 
     * @param reservation The reservation to retrieve the user ID from.
     * @return The user ID of the assigned user.
     */
	public static int getAssignedUserID(Reservation reservation){
		return reservation.getAssignedUserID();
	}

	/**
     * Retrieves the user assigned to a reservation.
     * 
     * @param reservation The reservation to retrieve the user from.
     * @return The User object assigned to the reservation.
     */
	public static User getAssignedUser(Reservation reservation){
		return DatabaseConnector.translateUserFromDatabase(reservation.getAssignedUserID());
	}

	/**
     * Retrieves the hotel object assigned to a reservation.
     * 
     * @param reservation The reservation to retrieve the hotel objec from.
     * @return The hotel object assigned to the reservation.
     */
	public static Hotel getHotelFromReservation(Reservation reservation) {
		Room room = getRoom(reservation);
		int roomID = RoomManager.getRoomID(room);
		int hotelID = roomID / 1000;
		return DatabaseManager.getHotel(hotelID);
	}

	/**
     * Retrieves the hotel object assigned to a reservation.
     * 
     * @param userID The ID of the user with reservations
     * @return List of Reservation IDs
     */
	public static ArrayList<Integer> getReservationsFromUser(int userID) {
		User user = AccountManager.getAccount(userID);
		return AccountManager.getAllreservationNumbers(user);
	}
	
	/****************************************************************
 	*                  		Setters 	                        *
 	****************************************************************/

	/**
     * Updates the total price of a reservation.
     * 
     * @param reservation The reservation to update.
     * @param price The new total price.
     */
	public static void setTotalPrice(Reservation reservation, double price) {
		reservation.setTotalPrice(price);
	}

	/**
     * Updates the room of a reservation.
     * 
     * @param reservation The reservation to update.
     * @param room The new Room object for the reservation.
     */
	public static void setRoom(Reservation reservation, Room room) {
		reservation.setRoomID(room.getRoomID());
	}

	/**
     * Updates the start date of a reservation.
     * 
     * @param reservation The reservation to update.
     * @param date The new start date.
     */
	public static void setStartDate(Reservation reservation, LocalDate date) {
		reservation.setStartDate(date);
	}

	/**
     * Updates the end date of a reservation.
     * 
     * @param reservation The reservation to update.
     * @param date The new end date.
     */
	public static void setEndDate(Reservation reservation, LocalDate date) {
		reservation.setEndDate(date);
	}

	/**
     * Updates the total price of a reservation.
     * 
     * @param reservation The reservation to update.
     * @param reservationID The new reservation ID.
     */
	public static void setReservationID(Reservation reservation, int reservationID) {
		reservation.setReservationID(reservationID);
	}
	

	/****************************************************************
 	*                  		Calculations	                    *
 	****************************************************************/

	/**
     * Calculates the total price of a stay given a room and the number of nights.
     * 
     * @param room The room being reserved.
     * @param nights The number of nights.
     * @return The calculated total price.
     */
	public static double calculateTotalPrice(Room room, long nights) {
		double totalPrice = RoomManager.getPricePerNight(room) * nights;
		return totalPrice;
	}

	/****************************************************************
 	*                  			Print	       		            *
 	****************************************************************/
	
	/**
     * Prints the details of a reservation to the console.
     * 
     * @param reservation The reservation to print.
     */
	public static void printReservation(Reservation reservation) {
		reservation.printReservation();
	}

	/**
     * Prints the details of a reservation to the console.
     * 
     * @param reservationID The reservation to print via its ID.
     */
	public static void printReservation(int reservationID) {
		DatabaseConnector.translateReservationFromDatabase(reservationID).printReservation();
	}

	/****************************************************************
 	*                 View Rooms (Make Reservation)		   	 	    *
 	****************************************************************/

	/**
     * Filters a list of rooms by size and availability.
     * 
     * @param rooms The list of rooms to filter.
     * @param roomSize The desired number of beds.
     * @param hotel The hotel where the rooms are located.
     * @param startDate The start date of the desired reservation.
     * @param endDate The end date of the desired reservation.
     * @return A list of rooms matching the criteria.
     */
	public static ArrayList<Room> filterRooms(ArrayList<Room> rooms, int roomSize, Hotel hotel, LocalDate startDate, LocalDate endDate) {
		ArrayList<Room> newRoomList = new ArrayList<>();
		for (Room room : rooms) {
			if (RoomManager.getNumberOfBeds(room) == roomSize) {
				boolean isAvailable = true;
				for (Reservation reservation : HotelManager.getAllReservations(hotel)) {
					if (DatabaseConnector.translateRoomFromDatabase(reservation.getRoomID()).equals(room)) {
						LocalDate existingStart = reservation.getStartDate();
						LocalDate existingEnd = reservation.getEndDate();

						if (existingStart.isBefore(endDate) && existingEnd.isAfter(startDate)) {
							isAvailable = false;
							break;
						}
 					}
				}
				if (isAvailable == true) {
					newRoomList.add(room);
				}
			}
		}
		return newRoomList;
	}

	/**
     * Lists all room information for a given list of rooms.
     * 
     * @param rooms The list of rooms to display.
     */
	public static void listRooms(ArrayList<Room> rooms) {
		System.out.println("--------------------------------");
		for (Room room : rooms) {
			RoomManager.printRoomInfo(RoomManager.getRoomNumber(room));;
        	System.out.println("--------------------------------");
		}
	}

	/**
     * Finds and returns a room from a list of rooms based on its room number.
     * 
     * @param rooms The list of rooms to search through.
     * @param roomNumber The room number to search for.
     * @return The Room object with the specified room number, or null if not found.
     */
	public static Room findRoomByRoomNumber(ArrayList<Room> rooms, int roomNumber) {
		for (Room room : rooms) {
			if (RoomManager.getRoomNumber(room) == roomNumber) {
				return room;
			}
		}
		return null;
	}
}
