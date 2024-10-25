//Hotel.java


import java.util.ArrayList;


public class Hotel{


	/*Variables:
        Hotel Name
        List of Rooms
		Number of Rooms
		List of Reservation Index Numbers
    */
    
    /*Functions:
        Get/Set Hotel Name
        Get/Set Number of Rooms
        Add/Remove Rooms
        Get Room from List
		Get Reservation
		Get All Reservations
		Print Hotel        
    */


	//Public:

	//Private:
	private String hotelName;
	private int totalRooms;
	private ArrayList<Room> hotelRooms;
	private	ArrayList<Reservation> allReservations;


	//Empty Hotel
	public Hotel(String hotelName){
		//Default
		totalRooms = 0;
		hotelRooms = new ArrayList<>();
		allReservations = new ArrayList<Reservation>();
		this.hotelName = hotelName;
	}

	//Dont Use This one anymore
	public Hotel(String hotelName, int totalRooms){
		this.hotelName = hotelName;
		this.totalRooms = totalRooms;
		hotelRooms = new ArrayList<>();
	}

 /****************************************************************
  *                    		  Getters		                     *
  ****************************************************************/

	public String getHotelName(){
		return hotelName;
	}

	public int getNumOfRooms(){
		return totalRooms;
	}

	public Room getRoom(int searchRoomNumber){
		for (Room room : hotelRooms){
			if (room.getRoomNumber() == searchRoomNumber){
				return room;
			}
		}
		return null; //Failed to find Room with matching Room Number
	}

	public ArrayList<Room> getAllRooms(){
		return hotelRooms;
	}

	public Reservation getReservation(int searchReservationNumber){
		for (Reservation reservation : allReservations){
			if (reservation.getReservationNumber() == searchRoomNumber)
				return reservation;
		}
		return null; //No Reservaiton found
	}


	public ArrayList<Reservaiton> getAllReservations(){
		return allReservations;
	}


 /****************************************************************
  *                    		  Setters		                     *
  ****************************************************************/

	public void setHotelName(String newHotelName){
		hotelName = newHotelName;
	}

	public void setTotalRooms(){//Updates total rooms
		totalRooms = hotelRooms.size();
	}



 /****************************************************************
  *                    		  Adders		                     *
  ****************************************************************/

    //Add a Reservation object to the list
    public void addReservation(Reservation reservation) {
        allReservations.add(reservation);
    }




 /****************************************************************
  *                    		  Removers		                     *
  ****************************************************************/

    //Remove a Reservation object from the list by matching with isEqualTo()
    public void removeReservation(Reservation reservation) {
        for (int i = 0; i < allReservations.size(); i++) {
            if (allReservations.get(i).isEqualTo(reservation)) {
                allReservations.remove(i);
                break; //Stop after removing the first match
            }
        }
    }


 /****************************************************************
  *                    		  Print 		                     *
  ****************************************************************/


	public void printHotel(){
        System.out.println("Hotel: " + hotelName);
        System.out.println("Total rooms: " + totalRooms); 
		return;
	}


	public void addReservation(Reservation reservation, int rooms) {
		availibleRooms = availibleRooms - rooms;
	}

	public void removeReservation(Reservation reservation, int rooms) {
		availibleRooms = availibleRooms + rooms;
	}

	/****************************************************************
	 *							End					 				*
	 ****************************************************************/
}

                                                    