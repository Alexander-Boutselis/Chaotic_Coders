//Database.java


import java.util.ArrayList;


/****************************************************************
 *						Database Class							*
 ****************************************************************/
public class Database { //Currently where data is stored, will eventually be replace with Database calls and returns

	/*Variables:
        List of All Hotels
        List of All Users
        Current Hotel
        Current User
        Signed-in Status
    */
    
    /*Functions:
       	Add/Remove Hotel
        Get/Set Current Hotel
        Get Hotel by name
        Get All Hotels
        Print Current Hotel
        Add/Remove User
        Get/Set Current User
        Get User by Username
        Get All User
        Print Current User
        Get/Set Sign-in Status
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
        currentHotel = null;
        signedIn = false;
	}

	
    /****************************************************************
     *                        Hotel Methods                         *
     ****************************************************************/

    /********************************
     *       Add/Remove Hotel       *
     ********************************/
    //Add a Hotel object to the list
    public void addHotel(Hotel hotel) {
        allHotels.add(hotel);
    }

    //Remove a Hotel object from the list by matching with isEqualTo()
    public void removeHotel(String hotelName) {
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).equals(hotelName)) {
                allHotels.remove(i);
                break; //Stop after removing the first match
            }
        }
    }


    /********************************
     *        Set/Get Hotel(s)      *
     ********************************/
    //Set Current Hotel
    public void setCurrentHotel(Hotel hotel){
        currentHotel = hotel;
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

    /********************************
     *          Print Hotel         *
     ********************************/
    //Print Current Hotel
    public void printCurrentHotel(){
        if(currentHotel != null){
        System.out.println("Current Hotel: "+ currentHotel.getHotelName());
        }
        return;
    }


    /****************************************************************
     *                        User Methods                          *
     ****************************************************************/

    /********************************
     *        Add/Remove User       *
     ********************************/
    //Add a User object to the list
    public void addUser(User user) {
        allUsers.add(user);
    }

    //Remove a User object from the list by matching with isEqualTo()
    public void removeUser(String searchUsername) {
        for(User user : allUsers){
            if (user.getUsername().equals(searchUsername))
                allUsers.remove(user);
                break; //Stop after removing the first match
        }
    }

    /********************************
     *         Set/Get User(s)      *
     ********************************/
    //Set Current User
    public void setCurrentUser(User user){
        currentUser = user;
    }

    //Get Current User
    public User getCurrentUser(){
        return currentUser;
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

    /********************************
     *          Print User          *
     ********************************/
    //Print Current User
    public void printCurrentUser(){
        if(currentUser != null){
        System.out.println("Current User: "+ currentUser.getName());
        }else{

        }
        return;
    }

    /****************************************************************
     *                       Signed-In Methods                      *
     ****************************************************************/

    /********************************
     *        Set/Get Status        *
     ********************************/
    //Set Signed-In Status
    public void setSignedInStatus(boolean status){
        signedIn = status;
    }

    //Get Signed in Status
    public boolean isSignedIn(){
        return signedIn;
    }

    /****************************************************************
     *                      Connect to Database                     *
     ****************************************************************/

    /********************************
     *            Connect           *
     ********************************/

    /********************************
     *        Query Database        *
     ********************************/

    /********************************
     *       Send to Database       *
     ********************************/


	/****************************************************************
	 *							End			    					*
	 ****************************************************************/
}//End of Database Class