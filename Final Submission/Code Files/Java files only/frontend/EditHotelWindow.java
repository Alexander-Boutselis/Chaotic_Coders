package com.hotelapplication.frontend;

import com.hotelapplication.backend.*;

import javax.swing.*;
import java.awt.*;

public class EditHotelWindow extends JFrame {
    public EditHotelWindow() {
        setTitle("Edit Hotel");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JButton viewRoomsButton = new JButton("View All Rooms");
        JButton addRoomButton = new JButton("Add Room");
        JButton removeRoomButton = new JButton("Remove Room");
        JButton editInfoButton = new JButton("Edit Hotel Info");

        buttonPanel.add(viewRoomsButton);
        buttonPanel.add(addRoomButton);
        buttonPanel.add(removeRoomButton);
        buttonPanel.add(editInfoButton);

        add(buttonPanel, BorderLayout.CENTER);

        // View All Rooms Logic
        viewRoomsButton.addActionListener(e -> {
            if (HotelManager.getCurrentHotel() == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "No hotel selected. Please select a hotel first.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );
            } else {
                JTextArea roomList = new JTextArea(
                        HotelManager.getAllCurrentHotelRoomsInfo()
                );
                roomList.setEditable(false);
                JOptionPane.showMessageDialog(
                        this,
                        new JScrollPane(roomList),
                        "All Rooms",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        // Add Room Logic
        addRoomButton.addActionListener(e -> {
            try {
                String bedType = JOptionPane.showInputDialog(this, "Enter Bed Type (e.g., twin, full, queen, king, suite):");
                if (bedType == null || bedType.trim().isEmpty()) return;

                String bedsInput = JOptionPane.showInputDialog(this, "Enter Number of Beds:");
                if (bedsInput == null || bedsInput.trim().isEmpty()) return;
                int numOfBeds = Integer.parseInt(bedsInput);

                String description = JOptionPane.showInputDialog(this, "Enter Room Description (optional):");
                if (description == null) description = "";

                RoomManager.createRoom(numOfBeds, bedType.toLowerCase(), description);
                JOptionPane.showMessageDialog(this, "Room added successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding room: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Remove Room Logic
        removeRoomButton.addActionListener(e -> {
            try {
                String roomNumberInput = JOptionPane.showInputDialog(this, "Enter Room Number to Remove:");
                if (roomNumberInput == null || roomNumberInput.trim().isEmpty()) return;
                int roomNumber = Integer.parseInt(roomNumberInput);

                RoomManager.removeRoom(roomNumber);
                JOptionPane.showMessageDialog(this, "Room removed successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error removing room: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Edit Hotel Info Logic
        editInfoButton.addActionListener(e -> {
            String newName = JOptionPane.showInputDialog(this, "Enter new hotel name:", HotelManager.getHotelName());
            if (newName != null && !newName.trim().isEmpty()) {
                HotelManager.setHotelName(newName);
            }

            String newAddress = JOptionPane.showInputDialog(this, "Enter new hotel address:", HotelManager.getHotelAddress());
            if (newAddress != null && !newAddress.trim().isEmpty()) {
                HotelManager.setHotelAddress(newAddress);
            }

            JOptionPane.showMessageDialog(this, "Hotel information updated successfully.");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditHotelWindow().setVisible(true));
    }
}
