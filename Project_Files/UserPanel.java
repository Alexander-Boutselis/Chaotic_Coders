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

        exitButton.addActionListener(e -> System.exit(0));

        panel.add(viewReservationsButton);
        panel.add(makeReservationButton);
        panel.add(selectHotelButton);
        panel.add(editAccountButton);

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
        button.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UserPanel().setVisible(true);
        });
    }
    
}
