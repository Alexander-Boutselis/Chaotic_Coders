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
import java.time.temporal.ChronoUnit;

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

	public Reservation(LocalDate startDate, LocalDate endDate, Room reservedRoom, User user) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.reservedRooms = new Room[]{reservedRoom};
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
		long nights = ChronoUnit.DAYS.between(startDate, endDate);
		for (Room room : reservedRooms) {
			BigDecimal roomTotal = (BigDecimal.valueOf(nights)).multiply(BigDecimal.valueOf(room.getPricePerNight()));
			total = total.add(roomTotal);
		}
		return total;
	}

	// Make receipt into string
	public String getReceipt() {
		StringBuilder receipt = new StringBuilder();
		receipt.append("HOTEL RESERVATIONS\n");
		receipt.append("--------------------------------\n");
		receipt.append("Your reservation was successsful.\n");
		receipt.append("Name: ");
		receipt.append(user.getName());
		receipt.append("\n");
		receipt.append("User ID: ");
		receipt.append(user.getUserNumber());
		receipt.append("\n");
		receipt.append("Dates: ");
		receipt.append(startDate);
		receipt.append(" - ");
		receipt.append(endDate);
		receipt.append("\n");
		receipt.append("--------------------------------\n");
		receipt.append("Your room(s): \n");
		receipt.append("--------------------------------\n");
		for (Room room : reservedRooms) {
			receipt.append("Room number: ");
			receipt.append(room.getRoomNumber());
			receipt.append("\n");
			receipt.append("Room type: ");
			receipt.append(room.getRoomType());
			receipt.append("\n");
			receipt.append("Room size: ");
			receipt.append(room.getRoomSize());
			receipt.append("\n");
			receipt.append("Nightly rate: ");
			receipt.append(room.getPricePerNight());
			receipt.append("\n");
			receipt.append("--------------------------------\n");
		}
		receipt.append("TOTAL OWED: $");
		receipt.append(getAmount());
		receipt.append("\n");

		return receipt.toString();
	}

	// Receipt printing
	public void printReceipt() {
		System.out.println(getReceipt());
	}
}
