//TestClass.java

package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;


import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;






public class TestClass{


    // Private constructor to prevent instantiation
    private TestClass() {}

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

				String hotelName = "Test Hotel";
		        
		        //Create Initial Empty Hotel
		        HotelManager.createHotel(hotelName);
		        passedTest++;

		        //Set it as Current Hotel
		        DatabaseManager.setCurrentHotel(DatabaseManager.getHotel(hotelName));
		        passedTest++;

		        //Loop to Generate Rooms
		        for (int i = 0; i < numberOfRooms; i++){
		        	RoomManager.createRoom(2, "queen","");
		        	RoomManager.createRoom(3, "twin","");
		        	RoomManager.createRoom(1, "california king","");
		        }
		        passedTest++;


				//Print Hotel
				HotelManager.printCurrentHotelInfo();
		        passedTest++;


				//Print All Hotel Rooms
		        System.out.println(HotelManager.getAllCurrentHotelRoomsInfo());
		        passedTest++;



		        //System.out.println(HotelManager.getCurrentHotelAndRoomsInfo());
		        //passedTest++;


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
	    	/*} catch (Exception e) {
        		// Handle any exceptions that were thrown
        		System.out.println("Exception caught: " + e.getMessage());
        		System.out.println("Number of passed tries: " + passedTest);
        	}*/

			break;
		//case 3:
	    //    System.out.println("\n---Test Case 3---");
	    //    System.out.println("---3. Passed ---");

		default:

			break;

		}

	}

	



/****************************************************************
 *                           End                                *
 ****************************************************************/
}
