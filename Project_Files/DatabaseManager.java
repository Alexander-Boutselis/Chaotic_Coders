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
    public static void initializeDatabase(String hotelName, int numberOfRooms) {

        //Create Initial Empty Hotel
        HotelManager.createHotel(hotelName);
        Hotel hotel = HotelManager.getHotel(hotelName);

        //Set it as Current Hotel
        setCurrentHotel(getHotel(hotelName));

        //Loop to Generate Rooms
        //Loop to Generate Rooms
        for (int i = 0; i < numberOfRooms; i++){
            RoomManager.createRoom(2, "queen","");
            RoomManager.createRoom(3, "twin","");
            RoomManager.createRoom(1, "king","");
        }       

        setCurrentHotel(null);
        
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
    public static User getUser(String searchUsername) {
        return database.getUser(searchUsername);
    }

    //Get all Users
    public static ArrayList<User> getAllUsers(){
        return database.getAllUsers();
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
