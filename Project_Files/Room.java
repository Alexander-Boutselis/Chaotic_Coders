//Room.java


import java.util.ArrayList;

/****************************************************************
 *                      Room Class                              *
 ****************************************************************/
public class Room {

    /*Variables:
        Room Number
        Number of Beds
        Bed Type
        Price Per Night
        Description
        List of Reservation Index Numbers in Database
    */
    
    /*Functions:
        Get/Set Room Number
        Get/Set Number of Beds
        Get/Set Bed Type
        Get/Set Price Per Night
        Get/Set Description
        Get Reservation 
        Get All Reservations
        Add/Remove Reservation
        Print Room
    */


    private int roomNumber;
    private int numOfBeds;
    private String bedType;
    private String roomDescription;
    private double pricePerNight;
    private ArrayList<Integer> reservationNumbers;


    // Constructor for initializing the Room object
    public Room(int roomNumber, int numOfBeds, String bedType, double pricePerNight, String roomDescription) {
        this.roomNumber = roomNumber;
        this.bedType = bedType;
        this.numOfBeds = numOfBeds;
        this.pricePerNight = pricePerNight;
        this.roomDescription = roomDescription;
        reservationNumbers = new ArrayList<>();
    }


/****************************************************************
 *                      Setters                                 *
 ****************************************************************/

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setNumberOfBeds(int numOfBeds){
        this.numOfBeds = numOfBeds;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public void setRoomDescription(String roomDescription){
        this.roomDescription = roomDescription;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }


/****************************************************************
 *                      Getters                                 *
 ****************************************************************/
    public int getRoomNumber(){
        return roomNumber;
    }

    public int getNumberOfBeds(){
        return numOfBeds;
    }

    public String getBedType(){
        return bedType;
    }
    
    public String getRoomDescription(){
        return roomDescription;
    }

    public double getPricePerNight(){
        return pricePerNight;
    }

    public int getReservationIndexNumber(int index){
        return reservationNumbers.get(index);
    }

    public ArrayList<Integer> getAllreservationNumbers(){
        return reservationNumbers;
    }


/****************************************************************
 *                      Add Reservation                         *
 ****************************************************************/
    public void addReservationIndexNumber(int newReservationIndexNumber){
        reservationNumbers.add(newReservationIndexNumber);
    }
    

/****************************************************************
 *                      Remove Reservation                      *
 ****************************************************************/
    public void removeReservationIndexNumber(int indexNumberToDelete){

        for (int i = 0; i < reservationNumbers.size(); i++) {

            if (reservationNumbers.get(i) == indexNumberToDelete){
                reservationNumbers.remove(i);
                return;
            }
        }
    }

/****************************************************************
 *                           Print                              *
 ****************************************************************/

    // Display room details
    public void printRoom() {
        System.out.println("Room Number: " + roomNumber);
        System.out.print(numOfBeds + " ");
        System.out.println(bedType);
        System.out.println("Price per Night: $" + pricePerNight);
        System.out.println("Description: ");
        System.out.println(roomDescription);
    }

/****************************************************************
 *                          End                                 *
 ****************************************************************/

}