//Database.java


package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;



import java.util.ArrayList;


/****************************************************************
 *						Database Class							*
 ****************************************************************/
public class Database { //Currently where data is stored, will eventually be replace with Database calls and returns

    //Object Variables
    private Hotel currentHotel;
    private User currentUser;
    private boolean signedIn;

    //Database Variables
    private ArrayList<Hotel> allHotels;
    private ArrayList<User> allUsers;


    //Database constructor
    public Database(){
        allHotels = new ArrayList<Hotel>();
        allUsers = new ArrayList<User>();
        currentUser = null;
        currentHotel = null;
        signedIn = false;
    }





    //Method to Add Hotel to Database 
    //Method to Add Room to Database 
    //Method to Add User to Database 
    //Method to Add Reservation to Database 

    //Method to Query Hotel from Database
    //Method to Query Room from Database
    //Method to Query User from Database
    //Method to Query Reservation from Database

   

    //Methods to Add/Remove Hotel from list
    //Methods to Add/Remove Users from list

    //Method to Get/Set Current Hotel/User
    //Method to Get/Set Signed-in Status







/*

1. Hotels Table
Name            Data Type   Notes
hotel_id        INT (PK)    AUTO_INCREMENT
name            VARCHAR     Name of the hotel
address         VARCHAR     Full address of the hotel

2. Rooms Table
Name            Data Type   Notes
room_id         INT (PK)    Hotel id + Room number
hotel_id        INT (FK)    Links to Hotels table
room_number     INT         Room number within hotel
bed_type        VARCHAR     Type of bed (e.g., Queen)
num_of_beds     INT         Number of beds
price_per_night DECIMAL     Room price per night
reservation_id  INT (FK)    Links to Reservations table (if reserved)

3. Users Table
Name            Data Type   Notes
user_id         INT (PK)    AUTO_INCREMENT
first_name      VARCHAR     User's first name
last_name       VARCHAR     User's last name
username        VARCHAR     Unique login name
password        VARCHAR     Encrypted password
birthday        Date        Email address
start_date      DATE        For managers only
employee_num    INT         For managers only
Note: Fields like department and employee_number will be null for users who aren’t managers.

4. Reservations Table
Name            Data Type   Notes
reservation_id  INT (PK)    AUTO_INCREMENT
user_id         INT (FK)    Links to Users table
room_id         INT (FK)    Links to Rooms table
hotel_id        INT (FK)    Links to Hotels table
check_in_date   DATE        Check-in date
check_out_date  DATE        Check-out date
total_cost      DECIMAL     Total cost of stay

*/

	




	
    /****************************************************************
     *                        Hotel Methods                         *
     ****************************************************************/
    /********************************
     *       Add/Remove Hotel       *
     ********************************/
    //Add a Hotel object to the list
    public void addHotel(Hotel hotel) {
        allHotels.add(hotel);
    }

    //Remove a Hotel object from the list by matching with isEqualTo()
    public void removeHotel(String hotelName) {
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).equals(hotelName)) {
                allHotels.remove(i);
                break; //Stop after removing the first match
            }
        }
    }


    /********************************
     *        Set/Get Hotel(s)      *
     ********************************/
    //Set Current Hotel
    public void setCurrentHotel(Hotel hotel){
        currentHotel = hotel;
    }

    //Get Current Hotel
    public Hotel getCurrentHotel(){
        return currentHotel;
    }

    //Get a Hotel object by comparing with another Hotel object using isEqualTo()
    public Hotel getHotel(String searchHotelName) {
        for (Hotel hotel : allHotels) {
            if (hotel.getHotelName().equals(searchHotelName)) {
                return hotel;
            }
        }
        return null; //If no matching object is found
    }

    //Get all Hotels
    public ArrayList<Hotel> getAllHotels(){
        return allHotels;
    }

    /********************************
     *          Print Hotel         *
     ********************************/
    //Print Current Hotel
    public void printCurrentHotel(){
        if(currentHotel != null){
        System.out.println("Current Hotel: "+ currentHotel.getHotelName());
        }
        return;
    }


    /****************************************************************
     *                        User Methods                          *
     ****************************************************************/
    /********************************
     *        Add/Remove User       *
     ********************************/
    //Add a User object to the list
    public void addUser(User user) {
        allUsers.add(user);
    }

    //Remove a User object from the list by matching with isEqualTo()
    public void removeUser(String searchUsername) {
        for(User user : allUsers){
            if (user.getUsername().equals(searchUsername))
                allUsers.remove(user);
                break; //Stop after removing the first match
        }
    }

    /********************************
     *         Set/Get User(s)      *
     ********************************/
    //Set Current User
    public void setCurrentUser(User user){
        currentUser = user;
    }

    //Get Current User
    public User getCurrentUser(){
        return currentUser;
    }

    // Get a User object by comparing with another User object using isEqualTo()
    public User getUser(String searchUser) {
        for (User user : allUsers) {
            if (user.getUsername().equals(searchUser)) {
                return user;
            }
        }
        return null; //If no matching object is found
    }

    //Get all Users
    public ArrayList<User> getAllUsers(){
        return allUsers;
    }

    /********************************
     *          Print User          *
     ********************************/
    //Print Current User
    public void printCurrentUser(){
        if(currentUser != null){
        System.out.println("Current User: "+ currentUser.getName());
        }else{

        }
        return;
    }

    /****************************************************************
     *                       Signed-In Methods                      *
     ****************************************************************/
    /********************************
     *        Set/Get Status        *
     ********************************/
    //Set Signed-In Status
    public void setSignedInStatus(boolean status){
        signedIn = status;
    }

    //Get Signed in Status
    public boolean isSignedIn(){
        return signedIn;
    }

    /****************************************************************
     *                      Connect to Database                     *
     ****************************************************************/
    /********************************
     *            Connect           *
     ********************************/

    /********************************
     *        Query Database        *
     ********************************/

    /********************************
     *       Send to Database       *
     ********************************/


	/****************************************************************
	 *							End			    					*
	 ****************************************************************/
}//End of Database Class