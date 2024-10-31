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
        Current User
        Signed-in Status
    */
    
    /*Functions:
       	Add/Remove Hotel
       	Add/Remove User
       	Add/Remove Reservation
        Get/Set Current User
        Get/Set Sign-in Status
       	Get Hotel
       	Get User
       	Get Reservation  
        Print Current User
    */


	//Private
	private ArrayList<Hotel> allHotels;
    private ArrayList<User> allUsers;
    private Hotel currentHotel;
    private User currentUser;
    private boolean signedIn;



	//Database constructor
	public Database(){
		allHotels = new ArrayList<Hotel>();
        allUsers = new ArrayList<User>();
        currentUser = null;
        signedIn = false;
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


	/****************************************************************
	 *							Removers							*
	 ****************************************************************/

	//Remove a Hotel object from the list by matching with isEqualTo()
    public void removeHotel(String hotelName) {
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).equals(hotelName)) {
                allHotels.remove(i);
                break; //Stop after removing the first match
            }
        }
    }

    //Remove a User object from the list by matching with isEqualTo()
    public void removeUser(String searchUsername) {
        for(User user : allUsers){
            if (user.getUsername().equals(searchUsername))
                allUsers.remove(user);
                break; //Stop after removing the first match
        }
    }



    /****************************************************************
     *                          Setters                             *
     ****************************************************************/

    //Set Current User
    public void setCurrentUser(User user){
        currentUser = user;
    }

    //Set Signed-In Status
    public void setSignedInStatus(boolean status){
        signedIn = status;
    }

    //Set Current Hotel
    public void setCurrentHotel(Hotel hotel){
        currentHotel = hotel;
    }

	/****************************************************************
	 *							Getters								*
	 ****************************************************************/

    //Get Current User
    public User getCurrentUser(){
        return currentUser;
    }

    //Get Signed in Status
    public boolean isSignedIn(){
        return signedIn;
    }

    //Get Current Hotel
    public Hotel getCurrentHotel(){
        return currentHotel;
    }

	//Get a Hotel object by comparing with another Hotel object using isEqualTo()
    public Hotel getHotel(String searchHotelName) {
        for (Hotel hotel : allHotels) {
            if (hotel.getHotelName().equals(searchHotelName)) {
                return hotel;
            }
        }
        return null; //If no matching object is found
    }

    //Get all Hotels
    public ArrayList<Hotel> getAllHotels(){
        return allHotels;
    }

    // Get a User object by comparing with another User object using isEqualTo()
    public User getUser(String searchUser) {
        for (User user : allUsers) {
            if (user.getUsername().equals(searchUser)) {
                return user;
            }
        }
        return null; //If no matching object is found
    }

        
    //Get all Users
    public ArrayList<User> getAllUsers(){
        return allUsers;
    }

    /****************************************************************
     *                          Print                               *
     ****************************************************************/
    
    //Print Current User
    public void printCurrentUser(){
        if(currentUser != null){
        System.out.println("Current User: "+ currentUser.getName());
        }
        return;
    }

    //Print Current Hotel
    public void printCurrentHotel(){
        if(currentHotel != null){
        System.out.println("Current Hotel: "+ currentHotel.getHotelName());
        }
        return;
    }
	/****************************************************************
	 *							End			    					*
	 ****************************************************************/
}