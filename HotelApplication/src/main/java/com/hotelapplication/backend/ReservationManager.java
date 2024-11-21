//ReservationManager.j

/***************************************************
* Allow users to view all rooms                    *
* Show room type, room number, price               *
* Select rooms and view all details                *
* After selecting, allow users to make reservation *
* Prompt for start date, end date, desired room    *
***************************************************/

package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.util.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

/****************************************************************
*                  	Reservation Manager 	                    *
****************************************************************/

public class ReservationManager {

	/*Functions: (All Methods must be Static)
		Create Reservation
		Assign User
		Get Duration
		Calc Total Price
		Get/Set Total Price
		Get/Set Room Number
		Get/Set Room Number Reserved
		Get/Set Start Date
		Get/Set End Date
		isEqualTo()
		Get next unused reservation number
		Print Reservation
	*/

	private ReservationManager() {}

	/****************************************************************
 	*                  	Functions 	               		   	 	    *
 	****************************************************************/

	//Create reservation
	public static Reservation createReservation(Hotel hotel, User user, int reservationNumber, double totalPrice, Room room, LocalDate startDate, LocalDate endDate) {
		Reservation newReservation = new Reservation(user, reservationNumber, totalPrice, room, startDate, endDate);
		hotel.addReservation(newReservation);
		user.addReservation(reservationNumber);
		room.addReservationNumber(reservationNumber);
		DatabaseConnector.addReservation(newReservation);
		return newReservation;
	}

	//Create reservation given reservation object
	public static void createReservationGivenReservation(Reservation reservation) {
		getHotelFromReservation(reservation).addReservation(reservation);
		User user = getAssignedUser(reservation);
		user.addReservation(reservation.getReservationNumber());
		getRoom(reservation).addReservationNumber(reservation.getReservationNumber());
	}

	//Cancel reservation
	public static void cancelReservation(Hotel hotel, User user, Reservation reservation) {
		hotel.removeReservation(reservation);
		user.removeReservation(reservation.getReservationNumber());
		getRoom(reservation).removeReservationNumber(reservation.getReservationNumber());
	}

	//Assign user to reservation
	public static void assignUser(Hotel hotel, User user, Reservation reservation) {		
		Reservation newReservation = new Reservation(reservation);
		hotel.removeReservation(reservation);
		user.removeReservation(reservation.getReservationNumber());
		newReservation.setAssignedUser(user);
		hotel.addReservation(newReservation);
		user.addReservation(newReservation.getReservationNumber());
	}

	//Get next unused reservation number in hotel
	public static int getNextUnusedNumber(Hotel hotel) {
		ArrayList<Reservation> reservations = hotel.getAllReservations();
		ArrayList<Integer> reservationNumbers = new ArrayList<>();
		for (Reservation reservation : reservations) {
			reservationNumbers.add(reservation.getReservationNumber());
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

	//Check if a string YYYY/MM/DD is a valid date
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

	/****************************************************************
 	*                  		Getters 	                        *
 	****************************************************************/

	//Get duration (nights) given reservation
	public static long getDurationInNights(Reservation reservation) {
	return reservation.calculateNights(reservation.getStartDate(), reservation.getEndDate());
	}

	//Get duration (days) given reservation
	public static long getDurationInDays(Reservation reservation) {
		return reservation.calculateDays(reservation.getStartDate(), reservation.getEndDate());
	}

	//Get total price
	public static double getTotalPrice(Reservation reservation) {
		return reservation.getTotalPrice();
	}

	//Get room
	public static Room getRoom(Reservation reservation) {
		return reservation.getRoom();
	}

	//Get start date
	public static LocalDate getStartDate(Reservation reservation) {
		return reservation.getStartDate();
	}

	//Get end date
	public static LocalDate getEndDate(Reservation reservation) {
		return reservation.getEndDate();
	}

	//Get hotel
	public static Hotel getHotel() {
		return DatabaseManager.getCurrentHotel();
	}

	//Get the Assigned User
	public static User getAssignedUser(Reservation reservation){
		return reservation.getAssignedUser();
	}

	//Get the hotel for a reservation
	public static Hotel getHotelFromReservation(Reservation reservation) {
		ArrayList<Hotel> allHotels = HotelManager.getAllHotels();
		for (Hotel hotel : allHotels) {
			ArrayList<Room> rooms = HotelManager.getAllHotelRooms(hotel);
			if (rooms.contains(getRoom(reservation))) {
				return hotel;
			}
		}
		return null;
	}
	
	/****************************************************************
 	*                  		Setters 	                        *
 	****************************************************************/

	//Set total price
	public static void setTotalPrice(Reservation reservation, double price) {
		reservation.setTotalPrice(price);
	}

	//Set room
	public static void setRoom(Reservation reservation, Room room) {
		reservation.setRoom(room);
	}

	//Set start date
	public static void setStartDate(Reservation reservation, LocalDate date) {
		reservation.setStartDate(date);
	}

	//Set end date
	public static void setEndDate(Reservation reservation, LocalDate date) {
		reservation.setEndDate(date);
	}

	/****************************************************************
 	*                  		Calculations	                    *
 	****************************************************************/

	//Calculate total price
	public static double calculateTotalPrice(Room room, long nights) {
		double totalPrice = room.getPricePerNight() * nights;
		return totalPrice;
	}

	/****************************************************************
 	*                  			Print	       		            *
 	****************************************************************/
	
	//Print reservation
	public static void printReservation(Reservation reservation) {
		reservation.printReservation();
	}

	/****************************************************************
 	*                 View Rooms (Make Reservation)		   	 	    *
 	****************************************************************/

	//Filter array of rooms based on number of beds
	public static ArrayList<Room> filterRooms(ArrayList<Room> rooms, int roomSize, Hotel hotel, LocalDate startDate, LocalDate endDate) {
		ArrayList<Room> newRoomList = new ArrayList<>();
		for (Room room : rooms) {
			if (room.getNumberOfBeds() == roomSize) {
				boolean isAvailable = true;
				for (Reservation reservation : hotel.getAllReservations()) {
					if (reservation.getRoom().equals(room)) {
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

	//List all room information given array of rooms
	public static void listRooms(ArrayList<Room> rooms) {
		System.out.println("--------------------------------");
		for (Room room : rooms) {
			room.printRoomInfo();
        	System.out.println("--------------------------------");
		}
	}

	//Get room from array of rooms given room number
	public static Room findRoomByRoomNumber(ArrayList<Room> rooms, int roomNumber) {
		for (Room room : rooms) {
			if (room.getRoomNumber() == roomNumber) {
				return room;
			}
		}
		return null;
	}
}
