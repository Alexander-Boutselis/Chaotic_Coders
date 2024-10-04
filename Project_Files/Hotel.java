//Hotel.j

/*************************************
*Hotel Class should include:         *
* Total # of Rooms                   *
* Array of Reserved Rooms            *
* Array of Users                     *
*Functions:                          *
* User Shell                         *
* Sign in                            *
* Make Reservation                   *
* Modify Reservation                 *
* Modify Hotel Room (Admin Only)     *
* Add Room                           *
* Modify Room                        *
* Print Rooms List (Admin)           *
**************************************/
import java.util.ArrayList;


public class Hotel{

	//Public:
	public String hotel_Name = "Chaotic Coder Inn";
	public int total_Rooms;
	public ArrayList<Room> hotel_Rooms;
	public User current_User;
	public boolean signed_In;
	public int availible_Rooms;
	public ArrayList<Reservation> all_Reservations;


	//Private:
	private ArrayList<User> all_Users;//Might need to move to main
	private String manager_Confrimation;

	//First Run Constructor
	public Hotel(){
		//Default will be 10 Rooms and users
		total_Rooms = 10;
		availible_Rooms = total_Rooms;
		hotel_Rooms = new ArrayList<>();
		all_Users = new ArrayList<>();
		current_User = new User(1);
		signed_In = false;
		all_Users.add(current_User);
		all_Reservations = new ArrayList<>();
	}

	//Program has been run before Constructor WIP
	public Hotel(int tmp){
		//Load stored data
		loadData();

	}

	private void loadData(){
		//Load Number of Rooms
		//Load Array of Users from data
		//Load Reservations from data
		//Load number of rooms from data
		//Load Array of hotel rooms from data
		//Load number of unavailable rooms
		//Load number of available rooms
		//Load

		return;
	}


	public void printHotel(){
        System.out.println("Hotel: " + hotel_Name);
        System.out.println("Total rooms: " + total_Rooms); 
		return;
	}


	public void printCurrentUser(){
        System.out.println("Current User: "+ current_User.getName());
        return;
	}

	public void setCurrentUser(User new_User){
		current_User = new_User;
		return;
	}

	public void addReservation(Reservation reservation, int rooms) {
		all_Reservations.add(reservation);
		availible_Rooms = availible_Rooms - rooms;
	}

	public void removeReservation(Reservation reservation, int rooms) {
		all_Reservations.remove(reservation);
		availible_Rooms = availible_Rooms + rooms;
	}
}

                                                    