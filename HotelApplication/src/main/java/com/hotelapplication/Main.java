//Main.java
/**************************
 *Hotel Management Project*
 * COMP 380/L             *
 **************************/

/*
Maven Commands:
mvn clean compile javadoc:javadoc exec:java
mvn compile exec:java
mvn test
*/
package com.hotelapplication;
import com.hotelapplication.frontend.*;
import com.hotelapplication.backend.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class of the HotelApplication. 
 * This is the entry point of the application that initializes the database, 
 * prompts for user interaction, and either launches a GUI or runs the program in the terminal.
 */
public class Main {
    /**
     * The main method serves as the entry point of the application.
     * It initializes the application, prints tables, prompts the user for GUI or terminal operation,
     * and handles resource cleanup and shutdown.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            //DatabaseConnector.deleteDatabaseFile();
            
            DatabaseConnector.initializeApplication();

        } catch (Exception e) {
            System.err.println("Ran into an Exception: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for better debugging
        }

        try {
            
            GUIManager.runAppGUI();

            // Print the current database tables for verification
            DatabaseConnector.printAllTables();
           
            //TestClass.testPrints();
            

        } catch (Exception e) {
            System.err.println("Database not connected: " + e.getMessage());
        } finally {
            // Clean up and close the application
            GUIManager.closeApplication();
            scanner.close(); // Close the scanner to avoid resource leaks
            System.exit(0);
        }
    } // End of main

    /**
     * Debug method for setting the program state. Allows selection between different user states.
     *
     * @return an integer representing the selected state (1: Not Signed in, 2: User Signed in, 3: Manager Signed in, 4: Quit Program)
     */
    public static int debug() {
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
} // End of Main
