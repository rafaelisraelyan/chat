package rafa.client;

/*import rafa.code.ComponentResizer;

import javax.swing.*;
import java.awt.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class UndecoratedExample {

    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame();

    class MainPanel extends JPanel {

        public MainPanel() {
            setBackground(Color.gray);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(400, 400);
        }
    }

    class BorderPanel extends JPanel {

        private JLabel label;
        int pX, pY;

        public BorderPanel() {
            label = new JLabel(" X ");
            label.setOpaque(true);
            label.setBackground(Color.RED);
            label.setForeground(Color.WHITE);

            setBackground(Color.black);
            setLayout(new FlowLayout(FlowLayout.RIGHT));

            add(label);

            label.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    System.exit(0);
                }
            });
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent me) {
                    // Get x,y and store them
                    pX = me.getX();
                    pY = me.getY();

                }

                public void mouseDragged(MouseEvent me) {

                    frame.setLocation(frame.getLocation().x + me.getX() - pX,
                            frame.getLocation().y + me.getY() - pY);
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent me) {

                    frame.setLocation(frame.getLocation().x + me.getX() - pX,
                            frame.getLocation().y + me.getY() - pY);
                }
            });
        }
    }

    private void createAnsShowGui() {
        panel.setLayout(new BorderLayout());
        panel.add(new MainPanel(), BorderLayout.CENTER);
        panel.add(new BorderPanel(), BorderLayout.PAGE_START);
        panel.setBorder(new LineBorder(Color.BLACK, 5));
        ComponentResizer cr = new ComponentResizer();
        cr.setMinimumSize(new Dimension(300, 300));
        cr.setMaximumSize(new Dimension(800, 600));
        cr.registerComponent(frame);
        cr.setSnapSize(new Dimension(10, 10));
        frame.setUndecorated(true);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UndecoratedExample().createAnsShowGui();
            }
        });
    }
}*/