//Hotel.java

package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.util.ArrayList;


public class Hotel{


	/*Variables:
        Hotel Name
        List of Rooms
		List of of Removed Rooms (Room number Stored)
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
	private ArrayList<Room> hotelRooms;
	private ArrayList<Integer> removedRoomNumbers;
	private	ArrayList<Reservation> allReservations;


	//Empty Hotel
	public Hotel(String hotelName){
		//Default
		hotelRooms = new ArrayList<>();
		allReservations = new ArrayList<>();
		removedRoomNumbers = new ArrayList<>();
		this.hotelName = hotelName;
	}

    /****************************************************************
     *                      Get/Set Hotel Info                      *
     ****************************************************************/

    /********************************
     *        Get Hotel Info        *
     ********************************/
	//Get Hotel Name
	public String getHotelName(){
		return hotelName;
	}

	//Get Number of Rooms
	public int getNumOfRooms(){
		return hotelRooms.size();
	}

	/********************************
     *        Set Hotel Info        *
     ********************************/
	//Set Hotel Name
	public void setHotelName(String newHotelName){
		hotelName = newHotelName;
	}

    /********************************
     *          Print Hotel         *
     ********************************/
    //toString Hotel info
    public String getHotelInfo(){

        StringBuilder receipt = new StringBuilder();

        receipt.append("\n");
        receipt.append("Hotel: " + hotelName);
        receipt.append("\n");
        receipt.append("Total rooms: " + getNumOfRooms()); 

		return receipt.toString();
    }

	public void printHotel(){
        System.out.println(getHotelInfo());	
    }


    /****************************************************************
     *                        Hotel Room(s)                         *
     ****************************************************************/

    /********************************
     *       Get Hotel Room(s)      *
     ********************************/
    //Get Room by Room Number
	public Room getRoom(int searchRoomNumber){
		for (Room room : hotelRooms){
			if (room.getRoomNumber() == searchRoomNumber){
				return room;
			}
		}
		return null; //Failed to find Room with matching Room Number
	}

	//Get All Rooms
	public ArrayList<Room> getAllRooms(){
		return hotelRooms;
	}

    /********************************
     *      Add/Remove Room(s)      *
     ********************************/
	//Add Room to this hotel
    public void addRoom(Room newRoom){
    	hotelRooms.add(newRoom);
    }

    //Remove Room from this Hotel
    public void removeRoom(int searchRoomNumber){
    	for (Room room : hotelRooms){
    		if (room.getRoomNumber() == searchRoomNumber){
    			hotelRooms.remove(room);
    		}
    	}

    }

    /********************************
     *        Removed Room(s)       *
     ********************************/
	//Get All Removed Room Numbers
	public ArrayList<Integer> getRemovedRoomNumbers(){
		return removedRoomNumbers;
	}

	//Add Removed Room Number
    public void addRemovedRoomNumber(int removedRoomNumber){
		removedRoomNumbers.add(removedRoomNumber);
	}

	//Remove Removed Room Number
	public void removeRemovedRoomNumber(int usedRoomNumber){
	//	for (int i = 0; i < removedRoomNumbers.size(); i++){
	//		if(r == usedRoomNumber){
	//			removedRoomNumbers.remove(i);
	//		}
	//	}
	}


    /****************************************************************
     *                      Reservation Methods                     *
     ****************************************************************/

    /********************************
     *       Get Reservations       *
     ********************************/
	//Get Reservation based on Reservation Number
	public Reservation getReservation(int searchReservationNumber){
		for (Reservation reservation : allReservations){
			if (reservation.getReservationNumber() == searchReservationNumber)
				return reservation;
		}
		return null; //No Reservaiton found
	}

	//Get All Reservations
	public ArrayList<Reservation> getAllReservations(){
		return allReservations;
	}

    /********************************
     *    Add/Remove Reservations   *
     ********************************/
    //Add a Reservation object to the list
    public void addReservation(Reservation reservation) {
        allReservations.add(reservation);
    }

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
     *                       Boolean Methods                        *
  	 ****************************************************************/

   	/********************************
     *       Are Hotels Equal       *
     ********************************/
    //Checks if Hotels have matching names
    public boolean isEqualTo(Hotel otherHotel){
    	if (hotelName.equals(otherHotel.getHotelName())){
    		return true;
    	}else{
    		return false;
    	}
    }


	/****************************************************************
	 *							End					 				*
	 ****************************************************************/
}//End of Hotel Class

                                                    