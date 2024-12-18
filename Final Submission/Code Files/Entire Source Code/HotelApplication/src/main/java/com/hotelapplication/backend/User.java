//User.java

package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Represents a user in the hotel reservation system.
 * 
 * This class stores user details such as name, birthday, account information, 
 * and reservations. It provides methods to manage user details and reservations.
 * 
 * @author Alexander Boutselis
 * 
 */
public class User {

    //User Info
    private Integer userID;
    private String firstName;
    private String lastName;
    protected Calendar birthday = Calendar.getInstance();
    protected String username;
    private String password;
    protected boolean isActive;

    //User Reservations
    protected ArrayList<Integer> reservationNumbers;
 
    /**
     * Constructs a User object with basic account details.
     * 
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param birthday The user's date of birth.
     * @param username The username for the account.
     * @param password The password for the account.
     */
    public User(String firstName, String lastName, Calendar birthday, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        isActive = true;
        this.reservationNumbers = new ArrayList<>();
    }

    /**
     * Constructs a User object with detailed account and status information.
     * 
     * @param userID The unique ID of the user.
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param birthday The user's date of birth.
     * @param username The username for the account.
     * @param password The password for the account.
     * @param isActive The active status of the account.
     */
    public User(Integer userID, String firstName, String lastName, Calendar birthday, String username, String password, boolean isActive) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
        this.reservationNumbers = new ArrayList<>();
    }

    /****************************************************************
     *                         User Info Methods                    *
     ****************************************************************/
    /********************************
     *        Set/Get userID        *
     ********************************/
    
    /**
     * Sets the user's unique ID.
     * 
     * @param userID The unique ID to assign to the user.
     */
    public void setUserID(int userID){
        this.userID = userID;
    }

    /**
     * Retrieves the user's unique ID.
     * 
     * @return The user's unique ID.
     */
    public Integer getUserID(){
        return userID;
    }

    /********************************
     *         Set/Get Name         *
     ********************************/
    /**
     * Sets the user's first name.
     * 
     * @param firstName The first name of the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the user's last name.
     * 
     * @param lastName The last name of the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the user's first name.
     * 
     * @return The user's first name.
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * Retrieves the user's last name.
     * 
     * @return The user's last name.
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * Retrieves the user's full name by combining first and last names.
     * 
     * @return The user's full name.
     */
    public String getName() {
        String nameCombiner;
        nameCombiner = firstName + " " + lastName;
        return nameCombiner;
    }

    /********************************
     *       Set/Get Birthday       *
     ********************************/
    
    /**
     * Sets the user's date of birth.
     * 
     * @param birthday The user's date of birth as a Calendar object.
     */
    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    /**
     * Retrieves the user's date of birth.
     * 
     * @return The user's date of birth as a Calendar object.
     */
    public Calendar getBirthday() {
        return birthday;
    }

    /********************************
     *          Print Info          *
     ********************************/
    
    /**
     * Retrieves account information as a formatted string.
     * 
     * @return A string containing the user's account details.
     */
    public String getAccountInfo(){
        StringBuilder accountInfo = new StringBuilder();

        accountInfo.append("\n***********************\n");

        if(!isActive){            
            accountInfo.append("ACCOUNT REMOVED, INACTIVE");
            accountInfo.append("\n");
        }
        accountInfo.append("User: " + getName());
        accountInfo.append("\n");
        accountInfo.append("Username: " + username);
        accountInfo.append("\n");
        SimpleDateFormat simpleFormatBirthday = new SimpleDateFormat("MM/dd/yyyy");
        accountInfo.append("Birthday: " + simpleFormatBirthday.format(birthday.getTime()));
        accountInfo.append("\n");
        accountInfo.append("Number of Reservations: " + reservationNumbers.size());
        accountInfo.append("\n***********************");

        return accountInfo.toString();
    }

    /**
     * Prints the user's account information to the console.
     */
    public void printAccount(){
        System.out.println(getAccountInfo());
    }

    /****************************************************************
     *                   Username/Password Methods                  *
     ****************************************************************/

    /********************************
     *       Set/Get Username       *
     ********************************/
    
    /**
     * Sets the user's username.
     * 
     * @param username The username to assign.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the user's username.
     * 
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /********************************
     *       Set/Get Password       *
     ********************************/
    
    /**
     * Sets the user's password.
     * 
     * @param password The password to assign.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the user's password.
     * 
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /****************************************************************
     *                     Reservation Methods                      *
     ****************************************************************/

    /********************************
     *      Get Reservation(s)      *
     ********************************/
    
    /**
     * Retrieves a reservation number from the user's list of reservation numbers.
     * 
     * This method searches for a reservation number in the list of the user's reservations
     * and returns the first match found. If no reservation number is found, it returns -1.
     *
     * @param searchReservationNumber The reservation number to search for.
     * @return The found reservation number, or -1 if no match is found.
     */
    public int getReservationNumber(int searchReservationNumber){
        for(int reservationNumber : reservationNumbers){
            return reservationNumber;
        }
        return -1;
    }

    /**
     * Retrieves all reservation numbers associated with the user.
     * 
     * This method returns the entire list of reservation numbers that belong to the user.
     * It can be used to get a list of all reservations made by the user.
     *
     * @return An ArrayList of reservation numbers.
     */
    public ArrayList<Integer> getAllreservationNumbers() {
        return reservationNumbers;
    }

    /********************************
     *    Add/Remove Reservation    *
     ********************************/
    
    /**
     * Adds a reservation number to the user's list of reservations.
     * 
     * @param reservationNumber The reservation number to add.
     */
    public void addReservation(int reservationNumber) {
        reservationNumbers.add(reservationNumber);
    }

    /**
     * Removes a reservation number from the user's list of reservations.
     * 
     * @param reservationNumber The reservation number to remove.
     */
    public void removeReservation(int reservationNumber) {
        for(int i = 0; i < reservationNumbers.size(); i++){
            if(reservationNumbers.get(i) == reservationNumber){
                reservationNumbers.remove(i);
                return;
            }
        }
    }



    /****************************************************************
     *                       Boolean Methods                        *
     ****************************************************************/

    /********************************
     *            Equals            *
     ********************************/
    
    /**
     * Checks if the current user is equal to another user based on their usernames.
     * 
     * @param otherUser The other User object to compare.
     * @return True if the usernames are equal; false otherwise.
     */
    public boolean isEqualTo(User otherUser){
        if (username.equals(otherUser.username)) {
            return true;
        }else{
            return false;
        }
    }

    /********************************
     *    Set/Get Active Status     *
     ********************************/
    
    /**
     * Sets the user's active status.
     * 
     * @param newStatus The active status to assign.
     */
    public void setActiveStatus(boolean newStatus){
        isActive = newStatus;
    }

    /**
     * Retrieves the user's active status.
     * 
     * @return True if the user is active; false otherwise.
     */
    public boolean getActiveStatus(){
        return isActive;
    }

/****************************************************************
 *                             End                              *
 ****************************************************************/
}//End of User Class
