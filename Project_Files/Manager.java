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


public class Manager extends User{

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
}




/*
Should reservations be stored in the User or should the user have a pointer to their reservations in the hotel reservation arraylist?



*/