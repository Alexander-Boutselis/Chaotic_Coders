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


public class Hotel{

	//Declare Variables
	//Public:
	public Room[] hotel_Rooms;
	public int total_Rooms;
	public int availible_Rooms;
	public User current_User;
	public String hotel_Name = "Chaotic Coder Inn";

	//Protected:
	protected Reservation[] all_Reservations;

	//Private:
	private User[] all_Users;//Might need to move to main
	private String manager_Confrimation;

	//First Run Constructor
	public Hotel(){
		//Default will be 10 Rooms and users
		total_Rooms = 10;
		availible_Rooms = total_Rooms;
		manager_Confrimation = "Manager";
		hotel_Rooms = new Room[10];
		current_User = new User(1);
		all_Users = new User[10];
		all_Reservations = new Reservation[10];
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

}

                                                    