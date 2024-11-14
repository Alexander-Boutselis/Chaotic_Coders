//GUIManager.java

package com.hotelapplication.frontend;
import com.hotelapplication.backend.*;

import java.util.Scanner;

import javax.swing.SwingUtilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.time.format.*;
import java.time.temporal.ChronoUnit;
import java.time.*;

public class GUIManager{

    // Private constructor to prevent instantiation
    private GUIManager() {}

// Authentication method that returns a User object if login is successful
public static String authenticateAndGetRole(String username, String password) {
    User user = DatabaseManager.getUser(username);
    if (user != null && user.getPassword().equals(password)) {
        if (user instanceof Manager) {
            return "Manager";
        } else {
            return "User";
        }
    }
    return null;  // Return null if no user is found or password does not match
}


/****************************************************************
 *                     Run App in GUI                           *
 ****************************************************************/
// Method to run the GUI, showing login screen
public static void runAppGUI() {
    Login loginWindow = new Login();
    loginWindow.setVisible(true);

    //Keeps terminal open
    Scanner scanner = new Scanner(System.in);
    int choice = scanner.nextInt();
        scanner.nextLine(); 
}
//End of Run App GUI

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
        //            Sign-In            *
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
        //              Quit             *
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
            //     View User Reservations    *
            //********************************
                //My Reservations
                screen = "View Reservations Screen";
                System.out.println("Current screen: " + screen);
                viewUsersReservationsScreen(scanner);
                break;

            case 5:
            //********************************
            //              Quit             *
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

        if(hotelChoice.equals("null")){
            HotelManager.setCurrentHotel(null);
        }else {
            for (Hotel hotel : DatabaseManager.getAllHotels()){
                if (hotelChoice.equals(HotelManager.getHotelName(hotel)))
                    HotelManager.setCurrentHotel(hotel);
            }
        }
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

        //Get the desired start and end dates + error checking
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		int looper = 0;
		LocalDate startDate = null;
		LocalDate endDate = null;

		while (looper == 0) {
			System.out.println("Enter the start date of your reservation in the following format: YYYY/MM/DD");
			String startInput = scanner.nextLine();
			if (ReservationManager.isValidDate(startInput)) {
				startDate = LocalDate.parse(startInput, formatter);
			} else {
				System.out.println("This is not a valid date, try again.");
				continue;
			}

			System.out.println("Now, enter the end date of your reservation in the following format: YYYY/MM/DD");
			String endInput = scanner.nextLine();
			if (ReservationManager.isValidDate(endInput)) {
				endDate = LocalDate.parse(endInput, formatter);
				if (endDate.isBefore(startDate) || endDate.isEqual(startDate)) {
					System.out.println("Your end date cannot be before or on the same day as your start date.");
					System.out.println("Try again from the beginning.");
					continue;
				} else {
					looper = 1;
				}
			} else {
				System.out.println("This is not a valid date, try again from the beginning.");
			}
		}

		int looper2 = 0;
		int reservationSize = 0;
		Room chosenRoom = null;

		while (looper2 == 0) {
			
			//Print the list of rooms matching the date range and desired room size
			//Need to add search parameters later
			System.out.println("Enter the number of beds for your room:");
			reservationSize = scanner.nextInt();

			ArrayList<Room> filteredRooms = ReservationManager.filterRooms(DatabaseManager.getCurrentHotel().getAllRooms(), reservationSize);
			if (filteredRooms.isEmpty()) {
				System.out.println("There are no rooms matching your search. Please try again.");
				continue;
			}
			ReservationManager.listRooms(filteredRooms);

			//User selects a room by typing in the room number + error check
			System.out.println("Type in the room number of your desired room: ");
			int roomNumber = scanner.nextInt();
			chosenRoom = ReservationManager.findRoomByRoomNumber(filteredRooms, roomNumber);
			if (chosenRoom == null) {
				System.out.println("This room number is not from the listed rooms. Please try again.");
				continue;
			}
			
			//The full selected room details get printed
			System.out.println("FULL ROOM DETAILS");
			System.out.println("--------------------------------");
			chosenRoom.printRoomInfo();
			System.out.println("--------------------------------");
			
			//Are you sure prompt, if answer is no then it will go back to reprint list
			System.out.println("Are you sure you want to reserve this room? Type 1 to continue and any other number if not.");
			int decision = scanner.nextInt();
			if (decision != 1) {
				continue;
			}

			looper2 = 1;
		}

		long nights = ChronoUnit.DAYS.between(startDate, endDate);
		double price = ReservationManager.calculateTotalPrice(chosenRoom, nights);

		//Room is reserved and set
		Reservation processedReservation = ReservationManager.createReservation(DatabaseManager.getCurrentHotel(), DatabaseManager.getCurrentUser(), ReservationManager.getNextUnusedNumber(DatabaseManager.getCurrentHotel()), price, chosenRoom, startDate, endDate);
		System.out.println();
        ReservationManager.printReservation(processedReservation);
    }   //End of makeReservationScreen

//********************************
//    View User Reservations     *
//********************************
    public static void viewUsersReservationsScreen(Scanner scanner){
        
        if (DatabaseManager.getCurrentUser().getAllreservationNumbers().isEmpty()) {
			System.out.println("You have no current reservations.");
			return;
		}

		ArrayList<Integer> allUserNumbers = DatabaseManager.getCurrentUser().getAllreservationNumbers();
		for (int i : allUserNumbers) {
			System.out.println(i);
		}

		System.out.println();
		System.out.println("These are all of your current reservation numbers.");
		System.out.println("To view a reservation, type the number. Otherwise, type CANCEL to go back.");

		String reservationNumber = scanner.nextLine();

		if (reservationNumber.equalsIgnoreCase("CANCEL")) {
			return;
		}

        viewReceiptScreen(DatabaseManager.getCurrentHotel().getReservation(Integer.valueOf(reservationNumber)), scanner);
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
        //System.out.println("4. View Hotel Rooms");
        System.out.println("4. View Reservations");
        System.out.println("5. Quit");
        System.out.print("Please enter a number: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); 


        switch(choice){
            case 1:
            //********************************
            //          Select Hotel         *
            //********************************
                screen = "Select Hotel Screen";
                System.out.println("Current screen: " + screen);

                selectHotelScreen(scanner);
                
                break;

            case 2:
            //********************************
            //          Edit Hotel           *
            //********************************
                screen = "Edit Hotel Screen";
                System.out.println("Current screen: " + screen);
                if (DatabaseManager.getCurrentHotel() != null) {
                    editHotelScreen(scanner);
                }else{
                    System.out.println("No Hotel Selected");
                }
                break;

            case 3:
            //********************************
            //          Edit Account         *
            //********************************
                screen = "Edit My Account";
                System.out.println("Current screen: " + screen);
                editAccountScreen();
                break;

            case 4:
            //********************************
            //    View Hotel Reservations    *
            //********************************
                //Hotel Reservations
                System.out.println("View Hotel Reservations");
                viewHotelReservationsScreen(scanner);
                break;

            case 5:
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
            //System.out.println("3. View Specific Room");
            System.out.println("3. View All Rooms");
            System.out.println("4. Exit Hotel Edditer");

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
                        System.out.println("Hotel Room Not Removed");
                    }
                    break;

                case 3:
                    viewHotelRoomsScreen(scanner);

                    break;
                case 4:
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
        //Print All Hotel Rooms for Current Hotel
        for (Room room : HotelManager.getAllCurrentHotelRooms()){
            System.out.println(RoomManager.getRoomInfo(room));
        }

        //Prompt User to select a Room by Room Number
        System.out.print("Please Select a Room Number: ");
        int userInputRoomNumber = scanner.nextInt();
        scanner.nextLine(); 

        for (Room room : HotelManager.getAllCurrentHotelRooms()){
            if(RoomManager.getRoomNumber(room) == userInputRoomNumber){
                System.out.println(RoomManager.getRoomInfo(room));
                
                System.out.print("Edit Hotel Room?(Yes/No): ");
                String userInput = scanner.nextLine().toLowerCase();
                if (userInput.charAt(0)== 'y'){
                    editRoomInfoScreen(scanner);
                }else{
                    return;
                }
            }
        }
    }    



//********************************
//    View Hotel Room Screen     * Shared
//********************************
    public static void editRoomInfoScreen(Scanner scanner){
        //Display Room info

        //Prompt Manager to:
            //Edit Room
            //Cancel
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
//    View Reservation Recipet   * Shared
//********************************
    public static void viewReceiptScreen(Reservation reservation, Scanner scanner){

        ReservationManager.printReservation(reservation);

        System.out.println();
		System.out.println("Do you want to modify this reservation? If so, type anything.");
		System.out.println("Otherwise, type CANCEL to go back.");

        String responseOne = scanner.nextLine();

		if (responseOne.equalsIgnoreCase("CANCEL")) {
			return;
		}

        System.out.println();
		System.out.println("Select an option.");
        System.out.println("OPTION 1: Edit Reservation");
        System.out.println("OPTION 2: Cancel Reservation");
        System.out.println("OPTION 3: Go Back");
        System.out.print("Type your selection: ");

        int responseTwo = scanner.nextInt();

        switch (responseTwo) {
            case 1:
                editReservationScreen(reservation, scanner);
            case 2: 
                cancelReservationScreen(reservation, scanner);
            case 3:
                return;
            default:
                return;
        }
        //Prompt for User to:
            //Edit Slected Reservation
                //Go to Edit Reservation Screen
            //Cancel Reservation
    }//End of viewReceiptScreen

//********************************
//       Edit Reservations       * Shared
//********************************
    public static void editReservationScreen(Reservation reservation, Scanner scanner){
        //Check if User is Manager or Not

        //Allow all users to:
            //Edit Reservation Dates
            //Edit Room Selection
            //Cancel Reservation
        //Allow Managers to:
            //Edit Reservation Price
            //Edit Room Selection Advanced
        
        System.out.println("Select an option.");
        System.out.println("OPTION 1: Edit Reservation Dates");
        System.out.println("OPTION 2: Edit Room Selection");
        System.out.println("OPTION 3: Cancel Reservation");
        System.out.println("OPTION 4: Go Back");

        if (DatabaseManager.getCurrentUser() instanceof Manager) {
            System.out.println("OPTION 5: Edit Reservation Price");
            System.out.println("OPTION 6: Edit Room Selection Advanced");
        }

        System.out.print("Type your selection: ");

        if (scanner.hasNextInt()) {
            scanner.nextInt();
        }

        int response = scanner.nextInt();

        switch (response) {
            case 1: 
                editReservationInfoScreen(reservation, scanner, 1);
            case 2:
                editReservationInfoScreen(reservation, scanner, 2);
            case 3:
                cancelReservationScreen(reservation, scanner);
            case 4: 
                viewReceiptScreen(reservation, scanner);
            case 5: 
                if (DatabaseManager.getCurrentUser() instanceof Manager) {
                    editReservationInfoScreen(reservation, scanner, 5);
                }
            case 6:
                if (DatabaseManager.getCurrentUser() instanceof Manager) {
                    editReservationInfoScreen(reservation, scanner, 6);
                }
        }
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
                //Try(){
                AccountManager.setFirstName(DatabaseManager.getCurrentUser(), userInput);

                System.out.println("Please enter your last name: ");
                userInput = scanner.nextLine();
                AccountManager.setLastName(DatabaseManager.getCurrentUser(), userInput);
                //}catch(Exception e){
                AccountManager.printCurrentUser();
                //}
            break;
            
            case 2:
                System.out.println("\n---Edit Username---");
                System.out.println("\nPlease enter new Username: ");
                userInput = scanner.nextLine();

                if(AccountManager.isUniqueUsername(userInput)){
                    AccountManager.setUsername(DatabaseManager.getCurrentUser(), userInput);
                    System.out.println("Username set");
                }else{
                    System.out.println("Username not original! Username not set!");
                }

            break;

            case 3:
                System.out.println("\n---Edit Password---");
                String oldPassword;
                String newPassword;
                boolean passwordCheck = true;
                while(passwordCheck){
                    System.out.println("\nPlease enter old Password: ");
                    userInput = scanner.nextLine();

                    if(AccountManager.getPassword(DatabaseManager.getCurrentUser()).equals(userInput)){
                        oldPassword = userInput;
                        System.out.println("\nPlease enter New Password: ");
                        newPassword = scanner.nextLine();
                        System.out.println("\nPlease re-enter New Password: ");
                        userInput = scanner.nextLine();
                        if(userInput.equals(newPassword) && !newPassword.equals(oldPassword)){
                            AccountManager.setPassword(DatabaseManager.getCurrentUser(), newPassword);
                            passwordCheck = false;
                        }else{
                            System.out.println("New Password cannot be Old Password!");
                        }
                    }else{
                    System.out.println("Old Password Incorrect");
                    }
                }
                

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

    //Add Room
    public static void addRoomScreen(Scanner scanner){
        
    }
    //Remove Room
    public static void removeRoomScreen(Scanner scanner){
        
    }


//Shared Screens

    //Cancel Reservation
    public static void cancelReservationScreen(Reservation reservation, Scanner scanner){
        
        double price = ReservationManager.getTotalPrice(reservation);

        System.out.println("Are you sure you want to proceed with canceling this reservation?");
        System.out.println("Type NO to go back. Otherwise, type anything to continue.");

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("NO")) {
            return;
        } else {
            ReservationManager.cancelReservation(DatabaseManager.getCurrentHotel(), DatabaseManager.getCurrentUser(), reservation);
        }

        System.out.println("Your reservation has been cancelled successfully.");
        System.out.println("You will be refunded $" + price + ".");
        System.out.println("If this was done in error, please rebook your stay immediately.");

        if (DatabaseManager.getCurrentUser() instanceof Manager) {
            managerHomeScreen(scanner);
        } else {
            userHomeScreen(scanner);
        }
    }
    //Edit Reservation Info
    public static void editReservationInfoScreen(Reservation reservation, Scanner scanner, int selection){
        //Behave slightly differently based on if current User is of type Manager

        // Need to implement
        switch (selection) {
            // Edit Reservation Dates
            case 1:
                System.out.println("These are the current dates for this reservation.");
                System.out.println(reservation.getStartDate() + " - " + reservation.getEndDate());

                int looper = 0;
                LocalDate startDate = null;
                LocalDate endDate = null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd");

                while (looper == 0) {
                    System.out.println("Type CANCEL to go back. Otherwise, type the new start date for the reservation.");
                    System.out.println("The format should be: YYYY/MM/DD");
                    String startInput = scanner.nextLine().trim();

                    if (startInput.equalsIgnoreCase("CANCEL")) {
                        return;
                    }

                    if (ReservationManager.isValidDate(startInput)) {
                        startDate = LocalDate.parse(startInput, formatter);
                    } else {
                        System.out.println("This is not a valid date, try again.");
                        continue;
                    }
        
                    System.out.println("Now, enter the new end date of the reservation in the following format: YYYY/MM/DD");
                    String endInput = scanner.nextLine().trim();
                    if (ReservationManager.isValidDate(endInput)) {
                        endDate = LocalDate.parse(endInput, formatter);
                        if (endDate.isBefore(startDate) || endDate.isEqual(startDate)) {
                            System.out.println("The end date cannot be before or on the same day as the start date.");
                            System.out.println("Try again from the beginning.");
                            continue;
                        } else {
                            looper = 1;
                        }
                    } else {
                        System.out.println("This is not a valid date, try again from the beginning.");
                    }
                }

                // Check room availability for selected date (NEED TO ADD)

                System.out.println("Are you sure you want to change to these dates for this reservation?");
                System.out.println(startDate + " - " + endDate);

                System.out.println("Type 1 to confirm, otherwise type any number to go back to the main menu.");

                int decision = scanner.nextInt();
                scanner.nextLine();

                if (decision == 1) {
                    ReservationManager.cancelReservation(DatabaseManager.getCurrentHotel(), reservation.getAssignedUser(), reservation);
                    ReservationManager.createReservation(DatabaseManager.getCurrentHotel(), reservation.getAssignedUser(), ReservationManager.getNextUnusedNumber(DatabaseManager.getCurrentHotel()),reservation.getTotalPrice(), reservation.getRoom(), startDate, endDate);
                    System.out.println("The reservation dates have been changed successfully.");
                    userHomeScreen(scanner);
                } else {
                    if (DatabaseManager.getCurrentUser() instanceof Manager) {
                        managerHomeScreen(scanner);
                    } else {
                        userHomeScreen(scanner);
                    }
                }
                break;
            // Edit Reservation Room
            case 2:
                System.out.println("This is the current room for this reservation.");
                RoomManager.printRoomInfo(RoomManager.getRoomNumber(reservation.getRoom()));

                int looper2 = 0;
                int reservationSize = 0;
                Room chosenRoom = null;
        
                while (looper2 == 0) {
                    System.out.println("Type CANCEL to go back. Otherwise, type anything to continue.");
                    String input = scanner.nextLine().trim();

                    if (input.equalsIgnoreCase("CANCEL")) {
                        return;
                    }

                    //Print the list of rooms matching the date range and desired room size
                    //Need to add search parameters later
                    System.out.println("Enter the number of beds for the room:");
                    scanner.nextLine();
                    reservationSize = scanner.nextInt();
        
                    ArrayList<Room> filteredRooms = ReservationManager.filterRooms(DatabaseManager.getCurrentHotel().getAllRooms(), reservationSize);
                    if (filteredRooms.isEmpty()) {
                        System.out.println("There are no rooms matching your search. Please try again.");
                        continue;
                    }
                    ReservationManager.listRooms(filteredRooms);
        
                    //User selects a room by typing in the room number + error check
                    System.out.println("Type in the room number of the desired room: ");
                    int roomNumber = scanner.nextInt();
                    chosenRoom = ReservationManager.findRoomByRoomNumber(filteredRooms, roomNumber);
                    if (chosenRoom == null) {
                        System.out.println("This room number is not from the listed rooms. Please try again.");
                        continue;
                    }
                    
                    //The full selected room details get printed
                    System.out.println("FULL ROOM DETAILS");
                    System.out.println("--------------------------------");
                    chosenRoom.printRoomInfo();
                    System.out.println("--------------------------------");
                    
                    //Are you sure prompt, if answer is no then it will go back to reprint list
                    System.out.println("Are you sure you want to change to this room? Type 1 to continue and any other number if not.");
                    int decisionTwo = scanner.nextInt();
                    if (decisionTwo != 1) {
                        continue;
                    }
        
                    looper2 = 1;
                }
                
                ReservationManager.cancelReservation(DatabaseManager.getCurrentHotel(), reservation.getAssignedUser(), reservation);
                ReservationManager.createReservation(DatabaseManager.getCurrentHotel(), reservation.getAssignedUser(), ReservationManager.getNextUnusedNumber(DatabaseManager.getCurrentHotel()),reservation.getTotalPrice(), chosenRoom, reservation.getStartDate(), reservation.getEndDate());
                System.out.println("The room has been changed successfully.");
            
                if (DatabaseManager.getCurrentUser() instanceof Manager) {
                    managerHomeScreen(scanner);
                } else {
                    userHomeScreen(scanner);
                }

                break;
            // Edit Reservation Price
            case 5:
                System.out.println("This is the current price for this reservation.");
                System.out.println("$" + reservation.getTotalPrice());

                System.out.println("Type your desired total price for the reservation.");
                System.out.println("To go back, type CANCEL.");

                String choice = scanner.nextLine().trim();

                if (choice.equalsIgnoreCase("CANCEL")) {
                    return;
                } else {
                    ReservationManager.setTotalPrice(reservation, Double.parseDouble(choice));
                }

                System.out.println("The reservation price has been changed successfully.");

                managerHomeScreen(scanner);
                break;
            // Edit Room Selection (Advanced)
            case 6:
                break;
        }
        return;
    }










}//End of GUI Manager Class