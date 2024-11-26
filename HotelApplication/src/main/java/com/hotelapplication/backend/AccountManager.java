//AccountManager.java

package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Controller for creating and managing user and manager accounts in the hotel reservation system.
 * 
 * This class includes methods to create, edit, and remove user and manager accounts, as well as
 * managing user login and unique username validation. It provides functionality to retrieve, 
 * set, and compare user information, as well as manage their sign-in and sign-out processes.
 * 
 * @author Ethan Mojahedi
 * 
 */
public class AccountManager{

    //Manager Password
    private static String managerPassword = "Manager";

    // Private constructor to prevent instantiation
    private AccountManager() {}

/****************************************************************
 *                          Getters                             *
 ****************************************************************/
    
    /**
     * Gets the User ID for a given user.
     * 
     * @param user The user whose ID is being retrieved.
     * @return The User ID.
     */
    public static int getUserID(User user){
        return user.getUserID();
    }

    /**
     * Retrieves the account associated with a specific username.
     * 
     * @param searchUsername The username of the account to retrieve.
     * @return The User object associated with the provided username.
     */
    public static User getAccount(String searchUsername){
        return DatabaseManager.getUser(searchUsername);
    }

    /**
     * Retrieves the account associated with a specific username.
     * 
     * @param userID The username of the account to retrieve.
     * @return The User object associated with the provided username.
     */
    public static User getAccount(int userID){
        return DatabaseManager.getUser(userID);
    }

    /**
     * Retrieves the username of a given user.
     * 
     * @param user The user whose username is being retrieved.
     * @return The username of the user.
     */
    public static String getUsername(User user){
        return user.getUsername();
    }

    /**
     * Retrieves the first name of a given user.
     * 
     * @param user The user whose first name is being retrieved.
     * @return The first name of the user.
     */
    public static String getFirstName(User user){
        return user.getFirstName();
    }

    /**
     * Retrieves the last name of a given user.
     * 
     * @param user The user whose last name is being retrieved.
     * @return The last name of the user.
     */
    public static String getLastName(User user){
        return user.getLastName();
    }

    /**
     * Retrieves the full name (first + last name) of a given user.
     * 
     * @param user The user whose full name is being retrieved.
     * @return The full name of the user.
     */
    public static String getFullName(User user){
        return user.getName();
    }

    /**
     * Retrieves the birthday of a given user.
     * 
     * @param user The user whose birthday is being retrieved.
     * @return The birthday of the user.
     */
    public static Calendar getBirthday(User user){
        return user.getBirthday();
    }

    /**
     * Retrieves the password of a given user.
     * 
     * @param user The user whose password is being retrieved.
     * @return The password of the user.
     */
    public static String getPassword(User user){
        return user.getPassword();
    }

    /**
     * Retrieves all reservation numbers associated with a given user.
     * 
     * @param user The user whose reservation numbers are being retrieved.
     * @return A list of reservation numbers.
     */
    public static ArrayList<Integer> getAllreservationNumbers(User user){
        return user.getAllreservationNumbers();
    }

    /*//Get All Notificaitons
    public static ArrayList<Notification> getAllNotifications(User user){
        return user.getAllNotifications();
    }*/

    /**
     * Retrieves the employee number of a manager.
     * 
     * @param manager The manager whose employee number is being retrieved.
     * @return The employee number of the manager.
     */
    public static int getEmployeeNumber(Manager manager){
        return manager.getEmployeeNumber();
    }

    /**
     * Retrieves the start date of a manager's employment.
     * 
     * @param manager The manager whose start date is being retrieved.
     * @return The start date of the manager's employment.
     */
    public static Calendar getEmployeeStartDate(Manager manager){
        return manager.getStartDate();
    }

    /**
     * Retrieves the end date of a manager's employment.
     * 
     * @param manager The manager whose end date is being retrieved.
     * @return The end date of the manager's employment, or `null` if not set.
     *
     */
    public static Calendar getEmployeeEndDate(Manager manager){
        return manager.getEndDate();
    }

    /**
     * Retrieves the manager password.
     * 
     * @return The manager password.
     */
    public static String getManagerPassword(){
        return managerPassword;
    }

/****************************************************************
 *                          Setters                             *
 ****************************************************************/
    
    /**
     * Sets the User ID for a given user.
     * 
     * @param user The user whose ID is being set.
     * @param userID The new User ID.
     */
    public static void setUserID(User user, int userID){
        user.setUserID(userID);
        DatabaseConnector.updateUserInDatabase(user);

    }

    /**
     * Sets the username for a given user.
     * 
     * @param user The user whose username is being set.
     * @param newUsername The new username.
     */
    public static void setUsername(User user, String newUsername){
        user.setUsername(newUsername);
        DatabaseConnector.updateUserInDatabase(user);
    }

    /**
     * Sets the first name for a given user.
     * 
     * @param user The user whose first name is being set.
     * @param newFirstName The new first name.
     */
    public static void setFirstName(User user, String newFirstName){
        user.setFirstName(newFirstName);
        DatabaseConnector.updateUserInDatabase(user);

    }

    /**
     * Sets the last name for a given user.
     * 
     * @param user The user whose last name is being set.
     * @param newLastName The new last name.
     */
    public static void setLastName(User user, String newLastName){
        user.setLastName(newLastName);
        DatabaseConnector.updateUserInDatabase(user);

    }

    /**
     * Sets the full name (first + last name) for a given user.
     * 
     * @param user The user whose full name is being set.
     * @param newFirstName The new first name.
     * @param newLastName The new last name.
     */
    public static void setFullName(User user, String newFirstName, String newLastName){
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        DatabaseConnector.updateUserInDatabase(user);

    }

    /**
     * Sets the birthday for a given user.
     * 
     * @param user The user whose birthday is being set.
     * @param newBirthday The new birthday.
     */
    public static void setBirthday(User user, Calendar newBirthday){
        user.setBirthday(newBirthday);
        DatabaseConnector.updateUserInDatabase(user);

    }

    /**
     * Sets the password for a given user.
     * 
     * @param user The user whose password is being set.
     * @param newPassword The new password.
     */
    public static void setPassword(User user, String newPassword){
        user.setPassword(newPassword);
        DatabaseConnector.updateUserInDatabase(user);

    }

    /**
     * Sets the employee number for a manager.
     * 
     * @param manager The manager whose employee number is being set.
     * @param newEmployeeNumber The new employee number.
     */
    public static void setEmployeeNumber(Manager manager, int newEmployeeNumber){
        manager.setEmployeeNumber(newEmployeeNumber);
        DatabaseConnector.updateUserInDatabase(manager);

    }

    /**
     * Sets the start date for a manager's employment.
     * 
     * @param manager The manager whose start date is being set.
     * @param newStartDate The new start date.
     */
    public static void setEmployeeStartDate(Manager manager, Calendar newStartDate){
        manager.setStartDate(newStartDate);
        DatabaseConnector.updateUserInDatabase(manager);

    }

    /**
     * Sets the end date for a manager's employment.
     * 
     * @param manager The manager whose end date is being set.
     * @param newEndDate The new end date.
     */
    public static void setEmployeeEndDate(Manager manager, Calendar newEndDate){
        manager.setEndDate(newEndDate);
        DatabaseConnector.updateUserInDatabase(manager);

    }


/****************************************************************
 *                         Booleans                             *
 ****************************************************************/
 
    /**
     * Checks if a given user is a manager.
     * 
     * @param user The user to check.
     * @return `true` if the user is a manager, `false` otherwise.
     */
    public static boolean isManager(User user){
        if (user instanceof Manager){
            return true;
        }else{
        return false;
        }
    }

    /**
     * Checks if two users are equal based on their username.
     * 
     * @param user1 The first user.
     * @param user2 The second user.
     * @return `true` if the usernames are equal, `false` otherwise.
     */
    public static boolean isEqual(User user1, User user2){
       if(user1.getUsername().equals(user2.getUsername())){
        return true;
       }else{
        return false;
       }
    }

   /**
     * Checks if a given username is unique.
     * 
     * @param uniqueUsername The username to check.
     * @return `true` if the username is unique, `false` otherwise.
     */
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

    /**
     * Signs a user into the system.
     * 
     * @param user The user to sign in.
     */
    public static void signIn(User user){
        DatabaseManager.signIn(user);
    }

    /**
     * Signs the current user out of the system.
     */
    public static void signOut(){
        DatabaseManager.signOut();
    }



/****************************************************************
 *                     Add/Remove/Edit Account                  *
 ****************************************************************/

    /**
     * Adds a new user account to the system.
     * 
     * @param newUser The user to add to the database.
     */
    public static void addAccount(User newUser){
        DatabaseManager.addUser(newUser);
    }

   /**
     * Removes an account from the system by deactivating it.
     * 
     * @param user The user to remove from the system.
     */
    public static void removeAccount(User user){
        user.setActiveStatus(false);
        DatabaseConnector.updateUserInDatabase(user);

    }


/****************************************************************
 *                          Print                               *
 ****************************************************************/

    /**
     * Retrieves the account information of a given user.
     * 
     * @param user The user whose account information is being retrieved.
     */
    public static String getAccountInfo(User user){
        return user.getAccountInfo();
    }

    /**
     * Retrieves and prints the account information of a given user.
     * 
     * @param user The user whose account information is being printed.
     */
    public static void printAccountInfo(User user){
        System.out.println(getAccountInfo(user));
    }

    /**
     * Prints the account information of the current user.
     */
    public static void printCurrentUser(){
        printAccountInfo(DatabaseManager.getCurrentUser());
    }


/****************************************************************
 *                        Create Account                        *
 ****************************************************************/
    
    /**
     * Creates a new user account with the specified information.
     * 
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param birthday The user's birthday.
     * @param username The user's desired username.
     * @param password The user's desired password.
     */
    public static void createUser(String firstName, String lastName, Calendar birthday, String username, String password){

        if(isUniqueUsername(username)){

        //Create User
        User newUser = new User(firstName, lastName, birthday, username, password);

        //Add to Database
        addAccount(newUser);
        DatabaseConnector.addAccount(newUser);

        //Set Current User
        DatabaseManager.signIn(newUser);
        }else{
            System.out.println("Username Not Unique");

        }
        return;
   }//End of createUser

   /**
     * Creates a new manager account with the specified information.
     * 
     * @param firstName The manager's first name.
     * @param lastName The manager's last name.
     * @param birthday The manager's birthday.
     * @param username The manager's desired username.
     * @param password The manager's desired password.
     */
   public static void createManager(String firstName, String lastName, Calendar birthday, String username, String password){  

            if(isUniqueUsername(username)){
            Manager newManager = new Manager(DatabaseManager.nextEmployeeNumber(), firstName, lastName, birthday, username, password);
            
            //Add to Database
            addAccount(newManager);
            DatabaseConnector.addAccount(newManager);

            //Set Current User
            DatabaseManager.signIn(newManager);
        }
        return;
    }//End of createManager

/****************************************************************
 *                           End                                *
 ****************************************************************/
}