//EditAccountInfoForm.java

package com.hotelapplication.frontend;
import com.hotelapplication.backend.*;

import javax.swing.*;
import java.awt.*;

/**
 * The EditAccountInfoForm class provides a GUI for users to update their account information,
 * including full name, username, and password.
 * 
 * @author Minas Papazyan
 */
public class EditAccountInfoForm extends JFrame {

    /**
     * Constructs the EditAccountInfoForm GUI, initializing the fields with the user's
     * current information and providing options to save or discard changes.
     */
    public EditAccountInfoForm() {
        setTitle("Edit Account Info");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(3, 45, 48));

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(new Color(3, 45, 48));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Labels and input fields for user information
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setForeground(Color.WHITE);
        JTextField nameField = new JTextField();

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        JPasswordField passwordField = new JPasswordField();

        // Pre-fill the fields with current user information
        User currentUser = DatabaseManager.getCurrentUser();
        if (currentUser != null) {
            String currentFirstName = currentUser.getFirstName();
            String currentLastName = currentUser.getLastName();
            nameField.setText(currentFirstName + " " + currentLastName);
            usernameField.setText(currentUser.getUsername());
        }

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        // Save Changes Button
        JButton saveButton = createModernButton("Save Changes");
        saveButton.addActionListener(e -> {
            String fullName = nameField.getText().trim();
            String newUsername = usernameField.getText().trim();
            String newPassword = new String(passwordField.getPassword()).trim();

            if (!fullName.isEmpty()) {
                String[] nameParts = fullName.split(" ", 2);
                String firstName = nameParts[0];
                String lastName = nameParts.length > 1 ? nameParts[1] : "";

                AccountManager.setFirstName(currentUser, firstName);
                AccountManager.setLastName(currentUser, lastName);
            }

            if (!newUsername.isEmpty() && !newUsername.equals(currentUser.getUsername())) {
                if (AccountManager.isUniqueUsername(newUsername)) {
                    AccountManager.setUsername(currentUser, newUsername);
                } else {
                    JOptionPane.showMessageDialog(this, "Username is already taken.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            if (!newPassword.isEmpty()) {
                AccountManager.setPassword(currentUser, newPassword);
            }

            JOptionPane.showMessageDialog(this, "Changes saved successfully!");
            this.dispose();
            SwingUtilities.invokeLater(() -> new UserPanel().setVisible(true));
        });

        // Discard Changes Button
        JButton discardButton = createModernButton("Discard Changes");
        discardButton.addActionListener(e -> {
            this.dispose();
            SwingUtilities.invokeLater(() -> new EditAccountForm().setVisible(true));
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(3, 45, 48));
        buttonPanel.add(saveButton);
        buttonPanel.add(discardButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates a modern-styled JButton with the given text.
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
     * The main method to test the EditAccountInfoForm GUI.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditAccountInfoForm().setVisible(true));
    }
}
