package rafa.client.chat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import rafa.client.chat.Client;
import static rafa.client.chat.Client.NAME;
import static rafa.client.chat.Client.IP;
import static rafa.client.chat.Client.PORT;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

    public static String eror;
    private JPanel contentPane;
    private JTextField txtAddress;
    private JTextField txtPort;
    private JTextField txtName;
    private JLabel lblIpAddress;
    private JLabel lblPort;
    private JLabel lblAddressDesc;
    private JLabel lblPortDesc;
    private JButton btnLogin;
    
    public String NickName;
    
    
    public Login() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Font font = new Font("Arial",Font.PLAIN,14);
        
        setResizable(false);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 380);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(0x232222));
        contentPane.setForeground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);


        txtName = new JTextField();
        txtName.setBackground(new Color(0x232226));
        txtName.setFont(font);
        txtName.setForeground(Color.WHITE);
        txtName.setCaretColor(getBackground());
        txtName.setBounds(79, 60, 142, 26);
        contentPane.add(txtName);
        txtName.setColumns(10);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(119, 45, 61, 16);
        lblName.setForeground(Color.WHITE);
        contentPane.add(lblName);

        lblIpAddress = new JLabel("IP Address:");
        lblIpAddress.setBounds(111, 98, 78, 16);
        lblIpAddress.setForeground(Color.WHITE);
        contentPane.add(lblIpAddress);

        txtAddress = new JTextField();        
        txtAddress.setBackground(new Color(0x232226));
        txtAddress.setCaretColor(getBackground());
        txtAddress.setFont(font);
        txtAddress.setForeground(Color.WHITE);
        txtAddress.setBounds(79, 114, 142, 26);
        contentPane.add(txtAddress);
        txtAddress.setColumns(10);

        txtPort = new JTextField();
        txtPort.setBackground(new Color(0x232226));
        txtPort.setForeground(Color.WHITE);
        txtPort.setFont(font);
        txtPort.setCaretColor(getBackground());
        txtPort.setColumns(10);
        txtPort.setBounds(79, 184, 142, 26);
        contentPane.add(txtPort);

        lblPort = new JLabel("Port:");
        lblPort.setBounds(130, 168, 40, 16);
        lblPort.setForeground(Color.WHITE);
        contentPane.add(lblPort);

        lblAddressDesc = new JLabel("(Ex: 192.168.1.5)");
        lblAddressDesc.setForeground(Color.WHITE);
        lblAddressDesc.setBounds(95, 140, 110, 16);
        contentPane.add(lblAddressDesc);


        lblPortDesc = new JLabel("(Ex: 7777)");
        lblPortDesc.setBounds(115, 209, 70, 16);
        lblPortDesc.setForeground(Color.WHITE);
        contentPane.add(lblPortDesc);

        btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(0x6e6e6e));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setOpaque(true);
        btnLogin.setFont(new Font("Arial",Font.BOLD,18));

        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int lenName = txtName.getText().length();
                int lenPort = txtPort.getText().length();
                int lenIp = txtAddress.getText().length();
                do {
                    if (lenName == 0 && lenIp == 0 && lenPort == 0) {
                        new LoginEror("You must enter Name,Ip and port");
                        break;
                    } else {
                        if ((lenName == 0 && lenIp == 0) || (lenIp == 0 && lenPort == 0) || (lenName == 0 && lenPort == 0)) {
                            if (lenName == 0 && lenIp == 0) {
                                new LoginEror("You must enter Name and Ip");
                                break;
                            }
                            if (lenIp == 0 && lenPort == 0) {
                                new LoginEror("You must enter Ip and port");
                                break;
                            }
                            if (lenName == 0 && lenPort == 0) {
                                new LoginEror("You must enter Name and port");
                                break;
                            }
                        }
                        if (lenName == 0 || lenIp == 0 || lenPort == 0) {
                            if (lenName == 0) {
                                new LoginEror("You must enter Name.");
                                break;
                            }
                            if (lenPort == 0) {
                                new LoginEror("You must enter Port");
                                break;
                            }
                            if (lenIp == 0) {
                                new LoginEror("You must enter Ip");
                                break;
                            }

                        }

                        if (lenName != 0 && lenIp != 0 && lenPort != 0) {
                            NickName = txtName.getText();
                            String address = txtAddress.getText();
                            int port = Integer.parseInt(txtPort.getText());
                            login(NickName,address,port);
                        }
                            
                    }
                }while (false);
            }
        });
        btnLogin.setBounds(88, 300, 117, 29);
        contentPane.add(btnLogin);
    }

    private void login(String name, String address, int port) {
        dispose();
        NAME = name;
        PORT = port;
        IP = address;

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {new Client();}
        });
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
