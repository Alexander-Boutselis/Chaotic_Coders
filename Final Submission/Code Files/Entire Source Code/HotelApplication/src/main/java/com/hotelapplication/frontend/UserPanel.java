//UserPanel.java

package com.hotelapplication.frontend;
import com.hotelapplication.backend.*;

import javax.swing.*;
import java.awt.*;

/**
 * The UserPanel class represents the GUI for the user's main dashboard.
 * It allows users to perform operations such as viewing reservations,
 * making reservations, selecting hotels, and editing account information.
 *
 * @author Minas Papazyan
 */
public class UserPanel extends JFrame {

    /**
     * Constructs the UserPanel GUI with all its components.
     * The panel includes buttons for user-related operations and navigation.
     */
    public UserPanel() {
        setTitle("User Menu");
        setSize(600, 500); // Adjusted to align with Login size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(3, 45, 48));

        // Create the main panel with grid layout for buttons
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBackground(new Color(3, 45, 48));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create buttons for user options
        JButton viewReservationsButton = createModernButton("View My Reservations");
        JButton makeReservationButton = createModernButton("Make Reservation");
        JButton selectHotelButton = createModernButton("Select Hotel");
        JButton editAccountButton = createModernButton("Edit My Account");

        // Add logic to buttons
        viewReservationsButton.addActionListener(e -> {
            // Add logic for viewing reservations (to be implemented)
        });

        makeReservationButton.addActionListener(e -> {
            // Add logic for making a reservation (to be implemented)
        });

        selectHotelButton.addActionListener(e -> {
            // Logic for selecting a hotel
            SwingUtilities.invokeLater(() -> new SelectHotel().setVisible(true));
        });

        editAccountButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new EditAccountForm().setVisible(true));
            this.dispose();
        });

        // Add buttons to the main panel
        panel.add(viewReservationsButton);
        panel.add(makeReservationButton);
        panel.add(selectHotelButton);
        panel.add(editAccountButton);

        // Create a bottom panel for Exit and Sign-Out buttons
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(new Color(3, 45, 48));
        JButton exitButton = createModernButton("Exit");
        JButton signOutButton = createModernButton("Sign Out");

        // Exit Button Logic
        exitButton.addActionListener(e -> GUIManager.closeApplication());

        // Sign-Out Button Logic
        signOutButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to sign out?",
                    "Confirm Sign Out",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                this.dispose();
                SwingUtilities.invokeLater(() -> new Login().setVisible(true));
            }
        });

        // Add Exit and Sign-Out buttons to the bottom panel
        bottomPanel.add(signOutButton);
        bottomPanel.add(exitButton);

        // Add panels to the frame
        add(panel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates a modern-styled button with the given text.
     *
     * @param text The text to display on the button.
     * @return A styled JButton object.
     */
    private JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(26, 104, 110));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.BOLD, 16));
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    /**
     * The main method to test the UserPanel GUI.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserPanel().setVisible(true));
    }
}
