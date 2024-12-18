package com.hotelapplication.frontend;

import com.hotelapplication.backend.*;

public class ReservationMain {

    public static void main(String[] args) {
        // Sample data for a reservation
        String roomType = "----------- ";
        String roomCapacity = "------- ";
        String checkInDate = "--------- ";
        String checkOutDate = "-------- ";

        // Create and display the ViewReservation form
        
        java.awt.EventQueue.invokeLater(() -> {
            new ViewReservation(roomType, roomCapacity, checkInDate, checkOutDate).setVisible(true);
        });
    }
}
