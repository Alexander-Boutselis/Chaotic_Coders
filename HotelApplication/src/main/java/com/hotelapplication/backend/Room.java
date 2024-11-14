//Room.java


package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.util.ArrayList;

/****************************************************************
 *                      Room Class                              *
 ****************************************************************/
public class Room {

   
    //Private
    private Integer roomID;
    private int roomNumber;
    private int numOfBeds;
    private String bedType;
    private String roomDescription;
    private double pricePerNight;
    private ArrayList<Integer> reservationNumbers;


    //Constructor
    public Room(int roomID, int roomNumber, int numOfBeds, String bedType, double pricePerNight, String roomDescription) {
        this.roomID = roomID;
        this.roomNumber = roomNumber;
        this.bedType = bedType;
        this.numOfBeds = numOfBeds;
        this.pricePerNight = pricePerNight;
        this.roomDescription = roomDescription;
        reservationNumbers = new ArrayList<>();
    }


    /****************************************************************
     *                      Room Info Methods                       *
     ****************************************************************/
    /********************************
     *        Set/Get roomID        *
     ********************************/
    //Set Room ID
    public void setRoomID(int roomID){
        this.roomID = roomID;
    }

    //Set Room ID
    public int getRoomID(){
        return roomID;
    }

    /********************************
     *      Set/Get Room Number     *
     ********************************/
    //Set Room Number
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    //Get Room Number
    public int getRoomNumber(){
        return roomNumber;
    }

    /********************************
     *        Set/Get Bed Info      *
     ********************************/
    //Set Number of Beds
    public void setNumberOfBeds(int numOfBeds){
        this.numOfBeds = numOfBeds;
    }

    //Get Number of Beds
    public int getNumberOfBeds(){
        return numOfBeds;
    }

    //Set Bed Type
    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    //Get Bed Type
    public String getBedType(){
        return bedType;
    }

    /********************************
     *   Set/Get Room Description   *
     ********************************/
    //Set Room Description
    public void setRoomDescription(String roomDescription){
        this.roomDescription = roomDescription;
    }

    //Get Room Description
    public String getRoomDescription(){
        return roomDescription;
    }

    /********************************
     *      Set/Get Room Price      *
     ********************************/
    //Set Price Per Night
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    //Get Price Per Night
    public double getPricePerNight(){
        return pricePerNight;
    }

    /********************************
     *          Print Room          *
     ********************************/
    //toString Room Info
    public String getRoomInfo(){
        StringBuilder receipt = new StringBuilder();

        receipt.append("\n***********************\n");
        receipt.append("Room #: " + roomNumber);
        receipt.append("\n");
        String capitalizedBedType = bedType.substring(0, 1).toUpperCase() + bedType.substring(1);
        receipt.append(numOfBeds + " " + capitalizedBedType); 
        receipt.append("\n");
        String formatedPricePerNight = String.format("%.2f", pricePerNight);
        receipt.append("Price per Night: $" + formatedPricePerNight);
        receipt.append("\n");
        receipt.append("Description: \n");

        //If no Description generate description
        if (roomDescription.equals("")){
            receipt.append("This room has " + numOfBeds + " " + capitalizedBedType  + " sized bed(s).\n");

            int roomFloor = roomNumber/ 100;
            if (roomFloor == 0){
                receipt.append("It is on the ground floor.\n");
            }else{
                receipt.append("It is on the " + roomFloor + " floor.\n");
            }

            receipt.append("This room costs " + formatedPricePerNight + " per night.");

        }else{
        receipt.append(roomDescription);
        }
        receipt.append("\n***********************\n");

        return receipt.toString();
    }

    // Display room details
    public void printRoomInfo() {
        System.out.println(getRoomInfo());
    }


    /****************************************************************
     *                     Reservation Methods                      *
     ****************************************************************/

    /********************************
     *       Get Reservations       *
     ********************************/
    //Get All Reservation Numbers
    public ArrayList<Integer> getAllreservationNumbers(){
        return reservationNumbers;
    }

    /********************************
     *    Check for Reservations    *
     ********************************/
    //Check if Room has Reservation Number
    public boolean hasReservationNumber(int searchReservationNumber){
        for (int reservationNumber : reservationNumbers){
            if (reservationNumber == searchReservationNumber){
                return true;
            }
        }
        return false;
    }

    /********************************
     *    Add/Remove Reservation    *
     ********************************/
    //Add Reservation Number
    public void addReservationNumber(int newReservationNumber){
        reservationNumbers.add(newReservationNumber);
    }
   
    //Remove Reservation Number
    public void removeReservationNumber(int roomNumberToDelete){

        for (int i = 0; i < reservationNumbers.size(); i++) {

            if (reservationNumbers.get(i) == roomNumberToDelete){
                reservationNumbers.remove(i);
                return;
            }
        }
    }


/****************************************************************
 *                          End                                 *
 ****************************************************************/
}//End of Room Class