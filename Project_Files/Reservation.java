//Reservation.j

/**************************************
*Reservation Class should include:    *
* Start and End Date of Reservation   *
* Array of Room or Rooms reserved     *
* User ID# associated with reservation*
* Amount for total stay               *
* Print Receipt                       *    
***************************************/

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reservation {

	// Variables
	private LocalDate startDate;
	private LocalDate endDate;
	private Room[] reservedRooms;
	private User user;

	// Constructor
	public Reservation(LocalDate startDate, LocalDate endDate, Room[] reservedRooms, User user) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.reservedRooms = reservedRooms;
		this.user = user;
	}

	// Get and set methods
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public Room[] getRooms() {
		return reservedRooms;
	}

	public void setRooms(Room[] reservedRooms) {
		this.reservedRooms = reservedRooms;
	}

	public void setRooms(Room singleRoom) {
		this.reservedRooms = new Room[]{singleRoom};
	}

	public User getUser() {
		return user;
	}

	// Get + calculation for amount based on room's nightly rate
	public BigDecimal getAmount() {
		return calculateTotalAmount();
	}

	public BigDecimal calculateTotalAmount() {
		BigDecimal total = BigDecimal.ZERO;
		for (Room room : reservedRooms) {
			total = total.add(BigDecimal.valueOf(room.getPricePerNight()));
		}
		return total;
	}

	// Receipt printing
	public void printReceipt() {
		System.out.println("HOTEL RESERVATIONS");
		System.out.println("--------------------------------%n");
		System.out.println("Your reservation was successsful.");
		System.out.println("Name: " + user.getName());
		System.out.println("User ID: " + user.getUserNumber());
		System.out.println("Dates: " + startDate + " - " + endDate);
		System.out.println("--------------------------------%n");
		System.out.println("Your room(s): ");
		System.out.println("--------------------------------%n");
		for (Room room : reservedRooms) {
			System.out.println("Room number: " + room.getRoomNumber());
			System.out.println("Room type: " + room.getRoomType());
			System.out.println("Room size: " + room.getRoomSize());
			System.out.println("Nightly rate: " + room.getPricePerNight());
			System.out.println("--------------------------------%n");
		}
		System.out.println("TOTAL OWED: " + getAmount());
	}
}
