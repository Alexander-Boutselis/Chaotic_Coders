//ReservationManager.j

/***************************************************
* Allow users to view all rooms                    *
* Show room type, room number, price               *
* Select rooms and view all details                *
* After selecting, allow users to make reservation *
* Prompt for start date, end date, desired room    *
***************************************************/

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
		return newReservation;
	}

	//Assign user to reservation
	public static void assignUser(Hotel hotel, User user, Reservation reservation) {		
		Reservation newReservation = new Reservation(reservation);
		//	hotel.removeReservation(reservation);
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

	public static void viewRoomsAndMakeReservation(Hotel hotel, User user) {
		
		//Get the desired start and end dates + error checking
		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		int looper = 0;
		LocalDate startDate = null;
		LocalDate endDate = null;

		while (looper == 0) {
			System.out.println("Enter the start date of your reservation in the following format: YYYY/MM/DD");
			String startInput = scanner.nextLine();
			if (isValidDate(startInput)) {
				startDate = LocalDate.parse(startInput, formatter);
			} else {
				System.out.println("This is not a valid date, try again.");
				continue;
			}

			System.out.println("Now, enter the end date of your reservation in the following format: YYYY/MM/DD");
			String endInput = scanner.nextLine();
			if (isValidDate(endInput)) {
				endDate = LocalDate.parse(endInput, formatter);
				if (endDate.isBefore(startDate) || endDate.isEqual(startDate)) {
					System.out.println("Your end date cannot be before or on the same day as your start date.");
					System.out.println("Try again from the beginning.");
					continue;
				} else {
					scanner.close();
					looper = 1;
				}
			} else {
				System.out.println("This is not a valid date, try again from the beginning.");
			}
		}

		int looper2 = 0;
		Scanner roomScanner = new Scanner(System.in);
		int reservationSize = 0;
		Room chosenRoom = null;

		while (looper2 == 0) {
			
			//Print the list of rooms matching the date range and desired room size
			//Need to add search parameters later
			System.out.println("Enter the number of beds for your room:");
			reservationSize = roomScanner.nextInt();

			ArrayList<Room> filteredRooms = filterRooms(hotel.getAllRooms(), reservationSize);
			if (filteredRooms.isEmpty()) {
				System.out.println("There are no rooms matching your search. Please try again.");
				continue;
			}
			listRooms(filteredRooms);

			//User selects a room by typing in the room number + error check
			System.out.println("Type in the room number of your desired room: ");
			int roomNumber = scanner.nextInt();
			chosenRoom = findRoomByRoomNumber(filteredRooms, roomNumber);
			if (chosenRoom == null) {
				System.out.println("This room number is not from the listed rooms. Please try again.");
				continue;
			}
			
			//The full selected room details get printed
			System.out.println("FULL ROOM DETAILS");
			System.out.println("--------------------------------");
			chosenRoom.printRoomInfo();
			System.out.println("--------------------------------");
			
			//Are you sure prompt, if answer is no then it will go back to reprint list
			System.out.println("Are you sure you want to reserve this room? Type 1 to continue and any other number if not.");
			int decision = scanner.nextInt();
			if (decision != 1) {
				continue;
			}

			roomScanner.close();
			looper2 = 1;
		}

		long nights = ChronoUnit.DAYS.between(startDate, endDate) - 1;
		double price = calculateTotalPrice(chosenRoom, nights);

		//Room is reserved and set
		Reservation processedReservation = createReservation(hotel, user, getNextUnusedNumber(hotel), price, chosenRoom, startDate, endDate);
		printReservation(processedReservation);
	}

	//Filter array of rooms based on number of beds
	public static ArrayList<Room> filterRooms(ArrayList<Room> rooms, int roomSize) {
		ArrayList<Room> newRoomList = new ArrayList<>();
		for (Room room : rooms) {
			if (room.getNumberOfBeds() >= roomSize) {
				newRoomList.add(room);
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

	/****************************************************************
 	*           		  View Reservations	(User)	   			    *
 	****************************************************************/



	/****************************************************************
 	*           	  View Reservations (Manager)	 	  			*
 	****************************************************************/


	
}
