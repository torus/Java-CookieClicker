import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.util.Date;

public class CookieClicker extends JFrame {
    public CookieClicker() {
        setSize(800, 600);
        setTitle("Cookie Clicker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        Container c = getContentPane();
        c.add(new CookiePanel());
        c.add(new ProducerPanel());
        c.add(new ItemPanel());
        setVisible(true);
    }

    public class CookiePanel extends JPanel implements ActionListener {
        JLabel cookiesLabel, cpsLabel;
        JButton cookieButton;
        double cookies = 0;
        double cookiesPerSecond = 0.15;
        Date lastDate = new Date();

        public CookiePanel() {
            setPreferredSize(new Dimension(220, 550));
            BevelBorder border = new BevelBorder(BevelBorder.RAISED);
            setBorder(border);

            cookiesLabel = new JLabel("0 Cookies", JLabel.CENTER);
            cookiesLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 23));
            cookiesLabel.setPreferredSize(new Dimension(200, 50));
            cpsLabel = new JLabel("per seconds: 0", JLabel.CENTER);
            cpsLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            cpsLabel.setPreferredSize(new Dimension(200, 20));
            cookieButton = new JButton("Cookie");
            cookieButton.setPreferredSize(new Dimension(200, 200));
            cookieButton.addActionListener(this);

            add(cookiesLabel);
            add(cpsLabel);
            add(cookieButton);

            int delay = 100;
            final JPanel self = this;
            ActionListener taskPerformer = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        self.repaint();
                    }
                };
            new Timer(delay, taskPerformer).start();
        }

        public void paintComponent(Graphics g) {
            // super.paintComponent(g);
            Date current = new Date();
            long elapsed = current.getTime() - lastDate.getTime();
            lastDate = current;

            cookies += cookiesPerSecond * (elapsed / 1000.0);

            cookiesLabel.setText(String.format("%d Cookies", (long)cookies));
            cpsLabel.setText(String.format("per seconds: %.2f", cookiesPerSecond));
        }

        public void addCookiesPerSecond(double cps) {
            cookiesPerSecond += cps;
        }

        public void actionPerformed(ActionEvent e) {
            cookies += 1.0;
            repaint();
        }
    }

    public class ProducerPanel extends JPanel {
        public ProducerPanel() {
            setPreferredSize(new Dimension(280, 550));
            BevelBorder border = new BevelBorder(BevelBorder.RAISED);
            setBorder(border);
        }
    }

    public class ItemPanel extends JPanel {
        public ItemPanel() {
            setPreferredSize(new Dimension(280, 550));
            BevelBorder border = new BevelBorder(BevelBorder.RAISED);
            setBorder(border);
        }
    }
}
