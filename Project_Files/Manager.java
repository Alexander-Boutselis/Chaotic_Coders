//Manager.java




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


	private int employeeNumber;
    private Calendar startDate;
    private ArrayList<String> notifications;


	public Manager(int employeeNumber, String firstName, String lastName, Calendar birthday, String username, String password) {
        super(firstName, lastName, birthday, username, password);
        this.employeeNumber = employeeNumber;
        startDate = Calendar.getInstance();
		notifications = new ArrayList<>();
	}


/****************************************************************
 *                      Notifications                           *
 ****************************************************************/
	public ArrayList<String> getNotifications() {
        return notifications;
    }

    public void addNotification(String notification) {
        this.notifications.add(notification);
    }

    public void removeNotification(String notification) {
        this.notifications.remove(notification);
    }


/****************************************************************
 *                      Getters                                 *
 ****************************************************************/
    public Calendar getStartDate(){
        return startDate;
    }

    public int getEmployeeNumber(){
        return employeeNumber;
    }


/****************************************************************
 *                      Setters                                 *
 ****************************************************************/
    public void getStartDate(Calendar date){
        startDate = date;
    }

    public void getEmployeeNumber(int num){//Should not need this?
        employeeNumber = num;
    }


/****************************************************************
 *                       Print                                  *
 ****************************************************************/
    public void printAccount(){
        System.out.println("\nUser Signed in: " + getName());
        System.out.println("Username: " + username);
        System.out.println("Employee Number: " + employeeNumber);
        SimpleDateFormat simpleFormatBirthday = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println(getName() + "'s Birthday: " + simpleFormatBirthday.format(birthday.getTime()));
    }

}


