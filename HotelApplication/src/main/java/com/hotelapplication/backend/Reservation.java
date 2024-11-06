//Reservation.j

/**************************************
*Reservation Class should include:    *
* Start and End Date of Reservation   *
* Array of Room or Rooms reserved     *
* User ID# associated with reservation*
* Amount for total stay               *
* Print Receipt                       *    
***************************************/

package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

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

	public Reservation(Reservation reservation) {
		this.assignedUser = reservation.assignedUser;
		this.reservationNumber = reservation.reservationNumber;
		this.totalPrice = reservation.totalPrice;
		this.room = reservation.room;
		this.startDate = reservation.startDate;
		this.endDate = reservation.endDate;
	}

/****************************************************************
 *                  		Getters 	                        *
 ****************************************************************/

	//Get Assigned User
	public User getAssignedUser() {
		return assignedUser;
	}

		//Get Reservation Number
	public int getReservationNumber() {
		return reservationNumber;
	}

	//Get Total Price
	public double getTotalPrice() {
		return totalPrice;
	}

	//Get Room
	public Room getRoom() {
		return room;
	}

	//Get Start Date
	public LocalDate getStartDate() {
		return startDate;
	}

	//Get End Date
	public LocalDate getEndDate() {
		return endDate;
	}

	//Get Start Date as Calendar
	public Calendar getStartDateAsCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfYear());
		return calendar;
	}

	//Get End Date as Calendar
	public Calendar getEndDateAsCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfYear());
		return calendar;
	}

/****************************************************************
 *                  		Setters 	                        *
 ****************************************************************/
	//Set Assigned User
	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}

	//Set Reservation Number
	public void setReservationNumber(int reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

	//Set Total Price
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	//Set Room
	public void setRoom(Room room) {
		this.room = room;
	}

	//Set Start Date
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	//Set End Date
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	//Set Start Date from Calendar
	public void setStartDateCalendar(Calendar calendar) {
		this.startDate = LocalDate.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
	}

	//Set End Date from Calendar
	public void setEndDateCalendar(Calendar calendar) {
		this.endDate = LocalDate.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
	}

/****************************************************************
 *                  		Calculations	                    *
 ****************************************************************/
	// Calculate duration of stay
	public long calculateDays(LocalDate startDate, LocalDate endDate) {
		return ChronoUnit.DAYS.between(startDate, endDate) + 1;
	}

	public long calculateNights(LocalDate startDate, LocalDate endDate) {
		return ChronoUnit.DAYS.between(startDate, endDate);
	}


/****************************************************************
 *                  			Print	       		            *
 ****************************************************************/
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
		receipt.append("\n");
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
	 *                      Equals                                  *
	 ****************************************************************/
	
	public boolean isEqualTo(Reservation otherReservation){
		if (reservationNumber == otherReservation.reservationNumber) {
			return true;
		} else {
			return false;
		}
	}

	/****************************************************************
	 *							End					 				*
	 ****************************************************************/
}
