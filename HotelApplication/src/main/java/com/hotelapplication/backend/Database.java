//Database.java


package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;



import java.util.ArrayList;


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


/**
* The Database class represents an in-memory storage for managing hotel and user data.
* It provides methods for adding, removing, and retrieving hotel and user information.
* This implementation will eventually be replaced with actual database calls.
* 
* @author Alexander Boutselis
*/
public class Database {

    //Object Variables
    private Hotel currentHotel;
    private User currentUser;
    private boolean signedIn;

    //Database Variables
    private boolean isConnected;
    private ArrayList<Hotel> allHotels;
    private ArrayList<User> allUsers;
    private ArrayList<Reservation> allReservations;

    /****************************************************************
     *                       Constructor                            *
     ****************************************************************/
    /**
    * Constructs a new Database object, initializing the lists for hotels and users.
    */
    public Database() {
        allHotels = new ArrayList<Hotel>();
        allUsers = new ArrayList<User>();
        allReservations = new ArrayList<Reservation>();
        currentUser = null;
        currentHotel = null;
        signedIn = false;
        try {
            //Placeholder for any future implementation
        } catch (Exception e) {
            //Handle exception
        }
    }

    /****************************************************************
     *                       Hotel Methods                          *
     ****************************************************************/
    /**
    * Adds a hotel to the database.
    *
    * @param hotel The Hotel object to be added.
    */
    public void addHotel(Hotel hotel) {
        allHotels.add(hotel);
    }

    /**
    * Removes a hotel from the database by matching its name.
    *
    * @param hotelName The name of the hotel to be removed.
    */
    public void removeHotel(String hotelName) {
        for (int i = 0; i < allHotels.size(); i++) {
            if (allHotels.get(i).equals(hotelName)) {
                allHotels.remove(i);
                break; //Stop after removing the first match
            }
        }
    }

    /**
    * Sets the current hotel being managed.
    *
    * @param hotel The Hotel object to set as the current hotel.
    */
    public void setCurrentHotel(Hotel hotel) {
        currentHotel = hotel;
    }

    /**
    * Gets the current hotel being managed.
    *
    * @return The current Hotel object.
    */
    public Hotel getCurrentHotel() {
        return currentHotel;
    }

    /**
    * Gets a hotel from the database by its name.
    *
    * @param searchHotelName The name of the hotel to be searched.
    * @return The Hotel object if found, otherwise null.
    */
    public Hotel getHotel(String searchHotelName) {
        for (Hotel hotel : allHotels) {
            if (hotel.getHotelName().equals(searchHotelName)) {
                return hotel;
            }
        }
        return null; //If no matching object is found
    }

    /**
    * Gets a hotel from the database by its ID.
    *
    * @param hotelID The ID of the hotel to be searched.
    * @return The Hotel object if found, otherwise null.
    */
    public Hotel getHotel(int hotelID) {
        for (Hotel hotel : allHotels) {
            if (hotel.getHotelID() == hotelID) {
                return hotel;
            }
        }
        return null; //If no matching object is found
    }

    /**
    * Gets all hotels from the database.
    *
    * @return A list of all Hotel objects.
    */
    public ArrayList<Hotel> getAllHotels() {
        return allHotels;
    }

    /**
    * Prints the name of the current hotel.
    */
    public void printCurrentHotel() {
        if (currentHotel != null) {
            System.out.println("Current Hotel: " + currentHotel.getHotelName());
        }
    }

    /****************************************************************
     *                       User Methods                           *
     ****************************************************************/
    /**
    * Adds a user to the database.
    *
    * @param user The User object to be added.
    */
    public void addUser(User user) {
        allUsers.add(user);
    }

    /**
    * Removes a user from the database by matching its username.
    *
    * @param searchUsername The username of the user to be removed.
    */
    public void removeUser(String searchUsername) {
        for (User user : allUsers) {
            if (user.getUsername().equals(searchUsername)) {
                allUsers.remove(user);
                break; //Stop after removing the first match
            }
        }
    }

    /**
    * Sets the current user.
    *
    * @param user The User object to set as the current user.
    */
    public void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
    * Gets the current user.
    *
    * @return The current User object.
    */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
    * Gets a user from the database by username.
    *
    * @param searchUser The username of the user to be searched.
    * @return The User object if found, otherwise null.
    */
    public User getUser(String searchUser) {
        for (User user : allUsers) {
            if (user.getUsername().equals(searchUser)) {
                return user;
            }
        }
        return null; //If no matching object is found
    }

    /**
    * Gets a user from the database by ID.
    *
    * @param userID The ID of the user to be searched.
    * @return The User object if found, otherwise null.
    */
    public User getUser(int userID) {
        for (User user : allUsers) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null; //If no matching object is found
    }

    /**
    * Gets all users from the database.
    *
    * @return A list of all User objects.
    */
    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    /**
    * Prints the name of the current user.
    */
    public void printCurrentUser() {
        if (currentUser != null) {
            System.out.println("Current User: " + currentUser.getName());
        }
    }


    /****************************************************************
     *                      Reservation Methods                     *
     ****************************************************************/
    /**
    * Adds a reservation to the database.
    *
    * @param reservation The Reservation object to be added.
    */
    public void addReservation(Reservation reservation) {
        allReservations.add(reservation);
    }

    /**
    * Removes a reservation from the database by matching its reservation ID.
    *
    * @param reservationID The ID of the reservation to be removed.
    */
    public void removeReservation(int reservationID) {
        for (Reservation reservation : allReservations) {
            if (reservation.getReservationID() == reservationID) {
                allReservations.remove(reservation);
                break; // Stop after removing the first match
            }
        }
    }

    /**
    * Gets a reservation from the database by ID.
    *
    * @param reservationID The ID of the reservation to be searched.
    * @return The Reservation object if found, otherwise null.
    */
    public Reservation getReservation(int reservationID) {
        for (Reservation reservation : allReservations) {
            if (reservation.getReservationID() == reservationID) {
                return reservation;
            }
        }
        return null; // If no matching object is found
    }

    /**
    * Gets all reservations from the database.
    *
    * @return A list of all Reservation objects.
    */
    public ArrayList<Reservation> getAllReservations() {
        return allReservations;
    }

    /**
    * Prints the details of the given reservation.
    *
    * @param reservation The Reservation object whose details are to be printed.
    */
    public void printReservation(Reservation reservation) {
        if (reservation != null) {
            System.out.println("Reservation: " + reservation.getReceipt());
        }
    }

    /****************************************************************
     *                       Signed-In Methods                      *
     ****************************************************************/
    /**
    * Sets the signed-in status.
    *
    * @param status The new signed-in status.
    */
    public void setSignedInStatus(boolean status) {
        signedIn = status;
    }

    /**
    * Checks if a user is signed in.
    *
    * @return True if a user is signed in, otherwise false.
    */
    public boolean isSignedIn() {
        return signedIn;
    }

    /****************************************************************
     *                           End                                *
     ****************************************************************/
}//End of Database Class