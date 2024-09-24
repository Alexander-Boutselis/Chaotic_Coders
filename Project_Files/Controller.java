//Controller.j

/*************************************
*Controller Class should include:    *
* Total # of Rooms                   *
* Array of Reserved Rooms            *
* Array of Users                     *
* Manager Confirmation #             *
*                                    *
*Functions:                          *
* User Shell                         *
* Sign in                            *
* Create Account                     *
* Modify Account                     *
* Make Reservation                   *
* Modify Reservation                 *
* Modify Hotel Room (Admin Only)     *
* Search                             *
* Add Room                           *
* Modify Room                        *
* Print Rooms List (Admin)           *
**************************************/


public class Controller{

	//Declare Variables
	//Public:
	public Room[] hotel_Rooms;
	public int total_Rooms;
	public int availible_Rooms;
	public int unavailible_Rooms;

	//Protected:
	protected Reservation[] all_Reservations;

	//Private:
	private User[] all_Users;//Might need to move to main
	private String manager_Confrimation;

	//First Run Constructor
	public Controller(){
		//Default will be 10 Rooms and users
		total_Rooms = 10;
		availible_Rooms = total_Rooms;
		unavailible_Rooms = 0;
		manager_Confrimation = "Manager";
		hotel_Rooms = new Room[10];
		all_Users = new User[10];
		all_Reservations = new Reservation[10];
	}

	//Program has been run before Constructor
	public Controller(int tmp){
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
}

                                                    