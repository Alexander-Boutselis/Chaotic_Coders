package com.hotelapplication.frontend;
import com.hotelapplication.backend.*;

import javax.swing.*;
import java.awt.*;

public class EditAccountForm extends JFrame {

    public EditAccountForm() {
        setTitle("Edit My Account");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(3, 45, 48));

        JPanel panel = new JPanel(new GridLayout(4, 1, 15, 15));
        panel.setBackground(new Color(3, 45, 48));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton editInfoButton = createModernButton("Edit Account Info");
        JButton signOutButton = createModernButton("Sign Out");
        JButton deleteAccountButton = createModernButton("Delete Account");
        JButton cancelButton = createModernButton("Cancel");

        // Edit Account Info Button
        editInfoButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new EditAccountInfoForm().setVisible(true));
            this.dispose();
        });

        // Sign Out Button
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

        // Delete Account Button
        deleteAccountButton.addActionListener(e -> {
            User currentUser = DatabaseManager.getCurrentUser();
            
            if (currentUser != null) {
                int confirmation = JOptionPane.showConfirmDialog(
                    this, 
                    "Are you sure you want to delete your account? This action cannot be undone.", 
                    "Delete Account", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );

                if (confirmation == JOptionPane.YES_OPTION) {
                    AccountManager.removeAccount(currentUser); // Deactivate account
                    JOptionPane.showMessageDialog(this, "Account deleted successfully.");
                    DatabaseManager.signOut(); // Sign out the user
                    this.dispose(); // Close the form
                    SwingUtilities.invokeLater(() -> new Login().setVisible(true)); // Return to login screen
                }
            } else {
                JOptionPane.showMessageDialog(this, "No user is currently signed in.");
            }
        });

        // Cancel Button
        cancelButton.addActionListener(e -> {
            this.dispose();
            SwingUtilities.invokeLater(() -> new UserPanel().setVisible(true));
        });

        panel.add(editInfoButton);
        panel.add(signOutButton);
        panel.add(deleteAccountButton);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(3, 45, 48));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(cancelButton);

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
        SwingUtilities.invokeLater(() -> new EditAccountForm().setVisible(true));
    }
}
