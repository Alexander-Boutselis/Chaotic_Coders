//TestClass.java











public class TestClass{


    // Private constructor to prevent instantiation
    private TestClass() {}

public static void testCases(int choice){

	int numberOfCases = 10;
	int numberOfRooms = 35;

	switch(choice){
	case 0:
		//Run All Cases
		//System.out.println();
        System.out.println("---Running All Test Cases---");

		for (int i = 1; i <= numberOfCases; i++){
			testCases(i);
		}
		break;
	case 1:
        System.out.println("\n---Test Case 1---");

		String hotelName = "Test Hotel";
        //Create Initial Empty Hotel
        HotelManager.createHotel(hotelName);

        //Set it as Current Hotel
        DatabaseManager.setCurrentHotel(DatabaseManager.getHotel(hotelName));

        //Loop to Generate Rooms
        for (int i = 0; i < numberOfRooms; i++){
        	RoomManager.createRoom(2, "queen","");
        	RoomManager.createRoom(3, "twin","");
        	RoomManager.createRoom(1, "suite","");
        }

		//Print Hotel
		HotelManager.printCurrentHotelInfo();

		//Print All Hotel Rooms
        System.out.println(HotelManager.getAllCurrentHotelRoomsInfo());


        System.out.println(HotelManager.getCurrentHotelAndRoomsInfo());

        System.out.println("---1. Passed ---");
		break;
	case 2:
        System.out.println("\n---Test Case 2---");
        System.out.println("---2. Passed ---");

		break;
	case 3:
        System.out.println("\n---Test Case 3---");
        System.out.println("---3. Passed ---");

		break;
	case 4:
        System.out.println("\n---Test Case 4---");
        System.out.println("---4. Passed ---");

		break;
	case 5:
        System.out.println("\n---Test Case 5---");
        System.out.println("---5. Passed ---");

		break;
	case 6:
        System.out.println("\n---Test Case 6---");
        System.out.println("---6. Passed ---");

		break;
	case 7:
        System.out.println("\n---Test Case 7---");
        System.out.println("---7. Passed ---");

		break;
	case 8:
        System.out.println("\n---Test Case 8---");
        System.out.println("---8. Passed ---");

		break;
	case 9:
        System.out.println("\n---Test Case 9---");
        System.out.println("---9. Passed ---");

		break;
	case 10:
        System.out.println("\n---Test Case 10---");
        System.out.println("---10. Passed ---");

		break;

	default:

		break;

	}

}
















/****************************************************************
 *                           End                                *
 ****************************************************************/
}