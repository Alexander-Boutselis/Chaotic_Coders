import javax.swing.*;
import java.awt.*;

public class UserPanel extends JFrame {
    public UserPanel() {
        setTitle("User Menu");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(3, 45, 48));

        JPanel panel = new JPanel(new GridLayout(3, 2, 15, 15));
        panel.setBackground(new Color(3, 45, 48));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton onlineResButton = createModernButton("Online Reservations");
        JButton viewRoomsButton = createModernButton("Available Rooms");
        JButton checkInButton = createModernButton("Check-in");
        JButton checkOutButton = createModernButton("Check-out");
        JButton promotionsButton = createModernButton("Promotions");
        JButton receiptsButton = createModernButton("Receipts");
        JButton exitButton = createModernButton("Exit");

        exitButton.addActionListener(e -> System.exit(0));

        panel.add(onlineResButton);
        panel.add(viewRoomsButton);
        panel.add(checkInButton);
        panel.add(checkOutButton);
        panel.add(promotionsButton);
        panel.add(receiptsButton);

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
