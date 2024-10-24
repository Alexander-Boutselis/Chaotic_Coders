//Database.java


import java.util.ArrayList;


/****************************************************************
 *						Database Class							*
 ****************************************************************/
public class Database { //Currently where data is stored, will eventually be replace with Database calls and returns



	/*Variables:
        List of All Hotels
        List of All Users
        List of All Reservations
    */
    
    /*Functions:
       	Add/Remove Hotel
       	Add/Remove User
       	Add/Remove Reservation
       	Get Hotel
       	Get User
       	Get Reservation
        
    */









	//Public

	//Private
	private ArrayList<Hotel> allHotels;
	private ArrayList<User> allUsers;
	private ArrayList<Reservation> allReservations;
	

	//private AccountManager accountManager;
	//private ReservationManager reservationManager;


	//Database constructor
	public Database(){
		allHotels = new ArrayList<Hotel>();
		allUsers = new ArrayList<User>();
		allReservations = new ArrayList<Reservation>();
	}


	
	/****************************************************************
	 *							Adders								*
	 ****************************************************************/

	//Add a Hotel object to the list
    public void addHotel(Hotel hotel) {
        allHotels.add(hotel);
    }

    //Add a User object to the list
    public void addUser(User user) {
        allUsers.add(user);
    }

    //Add a Reservation object to the list
    public void addReservation(Reservation reservation) {
        allReservations.add(reservation);
    }

	/****************************************************************
	 *							Removers							*
	 ****************************************************************/

	//Remove a Hotel object from the list by matching with equalTo()
    public void removeHotel(Hotel hotel) {
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).equalTo(hotel)) {
                allHotels.remove(i);
                break; //Stop after removing the first match
            }
        }
    }

    //Remove a User object from the list by matching with equalTo()
    public void removeUser(User user) {
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).equalTo(user)) {
                allUsers.remove(i);
                break; //Stop after removing the first match
            }
        }
    }

    //Remove a Reservation object from the list by matching with equalTo()
    public void removeReservation(Reservation reservation) {
        for (int i = 0; i < allReservations.size(); i++) {
            if (allReservations.get(i).equalTo(reservation)) {
                allReservations.remove(i);
                break; //Stop after removing the first match
            }
        }
    }


	/****************************************************************
	 *							Getters								*
	 ****************************************************************/

	//Get a Hotel object by comparing with another Hotel object using equalTo()
    public Hotel getHotel(Hotel searchHotel) {
        for (Hotel hotel : allHotels) {
            if (hotel.equalTo(searchHotel)) {
                return hotel;
            }
        }
        return null; //If no matching object is found
    }

    // Get a User object by comparing with another User object using equalTo()
    public User getUser(User searchUser) {
        for (User user : allUsers) {
            if (user.equalTo(searchUser)) {
                return user;
            }
        }
        return null; //If no matching object is found
    }

    //Get a Reservation object by comparing with another Reservation object using equalTo()
    public Reservation getReservation(Reservation searchReservation) {
        for (Reservation reservation : allReservations) {
            if (reservation.equalTo(searchReservation)) {
                return reservation;
            }
        }
        return null; //If no matching object is found
    }

	

	/****************************************************************
	 *							End								*
	 ****************************************************************/


}