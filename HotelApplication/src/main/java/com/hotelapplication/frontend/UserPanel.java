package com.hotelapplication.frontend;
import com.hotelapplication.backend.*;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JFrame {
    public UserPanel() {
        setTitle("User Menu");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(3, 45, 48));

        JPanel panel = new JPanel(new GridLayout(2, 2, 20, 20));
        panel.setBackground(new Color(3, 45, 48));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton viewReservationsButton = createModernButton("View My Reservations");
        JButton makeReservationButton = createModernButton("Make Reservation");
        JButton selectHotelButton = createModernButton("Select Hotel");
        JButton editAccountButton = createModernButton("Edit My Account");
        
        JButton exitButton = createModernButton("Exit");
        JButton signOutButton = createModernButton("Sign Out");

        exitButton.addActionListener(e -> System.exit(0));
        signOutButton.addActionListener(e -> {
            this.dispose();
            SwingUtilities.invokeLater(() -> new login().setVisible(true));  // Go back to login form
        });

        panel.add(viewReservationsButton);
        panel.add(makeReservationButton);
        panel.add(selectHotelButton);
        panel.add(editAccountButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(3, 45, 48));
        buttonPanel.add(signOutButton);
        buttonPanel.add(exitButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserPanel().setVisible(true));
    }
}
