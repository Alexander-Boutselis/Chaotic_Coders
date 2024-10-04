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


import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;

public class User {
    
    private String name; //We should seperate first and last name into their own Variables and seperate the prompt. This will intigrate with the GUI easier
    private Calendar birthday = Calendar.getInstance();
    private int userNumber;
    private String password;
    private ArrayList<String> reservations;
    private ArrayList<String> notifications;


    // Testing Default Constructor
    public User() {
        name = "Tina TestUser";
        birthday.set(2000, Calendar.APRIL, 13);
        userNumber = 00001;
        password = "password";
        reservations = new ArrayList<>();
        notifications = new ArrayList<>();
    }

    //Signed out User
    public User(int num) {
        if (num == 1) {
            name = " ";
            birthday.getInstance();
            userNumber = 00000;
            password = " ";
            reservations = new ArrayList<>();
            notifications = new ArrayList<>();
            
        }
    }


    // Constructor
    public User(String name, Calendar birthday, int userNumber, String password) {
        this.name = name;
        this.birthday = birthday;
        this.userNumber = userNumber;
        this.password = password;
        this.reservations = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    public void addNotification(String notification) {
        this.notifications.add(notification);
    }

    public void removeNotification(String notification) {
        this.notifications.remove(notification);
    }

    //method to display User information
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", userNumber=" + userNumber +
                ", reservations=" + reservations +
                ", notifications=" + notifications +
                '}';
    }
}
