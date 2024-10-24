//Room.j

/************************************
*Room Class should include:         *
* Room #                            *
* Room Type                         *
* Status of Room                    *
* Amount per night                  *
* Size of Room (# of people it fits)*
*************************************/
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


    // Fields for the Room class
    private int roomNumber;
    private String roomType;
    private boolean isAvailable;
    private double pricePerNight;
    private int roomSize; // Number of people the room fits

    // Constructor for initializing the Room object
    public Room(int roomNumber, String roomType, boolean isOccupied, double pricePerNight, int roomSize) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.isOccupied = isOccupied;
        this.pricePerNight = pricePerNight;
        this.roomSize = roomSize;
    }

    // Getter and Setter methods
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    // Display room details
    public void displayRoomDetails() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room Type: " + roomType);
        System.out.println("isAvailable: " + (isAvailable ? "Yes" : "No"));
        System.out.println("Price per Night: $" + pricePerNight);
        System.out.println("Room Size: Fits " + roomSize + " people");
    }

    /****************************************************************
     *                          End                                 *
     ****************************************************************/

}