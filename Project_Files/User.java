//User.java

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class User {

    /*Variables
    First Name
    Last Name
    Birthday
    Username
    Password
    List of Reservation Index in Database
    List of Notificaitons
    */

    /*Functions:
    Get/Set First and Last name (seperate)
    Get/Set Birthday
    Get/Set Username
    Get/Set Password
    Add Reservation
    Remove Reservation
    Get Reservation from list
    Add/Remove Notificaitons
    Get Notificaitons
    Print User
    */

    //User Info
    private String firstName;
    private String lastName;
    protected Calendar birthday = Calendar.getInstance();
    //$ Balance - Create a Payment Manager

    //Log in Info
    protected String username;
    private String password;
    //Add Boolean isActive to set as false when a user wants to terminate their account (dont delete it need it for records)

    //User Reservations
    protected ArrayList<Integer> reservationNumbers;
    //private ArrayList<Notification> notifications;

 
    // Constructor
    public User(String firstName, String lastName, Calendar birthday, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        this.reservationNumbers = new ArrayList<>();
    }


/****************************************************************
 *                      Setters                                 *
 ****************************************************************/
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


/****************************************************************
 *                      Getters                                 *
 ****************************************************************/
    public String getName() {
        String nameCombiner;
        nameCombiner = firstName + " " + lastName;
        return nameCombiner;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

   public int getReservationIndexNumber(int index){
        return reservationNumbers.get(index);
    }

    public ArrayList<Integer> getAllreservationNumbers() {
        return reservationNumbers;
    }


/****************************************************************
 *                  Add/Remove Reservation                      *
 ****************************************************************/
    public void addReservation(int reservationIndex) {
        this.reservationNumbers.add(reservationIndex);
    }

    public void removeReservation(int reservationIndex) {
        this.reservationNumbers.remove(reservationIndex);
    }


/****************************************************************
 *                      Print                                   *
 ****************************************************************/

    //Get Account Info toSting()
    public String getAccountInfo(){
        StringBuilder accountInfo = new StringBuilder();

        accountInfo.append("\n***********************\n");
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

    //Print Account info
    public void printAccount(){
        System.out.println(getAccountInfo());
    }


/****************************************************************
 *                      Equals                                  *
 ****************************************************************/
    public boolean isEqualTo(User otherUser){
        if (username.equals(otherUser.username)) {
            return true;
        }else{
            return false;
        }
    }
}
