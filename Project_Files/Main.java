/**************************
 * Hotel Management Project*
 * COMP 380/L             *
 **************************/
import java.util.Scanner;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


//Start of Main
public class Main{
	public static void main(String[] args) {

        



        //Connect to Database
        //Database database = new Database();
        DatabaseManager.initializeDatabase("Chaotic Coder Inn", 100);

        TestClass.testCases(0);

        DatabaseManager.setCurrentHotel(DatabaseManager.getHotel("Chaotic Coder Inn"));

        HotelManager.printCurrentHotelInfo();

 




		Scanner scanner = new Scanner(System.in);


/*
public class AddRoom extends JFrame implements ActionListener{

    JTextField t2, t4;
    JComboBox t3,t5,t6;
    JButton b1, b2;

    AddRoom(){

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 875, 490);
        panel.setBackground(new Color(3,45,48));
        panel.setLayout(null);
        add(panel);

        JLabel l1 = new JLabel("Add Rooms");
        l1.setBounds(194, 10, 160, 22);
        l1.setFont(new Font("Tahoma", Font.BOLD, 22));
        l1.setForeground(Color.WHITE);
        panel.add(l1);

        JLabel l2 = new JLabel("Room Number");
        l2.setBounds(64,70,152,22);
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        l2.setForeground(Color.WHITE);
        panel.add(l2);

        t2 = new JTextField();
        t2.setBounds(200,70,156,20);
        t2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t2.setForeground(Color.WHITE);
        t2.setBackground(new Color(16,108,155));
        panel.add(t2);


        JLabel l3 = new JLabel("Availability");
        l3.setBounds(64,110,152,22);
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        l3.setForeground(Color.WHITE);
        panel.add(l3);

        t3 = new JComboBox(new String[] {"Available", "Occupied"});
        t3.setBounds(200,110,156,20);
        t3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t3.setForeground(Color.WHITE);
        t3.setBackground(new Color(16,108,155));
        panel.add(t3);

        JLabel l4 = new JLabel("Price");
        l4.setBounds(64,150,152,22);
        l4.setFont(new Font("Tahoma", Font.BOLD, 14));
        l4.setForeground(Color.WHITE);
        panel.add(l4);

        t4 = new JTextField();
        t4.setBounds(200,150,156,20);
        t4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t4.setForeground(Color.WHITE);
        t4.setBackground(new Color(16,108,155));
        panel.add(t4);


        JLabel l5 = new JLabel("Cleaning Status");
        l5.setBounds(64,190,152,22);
        l5.setFont(new Font("Tahoma", Font.BOLD, 14));
        l5.setForeground(Color.WHITE);
        panel.add(l5);

        t5 = new JComboBox(new String[] {"Cleaned", "Dirty"});
        t5.setBounds(200,190,156,20);
        t5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t5.setForeground(Color.WHITE);
        t5.setBackground(new Color(16,108,155));
        panel.add(t5);


        JLabel l6 = new JLabel("Bed Type");
        l6.setBounds(64,230,152,22);
        l6.setFont(new Font("Tahoma", Font.BOLD, 14));
        l6.setForeground(Color.WHITE);
        panel.add(l6);

        t6 = new JComboBox(new String[] {"Single Bed", "Double Bed"});
        t6.setBounds(200,230,156,20);
        t6.setFont(new Font("Tahoma", Font.PLAIN, 14));
        t6.setForeground(Color.WHITE);
        t6.setBackground(new Color(16,108,155));
        panel.add(t6);
        

        b1 = new JButton("Add");
        b1.setBounds(64, 321, 111, 33);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        panel.add(b1);

        b2 = new JButton("Back");
        b2.setBounds(198, 321, 111, 33);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        panel.add(b2);



        setUndecorated(true);
        setLocation(20,200);
        setLayout(null);
        setSize(885, 500);
        setVisible(true);

    }
    
    
    
    
    
    
    public static void main(String[] args) {
        new AddRoom();
        
    }






    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {

            try {
                conn c = new conn();
                String room = t2.getText();
                String ava = (String)t3.getSelectedItem();
                String status = (String) t5.getSelectedItem();
                String price = t4.getText();
                String type = (String) t6.getSelectedItem();


                String q = "insert into room values('"+room+"', '"+ava+"', '"+status+"', '"+price+"', '"+type+"' )";
                //c.statement.executeUpdate(q);

                JOptionPane.showMessageDialog(null, "Room Successfully Added");
                setVisible(false);

            } catch (Exception E) {
                E.printStackTrace();
            }
            
        } else {
            setVisible(false);
            
        }
    }
}

        
*/
        promptForGUI(scanner);

        scanner.close(); // Close the scanner to avoid resource leaks

	}//End of main 



public static void promptForGUI(Scanner scanner){
    String choice;

    System.out.println("\n---Launch GUI---");

    System.out.println("Would you like to launch the GUI (Y/N): ");

    choice = scanner.nextLine().toLowerCase();

    if (choice.charAt(0)== 'y'){
        //ADD CODE TO LAUNCH GUI HERE
    }else {
        //Hotel App Run in terminal window
        TestClass.runAppInTerminal(scanner);
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
