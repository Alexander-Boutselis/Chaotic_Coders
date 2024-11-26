//Reservation.java

package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * The Reservation class represents a single hotel reservation. It includes details such as
 * the assigned user, room ID, hotel ID, start and end dates of the reservation, and total price.
 * This class provides methods to manage and retrieve reservation details, calculate the stay duration,
 * and print a receipt.
 * 
 * @author Ethan Mojahedi
 * 
 */
public class Reservation {

	// Private Fields
	private int reservationID;
	private int assignedUserID;
	private int roomID;
	private int hotelID;
	private LocalDate startDate;
	private LocalDate endDate;
	private double totalPrice;

	/**
     * Constructs a new Reservation object with specified details.
     * 
     * @param reservationID Unique reservation ID.
     * @param assignedUserID User ID associated with the reservation.
     * @param roomID Room ID for the reservation.
     * @param hotelID Hotel ID where the reservation was made.
     * @param startDate Start date of the reservation.
     * @param endDate End date of the reservation.
     * @param totalPrice Total price for the reservation.
     */
	public Reservation(int reservationID, int assignedUserID, int roomID, int hotelID, LocalDate startDate, LocalDate endDate, double totalPrice) {
		this.reservationID = reservationID;
		this.assignedUserID = assignedUserID;
		this.roomID = roomID;
		this.hotelID = hotelID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalPrice = totalPrice;
	}

	/**
     * Constructs a new Reservation object by copying another Reservation.
     * 
     * @param reservation The Reservation object to copy.
     */
	public Reservation(Reservation reservation) {
		this.reservationID = reservation.reservationID;
		this.assignedUserID = reservation.assignedUserID;
		this.roomID = reservation.roomID;
		this.hotelID = reservation.hotelID;
		this.startDate = reservation.startDate;
		this.endDate = reservation.endDate;
		this.totalPrice = reservation.totalPrice;
	}

/****************************************************************
 *                  		Getters 	                        *
 ****************************************************************/

	/**
     * Retrieves the assigned user ID for this reservation.
     * 
     * @return The assigned user ID.
     */
	public int getAssignedUserID() {
		return assignedUserID;
	}

	/**
     * Retrieves the reservation number for this reservation.
     * 
     * @return The reservation number.
     */
	public int getReservationID() {
		return reservationID;
	}

	/**
     * Retrieves the total price for this reservation.
     * 
     * @return The total price.
     */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
     * Retrieves the room ID for this reservation.
     * 
     * @return The room ID.
     */
	public int getRoomID() {
		return roomID;
	}

	/**
     * Retrieves the hotel ID for this reservation.
     * 
     * @return The hotel ID.
     */
	public int getHotelID() {
		return hotelID;
	}

	/**
     * Retrieves the start date for this reservation.
     * 
     * @return The start date.
     */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
     * Retrieves the end date for this reservation.
     * 
     * @return The end date.
     */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
     * Retrieves the start date for this reservation as a calendar.
     * 
     * @return The start date as a calendar.
     */
	public Calendar getStartDateAsCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfYear());
		return calendar;
	}

	/**
     * Retrieves the end date for this reservation as a calendar.
     * 
     * @return The end date as a calendar.
     */
	public Calendar getEndDateAsCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfYear());
		return calendar;
	}

/****************************************************************
 *                  		Setters 	                        *
 ****************************************************************/
	
 	/**
     * Sets the assigned user ID for this reservation.
     * 
     * @param assignedUserID The user ID to assign.
     */
	public void setAssignedUserID(int assignedUserID) {
		this.assignedUserID = assignedUserID;
	}

	/**
     * Sets the reservation number for this reservation.
     * 
     * @param reservationNumber The reservation number to set.
     */
	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	/**
     * Sets the total price for this reservation.
     * 
     * @param totalPrice The total price to set.
     */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
     * Sets the room ID for this reservation.
     * 
     * @param roomID The room ID to set.
     */
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	/**
     * Sets the hotel ID for this reservation.
     * 
     * @param hotelID The hotel ID to set.
     */
	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}

	/**
     * Sets the start date for this reservation.
     * 
     * @param startDate The start date to set.
     */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
     * Sets the end date for this reservation.
     * 
     * @param endDate The end date to set.
     */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
     * Sets the start date for this reservation from a calendar.
     * 
     * @param calendar The start date to set from a calendar.
     */
	public void setStartDateCalendar(Calendar calendar) {
		this.startDate = LocalDate.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
	}

	/**
     * Sets the end date for this reservation from a calendar.
     * 
     * @param calendar The end date to set from a calendar.
     */
	public void setEndDateCalendar(Calendar calendar) {
		this.endDate = LocalDate.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId());
	}

/****************************************************************
 *                  		Calculations	                    *
 ****************************************************************/
	
 	/**
     * Calculates the duration of stay in days (inclusive).
     * 
     * @param startDate The start date of the stay.
     * @param endDate The end date of the stay.
     * @return The total number of days for the stay.
     */
	public long calculateDays(LocalDate startDate, LocalDate endDate) {
		return ChronoUnit.DAYS.between(startDate, endDate) + 1;
	}

	/**
     * Calculates the duration of stay in nights.
     * 
     * @param startDate The start date of the stay.
     * @param endDate The end date of the stay.
     * @return The total number of nights for the stay.
     */
	public long calculateNights(LocalDate startDate, LocalDate endDate) {
		return ChronoUnit.DAYS.between(startDate, endDate);
	}

/****************************************************************
 *                  			Print	       		            *
 ****************************************************************/
	
	/**
     * Generates a receipt for this reservation.
     * 
     * @return A formatted string containing reservation details.
     */
	public String getReceipt() {
		StringBuilder receipt = new StringBuilder();
		receipt.append("HOTEL RESERVATIONS\n");
		receipt.append("--------------------------------\n");
		receipt.append("Your reservation was successsful.\n");
		receipt.append("Name: ");
		receipt.append(DatabaseConnector.translateUserFromDatabase(assignedUserID).getName());
		receipt.append("\n");
		receipt.append("Reservation ID: ");
		receipt.append(reservationID);
		receipt.append("\n");
		receipt.append("Username: ");
		receipt.append(DatabaseConnector.translateUserFromDatabase(assignedUserID).getUsername());
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
		receipt.append(DatabaseConnector.translateRoomFromDatabase(roomID).getRoomNumber());
		receipt.append("\n");
		receipt.append("Room description: ");
		receipt.append(DatabaseConnector.translateRoomFromDatabase(roomID).getRoomDescription());
		receipt.append("\n");
		receipt.append("Number of beds: ");
		receipt.append(DatabaseConnector.translateRoomFromDatabase(roomID).getNumberOfBeds());
		receipt.append("\n");
		receipt.append("Bed type: ");
		receipt.append(DatabaseConnector.translateRoomFromDatabase(roomID).getBedType());
		receipt.append("\n");
		receipt.append("Nightly rate: ");
		receipt.append(DatabaseConnector.translateRoomFromDatabase(roomID).getPricePerNight());
		receipt.append("\n");
		receipt.append("--------------------------------\n");
		receipt.append("TOTAL OWED: $");
		receipt.append(totalPrice);
		receipt.append("\n");

		return receipt.toString();
	}

	/**
     * Prints the reservation details to the console.
     */
	public void printReservation() {
		System.out.println(getReceipt());
	}

	/****************************************************************
	 *                      Equals                                  *
	 ****************************************************************/
	
	/**
     * Compares this reservation to another to check equality by reservation number.
     * 
     * @param otherReservation The Reservation to compare against.
     * @return true if reservation numbers are equal, false otherwise.
     */
	public boolean isEqualTo(Reservation otherReservation){
		if (reservationID == otherReservation.reservationID) {
			return true;
		} else {
			return false;
		}
	}

	/****************************************************************
	 *							End					 				*
	 ****************************************************************/
}
