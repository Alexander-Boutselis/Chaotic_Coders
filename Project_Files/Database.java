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



public class Database { //Once Database is implemented make a UTILITY Class Static

	//Public
	public Hotel hotel;
	public ArrayList<Reservation> allReservations;
	public Calendar birthday;
	public ArrayList<User> allUsers;
	public AccountManager accountManager;


	//Private
	private ReservationManager reservationManager;
	



	public Database(){

		//Check if hotel object exists in database

        //if Hotel does not exist
		//Create Hotel
		hotel = new Hotel("Chotic Coder Inn", 100);

		//Create List of Users
		allUsers = new ArrayList<User>();

		//Create List of Reservations
		allReservations = new ArrayList<Reservation>();

		//Create Controller Classes
		accountManager = new AccountManager(this);
		reservationManager = new ReservationManager(this);

		//Create "Signed Out" User 0 and set it to Hotel Current User
		//birthday.getInstance();
		//accountManager.createUser(" ", " ", birthday, " ");
		//allUsers.add(user);
		//hotel.setCurrentUser(null);

		
	}

	public void addUser(User newUser){

		allUsers.add(newUser);

		return;

	}


	
}