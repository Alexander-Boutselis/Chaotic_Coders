package com.hotelapplication.frontend;
import com.hotelapplication.backend.*;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class login extends JFrame implements ActionListener {

    JTextField textField1;
    JPasswordField passwordField1;
    JButton b1, b2;

    login() {
        JLabel label1 = new JLabel("Username");
        label1.setBounds(40, 20, 100, 30);
        label1.setFont(new Font("Tahoma", Font.BOLD, 16));
        label1.setForeground(Color.WHITE);
        add(label1);

        textField1 = new JTextField();
        textField1.setBounds(150, 20, 150, 30);
        textField1.setForeground(Color.WHITE);
        textField1.setBackground(new Color(26, 104, 110));
        add(textField1);

        JLabel label2 = new JLabel("Password");
        label2.setBounds(40, 70, 100, 30);
        label2.setFont(new Font("Tahoma", Font.BOLD, 16));
        label2.setForeground(Color.WHITE);
        add(label2);

        passwordField1 = new JPasswordField();
        passwordField1.setBounds(150, 70, 150, 30);
        passwordField1.setForeground(Color.WHITE);
        passwordField1.setBackground(new Color(26, 104, 110));
        add(passwordField1);

        b1 = new JButton("Login");
        b1.setBounds(40, 140, 120, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(180, 140, 120, 30);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        getContentPane().setBackground(new Color(3, 45, 48));
        setLayout(null);
        setLocation(300, 170);
        setSize(600, 300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String username = textField1.getText();
            String password = new String(passwordField1.getPassword());
            String role = GUIManager.authenticateAndGetRole(username, password);

            if (role != null) {
                SwingUtilities.invokeLater(() -> {
                    this.dispose(); // Close the login window
                    if ("Manager".equals(role)) {
                        new ManagerPanel().setVisible(true);  // Launch ManagerPanel
                    } else {
                        new UserPanel().setVisible(true);  // Launch UserPanel
                    }
                });
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == b2) {
            System.exit(0);  // Exit the application
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new login().setVisible(true);
        });
    }
}
