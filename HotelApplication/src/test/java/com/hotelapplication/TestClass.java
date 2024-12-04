//TestClass.java

package com.hotelapplication;

import com.hotelapplication.frontend.*;
import com.hotelapplication.backend.*;


import org.jdbi.v3.core.Handle;
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

	public int numOfTests = 1;
	public Handle handle;
	public User testUser;
	public Manager testManager;
	public Reservation testReservation;
	public Room testRoom;
	public Hotel testHotel;
	

	@Test
    public void testConnection() {
    	DatabaseConnector.connect();
	DatabaseConnector.emptyDatabase();
    	handle = DatabaseConnector.getHandle();
    }

    @Test
    public void testUserCreation(){
    	System.out.println(String.format("---Test #%d: Create User---", numOfTests));
	    Calendar birthday = new GregorianCalendar(1, 0, 1);
	    testUser = new User("Test", "User", birthday, "TestUser", "Password");
    	assertNotNull(testUser);
        numOfTests++;
    }

    @Test
    public void testAddUserToDatabase(){
        System.out.println(String.format("---Test #%d: Add User to Database---", numOfTests));
        DatabaseConnector.addAccount(testUser);
        assertNotNull(testUser.getUserID());
    	DatabaseConnector.printUsersTable();
        numOfTests++;	        
    }

    @Test
    public void testUserSignIn(){
    	System.out.println(String.format("---Test #%d: Sign in---", numOfTests));
        DatabaseManager.signIn(testUser);
        assertTrue(DatabaseManager.isSignedIn());
        numOfTests++;
    }

    @Test
    public void testUserSignOut(){
    	System.out.println(String.format("---Test #%d: Sign out---", numOfTests));
        DatabaseManager.signOut();
        assertFalse(DatabaseManager.isSignedIn());
        numOfTests++;
    }

    @Test
    public void testManagerCreation(){
	    System.out.println(String.format("---Test #%d: Create Manager---", numOfTests));
	    Calendar birthday = new GregorianCalendar(1, 0, 1);
	    testManager = new Manager(DatabaseManager.nextEmployeeNumber(), "Test", "Manager", birthday, "TestManager", "Password");
    	assertNotNull(testManager);
	    numOfTests++;

    }

    @Test
    public void testAddManagerToDatabase(){
	    System.out.println(String.format("---Test #%d: Add Manager to Database---", numOfTests));
	    DatabaseConnector.addAccount(testManager);
    	assertNotNull(testManager.getUserID());
    	DatabaseConnector.printUsersTable();
	    numOfTests++;
    }

    @Test
    public void testManagerSignIn(){
	    System.out.println(String.format("---Test #%d: Sign in Manager---", numOfTests));
	    DatabaseManager.signIn(testManager);
        assertTrue(DatabaseManager.isSignedIn());
	    numOfTests++;
    }

    @Test
    public void testManagerSignOut(){
	    System.out.println(String.format("---Test #%d: Sign out Manager---", numOfTests));
	    DatabaseManager.signOut();
        assertFalse(DatabaseManager.isSignedIn());
	    numOfTests++;
    }

	@Test
	public void testEditUserFirstName(){
    	System.out.println(String.format("---Test #%d: Edit User First Name---", numOfTests));
        AccountManager.setFirstName(testUser, "User");
        assertTrue(testUser.getFirstName().equals("User"));
        numOfTests++;
	}

	@Test
	public void testEditUserLastName(){
        System.out.println(String.format("---Test #%d: Edit User Last Name---", numOfTests));
        AccountManager.setLastName(testUser, "Test");
        assertTrue(testUser.getLastName().equals("Test"));
        numOfTests++;
	}

	@Test
	public void testEditUserUesername(){
        System.out.println(String.format("---Test #%d: Edit Username---", numOfTests));
        AccountManager.setUsername(testUser, "NewUsername");
        assertTrue(testUser.getUsername().equals("NewUsername"));
        numOfTests++;
	}

	@Test
	public void testUpdateUserInDatabase(){
        System.out.println(String.format("---Test #%d: Update User in Database---", numOfTests));
        DatabaseConnector.updateUserInDatabase(testUser);
        assertTrue(DatabaseConnector.translateUserFromDatabase(testUser.getUserID()).getUsername().equals("NewUsername"));
    	DatabaseConnector.printUsersTable();
        numOfTests++;
	}


	//Add Hotel Methods Here
	@Test
	public void testHotelCreation() {
		System.out.println(String.format("---Test #%d: Create Hotel---", numOfTests));
		testHotel = new Hotel("TestHotel", "123 Address St, City, State");
		DatabaseManager.setCurrentHotel(testHotel);
		assertNotNull(testHotel, "Failed to create Hotel");
		System.out.println("Passed");
		numOfTests++;
	}
	
	@Test
	public void testAddHotelToDatabase() {
		System.out.println(String.format("---Test #%d: Add Hotel to Database---", numOfTests));
		DatabaseConnector.addHotel(testHotel);
		assertNotNull(testHotel.getHotelID(), "Failed to add Hotel to database");
		DatabaseConnector.printHotelsTable();
		System.out.println("Passed");
		numOfTests++;
	}
	
	@Test
	public void testEditHotelName() {
		System.out.println(String.format("---Test #%d: Edit Hotel Name---", numOfTests));
		HotelManager.setHotelName(testHotel, "New Hotel Name");
		assertEquals("New Hotel Name", testHotel.getHotelName(), "Failed to edit Hotel name");
		System.out.println("Passed");
		numOfTests++;
	}
	
	@Test
	public void testEditHotelAddress() {
		System.out.println(String.format("---Test #%d: Edit Hotel Address---", numOfTests));
		HotelManager.setHotelAddress(testHotel, "New Address");
		assertEquals("New Address", testHotel.getHotelAddress(), "Failed to edit Hotel address");
		System.out.println("Passed");
		numOfTests++;
	}
	
	@Test
	public void testUpdateHotelInDatabase() {
		System.out.println(String.format("---Test #%d: Update Hotel in Database---", numOfTests));
		DatabaseConnector.updateHotelInDatabase(testHotel);
		Hotel updatedHotel = DatabaseConnector.translateHotelFromDatabase(testHotel.getHotelID());
		assertEquals("New Hotel Name", updatedHotel.getHotelName(), "Failed to update Hotel name in database");
		assertEquals("New Address", updatedHotel.getHotelAddress(), "Failed to update Hotel address in database");
		System.out.println("Passed");
		numOfTests++;
	}

	//Add Room Methods Here
	/********************************
	 *        Run Test Cases        *
	 ********************************/
	/**
	 * Runs a set of test cases that create objects, add them to the database, update the objects, update items in database, and delete objects from database.
	 * 
	 */
    
	@Test
	public void testCases() {

	        System.out.println(String.format("---Test #%d: Create Room---", numOfTests));
	        int roomNumber = RoomManager.getNextRoomNumber();  // Get next room number
	        double pricePerNight = RoomManager.calcPricePerNight(roomNumber, 2, "queen");  
	        int roomID = HotelManager.getHotelID(HotelManager.getCurrentHotel()) * 1000 + roomNumber;
	        testRoom = new Room(roomID, roomNumber, 2, "queen", pricePerNight, "");
	        if (testRoom != null) {
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


	        System.out.println(String.format("---Test #%d: Add Room to Database---", numOfTests));
	        DatabaseConnector.addRoom(testRoom);
	        if (DatabaseConnector.translateRoomFromDatabase(roomID) != null) {
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;



			System.out.println(String.format("---Test #%d: Add Room to Hotel---", numOfTests));
	        HotelManager.addRoomToHotel(testHotel,testRoom);
	        if (HotelManager.getRoom(roomID) != null) {
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;


	       

            System.out.println(String.format("---Test #%d: Edit Room Bed Type---", numOfTests));
            RoomManager.setBedType(testRoom, "twin");
	        if (testRoom.getBedType().equals("twin")) {
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Edit Number of Beds in Room---", numOfTests));
            RoomManager.setNumberOfBeds(testRoom, 1);
	        if (testRoom.getNumberOfBeds() == 1) {
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Edit Room Price Per Night---", numOfTests));
            RoomManager.setPricePerNight(testRoom, 130.00);
	        if (testRoom.getPricePerNight() == 130.00) {
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Edit Room Description---", numOfTests));
            RoomManager.setRoomDescription(testRoom, "Updated Room Description");
	        if (testRoom.getRoomDescription().equals("Updated Room Description")) {
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            System.out.println(String.format("---Test #%d: Update Room in Database---", numOfTests));
            DatabaseConnector.updateRoomInDatabase(testRoom);
	        if (DatabaseConnector.translateRoomFromDatabase(testRoom.getRoomID()).getRoomDescription().equals("Updated Room Description")) {
	            System.out.println("Test #" + numOfTests + ": Passed\n");
	        } else {
	            System.out.println("Test #" + numOfTests + ": Failed\n");
	        }
	        numOfTests++;

            
	        
	
	        
	}


	//Add Reservation Methods Here
	@Test
	public void testCreateReservation() {
		System.out.println(String.format("---Test #%d: Create Reservation---", numOfTests));
		LocalDate startDate = LocalDate.of(2024, 12, 17);
		LocalDate endDate = LocalDate.of(2024, 12, 31);
		testReservation = new Reservation(
			testUser.getUserID(), 
			testRoom.getRoomID(), 
			startDate, 
			endDate);
		assertNotNull(testReservation, "Failed");
		System.out.println("Passed");
		numOfTests++;
	}

	@Test
	public void testAddReservationToDatabase() {
		System.out.println(String.format("---Test #%d: Add Reservation to Database---", numOfTests));
		DatabaseConnector.addReservation(testReservation);
    	assertNotNull(DatabaseConnector.translateReservationFromDatabase(testReservation.getReservationID()), "Failed");
    	System.out.println("Passed");
    	numOfTests++;
	}

	@Test
	public void testEditReservationStartDate() {
		System.out.println(String.format("---Test #%d: Edit Reservation Start Date---", numOfTests));
    	LocalDate newStartDate = LocalDate.of(1111, 1, 11);
    	ReservationManager.setStartDate(testReservation, newStartDate);
    	assertEquals(newStartDate, testReservation.getStartDate(), "Failed");
    	System.out.println("Passed");
    	numOfTests++;
	}

	@Test
	public void testEditReservationEndDate() {
		System.out.println(String.format("---Test #%d: Edit Reservation End Date---", numOfTests));
		LocalDate newEndDate = LocalDate.of(2222, 2, 22);
		ReservationManager.setEndDate(testReservation, newEndDate);
		assertEquals(newEndDate, testReservation.getEndDate(), "Failed");
		System.out.println("Passed");
		numOfTests++;
	}

	@Test
	public void testEditReservationTotalPrice() {
		System.out.println(String.format("---Test #%d: Edit Reservation Total Price---", numOfTests));
		double newTotalPrice = 123.45;
		ReservationManager.setTotalPrice(testReservation, newTotalPrice);
		assertEquals(newTotalPrice, testReservation.getTotalPrice(), "Failed");
		System.out.println("Passed");
		numOfTests++;
	}

	@Test
	public void testUpdateReservationInDatabase() {
		System.out.println(String.format("---Test #%d: Update Reservation in Database---", numOfTests));
		double updatedPrice = 150.00;
		ReservationManager.setTotalPrice(testReservation, updatedPrice);
		DatabaseConnector.updateReservationInDatabase(testReservation);
		assertEquals(updatedPrice, 
			DatabaseConnector.translateReservationFromDatabase(testReservation.getReservationID()).getTotalPrice(), 
			"Failed");
		System.out.println("Passed");
		numOfTests++;
	}



	@Test
	public void testPrintDatabaseTables() {
	  	DatabaseConnector.printAllTables();
	}



	//Add Remove Reservation Method Here
	@Test
	public void testRemoveReservationFromDatabase() {
		System.out.println(String.format("---Test #%d: Remove Reservation from Database---", numOfTests));
		DatabaseConnector.removeItemFromDatabase(testReservation);
		assertNull(DatabaseConnector.translateReservationFromDatabase(testReservation.getReservationID()), "Failed");
		System.out.println("Passed");
		numOfTests++;
	}

	//Add Remove Room Method Here
	@Test
	public void testRemoveRoomFromDatabase() {
		System.out.println(String.format("---Test #%d: Remove Room from Database---", numOfTests));
    	DatabaseConnector.removeItemFromDatabase(testRoom);
    	if (DatabaseConnector.translateRoomFromDatabase(testRoom.getRoomID()) == null) {
    	    System.out.println("Test #" + numOfTests + ": Passed\n");
    	} else {
    	    System.out.println("Test #" + numOfTests + ": Failed\n");
    	}
    	numOfTests++;
	}

	//Add Remove Hotel Method Here
	@Test
	public void testRemoveHotelFromDatabase() {
		System.out.println(String.format("---Test #%d: Remove Hotel from Database---", numOfTests));
		DatabaseConnector.removeItemFromDatabase(testHotel);
		assertNull(DatabaseConnector.translateHotelFromDatabase(testHotel.getHotelID()), "Failed to remove Hotel from database");
		DatabaseConnector.printHotelsTable();
		System.out.println("Passed");
		numOfTests++;
	}


	@Test
	public void testRemoveUserfromDatabase(){
        System.out.println(String.format("---Test #%d: Remove User from Database---", numOfTests));
        DatabaseConnector.removeItemFromDatabase(testUser);
        assertNull(DatabaseConnector.translateUserFromDatabase(testUser.getUserID()));
    	DatabaseConnector.printUsersTable();
        numOfTests++;
    }

    @Test
	public void testRemoveManagerfromDatabase(){
        System.out.println(String.format("---Test #%d: Remove Manager from Database---", numOfTests));
        DatabaseConnector.removeItemFromDatabase(testManager);
        assertNull(DatabaseConnector.translateUserFromDatabase(testManager.getUserID()));
    	DatabaseConnector.printUsersTable();
        numOfTests++;
    }


	//@Test
    //public void testEmptyDatabase() {
	//	DatabaseConnector.emptyDatabase();
        //DatabaseConnector.printAllTables();

    //}



	


	
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
