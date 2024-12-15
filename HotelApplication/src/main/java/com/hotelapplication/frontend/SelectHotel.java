package com.hotelapplication.frontend;

import com.hotelapplication.backend.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * GUI for selecting a hotel.
 */
public class SelectHotel extends JFrame {

    private JComboBox<String> hotelComboBox;
    private JButton selectButton;

    public SelectHotel() {
        setTitle("Select Hotel");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Label
        JLabel label = new JLabel("Select a Hotel:");
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        mainPanel.add(label, BorderLayout.NORTH);

        // Hotel ComboBox
        ArrayList<Hotel> hotels = DatabaseManager.getAllHotels();
        String[] hotelNames = hotels.stream().map(HotelManager::getHotelName).toArray(String[]::new);
        hotelComboBox = new JComboBox<>(hotelNames);
        mainPanel.add(hotelComboBox, BorderLayout.CENTER);

        // Select Button
        selectButton = new JButton("Select");
        selectButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        selectButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectButton.addActionListener(new SelectHotelListener(hotels));
        mainPanel.add(selectButton, BorderLayout.SOUTH);

        // Add to frame
        add(mainPanel);
    }

    /**
     * Listener for selecting a hotel.
     */
    private class SelectHotelListener implements ActionListener {
        private final ArrayList<Hotel> hotels;

        public SelectHotelListener(ArrayList<Hotel> hotels) {
            this.hotels = hotels;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = hotelComboBox.getSelectedIndex();
            if (selectedIndex >= 0) {
                HotelManager.setCurrentHotel(hotels.get(selectedIndex));
                JOptionPane.showMessageDialog(
                    SelectHotel.this,
                    "You have selected: " + HotelManager.getHotelName(hotels.get(selectedIndex)),
                    "Hotel Selected",
                    JOptionPane.INFORMATION_MESSAGE
                );
                dispose();
            } else {
                JOptionPane.showMessageDialog(
                    SelectHotel.this,
                    "Please select a valid hotel.",
                    "Selection Error",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }

    /**
     * Main method to test the SelectHotel GUI.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SelectHotel().setVisible(true));
    }
}

