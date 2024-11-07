import javax.swing.*;
import java.awt.*;

public class ManagerPanel extends JFrame {
    public ManagerPanel() {
        setTitle("Manager Menu");
        setSize(600, 700);  // Match size with UserPanel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(3, 45, 48));

        JPanel panel = new JPanel(new GridLayout(2, 2, 20, 20));
        panel.setBackground(new Color(3, 45, 48));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton selectHotelButton = createModernButton("Select Hotel");
        JButton editAccountButton = createModernButton("Edit My Account");
        JButton viewReservationsButton = createModernButton("View All Reservations");
        JButton editHotelButton = createModernButton("Edit Hotel");
        JButton exitButton = createModernButton("Exit");

        exitButton.addActionListener(e -> System.exit(0));

        panel.add(selectHotelButton);
        panel.add(editAccountButton);
        panel.add(viewReservationsButton);
        panel.add(editHotelButton);

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
        SwingUtilities.invokeLater(() -> new ManagerPanel().setVisible(true));
    }
}
