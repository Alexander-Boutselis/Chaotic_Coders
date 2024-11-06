package com.hotelapplication.frontend;
import com.hotelapplication.backend.*;


import javax.swing.*;
import java.awt.*;

public class ManagerPanel extends JFrame {
    public ManagerPanel() {
        setTitle("Manager Menu");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(3, 45, 48));

        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBackground(new Color(3, 45, 48));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton viewReportsButton = createModernButton("View Reports");
        JButton manageHotelButton = createModernButton("Manage Hotel");
        JButton setPromotionsButton = createModernButton("Set Promotions");
        JButton exitButton = createModernButton("Exit");

        exitButton.addActionListener(e -> System.exit(0));

        panel.add(viewReportsButton);
        panel.add(manageHotelButton);
        panel.add(setPromotionsButton);

        JPanel exitPanel = new JPanel();
        exitPanel.setBackground(new Color(3, 45, 48));
        exitPanel.add(exitButton);

        add(panel, BorderLayout.CENTER);
        add(exitPanel, BorderLayout.SOUTH);
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
}
