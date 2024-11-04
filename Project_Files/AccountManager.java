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
import java.util.ArrayList;
import java.util.Calendar;

//if (user instanceof Manager){} //For separating User and Manager, can be used for Rooms as well.


public class AccountManager{


    /*Variables:
        Manager Password
    */

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

    //Manager Password
    private static String managerPassword = "Manager";

    // Private constructor to prevent instantiation
    private AccountManager() {}



/****************************************************************
 *                          Getters                             *
 ****************************************************************/

    //Get Account from Database
    public static User getAccount(String searchUsername){
        return DatabaseManager.getUser(searchUsername);
    }

    //Get Username
    public static String getUsername(User user){
        return user.getUsername();
    }

    //Get First Name
    public static String getFirstName(User user){
        return user.getFirstName();
    }

    //Get Last Name
    public static String getLastName(User user){
        return user.getLastName();
    }

    //Get Name
    public static String getFullName(User user){
        return user.getName();
    }

    //Get Birthday
    public static Calendar getBirthday(User user){
        return user.getBirthday();
    }

    //Get Password
    public static String getPassword(User user){
        return user.getPassword();
    }

    //Get All Reservation Numbers
    public static ArrayList<Integer> getAllreservationNumbers(User user){
        return user.getAllreservationNumbers();
    }

    /*//Get All Notificaitons
    public static ArrayList<Notification> getAllNotifications(User user){
        return user.getAllNotifications();
    }*/

    //Get Employee Number
    public static int getEmployeeNumber(Manager manager){
        return manager.getEmployeeNumber();
    }

    //Get Start Date
    public static Calendar getEmployeeStartDate(Manager manager){
        return manager.getStartDate();
    }

    //Get End Date
    public static Calendar getEmployeeEndDate(Manager manager){
        return manager.getEndDate();
    }

/****************************************************************
 *                          Setters                             *
 ****************************************************************/

    //Set Username
    public static void setUsername(User user, String newUsername){
        user.setUsername(newUsername);
    }

    //Set First Name
    public static void setFirstName(User user, String newFirstName){
        user.setFirstName(newFirstName);
    }

    //Set Last Name
    public static void setLastName(User user, String newLastName){
        user.setLastName(newLastName);
    }

    //Set Name
    public static void setFullName(User user, String newFirstName, String newLastName){
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
    }

    //Set Birthday
    public static void setBirthday(User user, Calendar newBirthday){
        user.setBirthday(newBirthday);
    }

    //Set Password
    public static void setsetPassword(User user, String newPassword){
        user.setPassword(newPassword);
    }

    //Set Employee Number
    public static void setEmployeeNumber(Manager manager, int newEmployeeNumber){
        manager.setEmployeeNumber(newEmployeeNumber);
    }

    //Set Start Date
    public static void setEmployeeStartDate(Manager manager, Calendar newStartDate){
        manager.setStartDate(newStartDate);
    }

    //Set End Date
    public static void setEmployeeEndDate(Manager manager, Calendar newEndDate){
        manager.setEndDate(newEndDate);
    }


/****************************************************************
 *                         Booleans                             *
 ****************************************************************/
 
    //Check if Account is Manager (isManager)
    public static boolean isManager(User user){
        if (user instanceof Manager){
            return true;
        }else{
        return false;
        }
    }

    //Check if 2 Users are Equal (isEqual)
    public static boolean isEqual(User user1, User user2){
       if(user1.getUsername().equals(user2.getUsername())){
        return true;
       }else{
        return false;
       }
    }

    //Checks if a username is Unique (isUnique)
    public static boolean isUniqueUsername(String uniqueUsername){
        for (User user : DatabaseManager.getAllUsers()){
            if(user.getUsername().equals(uniqueUsername)){
                return false;
            }
        }
        return true;
   }


/****************************************************************
 *                      Sign-in/out                             *
 ****************************************************************/

    //Prompt to Sign-in
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
        if (user.getUsername().equals(inputUsername)){
            if (user.getPassword().equals(inputPassword)){

                signIn(user);
                return;
            }
        }
    }
    System.out.println("Username or Password incorrect");

    return;
}//End of accountSignIn

    //Sign-in
    public static void signIn(User user){
        DatabaseManager.signIn(user);
    }

    //Sign-out
    public static void signOut(){
        DatabaseManager.signOut();
    }



/****************************************************************
 *                     Add/Remove/Edit Account                  *
 ****************************************************************/

    //Add Account to Database
    public static void addAccount(User newUser){
        DatabaseManager.addUser(newUser);
    }

    //Remove Account from Database
    public static void removeAccount(String username){

    }

    //Edit User Account
    public static void editUserAccount(){

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
                editUserInfo(scanner);

                break;

            //***********************************************
            //          Sign-Out of User Account
            //***********************************************
            case 2:
                System.out.println("\nAre you sure you want to sign-out?");
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

    //Edit User Info
    public static void editUserInfo(Scanner scanner){
        boolean running = true;
        int option;
        String userInput;

        while (running){

            //Prompt user to select what to change
            System.out.println("\n---Edit Account Info---");
            
            //Print current User ifo
            printCurrentUser();

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


/****************************************************************
 *                          Print                               *
 ****************************************************************/

    //Get User toString
    public static String getAccountInfo(User user){
        return user.getAccountInfo();
    }

    //Get Current User toString
    public static void printAccountInfo(User user){
        System.out.println(getAccountInfo(user));
    }

    //Get Current User toString
    public static void printCurrentUser(){
        printAccountInfo(DatabaseManager.getCurrentUser());
    }


/****************************************************************
 *                        Create Account                        *
 ****************************************************************/
  
    //********************************
    //         Create Account        *
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

                    if (inputManagerPassword.equals(managerPassword)) {
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
    //         Create User           *
    //********************************
       public static void createUser(String firstName, String lastName, Calendar birthday, String username, String password){

        if(isUniqueUsername(username)){

        //Create User
        User newUser = new User(firstName, lastName, birthday, username, password);

        //Add to Database
        DatabaseManager.addUser(newUser);

        //Set Current User
        DatabaseManager.signIn(newUser);
        }else{
            System.out.println("Username Not Unique");

        }
        return;
   }//End of createUser


    //********************************
    //         Create Manager        *
    //********************************
   public static void createManager(String firstName, String lastName, Calendar birthday, String username, String password){  

            if(isUniqueUsername(username)){
            Manager newManager = new Manager(DatabaseManager.nextEmployeeNumber(), firstName, lastName, birthday, username, password);
            
            //Add to Database
            DatabaseManager.addUser(newManager);

            //Set Current User
            DatabaseManager.signIn(newManager);
        }
        return;
    }//End of createManager



/****************************************************************
 *                          Prompts                             *
 ****************************************************************/

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

            if(isUniqueUsername(inputUsername)){
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
            createManager(inputFirstName, inputLastName, inputFullBirthday, inputUsername, inputPassword2);

        }else{
            //Create User
            createUser(inputFirstName, inputLastName, inputFullBirthday, inputUsername, inputPassword2);
        }
        return;
    }//End of accountPrompts


/****************************************************************
 *                           End                                *
 ****************************************************************/
}