//User.j

/*******************************
*User Class should include:    *
* Name                         *
* Birthday                     *
* User #                       *
* Password                     *
* An Array of Reservations     *
* Array Notificaitons (updates)*
* Boolean isManager            * 
********************************/


//import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class User {
    
    //User Info
    private String firstName; //We should seperate first and last name into their own Variables and seperate the prompt. This will intigrate with the GUI easier
    private String lastName;
    protected Calendar birthday = Calendar.getInstance();

    //Log in Info
    protected int userNumber;
    private String password;

    //User Reservations
    private ArrayList<String> reservations;


/*    // Testing Default Constructor
    public User() {
        firstName = "Tina TestUser";
        birthday.set(2000, Calendar.APRIL, 13);
        userNumber = 00001;
        password = "password";
        reservations = new ArrayList<>();
    }
*/
 
    // Constructor
    public User(String firstName, String lastName, Calendar birthday, int userNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.userNumber = userNumber;
        this.password = password;
        this.reservations = new ArrayList<>();
        
    }

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

    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getReservations() {
        return reservations;
    }

    public void addReservation(String reservation) {
        this.reservations.add(reservation);
    }

    public void removeReservation(String reservation) {
        this.reservations.remove(reservation);
    }

    public void printUser(){
        System.out.println("\nUser Signed in: " + getName());
        System.out.println("User Number: " + userNumber);
        SimpleDateFormat simpleFormatBirthday = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println("User's Birthday: " + simpleFormatBirthday.format(birthday.getTime()));
    }

    //method to display User information
    public String toString() {
        return "User{" +
                "name='" + firstName + " " + lastName + '\'' +
                ", birthday=" + birthday +
                ", userNumber=" + userNumber +
                ", reservations=" + reservations +
                '}';
    }
}
