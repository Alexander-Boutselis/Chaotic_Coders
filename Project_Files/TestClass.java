//TestClass.java



import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;






public class TestClass{


    // Private constructor to prevent instantiation
    private TestClass() {}

	public static void testCases(int choice){

		int numberOfCases = 10;
		int numberOfRooms = 35;
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

	        try{

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
		        AccountManager.createUser("Mr", "Manager", birthday, "Testmanager1", "Password");
		        passedTest++;

		        AccountManager.signIn(AccountManager.getAccount("Testmanager1"));
		        System.out.print("Signed in as: ");
				passedTest++;

		        //Print CurrentUser
	        	AccountManager.printCurrentUser();
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
		//case 3:
	    //    System.out.println("\n---Test Case 3---");
	    //    System.out.println("---3. Passed ---");

		default:

			break;

		}

	}

	public static void runAppInTerminal(Scanner scanner){
		boolean running = true;
        int choice = 1;
        int screenNum = 0;
        String screen = "start";

        while (running) {


        	//If user is not signed in enter screen 1
        	if (!DatabaseManager.isSignedIn()){
                screen = "Welcome, Sign-in / Sign-up Screen";
                screenNum = 1;
            }else if (DatabaseManager.getCurrentUser() instanceof Manager){//If signed in User is of type manager
                screen = "Signed in as Manager Home Screen";
                screenNum = 3;

            }else {//If signed in User is of type User
                screen = "Signed in as User Home Screen";
                screenNum = 2;
            }




            //Simulate what will be on each GUI screen
            switch (screenNum) {         
/****************************************************************
 *                      Welcome Screen                          *
 ****************************************************************/
                case 1://Not Signed-in
            		//Output the current screen
            		System.out.println("Current screen: " + screen);

            		System.out.println("---------------Welcome---------------");
                    System.out.println("1. Sign-in");
                    System.out.println("2. Sign-up");
                    System.out.println("3. Quit");
                    System.out.print("Please enter a number: ");

                    choice = scanner.nextInt();
                    scanner.nextLine(); 


                    switch(choice){
                        case 1:
   					//********************************
    				//         	  Sign-In            *
    				//********************************
                            AccountManager.accountSignIn();
                            break;

                        case 2:
                    //********************************
    				//         Create Account        *
    				//********************************
                            //Account Creation Screen
                            AccountManager.createAccount();
                            break;

                        case 3:
                    //********************************
    				//         		Quit	         *
    				//********************************
                            System.out.println("Exiting...");
                            running = false;
                            break;

                        default:
                            //Invalid choice
                            System.out.println("Invalid Selection");
                            break;
                    }
                    break;


/****************************************************************
 *                      User Home Screen                        *
 ****************************************************************/
                case 2://Signed in as User

                	//Output the current screen
            		System.out.println("Current screen: " + screen);

            		System.out.println("---------------Welcome " + AccountManager.getFullName(DatabaseManager.getCurrentUser()) + "!---------------");
                    System.out.println("1. Select Hotel");
                    System.out.println("2. Make Reservation");
                    System.out.println("3. Edit My Account");
                    System.out.println("4. View My Reservations");
                    //Notificaitons
                    System.out.println("5. Quit");
                    System.out.print("Please enter a number: ");

                    choice = scanner.nextInt();
                    scanner.nextLine(); 


                    switch(choice){
                        case 1:
                    	//********************************
    					//          Select Hotel         *
    					//********************************
    						//Select Hotel
                            screen = "Select Hotel Screen";
            				System.out.println("Current screen: " + screen);

                   			System.out.println("----Select Hotel----");
                   			//Print all hotels

                   			for (Hotel hotel : DatabaseManager.getAllHotels()){
                   				System.out.println(HotelManager.getHotelInfo(hotel));
                   			}
                            
                            //Print All Hotel Rooms
	        				System.out.println(HotelManager.getAllCurrentHotelRoomsInfo());
                            break;

                        case 2:
                        //********************************
    					//        Make Reservation       *
    					//********************************
                            //Make a Reservation
                            System.out.println("Make a Reservation");
                            ReservationManager.viewRoomsAndMakeReservation(HotelManager.getCurrentHotel(), DatabaseManager.getCurrentUser());
                            break;

                        case 3:
                    	//********************************
    					//        Edit User Account      *
    					//********************************
                            //Edit My Account
                            System.out.println("Edit My Account");
                            AccountManager.editUserAccount();
                            break;

                        case 4:
                    	//********************************
    					//     View User Reservations	 *
    					//********************************
                            //My Reservations
                            System.out.println("My Reservations");
                            break;

                        case 5:
                    	//********************************
    					//         		Quit	         *
    					//********************************
                            System.out.println("Exiting...");
                            running = false;
                            break;
                            
                        default:
                            //Invalid choice
                            System.out.println("Invalid Selection");
                            break;
                    }

                    break;


/****************************************************************
 *                    Manager Home Screen        	            *
 ****************************************************************/
                case 3://Manager Signed-in

                	//Output the current screen
            		System.out.println("Current screen: " + screen);

            		System.out.println("---------------Welcome " + AccountManager.getFullName(DatabaseManager.getCurrentUser()) + "!---------------");
                    System.out.println("1. Edit Hotel");
                    System.out.println("2. Edit My Account");
                    System.out.println("3. Edit User Accounts");
                    System.out.println("4. View Reservations");
                    System.out.println("5. Quit");
                    System.out.print("Please enter a number: ");

                    choice = scanner.nextInt();
                    scanner.nextLine(); 


                    switch(choice){
                        case 1:
                            //View Rooms
                            System.out.println(" Edit Hotel");
                            break;

                        case 2:
                            //Edit My Account
                            System.out.println("Edit My Account");
                            AccountManager.editUserAccount();
                            break;

                        case 3:
                            //My Reservations
                            System.out.println("Edit User Accounts");
                            break;


                        case 4:
                            //My Reservations
                            System.out.println("View Reservations");
                            break;

                        case 5:
                            System.out.println("Exiting...");
                            running = false;
                            break;
                            
                        default:
                            //Invalid choice
                            System.out.println("Invalid Selection");
                            break;
                    
                    }
                    //screen = debug();//REMOVE
                    break;


/****************************************************************
 *                       		Exit         	                *
 ****************************************************************/
                case 4://Exit Program
                    System.out.println("Exiting the shell.");
                    running = false; // This ends the loop and exits the program.
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

	}


/****************************************************************
 *                           End                                *
 ****************************************************************/
}
