//DatabaseManager.java




public class DatabaseManager {


	/*Functions:
		Initialize
		Add/Remove Hotel
		Get Hotel
		Add/Remove User
		Get User
		Add/Remove Reservation
		Get Reservation
	*/



    private static Database database = new Database();


    // Private constructor to prevent instantiation
    private DatabaseManager() {}


	/****************************************************************
	 *					Initialize Database							*
	 ****************************************************************/
    // Initialization method to set up a hotel with n rooms
    public static void initializeData(String hotelName, int numberOfRooms) {
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
    }

	/****************************************************************
	 *						Hotel Data								*
	 ****************************************************************/

    // Static method to add a hotel
    public static void addHotel(Hotel hotel) {
        database.addHotel(hotel);
    }

    // Static method to remove a hotel
    public static void removeHotel(Hotel hotel) {
        database.removeHotel(hotel);
    }

    // Static method to get a hotel
    public static Hotel getHotel(Hotel searchHotel) {
        return database.getHotel(searchHotel);
    }




	/****************************************************************
	 *						User Data								*
	 ****************************************************************/

    // Static method to add a user
    public static void addUser(User user) {
        database.addUser(user);
    }

    // Static method to remove a user
    public static void removeUser(User user) {
        database.removeUser(user);
    }

    // Static method to get a user
    public static User getUser(User searchUser) {
        return database.getUser(searchUser);
    }


    /****************************************************************
	 *						Reservation Data						*
	 ****************************************************************/

    // Static method to add a reservation
    public static void addReservation(Reservation reservation) {
        database.addReservation(reservation);
    }

    // Static method to remove a reservation
    public static void removeReservation(Reservation reservation) {
        database.removeReservation(reservation);
    }

    // Static method to get a reservation
    public static Reservation getReservation(Reservation searchReservation) {
        return database.getReservation(searchReservation);
    }


}
