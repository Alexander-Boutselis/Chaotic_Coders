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
import java.util.ArrayList;

public class User {
    
    private String name;
    private Date birthday;
    private int userNumber;
    private String password;
    private ArrayList<String> reservations;
    private ArrayList<String> notifications;

    // Constructor
    public User(String name, Date birthday, int userNumber, String password) {
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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
