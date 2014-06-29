import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CookieClicker extends JFrame {
    public CookieClicker() {
        setSize(500, 500);
        setTitle("Software Development II");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        MyJPanel panel= new MyJPanel();
        Container c = getContentPane();
        c.add(panel);
        setVisible(true);
    }

    public class MyJPanel extends JPanel implements ActionListener {
        JLabel cookiesLabel;
        JButton cookieButton;
        double cookies = 0;
        Dimension dim;
        public MyJPanel() {
            cookiesLabel = new JLabel("0 Cookies", JLabel.CENTER);
            cookiesLabel.setPreferredSize(new Dimension(400, 30));
            cookieButton = new JButton("Cookie");
            cookieButton.setPreferredSize(new Dimension(400, 400));
            cookieButton.addActionListener(this);

            add(cookiesLabel);
            add(cookieButton);
        }

        public void paintComponent(Graphics g) {
            dim = getSize();
            // super.paintComponent(g);

            cookiesLabel.setText(String.format("%d Cookies", (long)cookies));
        }

        public void actionPerformed(ActionEvent e) {
            // System.out.println("x");
            cookies += 1.0;
            repaint();
        }
    }
}
