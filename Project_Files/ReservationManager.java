//ReservationManager.j

/***************************************************
* Allow users to view all rooms                    *
* Show room type, room number, price               *
* Select rooms and view all details                *
* After selecting, allow users to make reservation *
* Prompt for start date, end date, desired room    *
***************************************************/

import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ReservationManager {
	private Hotel hotel;
	private Database database;

	public ReservationManager(Hotel hotel) {
		this.hotel = hotel;
	}

	public ReservationManager(Database database) {
		this.database = database;
	}

	// VIEW ROOMS (MAKE RESERVATION) option
	public void viewRoomsMakeReservation(User user) {
		LocalDate startDate = getStartDateInput();
		LocalDate endDate = getEndDateInput(startDate);
		// Need to implement updating parameters logic
		// Need to implement overlapping dates logic
		Reservation newReservation = searchTheRooms(startDate, endDate);
		newReservation.printReceipt();
	}

	private LocalDate getStartDateInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the start date of your reservation in the following format: YYYY/MM/DD");
		String startInput = scanner.nextLine();
		if (isValidDate(startInput)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/mm/dd");
			LocalDate startDate = LocalDate.parse(startInput, formatter);
			scanner.close();
			return startDate;
		} else {
			System.out.println("This is not a valid date, try again.");
			scanner.close();
			return getStartDateInput();
		}
	}

	private LocalDate getEndDateInput(LocalDate startDate) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the end date of your reservation in the following format: YYYY/MM/DD");
		String startInput = scanner.nextLine();
		if (isValidDate(startInput)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/mm/dd");
			LocalDate endDate = LocalDate.parse(startInput, formatter);
			if (endDate.isBefore(startDate) || endDate.isEqual(startDate)) {
				System.out.println("Your end date cannot be before or on the same day as your start date.");
				scanner.close();
				return getEndDateInput(startDate);
			} else {
				scanner.close();
				return endDate;
			}
		} else {
			System.out.println("This is not a valid date, try again.");
			scanner.close();
			return getEndDateInput(startDate);
		}
	}

	private Reservation searchTheRooms(LocalDate startDate, LocalDate endDate) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the amount of people for the reservation: ");
		int roomSize = scanner.nextInt();

		ArrayList<Room> filteredRooms = filterRooms(database.hotel.hotelRooms, roomSize);
		listRooms(filteredRooms);

		System.out.println("Type in the room number of your desired room: ");
		int roomNumber = scanner.nextInt();
		// Need to add error checking for invalid room number
		Room chosenRoom = findRoomByRoomNumber(filteredRooms, roomNumber);

		System.out.println("Are you sure you want to reserve this room? Type 1 to continue and any other number if not.");
		int decision = scanner.nextInt();
		if (decision != 1) {
			scanner.close();
			return searchTheRooms(startDate, endDate);
		}
		scanner.close();
		return new Reservation(startDate, endDate, chosenRoom, database.hotel.getCurrentUser());
	}

	public ArrayList<Room> filterRooms(ArrayList<Room> rooms, int roomSize) {
		ArrayList<Room> newRoomList = new ArrayList<>();
		for (Room room : rooms) {
			if (room.getRoomSize() >= roomSize) {
				newRoomList.add(room);
			}
		}
		return newRoomList;
	}

	public boolean isValidDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/mm/dd");
		try {
			LocalDate.parse(date, formatter);
			return true;
		} catch (DateTimeParseException exc) {
			return false;
		}
	}

	public Room findRoomByRoomNumber(ArrayList<Room> rooms, int roomNumber) {
		for (Room room : rooms) {
			if (room.getRoomNumber() == roomNumber) {
				return room;
			}
		}
		return null;
	}

	// Need to implement
	public void viewReservations(User user) {
		return;
	}

	public void listRooms(Room[] rooms) {
		System.out.println("--------------------------------");
		for (Room room : rooms) {
			System.out.println("Room number: " + room.getRoomNumber());
        	System.out.println("Room type: " + room.getRoomType());
        	System.out.println("Nightly rate: $" + room.getPricePerNight());
        	System.out.println("--------------------------------");
		}
	}

	public void listRooms(ArrayList<Room> rooms) {
		System.out.println("--------------------------------");
		for (Room room : rooms) {
			System.out.println("Room number: " + room.getRoomNumber());
        	System.out.println("Room type: " + room.getRoomType());
        	System.out.println("Nightly rate: $" + room.getPricePerNight());
        	System.out.println("--------------------------------");
		}
	}

	public void displayRoomDetails(Room room) {
		System.out.println("Information for this room:");
		room.displayRoomDetails();
	}

	// Process reservation for multiple rooms
	public void makeReservation(LocalDate startDate, LocalDate endDate, Room[] reservedRooms, User user) {
		for (Room room : reservedRooms) { 
			if (room.isOccupied()) {
				return;
			}
		}
		if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
			return;
		}
		Reservation reservation = new Reservation(startDate, endDate, reservedRooms, user);
		for (Room room : reservedRooms) {
			user.addReservation(Integer.toString(room.getRoomNumber()));
			room.setOccupied(true);
		}
		hotel.addReservation(reservation, reservedRooms.length);
	}

	// Process reservation for single room
	public void makeReservation(LocalDate startDate, LocalDate endDate, Room reservedRoom, User user) {
		if (reservedRoom.isOccupied()) {
			return;
		}
		if (startDate.isAfter(endDate) || startDate.isEqual(endDate)) {
			return;
		}
		Room[] rooms = {reservedRoom};
		Reservation reservation = new Reservation(startDate, endDate, rooms, user);
		for (Room room : rooms) {
			user.addReservation(Integer.toString(room.getRoomNumber()));
			room.setOccupied(true);
		}
		hotel.addReservation(reservation, 1);
	}

	// Change reservation dates
	private void changeReservationDates(Reservation reservation, LocalDate newStartDate, LocalDate newEndDate) {
		return;
	}

	// Change reservation room (?)
	private void changeReservationRoom(Reservation reservation) {
		return;
	}

	// Cancel the reservation
	public void cancelReservation(Reservation reservation) {
		Room[] reservedRooms = reservation.getRooms();
		for (Room room : reservedRooms) {
			room.setOccupied(false);
		}
		hotel.removeReservation(reservation, reservedRooms.length);
	}
}
