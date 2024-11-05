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
     *                          Methods                             *
     ****************************************************************/

    /********************************
     *           Example            *
     ********************************/

    /****************************************************************
     *                         User Info Methods                    *
     ****************************************************************/

    /********************************
     *         Set/Get Name         *
     ********************************/
    //Set First Name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //Set Last Name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //Get First Name
    public String getFirstName(){
        return firstName;
    }

    //Get Last Name
    public String getLastName(){
        return lastName;
    }

    //Get Full Name
    public String getName() {
        String nameCombiner;
        nameCombiner = firstName + " " + lastName;
        return nameCombiner;
    }

    /********************************
     *       Set/Get Birthday       *
     ********************************/
    //Set Birthday
    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    //Get Birthday
    public Calendar getBirthday() {
        return birthday;
    }

    /********************************
     *          Print Info          *
     ********************************/
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
     *                   Username/Password Methods                  *
     ****************************************************************/

    /********************************
     *       Set/Get Username       *
     ********************************/
    //Set Username
    public void setUsername(String username) {
        this.username = username;
    }

    //Get Username
    public String getUsername() {
        return username;
    }

    /********************************
     *       Set/Get Password       *
     ********************************/
    //Set Password
    public void setPassword(String password) {
        this.password = password;
    }

    //Get Password
    public String getPassword() {
        return password;
    }


    /****************************************************************
     *                     Reservation Methods                      *
     ****************************************************************/

    /********************************
     *      Get Reservation(s)      *
     ********************************/
    //Get Reservation based on its Reservation Number
    public int getReservationNumber(int searchReservationNumber){
        for(int reservationNumber : reservationNumbers){
            return reservationNumber;
        }
        return -1;
    }

    //Get All ReservationNumbers
    public ArrayList<Integer> getAllreservationNumbers() {
        return reservationNumbers;
    }

    /********************************
     *    Add/Remove Reservation    *
     ********************************/
    //Add Reservation Number
    public void addReservation(int reservationNumber) {
        reservationNumbers.add(reservationNumber);
    }

    //Remove Reservation Number
    public void removeReservation(int reservationNumber) {
        for(int i = 0; i < reservationNumbers.size(); i++){
            if(reservationNumbers.get(i) == reservationNumber){
                reservationNumbers.remove(i);
                return;
            }
        }
    }



    /****************************************************************
     *                            Equals                            *
     ****************************************************************/

    /********************************
     *           isEqualTo          *
     ********************************/
    //Checks if 2 Users names are equal
    public boolean isEqualTo(User otherUser){
        if (username.equals(otherUser.username)) {
            return true;
        }else{
            return false;
        }
    }
    

/****************************************************************
 *                             End                              *
 ****************************************************************/
}//End of User Class
