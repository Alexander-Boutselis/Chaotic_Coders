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
package com.hotelapplication;
import com.hotelapplication.frontend.*;
import com.hotelapplication.backend.*;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//How to run
//mvn clean compile exec:java

//Start of Main
public class Main{
	public static void main(String[] args) {
 // Create an instance of DatabaseConnecter
        // Create an instance of DatabaseConnecter
        DatabaseConnecter dbConnecter = new DatabaseConnecter();

        // Connect to the database
        dbConnecter.connect();

        // Example query: Retrieve all users
        String query = "SELECT * FROM Users";
        try (ResultSet rs = dbConnecter.executeQuery(query)) {
            while (rs != null && rs.next()) {
                System.out.println("User ID: " + rs.getInt("user_id") + ", Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        DatabaseManager.initializeDatabase("Chaotic Coder Inn", 100);

        TestClass.testCases(0);

        DatabaseManager.setCurrentHotel(DatabaseManager.getHotel("Chaotic Coder Inn"));

        HotelManager.printCurrentHotelInfo();

		Scanner scanner = new Scanner(System.in);
       promptForGUI(scanner);

        scanner.close(); // Close the scanner to avoid resource leaks
        // Disconnect from the database using the instance
        dbConnecter.disconnect();
        System.exit(0);
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
