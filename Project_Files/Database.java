//Database.java




/***********************************************************
 * Database Class
 * 
 * Class to hold important Data
 * Eventually will be linked to AWS Database
 * Contains:
 * Hotel
 * Rooms[]
 * Users[]
 * Reservations[]
 * 
 * Initiates Controller Classes:
 * Account Manager
 * Room Manager
 * Reservation Manager
 * Search
 * Calendar - Maybe
************************************************************/
import java.util.ArrayList;
import java.util.Calendar;



public class Database {

	//Public
	public Hotel hotel;
	public ArrayList<Reservation> all_Reservations;
	public Calendar birthday;


	//Private
	private ArrayList<User> all_Users;
	private AccountManager accountManager;
	private ReservationManager reservationManager;
	private RoomManager roomManager;//To be implemented



	public Database(){
		//Create Hotel
		hotel = new Hotel("Chotic Coder Inn", 10);

		//Create List of Users
		all_Users = new ArrayList<User>();

		//Create "Signed Out" User 0 and set it to Hotel Current User
		birthday.getInstance();
		User user = new User(" ", " ", birthday, 00000, " ");
		all_Users.add(user);
		hotel.setCurrentUser(all_Users.get(0));

		//Create Controller Classes
		accountManager = new AccountManager();
		reservationManager = new ReservationManager();
		roomManager = new RoomManager();
	}



	
}