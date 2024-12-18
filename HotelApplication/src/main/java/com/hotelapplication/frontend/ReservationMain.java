package login.and.signup;

public class ReservationMain {

    public static void main(String[] args) {
        // Sample data for a reservation
        String roomType = "Room type?";
        String roomCapacity = "Room capacity?";
        String checkInDate = "Check in Date?";
        String checkOutDate = "Checkout Date?";

        // Create and display the ViewReservation form
        
        java.awt.EventQueue.invokeLater(() -> {
            new ViewReservation(roomType, roomCapacity, checkInDate, checkOutDate).setVisible(true);
        });
    }
}
