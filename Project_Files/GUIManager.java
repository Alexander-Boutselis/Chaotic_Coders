//GUIManager.java

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;

public class GUIManager{

    // Private constructor to prevent instantiation
    private GUIManager() {}




public static void runAppGUI(Scanner scanner){


}//End of Run App GUI

/****************************************************************
 *                     Run App in Terminal                      *
 ****************************************************************/
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
			/********************************
			 *         Not Signed-in        *
			 ********************************/
			case 1://Not Signed-in
				//Output the current screen
				System.out.println("Current screen: " + screen);
				running = signInScreen(scanner);
			break;
			/********************************
			 *         User Signed-in       *
			 ********************************/
			case 2://Signed in as User
				//Output the current screen
				System.out.println("Current screen: " + screen);
				running = userHomeScreen(scanner);
			break;
			/********************************
			 *       Manager Signed-in      *
			 ********************************/
			case 3://Manager Signed-in
				//Output the current screen
				System.out.println("Current screen: " + screen);
				running = managerHomeScreen(scanner);
			break;
			/********************************
			 *             Exit             *
			 ********************************/
			case 4://Exit Program
			    System.out.println("Exiting the shell.");
			    running = false; // This ends the loop and exits the program.
			break;
			/********************************
			 *           Default            *
			 ********************************/
            default:
                System.out.println("Invalid option, please try again.");
            break;
            }
        }
	}



/****************************************************************
 *                   Sign In/Sign Up Screens                    *
 ****************************************************************/
/********************************
 *     Not Signed-in Screen     *
 ********************************/
	public static boolean signInScreen(Scanner scanner){	
		System.out.println("---------------Welcome---------------");
		System.out.println("1. Sign-in");
		System.out.println("2. Sign-up");
		System.out.println("3. Quit");
		System.out.print("Please enter a number: ");
		
		int choice = scanner.nextInt();
		scanner.nextLine(); 
		
		
		switch(choice){
		case 1:
		//********************************
		//         	  Sign-In            *
		//********************************
			accountSignIn();
		break;
		
		case 2:
		//********************************
		//         Create Account        *
		//********************************
			createAccount();
		break;
		
		case 3:
		//********************************
		//         		Quit	         *
		//********************************
			System.out.println("Exiting...");
			return false;

		default:
			//Invalid choice
			System.out.println("Invalid Selection");
		break;
		}
		return true;
	}//End of signInScreen

//********************************
//         Sign-In Screen        *
//********************************
    public static void accountSignIn(){

    Scanner scanner = new Scanner(System.in);
 
    System.out.println("\n---Sign-in to Account---");

    //Get Username
    System.out.print("Enter Username: ");
    String inputUsername = scanner.nextLine();

     //Get Password
    System.out.print("Enter Password: ");
    String inputPassword = scanner.nextLine();


    for (User user : DatabaseManager.getAllUsers()){
        //System.out.println("Checked User Number: " + user.getUserNumber());
        //System.out.println("Entered Password: " + inputPassword + "\nUser Password: " + user.getPassword());
        if (user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)){
            if (user.getActiveStatus()){

                AccountManager.signIn(user);
                return;
            }
        }
    }
    System.out.println("Username or Password incorrect");

    return;
}//End of accountSignIn

//********************************
//         Sign-up Screen        *
//********************************
	public static void createAccount(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        int option;

        while (running) {
            System.out.println("---Create an Account---");
            System.out.println("1. Create a User Account");
            System.out.println("2. Create a Manager Account");
            System.out.println("3. Exit Account Creator");
            System.out.print("Please enter a number: ");

            option = scanner.nextInt();
            scanner.nextLine(); 


            switch (option) {
                //***********************************************
                //          Create User Account Option
                //***********************************************
                case 1:
                    //Prompt for User info isManager = false
                   accountPrompts(false);
                    running = false;
                    break;

                //***********************************************
                //          Create Manager Account Option
                //***********************************************

                case 2:
                    //Verify Manager Code
                    System.out.print("Please enter the Manager Confirmation Code: ");
                    String inputManagerPassword = scanner.nextLine();

                    if (inputManagerPassword.equals(AccountManager.getManagerPassword())) {
                        //Prompt for Manager info isManager = true
                        accountPrompts(true);
                        running = false;

                    }else{
                        System.out.println("Incorrect Manager Confirmation Code");

                    }
                    break;

                //***********************************************
                //          Exit Account Creation Screen
                //***********************************************
                case 3:
                    running = false;
                    return;

                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
        return;
    }//End of creatAccount

//********************************
//    Account Creation Prompts   *
//********************************
	public static void accountPrompts(boolean isManager){
        Scanner scanner = new Scanner(System.in);

        if (isManager) {
            System.out.println("---Manager Name---");
            
        }else {
            System.out.println("---User Name---");
            
        }
        //Get First name
        System.out.print("Please enter your first name: ");
        String inputFirstName = scanner.nextLine();

        //Get Last name
        System.out.print("Please enter your last name: ");
        String inputLastName = scanner.nextLine();

        //Get Birthday
        System.out.println("---Birthday---");

        //Get Day of birth
        System.out.print("Please enter the DAY of your birth (1-31): ");
        int inputBirthDAY = scanner.nextInt();

        //Get Month of birth
        System.out.print("Please enter the MONTH of your birth (1-12): ");
        int inputBirthMONTH = scanner.nextInt();

        //Get Year of birth
        System.out.print("Please enter the YEAR of your birth(####): ");
        int inputBirthYEAR = scanner.nextInt();
        scanner.nextLine();

        //Combine into Calendar object
        Calendar inputFullBirthday = Calendar.getInstance();
        inputFullBirthday.set(inputBirthYEAR, inputBirthMONTH - 1, inputBirthDAY);

        //Get the current date
        Calendar today = Calendar.getInstance();

        //Calculate the difference in years
        int age = today.get(Calendar.YEAR) - inputFullBirthday.get(Calendar.YEAR);

        //Check if the user hasn't had their birthday this year yet
        if (today.get(Calendar.MONTH) < inputFullBirthday.get(Calendar.MONTH) ||
            (today.get(Calendar.MONTH) == inputFullBirthday.get(Calendar.MONTH) &&
             today.get(Calendar.DAY_OF_MONTH) < inputFullBirthday.get(Calendar.DAY_OF_MONTH))) {
            age--; // Subtract one if the birthday hasn't occurred yet this year
        }

        // Verify if the user is 18 or older
        if (age < 18) {
            System.out.println("You must be 18 years or older to create an account.\nAccount Not Created");
            return; // Exit method if the user is under 18
        }

        boolean isUnique = false;
        String inputUsername = "";
        //Loop until username is unique
        while (isUnique == false){
            //Get Username
            System.out.print("Please enter a Username: ");
            inputUsername = scanner.nextLine();

            if(AccountManager.isUniqueUsername(inputUsername)){
                isUnique = true;
            }else{
            System.out.println("That Username Already Exists");
            System.out.println("Please try a different username");
            }
        }

        //Loop until passwords match
        String inputPassword1;
        String inputPassword2 = "";
        boolean matching = false;

        while(matching == false){

            System.out.println("---Password---");

            //Get Password 1
            System.out.print("Please create your password: ");
            inputPassword1 = scanner.nextLine();

            //Get Password 2
            System.out.print("Please re-enter your password: ");
            inputPassword2 = scanner.nextLine();


            //Confirm passwords match
            if (inputPassword1.equals(inputPassword2)){
                matching = true;
            }else{
                System.out.println("Passwords do not match, try again");
            }

        }
        if (isManager) {
            //Create Manager
            AccountManager.createManager(inputFirstName, inputLastName, inputFullBirthday, inputUsername, inputPassword2);

        }else{
            //Create User
            AccountManager.createUser(inputFirstName, inputLastName, inputFullBirthday, inputUsername, inputPassword2);
        }
        return;
    }//End of accountPrompts


/****************************************************************
 *                   Signed in User Screens                     *
 ****************************************************************/
/********************************
 *       User Home Screen       *
 ********************************/
	public static boolean userHomeScreen(Scanner scanner){
		String screen;
		System.out.println("---------------Welcome " + AccountManager.getFullName(DatabaseManager.getCurrentUser()) + "!---------------");
		if(HotelManager.getCurrentHotel() == null){
			System.out.println("1. Select Hotel");
		}else{
			System.out.println("1. Change Hotel");
		}
		System.out.println("2. Edit My Account");
		System.out.println("3. Make Reservation");
		System.out.println("4. View My Reservations");
		//Notifications
		System.out.println("5. Quit");
		System.out.print("Please enter a number: ");

		int choice = scanner.nextInt();
		scanner.nextLine(); 


		switch(choice){
		    case 1:
			//********************************
			//          Select Hotel         *
			//********************************
				//Select Hotel
		        screen = "Select Hotel Screen";
				System.out.println("Current screen: " + screen);

				selectHotelScreen(scanner);
		        
		        //Print All Hotel Rooms
				//System.out.println(HotelManager.getAllCurrentHotelRoomsInfo());
		        break;

		    case 2:
			//********************************
			//        Edit User Account      *
			//********************************
		        //Edit My Account
		       	screen = "Edit Account Screen";
				System.out.println("Current screen: " + screen);
		        editAccountScreen();
		        break;

		    case 3:
		    //********************************
			//        Make Reservation       *
			//********************************
		        //Make a Reservation
		        screen = "Make Reservation Screen";
				System.out.println("Current screen: " + screen);

		        makeReservationScreen(scanner);
		        break;

		    case 4:
			//********************************
			//     View User Reservations	 *
			//********************************
		        //My Reservations
		        screen = "View Reservations Screen";
				System.out.println("Current screen: " + screen);
		        viewUsersReservationsScreen(scanner);
		        break;

		    case 5:
			//********************************
			//         		Quit	         *
			//********************************
		        System.out.println("Exiting...");
		        return false;
		        
		    default:
		        //Invalid choice
		        System.out.println("Invalid Selection");
		        break;
		}
	return true;
	}//End of userHomeScreen

/********************************
 *      Select Hotel Screen     * Shared
 ********************************/
    public static void selectHotelScreen(Scanner scanner){
        String hotelChoice;
        System.out.println("----Select Hotel----");

        //Print all hotels
        for (Hotel hotel : DatabaseManager.getAllHotels()){
            System.out.println(HotelManager.getHotelInfo(hotel));
        }
                
        System.out.println("Enter Hotel Name");
        
        hotelChoice = scanner.nextLine();

        HotelManager.setCurrentHotel(HotelManager.getHotel(hotelChoice));
    }


//********************************
//        Edit User Account      * Shared
//********************************
    public static void editAccountScreen(){

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        int option;
        String userInput;


        //Add Difference for Managers

        while (running) {

            System.out.println("\n---Edit Account---");
            System.out.println("1. Edit User Info");
            System.out.println("2. Sign-out");
            System.out.println("3. Delete Account");
            System.out.println("4. Cancel");
            System.out.print("Please enter a number: ");

            option = scanner.nextInt();
            scanner.nextLine(); 



            switch (option) {

            //***********************************************
            //          Edit User Info
            //***********************************************
            case 1:
                System.out.println("Edit User Info");
                editUserInfo(scanner);

                break;

            //***********************************************
            //          Sign-Out of User Account
            //***********************************************
            case 2:
                System.out.println("\nAre you sure you want to sign-out?");
                System.out.print("(Yes/No): ");
                userInput = scanner.nextLine().toLowerCase();

                if (userInput.charAt(0)== 'y'){
                    DatabaseManager.signOut();
                    running = false;
                }
                break;

            //***********************************************
            //          Delete User Account
            //***********************************************
            case 3:
            	System.out.println("Delete Account");
                System.out.println("\nAre you sure you want to DELETE your Account?");
                System.out.print("(Yes/No): ");
                userInput = scanner.nextLine().toLowerCase();

                if (userInput.charAt(0)== 'y'){
                    AccountManager.removeAccount(DatabaseManager.getCurrentUser());
                    DatabaseManager.signOut();
                    running = false;
                }
                break;

            //***********************************************
            //          Exit Edit Account Screen
            //***********************************************
            case 4:
                System.out.println("4. Cancel");
                running = false;                    
            default:

                break;
            }
        }
        return;
    }//End of editAccountScreen

//********************************
//        Make Reservation       *
//********************************
	public static void makeReservationScreen(Scanner scanner){
		//Move this method into here
		ReservationManager.viewRoomsAndMakeReservation(HotelManager.getCurrentHotel(), DatabaseManager.getCurrentUser());
	}   //End of makeReservationScreen

//********************************
//    View User Reservations	 *
//********************************
	public static void viewUsersReservationsScreen(Scanner scanner){
		//Check if user has reservations

		//Print all reservations

		//Prompt for user to:
			//Select Reservation
			//Cancel

		//Get User input

		//Call Reservation Receipt Screen for that Reservation
	}//End of viewUserReservationsScreen


/****************************************************************
 *                   Signed in Manager Screens                  *
 ****************************************************************/
/********************************
 *      Manager Home Screen     *
 ********************************/
	public static boolean managerHomeScreen(Scanner scanner){
		String screen;
		System.out.println("---------------Welcome " + AccountManager.getFullName(DatabaseManager.getCurrentUser()) + "!---------------");
		if(HotelManager.getCurrentHotel() == null){
			System.out.println("1. Select Hotel");

		}else{
			System.out.println("1. Change Hotel");
		}
		System.out.println("2. Edit Hotel");
		System.out.println("3. Edit My Account");
		System.out.println("4. View Hotel Rooms");
		System.out.println("5. View Reservations");
		System.out.println("6. Quit");
		System.out.print("Please enter a number: ");

		int choice = scanner.nextInt();
		scanner.nextLine(); 


		switch(choice){
		    case 1:
		    //********************************
			//        	Select Hotel 	     *
			//********************************
		        screen = "Select Hotel Screen";
				System.out.println("Current screen: " + screen);

				selectHotelScreen(scanner);
		        
		        break;

		    case 2:
		    //********************************
			//        	Edit Hotel 		     *
			//********************************
				screen = "Edit Hotel Screen";
				System.out.println("Current screen: " + screen);
				editHotelScreen(scanner);
		        break;
		    case 3:
		    //********************************
			//        	Edit Account	     *
			//********************************
				screen = "Edit My Account";
				System.out.println("Current screen: " + screen);
		        editAccountScreen();
		        break;

		    case 4:
		    //********************************
			//        View Hotel Rooms       *
			//********************************
				screen = "View Hotel Rooms";
				System.out.println("Current screen: " + screen);
				viewHotelReservationsScreen(scanner);
		        break;


		    case 5:
		    //********************************
			//    View Hotel Reservations    *
			//********************************
		        //Hotel Reservations
		        System.out.println("View Hotel Reservations");
		        viewHotelReservationsScreen(scanner);
		        break;

		    case 6:
		    //********************************
			//              Exit             *
			//********************************
		        System.out.println("Exiting...");
		        return false;
		        
		    default:
		    //********************************
			//            Defualt            *
			//********************************
		        //Invalid choice
		        System.out.println("Invalid Selection");
		        break;
		}
		return true;
	}//End of managerHomeScreen

/********************************
 *       Edit Hotel Screen      *
 ********************************/
    public static void editHotelScreen(Scanner scanner){
        boolean running = true;
        int option;

        while (running) {
            System.out.println("----Edit Hotel----");

            //Print hotel
            System.out.println(HotelManager.getCurrentHotelInfo());
            
            System.out.println("Editing: " + HotelManager.getHotelName());
            System.out.println("1. Add Room");
            System.out.println("2. Remove Room");
            System.out.println("3. View Specific Room");
            System.out.println("4. View All Rooms");
            System.out.println("5. Exit Hotel Edditer");

            System.out.print("Please enter a number: ");
            option = scanner.nextInt();
            scanner.nextLine(); 


            switch (option) {
                //***********************************************
                //               Edit Hotel Screen
                //***********************************************
                case 1:
                    //Add a Hotel Room
                    RoomManager.createAHotelRoom(scanner);
                    break;

                case 2:
                    //Remove Hotel Room
                    try{
                        RoomManager.removeAHotelRoom(scanner);
                    }catch(Exception e){

                    }
                    break;

                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    break;
            }
                
        

        }
    }//End of editHotelScreen

//********************************
//    View Hotel Rooms Screen    *
//********************************
	public static void viewHotelRoomsScreen(Scanner scanner){
		//Print All Hotel Rooms
		//Prompt User to:
			//Select Room
				//Go to viewRoomInfo
			//Add Room
			//Remove Room
	}    

/********************************
 *View Hotel Reservations Screen*
 ********************************/
	public static void viewHotelReservationsScreen(Scanner scanner){
		//Print All Reservations for this Hotel
		//Print Amount Earned (Reservation has passed)
		//Print Projected Earnings (Reservations in the future)
		//Print Sum Total Earned+Projected

		//Prompt User to:
			//Select Reservation
				//Go to Reservation Receipt Screen
			//Cancel
	}//End of viewHotelReservationsScreen























//********************************
//    View Hotel Room Screen     * Shared
//********************************
	public static void viewRoomInfoScreen(Scanner scanner){
		//Display Room info

		//Prompt Manager to:
			//Edit Room
			//Cancel
	}

//********************************
//    View Reservation Recipet	 * Shared
//********************************
	public static void viewReceiptScreen(Reservation reservation){
		//Print reservation information
		//Prompt for User to:
			//Edit Slected Reservation
				//Go to Edit Reservation Screen
			//Cancel Reservation
	}//End of viewReceiptScreen

//********************************
//       Edit Reservations       * Shared
//********************************
	public static void editReservationScreen(Scanner scanner){
		//Check if User is Manager or Not

		//Allow all users to:
			//Edit Reservation Dates
			//Edit Room Selection
			//Cancel Reservation
		//Allow Managers to:
			//Edit Reservation Price
			//Edit Room Selection Advanced
	}

//********************************
//        Edit Account Info      * Shared
//********************************
    public static void editUserInfo(Scanner scanner){
        boolean running = true;
        int option;
        String userInput;

        while (running){

            //Prompt user to select what to change
            System.out.println("\n---Edit Account Info---");
            
            //Print current User ifo
            AccountManager.printCurrentUser();

            System.out.println("1. Edit Name");
            System.out.println("2. Change Username");
            System.out.println("3. Change Password");
            System.out.println("4. Cancel");
            System.out.print("Please enter a number: ");

            option = scanner.nextInt();
            scanner.nextLine(); 

            switch(option){
            case 1:
                System.out.println("\n---Edit Name---");
                System.out.println("Please enter your first name: ");
                userInput = scanner.nextLine();
            break;
            
            case 2:

            break;

            case 3:

            break;

            case 4:
                System.out.println("4. Cancel");
                running = false;                    
            
            default:

            break;
            }
        }
    }


	//Calendar
	public static void calendarScreen(Scanner scanner){
		
	}
	//View Rooms
	public static void viewAvailableRoomsScreen(Scanner scanner){
		
	}
	//Confirm Reservation
	public static void confirmationScreen(Scanner scanner, int confirmationType){
		
	}







	//Edit Room 
	public static void editRoomInfoScreen(Scanner scanner){
		
	}
	//Add Room
	public static void addRoomScreen(Scanner scanner){
		
	}
	//Remove Room
	public static void removeRoomScreen(Scanner scanner){
		
	}


//Shared Screens

	//Cancel Reservation
	public static void cancelReservationScreen(Scanner scanner){
		
	}
	//Edit Reservation Info
	public static void editReservationInfoScreen(Scanner scanner){
		//Behave slightly differently based on if current User is of type Manager
	}










}//End of GUI Manager Class