package rafa.client;

import rafa.code.ComponentResizer;
import rafa.code.RoundedBorder;
import rafa.code.VerticalLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import static rafa.code.createIcon.createIcon;

public class Login extends JFrame {

    private static final int WIDTH_W = 970;
    private static final int HEIGHT_W = 800;
    private int x;
    private int y;
    private JFrame frame = new JFrame("Chat");
    private JPanel p = new JPanel();
    private JPanel panelMain = new JPanel();
    private JPanel panelTop = new JPanel();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Login().UI());
    }

    private Login(){
        createUI();
    }

    private void createUI() {
        JPanel panelLogin = new JPanel();
        JLabel lblName = new JLabel("Введите имя:");
        JButton btnLogin = new JButton("Войти");
        JTextField tfName = new JTextField();

        JLabel iconWindow = new JLabel();
        iconWindow.setIcon(createIcon("../img/logoMain.png"));
        iconWindow.setPreferredSize(new Dimension(20,20));

        JLabel titleWindow = new JLabel(frame.getTitle());
        titleWindow.setForeground(Colors.mainTxtColor);
        titleWindow.setFont(new Font("", Font.BOLD,13));
        titleWindow.setBorder(new EmptyBorder(5,5,5,5));

        JLabel iconExit = new JLabel("❌");
        iconExit.setForeground(Colors.mainTxtColor);
        iconExit.setFont(new Font("", Font.BOLD,15));
        iconExit.setBorder(new EmptyBorder(3,8,3,8));
        iconExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        iconExit.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseMoved(MouseEvent evt){
                iconExit.setBackground(Color.RED);
                iconExit.setOpaque(true);
            }
        });
        iconExit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseExited(MouseEvent evt) {
                iconExit.setBackground(Colors.mainColor);
                iconExit.setOpaque(true);
            }
        });
        JPanel panelTitleWindow = new JPanel();
        panelTitleWindow.setBackground(Colors.mainColor);
        panelTitleWindow.add(iconWindow);
        panelTitleWindow.add(titleWindow);
        panelTop.setLayout(new BorderLayout());
        panelTop.setBackground(Colors.mainColor);
        panelTop.addMouseListener(new MouseAdapter() {
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
        });
        panelTop.add(panelTitleWindow, BorderLayout.WEST);
        panelTop.add(iconExit, BorderLayout.EAST);


        lblName.setForeground(Colors.mainTxtColor);
        lblName.setHorizontalAlignment(JLabel.CENTER);
        lblName.setFont(new Font("Arial", Font.BOLD,24));
        lblName.setBounds(0, 0, 300, 37);

        tfName.setBackground(Colors.sideColor);
        tfName.setFont(new Font("Arial", Font.BOLD, 18));
        //tfName.setBorder(new RoundedBorder(2,4,4,4,10));
        tfName.setBorder(BorderFactory.createTitledBorder(new RoundedBorder(2,5,2,5,2,"tfName"), "Имя:", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Colors.sideTxtColor));
        tfName.setForeground(Colors.mainTxtColor);
        tfName.setCaretColor(tfName.getForeground());
        tfName.setName("tfName");
        tfName.setBounds(10, 45, 280, 55);

        tfName.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    EventQueue.invokeLater(() -> new Client().UI(tfName.getText()));
                    frame.dispose();
                }
            }

        });

        btnLogin.setBackground(Colors.mainColor);
        btnLogin.setForeground(Colors.mainTxtColor);
        btnLogin.setName("btnLogin");
        btnLogin.setFont(new Font("Arial", Font.BOLD,18));
        btnLogin.setBorder(new RoundedBorder(-2,3,3,3,3,"btnLogin"));
        btnLogin.setOpaque(true);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setBounds(55,110,180,55);
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent evt) {
                btnLogin.setBackground(Colors.mainBorderColor);
                btnLogin.repaint();
            }
        });
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent evt) {
                btnLogin.setBackground(Colors.mainColor);
                btnLogin.repaint();
            }
        });
        btnLogin.addActionListener(e -> EventQueue.invokeLater(() -> new Client().UI(tfName.getText())));

        panelLogin.setBackground(Colors.sideColor);
        panelLogin.setLayout(null);
        panelLogin.setBorder(new EmptyBorder(4,4,4,4));
        panelLogin.setPreferredSize(new Dimension(300,300));
        panelLogin.setName("00");
        panelLogin.add(lblName);
        panelLogin.add(tfName);
        panelLogin.add(btnLogin);

        panelMain.setBackground(Colors.sideColor);
        //panelMain.setBorder(new EmptyBorder(4,4,4,4));
        panelMain.setLayout(new VerticalLayout());
        panelMain.add(panelLogin);
    }

    private void UI(){
        p.setLayout(new BorderLayout());
        p.add(panelMain,BorderLayout.CENTER);
        p.add(panelTop,BorderLayout.PAGE_START);
        //p.setBorder(new EmptyBorder(4,4,4,4));
        ComponentResizer cr = new ComponentResizer();
        cr.setMinimumSize(new Dimension(400, 550));
        cr.setMaximumSize(getMaximumSize());
        cr.registerComponent(frame);
        frame.setResizable(true);
        frame.setSize(WIDTH_W,HEIGHT_W);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setBackground(Colors.sideColor);
        frame.setTitle("Чат");
        frame.setUndecorated(true);
        frame.setForeground(Colors.mainTxtColor);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(p);
        frame.getRootPane().setDoubleBuffered(true);
        frame.setMinimumSize(new Dimension(600,650));
        frame.setVisible(true);
    }
}
