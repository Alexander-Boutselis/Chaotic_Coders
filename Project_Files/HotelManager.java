//HotelManager.java

import java.util.ArrayList;


public class HotelManager {
	

	/*Functions:
		Get Hotel
		Get/Set Hotel Name
		Account Manager
		isSignedIn()
		Add/Remove Rooms
		Calculate Total Rooms
		Find Next Hotel Room #
		Add/Remove Reservation
		Calc Finances
		isEqualTo()
		Print Hotel Info
	*/


    // Private constructor to prevent instantiation
    private HotelManager() {}

/****************************************************************
 *                     Create/Destroy Hotel                     *
 ****************************************************************/

    //Create Empty Hotel
    public static void createHotel(String hotelName){
        Hotel newHotel = new Hotel(hotelName);
        DatabaseManager.addHotel(newHotel);
    }



/****************************************************************
 *		                      Getters 		                    *
 ****************************************************************/

    //Get Hotel by searching its name
    public static Hotel getHotel(String searchHotelName){
    	return DatabaseManager.getHotel(searchHotelName);
    }

    //Get All Hotels in Database
    public static ArrayList<Hotel> getAllHotels(){
        return DatabaseManager.getAllHotels();
    }

    //Get Hotel Name
    public static String getHotelName(){
    	return DatabaseManager.getCurrentHotel().getHotelName();
    }

    //Get Current Hotel
    public static Hotel getCurrentHotel(){
        return DatabaseManager.getCurrentHotel();
    }

    //Get All Rooms from Current Hotel
    public static ArrayList<Room> getAllCurrentHotelRooms(){
        //Get Current Hotel
        Hotel currHotel = DatabaseManager.getCurrentHotel();
        return currHotel.getAllRooms();
    }

    //Get Total Number of Rooms
    public static int getTotalNumberOfRooms(){
        return getAllCurrentHotelRooms().size();
    }


/****************************************************************
 *                     		 Setters                            *
 ****************************************************************/

    //Set Current Hotel
    public static void setCurrentHotel(Hotel hotel){
        DatabaseManager.setCurrentHotel(hotel);
    }

    //Set Current Hotel Name
    public static void setHotelName(String newHotelName){
        DatabaseManager.getCurrentHotel().setHotelName(newHotelName);
    }



/****************************************************************
 *                            Adders                            *
 ****************************************************************/

    //Add Hotel to Database
    public static void addHotel(Hotel newHotel){
        DatabaseManager.addHotel(newHotel);
    }

    //Add Room to Current Hotel
    public static void addRoomToCurrentHotel(Room newRoom){
        DatabaseManager.getCurrentHotel().addRoom(newRoom);
    }


/****************************************************************
 *                            Removers                          *
 ****************************************************************/

    //Remove Hotel from Database
    public static void removeHotel(String searchHotelName){
        DatabaseManager.removeHotel(searchHotelName); 
    }

    //Remove Room from Current Hotel
    public static void removeRoomFromCurrentHotel(Room newRoom){
        DatabaseManager.getCurrentHotel().removeRoom(newRoom.getRoomNumber());
    }


/****************************************************************
 *                          Booleans                            *
 ****************************************************************/

    //Get Signed in Status
    public static boolean getSignedInStatus(){
        return DatabaseManager.isSignedIn();
    }

    //Hotel Has Unique Name
    public static boolean isUniqueName(String checkName){
        for(Hotel hotel : getAllHotels()){
            if (hotel.getHotelName().equals(checkName)){
                return false;
            }
        }
        return true;
    }

/****************************************************************
 *						Reservation Data						*
 ****************************************************************/

    // Static method to add a reservation
    public static void addReservation(Hotel hotel, Reservation reservation) {
        hotel.addReservation(reservation);
    }

/*
    // Static method to remove a reservation
    public static void removeReservation(Hotel hotel, Reservation reservation) {
        hotel.removeReservation(reservation);
    }

    // Static method to get a reservation
    public static Reservation getReservation(Hotel hotel, Reservation searchReservation) {
        return hotel.getReservation(searchReservation);
    }
*/

 /****************************************************************
  *                    Calculate Finances 		                 *
  ****************************************************************/

    //Calculate Total Earnings

    //Calculate Planned Earnings

 /****************************************************************
  *                    		  Print  			                 *
  ****************************************************************/

    //Print All Rooms - Call Room Manager to Print each Room
    public static String getAllCurrentHotelRoomsInfo(){
        
        StringBuilder receipt = new StringBuilder();

        for(Room room : getAllCurrentHotelRooms()){
             receipt.append(room.getRoomInfo());
        }


        return receipt.toString();
    }

    //toString Hotel info
    public static String getCurrentHotelInfo(){
        return getCurrentHotel().getHotelInfo();
    }


    //toString Hotel and All its Rooms
    public static String getCurrentHotelAndRoomsInfo(){
        StringBuilder receipt = new StringBuilder();


        receipt.append("\n--------------------------------\n");

        receipt.append(getCurrentHotelInfo());
        receipt.append(getAllCurrentHotelRoomsInfo());

        receipt.append("\n--------------------------------\n");
        


        return receipt.toString();
    }

    //Print Hotel
    public static void printCurrentHotelInfo(){
        System.out.println(getCurrentHotelInfo());
    }

    //Print Hotel Finances

 
/****************************************************************
 *                     			End                             *
 ****************************************************************/
}