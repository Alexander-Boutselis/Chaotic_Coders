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
	public String hotel_Name;
	public int total_Rooms;
	public ArrayList<Room> hotel_Rooms;
	public int availible_Rooms;


	//Private:
	private boolean signed_In;
	private User current_User;

	//First Run Constructor
	public Hotel(){
		//Default will be 10 Rooms and users
		total_Rooms = 10;
		availible_Rooms = total_Rooms;
		hotel_Rooms = new ArrayList<>();
		current_User = new User(1);
		signed_In = false;
		hotel_Name = "Chaotic Coder Inn";
	}

	public Hotel(String hotel_Name, int total_Rooms){
		this.hotel_Name = hotel_Name;
		this.total_Rooms = total_Rooms;
		availible_Rooms = total_Rooms;
		hotel_Rooms = new ArrayList<Room>();

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
		availible_Rooms = availible_Rooms - rooms;
	}

	public void removeReservation(Reservation reservation, int rooms) {
		availible_Rooms = availible_Rooms + rooms;
	}
}

                                                    