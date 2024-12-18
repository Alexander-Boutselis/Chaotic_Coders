package com.hotelapplication.frontend;

import com.hotelapplication.backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The ViewAllReservations class represents the GUI for viewing all reservations of the current hotel.
 * This panel displays the details of all reservations in a scrollable text area.
 *
 * @author Your Name
 */
public class ViewAllReservations extends JFrame {

    private JTextArea textArea;

    /**
     * Constructs the ViewAllReservations GUI with all its components.
     */
    public ViewAllReservations() {
        setTitle("View All Reservations");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(3, 45, 48));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Text area to display reservations
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setBackground(new Color(245, 245, 245));
        textArea.setForeground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add a "View Reservations" button
        JButton viewButton = new JButton("View Reservations");
        viewButton.setFocusPainted(false);
        viewButton.setBackground(new Color(26, 104, 110));
        viewButton.setForeground(Color.WHITE);
        viewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        viewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewButton.addActionListener(new ViewReservationsListener());

        // Add components to the main panel
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(viewButton, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);
    }

    /**
     * Listener for the "View Reservations" button to fetch and display all reservations.
     */
    private class ViewReservationsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<Reservation> reservations = HotelManager.getAllReservations(HotelManager.getCurrentHotel());
            if (reservations.isEmpty()) {
                textArea.setText("No reservations found.");
            } else {
                textArea.setText(""); // Clear previous text
                for (Reservation reservation : reservations) {
                    System.out.println("Reservation ID: " + reservation.getReservationID());
                    String reservationDetails = String.format(
                        "Reservation ID: %d\nUser ID: %d\nRoom ID: %d\nHotel ID: %d\nStart Date: %s\nEnd Date: %s\nTotal Price: $%.2f\n\n",
                        reservation.getReservationID(),
                        reservation.getAssignedUserID(),
                        reservation.getRoomID(),
                        reservation.getHotelID(),
                        (reservation.getStartDate() != null ? reservation.getStartDate().toString() : "N/A"),
                        (reservation.getEndDate() != null ? reservation.getEndDate().toString() : "N/A"),
                        reservation.getTotalPrice()
                    );
                    textArea.append(reservationDetails);
                }
            }
        }
    }

    /**
     * Main method to test the ViewAllReservations GUI.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewAllReservations().setVisible(true));
    }
}
