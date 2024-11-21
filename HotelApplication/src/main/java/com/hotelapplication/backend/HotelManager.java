//HotelManager.java


package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.util.ArrayList;
import java.util.Scanner;
import org.jdbi.v3.core.Handle;



public class HotelManager {
	
    //Private constructor to prevent instantiation
    private HotelManager() {}



/****************************************************************
 *                     Create/Destroy Hotel                     *
 ****************************************************************/
/********************************
 *       Create New Hotel       *
 ********************************/
    public static void createHotel(String hotelName, String hotelAddress){
        try{
            //Create new Hotel
            Hotel newHotel = new Hotel( hotelName, hotelAddress);

            //Add Hotel to database
            DatabaseManager.addHotel(newHotel);
            DatabaseConnector.addHotel(newHotel);

        }catch (Exception e) {
            System.out.println("Failed to create Hotel and Add to Database");
        }
    }

/********************************
 *          Remove Hotel        *
 ********************************/
    

/****************************************************************
 *                        Get Hotel Info                        *
 ****************************************************************/
/********************************
 *          Hotel Name          *
 ********************************/
    //Get Current Hotel Name
    public static String getHotelName(){
    	return DatabaseManager.getCurrentHotel().getHotelName();
    }

    //Get Hotel Name
    public static String getHotelName(Hotel hotel){
        return hotel.getHotelName();
    }

/********************************
 *          Hotel ID            *
 ********************************/
    //Get Current Hotel Id
    public static int getHotelID(){
        return DatabaseManager.getCurrentHotel().getHotelID();
    }

    //Get Hotel Id
    public static int getHotelID(Hotel hotel){
        return hotel.getHotelID();
    }

/********************************
 *        Hotel Address         *
 ********************************/
    //Get Current Hotel Address
    public static String getHotelAddress(){
        return DatabaseManager.getCurrentHotel().getHotelAddress();
    }

    //Get Hotel Address
    public static String getHotelAddress(Hotel hotel){
        return hotel.getHotelAddress();
    }

/********************************
 *     Number of Hotel Rooms    *
 ********************************/
    //Get Total Number of Rooms in Current Hotel
    public static int getTotalNumberOfRooms(){
        return getAllHotelRooms().size();
    }

    //Get Total Number of Rooms in Hotel
    public static int getTotalNumberOfRooms(Hotel hotel){
        return getAllHotelRooms(hotel).size();
    }


/****************************************************************
 *                        Set Hotel Info                        *
 ****************************************************************/
/********************************
 *          Hotel Name          *
 ********************************/
    //Set Current Hotel Name
    public static void setHotelName(String newHotelName){
        DatabaseManager.getCurrentHotel().setHotelName(newHotelName);
    }

    //Set Hotel Name
    public static void setHotelName(Hotel hotel, String newHotelName){
       hotel.setHotelName(newHotelName);
    }

/********************************
 *          Hotel ID            *
 ********************************/
    //Set Current Hotel Id
    public static void setHotelID(int hotelID){
        DatabaseManager.getCurrentHotel().setHotelID(hotelID);
    }

    //Set Hotel Id
    public static void setHotelID(Hotel hotel, int hotelID){
        hotel.setHotelID(hotelID);
    }

/********************************
 *        Hotel Address         *
 ********************************/
    //Set Current Hotel Address
    public static void setHotelAddress(String hotelAddress){
        DatabaseManager.getCurrentHotel().setHotelAddress(hotelAddress);
    }

    //Set Hotel Address
    public static void setHotelAddress(Hotel hotel, String hotelAddress){
        hotel.setHotelAddress(hotelAddress);
    }


/****************************************************************
 *                          Hotels and Rooms                    *
 ****************************************************************/
/********************************
 *          Get Hotel           *
 ********************************/
    //Get Hotel by searching its name
    public static Hotel getHotel(String searchHotelName){
    	return DatabaseManager.getHotel(searchHotelName);
    }

    //Get Hotel by searching its hotelID
    public static Hotel getHotel(int hotelID){
        return DatabaseManager.getHotel(hotelID);
    }

/********************************
 *         Get All Hotels       *
 ********************************/
    //Get All Hotels in Database
    public static ArrayList<Hotel> getAllHotels(){
        return DatabaseManager.getAllHotels();
    }

/********************************
 *       Get Current Hotel      *
 ********************************/
    //Get Current Hotel
    public static Hotel getCurrentHotel(){
        return DatabaseManager.getCurrentHotel();
    }

/********************************
 *       Set Current Hotel      *
 ********************************/
 //Set Current Hotel
    public static void setCurrentHotel(Hotel hotel){
        DatabaseManager.setCurrentHotel(hotel);
    }

/********************************
 *          Get Rooms           *
 ********************************/
    //Get All Rooms from Current Hotel
    public static ArrayList<Room> getAllHotelRooms(){
        return DatabaseManager.getCurrentHotel().getAllRooms();
    }

    //Get All Rooms from Hotel
    public static ArrayList<Room> getAllHotelRooms(Hotel hotel){
        return hotel.getAllRooms();
    }


/****************************************************************
 *                            Adders                            *
 ****************************************************************/
/********************************
 *          Add Hotel           *
 ********************************/
    //Add Hotel to Database
    public static void addHotel(Hotel newHotel){
        DatabaseManager.addHotel(newHotel);
    }

/********************************
 *           Add Room           *
 ********************************/
    //Add Room to Current Hotel
    public static void addRoomToHotel(Room newRoom){
        DatabaseManager.getCurrentHotel().addRoom(newRoom);
    }

    //Add Room to Hotel
    public static void addRoomToHotel(Hotel hotel,Room newRoom){
        hotel.addRoom(newRoom);
    }

    
/****************************************************************
 *                            Removers                          *
 ****************************************************************/
/********************************
 *         Remove Hotel         *
 ********************************/
    //Remove Hotel from Database
    public static void removeHotel(String searchHotelName){
        DatabaseManager.removeHotel(searchHotelName); 
    }

/********************************
 *         Remove Room          *
 ********************************/
    //Remove Room from Current Hotel
    public static void removeRoomHotel(Room newRoom){
        DatabaseManager.getCurrentHotel().removeRoom(newRoom.getRoomNumber());
    }

    //Remove Room from Hotel
    public static void removeRoomHotel(Hotel hotel, Room newRoom){
        hotel.removeRoom(newRoom.getRoomNumber());
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

        for(Room room : getAllHotelRooms()){
            try{
                receipt.append(room.getRoomInfo());
            }catch (Exception e){
                receipt.append("\n***********************\n");
                receipt.append("Room #: null");
                receipt.append("\n");
                receipt.append("\n");
                receipt.append("Price per Night: null");
                receipt.append("\n");
                receipt.append("Description: null\n");
            }
        }


        return receipt.toString();
    }

    //toString Hotel info
    public static String getCurrentHotelInfo(){
        return getCurrentHotel().getHotelInfo();
    }

    //toString Hotel info
    public static String getHotelInfo(Hotel hotel){
        return hotel.getHotelInfo();
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
 *                          Screens                             *
 ****************************************************************/

    
/****************************************************************
 *                     			End                             *
 ****************************************************************/
}