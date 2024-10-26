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

        HotelManager.createHotel(hotelName);
        
        
        /*
        // Step 1: Create a hotel and add it to the database
        Hotel hotel = new Hotel(hotelName);
        addHotel(hotel);
        
        // Step 2: Create rooms and add them to the database
        for (int i = 1; i <= numberOfRooms; i++) {
            // Example room creation with varying data
            String bedType = (i % 2 == 0) ? "Queen" : "King"; // Alternating bed types
            Room room = new Room(i, (i % 2) + 1, bedType, "Room " + i + " description");
            addRoom(room);
        }

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
