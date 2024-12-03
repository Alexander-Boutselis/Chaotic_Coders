//TestClass.java

package com.hotelapplication.backend;

import com.hotelapplication.frontend.*;
import com.hotelapplication.backend.*;



import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;





public class TestClass{



	@BeforeAll
    public static void setUp() {
        DatabaseConnector.connect();
    }


    @Test
    public void testJUnit(){
    	Calendar birthday = new GregorianCalendar(1, 0, 1);
	        User testUser = new User("Test", "User", birthday, "TestUser", "Password");
        	assertNotNull(testUser, "User object should not be null");
    }


	/********************************
	 *        Run Test Cases        *
	 ********************************/
	/**
	 * Runs a set of test cases that create objects, add them to the database, update the objects, update items in database, and delete objects from database.
	 * 
	 */
	@Test
	public void testCases() {
	    int passedTest = 0;
	    int numOfTests = 1;

	    try {
	        System.out.println("---Running Test Cases---");

	        System.out.println(String.format("---Test #%d: Create User---", numOfTests));
	        Calendar birthday = new GregorianCalendar(1, 0, 1);
	        User testUser = new User("Test", "User", birthday, "TestUser", "Password");
        	assertNotNull(testUser, "User object should not be null");
	        if (testUser != null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

   


	        System.out.println(String.format("---Test #%d: Add User to Database---", numOfTests));
	        DatabaseConnector.addAccount(testUser);
	        if (testUser.getUserID() != null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


	        System.out.println(String.format("---Test #%d: Sign in---", numOfTests));
	        DatabaseManager.signIn(testUser);
	        if (DatabaseManager.isSignedIn()) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


	        System.out.println(String.format("---Test #%d: Sign out---", numOfTests));
	        DatabaseManager.signOut();
	        if (!DatabaseManager.isSignedIn()) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


	        System.out.println(String.format("---Test #%d: Create Manager---", numOfTests));
	        Manager testManager = new Manager(DatabaseManager.nextEmployeeNumber(), "Test", "User", birthday, "TestManager", "Password");
	        if (testManager != null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


	        System.out.println(String.format("---Test #%d: Add Manager to Database---", numOfTests));
	        DatabaseConnector.addAccount(testManager);
	        if (testManager.getUserID() != null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


	        System.out.println(String.format("---Test #%d: Sign in Manager---", numOfTests));
	        DatabaseManager.signIn(testManager);
	        if (DatabaseManager.isSignedIn()) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


	        System.out.println(String.format("---Test #%d: Sign out Manager---", numOfTests));
	        DatabaseManager.signOut();
	        if (!DatabaseManager.isSignedIn()) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


	        System.out.println(String.format("---Test #%d: Create Hotel---", numOfTests));
	        Hotel testHotel = new Hotel("TestHotel", "123 Address St, City, State");
	        if (testHotel != null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


	        System.out.println(String.format("---Test #%d: Add Hotel to Database---", numOfTests));
	        DatabaseConnector.addHotel(testHotel);
	        if (testHotel.getHotelID() != null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


	        System.out.println(String.format("---Test #%d: Set Current Hotel---", numOfTests));
	        DatabaseManager.setCurrentHotel(testHotel);
	        if (DatabaseManager.getCurrentHotel().getHotelID() == testHotel.getHotelID()) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


	        System.out.println(String.format("---Test #%d: Create Room---", numOfTests));
	        int roomNumber = RoomManager.getNextRoomNumber();  // Get next room number
	        double pricePerNight = RoomManager.calcPricePerNight(roomNumber, 2, "queen");  
	        int roomID = HotelManager.getHotelID(HotelManager.getCurrentHotel()) * 1000 + roomNumber;
	        Room testRoom = new Room(roomID, roomNumber, 2, "queen", pricePerNight, "");
	        if (testRoom != null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


	        System.out.println(String.format("---Test #%d: Add Room to Database---", numOfTests));
	        DatabaseConnector.addRoom(testRoom);
	        if (DatabaseConnector.translateRoomFromDatabase(roomID) != null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;



			System.out.println(String.format("---Test #%d: Add Room to Hotel---", numOfTests));
	        HotelManager.addRoomToHotel(testHotel,testRoom);
	        if (HotelManager.getRoom(roomID) != null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


	        System.out.println(String.format("---Test #%d: Create Reservation---", numOfTests));
	        LocalDate startDate = LocalDate.of(2024,12,17);
	        LocalDate endDate = LocalDate.of(2024,12,31);

	        Reservation testReservation = new Reservation(null, testUser.getUserID(), roomID, testHotel.getHotelID(), startDate, endDate, 420.69);
	        if (testReservation != null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

	        System.out.println(String.format("---Test #%d: Add Reservation to Database---", numOfTests));
	        DatabaseConnector.addReservation(testReservation);
	        if (DatabaseConnector.translateReservationFromDatabase(testReservation.getReservationID()) != null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


            DatabaseConnector.printAllTables();


            System.out.println(String.format("---Test #%d: Edit User First Name---", numOfTests));
	        AccountManager.setFirstName(testUser, "User");
	        if (testUser.getFirstName().equals("User")) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Edit User Last Name---", numOfTests));
	        AccountManager.setLastName(testUser, "Test");
	        if (testUser.getLastName().equals("Test")) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Edit Username---", numOfTests));
	        AccountManager.setUsername(testUser, "NewUsername");
	        if (testUser.getUsername().equals("NewUsername")) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Update User in Database---", numOfTests));
            DatabaseConnector.updateUserInDatabase(testUser);
	        if (DatabaseConnector.translateUserFromDatabase(testUser.getUserID()).getUsername().equals("NewUsername")) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Edit Hotel Name---", numOfTests));
            HotelManager.setHotelName(testHotel, "New Hotel Name");
	        if (testHotel.getHotelName().equals("New Hotel Name")) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Edit Hotel Address---", numOfTests));
            HotelManager.setHotelAddress(testHotel, "New Address");
	        if (testHotel.getHotelAddress().equals("New Address")) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Update Hotel in Database---", numOfTests));
            DatabaseConnector.updateHotelInDatabase(testHotel);
	        if (DatabaseConnector.translateHotelFromDatabase(testHotel.getHotelID()).getHotelName().equals("New Hotel Name")) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Edit Room Bed Type---", numOfTests));
            RoomManager.setBedType(testRoom, "twin");
	        if (testRoom.getBedType().equals("twin")) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Edit Number of Beds in Room---", numOfTests));
            RoomManager.setNumberOfBeds(testRoom, 1);
	        if (testRoom.getNumberOfBeds() == 1) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Edit Room Price Per Night---", numOfTests));
            RoomManager.setPricePerNight(testRoom, 130.00);
	        if (testRoom.getPricePerNight() == 130.00) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Edit Room Description---", numOfTests));
            RoomManager.setRoomDescription(testRoom, "Updated Room Description");
	        if (testRoom.getRoomDescription().equals("Updated Room Description")) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Update Room in Database---", numOfTests));
            DatabaseConnector.updateRoomInDatabase(testRoom);
	        if (DatabaseConnector.translateRoomFromDatabase(testRoom.getRoomID()).getRoomDescription().equals("Updated Room Description")) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Edit Reservation Start Date---", numOfTests));
            startDate = LocalDate.of(1111,1,11);
            ReservationManager.setStartDate(testReservation, startDate);
	        if (testReservation.getStartDate().isEqual(startDate)) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Edit Reservation End Date---", numOfTests));
            endDate = LocalDate.of(2222,2,22);
            ReservationManager.setEndDate(testReservation, endDate);
	        if (testReservation.getEndDate().isEqual(endDate)) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Edit Reservation Total Price---", numOfTests));
            ReservationManager.setTotalPrice(testReservation, 123.45);
	        if (testReservation.getTotalPrice() == 123.45) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            //Update Reservation in Database
            System.out.println(String.format("---Test #%d: Update Reservation in Database---", numOfTests));
            DatabaseConnector.updateReservationInDatabase(testReservation);
	        if (DatabaseConnector.translateReservationFromDatabase(testReservation.getReservationID()).getTotalPrice() == 123.45) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


            DatabaseConnector.printAllTables();


            System.out.println(String.format("---Test #%d: Remove Reservation from Database---", numOfTests));
	        DatabaseConnector.removeItemFromDatabase(testReservation);
	        if (DatabaseConnector.translateReservationFromDatabase(testReservation.getReservationID()) == null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

	        System.out.println(String.format("---Test #%d: Remove Room from Database---", numOfTests));
	        DatabaseConnector.removeItemFromDatabase(testRoom);
	        if (DatabaseConnector.translateRoomFromDatabase(testRoom.getRoomID()) == null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

	        System.out.println(String.format("---Test #%d: Remove Hotel from Database---", numOfTests));
	        DatabaseConnector.removeItemFromDatabase(testHotel);
	        if (DatabaseConnector.translateHotelFromDatabase(testHotel.getHotelID()) == null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

	        System.out.println(String.format("---Test #%d: Remove User from Database---", numOfTests));
	        DatabaseConnector.removeItemFromDatabase(testUser);
	        
	        if (DatabaseConnector.translateUserFromDatabase(testUser.getUserID()) == null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

	        System.out.println(String.format("---Test #%d: Remove Manager from Database---", numOfTests));
	        DatabaseConnector.removeItemFromDatabase(testManager);
	        if (DatabaseConnector.translateUserFromDatabase(testManager.getUserID()) == null) {
	            passedTest++;
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }

	    } catch (Exception e) {
	        System.out.println("Test Encountered an error: " + e);

	    } finally {
	    	//DatabaseConnector.emptyDatabase();

	        System.out.println("Number of Tests Run: " + numOfTests);
	        System.out.println("Number of Tests Passed: " + passedTest);
	    }
	}


	
	public static void testPrints(){


	    System.out.println("\n-----Printing Objects in Applicaiton-----");
	    System.out.println("\t---Printing Users---");
	    //Get all Users Objects
	    ArrayList<User> allUsers = DatabaseManager.getAllUsers();

	    //Loop through and print each account
	    for (User user : allUsers){
	    AccountManager.printAccountInfo(user);
	    }

	    System.out.println("-----Printing Objects in Database-----");
	    System.out.println("\t---Printing Users---");
        DatabaseConnector.printUsersTable();


	    System.out.println("-----Printing Objects in Applicaiton-----");
	    System.out.println("\t---Printing Hotels and Rooms---");
	    //Get all Hotel Objects
	    ArrayList<Hotel> allHotels = DatabaseManager.getAllHotels();
	    ArrayList<Reservation> allReservations = new ArrayList<Reservation>();
		//Get all Reservation Objects

	    //Loop through and print each Hotel
	    for (Hotel hotel : allHotels){
	    System.out.println(HotelManager.getHotelAndRoomsInfo(hotel));
	    allReservations.addAll(HotelManager.getAllReservations(hotel));
	    }
	    System.out.println("-----Printing Objects in Database-----");
	    System.out.println("\t---Printing Hotels and Rooms---");
	    DatabaseConnector.printHotelsTable();
        DatabaseConnector.printRoomsTable();

	    System.out.println("-----Printing Objects in Applicaiton-----");
	    System.out.println("\t---Printing Reservations---");

	    //Loop through and print each account
	    for (Reservation reservation : allReservations){
	    ReservationManager.printReservation(reservation);
	    }
	    
	    System.out.println("-----Printing Objects in Database-----");
	    System.out.println("\t---Printing Reservations---");
        DatabaseConnector.printReservationsTable();


	}


/****************************************************************
 *                           End                                *
 ****************************************************************/
}
