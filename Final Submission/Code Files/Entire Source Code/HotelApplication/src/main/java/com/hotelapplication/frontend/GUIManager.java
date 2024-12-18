//GUIManager.java

package com.hotelapplication.frontend;
import com.hotelapplication.backend.*;

import java.util.Scanner;

import javax.swing.SwingUtilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.time.format.*;
import java.time.temporal.ChronoUnit;
import java.time.*;

public class GUIManager{

    // Private constructor to prevent instantiation
    private GUIManager() {}

public static String accountSignIn(String username, String password) {
    for (User user : DatabaseManager.getAllUsers()) {
        if (user.getUsername().equals(username) && user.getPassword().equals(password) && user.getActiveStatus()) {
            AccountManager.signIn(user);
            return (user instanceof Manager) ? "Manager" : "User";
        }
    }
    return null; //Return null for incorrect login
}



/****************************************************************
 *                     Run App in GUI                           *
 ****************************************************************/
// Method to run the GUI, showing login screen
public static void runAppGUI() {
    //GUIScreen screen = new GUIScreen();
    //screen.setVisible(true);
    //screen.loginScreen();


    Login loginWindow = new Login();
    loginWindow.setVisible(true);

    //Keeps terminal open
    Scanner scanner = new Scanner(System.in);
    int pause = scanner.nextInt();
    scanner.nextLine(); 
}//End of Run App GUI


/****************************************************************
 *                           Exit Program                       *
 ****************************************************************/
//Method to end program gracefully
public static int closeApplication(){
    //DatabaseConnector.printAllTables();
    DatabaseConnector.disconnect();
    System.exit(0);
    return 0;
}

}//End of GUI Manager Class