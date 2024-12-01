//TestClass.java

package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;


import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;






public class TestClass{


    // Private constructor to prevent instantiation
    private TestClass() {}
/*
	public static void testCases(int choice){

		int numberOfCases = 10;
		int numberOfRooms = 5;
		int passedTest;

		switch(choice){
		case 0:
			//Run All Cases
			//System.out.println();
	        System.out.println("---Running All Test Cases---");

			for (int i = 1; i <= numberOfCases; i++){
				testCases(i);
			}
			break;

		case 1:
			passedTest = 0;
	        System.out.println("\n---Test Case 1---");

	        try{
		        //Create Initial Empty Hotel
		        HotelManager.createHotel("Test Hotel", "123 Address St, City, State");
		        passedTest++;

		        //Set it as Current Hotel
		        DatabaseManager.setCurrentHotel(DatabaseManager.getHotel("Test Hotel"));
		        passedTest++;

		        //Loop to Generate Rooms
		        for (int i = 0; i < numberOfRooms; i++){
		        	RoomManager.createRoom(2, "queen","");
		        	RoomManager.createRoom(3, "twin","");
		        	RoomManager.createRoom(1, "suite","");
		        }
		        passedTest++;


				//Print Hotel
				HotelManager.printCurrentHotelInfo();
		        passedTest++;


				//Print All Hotel Rooms
		        System.out.println(HotelManager.getAllCurrentHotelRoomsInfo());
		        passedTest++;



		        

		        System.out.println("---1. Passed ---");

	            } catch (Exception e) {
        			// Handle any exceptions that were thrown
        			System.out.println("Exception caught: " + e.getMessage());
        			System.out.println("Number of passed tries: " + passedTest);
        		}
			break;
		case 2:
			passedTest = 0;
	        System.out.println("\n---Test Case 2---");

	        //try{

		        //Create 2 User accounts
		        Calendar birthday = new GregorianCalendar(2000, Calendar.APRIL, 13);
		        AccountManager.createUser("Greg", "Testaburger", birthday, "Testuser1", "Password");
		        passedTest++;
	
		        birthday = new GregorianCalendar(0, 0, 0);
		        AccountManager.createUser("Tina", "Testaburger", birthday, "Testuser2", "Password");
		        passedTest++;

		        birthday = new GregorianCalendar(2024, 9, 31);
		        AccountManager.createUser("Fail", "ShouldNotSeeThis", birthday, "Testuser1", "Password");
		        passedTest++;


		        //Sign in as Testuser1
		        AccountManager.signIn(AccountManager.getAccount("Testuser1"));
		        System.out.print("Signed in as: ");
		        passedTest++;

		        //Print CurrentUser
				AccountManager.printCurrentUser();
	        	passedTest++;

		        //Sign out
		        AccountManager.signOut();
		        System.out.println("Signed out");

		        passedTest++;

		        //Sign in as Testuser1
		        AccountManager.signIn(AccountManager.getAccount("Testuser1"));
		        System.out.print("Signed in as: ");
		        passedTest++;

		        //Print CurrentUser
	        	AccountManager.printCurrentUser();
	        	passedTest++;

		        //Sign in as Testuser2
				AccountManager.signIn(AccountManager.getAccount("Testuser2"));
		        System.out.print("Signed in as: ");
				passedTest++;

		        //Print CurrentUser
	        	AccountManager.printCurrentUser();
	        	passedTest++;

		        //Sign out
		        AccountManager.signOut();
		        System.out.println("Signed out");
		        passedTest++;
				
				//Create a Manager
				birthday = new GregorianCalendar(2000, 4, 4);
		        AccountManager.createManager("Mr", "Manager", birthday, "Testmanager1", "Password");
		        passedTest++;

		        //Sign in Manager
		        AccountManager.signIn(AccountManager.getAccount("Testmanager1"));
		        System.out.print("Signed in as: ");
				passedTest++;

		        //Print CurrentUser
	        	AccountManager.printCurrentUser();
	        	passedTest++;
	
				//Sign out
		        AccountManager.signOut();
		        System.out.println("Signed out");
		        passedTest++;

		        //Print all Users
		        for(User account : DatabaseManager.getAllUsers()){
		        	AccountManager.printAccountInfo(account);
		        }
		        passedTest++;

				
		        System.out.println("---2. Passed ---");
	    	} catch (Exception e) {
        		// Handle any exceptions that were thrown
        		System.out.println("Exception caught: " + e.getMessage());
        		System.out.println("Number of passed tries: " + passedTest);
        	}

			break;
		case 3:
	    	System.out.println("\n---Test Case 3---");
			passedTest = 0;
			try {
				Hotel testHotel = HotelManager.getHotel("Test Hotel");
				passedTest++;

				//Create 3 standalone reservations
				ReservationManager.createReservation(testHotel, AccountManager.getAccount("Testuser1"), 
					ReservationManager.getNextUnusedNumber(testHotel), 
					ReservationManager.calculateTotalPrice(RoomManager.getHotelRoom(1), 
						ChronoUnit.DAYS.between(LocalDate.of(2024, 12, 11), LocalDate.of(2024, 12, 12))), 
					RoomManager.getHotelRoom(1), 
					LocalDate.of(2024, 12, 11), LocalDate.of(2024, 12, 12));
				passedTest++;

				ReservationManager.createReservation(testHotel, AccountManager.getAccount("Testuser2"), 
					ReservationManager.getNextUnusedNumber(testHotel), 
					ReservationManager.calculateTotalPrice(RoomManager.getHotelRoom(2), 
						ChronoUnit.DAYS.between(LocalDate.of(2024, 12, 14), LocalDate.of(2024, 12, 15))), 
					RoomManager.getHotelRoom(2), 
					LocalDate.of(2024, 12, 14), LocalDate.of(2024, 12, 15));
				passedTest++;

				ReservationManager.createReservation(testHotel, AccountManager.getAccount("Testuser1"), 
					ReservationManager.getNextUnusedNumber(testHotel), 
					ReservationManager.calculateTotalPrice(RoomManager.getHotelRoom(3), 
						ChronoUnit.DAYS.between(LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 17))), 
					RoomManager.getHotelRoom(3), 
					LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 17));
				passedTest++;

				//Test back-to-back reservation for same room
				ReservationManager.createReservation(testHotel, AccountManager.getAccount("Testuser2"), 
					ReservationManager.getNextUnusedNumber(testHotel), 
					ReservationManager.calculateTotalPrice(RoomManager.getHotelRoom(2), 
						ChronoUnit.DAYS.between(LocalDate.of(2024, 12, 18), LocalDate.of(2024, 12, 19))), 
					RoomManager.getHotelRoom(2), 
					LocalDate.of(2024, 12, 18), LocalDate.of(2024, 12, 19));
				passedTest++;

				ReservationManager.createReservation(testHotel, AccountManager.getAccount("Testuser1"), 
					ReservationManager.getNextUnusedNumber(testHotel), 
					ReservationManager.calculateTotalPrice(RoomManager.getHotelRoom(2), 
						ChronoUnit.DAYS.between(LocalDate.of(2024, 12, 19), LocalDate.of(2024, 12, 20))), 
					RoomManager.getHotelRoom(2), 
					LocalDate.of(2024, 12, 19), LocalDate.of(2024, 12, 20));
				passedTest++;

				//Delete 2 reservations
				ReservationManager.cancelReservation(testHotel, AccountManager.getAccount("Testuser2"), testHotel.getReservation(3));
				passedTest++;

				ReservationManager.cancelReservation(testHotel, AccountManager.getAccount("Testuser1"), testHotel.getReservation(4));
				passedTest++;

				//Print all reservations
				for (Reservation reservation : testHotel.getAllReservations()) {
					ReservationManager.printReservation(reservation);
				}
				passedTest++;
			} catch (Exception e) {
				System.out.println("Exception caught: " + e.getMessage());
			}
			System.out.println("Number of passed tries: " + passedTest);
	    	System.out.println("---3. Passed ---");
		default:

			break;

		}

	}
*/

	
	/********************************
	 *        Run Test Cases        *
	 ********************************/
	/**
	 * Runs a set of test cases for various operations.
	 */
	public static void runTestCases() {
	    int passedTest = 0;
	    int numOfTests = 1;

	    try {
	        System.out.println("---Running Test Cases---");

	        System.out.println(String.format("---Test #%d: Create User---", numOfTests));
	        Calendar birthday = new GregorianCalendar(0, 0, 0);
	        User testUser = new User("Test", "User", birthday, "TestUser", "Password");
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


	        








	        System.out.println(String.format("---Test #%d: Remove User from Database---", numOfTests));
	        DatabaseConnector.removeObjectFromDatabase("User", testUser.getUserID());
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
	        System.out.println("\nNumber of Tests Run: " + numOfTests);
	        System.out.println("\nNumber of Tests Passed: " + passedTest);
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
