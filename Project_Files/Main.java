/**************************
 * Hotel Management Project*
 * COMP 380/L             *
 **************************/
import java.util.Scanner;


//Start of Main
public class Main{
	public static void main(String[] args) {

        


//-----------------------------------------------------------------------------------------Test Code
//-----------------------------------------------------------------------------------------End of Test Code

        //Connect to Database
        //Database database = new Database();
        DatabaseManager.initializeDatabase();

        //Verify that the Hotel is created
        database.hotel.printHotel();
     
        //if (user instanceof Manager){} //For separating User and Manager, can be used for Rooms as well.




		Scanner scanner = new Scanner(System.in);
        boolean running = true;
        int choice = 1;
        int state = 1;

        while (running) {

            if (database.hotel.currentUser == null){
                state = 1;
            }else if (database.hotel.currentUser instanceof Manager){
                state = 3;

            }else {
                state = 2;
            }

            System.out.println("Current State: " + state);

            switch (state) {
//--------------------------------------------------------------------------------------------State 1 Not Signed-In
                case 1://Not Signed-in

                    System.out.println("\n---State 1 Entered: User not signed in---");

                    System.out.println("1. Sign-in");
                    System.out.println("2. Sign-up");
                    System.out.println("3. Quit");
                    System.out.print("Please enter a number: ");

                    choice = scanner.nextInt();
                    scanner.nextLine(); 


                    switch(choice){
                        case 1:
                            //Call AccountManager.SignIn();
                            database.accountManager.accountSignIn();
                            break;

                        case 2:
                            //Call AccountManager.CreateAccount();
                            database.accountManager.createAccount();
                            break;

                        case 3:
                            System.out.println("Exiting...");
                            running = false;
                            break;

                        default:
                            //Invalid choice
                            System.out.println("Invalid Selection");
                            break;
                    }
                    break;

//--------------------------------------------------------------------------------------------State 2 User Signed-In
                case 2://User Signed-in
                    System.out.println("\n---State 2 Entered: User signed in---");

                    System.out.println("1. View Rooms (Make a reservation)");
                    System.out.println("2. Edit My Account");
                    System.out.println("3. My Reservations");
                    System.out.println("4. Quit");
                    System.out.print("Please enter a number: ");

                    choice = scanner.nextInt();
                    scanner.nextLine(); 


                    switch(choice){
                        case 1:
                            //View Rooms
                            System.out.println("View Rooms");
                            database.reservationManager.viewRoomsMakeReservation(database.hotel.getCurrentUser());
                            break;

                        case 2:
                            //Edit My Account
                            System.out.println("Edit My Account");
                            database.accountManager.editUserAccount();
                            break;

                        case 3:
                            //My Reservations
                            System.out.println("My Reservations");
                            break;

                        case 4:
                            System.out.println("Exiting...");
                            running = false;
                            break;
                            
                        default:
                            //Invalid choice
                            System.out.println("Invalid Selection");
                            break;
                    }

                    break;

//--------------------------------------------------------------------------------------------State 3 Manager Signed-In
                case 3://Manager Signed-in
                    System.out.println("\n---State 3 Entered: Manager signed in---");
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
                            database.accountManager.editUserAccount();
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
                    //state = debug();//REMOVE
                    break;
            
//--------------------------------------------------------------------------------------------State 4 ExitProgram
                case 4://Exit Program
                    System.out.println("Exiting the shell.");
                    running = false; // This ends the loop and exits the program.
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close(); // Close the scanner to avoid resource leaks
	}//End of main


public static int debug(){
        Scanner scanner = new Scanner(System.in);
        int input;
    System.out.println("\n---State Options (DEBUG)---");
    System.out.println("1. Not Signed in State");
    System.out.println("2. User Signed in State");
    System.out.println("3. Manager Signed in State");
    System.out.println("4. Quit Program");
    System.out.print("Set program state to:");
    input = scanner.nextInt();

    return input;
}



}//End of Main