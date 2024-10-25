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


/****************************************************************
 *                     		 Setters                            *
 ****************************************************************/

    //Set Hotel

    //Set Hotel Name

    //Set Signed in Status



/****************************************************************
 *						Room Management							*
 ****************************************************************/




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