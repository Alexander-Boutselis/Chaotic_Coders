/**************************
 * Hotel Managment Project*
 * COMP 380/L             *
 **************************/
import java.util.Scanner;


//Start of Main
public class Main{
	public static void main(String[] args) {
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