package com.hotelapplication.frontend;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JFrame {

    public UserPanel() {
        setTitle("User Menu");
        setSize(600, 500); // Adjusted to align with Login size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(3, 45, 48));

        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBackground(new Color(3, 45, 48));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton viewReservationsButton = createModernButton("View My Reservations");
        JButton makeReservationButton = createModernButton("Make Reservation");
        JButton selectHotelButton = createModernButton("Select Hotel");
        JButton editAccountButton = createModernButton("Edit My Account");
        
        // Add buttons and actions
        viewReservationsButton.addActionListener(e -> {
            // Add logic for viewing reservations
        });

        makeReservationButton.addActionListener(e -> {
            // Add logic for making a reservation
        });

        selectHotelButton.addActionListener(e -> {
            // Add logic for selecting a hotel
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

        // Exit and Sign-out Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(new Color(3, 45, 48));
        JButton exitButton = createModernButton("Exit");
        JButton signOutButton = createModernButton("Sign Out");

        exitButton.addActionListener(e -> GUIManager.closeApplication());
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

        bottomPanel.add(signOutButton);
        bottomPanel.add(exitButton);

        add(panel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserPanel().setVisible(true));
    }
}
