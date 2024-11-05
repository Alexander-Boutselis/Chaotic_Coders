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

    //Get Account from Database -------------------------------------REWORK
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

    //Get Manager Password
    public static String getManagerPassword(){
        return managerPassword;
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
    public static void setPassword(User user, String newPassword){
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
    public static void removeAccount(User user){
        user.setActiveStatus(false);
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
 *                           End                                *
 ****************************************************************/
}