//ReservationManager.j

/***************************************************
* Allow users to view all rooms                    *
* Show room type, room number, price               *
* Select rooms and view all details                *
* After selecting, allow users to make reservation *
* Prompt for start date, end date, desired room    *
***************************************************/

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationManager {
	private Hotel hotel;

	public ReservationManager(Hotel hotel) {
		this.hotel = hotel;
	}

	public ReservationManager(Database database) {
		
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
