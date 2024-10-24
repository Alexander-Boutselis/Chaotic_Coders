//Manager.java


/***********************************
*Manager Class should extend User: *
* Employee #                   *
* Password                     *
* Array Notificaitons (updates)*
********************************/


import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;



public class Manager extends User{

    /*Variables:
        Employee Number
        Start Date
    */
    
    /*Functions:
        Get/Set Employee Number
        Get/Set Start Date
        Calc Time worked at Hotel
        PrintAccount
        
    */



	protected int employeeNumber;
    private ArrayList<String> notifications;




	public Manager(int employeeNumber, String firstName, String lastName, Calendar birthday, int userNumber, String password) {
        super(firstName, lastName, birthday, userNumber, password);
        this.employeeNumber = employeeNumber;
		notifications = new ArrayList<>();
	}


	public ArrayList<String> getNotifications() {
        return notifications;
    }

    public void addNotification(String notification) {
        this.notifications.add(notification);
    }

    public void removeNotification(String notification) {
        this.notifications.remove(notification);
    }

    //Overide
    public void printAccount(){
        System.out.println("\nUser Signed in: " + getName());
        System.out.println("User Number: " + userNumber);
        SimpleDateFormat simpleFormatBirthday = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println("User's Birthday: " + simpleFormatBirthday.format(birthday.getTime()));
    }

}


