import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class dashboard extends JFrame implements ActionListener {

    JButton add,rec;

    dashboard(){

        super("HOTEL MANAGEMENT SYSTEM");

        rec = new JButton("RECEPTION");
        rec.setBounds(425, 510, 140, 30);
        rec.setFont(new Font("Tahoma", Font.BOLD,15));
        rec.setBackground(new Color(255,98,0));
        rec.setForeground(Color.WHITE);
        rec.addActionListener(this);
        add(rec);


        add = new JButton("ADMIN");
        add.setBounds(880, 510, 140, 30);
        add.setFont(new Font("Tahoma", Font.BOLD,15));
        add.setBackground(new Color(255,98,0));
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);


        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("n3.jpg"));
        Image i1 = imageIcon.getImage().getScaledInstance(1950, 1090, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(0,0,1900,1050);
        add(label);

        setLayout(null);
        setSize(1950,1090);
        setVisible(true);
    }
    

    public static void main(String[] args) {
        new dashboard();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == rec) {
            
        } else {
            
        }
    }
}
