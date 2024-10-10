//AccountManager.java



/***********************************************************
 * Account Manager Class
 * 
 * Controller to create and edit user/manager accounts
 * 
 * User Number Counter
 * Employee Number Counter
************************************************************/
import java.util.Scanner;
import java.util.Calendar;
import java.io.Console;



public class AccountManager{

    //public
    public int numOfUsers;

	private int nextUserNumber;
	private int lastEmployeeNumber;
	private String manager_Confrimation; 
    private Database database;


	public AccountManager(Database database){
		nextUserNumber = 1;
		lastEmployeeNumber = 0;
		manager_Confrimation = "Manager";
        numOfUsers = 0;
        this.database = database;
        System.out.println("***Account Manager Created***");

		
	}

    //Check when User wants to create a Manager Account
    public boolean isManager(String userInput){

        if (userInput == manager_Confrimation){
            return true;
        }else{
            return false;
        }
    }


    public boolean isOriginalName(String firstName, String lastName){
        for (int i = 0; i < numOfUsers; i++){
            if (firstName.equals(database.allUsers.get(i).getFirstName()) && lastName.equals(database.allUsers.get(i).getLastName())){
                return false;
            }
        }
        return true;
    }


    public void createUser(String firstName, String lastName, 
                         Calendar birthday,  String password){

        if (isOriginalName(firstName,lastName)){
        User newUser = new User(firstName, lastName, birthday, nextUserNumber, password);

        nextUserNumber++;
        numOfUsers++;
        database.addUser(newUser);
        database.hotel.setCurrentUser(newUser);
        }

        return;
    }//End of createUser

    /*
    public void createManager(String firstName, String lastName, 
                                     int userNumber,String password,
                                Calendar birthday,Database database){

        //check if manager confirmaiton number is correct then create Manager 

        Manager newManager = new Manager(firstName, lastName, userNumber, password, birthday);
        database.addUser(newUser);

        return;
    }//End of createManager
    */

	public int createAccount(){
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
        int option;

        while (running) {
            System.out.println("---Create an Account---");
            System.out.println("1. Create a User Account");
            System.out.println("2. Create a Manager Account");
            System.out.println("3. Exit Appliation");
            System.out.print("Please enter a number: ");

            option = scanner.nextInt();
            scanner.nextLine(); 


            switch (option) {
                case 1:
                    System.out.println("---User Name---");

                    //Get first name
                    System.out.print("Please enter your first name: ");
                    String inputFirstName = scanner.nextLine();

                    //Get last name
                    System.out.print("Please enter your last name: ");
                    String inputLastName = scanner.nextLine();

                    //Get Birthday
                    System.out.println("---Birthday---");

                    //Get day of birth
                    System.out.print("Please enter the DAY of your birth (1-31): ");
                    int inputBirthDAY = scanner.nextInt();

                    //Get month of birth
                    System.out.print("Please enter the MONTH of your birth (1-12): ");
                    int inputBirthMONTH = scanner.nextInt();

                    //Get year of birth
                    System.out.print("Please enter the YEAR of your birth(####): ");
                    int inputBirthYEAR = scanner.nextInt();

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
                        System.out.println("You must be 18 years or older to create an account.");
                        break; // Exit method if the user is under 18
                    }

                    //Loop until passwords match
                    String inputPassword1;
                    String inputPassword2 = "";
                    boolean matching = false;

                    while(matching != true){

                        System.out.println("---Password---");

                        //Get Password
                        Console console = System.console();
                        char[] passwordArray = console.readPassword("Please create your password: ");
        
                        // Convert the password array to a string
                        inputPassword1 = new String(passwordArray);

                        //Confirm Password
                        passwordArray = console.readPassword("Please re-enter your password: ");
        
                        // Convert the password array to a string
                        inputPassword2 = new String(passwordArray);

                        if (inputPassword1.equals(inputPassword2)){
                            matching = true;
                        }else{
                            System.out.println("Passwords do not match, try again");
                        }

                    }

                    createUser(inputFirstName, inputLastName, inputFullBirthday, inputPassword2);
                    return 2;

                case 2:
                    System.out.println("---Manager Name---");
                    return 3;

                default:
                    System.out.println("Invalid option, please try again.");
                    break;
            }
        }
        scanner.close(); // Close the scanner to avoid resource leaks
        return 1;
	}//End of creatAccount






}//End of AccountManager