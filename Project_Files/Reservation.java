//Reservation.j

/**************************************
*Reservation Class should include:    *
* Start and End Date of Reservation   *
* Array of Room or Rooms reserved     *
* User ID# associated with reservation*
* Amount for total stay               *
* Print Receipt                       *    
***************************************/

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {

	/*Variables:
		Assigned User
		Reservation Number
		Total Price 
		Room Number Reserved (Can only reserve 1 room per reservation)
		Start Date
		End Date

	*/
	
	/*Functions:
		Get/Set Assigned User
		Get/Set Total Price
		Get/Set Room Number Reserved
		Get/Set Start Date
		Get/Set End Date
		Calculate Duration of Stay
		Print Reservation
	*/

	// Variables
	private User assignedUser;
	private int reservationNumber;
	private double totalPrice;
	private Room room;
	private LocalDate startDate;
	private LocalDate endDate;

	// Constructor
	public Reservation(User assignedUser, int reservationNumber, double totalPrice, Room room, LocalDate startDate, LocalDate endDate) {
		this.assignedUser = assignedUser;
		this.reservationNumber = reservationNumber;
		this.totalPrice = totalPrice;
		this.room = room;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	// Functions
	public User getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}

	public int getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(int reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

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

	// Calculate duration of stay
	public long calculateDays(LocalDate startDate, LocalDate endDate) {
		return ChronoUnit.DAYS.between(startDate, endDate);
	}

	public long calculateNights(LocalDate startDate, LocalDate endDate) {
		return ChronoUnit.DAYS.between(startDate, endDate) - 1;
	}

	// Print reservation

	public String getReceipt() {
		StringBuilder receipt = new StringBuilder();
		receipt.append("HOTEL RESERVATIONS\n");
		receipt.append("--------------------------------\n");
		receipt.append("Your reservation was successsful.\n");
		receipt.append("Name: ");
		receipt.append(assignedUser.getName());
		receipt.append("\n");
		receipt.append("Reservation Number: ");
		receipt.append(reservationNumber);
		receipt.append("\n");
		receipt.append("Username: ");
		receipt.append(assignedUser.getUsername());
		receipt.append("\n");
		receipt.append("Dates: ");
		receipt.append(startDate);
		receipt.append(" - ");
		receipt.append(endDate);
		receipt.append(calculateDays(startDate, endDate));
		receipt.append(" days, ");
		receipt.append(calculateNights(startDate, endDate));
		receipt.append(" nights");
		receipt.append("\n");
		receipt.append("--------------------------------\n");
		receipt.append("Your room: \n");
		receipt.append("--------------------------------\n");
		receipt.append("Room number: ");
		receipt.append(room.getRoomNumber());
		receipt.append("\n");
		receipt.append("Room description: ");
		receipt.append(room.getRoomDescription());
		receipt.append("\n");
		receipt.append("Number of beds: ");
		receipt.append(room.getNumberOfBeds());
		receipt.append("\n");
		receipt.append("Bed type: ");
		receipt.append(room.getBedType());
		receipt.append("\n");
		receipt.append("Nightly rate: ");
		receipt.append(room.getPricePerNight());
		receipt.append("\n");
		receipt.append("--------------------------------\n");
		receipt.append("TOTAL OWED: $");
		receipt.append(totalPrice);
		receipt.append("\n");

		return receipt.toString();
	}

	public void printReservation() {
		System.out.println(getReceipt());
	}

	/****************************************************************
	 *							End					 				*
	 ****************************************************************/
}
