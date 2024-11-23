//ManagerPanel.java

package com.hotelapplication.frontend;
// import com.hotelapplication.backend.*;

import javax.swing.*;
import java.awt.*;

/**
 * The ManagerPanel class represents the GUI for the manager's main dashboard.
 * It allows managers to perform operations such as selecting a hotel,
 * editing account information, viewing reservations, and editing hotel details.
 *
 * @author Minas Papazyan
 */
public class ManagerPanel extends JFrame {

    /**
     * Constructs the ManagerPanel GUI with all its components.
     * The panel includes buttons for managing hotel and account-related operations.
     */
    public ManagerPanel() {
        setTitle("Manager Menu");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(3, 45, 48));

        // Create the main panel with grid layout for buttons
        JPanel panel = new JPanel(new GridLayout(2, 2, 20, 20));
        panel.setBackground(new Color(3, 45, 48));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Create buttons for manager options
        JButton selectHotelButton = createModernButton("Select Hotel");
        JButton editAccountButton = createModernButton("Edit My Account");
        JButton viewReservationsButton = createModernButton("View All Reservations");
        JButton editHotelButton = createModernButton("Edit Hotel");

        // Create buttons for exiting and signing out
        JButton exitButton = createModernButton("Exit");
        JButton signOutButton = createModernButton("Sign Out");

        // Exit Button Logic
        exitButton.addActionListener(e -> GUIManager.closeApplication());

        // Sign-Out Button Logic
        signOutButton.addActionListener(e -> {
            this.dispose();
            SwingUtilities.invokeLater(() -> new Login().setVisible(true)); // Return to login form
        });

        // Edit Account Button Logic
        editAccountButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new EditAccountForm().setVisible(true)); // Open Edit Account form
            this.dispose();
        });

        // Add buttons to the main panel
        panel.add(selectHotelButton);
        panel.add(editAccountButton);
        panel.add(viewReservationsButton);
        panel.add(editHotelButton);

        // Create a bottom panel for Exit and Sign Out buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(3, 45, 48));
        buttonPanel.add(signOutButton);
        buttonPanel.add(exitButton);

        // Add panels to the frame
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
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
        button.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    /**
     * The main method to test the ManagerPanel GUI.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManagerPanel().setVisible(true));
    }
}
