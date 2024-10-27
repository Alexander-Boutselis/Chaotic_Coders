//DatabaseManager.java

import java.util.ArrayList;



public class DatabaseManager {


	/*Functions:
		Initialize
		Add/Remove Hotel
		Get Hotel
		Add/Remove User
		Get User
        Get Current User
        Sign-in/out
	*/


    //Single Instance of Database
    private static Database database = new Database();


    // Private constructor to prevent instantiation
    private DatabaseManager() {}


	/****************************************************************
	 *					Initialize Database							*WIP
	 ****************************************************************/
    // Initialization method to set up a hotel with n rooms
    public static void initializeDatabase(String hotelName) {

        //Create Initial Empty Hotel
        HotelManager.createHotel(hotelName);

        //Set it as Current Hotel
        setCurrentHotel(getHotel(hotelName));

        //Loop to Generate Rooms
        //Call a test class
        

        
/*
        System.out.println("Initialization complete. Added " + numberOfRooms + " rooms to " + hotelName);
*/
    }

	/****************************************************************
	 *						Hotel Data								*
	 ****************************************************************/

    // Static method to add a hotel
    public static void addHotel(Hotel hotel) {
        database.addHotel(hotel);
    }

    // Static method to remove a hotel
    public static void removeHotel(String hotel) {
        database.removeHotel(hotel);
    }

    // Static method to get a hotel
    public static Hotel getHotel(String searchHotel) {
        return database.getHotel(searchHotel);
    }

    //Get Current Hotel
    public static Hotel getCurrentHotel(){
        return database.getCurrentHotel();
    }

    public static ArrayList<Hotel> getAllHotels(){
        return database.getAllHotels();
    }

    //Set Current Hotel
    public static void setCurrentHotel(Hotel hotel){
        database.setCurrentHotel(hotel);
    }


	/****************************************************************
	 *						User Data								*
	 ****************************************************************/
    // Static method to add a user
    public static void addUser(User newUser) {
        database.addUser(newUser);
    }

    // Static method to remove a user
    public static void removeUser(String username) {
        database.removeUser(username);
    }

    // Static method to get a user
    public static User getUser(User searchUsername) {
        return database.getUser(searchUsername);
    }

    //Get all Users
    public static ArrayList<User> getAllUsers(){
        return database.getAllUsers();
    }

    //Check if Account name is Unique (isUniqueName)
   public static boolean isUniqueName(String newUsername){
        for (User user : database.getAllUsers()){
            if(user.getUsername().equals(newUsername)){
                return false;
            }
        }
        return true;
   }

   //Get the next unused Employee Number
   public static int nextEmployeeNumber(){
    int largestEmployeeNumber = 0;
    for (User manager : database.getAllUsers()){
        if (manager instanceof Manager){
            int managerEmployeeNumber = ((Manager) manager).getEmployeeNumber();
            if(largestEmployeeNumber < managerEmployeeNumber){
                largestEmployeeNumber = managerEmployeeNumber;
            }
        }
    }
    return largestEmployeeNumber + 1;
   }

    /****************************************************************
     *                      Current User                            *
     ****************************************************************/

    //Sign in User
    public static void signIn(User user){
        database.setCurrentUser(user);
        database.setSignedInStatus(true);
    }

    //Sign out User
    public static void signOut(){
        database.setCurrentUser(null);
        database.setSignedInStatus(false);
    }


    public static User getCurrentUser(){
        return database.getCurrentUser();
    }

    public static boolean isSignedIn(){
        return database.isSignedIn();
    }


    /****************************************************************
     *                           End                                *
     ****************************************************************/
}
