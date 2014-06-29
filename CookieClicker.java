import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CookieClicker extends JFrame {
    public CookieClicker() {
        setSize(400, 600);
        setTitle("Software Development II");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        CookiePanel panel= new CookiePanel();
        Container c = getContentPane();
        c.add(panel);
        setVisible(true);
    }

    public class CookiePanel extends JPanel implements ActionListener {
        JLabel cookiesLabel;
        JButton cookieButton;
        double cookies = 0;

        public CookiePanel() {
            cookiesLabel = new JLabel("0 Cookies", JLabel.CENTER);
            cookiesLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 23));
            cookiesLabel.setPreferredSize(new Dimension(300, 50));
            cookieButton = new JButton("Cookie");
            cookieButton.setPreferredSize(new Dimension(300, 300));
            cookieButton.addActionListener(this);

            add(cookiesLabel);
            add(cookieButton);
        }

        public void paintComponent(Graphics g) {
            // super.paintComponent(g);

            cookiesLabel.setText(String.format("%d Cookies", (long)cookies));
        }

        public void actionPerformed(ActionEvent e) {
            cookies += 1.0;
            repaint();
        }
    }
}
