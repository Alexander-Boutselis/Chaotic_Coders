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
    private Calendar endDate;
    private ArrayList<String> notifications;


	public Manager(int employeeNumber, String firstName, String lastName, Calendar birthday, String username, String password) {
        super(firstName, lastName, birthday, username, password);
        this.employeeNumber = employeeNumber;
        startDate = Calendar.getInstance();
        endDate = null;
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
    //Get Start Date
    public Calendar getStartDate(){
        return startDate;
    }

    //Get End Date
    public Calendar getEndDate(){
        return endDate;
    }

    //Get Employee Number
    public int getEmployeeNumber(){
        return employeeNumber;
    }


/****************************************************************
 *                      Setters                                 *
 ****************************************************************/
    public void setStartDate(Calendar startDate){
        this.startDate = startDate;
    }

    public void setEndDate(Calendar endDate){
        this.endDate = endDate;
    }


    public void setEmployeeNumber(int employeeNumber){
        this.employeeNumber = employeeNumber;
    }


/****************************************************************
 *                       Print                                  *
 ****************************************************************/

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
    
}


