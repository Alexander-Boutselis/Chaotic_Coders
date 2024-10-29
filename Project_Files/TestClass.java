//TestClass.java



import java.util.Scanner;
import java.util.ArrayList;






public class TestClass{


    // Private constructor to prevent instantiation
    private TestClass() {}

	public static void testCases(int choice){

		int numberOfCases = 10;
		int numberOfRooms = 35;

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
	        System.out.println("\n---Test Case 1---");

			String hotelName = "Test Hotel";
	        //Create Initial Empty Hotel
	        HotelManager.createHotel(hotelName);

	        //Set it as Current Hotel
	        DatabaseManager.setCurrentHotel(DatabaseManager.getHotel(hotelName));

	        //Loop to Generate Rooms
	        for (int i = 0; i < numberOfRooms; i++){
	        	RoomManager.createRoom(2, "queen","");
	        	RoomManager.createRoom(3, "twin","");
	        	RoomManager.createRoom(1, "california king","");
	        }

			//Print Hotel
			HotelManager.printCurrentHotelInfo();

			//Print All Hotel Rooms
	        System.out.println(HotelManager.getAllCurrentHotelRoomsInfo());


	        //System.out.println(HotelManager.getCurrentHotelAndRoomsInfo());

	        System.out.println("---1. Passed ---");
			break;
		case 2:
	    //    System.out.println("\n---Test Case 2---");
	    //    System.out.println("---2. Passed ---");

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

            		System.out.println("---------------Welcome " + AccountManager.getCurrentUserName() + "!---------------");
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
                            //database.reservationManager.viewRoomsMakeReservation(database.hotel.getCurrentUser());
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

            		System.out.println("---------------Welcome " + AccountManager.getCurrentUserName() + "!---------------");
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
