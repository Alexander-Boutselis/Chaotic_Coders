//RoomManager.java





/****************************************************************
 *                     Room Manager                             *
 ****************************************************************/
public class RoomManager {
	
    /*Functions Needed:
		Create/Destroy Hotel Room
		Get/Set Room #
		Get Next Room #
		Get/Set Bed Type
		Get/Set Number of Beds
		Get/Set Price Per Night -Calc Price Per Night
		Get/Set Room Availiblity
		Get/Set Reservation Index Number (Reservations are stored in the database and using this index number we can find the reservation associated with this room)
		isEqualTo()
		Print Room Info
	*/



    // Private constructor to prevent instantiation
    private RoomManager() {}

/****************************************************************
 *                  Create/Destroy Room                         *
 ****************************************************************/

    public static void createRoom(Hotel hotel, int roomNumber, int numOfBeds, String bedType, double pricePerNight, String roomDescription){
    	Room newRoom = new Room(roomNumber, numOfBeds, bedType, pricePerNight, roomDescription);
    	hotel.addRoom(newRoom);
    }


/****************************************************************
 *                      Calculate Price                         *
 ****************************************************************/


/****************************************************************
 *                           End                                *
 ****************************************************************/

}