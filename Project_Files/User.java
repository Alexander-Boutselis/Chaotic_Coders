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
    //$ Balance

    //Log in Info
    protected String username;
    private String password;

    //User Reservations
    private ArrayList<Integer> reservationIndexNumbers;
    //private ArrayList<Notification> notifications;

 
    // Constructor
    public User(String firstName, String lastName, Calendar birthday, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        this.reservationIndexNumbers = new ArrayList<>();
    }


/****************************************************************
 *                      Setters                                 *
 ****************************************************************/
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
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
        return reservationIndexNumbers.get(index);
    }

    public ArrayList<Integer> getAllReservationIndexNumbers() {
        return reservationIndexNumbers;
    }


/****************************************************************
 *                  Add/Remove Reservation                      *
 ****************************************************************/
    public void addReservation(int reservationIndex) {
        this.reservationIndexNumbers.add(reservationIndex);
    }

    public void removeReservation(String reservationIndex) {
        this.reservationIndexNumbers.remove(reservationIndex);
    }


/****************************************************************
 *                      Print                                   *
 ****************************************************************/
    public void printAccount(){
        System.out.println("\nUser Signed in: " + getName());
        System.out.println("Username: " + username);
        SimpleDateFormat simpleFormatBirthday = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println("User's Birthday: " + simpleFormatBirthday.format(birthday.getTime()));
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
