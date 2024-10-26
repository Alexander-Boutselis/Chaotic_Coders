import javax.swing.*;
import java.awt.*;
//To be merged with Nura's Login screen
public class LoginScreen extends JFrame {

    public LoginScreen() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();
        JLabel roleLabel = new JLabel("Role:");
        String[] roles = { "User", "Manager" };
        JComboBox<String> roleBox = new JComboBox<>(roles);
        JButton loginButton = new JButton("Login");

        panel.add(userLabel);
        panel.add(userField);
        panel.add(roleLabel);
        panel.add(roleBox);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(e -> {
            String role = (String) roleBox.getSelectedItem();
            if ("User".equals(role)) {
                new UserPanel().setVisible(true);
            } else {
                new ManagerPanel().setVisible(true);
            }
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);
        });
    }
}

// User Panel
class UserPanel extends JFrame {
    public UserPanel() {
        setTitle("User Menu");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(3, 2, 15, 15));
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
        exitPanel.add(exitButton);

        add(panel, BorderLayout.CENTER);
        add(exitPanel, BorderLayout.SOUTH);
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}

// Manager Panel
class ManagerPanel extends JFrame {
    public ManagerPanel() {
        setTitle("Manager Menu");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
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
        exitPanel.add(exitButton);

        add(panel, BorderLayout.CENTER);
        add(exitPanel, BorderLayout.SOUTH);
    }

    private JButton createModernButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(new Color(178, 34, 34));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
