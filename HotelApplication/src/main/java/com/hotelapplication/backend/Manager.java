//Manager.java

package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;



import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;



public class Manager extends User{

	private int employeeNumber;
    private Calendar startDate;
    private Calendar endDate;
    //private ArrayList<String> notifications;


	public Manager(int employeeNumber, String firstName, String lastName, Calendar birthday, String username, String password) {
        super(firstName, lastName, birthday, username, password);
        this.employeeNumber = employeeNumber;
        startDate = Calendar.getInstance();
        endDate = null;
		//notifications = new ArrayList<>();
	}


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
    //Set Start Date
    public void setStartDate(Calendar startDate){
        this.startDate = startDate;
    }

    //Get Start Date
    public Calendar getStartDate(){
        return startDate;
    }

    /********************************
     *       Set/Get End Date       *
     ********************************/
    //Set End Date
    public void setEndDate(Calendar endDate){
        this.endDate = endDate;
    }

    //Get End Date
    public Calendar getEndDate(){
        return endDate;
    }

    /********************************
     *    Set/Get Employee Number   *
     ********************************/
    //Set EmployeeNumber
    public void setEmployeeNumber(int employeeNumber){
        this.employeeNumber = employeeNumber;
    }

    //Get Employee Number
    public int getEmployeeNumber(){
        return employeeNumber;
    }

    /********************************
     *      Print Account Info      *
     ********************************/
    //Get Account Info toSting()
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

    //Print Account info
    public void printAccount(){
        System.out.println(getAccountInfo());
    }   


/****************************************************************
 *                     Notification Methods                     *
 ****************************************************************/

    /********************************
     *      Get Notificaiton(s)     *
     ********************************/
    //Get All Notifications
	//public ArrayList<String> getNotifications() {
    //    return notifications;
    //}

    /********************************
     *    Add/Remove Notificaiton   *
     ********************************/
    //Add Notification
    //public void addNotification(String notification) {
    //    this.notifications.add(notification);
    //}

    //Remove Notification
    //public void removeNotification(String notification) {
    //    this.notifications.remove(notification);
    //}


/****************************************************************
 *                             End                              *
 ****************************************************************/
}//End of Manager Class