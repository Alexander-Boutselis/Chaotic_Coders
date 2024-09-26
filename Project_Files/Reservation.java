//Reservation.j

/**************************************
*Reservation Class should include:    *
* Start and End Date of Reservation   *
* Array of Room or Rooms reserved     *
* User ID# associated with reservation*
* Amount for total stay               *
* Print Receipt                       *    
***************************************/

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
		Room reservedRooms[] = {singleRoom};
		this.reservedRooms = reservedRooms;
	}

	public User getUser() {
		return user;
	}

	// Get + calculation for amount based on room's nightly rate
	private BigDecimal getAmount() {
		return calculateTotalAmount();
	}

	public BigDecimal calculateTotalAmount() {
		BigDecimal total;
		for (Room room : reservedRooms) {
			total = room.getRatePerNight() + total;
		}
		return total;
	}

	// Receipt printing
	public void printReceipt() {
		// Need to implement
	}
}
