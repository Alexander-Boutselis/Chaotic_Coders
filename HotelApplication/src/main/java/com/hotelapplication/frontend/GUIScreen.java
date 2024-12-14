//GUIScreen.java

package com.hotelapplication.frontend;

import com.hotelapplication.backend.*;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.FlowLayout;



/**
 *
 * @author Alexander Boutselis
 */
public class GUIScreen extends javax.swing.JFrame {

    // --- Style Components ---
    private static final java.awt.Color PRIMARY_BACKGROUND_COLOR = new java.awt.Color(0, 51, 51);
    private static final java.awt.Color SECONDARY_BACKGROUND_COLOR = java.awt.Color.WHITE;
    private static final java.awt.Color BUTTON_BACKGROUND_COLOR = new java.awt.Color(0, 51, 51);
    private static final java.awt.Color BUTTON_TEXT_COLOR = java.awt.Color.WHITE;
    private static final java.awt.Color TILE_BACKGROUND_COLOR = new java.awt.Color(240, 240, 240);

    private static final java.awt.Font HEADER_FONT = new java.awt.Font("Arial", java.awt.Font.BOLD, 36);
    private static final java.awt.Font TILE_HEADER_FONT = new java.awt.Font("Arial", java.awt.Font.BOLD, 24);
    private static final java.awt.Font TILE_BODY_FONT = new java.awt.Font("Arial", java.awt.Font.PLAIN, 18);
    private static final java.awt.Font TILE_BODY_FONT_BOLD = new java.awt.Font("Arial", java.awt.Font.BOLD, 18);
    private static final java.awt.Font BODY_FONT = new java.awt.Font("Arial", java.awt.Font.PLAIN, 18);
    private static final java.awt.Font BODY_FONT_BOLD = new java.awt.Font("Arial", java.awt.Font.BOLD, 18);

    private static final int X_TILE_DIMENSION_ONLY = 600;
    private static final int TILE_FILL_VERTICAL = java.awt.GridBagConstraints.VERTICAL;

    private static final java.awt.Dimension QUIT_BUTTON_DIMENSIONS = new java.awt.Dimension(100, 30);
    private static final java.awt.Dimension SIGN_OUT_BUTTON_DIMENSIONS = new java.awt.Dimension(150, 30);
    private static final java.awt.Dimension STANDARD_BUTTON_DIMENSIONS = new java.awt.Dimension(200, 60);
    private static final java.awt.Dimension STANDARD_FIELD_DIMENSIONS = new java.awt.Dimension(350, 30);
    private static final java.awt.Dimension SIDE_PANEL_FIELD_DIMENSIONS = new java.awt.Dimension(175, 30);
    private static final java.awt.Dimension TILE_DIMENSIONS = new java.awt.Dimension(X_TILE_DIMENSION_ONLY, 200);
    private static final java.awt.Dimension TALL_TILE_DIMENSIONS = new java.awt.Dimension(600, 225);



    private static final java.awt.Insets STANDARD_INSETS = new java.awt.Insets(10, 10, 10, 10);

    private javax.swing.JPanel mainSplitPanel;
    private javax.swing.JPanel turquoiseSidePanel;
    private javax.swing.JPanel contentMainPanel;

    public GUIScreen() {
        initializeComponents();
    }

    private void initializeComponents() {
        mainSplitPanel = new javax.swing.JPanel(new java.awt.BorderLayout());

        turquoiseSidePanel = new javax.swing.JPanel();
        turquoiseSidePanel.setBackground(PRIMARY_BACKGROUND_COLOR);

        contentMainPanel = new javax.swing.JPanel();
        contentMainPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        mainSplitPanel.add(turquoiseSidePanel, java.awt.BorderLayout.WEST);
        mainSplitPanel.add(contentMainPanel, java.awt.BorderLayout.CENTER);

        turquoiseSidePanel.setPreferredSize(new java.awt.Dimension(200, 500));

        this.add(mainSplitPanel);
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(900, 800);
        this.setMinimumSize(new Dimension(900,800));
        this.setLocationRelativeTo(null);
    }


	public void loginScreen() {
        // Clear contentMainPanel
        contentMainPanel.removeAll();

        // --- Main Wrapper Panel for White Background ---
        javax.swing.JPanel whiteBackgroundPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        whiteBackgroundPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        // --- Header Label ---
        javax.swing.JLabel headerTitleLabel = new javax.swing.JLabel("Login");
        headerTitleLabel.setFont(HEADER_FONT);
        headerTitleLabel.setForeground(PRIMARY_BACKGROUND_COLOR);
        headerTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // --- Username Section ---
        javax.swing.JPanel usernamePanel = new javax.swing.JPanel(new java.awt.GridBagLayout());
        usernamePanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        
        javax.swing.JLabel usernameLabel = new javax.swing.JLabel("Username");
        usernameLabel.setFont(BODY_FONT);
        usernameLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JTextField usernameTextField = new javax.swing.JTextField(20);
        usernameTextField.setFont(BODY_FONT);

        javax.swing.JLabel passwordLabel = new javax.swing.JLabel("Password");
        passwordLabel.setFont(BODY_FONT);
        passwordLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JPasswordField passwordTextField = new javax.swing.JPasswordField(20);
        passwordTextField.setFont(BODY_FONT);

        javax.swing.JButton loginButton = new javax.swing.JButton("Login");
        loginButton.setBackground(BUTTON_BACKGROUND_COLOR);
        loginButton.setForeground(BUTTON_TEXT_COLOR);
        loginButton.setFont(BODY_FONT_BOLD);

        javax.swing.JLabel signUpLabel = new javax.swing.JLabel("Don't have an account?");
        signUpLabel.setFont(BODY_FONT);
        signUpLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JButton signUpButton = new javax.swing.JButton("Sign-Up");
        signUpButton.setBackground(BUTTON_BACKGROUND_COLOR);
        signUpButton.setForeground(BUTTON_TEXT_COLOR);
        signUpButton.setFont(BODY_FONT_BOLD);
        
        // --- Quit button ---
        javax.swing.JButton quitActionButton = new javax.swing.JButton("Quit");
        quitActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        quitActionButton.setForeground(BUTTON_TEXT_COLOR);
        quitActionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        // --- Listener for Interaction ---
        quitActionButton.addActionListener(event -> GUIManager.closeApplication());
        signUpButton.addActionListener(event -> signUpScreen());
        loginButton.addActionListener(event -> {
            String enteredUsername = usernameTextField.getText();
            String enteredPassword = new String(passwordTextField.getPassword());

        	AccountManager.accountSignIn(enteredUsername, enteredPassword);
        	if(DatabaseManager.getCurrentUser() == null){
        		JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
        	}else if (DatabaseManager.getCurrentUser() instanceof Manager){
        		managerWelcomeScreen();
        	}else{
            	userWelcomeScreen();
        	}
        });

        // Simulates pressing the login button when pressing Enter
        usernameTextField.addActionListener(event -> {
        	loginButton.doClick(); 
        });

        passwordTextField.addActionListener(event -> {
        	loginButton.doClick(); 
        });

        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = STANDARD_INSETS;

        // Username label above text field
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        usernamePanel.add(usernameLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        usernamePanel.add(usernameTextField, gridBagConstraints);

        // Password label above text field
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        usernamePanel.add(passwordLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        usernamePanel.add(passwordTextField, gridBagConstraints);

        // Login button
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        usernamePanel.add(loginButton, gridBagConstraints);

        // Sign-up label and button on the same row
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        usernamePanel.add(signUpLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        usernamePanel.add(signUpButton, gridBagConstraints);

        // --- Wrapper panel for quit button ---
        javax.swing.JPanel quitButtonPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        quitButtonPanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        quitButtonPanel.add(quitActionButton);
        
        // --- Add Components to White Background Panel ---
        whiteBackgroundPanel.add(headerTitleLabel, java.awt.BorderLayout.NORTH);
        whiteBackgroundPanel.add(usernamePanel, java.awt.BorderLayout.CENTER);
        whiteBackgroundPanel.add(quitButtonPanel, java.awt.BorderLayout.SOUTH);

        // --- Add White Background Panel to Content Main Panel ---
        contentMainPanel.setLayout(new java.awt.BorderLayout());
        contentMainPanel.add(whiteBackgroundPanel, java.awt.BorderLayout.CENTER);

        contentMainPanel.revalidate();
        contentMainPanel.repaint();
    }


	public void signUpScreen() {
        // Clear contentMainPanel
        contentMainPanel.removeAll();

        // --- Main Wrapper Panel for White Background ---
        javax.swing.JPanel whiteBackgroundPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        whiteBackgroundPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        // --- Header Label ---
        javax.swing.JLabel headerTitleLabel = new javax.swing.JLabel("Sign-Up");
        headerTitleLabel.setFont(HEADER_FONT);
        headerTitleLabel.setForeground(PRIMARY_BACKGROUND_COLOR);
        headerTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // --- Panel Setup ---
        javax.swing.JPanel inputPanel = new javax.swing.JPanel(new java.awt.GridBagLayout());
        inputPanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        
        // --- Name Section ---
        javax.swing.JLabel nameLabel = new javax.swing.JLabel("Name");
        nameLabel.setFont(BODY_FONT);
        nameLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JTextField nameTextField = new javax.swing.JTextField(20);
        nameTextField.setFont(BODY_FONT);

        // --- Birthday Section ---
        javax.swing.JLabel birthdayLabel = new javax.swing.JLabel("Date of Birth");
        birthdayLabel.setFont(BODY_FONT);
        birthdayLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        com.toedter.calendar.JDateChooser birthdayChooser = new com.toedter.calendar.JDateChooser();
        birthdayChooser.setFont(BODY_FONT);
        
        // --- Username Section ---
        javax.swing.JLabel usernameLabel = new javax.swing.JLabel("Username");
        usernameLabel.setFont(BODY_FONT);
        usernameLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JTextField usernameTextField = new javax.swing.JTextField(20);
        usernameTextField.setFont(BODY_FONT);

        // --- Password Section ---
        javax.swing.JLabel passwordLabel = new javax.swing.JLabel("Password");
        passwordLabel.setFont(BODY_FONT);
        passwordLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JPasswordField passwordTextField = new javax.swing.JPasswordField(20);
        passwordTextField.setFont(BODY_FONT);

        // --- Sign-Up and Login Buttons ---
        javax.swing.JButton signUpButton = new javax.swing.JButton("Sign-Up");
        signUpButton.setBackground(BUTTON_BACKGROUND_COLOR);
        signUpButton.setForeground(BUTTON_TEXT_COLOR);
        signUpButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));

        javax.swing.JRadioButton managerRadioButton = new javax.swing.JRadioButton("Manager");
        managerRadioButton.setBackground(SECONDARY_BACKGROUND_COLOR);
        managerRadioButton.setFont(BODY_FONT);
        managerRadioButton.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JLabel loginLabel = new javax.swing.JLabel("Already have an account?");
        loginLabel.setFont(BODY_FONT);
        loginLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JButton loginButton = new javax.swing.JButton("Login");
        loginButton.setBackground(BUTTON_BACKGROUND_COLOR);
        loginButton.setForeground(BUTTON_TEXT_COLOR);
        loginButton.setFont(BODY_FONT_BOLD);
        
        
        // --- Quit button ---
        javax.swing.JButton quitActionButton = new javax.swing.JButton("Quit");
        quitActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        quitActionButton.setForeground(BUTTON_TEXT_COLOR);
        quitActionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);
        quitActionButton.setFont(BODY_FONT_BOLD);


        // --- Listener for Interaction ---
        quitActionButton.addActionListener(event -> GUIManager.closeApplication());
        loginButton.addActionListener(event -> loginScreen());
        signUpButton.addActionListener(event -> {

        	String enteredPassphrase = "";
		    if(managerRadioButton.isSelected()){
		        enteredPassphrase = JOptionPane.showInputDialog(
		            this,
		            "Enter Employee Passphrase:",
		            "Employee Authentication",
		            JOptionPane.PLAIN_MESSAGE
		        );

		        if (enteredPassphrase.equals(AccountManager.getManagerPassword())) {
		            System.out.println("Entered the correct Passphrase");
		        } else {
		            System.out.println("Entered Passphrase: " + enteredPassphrase);
		        }
		    }

        	String enteredName = nameTextField.getText();
        	String[] nameParts = enteredName.split(" ");
        	String enteredFirstName;
        	String enteredLasttName;

			if (nameParts.length >= 2) {
			    enteredFirstName = nameParts[0];
			    enteredLasttName = nameParts[1];
			}else{
				enteredFirstName = enteredName;
			    enteredLasttName = "";
			}
        	String enteredUsername = usernameTextField.getText();
        	String enteredPassword = new String(passwordTextField.getPassword());
        	Date enteredDate = birthdayChooser.getDate();
        	Calendar enteredBirthday = Calendar.getInstance();
        	if (enteredDate == null || enteredName.trim().isEmpty() || enteredUsername == null || enteredPassword == null){
        		JOptionPane.showMessageDialog(this, "Missing Sign-Up Information, please fill out all the fields and try again!", "Sign-Up Error", JOptionPane.ERROR_MESSAGE);
        	}else{
    				enteredBirthday.setTime(enteredDate);

        		if(enteredPassphrase.equals(AccountManager.getManagerPassword())){
        			AccountManager.createManager(enteredFirstName, enteredLasttName, enteredBirthday, enteredUsername, enteredPassword);
        		}else{
        			AccountManager.createUser(enteredFirstName, enteredLasttName, enteredBirthday, enteredUsername, enteredPassword);
        		}
	
        		if(DatabaseManager.getCurrentUser() == null){
        			JOptionPane.showMessageDialog(this, "Invalid username or password", "Sign-Up Error", JOptionPane.ERROR_MESSAGE);
        		}else if (DatabaseManager.getCurrentUser() instanceof Manager){
        			managerWelcomeScreen();
        		}else{
            		userWelcomeScreen();
        		}
        	}
        });

        // Simulates pressing the login button when pressing Enter
        nameTextField.addActionListener(event -> {
        	signUpButton.doClick(); 
        });
        usernameTextField.addActionListener(event -> {
        	signUpButton.doClick(); 
        });
        passwordTextField.addActionListener(event -> {
        	signUpButton.doClick(); 
        });

        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = STANDARD_INSETS;

        // --- Layout ---
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        inputPanel.add(nameLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        inputPanel.add(nameTextField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        inputPanel.add(birthdayLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        inputPanel.add(birthdayChooser, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        inputPanel.add(usernameLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        inputPanel.add(usernameTextField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        inputPanel.add(passwordLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        inputPanel.add(passwordTextField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        inputPanel.add(signUpButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        inputPanel.add(managerRadioButton, gridBagConstraints);
        

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        inputPanel.add(loginLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        inputPanel.add(loginButton, gridBagConstraints);

        // --- Wrapper panel for quit button ---
        javax.swing.JPanel quitButtonPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        quitButtonPanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        quitButtonPanel.add(quitActionButton);
        
        // --- Add Components to White Background Panel ---
        whiteBackgroundPanel.add(headerTitleLabel, java.awt.BorderLayout.NORTH);
        whiteBackgroundPanel.add(inputPanel, java.awt.BorderLayout.CENTER);
        whiteBackgroundPanel.add(quitButtonPanel, java.awt.BorderLayout.SOUTH);

        // --- Add White Background Panel to Content Main Panel ---
        contentMainPanel.setLayout(new java.awt.BorderLayout());
        contentMainPanel.add(whiteBackgroundPanel, java.awt.BorderLayout.CENTER);

        contentMainPanel.revalidate();
        contentMainPanel.repaint();
    }


	public void userWelcomeScreen() {
        //Clear contentMainPanel
        contentMainPanel.removeAll();

        // --- Main Wrapper Panel for White Background ---
        javax.swing.JPanel whiteBackgroundPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        whiteBackgroundPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        // --- Header Label ---
        javax.swing.JLabel headerTitleLabel = new javax.swing.JLabel("Welcome "+ AccountManager.getFirstName());
        headerTitleLabel.setFont(HEADER_FONT);
        headerTitleLabel.setForeground(PRIMARY_BACKGROUND_COLOR);
        headerTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // --- Buttons ---
        javax.swing.JButton selectHotelActionButton = new javax.swing.JButton("Select Hotel");
        selectHotelActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        selectHotelActionButton.setForeground(BUTTON_TEXT_COLOR);
        selectHotelActionButton.setPreferredSize(STANDARD_BUTTON_DIMENSIONS);

        javax.swing.JButton editAccountActionButton = new javax.swing.JButton("Edit My Account");
        editAccountActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        editAccountActionButton.setForeground(BUTTON_TEXT_COLOR);
        editAccountActionButton.setPreferredSize(STANDARD_BUTTON_DIMENSIONS);

        javax.swing.JButton makeReservationActionButton = new javax.swing.JButton("Make a reservation");
        makeReservationActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        makeReservationActionButton.setForeground(BUTTON_TEXT_COLOR);
        makeReservationActionButton.setPreferredSize(STANDARD_BUTTON_DIMENSIONS);

        javax.swing.JButton viewReservationsActionButton = new javax.swing.JButton("View My Reservations");
        viewReservationsActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        viewReservationsActionButton.setForeground(BUTTON_TEXT_COLOR);
        viewReservationsActionButton.setPreferredSize(STANDARD_BUTTON_DIMENSIONS);

        javax.swing.JButton quitActionButton = new javax.swing.JButton("Quit");
        quitActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        quitActionButton.setForeground(BUTTON_TEXT_COLOR);
        quitActionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);


        // --- Listener for Interaction ---
        selectHotelActionButton.addActionListener(event -> selectHotelScreen());
        viewReservationsActionButton.addActionListener(event -> {

            ArrayList<Reservation> reservations = new ArrayList<Reservation>();
            for (int reservationID : AccountManager.getAllReservationNumbers()) {
               reservations.add(ReservationManager.getReservation(reservationID));
            }
            viewReservationsScreen(reservations);
        });
        editAccountActionButton.addActionListener(event -> editAccountScreen());
        makeReservationActionButton.addActionListener(event -> {
        if (DatabaseManager.getCurrentHotel() != null) {
            makeReservationScreen(HotelManager.getAllHotelRooms(), null, null, null, null);
        }else{
            JOptionPane.showMessageDialog(this, "You must first Select a Hotel", "Make Reservation Error", JOptionPane.ERROR_MESSAGE);
        }

        });

        quitActionButton.addActionListener(event -> GUIManager.closeApplication());

        // --- Buttons Panel with GridBagLayout ---
        javax.swing.JPanel buttonsPanel = new javax.swing.JPanel(new java.awt.GridBagLayout());
        buttonsPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = STANDARD_INSETS;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        buttonsPanel.add(selectHotelActionButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        buttonsPanel.add(editAccountActionButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        buttonsPanel.add(makeReservationActionButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        buttonsPanel.add(viewReservationsActionButton, gridBagConstraints);

        // --- Wrapper panel for quit button ---
        javax.swing.JPanel quitButtonPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        quitButtonPanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        quitButtonPanel.add(quitActionButton);

        // --- Add Components to White Background Panel ---
        whiteBackgroundPanel.add(headerTitleLabel, java.awt.BorderLayout.NORTH);
        whiteBackgroundPanel.add(buttonsPanel, java.awt.BorderLayout.CENTER);
        whiteBackgroundPanel.add(quitButtonPanel, java.awt.BorderLayout.SOUTH);

        // --- Add White Background Panel to Content Main Panel ---
        contentMainPanel.setLayout(new java.awt.BorderLayout());
        contentMainPanel.add(whiteBackgroundPanel, java.awt.BorderLayout.CENTER);

        contentMainPanel.revalidate();
        contentMainPanel.repaint();
    }


    public void editAccountScreen(){
    	String usersName = AccountManager.getFullName();
    	String usersUsername = AccountManager.getUsername();
    	String userspassword = AccountManager.getPassword();
    	Calendar usersBirthday = AccountManager.getBirthday();

    	// Clear contentMainPanel
        contentMainPanel.removeAll();

        // --- Main Wrapper Panel for White Background ---
        javax.swing.JPanel whiteBackgroundPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        whiteBackgroundPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        // --- Header Label ---
        javax.swing.JLabel headerTitleLabel = new javax.swing.JLabel("Edit Account");
        headerTitleLabel.setFont(HEADER_FONT);
        headerTitleLabel.setForeground(PRIMARY_BACKGROUND_COLOR);
        headerTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // --- Buttons ---
        javax.swing.JButton actionButton = new javax.swing.JButton("Back");
        actionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        actionButton.setForeground(BUTTON_TEXT_COLOR);
        actionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        javax.swing.JButton quitActionButton = new javax.swing.JButton("Quit");
        quitActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        quitActionButton.setForeground(BUTTON_TEXT_COLOR);
        quitActionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        // --- Name Section ---
        javax.swing.JLabel nameLabel = new javax.swing.JLabel("Name");
        nameLabel.setFont(BODY_FONT);
        nameLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JTextField nameTextField = new javax.swing.JTextField(20);
        nameTextField.setFont(BODY_FONT);
        nameTextField.setText(usersName);
        nameTextField.setPreferredSize(STANDARD_FIELD_DIMENSIONS);


        // --- Birthday Section ---
        javax.swing.JLabel birthdayLabel = new javax.swing.JLabel("Date of Birth");
        birthdayLabel.setFont(BODY_FONT);
        birthdayLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        com.toedter.calendar.JDateChooser birthdayChooser = new com.toedter.calendar.JDateChooser();
        birthdayChooser.setFont(BODY_FONT);
        birthdayChooser.setPreferredSize(STANDARD_FIELD_DIMENSIONS);
        birthdayChooser.setCalendar(usersBirthday);

		// --- Username Section ---
        javax.swing.JLabel usernameLabel = new javax.swing.JLabel("Username");
        usernameLabel.setFont(BODY_FONT);
        usernameLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JTextField usernameTextField = new javax.swing.JTextField(20);
        usernameTextField.setFont(BODY_FONT);
        usernameTextField.setText(usersUsername);
        usernameTextField.setPreferredSize(STANDARD_FIELD_DIMENSIONS);


		// --- Password Section ---
		javax.swing.JLabel passwordLabel = new javax.swing.JLabel("Change Password:");
        passwordLabel.setFont(BODY_FONT_BOLD);
        passwordLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JLabel oldPasswordLabel = new javax.swing.JLabel("Old Password");
        oldPasswordLabel.setFont(BODY_FONT);
        oldPasswordLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JPasswordField oldPasswordTextField = new javax.swing.JPasswordField(20);
        oldPasswordTextField.setFont(BODY_FONT);
        oldPasswordTextField.setPreferredSize(STANDARD_FIELD_DIMENSIONS);


        javax.swing.JLabel newPassword1Label = new javax.swing.JLabel("New Password");
        newPassword1Label.setFont(BODY_FONT);
        newPassword1Label.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JPasswordField newPassword1TextField = new javax.swing.JPasswordField(20);
        newPassword1TextField.setFont(BODY_FONT);
        newPassword1TextField.setPreferredSize(STANDARD_FIELD_DIMENSIONS);


        javax.swing.JLabel newPassword2Label = new javax.swing.JLabel("Re-type Password");
        newPassword2Label.setFont(BODY_FONT);
        newPassword2Label.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JPasswordField newPassword2TextField = new javax.swing.JPasswordField(20);
        newPassword2TextField.setFont(BODY_FONT);
        newPassword2TextField.setPreferredSize(STANDARD_FIELD_DIMENSIONS);



        // --- Save and Discard Buttons ---
        javax.swing.JButton saveChangesButton = new javax.swing.JButton("Save");
        saveChangesButton.setBackground(BUTTON_BACKGROUND_COLOR);
        saveChangesButton.setForeground(BUTTON_TEXT_COLOR);
        saveChangesButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        javax.swing.JButton discardChangesButton = new javax.swing.JButton("Discrad");
        discardChangesButton.setBackground(BUTTON_BACKGROUND_COLOR);
        discardChangesButton.setForeground(BUTTON_TEXT_COLOR);
        discardChangesButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);
        
        // --- Sign-Out and Delete Account Buttons ---
        javax.swing.JButton signOutButton = new javax.swing.JButton("Sign-Out");
        signOutButton.setBackground(BUTTON_BACKGROUND_COLOR);
        signOutButton.setForeground(BUTTON_TEXT_COLOR);
        signOutButton.setPreferredSize(SIGN_OUT_BUTTON_DIMENSIONS);

        javax.swing.JButton deleteAccountButton = new javax.swing.JButton("Delete Account");
        deleteAccountButton.setBackground(BUTTON_BACKGROUND_COLOR);
        deleteAccountButton.setForeground(BUTTON_TEXT_COLOR);
        deleteAccountButton.setPreferredSize(SIGN_OUT_BUTTON_DIMENSIONS);

        // --- Listener for Interaction ---
        quitActionButton.addActionListener(event -> GUIManager.closeApplication());
        actionButton.addActionListener(event -> returnToScreen());
        signOutButton.addActionListener(event-> {
        	AccountManager.signOut();
        	returnToScreen();
    	});
    	deleteAccountButton.addActionListener(event-> {
        	int confirmation = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to delete your account? This action cannot be undone.",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                AccountManager.removeAccount();
                returnToScreen();
            }
    	});
        discardChangesButton.addActionListener(event-> {
        	returnToScreen();
        });

        saveChangesButton.addActionListener(event-> {
        	//Check that feilds have correct input

        	String newName;
        	String newUsername;
        	Calendar newBirthday;
			String newPassword1;
			String newPassword2;
			String oldPassword;

        	newPassword1 = newPassword1TextField.getText();
        	newPassword2 = newPassword2TextField.getText();
        	oldPassword = oldPasswordTextField.getText();

        	newName = nameTextField.getText();
        	newUsername = usernameTextField.getText();
        	newBirthday = birthdayChooser.getCalendar();
	    	
        	if (!usersName.equals(newName)) {
        		String[] nameParts = newName.split(" ");
        		String firstName;
        		String lasttName;

				if (nameParts.length >= 2) {
				    firstName = nameParts[0];
				    lasttName = nameParts[1];
				}else{
					firstName = newName;
				    lasttName = "";
				}

				AccountManager.setFirstName(firstName);
				AccountManager.setLastName(lasttName);
        	}

        	if (!usersUsername.equals(newUsername)) {
        		AccountManager.setUsername(newUsername);
        	}

        	if (usersBirthday != newBirthday) {
        		AccountManager.setBirthday(newBirthday);
        	}

        	if (!oldPassword.trim().isEmpty()) {
        		if (newPassword1.equals(newPassword2) && oldPassword.equals(userspassword) && !newPassword1.equals(oldPassword)) {
		        	newPassword1 = newPassword1TextField.getText();
		        	if (!userspassword.equals(newPassword1)) {
		        		AccountManager.setPassword(newPassword1);
		        	}

				}else{
	        		JOptionPane.showMessageDialog(this, "Error, please make sure the old password and new password are different.\nPlease make sure new password matches", "Edit Account Error", JOptionPane.ERROR_MESSAGE);
	        	}
        	}
			returnToScreen();
        });
        
        // --- Layout ---
        javax.swing.JPanel buttonsPanel = new javax.swing.JPanel(new java.awt.GridBagLayout());
        buttonsPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = STANDARD_INSETS;
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        buttonsPanel.add(nameLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        buttonsPanel.add(nameTextField, gridBagConstraints);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        buttonsPanel.add(birthdayLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        buttonsPanel.add(birthdayChooser, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        buttonsPanel.add(usernameLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        buttonsPanel.add(usernameTextField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        buttonsPanel.add(passwordLabel, gridBagConstraints);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        buttonsPanel.add(oldPasswordLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        buttonsPanel.add(oldPasswordTextField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        buttonsPanel.add(newPassword1Label, gridBagConstraints);

		gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        buttonsPanel.add(newPassword1TextField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        buttonsPanel.add(newPassword2Label, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        buttonsPanel.add(newPassword2TextField, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        buttonsPanel.add(discardChangesButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        buttonsPanel.add(saveChangesButton, gridBagConstraints);

        // --- Wrapper panel for quit button ---
        javax.swing.JPanel quitButtonPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        quitButtonPanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        quitButtonPanel.add(actionButton);
        quitButtonPanel.add(quitActionButton);
        

        // --- Wrapper panel for Sign-out and Delete button ---
        javax.swing.JPanel signOutAndDeletePanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        signOutAndDeletePanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        signOutAndDeletePanel.add(signOutButton);
        signOutAndDeletePanel.add(deleteAccountButton);

        // --- Add Components to White Background Panel ---
        whiteBackgroundPanel.add(headerTitleLabel, java.awt.BorderLayout.NORTH);
        whiteBackgroundPanel.add(buttonsPanel, java.awt.BorderLayout.CENTER);
        whiteBackgroundPanel.add(signOutAndDeletePanel, java.awt.BorderLayout.SOUTH);

        // --- Add White Background Panel to Content Main Panel ---
        contentMainPanel.setLayout(new java.awt.BorderLayout());
        contentMainPanel.add(whiteBackgroundPanel, java.awt.BorderLayout.CENTER);
        contentMainPanel.add(quitButtonPanel, java.awt.BorderLayout.SOUTH);


        contentMainPanel.revalidate();
        contentMainPanel.repaint();
    }


    public void selectHotelScreen() {
        // Get List of all Hotels
        ArrayList<Hotel> hotels = DatabaseManager.getAllHotels();

        // Clear contentMainPanel
        contentMainPanel.removeAll();

        // --- Main Wrapper Panel for White Background ---
        javax.swing.JPanel whiteBackgroundPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        whiteBackgroundPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        // --- Header Label ---
        javax.swing.JLabel headerTitleLabel = new javax.swing.JLabel("Select Hotel");
        headerTitleLabel.setFont(HEADER_FONT);
        headerTitleLabel.setForeground(PRIMARY_BACKGROUND_COLOR);
        headerTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // --- Hotel Tiles Panel ---
        javax.swing.JPanel hotelTilesPanel = new javax.swing.JPanel(new java.awt.GridBagLayout());
        hotelTilesPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = STANDARD_INSETS;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        for (Hotel hotel : hotels) {

            javax.swing.JPanel hotelTile = new javax.swing.JPanel(new java.awt.GridBagLayout());
            hotelTile.setPreferredSize(TILE_DIMENSIONS);
            hotelTile.setBackground(TILE_BACKGROUND_COLOR);
            hotelTile.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));


            java.awt.GridBagConstraints tileConstraints = new java.awt.GridBagConstraints();
            tileConstraints.insets = STANDARD_INSETS;
            tileConstraints.gridx = 0;
            tileConstraints.gridy = 0;
            tileConstraints.anchor = java.awt.GridBagConstraints.CENTER;


            // Spacer
            javax.swing.JPanel gapSpacer = new javax.swing.JPanel(new java.awt.GridBagLayout());
            gapSpacer.setBackground(TILE_BACKGROUND_COLOR);
            hotelTile.add(gapSpacer, tileConstraints);
            tileConstraints.gridy++;



            // Hotel Name
            javax.swing.JLabel hotelNameLabel = new javax.swing.JLabel(HotelManager.getHotelName(hotel));
            hotelNameLabel.setFont(TILE_HEADER_FONT);
            tileConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
            hotelTile.add(hotelNameLabel, tileConstraints);
            tileConstraints.gridy++;

            // Hotel Address
            javax.swing.JLabel hotelAddressLabel = new javax.swing.JLabel(HotelManager.getHotelAddress(hotel));
            hotelAddressLabel.setFont(TILE_BODY_FONT);
            tileConstraints.anchor = java.awt.GridBagConstraints.CENTER;
            hotelTile.add(hotelAddressLabel, tileConstraints);
            tileConstraints.gridy++;


            // Number of Rooms
            javax.swing.JLabel hotelRoomsLabel = new javax.swing.JLabel("Number of Rooms: " + HotelManager.getTotalNumberOfRooms(hotel));
            hotelRoomsLabel.setFont(TILE_BODY_FONT);
            tileConstraints.anchor = java.awt.GridBagConstraints.CENTER;
            hotelTile.add(hotelRoomsLabel, tileConstraints);
            tileConstraints.gridy++;

            // Select Button
            javax.swing.JButton selectButton = new javax.swing.JButton("Select");
            selectButton.setBackground(BUTTON_BACKGROUND_COLOR);
            selectButton.setForeground(BUTTON_TEXT_COLOR);
            selectButton.setFont(BODY_FONT_BOLD);
            selectButton.addActionListener(event -> {
                DatabaseManager.setCurrentHotel(hotel);
                returnToScreen();
            });
            tileConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            hotelTile.add(selectButton, tileConstraints);

            hotelTile.add(gapSpacer, tileConstraints);
            tileConstraints.gridy++;
            //Gurg

            hotelTilesPanel.add(hotelTile, gridBagConstraints);
            gridBagConstraints.gridy++;

        }

        // Add a scroll pane for the hotel tiles panel
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(hotelTilesPanel);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);        

        // --- Buttons ---
        javax.swing.JButton actionButton = new javax.swing.JButton("Back");
        actionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        actionButton.setForeground(BUTTON_TEXT_COLOR);
        actionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        javax.swing.JButton quitActionButton = new javax.swing.JButton("Quit");
        quitActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        quitActionButton.setForeground(BUTTON_TEXT_COLOR);
        quitActionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        // --- Listener for Interaction ---
        quitActionButton.addActionListener(event -> GUIManager.closeApplication());
        actionButton.addActionListener(event -> returnToScreen());

        // --- Wrapper panel for quit button ---
        javax.swing.JPanel quitButtonPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        quitButtonPanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        quitButtonPanel.add(actionButton);
        quitButtonPanel.add(quitActionButton);

        // --- Add Components to White Background Panel ---
        whiteBackgroundPanel.add(headerTitleLabel, java.awt.BorderLayout.NORTH);
        whiteBackgroundPanel.add(scrollPane, java.awt.BorderLayout.CENTER);
        whiteBackgroundPanel.add(quitButtonPanel, java.awt.BorderLayout.SOUTH);

        // --- Add White Background Panel to Content Main Panel ---
        contentMainPanel.setLayout(new java.awt.BorderLayout());
        contentMainPanel.add(whiteBackgroundPanel, java.awt.BorderLayout.CENTER);

        contentMainPanel.revalidate();
        contentMainPanel.repaint();
    }


	public void viewReservationsScreen(ArrayList<Reservation> reservations) { //Need to add ability to Edit Reservation when viewing

	    // Get List of all Hotels
        ArrayList<Hotel> hotels = DatabaseManager.getAllHotels();

        // Clear contentMainPanel
        contentMainPanel.removeAll();

        // --- Main Wrapper Panel for White Background ---
        javax.swing.JPanel whiteBackgroundPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        whiteBackgroundPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        // --- Header Label ---
        javax.swing.JLabel headerTitleLabel = new javax.swing.JLabel("My Reservations");
        headerTitleLabel.setFont(HEADER_FONT);
        headerTitleLabel.setForeground(PRIMARY_BACKGROUND_COLOR);
        headerTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // --- Reservation Tiles Panel ---
        javax.swing.JPanel reservationTilesPanel = new javax.swing.JPanel(new java.awt.GridBagLayout());
        reservationTilesPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = STANDARD_INSETS;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        for (Reservation reservation : reservations) {


            javax.swing.JPanel reservationTile = new javax.swing.JPanel(new java.awt.GridBagLayout());
            reservationTile.setPreferredSize(TALL_TILE_DIMENSIONS);
            reservationTile.setBackground(TILE_BACKGROUND_COLOR);
            reservationTile.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));


            java.awt.GridBagConstraints tileConstraints = new java.awt.GridBagConstraints();
            tileConstraints.insets = STANDARD_INSETS;
            tileConstraints.gridx = 0;
            tileConstraints.gridy = 0;
            tileConstraints.anchor = java.awt.GridBagConstraints.CENTER;


            // Spacer
            javax.swing.JPanel gapSpacer = new javax.swing.JPanel(new java.awt.GridBagLayout());
            gapSpacer.setBackground(TILE_BACKGROUND_COLOR);
            reservationTile.add(gapSpacer, tileConstraints);
            tileConstraints.gridy++;


            // Reservation ID
            javax.swing.JLabel reservationIdLabel = new javax.swing.JLabel("Reservation ID: " + ReservationManager.getReservationID(reservation));
            reservationIdLabel.setFont(TILE_HEADER_FONT);
            tileConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
            reservationTile.add(reservationIdLabel, tileConstraints);
            tileConstraints.gridy++;

            //Room ID
            javax.swing.JLabel roomIdLabel = new javax.swing.JLabel("Room ID: " + ReservationManager.getRoomID(reservation) + "   User: " + ReservationManager.getAssignedUserID(reservation));
            roomIdLabel.setFont(TILE_BODY_FONT);
            tileConstraints.anchor = java.awt.GridBagConstraints.CENTER;
            reservationTile.add(roomIdLabel, tileConstraints);
            tileConstraints.gridy++;

            // Start Date
            javax.swing.JLabel startDateLabel = new javax.swing.JLabel("Start Date: " + ReservationManager.getStartDate(reservation).toString());
            startDateLabel.setFont(TILE_BODY_FONT_BOLD);
            tileConstraints.anchor = java.awt.GridBagConstraints.CENTER;
            reservationTile.add(startDateLabel, tileConstraints);
            tileConstraints.gridy++;

            // End Date
            javax.swing.JLabel endDateLabel = new javax.swing.JLabel("End Date: " + ReservationManager.getEndDate(reservation).toString());
            endDateLabel.setFont(TILE_BODY_FONT_BOLD);
            tileConstraints.anchor = java.awt.GridBagConstraints.CENTER;
            reservationTile.add(endDateLabel, tileConstraints);
            tileConstraints.gridy++;

            // Cancel Reservation Button
            javax.swing.JButton cancelReservationButton = new javax.swing.JButton("Cancel");
            cancelReservationButton.setBackground(BUTTON_BACKGROUND_COLOR);
            cancelReservationButton.setForeground(BUTTON_TEXT_COLOR);
            cancelReservationButton.setFont(BODY_FONT_BOLD);

            // Edit Reservation Button
            javax.swing.JButton editReservationButton = new javax.swing.JButton("Edit");
            editReservationButton.setBackground(BUTTON_BACKGROUND_COLOR);
            editReservationButton.setForeground(BUTTON_TEXT_COLOR);
            editReservationButton.setFont(BODY_FONT_BOLD);

            editReservationButton.addActionListener(event -> {

                Room room = ReservationManager.getRoom(reservation);
                Calendar startDate = ReservationManager.convertLocalDateToCalendar(ReservationManager.getStartDate(reservation));
                Calendar endDate = ReservationManager.convertLocalDateToCalendar(ReservationManager.getEndDate(reservation));

                int hotelID = ReservationManager.getRoomID(reservation) / 1000;
                DatabaseManager.setCurrentHotel(DatabaseManager.getHotel(hotelID));

                ArrayList<Room> filteredRoomList = ReservationManager.filterRooms(HotelManager.getAllHotelRooms(), RoomManager.getNumberOfBeds(room), RoomManager.getBedType(room), startDate, endDate);

                editReservationScreen(reservation, filteredRoomList, RoomManager.getNumberOfBeds(room), RoomManager.getBedType(room), startDate, endDate); 

            });

            cancelReservationButton.addActionListener(event -> {

                ReservationManager.cancelReservation(reservation);
                reservations.remove(reservation);
                viewReservationsScreen(reservations); 

            });

            tileConstraints.anchor = java.awt.GridBagConstraints.EAST;
            reservationTile.add(editReservationButton, tileConstraints);
            tileConstraints.anchor = java.awt.GridBagConstraints.WEST;
            reservationTile.add(cancelReservationButton, tileConstraints);

            reservationTile.add(gapSpacer, tileConstraints);
            tileConstraints.gridy++;
            reservationTilesPanel.add(reservationTile, gridBagConstraints);
            gridBagConstraints.gridy++;


        }

        // Add a scroll pane for the hotel tiles panel
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(reservationTilesPanel);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);        

        // --- Buttons ---
        javax.swing.JButton actionButton = new javax.swing.JButton("Back");
        actionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        actionButton.setForeground(BUTTON_TEXT_COLOR);
        actionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        javax.swing.JButton quitActionButton = new javax.swing.JButton("Quit");
        quitActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        quitActionButton.setForeground(BUTTON_TEXT_COLOR);
        quitActionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        // --- Listener for Interaction ---
        quitActionButton.addActionListener(event -> GUIManager.closeApplication());
        actionButton.addActionListener(event -> returnToScreen());

        // --- Wrapper panel for quit button ---
        javax.swing.JPanel quitButtonPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        quitButtonPanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        quitButtonPanel.add(actionButton);
        quitButtonPanel.add(quitActionButton);

        // --- Add Components to White Background Panel ---
        whiteBackgroundPanel.add(headerTitleLabel, java.awt.BorderLayout.NORTH);
        whiteBackgroundPanel.add(scrollPane, java.awt.BorderLayout.CENTER);
        whiteBackgroundPanel.add(quitButtonPanel, java.awt.BorderLayout.SOUTH);

        // --- Add White Background Panel to Content Main Panel ---
        contentMainPanel.setLayout(new java.awt.BorderLayout());
        contentMainPanel.add(whiteBackgroundPanel, java.awt.BorderLayout.CENTER);

        contentMainPanel.revalidate();
        contentMainPanel.repaint();
	}

        
	public void makeReservationScreen(ArrayList<Room> listOfRooms, Integer previousNumOfBeds, String  previousBedType, Calendar  previousStartDate, Calendar  previousEndDate) {
        // All Hotel Rooms
        ArrayList<Room> rooms = listOfRooms;
        
        

        //Number of beds in Rooms
        ArrayList<Integer> numOfBedsList = new ArrayList<>();
        ArrayList<String> bedTypesList = new ArrayList<>();

        // Clear contentMainPanel
        contentMainPanel.removeAll();

        // --- Main Wrapper Panel for White Background ---
        javax.swing.JPanel whiteBackgroundPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        whiteBackgroundPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        // --- Header Label ---
        javax.swing.JLabel headerTitleLabel = new javax.swing.JLabel("Make a Reservation");
        headerTitleLabel.setFont(HEADER_FONT);
        headerTitleLabel.setForeground(PRIMARY_BACKGROUND_COLOR);
        headerTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // --- Hotel Tiles Panel ---
        javax.swing.JPanel roomTilesPanel = new javax.swing.JPanel(new java.awt.GridBagLayout());
        roomTilesPanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        roomTilesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));


        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = STANDARD_INSETS;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;


        // --- Dropdown Boxes ---
        javax.swing.JLabel numBedsLabel = new javax.swing.JLabel("Number of Beds:");
        numBedsLabel.setFont(BODY_FONT);
        numBedsLabel.setForeground(SECONDARY_BACKGROUND_COLOR);

        JComboBox<Integer> numberOfBedDropdown = new JComboBox<>();
        numberOfBedDropdown.addItem(null);
        
        numberOfBedDropdown.setFont(TILE_BODY_FONT);
        numberOfBedDropdown.setPreferredSize(SIDE_PANEL_FIELD_DIMENSIONS);

        javax.swing.JLabel bedTypeLabel = new javax.swing.JLabel("Bed Type:");
        bedTypeLabel.setFont(BODY_FONT);
        bedTypeLabel.setForeground(SECONDARY_BACKGROUND_COLOR);

        JComboBox<String> bedTypeDropdown = new JComboBox<>();
        bedTypeDropdown.addItem(bedTypeLabel.getText());
        
        bedTypeDropdown.setFont(TILE_BODY_FONT);
        bedTypeDropdown.setPreferredSize(SIDE_PANEL_FIELD_DIMENSIONS);

        // --- Dates ---
        javax.swing.JLabel startDateLabel = new javax.swing.JLabel("Start Date");
        startDateLabel.setFont(BODY_FONT);
        startDateLabel.setForeground(SECONDARY_BACKGROUND_COLOR);

        com.toedter.calendar.JDateChooser startDateChooser = new com.toedter.calendar.JDateChooser();
        startDateChooser.setFont(BODY_FONT);
        startDateChooser.setPreferredSize(SIDE_PANEL_FIELD_DIMENSIONS);

        if (previousStartDate != null) {
            Calendar previousStartCalendar = previousStartDate;
            startDateChooser.setCalendar(previousStartCalendar);
        }

        javax.swing.JLabel endDateLabel = new javax.swing.JLabel("End Date");
        endDateLabel.setFont(BODY_FONT);
        endDateLabel.setForeground(SECONDARY_BACKGROUND_COLOR);

        com.toedter.calendar.JDateChooser endDateChooser = new com.toedter.calendar.JDateChooser();
        endDateChooser.setFont(BODY_FONT);
        endDateChooser.setPreferredSize(SIDE_PANEL_FIELD_DIMENSIONS);

        if (previousEndDate != null) {
            Calendar previousEndCalendar = previousEndDate;
            endDateChooser.setCalendar(previousEndCalendar);
        }

        
        javax.swing.JButton applyButton = new javax.swing.JButton("Apply");
        applyButton.setBackground(BUTTON_BACKGROUND_COLOR);
        applyButton.setForeground(SECONDARY_BACKGROUND_COLOR);
        applyButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);
        applyButton.setBorder(javax.swing.BorderFactory.createLineBorder(SECONDARY_BACKGROUND_COLOR));

        javax.swing.JButton resetButton = new javax.swing.JButton("Reset");
        resetButton.setBackground(BUTTON_BACKGROUND_COLOR);
        resetButton.setForeground(SECONDARY_BACKGROUND_COLOR);
        resetButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);
        resetButton.setBorder(javax.swing.BorderFactory.createLineBorder(SECONDARY_BACKGROUND_COLOR));


        for (Room room : rooms) {
            javax.swing.JPanel roomTile = new javax.swing.JPanel(new java.awt.GridBagLayout());
            
            roomTile.setBackground(TILE_BACKGROUND_COLOR);
            roomTile.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));



            java.awt.GridBagConstraints tileConstraints = new java.awt.GridBagConstraints();
            tileConstraints.insets = STANDARD_INSETS;
            tileConstraints.gridx = 0;
            tileConstraints.gridy = 0;
            tileConstraints.anchor = java.awt.GridBagConstraints.CENTER;
            tileConstraints.fill = TILE_FILL_VERTICAL;


            // Spacer
            javax.swing.JPanel gapSpacer = new javax.swing.JPanel(new java.awt.GridBagLayout());
            gapSpacer.setBackground(TILE_BACKGROUND_COLOR);
            roomTile.add(gapSpacer, tileConstraints);
            tileConstraints.gridy++;


            // Room Number
            javax.swing.JLabel roomNumberLabel = new javax.swing.JLabel("Room Number: " + String.valueOf(RoomManager.getRoomNumber(room)));
            roomNumberLabel.setFont(TILE_HEADER_FONT);
            tileConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
            roomTile.add(roomNumberLabel, tileConstraints);
            tileConstraints.gridy++;

            
            // Number of Beds and Bed Type
            javax.swing.JLabel roomBedsLabel;
            if (RoomManager.getNumberOfBeds(room) > 1) {
                roomBedsLabel = new javax.swing.JLabel(String.valueOf(RoomManager.getNumberOfBeds(room) + " " + RoomManager.getBedType(room) + " sized beds"));
                
            }else{
                roomBedsLabel = new javax.swing.JLabel(String.valueOf(RoomManager.getNumberOfBeds(room) + " " + RoomManager.getBedType(room) + " sized bed"));
            }
            roomBedsLabel.setFont(TILE_BODY_FONT);
            tileConstraints.anchor = java.awt.GridBagConstraints.CENTER;
            roomTile.add(roomBedsLabel, tileConstraints);
            tileConstraints.gridy++;

            
            // Description
            String[] descriptionLines = RoomManager.getRoomDescription(room).split("\\n");
            for (String line : descriptionLines) {
                javax.swing.JLabel roomDescriptionLabel = new javax.swing.JLabel(line);
                roomDescriptionLabel.setFont(TILE_BODY_FONT);
                tileConstraints.anchor = java.awt.GridBagConstraints.CENTER;
                roomTile.add(roomDescriptionLabel, tileConstraints);
                tileConstraints.gridy++;
            }
            
            // Select Button
            javax.swing.JButton selectButton = new javax.swing.JButton("Select");
            selectButton.setBackground(BUTTON_BACKGROUND_COLOR);
            selectButton.setForeground(BUTTON_TEXT_COLOR);
            selectButton.setFont(BODY_FONT_BOLD);

            selectButton.addActionListener(event -> {
            Integer numOfBeds = null;
            String bedType = null;
            String defaultBedType = bedTypeLabel.getText();
            Calendar startDate = null;
            Calendar endDate = null;

            if((Integer) numberOfBedDropdown.getSelectedItem() != null){
                numOfBeds = (Integer) numberOfBedDropdown.getSelectedItem();
            }


            if(!((String) bedTypeDropdown.getSelectedItem()).equals(defaultBedType)){
                bedType = ((String) bedTypeDropdown.getSelectedItem()).toLowerCase();
            }


            if (startDateChooser.getCalendar() == null || endDateChooser.getCalendar() == null) {
                    JOptionPane.showMessageDialog(this, "Please Enter a Start and End Date before applying search parameters", "Make Reservation Error", JOptionPane.ERROR_MESSAGE);
                }else{

                    startDate = startDateChooser.getCalendar();
                    endDate = endDateChooser.getCalendar();

                    System.out.println("Number of Beds: " + numOfBeds + "\nBed Type: " + bedType + "\nDefault Bed Type: " + defaultBedType.toLowerCase());

                    ArrayList<Room> newListOfRooms = ReservationManager.filterRooms(HotelManager.getAllHotelRooms(), numOfBeds, bedType, startDate, endDate);
                    
                    int thisRoomID = RoomManager.getRoomID(room);
                    boolean reservationMade = false;
                    for (Room thisRoom : newListOfRooms) {
                        if (thisRoomID == RoomManager.getRoomID(thisRoom)) {
                            turquoiseSidePanel.removeAll();
                            turquoiseSidePanel.revalidate();
                            turquoiseSidePanel.repaint();

                            ReservationManager.createReservation(AccountManager.getUserID(), thisRoomID, ReservationManager.convertCalendarToLocalDate(startDate), ReservationManager.convertCalendarToLocalDate(endDate));
                            reservationMade = true;
                        }
                    }

                    if (reservationMade) {
                        returnToScreen();
                    }else{
                        JOptionPane.showMessageDialog(this, "Please make sure to Apply your search parameters before selecting a room", "Make Reservation Error", JOptionPane.ERROR_MESSAGE);
                        turquoiseSidePanel.removeAll();
                        turquoiseSidePanel.revalidate();
                        turquoiseSidePanel.repaint();

                        makeReservationScreen(newListOfRooms, numOfBeds, bedType, startDate, endDate);
                    }

                }
        });


            tileConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            roomTile.add(selectButton, tileConstraints);

            roomTilesPanel.add(roomTile, gridBagConstraints);
            gridBagConstraints.gridy++;

            Dimension setWidth = roomTile.getPreferredSize();
            setWidth.width = X_TILE_DIMENSION_ONLY;     
            roomTile.setPreferredSize(setWidth);
        }

        for (Room room : HotelManager.getAllHotelRooms()) {
        // Fill List with number of bed options 
            Integer numberOfBeds = (Integer) RoomManager.getNumberOfBeds(room);
            if (!numOfBedsList.contains(numberOfBeds)) {
                numOfBedsList.add(numberOfBeds);
            }
            // Fill List with bed type options 
            String bedType = RoomManager.getBedType(room);
            String capitalizedBedType = bedType.substring(0, 1).toUpperCase() + bedType.substring(1);
            if (!bedTypesList.contains(capitalizedBedType)) {
                bedTypesList.add(capitalizedBedType);
            }
        }
        
        // Sorting numOfBedsList in ascending numerical order using Integer.compareTo
        numOfBedsList.sort(Integer::compareTo);
        // Sorting bedTypesList in lexicographical (alphabetical) order using String.compareTo
        bedTypesList.sort(String::compareTo);
        for (Integer beds : numOfBedsList) {
            numberOfBedDropdown.addItem(beds);
        }
        if (previousNumOfBeds != null) {
            numberOfBedDropdown.setSelectedItem(previousNumOfBeds);
        }

        for (String bedTypes : bedTypesList) {
            bedTypeDropdown.addItem(bedTypes);
        }
        if (previousBedType != null) {
            String capitalizedBedType = previousBedType.substring(0, 1).toUpperCase() + previousBedType.substring(1);
            bedTypeDropdown.setSelectedItem(capitalizedBedType);
        }


        // -- Turqoise Panel setup
        turquoiseSidePanel.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints sidePanelConstraints = new java.awt.GridBagConstraints();
        sidePanelConstraints.insets = STANDARD_INSETS;
        sidePanelConstraints.gridx = 0;
        sidePanelConstraints.gridy = 0;
        sidePanelConstraints.anchor = java.awt.GridBagConstraints.WEST;

        turquoiseSidePanel.add(startDateLabel, sidePanelConstraints);
        sidePanelConstraints.gridy++;
       
        turquoiseSidePanel.add(startDateChooser, sidePanelConstraints);
        sidePanelConstraints.gridy++;

        turquoiseSidePanel.add(endDateLabel, sidePanelConstraints);
        sidePanelConstraints.gridy++;
       
        turquoiseSidePanel.add(endDateChooser, sidePanelConstraints);
        sidePanelConstraints.gridy++;

        turquoiseSidePanel.add(numBedsLabel, sidePanelConstraints);
        sidePanelConstraints.gridy++;

        turquoiseSidePanel.add(numberOfBedDropdown, sidePanelConstraints);
        sidePanelConstraints.gridy++;
        
        turquoiseSidePanel.add(bedTypeLabel, sidePanelConstraints);
        sidePanelConstraints.gridy++;

        turquoiseSidePanel.add(bedTypeDropdown, sidePanelConstraints);
        sidePanelConstraints.gridy++;

        sidePanelConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        turquoiseSidePanel.add(resetButton, sidePanelConstraints);
        sidePanelConstraints.gridy++;

        turquoiseSidePanel.add(applyButton, sidePanelConstraints);
        sidePanelConstraints.gridy++;

        turquoiseSidePanel.revalidate();
        turquoiseSidePanel.repaint();

        // --- Scroll Pane ---
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(roomTilesPanel);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // --- Buttons ---
        javax.swing.JButton actionButton = new javax.swing.JButton("Back");
        actionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        actionButton.setForeground(BUTTON_TEXT_COLOR);
        actionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        javax.swing.JButton quitActionButton = new javax.swing.JButton("Quit");
        quitActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        quitActionButton.setForeground(BUTTON_TEXT_COLOR);
        quitActionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        // --- Listener for Interaction ---
        quitActionButton.addActionListener(event -> GUIManager.closeApplication());

        actionButton.addActionListener(event -> {
            turquoiseSidePanel.removeAll();
            turquoiseSidePanel.revalidate();
            turquoiseSidePanel.repaint();
            returnToScreen();
        });

        resetButton.addActionListener(event -> {
            turquoiseSidePanel.removeAll();
            turquoiseSidePanel.revalidate();
            turquoiseSidePanel.repaint();
            makeReservationScreen(HotelManager.getAllHotelRooms(), null , null, null, null);
        });

        applyButton.addActionListener(event -> {
            Integer numOfBeds = null;
            String bedType = null;
            String defaultBedType = bedTypeLabel.getText();
            Calendar startDate = null;
            Calendar endDate = null;

            if((Integer) numberOfBedDropdown.getSelectedItem() != null){
                numOfBeds = (Integer) numberOfBedDropdown.getSelectedItem();
            }


            if(!((String) bedTypeDropdown.getSelectedItem()).equals(defaultBedType)){
                bedType = ((String) bedTypeDropdown.getSelectedItem()).toLowerCase();
            }



            if (startDateChooser.getCalendar() == null || endDateChooser.getCalendar() == null){
                JOptionPane.showMessageDialog(this, "Please Enter a Start and End Date before applying search parameters.", "Filter Rooms Error", JOptionPane.ERROR_MESSAGE);
            }else if (startDateChooser.getCalendar().after(endDateChooser.getCalendar())) {
                JOptionPane.showMessageDialog(this, "Please double check your dates. ", "Filter Rooms Error", JOptionPane.ERROR_MESSAGE);
            }else {

                    startDate = startDateChooser.getCalendar();

                    endDate = endDateChooser.getCalendar();

                    turquoiseSidePanel.removeAll();
                    turquoiseSidePanel.revalidate();
                    turquoiseSidePanel.repaint();

                    //System.out.println("Number of Beds: " + numOfBeds + "\nBed Type: " + bedType + "\nDefault Bed Type: " + defaultBedType.toLowerCase());


                    ArrayList<Room> newListOfRooms = ReservationManager.filterRooms(HotelManager.getAllHotelRooms(), numOfBeds, bedType, startDate, endDate);
                    makeReservationScreen(newListOfRooms, numOfBeds, bedType, startDate, endDate);
                }
                
        });

        // --- Wrapper panel for quit button ---
        javax.swing.JPanel quitButtonPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        quitButtonPanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        quitButtonPanel.add(actionButton);
        quitButtonPanel.add(quitActionButton);

        // --- Add Components to White Background Panel ---
        whiteBackgroundPanel.add(headerTitleLabel, java.awt.BorderLayout.NORTH);
        whiteBackgroundPanel.add(scrollPane, java.awt.BorderLayout.CENTER);
        whiteBackgroundPanel.add(quitButtonPanel, java.awt.BorderLayout.SOUTH);

        // --- Add White Background Panel to Content Main Panel ---
        contentMainPanel.setLayout(new java.awt.BorderLayout());
        contentMainPanel.add(whiteBackgroundPanel, java.awt.BorderLayout.CENTER);

        contentMainPanel.revalidate();
        contentMainPanel.repaint();
    }


    public void editReservationScreen(Reservation currentReservation, ArrayList<Room> listOfRooms, Integer previousNumOfBeds, String  previousBedType, Calendar  previousStartDate, Calendar  previousEndDate) {
        // All Hotel Rooms
        ArrayList<Room> rooms = listOfRooms;
        
        

        //Number of beds in Rooms
        ArrayList<Integer> numOfBedsList = new ArrayList<>();
        ArrayList<String> bedTypesList = new ArrayList<>();

        // Clear contentMainPanel
        contentMainPanel.removeAll();

        // --- Main Wrapper Panel for White Background ---
        javax.swing.JPanel whiteBackgroundPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        whiteBackgroundPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        // --- Header Label ---
        javax.swing.JLabel headerTitleLabel = new javax.swing.JLabel("Edit Reservation");
        headerTitleLabel.setFont(HEADER_FONT);
        headerTitleLabel.setForeground(PRIMARY_BACKGROUND_COLOR);
        headerTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // --- Hotel Tiles Panel ---
        javax.swing.JPanel roomTilesPanel = new javax.swing.JPanel(new java.awt.GridBagLayout());
        roomTilesPanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        roomTilesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));


        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = STANDARD_INSETS;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;


        // --- Dropdown Boxes ---
        javax.swing.JLabel numBedsLabel = new javax.swing.JLabel("Number of Beds:");
        numBedsLabel.setFont(BODY_FONT);
        numBedsLabel.setForeground(SECONDARY_BACKGROUND_COLOR);

        JComboBox<Integer> numberOfBedDropdown = new JComboBox<>();
        numberOfBedDropdown.addItem(null);
        
        numberOfBedDropdown.setFont(TILE_BODY_FONT);
        numberOfBedDropdown.setPreferredSize(SIDE_PANEL_FIELD_DIMENSIONS);

        javax.swing.JLabel bedTypeLabel = new javax.swing.JLabel("Bed Type:");
        bedTypeLabel.setFont(BODY_FONT);
        bedTypeLabel.setForeground(SECONDARY_BACKGROUND_COLOR);

        JComboBox<String> bedTypeDropdown = new JComboBox<>();
        bedTypeDropdown.addItem(bedTypeLabel.getText());
        
        bedTypeDropdown.setFont(TILE_BODY_FONT);
        bedTypeDropdown.setPreferredSize(SIDE_PANEL_FIELD_DIMENSIONS);

        // --- Dates ---
        javax.swing.JLabel startDateLabel = new javax.swing.JLabel("Start Date");
        startDateLabel.setFont(BODY_FONT);
        startDateLabel.setForeground(SECONDARY_BACKGROUND_COLOR);

        com.toedter.calendar.JDateChooser startDateChooser = new com.toedter.calendar.JDateChooser();
        startDateChooser.setFont(BODY_FONT);
        startDateChooser.setPreferredSize(SIDE_PANEL_FIELD_DIMENSIONS);

        if (previousStartDate != null) {
            Calendar previousStartCalendar = previousStartDate;
            startDateChooser.setCalendar(previousStartCalendar);
        }

        javax.swing.JLabel endDateLabel = new javax.swing.JLabel("End Date");
        endDateLabel.setFont(BODY_FONT);
        endDateLabel.setForeground(SECONDARY_BACKGROUND_COLOR);

        com.toedter.calendar.JDateChooser endDateChooser = new com.toedter.calendar.JDateChooser();
        endDateChooser.setFont(BODY_FONT);
        endDateChooser.setPreferredSize(SIDE_PANEL_FIELD_DIMENSIONS);

        if (previousEndDate != null) {
            Calendar previousEndCalendar = previousEndDate;
            endDateChooser.setCalendar(previousEndCalendar);
        }

        
        javax.swing.JButton applyButton = new javax.swing.JButton("Apply");
        applyButton.setBackground(BUTTON_BACKGROUND_COLOR);
        applyButton.setForeground(SECONDARY_BACKGROUND_COLOR);
        applyButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);
        applyButton.setBorder(javax.swing.BorderFactory.createLineBorder(SECONDARY_BACKGROUND_COLOR));

        javax.swing.JButton resetButton = new javax.swing.JButton("Reset");
        resetButton.setBackground(BUTTON_BACKGROUND_COLOR);
        resetButton.setForeground(SECONDARY_BACKGROUND_COLOR);
        resetButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);
        resetButton.setBorder(javax.swing.BorderFactory.createLineBorder(SECONDARY_BACKGROUND_COLOR));


        for (Room room : rooms) {
            javax.swing.JPanel roomTile = new javax.swing.JPanel(new java.awt.GridBagLayout());
            
            roomTile.setBackground(TILE_BACKGROUND_COLOR);
            roomTile.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));



            java.awt.GridBagConstraints tileConstraints = new java.awt.GridBagConstraints();
            tileConstraints.insets = STANDARD_INSETS;
            tileConstraints.gridx = 0;
            tileConstraints.gridy = 0;
            tileConstraints.anchor = java.awt.GridBagConstraints.CENTER;
            tileConstraints.fill = TILE_FILL_VERTICAL;


            // Spacer
            javax.swing.JPanel gapSpacer = new javax.swing.JPanel(new java.awt.GridBagLayout());
            gapSpacer.setBackground(TILE_BACKGROUND_COLOR);
            roomTile.add(gapSpacer, tileConstraints);
            tileConstraints.gridy++;


            // Room Number
            javax.swing.JLabel roomNumberLabel = new javax.swing.JLabel("Room Number: " + String.valueOf(RoomManager.getRoomNumber(room)));
            roomNumberLabel.setFont(TILE_HEADER_FONT);
            tileConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
            roomTile.add(roomNumberLabel, tileConstraints);
            tileConstraints.gridy++;

            
            // Number of Beds and Bed Type
            javax.swing.JLabel roomBedsLabel;
            if (RoomManager.getNumberOfBeds(room) > 1) {
                roomBedsLabel = new javax.swing.JLabel(String.valueOf(RoomManager.getNumberOfBeds(room) + " " + RoomManager.getBedType(room) + " sized beds"));
                
            }else{
                roomBedsLabel = new javax.swing.JLabel(String.valueOf(RoomManager.getNumberOfBeds(room) + " " + RoomManager.getBedType(room) + " sized bed"));
            }
            roomBedsLabel.setFont(TILE_BODY_FONT);
            tileConstraints.anchor = java.awt.GridBagConstraints.CENTER;
            roomTile.add(roomBedsLabel, tileConstraints);
            tileConstraints.gridy++;

            
            // Description
            String[] descriptionLines = RoomManager.getRoomDescription(room).split("\\n");
            for (String line : descriptionLines) {
                javax.swing.JLabel roomDescriptionLabel = new javax.swing.JLabel(line);
                roomDescriptionLabel.setFont(TILE_BODY_FONT);
                tileConstraints.anchor = java.awt.GridBagConstraints.CENTER;
                roomTile.add(roomDescriptionLabel, tileConstraints);
                tileConstraints.gridy++;
            }
            
            // Select Button
            javax.swing.JButton selectButton = new javax.swing.JButton("Select");
            selectButton.setBackground(BUTTON_BACKGROUND_COLOR);
            selectButton.setForeground(BUTTON_TEXT_COLOR);
            selectButton.setFont(BODY_FONT_BOLD);

            selectButton.addActionListener(event -> {
            Integer numOfBeds = null;
            String bedType = null;
            String defaultBedType = bedTypeLabel.getText();
            Calendar startDate = null;
            Calendar endDate = null;

            if((Integer) numberOfBedDropdown.getSelectedItem() != null){
                numOfBeds = (Integer) numberOfBedDropdown.getSelectedItem();
            }


            if(!((String) bedTypeDropdown.getSelectedItem()).equals(defaultBedType)){
                bedType = ((String) bedTypeDropdown.getSelectedItem()).toLowerCase();
            }


            if (startDateChooser.getCalendar() == null || endDateChooser.getCalendar() == null) {
                    JOptionPane.showMessageDialog(this, "Please Enter a Start and End Date before applying search parameters", "Make Reservation Error", JOptionPane.ERROR_MESSAGE);
                }else{

                    startDate = startDateChooser.getCalendar();
                    endDate = endDateChooser.getCalendar();

                    System.out.println("Number of Beds: " + numOfBeds + "\nBed Type: " + bedType + "\nDefault Bed Type: " + defaultBedType.toLowerCase());

                    ArrayList<Room> newListOfRooms = ReservationManager.filterRooms(HotelManager.getAllHotelRooms(), numOfBeds, bedType, startDate, endDate);
                    
                    int thisRoomID = RoomManager.getRoomID(room);
                    boolean reservationMade = false;
                    for (Room thisRoom : newListOfRooms) {
                        if (thisRoomID == RoomManager.getRoomID(thisRoom)) {

                            turquoiseSidePanel.removeAll();
                            turquoiseSidePanel.revalidate();
                            turquoiseSidePanel.repaint();

                            int currentReservationID = ReservationManager.getReservationID(currentReservation);
                            System.out.println(currentReservationID);
                            Reservation updatedReservation = new Reservation(currentReservationID, AccountManager.getUserID(), thisRoomID, ReservationManager.convertCalendarToLocalDate(startDate), ReservationManager.convertCalendarToLocalDate(endDate));
                            DatabaseConnector.updateReservationInDatabase(updatedReservation);
                            reservationMade = true;
                        }
                    }

                    if (reservationMade) {
                        returnToScreen();
                    }else{
                        JOptionPane.showMessageDialog(this, "Please make sure to Apply your search parameters before selecting a room", "Make Reservation Error", JOptionPane.ERROR_MESSAGE);
                        turquoiseSidePanel.removeAll();
                        turquoiseSidePanel.revalidate();
                        turquoiseSidePanel.repaint();

                        makeReservationScreen(newListOfRooms, numOfBeds, bedType, startDate, endDate);
                    }

                }
        });


            tileConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            roomTile.add(selectButton, tileConstraints);

            roomTilesPanel.add(roomTile, gridBagConstraints);
            gridBagConstraints.gridy++;

            Dimension setWidth = roomTile.getPreferredSize();
            setWidth.width = X_TILE_DIMENSION_ONLY;     
            roomTile.setPreferredSize(setWidth);
        }

        for (Room room : HotelManager.getAllHotelRooms()) {
        // Fill List with number of bed options 
            Integer numberOfBeds = (Integer) RoomManager.getNumberOfBeds(room);
            if (!numOfBedsList.contains(numberOfBeds)) {
                numOfBedsList.add(numberOfBeds);
            }
            // Fill List with bed type options 
            String bedType = RoomManager.getBedType(room);
            String capitalizedBedType = bedType.substring(0, 1).toUpperCase() + bedType.substring(1);
            if (!bedTypesList.contains(capitalizedBedType)) {
                bedTypesList.add(capitalizedBedType);
            }
        }
        
        // Sorting numOfBedsList in ascending numerical order using Integer.compareTo
        numOfBedsList.sort(Integer::compareTo);
        // Sorting bedTypesList in lexicographical (alphabetical) order using String.compareTo
        bedTypesList.sort(String::compareTo);
        for (Integer beds : numOfBedsList) {
            numberOfBedDropdown.addItem(beds);
        }
        if (previousNumOfBeds != null) {
            numberOfBedDropdown.setSelectedItem(previousNumOfBeds);
        }

        for (String bedTypes : bedTypesList) {
            bedTypeDropdown.addItem(bedTypes);
        }
        if (previousBedType != null) {
            String capitalizedBedType = previousBedType.substring(0, 1).toUpperCase() + previousBedType.substring(1);
            bedTypeDropdown.setSelectedItem(capitalizedBedType);
        }


        // -- Turqoise Panel setup
        turquoiseSidePanel.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints sidePanelConstraints = new java.awt.GridBagConstraints();
        sidePanelConstraints.insets = STANDARD_INSETS;
        sidePanelConstraints.gridx = 0;
        sidePanelConstraints.gridy = 0;
        sidePanelConstraints.anchor = java.awt.GridBagConstraints.WEST;

        turquoiseSidePanel.add(startDateLabel, sidePanelConstraints);
        sidePanelConstraints.gridy++;
       
        turquoiseSidePanel.add(startDateChooser, sidePanelConstraints);
        sidePanelConstraints.gridy++;

        turquoiseSidePanel.add(endDateLabel, sidePanelConstraints);
        sidePanelConstraints.gridy++;
       
        turquoiseSidePanel.add(endDateChooser, sidePanelConstraints);
        sidePanelConstraints.gridy++;

        turquoiseSidePanel.add(numBedsLabel, sidePanelConstraints);
        sidePanelConstraints.gridy++;

        turquoiseSidePanel.add(numberOfBedDropdown, sidePanelConstraints);
        sidePanelConstraints.gridy++;
        
        turquoiseSidePanel.add(bedTypeLabel, sidePanelConstraints);
        sidePanelConstraints.gridy++;

        turquoiseSidePanel.add(bedTypeDropdown, sidePanelConstraints);
        sidePanelConstraints.gridy++;

        sidePanelConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        turquoiseSidePanel.add(resetButton, sidePanelConstraints);
        sidePanelConstraints.gridy++;

        turquoiseSidePanel.add(applyButton, sidePanelConstraints);
        sidePanelConstraints.gridy++;

        turquoiseSidePanel.revalidate();
        turquoiseSidePanel.repaint();

        // --- Scroll Pane ---
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(roomTilesPanel);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // --- Buttons ---
        javax.swing.JButton actionButton = new javax.swing.JButton("Back");
        actionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        actionButton.setForeground(BUTTON_TEXT_COLOR);
        actionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        javax.swing.JButton quitActionButton = new javax.swing.JButton("Quit");
        quitActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        quitActionButton.setForeground(BUTTON_TEXT_COLOR);
        quitActionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        // --- Listener for Interaction ---
        quitActionButton.addActionListener(event -> GUIManager.closeApplication());

        actionButton.addActionListener(event -> {
            turquoiseSidePanel.removeAll();
            turquoiseSidePanel.revalidate();
            turquoiseSidePanel.repaint();
            returnToScreen();
        });

        resetButton.addActionListener(event -> {
            turquoiseSidePanel.removeAll();
            turquoiseSidePanel.revalidate();
            turquoiseSidePanel.repaint();
            editReservationScreen(currentReservation, HotelManager.getAllHotelRooms(), null , null, null, null);
        });

        applyButton.addActionListener(event -> {
            Integer numOfBeds = null;
            String bedType = null;
            String defaultBedType = bedTypeLabel.getText();
            Calendar startDate = null;
            Calendar endDate = null;

            if((Integer) numberOfBedDropdown.getSelectedItem() != null){
                numOfBeds = (Integer) numberOfBedDropdown.getSelectedItem();
            }


            if(!((String) bedTypeDropdown.getSelectedItem()).equals(defaultBedType)){
                bedType = ((String) bedTypeDropdown.getSelectedItem()).toLowerCase();
            }



            if (startDateChooser.getCalendar() == null || endDateChooser.getCalendar() == null){
                JOptionPane.showMessageDialog(this, "Please Enter a Start and End Date before applying search parameters.", "Filter Rooms Error", JOptionPane.ERROR_MESSAGE);
            }else if (startDateChooser.getCalendar().after(endDateChooser.getCalendar())) {
                JOptionPane.showMessageDialog(this, "Please double check your dates. ", "Filter Rooms Error", JOptionPane.ERROR_MESSAGE);
            }else {

                    startDate = startDateChooser.getCalendar();

                    endDate = endDateChooser.getCalendar();

                    turquoiseSidePanel.removeAll();
                    turquoiseSidePanel.revalidate();
                    turquoiseSidePanel.repaint();

                    //System.out.println("Number of Beds: " + numOfBeds + "\nBed Type: " + bedType + "\nDefault Bed Type: " + defaultBedType.toLowerCase());


                    ArrayList<Room> newListOfRooms = ReservationManager.filterRooms(HotelManager.getAllHotelRooms(), numOfBeds, bedType, startDate, endDate);
                    editReservationScreen(currentReservation, newListOfRooms, numOfBeds, bedType, startDate, endDate);
                }
                
        });

        // --- Wrapper panel for quit button ---
        javax.swing.JPanel quitButtonPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        quitButtonPanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        quitButtonPanel.add(actionButton);
        quitButtonPanel.add(quitActionButton);

        // --- Add Components to White Background Panel ---
        whiteBackgroundPanel.add(headerTitleLabel, java.awt.BorderLayout.NORTH);
        whiteBackgroundPanel.add(scrollPane, java.awt.BorderLayout.CENTER);
        whiteBackgroundPanel.add(quitButtonPanel, java.awt.BorderLayout.SOUTH);

        // --- Add White Background Panel to Content Main Panel ---
        contentMainPanel.setLayout(new java.awt.BorderLayout());
        contentMainPanel.add(whiteBackgroundPanel, java.awt.BorderLayout.CENTER);

        contentMainPanel.revalidate();
        contentMainPanel.repaint();
    }


    public void managerWelcomeScreen() {
        //Clear contentMainPanel
        contentMainPanel.removeAll();

        // --- Main Wrapper Panel for White Background ---
        javax.swing.JPanel whiteBackgroundPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        whiteBackgroundPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        // --- Header Label ---
        javax.swing.JLabel headerTitleLabel = new javax.swing.JLabel("Welcome "+ AccountManager.getFirstName());
        headerTitleLabel.setFont(HEADER_FONT);
        headerTitleLabel.setForeground(PRIMARY_BACKGROUND_COLOR);
        headerTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // --- Buttons ---
        javax.swing.JButton selectHotelActionButton = new javax.swing.JButton("Select Hotel");
        selectHotelActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        selectHotelActionButton.setForeground(BUTTON_TEXT_COLOR);
        selectHotelActionButton.setPreferredSize(STANDARD_BUTTON_DIMENSIONS);
        
        javax.swing.JButton createHotelActionButton = new javax.swing.JButton("Create Hotel");
        createHotelActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        createHotelActionButton.setForeground(BUTTON_TEXT_COLOR);
        createHotelActionButton.setPreferredSize(STANDARD_BUTTON_DIMENSIONS);

        javax.swing.JButton editAccountActionButton = new javax.swing.JButton("Edit My Account");
        editAccountActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        editAccountActionButton.setForeground(BUTTON_TEXT_COLOR);
        editAccountActionButton.setPreferredSize(STANDARD_BUTTON_DIMENSIONS);

        javax.swing.JButton viewReservationsActionButton = new javax.swing.JButton("View Reservations");
        viewReservationsActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        viewReservationsActionButton.setForeground(BUTTON_TEXT_COLOR);
        viewReservationsActionButton.setPreferredSize(STANDARD_BUTTON_DIMENSIONS);

        javax.swing.JButton quitActionButton = new javax.swing.JButton("Quit");
        quitActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        quitActionButton.setForeground(BUTTON_TEXT_COLOR);
        quitActionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);


        // --- Listener for Interaction ---
        selectHotelActionButton.addActionListener(event -> selectHotelScreenManager());
        editAccountActionButton.addActionListener(event -> editAccountScreen());
        quitActionButton.addActionListener(event -> GUIManager.closeApplication());
        viewReservationsActionButton.addActionListener(event -> viewReservationsScreen(DatabaseManager.getAllReservations()));

        // --- Buttons Panel with GridBagLayout ---
        javax.swing.JPanel buttonsPanel = new javax.swing.JPanel(new java.awt.GridBagLayout());
        buttonsPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = STANDARD_INSETS;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        buttonsPanel.add(selectHotelActionButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        buttonsPanel.add(editAccountActionButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        buttonsPanel.add(createHotelActionButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        buttonsPanel.add(viewReservationsActionButton, gridBagConstraints);


        // --- Wrapper panel for quit button ---
        javax.swing.JPanel quitButtonPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        quitButtonPanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        quitButtonPanel.add(quitActionButton);

        // --- Add Components to White Background Panel ---
        whiteBackgroundPanel.add(headerTitleLabel, java.awt.BorderLayout.NORTH);
        whiteBackgroundPanel.add(buttonsPanel, java.awt.BorderLayout.CENTER);
        whiteBackgroundPanel.add(quitButtonPanel, java.awt.BorderLayout.SOUTH);

        // --- Add White Background Panel to Content Main Panel ---
        contentMainPanel.setLayout(new java.awt.BorderLayout());
        contentMainPanel.add(whiteBackgroundPanel, java.awt.BorderLayout.CENTER);

        contentMainPanel.revalidate();
        contentMainPanel.repaint();
    }


    public void selectHotelScreenManager() {
        // Get List of all Hotels
        ArrayList<Hotel> hotels = DatabaseManager.getAllHotels();

        // Clear contentMainPanel
        contentMainPanel.removeAll();

        // --- Main Wrapper Panel for White Background ---
        javax.swing.JPanel whiteBackgroundPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        whiteBackgroundPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        // --- Header Label ---
        javax.swing.JLabel headerTitleLabel = new javax.swing.JLabel("Select Hotel");
        headerTitleLabel.setFont(HEADER_FONT);
        headerTitleLabel.setForeground(PRIMARY_BACKGROUND_COLOR);
        headerTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // --- Hotel Tiles Panel ---
        javax.swing.JPanel hotelTilesPanel = new javax.swing.JPanel(new java.awt.GridBagLayout());
        hotelTilesPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = STANDARD_INSETS;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

        for (Hotel hotel : hotels) {

            javax.swing.JPanel hotelTile = new javax.swing.JPanel(new java.awt.GridBagLayout());
            hotelTile.setPreferredSize(TILE_DIMENSIONS);
            hotelTile.setBackground(TILE_BACKGROUND_COLOR);
            hotelTile.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));


            java.awt.GridBagConstraints tileConstraints = new java.awt.GridBagConstraints();
            tileConstraints.insets = STANDARD_INSETS;
            tileConstraints.gridx = 0;
            tileConstraints.gridy = 0;


            // Hotel Name
            javax.swing.JLabel hotelNameLabel = new javax.swing.JLabel(HotelManager.getHotelName(hotel));
            hotelNameLabel.setFont(TILE_HEADER_FONT);
            hotelTile.add(hotelNameLabel, tileConstraints);
            tileConstraints.gridy++;

            // Hotel Address
            javax.swing.JLabel hotelAddressLabel = new javax.swing.JLabel(HotelManager.getHotelAddress(hotel));
            hotelAddressLabel.setFont(TILE_BODY_FONT);
            hotelTile.add(hotelAddressLabel, tileConstraints);
            tileConstraints.gridy++;


            // Number of Rooms
            javax.swing.JLabel hotelRoomsLabel = new javax.swing.JLabel("Number of Rooms: " + HotelManager.getTotalNumberOfRooms(hotel));
            hotelRoomsLabel.setFont(TILE_BODY_FONT);
            hotelTile.add(hotelRoomsLabel, tileConstraints);
            tileConstraints.gridy++;

            // Button Panel
            // Adding horizontal gap of 15px between buttons using FlowLayout
            javax.swing.JPanel buttonPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 0));
            buttonPanel.setBackground(TILE_BACKGROUND_COLOR);

            // Select Button
            javax.swing.JButton selectButton = new javax.swing.JButton("Select");
            selectButton.setBackground(BUTTON_BACKGROUND_COLOR);
            selectButton.setForeground(BUTTON_TEXT_COLOR);
            selectButton.setFont(BODY_FONT_BOLD);
            selectButton.addActionListener(event -> {
                DatabaseManager.setCurrentHotel(hotel);
                returnToScreen();
            });
            buttonPanel.add(selectButton);

            // Edit Reservation Button
            javax.swing.JButton editHotelButton = new javax.swing.JButton("Edit");
            editHotelButton.setBackground(BUTTON_BACKGROUND_COLOR);
            editHotelButton.setForeground(BUTTON_TEXT_COLOR);
            editHotelButton.setFont(BODY_FONT_BOLD);
            editHotelButton.addActionListener(event -> {
                DatabaseManager.setCurrentHotel(hotel);
                editHotelScreen();
            });
            buttonPanel.add(editHotelButton);

            tileConstraints.gridx = 0;
            hotelTile.add(buttonPanel, tileConstraints);

            hotelTilesPanel.add(hotelTile, gridBagConstraints);
            gridBagConstraints.gridy++;


        }

        // Add a scroll pane for the hotel tiles panel
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(hotelTilesPanel);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);        

        // --- Buttons ---
        javax.swing.JButton actionButton = new javax.swing.JButton("Back");
        actionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        actionButton.setForeground(BUTTON_TEXT_COLOR);
        actionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        javax.swing.JButton quitActionButton = new javax.swing.JButton("Quit");
        quitActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        quitActionButton.setForeground(BUTTON_TEXT_COLOR);
        quitActionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        // --- Listener for Interaction ---
        quitActionButton.addActionListener(event -> GUIManager.closeApplication());
        actionButton.addActionListener(event -> returnToScreen());

        // --- Wrapper panel for quit button ---
        javax.swing.JPanel quitButtonPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        quitButtonPanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        quitButtonPanel.add(actionButton);
        quitButtonPanel.add(quitActionButton);

        // --- Add Components to White Background Panel ---
        whiteBackgroundPanel.add(headerTitleLabel, java.awt.BorderLayout.NORTH);
        whiteBackgroundPanel.add(scrollPane, java.awt.BorderLayout.CENTER);
        whiteBackgroundPanel.add(quitButtonPanel, java.awt.BorderLayout.SOUTH);

        // --- Add White Background Panel to Content Main Panel ---
        contentMainPanel.setLayout(new java.awt.BorderLayout());
        contentMainPanel.add(whiteBackgroundPanel, java.awt.BorderLayout.CENTER);

        contentMainPanel.revalidate();
        contentMainPanel.repaint();
    }


    public void editHotelScreen(){
        String hotelName = HotelManager.getHotelName();
        String hotelAddress = HotelManager.getHotelAddress();
        Integer numOfHotelRooms = HotelManager.getTotalNumberOfRooms();

        // Clear contentMainPanel
        contentMainPanel.removeAll();

        // --- Main Wrapper Panel for White Background ---
        javax.swing.JPanel whiteBackgroundPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        whiteBackgroundPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        // --- Header Label ---
        javax.swing.JLabel headerTitleLabel = new javax.swing.JLabel("Edit Hotel");
        headerTitleLabel.setFont(HEADER_FONT);
        headerTitleLabel.setForeground(PRIMARY_BACKGROUND_COLOR);
        headerTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // --- Buttons ---
        javax.swing.JButton actionButton = new javax.swing.JButton("Back");
        actionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        actionButton.setForeground(BUTTON_TEXT_COLOR);
        actionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        javax.swing.JButton quitActionButton = new javax.swing.JButton("Quit");
        quitActionButton.setBackground(BUTTON_BACKGROUND_COLOR);
        quitActionButton.setForeground(BUTTON_TEXT_COLOR);
        quitActionButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        // --- Hotel Name Section ---
        javax.swing.JLabel hotelNameLabel = new javax.swing.JLabel("Hotel Name");
        hotelNameLabel.setFont(BODY_FONT);
        hotelNameLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JTextField hotelNameField = new javax.swing.JTextField(20);
        hotelNameField.setFont(BODY_FONT);
        hotelNameField.setText(hotelName);
        hotelNameField.setPreferredSize(STANDARD_FIELD_DIMENSIONS);

        // --- Hotel Address Section ---
        javax.swing.JLabel hotelAddressLabel = new javax.swing.JLabel("Hotel Address");
        hotelAddressLabel.setFont(BODY_FONT);
        hotelAddressLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JTextField hotelAddressField = new javax.swing.JTextField(20);
        hotelAddressField.setFont(BODY_FONT);
        hotelAddressField.setText(hotelAddress);
        hotelAddressField.setPreferredSize(STANDARD_FIELD_DIMENSIONS);

        // --- Number of Rooms Section ---
        javax.swing.JLabel numOfHotelRoomsLabel = new javax.swing.JLabel("Number of Rooms:");
        numOfHotelRoomsLabel.setFont(BODY_FONT);
        numOfHotelRoomsLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

         javax.swing.JLabel numOfRoomsLabel = new javax.swing.JLabel(numOfHotelRooms.toString());
        numOfRoomsLabel.setFont(BODY_FONT);
        numOfRoomsLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        // --- Expected and Actual Earnings Section ---
        javax.swing.JLabel hotelEaringsLabel = new javax.swing.JLabel("$");
        hotelEaringsLabel.setFont(BODY_FONT);
        hotelEaringsLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        javax.swing.JLabel earingsLabel = new javax.swing.JLabel("" + HotelManager.calcHotelEarnings());
        earingsLabel.setFont(BODY_FONT);
        earingsLabel.setForeground(PRIMARY_BACKGROUND_COLOR);

        // --- View Hotel Rooms Buttons ---
        javax.swing.JButton viewRoomsButton = new javax.swing.JButton("View Rooms");
        viewRoomsButton.setBackground(BUTTON_BACKGROUND_COLOR);
        viewRoomsButton.setForeground(BUTTON_TEXT_COLOR);
        viewRoomsButton.setPreferredSize(SIGN_OUT_BUTTON_DIMENSIONS);

        // --- Save and Discard Buttons ---
        javax.swing.JButton saveChangesButton = new javax.swing.JButton("Save");
        saveChangesButton.setBackground(BUTTON_BACKGROUND_COLOR);
        saveChangesButton.setForeground(BUTTON_TEXT_COLOR);
        saveChangesButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        javax.swing.JButton discardChangesButton = new javax.swing.JButton("Discrad");
        discardChangesButton.setBackground(BUTTON_BACKGROUND_COLOR);
        discardChangesButton.setForeground(BUTTON_TEXT_COLOR);
        discardChangesButton.setPreferredSize(QUIT_BUTTON_DIMENSIONS);

        // --- Listener for Interaction ---
        quitActionButton.addActionListener(event -> GUIManager.closeApplication());
        actionButton.addActionListener(event -> returnToScreen());
        discardChangesButton.addActionListener(event-> {
            returnToScreen();
        });
        saveChangesButton.addActionListener(event-> {
            //Check if any values have changed
            String enteredHotelName = hotelNameField.getText().trim();
            String enteredHoteAddress = hotelAddressField.getText().trim();

            if (!enteredHotelName.equals(hotelName) && enteredHotelName != null) {
                HotelManager.setHotelName(enteredHotelName);
            }

            System.out.println(hotelAddress);
            System.out.println(enteredHoteAddress);
            if (!enteredHoteAddress.equals(hotelAddress) && enteredHoteAddress != null) {
                HotelManager.setHotelAddress(enteredHoteAddress);
            }

            returnToScreen();
        });
        
        // --- Layout ---
        javax.swing.JPanel buttonsPanel = new javax.swing.JPanel(new java.awt.GridBagLayout());
        buttonsPanel.setBackground(SECONDARY_BACKGROUND_COLOR);

        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = STANDARD_INSETS;
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        buttonsPanel.add(hotelNameLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        buttonsPanel.add(hotelNameField, gridBagConstraints);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        buttonsPanel.add(hotelAddressLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        buttonsPanel.add(hotelAddressField, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        buttonsPanel.add(numOfHotelRoomsLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        buttonsPanel.add(numOfRoomsLabel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        buttonsPanel.add(hotelEaringsLabel, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        buttonsPanel.add(earingsLabel, gridBagConstraints);
        
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        buttonsPanel.add(viewRoomsButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
        buttonsPanel.add(discardChangesButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        buttonsPanel.add(saveChangesButton, gridBagConstraints);



        // --- Wrapper panel for quit button ---
        javax.swing.JPanel quitButtonPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        quitButtonPanel.setBackground(SECONDARY_BACKGROUND_COLOR);
        quitButtonPanel.add(actionButton);
        quitButtonPanel.add(quitActionButton);
        
        // --- Add Components to White Background Panel ---
        whiteBackgroundPanel.add(headerTitleLabel, java.awt.BorderLayout.NORTH);
        whiteBackgroundPanel.add(buttonsPanel, java.awt.BorderLayout.CENTER);

        // --- Add White Background Panel to Content Main Panel ---
        contentMainPanel.setLayout(new java.awt.BorderLayout());
        contentMainPanel.add(whiteBackgroundPanel, java.awt.BorderLayout.CENTER);
        contentMainPanel.add(quitButtonPanel, java.awt.BorderLayout.SOUTH);


        contentMainPanel.revalidate();
        contentMainPanel.repaint();
    }


    public void returnToScreen(){
    	if(DatabaseManager.getCurrentUser() == null){
			loginScreen();
		}else if (DatabaseManager.getCurrentUser() instanceof Manager){
			managerWelcomeScreen();
		}else{
    		userWelcomeScreen();
		}
    }


}

