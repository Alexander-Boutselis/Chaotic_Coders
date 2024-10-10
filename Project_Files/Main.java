/**************************
 * Hotel Management Project*
 * COMP 380/L             *
 **************************/
import java.util.Scanner;


//Start of Main
public class Main{
	public static void main(String[] args) {

        


//-----------------------------------------------------------------------------------------Test Code

        //Connect to Database
        Database database = new Database();

        


        

        






        //Check if Hotel Exits (for now it will not exist on a fresh run)
        //Hotel chaotic_Coders_Inn = new Hotel();

        //Initiate Driver Classes
        //AccountManager accountManager = new AccountManager();
        //ReservationManager reservationManager = new ReservationManager(chaotic_Coders_Inn);

        //Verify that the Hotel is created
        database.hotel.printHotel();

        //Create Test User
        //User test = new User();
        //chaotic_Coders_Inn.setCurrentUser(test);

        //Verify that user has been created
        database.hotel.printCurrentUser();

        
        //if (user instanceof Manager){} //For separating User and Manager, can be used for Rooms as well.




		Scanner scanner = new Scanner(System.in);
        boolean running = true;
        int choice;
        int state = 1;

        while (running) {

            if (database.hotel.currentUser == null){
                state = 1;
            }

            System.out.println("Current State: " + state);

            switch (state) {

                case 1://Not Signed-in

                    System.out.println("State 1 Entered: User not signed in");

                    System.out.println("1. Sign-in");
                    System.out.println("2. Sign-up");
                    System.out.print("Please enter a number: ");


                    choice = scanner.nextInt();

                    switch(choice){
                        case 1:
                            //Call AccountManager.SignIn();
                            System.out.println("User has Signed-in");
                            break;

                        case 2:
                            //Call AccountManager.CreateAccount();
                            state = database.accountManager.createAccount();
                            database.hotel.currentUser.printUser();
                            System.out.println("User has created an account and has been Signed-in");
                            break;

                        default:
                            //Invalid choice
                            System.out.println("Invalid Selection");
                            break;
                    }
                    break;

                case 2://User Signed-in
                    System.out.println("State 2 Entered: User signed in");
                    state = scanner.nextInt();

                    break;

                case 3://Manager Signed-in
                    System.out.println("State 3 Entered: Manager signed in");
                    state = scanner.nextInt();
                    break;
                
                case 4://Exit Program
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