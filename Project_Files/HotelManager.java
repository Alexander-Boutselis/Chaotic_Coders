//HotelManager.java



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

    //Get Hotel Name
    public static String getHotelName(Hotel hotel){
    	return hotel.getHotelName();
    }


    //Get Signed in Status
    public static boolean getSignedInStatus(){
        return DatabaseManager.isSignedIn();
    }

/****************************************************************
 *                     		 Setters                            *
 ****************************************************************/

    //Set Current Hotel
    public static void setCurrentHotel(Hotel hotel){
        DatabaseManager.setCurrentHotel(hotel);
    }

    //Set Current Hotel Name
    public static void setHotelName(Hotel hotel, String newHotelName){
        hotel.setHotelName(newHotelName);
    }



/****************************************************************
 *                            Adders                            *
 ****************************************************************/

    //Add Hotel to Database
    public static void addHotel(Hotel newHotel){
        DatabaseManager.addHotel(newHotel);
    }


/****************************************************************
 *                            Removers                          *
 ****************************************************************/

    //Remove Hotel from Database
    public static void removeHotel(String searchHotelName){
        DatabaseManager.removeHotel(searchHotelName); 
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

    //Print Hotel info

    //Print Hotel Finances

 
/****************************************************************
 *                     			End                             *
 ****************************************************************/
}