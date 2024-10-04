/**************************
 * Hotel Managment Project*
 * COMP 380/L             *
 **************************/
import java.util.Scanner;


//Start of Main
public class Main{
	public static void main(String[] args) {

        /*General Application Flow
         * App runs
         * Check database class if Hotel already exits --> For now this will not exist, hotel must be created every new run
         * If it does not create Hotel()
         *
         * Prompt User to sign in/create account
         *
         * Pull up User Menu (Options: 1.View Rooms, 2.View Reservations, and 3.Sign out)
         * Option 1:View Rooms
         *  Bring up List of all rooms
         *  Once a room is selected the user is prompted to make a reservation
         *  Call --> Reservation Manager()
         * Option 2:View Reservations
         *  If no reservations prompt and return to menu
         *  Else Print all Reservations for user
         * Option 3:Sign out
         *  Sign user out
         *  Return to Initial Sign in Screen
        */

//----------------------------------------------------------------------------------------Test Code
        //Check if Hotel Exits (for now it will not exist on a fresh run)
        Hotel chaotic_Coders_Inn = new Hotel();

        //Verify that the Hotel is created
        chaotic_Coders_Inn.printHotel();

        //Create Test User
        //User test = new User();
        //chaotic_Coders_Inn.setCurrentUser(test);

        //Verify that user has been created
        chaotic_Coders_Inn.printCurrentUser();

        
//-----------------------------------------------------------------------------------------End of Test Code


		Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the User Shell");
            System.out.println("1. Option 1");
            System.out.println("2. Option 2");
            System.out.println("3. Option 3");
            System.out.println("4. Option 4");
            System.out.println("5. Exit");
            System.out.print("Please enter a number: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("You selected Option 1.");
                    break;
                case 2:
                    System.out.println("You selected Option 2.");
                    break;
                case 3:
                    System.out.println("You selected Option 3.");
                    break;
                case 4:
                    System.out.println("You selected Option 4.");
                    break;
                case 5:
                    System.out.println("Exiting the shell.");
                    running = false; // This ends the loop and exits the program.
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }

        scanner.close(); // Close the scanner to avoid resource leaks
	}
}