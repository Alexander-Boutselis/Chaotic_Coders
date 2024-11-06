//RoomManager.java

package com.hotelapplication.backend;
import com.hotelapplication.frontend.*;

import java.util.Scanner;
import java.util.ArrayList;




/****************************************************************
 *                     Room Manager                             *
 ****************************************************************/
public class RoomManager {
	
    /*Functions Needed:
		Create/Destroy Hotel Room
		Get/Set Room #
		Get Next Room #
		Get/Set Bed Type
		Get/Set Number of Beds
		Get/Set Price Per Night -Calc Price Per Night
		Get/Set Room Availability
		Get/Set Reservation Index Number (Reservations are stored in the database and using this index number we can find the reservation associated with this room)
		isEqualTo()
		Print Room Info
	*/



    // Private constructor to prevent instantiation
    private RoomManager() {}

/****************************************************************
 *                  Create/Destroy Room                         *
 ****************************************************************/

    public static void createRoom(int numOfBeds, String bedType, String roomDescription){

		//Get Next Room Number
		int roomNumber = getNextRoomNumber();
  
        //Calc Price Per Night
		double pricePerNight = calcPricePerNight(roomNumber, numOfBeds, bedType);

    	//Create Room
    	Room newRoom = new Room(roomNumber, numOfBeds, bedType, pricePerNight, roomDescription);

    	//Add Room to Current Hotel
    	HotelManager.addRoomToCurrentHotel(newRoom);
    }

    public static void removeRoom(int roomNumber){
        Room roomToRemove;
        int indexOfRoom;
        roomToRemove = getHotelRoom(roomNumber);
        indexOfRoom = HotelManager.getAllCurrentHotelRooms().indexOf(roomToRemove);
        HotelManager.getAllCurrentHotelRooms().set(indexOfRoom, null);
        HotelManager.addRemovedRoomNumber(HotelManager.getCurrentHotel(), roomNumber);
    }


/****************************************************************
 *                  		Getters 	                        *
 ****************************************************************/

    //Get Room #
    public static int getRoomNumber(Room room){
    	return room.getRoomNumber();
    }

    //Get Next Room #
    public static int getNextRoomNumber(){
    	int nextRoomNumber = 1;
    	int roomNumber;

    	for (Room room : HotelManager.getAllCurrentHotelRooms()){

    		roomNumber = getRoomNumber(room);
    		if (roomNumber <= nextRoomNumber){
    			nextRoomNumber = roomNumber + 1;
    		}
    	}

    	for (int roomNum : HotelManager.getRemovedRoomNumbers(HotelManager.getCurrentHotel())){
    		if (roomNum < nextRoomNumber){
    			nextRoomNumber = roomNum;
    		}
    	}
    	return nextRoomNumber;
    }

    //Get Bed Type
    public static String getBedType(Room room){
    	return room.getBedType();
    }

    //Get Number of Beds
    public static int getNumberOfBeds(Room room){
    	return room.getNumberOfBeds();
    }


    //Get Price Per Night
       public static double getPricePerNight(Room room){
    	return room.getPricePerNight();
    }

    //Get Room Description
    public static String getRoomDescription(Room room){
    	return room.getRoomDescription();
    }

    //Get Room from Current Hotel
    public static Room getHotelRoom(int searchRoomNumber){
    	for (Room room : HotelManager.getAllCurrentHotelRooms()){
    		if (room.getRoomNumber() == searchRoomNumber){
    			return room;
    		}
    	}
    	return null;
    }

    //Get All Reservation Numbers
    public static ArrayList<Integer> getAllReservationNumbers(Room room){
    	return room.getAllreservationNumbers();
    }


/****************************************************************
 *                  		Setters 	                        *
 ****************************************************************/

    //Set Room #


    //Set Bed Type
    public static void setBedType(Room room, String bedType){
    	bedType = bedType.toLowerCase();
    	room.setBedType(bedType);
    }

    //Set Number of Beds

    //Set Price Per Night (Manual)

    //Set Price Per Night
    public static void setPricePerNight(Room room){

    	//Get Room Number
    	int roomNumber = room.getRoomNumber();
    	
    	//Get Bed Type
    	String bedType = room.getBedType();

    	//Get Num of Beds
    	int numOfBeds = room.getNumberOfBeds();

    	//Calculate Price Per Night
    	room.setPricePerNight(calcPricePerNight(roomNumber, numOfBeds, bedType));
    }


    //Set Room Description


/****************************************************************
 *                  		 Adders 	                        *
 ****************************************************************/

	//Add Reservation


/****************************************************************
 *                  		 Removers 	                        *
 ****************************************************************/

    //Remove Reservation

/****************************************************************
 *                      Calculate Price                         *
 ****************************************************************/

    //Calculate Price Per Night - Call Set Price Per Night At End
    public static double calcPricePerNight(int roomNumber, int numOfBeds, String bedType){
    	double basePrice;

    	

        // Set base price based on bed type
        switch (bedType) {
            case "twin":
                basePrice = 60;
                break;
            case "full":
                basePrice = 80;
                break;
            case "queen":
                basePrice = 100;
                break;
            case "king":
                basePrice = 150;
                break;
            case "california king":
                basePrice = 200;
                break;
            default:
                //throw new IllegalArgumentException("Invalid bed type: " + bedType);
                basePrice = 666;
                break;
        }

        // Add additional price based on number of beds
        double bedAddition = numOfBeds * 20;
        double roomAddition = (roomNumber / 100) * 15.0;

        // Calculate final price per night
        double pricePerNight = basePrice + bedAddition + roomAddition;

        return pricePerNight;
    }

    //Calculate Reservation Total $

/****************************************************************
 *                         Boolean                              *
 ****************************************************************/

    //is Available


/****************************************************************
 *                         Prompts                              *
 ****************************************************************/

    //Prompt to get user info to create a room
    public static void createAHotelRoom(Scanner scanner){

    	String bedType = "";
    	int numOfBeds;
    	String roomDescription;
    	double pricePerNight;

    	System.out.println("\n---Create A Room---");
		//Get First name
        System.out.println("What type of Bed will this room have?");
        System.out.println("1. Twin");
        System.out.println("2. Full");
        System.out.println("3. Queen");
        System.out.println("4. King");
        System.out.println("5. California King");
        System.out.println("6. Cancel");
        System.out.print("\nSelect Type of Bed: ");

        int choice = scanner.nextInt();
        scanner.nextLine();       


        // Set base price based on bed type
        switch (choice) {
            case 1:
                bedType = "twin";
                break;
            case 2:
                bedType = "full";
                break;
            case 3:
                bedType = "queen";
                break;
            case 4:
                bedType = "king";
                break;
            case 5:
                bedType = "california king";
                break;
            case 6:
                return;
            default:
                break;
        }  


        System.out.println("\nHow many beds will there be?");
        System.out.print("Number of Beds: ");

        numOfBeds = scanner.nextInt();
        scanner.nextLine();     

        //Get Room Description
        System.out.println("\nEnter Room Description: ");
        roomDescription = scanner.nextLine();
        //roomDescription = "";

        createRoom(numOfBeds, bedType, roomDescription);

    }

    //Remove a Hotel Room
    public static void removeAHotelRoom(Scanner scanner){
        //Print all Hotel Rooms
        System.out.println(HotelManager.getAllCurrentHotelRoomsInfo());

        //Get Room Number from User
        System.out.println("Which Room would you like to remove?");
        System.out.print("Room Number: ");

        int roomNumber = scanner.nextInt();
        scanner.nextLine();  

        //Using that Room number Remove the room
        removeRoom(roomNumber);

    }

/****************************************************************
 *                           Print                              *
 ****************************************************************/

    //toString Hotel info
    public static String getRoomInfo(int roomNumber){
        return HotelManager.getCurrentHotel().getRoom(roomNumber).getRoomInfo();
    }

    //toString Hotel info
    public static String getRoomInfo(Room room){
        return room.getRoomInfo();
    }

    //Print Hotel
    public static void printRoomInfo(int roomNumber){
        System.out.println(getRoomInfo(roomNumber));
    }


/****************************************************************
 *                           End                                *
 ****************************************************************/

}