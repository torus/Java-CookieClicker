import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.util.Date;

public class CookieClicker extends JFrame {
    double cookies = 0;
    double cookiesPerSecond = 0;

    public CookieClicker() {
        setSize(800, 600);
        setTitle("Cookie Clicker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new FlowLayout());

        Container c = getContentPane();
        c.add(new CookiePanel(this));
        c.add(new ProducerPanel(this));
        c.add(new ShopPanel(this));
        setVisible(true);
    }

    public double getCookiesPerSecond() {
        return cookiesPerSecond;
    }

    public void addCookiesPerSecond(double cps) {
        cookiesPerSecond += cps;
    }

    public double getCookies() {
        return cookies;
    }

    public void decreaseCookies(double amount) {
        cookies -= amount;
    }

    public class CookiePanel extends JPanel implements ActionListener {
        JLabel cookiesLabel, cpsLabel;
        JButton cookieButton;
        Date lastDate = new Date();
        CookieClicker clicker;

        public CookiePanel(CookieClicker parent) {
            clicker = parent;
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

            cookies += clicker.getCookiesPerSecond() * (elapsed / 1000.0);

            cookiesLabel.setText(String.format("%d Cookies", (long)cookies));
            cpsLabel.setText(String.format("per seconds: %.2f", cookiesPerSecond));
        }

        public void actionPerformed(ActionEvent e) {
            cookies += 1.0;
            repaint();
        }
    }

    public class ProducerPanel extends JPanel {
        public ProducerPanel(CookieClicker clicker) {
            setPreferredSize(new Dimension(280, 550));
            BevelBorder border = new BevelBorder(BevelBorder.RAISED);
            setBorder(border);
        }
    }

    public class ShopItem {
        final String name;
        final double price;
        final double cps;
        final JButton button;

        public ShopItem(String nm, double pr, double c, final CookieClicker clicker) {
            name = nm;
            price = pr;
            cps = c;

            button = new JButton(String.format("%s %d", name, (long)price));
            button.setPreferredSize(new Dimension(200, 40));
            ActionListener taskPerformer = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (price <= clicker.getCookies()) {
                            clicker.addCookiesPerSecond(cps);
                            clicker.decreaseCookies(price);
                        }
                    }
                };
            button.addActionListener(taskPerformer);
        }

        public JButton getButton() {
            return button;
        }
    }

    public class ShopPanel extends JPanel {
        final CookieClicker clicker;
        public ShopPanel(CookieClicker parent) {
            clicker = parent;

            setPreferredSize(new Dimension(280, 550));
            BevelBorder border = new BevelBorder(BevelBorder.RAISED);
            setBorder(border);

            JLabel label = new JLabel("Shop", JLabel.CENTER);
            label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 23));
            label.setPreferredSize(new Dimension(200, 50));
            add(label);

            addItem("Cursor", 10, 0.1);
            addItem("Grandma", 20, 1);
            addItem("Farm", 30, 3);
        }

        public void addItem(String name, double price, double cps) {
            ShopItem item = new ShopItem(name, price, cps, clicker);
            add(item.getButton());
        }
    }
}
