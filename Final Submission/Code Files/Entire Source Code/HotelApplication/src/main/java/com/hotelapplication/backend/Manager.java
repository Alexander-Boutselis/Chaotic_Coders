//Manager.java

package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Represents a manager in the hotel reservation system.
 * 
 * This class extends the User class and adds additional functionality specific to managers, 
 * including managing employee-specific details such as employee number, start date, and end date. 
 * It also includes methods to manage and print account information.
 * 
 * @author Alexander Boutselis
 * 
 */
public class Manager extends User{

    // Private Fields
	private int employeeNumber;
    private Calendar startDate;
    private Calendar endDate;

    /**
     * Constructor for Manager with employee number, first name, last name, birthday, username, and password.
     * 
     * @param employeeNumber The unique employee number for the manager.
     * @param firstName The first name of the manager.
     * @param lastName The last name of the manager.
     * @param birthday The birthday of the manager.
     * @param username The username for the manager's login.
     * @param password The password for the manager's login.
     */
	public Manager(int employeeNumber, String firstName, String lastName, Calendar birthday, String username, String password) {
        super(firstName, lastName, birthday, username, password);
        this.employeeNumber = employeeNumber;
        startDate = Calendar.getInstance();
        endDate = null;
		//notifications = new ArrayList<>();
	}

    /**
     * Constructor for Manager with userID, employee number, first name, last name, birthday, username, password, 
     * start date, end date, and active status.
     * 
     * @param userID The unique ID of the user.
     * @param employeeNumber The unique employee number for the manager.
     * @param firstName The first name of the manager.
     * @param lastName The last name of the manager.
     * @param birthday The birthday of the manager.
     * @param username The username for the manager's login.
     * @param password The password for the manager's login.
     * @param startDate The start date of the manager's employment.
     * @param endDate The end date of the manager's employment, if applicable.
     * @param isActive The active status of the manager.
     */
    public Manager(Integer userID, int employeeNumber, String firstName, String lastName, Calendar birthday, String username, String password, Calendar startDate, Calendar endDate, boolean isActive) {
        super(userID, firstName, lastName, birthday, username, password, isActive);
        this.employeeNumber = employeeNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        //notifications = new ArrayList<>();
    }

    /****************************************************************
     *                     Manager Info Methods                     *
     ****************************************************************/
    
    /********************************
     *      Set/Get Start Date      *
     ********************************/
    
    /**
     * Sets the start date for the manager's employment.
     * 
     * @param startDate The start date of the manager's employment.
     */
    public void setStartDate(Calendar startDate){
        this.startDate = startDate;
    }

    /**
     * Retrieves the start date of the manager's employment.
     * 
     * @return The start date of the manager's employment.
     */
    public Calendar getStartDate(){
        return startDate;
    }

    /********************************
     *       Set/Get End Date       *
     ********************************/
    
    /**
     * Sets the end date for the manager's employment.
     * 
     * @param endDate The end date of the manager's employment.
     */
    public void setEndDate(Calendar endDate){
        this.endDate = endDate;
    }

    /**
     * Retrieves the end date of the manager's employment.
     * 
     * @return The end date of the manager's employment, or `null` if not set.
     */
    public Calendar getEndDate(){
        return endDate;
    }

    /********************************
     *    Set/Get Employee Number   *
     ********************************/
    
    /**
     * Sets the employee number for the manager.
     * 
     * @param employeeNumber The unique employee number assigned to the manager.
     */
    public void setEmployeeNumber(int employeeNumber){
        this.employeeNumber = employeeNumber;
    }

    /**
     * Retrieves the employee number of the manager.
     * 
     * @return The employee number of the manager.
     */
    public int getEmployeeNumber(){
        return employeeNumber;
    }

    /********************************
     *      Print Account Info      *
     ********************************/
    
    /**
     * Retrieves the account information of the manager.
     * 
     * This includes the manager's name, username, employee number, birthday, and the number of reservations they have made.
     * 
     * @return A string representation of the manager's account information.
     */
    public String getAccountInfo(){
        StringBuilder accountInfo = new StringBuilder();

        accountInfo.append("\n***********************\n");
        accountInfo.append("User Signed in: " + getName());
        accountInfo.append("\n");
        accountInfo.append("Username: " + username);
        accountInfo.append("\n");
        accountInfo.append("Employee Number: " + employeeNumber);
        accountInfo.append("\n");
        SimpleDateFormat simpleFormatBirthday = new SimpleDateFormat("MM/dd/yyyy");
        accountInfo.append("Birthday: " + simpleFormatBirthday.format(birthday.getTime()));
        accountInfo.append("\n");
        accountInfo.append("Number of Reservations: " + reservationNumbers.size());
        accountInfo.append("\n***********************\n");

        return accountInfo.toString();
    }

    /**
     * Prints the account information of the manager to the console.
     */
    public void printAccount(){
        System.out.println(getAccountInfo());
    }   

/****************************************************************
 *                             End                              *
 ****************************************************************/
}//End of Manager Class