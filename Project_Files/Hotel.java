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
	public String hotelName;
	public int totalRooms;
	public ArrayList<Room> hotelRooms;
	public int availibleRooms;
	public User currentUser;


	//Private:
	private boolean signedIn;
	private RoomManager roomManager;//To be implemented

	//REMOVE, DO NOT USE IN CURRENT STATE
	public Hotel(){
		//Default will be 10 Rooms and users
		totalRooms = 10;
		availibleRooms = totalRooms;
		hotelRooms = new ArrayList<>();
		currentUser = null;
		signedIn = false;
		hotelName = "Chaotic Coder Inn";
	}

	public Hotel(String hotelName, int totalRooms){
		this.hotelName = hotelName;
		this.totalRooms = totalRooms;
		availibleRooms = totalRooms;
		hotelRooms = new ArrayList<Room>();
		roomManager = new RoomManager();
		currentUser = null;
	}


	public void printHotel(){
        System.out.println("Hotel: " + hotelName);
        System.out.println("Total rooms: " + totalRooms); 
		return;
	}


	public void printCurrentUser(){
		if(currentUser != null){
        System.out.println("Current User: "+ currentUser.getName());
		}
        return;
	}

	public void setCurrentUser(User newUser){
		currentUser = newUser;
		return;
	}


	public void setCurrentUser(Manager newUser){
		currentUser = newUser;
		return;
	}

	public User getCurrentUser() {
		return currentUser;
	}


	public void addReservation(Reservation reservation, int rooms) {
		availibleRooms = availibleRooms - rooms;
	}

	public void removeReservation(Reservation reservation, int rooms) {
		availibleRooms = availibleRooms + rooms;
	}
}

                                                    