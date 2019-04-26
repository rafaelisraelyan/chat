package rafa.client;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static rafa.code.createIcon.createIcon;

public class Settings {

    private JFrame frame = new JFrame();
    private JPanel panelTop = new JPanel();
    private JPanel panelCenter = new JPanel();
    private JPanel p = new JPanel();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Settings().UI());
    }

    Settings(){
        JLabel iconWindow = new JLabel();
        iconWindow.setIcon(createIcon("../img/logoMain.png"));

        JLabel titleWindow = new JLabel("Настройки");
        titleWindow.setForeground(Colors.mainTxtColor);
        titleWindow.setFont(new Font("", Font.BOLD,13));
        titleWindow.setBorder(new EmptyBorder(5,5,5,5));

        JLabel iconExit = new JLabel("❌");
        iconExit.setForeground(Colors.mainTxtColor);
        iconExit.setFont(new Font("", Font.BOLD,15));
        iconExit.setBorder(new EmptyBorder(5,7,5,5));
        iconExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });

        JPanel panelContent = new JPanel();
        panelContent.setBackground(Colors.sideColor);
        JScrollPane scroll = new JScrollPane(panelContent);
        scroll.setBackground(Colors.sideColor);

        panelCenter.setBackground(Colors.sideColor);
        panelCenter.setPreferredSize(new Dimension(450,500));
        panelCenter.setBorder(new EmptyBorder(2,2,2,2));
        panelCenter.setLayout(new BorderLayout());
        panelTop.setLayout(new BorderLayout());
        panelTop.setBackground(Colors.mainColor);
        /*panelTop.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(!e.isMetaDown()){
                    x = e.getX();
                    y = e.getY();
                }
            }
        });
        panelTop.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if(!e.isMetaDown()){
                    Point p = frame.getLocation();//I Simply had to put button.getLocation() instead of getLocation()
                    frame.setLocation(p.x + e.getX()-x,
                            p.y + e.getY()-y);
                }
            }
        });*/
        panelCenter.add(panelTop,BorderLayout.NORTH);
        panelCenter.add(panelContent,BorderLayout.CENTER);
        panelTop.add(titleWindow, BorderLayout.WEST);
        panelTop.add(iconExit, BorderLayout.EAST);
    }

    void UI(){
        p.setBackground(Colors.sideColor);
        p.setLayout(new BorderLayout());
        p.add(panelCenter, BorderLayout.CENTER);
        p.add(panelTop, BorderLayout.PAGE_START);
        /*ComponentResizer cr = new ComponentResizer();
        cr.setMinimumSize(new Dimension(410, 450));
        cr.setMaximumSize(new Dimension(frame.getMaximumSize()));
        cr.registerComponent(frame);
        cr.setSnapSize(new Dimension(10, 10));*/
        frame.setUndecorated(true);
        frame.setSize(410,450);
        frame.getContentPane().setBackground(Colors.sideColor);
        frame.add(p);
        frame.pack();
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
        /*panelMain.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(!e.isMetaDown()){
                    x = e.getX();
                    y = e.getY();
                }
            }
        });
        panelMain.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                //if(!e.isMetaDown()){
                    if (frame.getWidth() <= 350 && frame.getHeight() <= 400) {
                        if (x - e.getX() < 0 ) frame.setSize(frame.getWidth()+1,
                                frame.getHeight()+1);
                    }
                    else{
                        if (x - e.getX() < 0 ) frame.setSize(frame.getWidth()+3, frame.getHeight());
                        else if (x - e.getX() > 0 ) frame.setSize(frame.getWidth()-3, frame.getHeight());
                        else if (y - e.getX() < 0) frame.setSize(frame.getWidth(),frame.getHeight() + 3 );
                        else if (y - e.getY() > 0 ) frame.setSize(frame.getWidth(),frame.getHeight() - 3);
                        else if (x - e.getX() < 0 && y - e.getY() < 0) frame.setSize(frame.getWidth()+(x-e.getX()),frame.getHeight() + 3);
                        else if (x - e.getY() > 0 && y - e.getY() > 0) frame.setSize(frame.getWidth()-(x-e.getX()),frame.getHeight()- 3);
                    }

                }
            //}
        });*/
        //frame.setAlwaysOnTop(true);
        //frame.setOpacity(0.5f);
        //frame.setResizable(false);
        //frame.pack();
        /*Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (frame.getWidth() / 2),
                middle.y - (frame.getHeight() / 2));
        frame.setLocation(newLocation);
        frame.setLocationRelativeTo(null);
        //frame.setLocation(c.getLocation().x + (c.getWidth()/2-frame.getWidth()/2) , c.getLocation().y + (c.getHeight()/2- frame.getHeight()/2));
        frame.setUndecorated(true);
        frame.getRootPane().setBorder(new LineBorder(Colors.mainTxtColor,1));
        frame.getContentPane().setBackground(Colors.mainColor);
        frame.setSize(new Dimension(450,500));
        frame.setShape(new Rectangle(0,0,frame.getWidth(),frame.getHeight()));
        frame.add(panelMain);*/
