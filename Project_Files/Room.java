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


    //Private
    private int roomNumber;
    private int numOfBeds;
    private String bedType;
    private String roomDescription;
    private double pricePerNight;
    private ArrayList<Integer> reservationNumbers;


    //Constructor
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

    //Set Room Number
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    //Set Number of Beds
    public void setNumberOfBeds(int numOfBeds){
        this.numOfBeds = numOfBeds;
    }

    //Set Bed Type
    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    //Set Room Description
    public void setRoomDescription(String roomDescription){
        this.roomDescription = roomDescription;
    }

    //Set Price Per Night
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }


/****************************************************************
 *                      Getters                                 *
 ****************************************************************/
    
    //Get Room Number
    public int getRoomNumber(){
        return roomNumber;
    }

    //Get Number of Beds
    public int getNumberOfBeds(){
        return numOfBeds;
    }

    //Get Bed Type
    public String getBedType(){
        return bedType;
    }
    
    //Get Room Description
    public String getRoomDescription(){
        return roomDescription;
    }

    //Get Price Per Night
    public double getPricePerNight(){
        return pricePerNight;
    }

    //Get All Reservation Numbers
    public ArrayList<Integer> getAllreservationNumbers(){
        return reservationNumbers;
    }

    //Check if Room has Reservation Number
    public boolean hasReservationNumber(int searchReservationNumber){
        for (int reservationNumber : reservationNumbers){
            if (reservationNumber == searchReservationNumber){
                return true;
            }
        }
        return false;
    }


/****************************************************************
 *                      Add Reservation                         *
 ****************************************************************/

    //Add Reservation Number
    public void addReservationNumber(int newReservationNumber){
        reservationNumbers.add(newReservationNumber);
    }
    

/****************************************************************
 *                      Remove Reservation                      *
 ****************************************************************/

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
 *                           Print                              *
 ****************************************************************/

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
            receipt.append("This room has " + numOfBeds + " " + capitalizedBedType  + ".\n");

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
 *                          End                                 *
 ****************************************************************/

}