/**************************
 * Hotel Management Project*
 * COMP 380/L             *
 **************************/
    
/****************************************************************
 *                          Title                               *
 ****************************************************************/

/********************************
 *          Sub Section         *
 ********************************/

//How to run
//mvn clean compile exec:java

package com.hotelapplication;
import com.hotelapplication.frontend.*;
import com.hotelapplication.backend.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

//Start of Main
public class Main{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            //DatabaseConnector.deleteDatabaseFile();
            DatabaseManager.initializeDatabase("Chaotic Coder Inn", 100);
            TestClass.testCases(0);

            //DatabaseConnector.emptyDatabase();
            //DatabaseManager.setCurrentHotel(DatabaseManager.getHotel("Chaotic Coder Inn"));

            HotelManager.printCurrentHotelInfo();


        } catch (Exception e) {
            System.err.println("Database not connected: " + e.getMessage());
        }


        try {
            DatabaseConnector.printHotelsTable();
            DatabaseConnector.printRoomsTable();
            DatabaseConnector.printUsersTable();
            DatabaseConnector.printReservationsTable();

            promptForGUI(scanner);
            
            DatabaseConnector.printHotelsTable();
            DatabaseConnector.printRoomsTable();
            DatabaseConnector.printUsersTable();
            DatabaseConnector.printReservationsTable();

           

        } catch (Exception e) {
            System.err.println("Database not connected: " + e.getMessage());
        }finally{
            GUIManager.closeApplication();
            //Close the scanner to avoid resource leaks
            scanner.close(); 
            System.exit(0);
        }
    }//End of main 


    public static void promptForGUI(Scanner scanner){
        String choice;

        System.out.println("\n---Launch GUI---");

        System.out.println("Would you like to launch the GUI (Y/N): ");

        choice = scanner.nextLine().toLowerCase();

        if (choice.charAt(0)== 'y'){
            GUIManager.runAppGUI();
        }else {
            //Hotel App Run in terminal window
            GUIManager.runAppInTerminal(scanner);
        }

    }


    public static int debug(){
        Scanner scanner = new Scanner(System.in);
        int input;
        System.out.println("\n---State Options (DEBUG)---");
        System.out.println("1. Not Signed in State");
        System.out.println("2. User Signed in State");
        System.out.println("3. Manager Signed in State");
        System.out.println("4. Quit Program");
        System.out.print("Set program state to:");
        input = scanner.nextInt();

        return input;
    }

    /****************************************************************
     *                          End                                 *
     ****************************************************************/
}//End of Main
