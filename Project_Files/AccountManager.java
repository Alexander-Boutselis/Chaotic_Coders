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



    /*Functions:
        Create User Account
        Create Manager Account
        Add/Remove Account
        Get Account from Database
        Set/Get Username
        Check for Unique Username
        isManager()
        Edit User
        Sign-In
        Sign out
        Get/Set CurrentUser
        isEqualTo()
        Print Account Info
    */

















    //public
    public int numOfUsers;

    //private
	private int nextUserNumber;
	private int nextEmployeeNumber;
	private String managerConfirmationCode; 
    private Database database;


    //Constructor
	public AccountManager(Database database){
		nextUserNumber = 1;
		nextEmployeeNumber = 1;
		managerConfirmationCode = "Manager";
        numOfUsers = 0;
        this.database = database;
    }

/*




//------------------------------------------------------------------------------------------------------------Create Account
	public void createAccount(){
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
                //          Create User Acoount Option
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
                    String inputManagerCode = scanner.nextLine();

                    if (inputManagerCode.equals(managerConfirmationCode)) {
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
//------------------------------------------------------------------------------------------------------------End of Create Account



//------------------------------------------------------------------------------------------------------------Edit User Account
    public void editUserAccount(){

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        int option;
        String userInput;

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

                    break;

                //***********************************************
                //          Sign-Out of User Account
                //***********************************************
                case 2:
                    System.out.println("\nAre you sure you want to sign-out?");
                    userInput = scanner.nextLine().toLowerCase();

                    if (userInput.charAt(0)== 'y'){
                        database.hotel.setCurrentUser(null);
                        running = false;
                    }
                    break;

                //***********************************************
                //          Delete User Account
                //***********************************************
                case 3:
                System.out.println("Delete Account");

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
    }//End of editUserAccount
//------------------------------------------------------------------------------------------------------------End of Edit User Account




    //Check when User wants to create a Manager Account
//------------------------------------------------------------------------------------------------------------Is Manager
    public boolean isManager(String userInput){

        if (userInput == managerConfirmationCode){
            return true;
        }else{
            return false;
        }
    }
//------------------------------------------------------------------------------------------------------------End of Is Manager





//------------------------------------------------------------------------------------------------------------Account Prompts
    public void accountPrompts(boolean isManager){
        Scanner scanner = new Scanner(System.in);


        if (isManager) {
            System.out.println("---Manager Name---");
            
        }else {
            System.out.println("---User Name---");
            
        }
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
                        System.out.println("You must be 18 years or older to create an account.\nAccount Not Created");
                        return; // Exit method if the user is under 18
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
        
                        //Convert the password array to a string
                        inputPassword2 = new String(passwordArray);

                        //Confirm passwords match
                        if (inputPassword1.equals(inputPassword2)){
                            matching = true;
                        }else{
                            System.out.println("Passwords do not match, try again");
                        }

                    }
                    if (isManager) {
                        createManager(inputFirstName, inputLastName, inputFullBirthday, inputPassword2);
                        Manager tmpManager = (Manager) database.hotel.currentUser;
                        tmpManager.printManager();
                    }else{
                        createUser(inputFirstName, inputLastName, inputFullBirthday, inputPassword2);
                        database.hotel.currentUser.printUser();
                    }
        return;
    }//End of accountPrompts
//------------------------------------------------------------------------------------------------------------End ofAccount Prompts




//------------------------------------------------------------------------------------------------------------Create User
    public void createUser(String firstName, 
                           String lastName,
                         Calendar birthday,
                           String password){

        User newUser = new User(firstName, lastName, birthday, nextUserNumber, password);

        nextUserNumber++;
        numOfUsers++;
        database.addUser(newUser);
        database.hotel.setCurrentUser(newUser);

        return;
    }//End of createUser
//------------------------------------------------------------------------------------------------------------End of Create User



//------------------------------------------------------------------------------------------------------------Create Manager
    public void createManager(String firstName, 
                              String lastName, 
                            Calendar birthday,
                              String password){  

        Manager newManager = new Manager(nextEmployeeNumber, firstName, lastName, birthday, nextUserNumber, password);
        nextUserNumber++;
        numOfUsers++;
        database.addUser(newManager);
        database.hotel.setCurrentUser(newManager);

        return;
    }//End of createManager
//------------------------------------------------------------------------------------------------------------End of Create Manager

    

//------------------------------------------------------------------------------------------------------------Sign-In
public void accountSignIn(){

    Scanner scanner = new Scanner(System.in);
    String inputPassword;


    System.out.println("\n---Sign-in to Account---");

    //Get User Number
    System.out.print("Enter User Number: ");
    int inputUserNumber = scanner.nextInt();
    scanner.nextLine(); 

     //Get Password
    Console console = System.console();
    char[] passwordArray = console.readPassword("Enter password: ");

    // Convert the password array to a string
    inputPassword = new String(passwordArray);

    for (User user : database.allUsers){
        System.out.println("Checked User Number: " + user.getUserNumber());
        System.out.println("Entered Password: " + inputPassword + "\nUser Password: " + user.getPassword());
        if (user.getUserNumber() == inputUserNumber){
            if (user.getPassword().equals(inputPassword)){

                database.hotel.setCurrentUser(user);
                return;
            }
        }
    }
    System.out.println("User Number or Password incorrect");

    return;
}//End of accountSignIn
//------------------------------------------------------------------------------------------------------------End of Sign-In










    /*
    public boolean isOriginalName(String firstName, String lastName){
        for (int i = 0; i < numOfUsers; i++){
            if (firstName.equals(database.allUsers.get(i).getFirstName()) && lastName.equals(database.allUsers.get(i).getLastName())){
                return false;
            }
        }
        return true;
    }
    */





}//End of AccountManager