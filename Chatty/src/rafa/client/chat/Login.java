package rafa.client.chat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static rafa.client.chat.Client.*;
import rafa.client.chat.Client;

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
    public String NickName;
    
    public Login() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setResizable(false);
        setTitle("Login");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 380);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.BLACK);
        contentPane.setForeground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);


        txtName = new JTextField();
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
        txtAddress.setBounds(79, 114, 142, 26);
        contentPane.add(txtAddress);
        txtAddress.setColumns(10);

        txtPort = new JTextField();
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

        JButton btnLogin = new JButton("Login");

        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int lenName = txtName.getText().length();
                int lenPort = txtPort.getText().length();
                int lenIp = txtAddress.getText().length();
                do {
                    if (lenName == 0 && lenIp == 0 && lenPort == 0) {
                        eror = "You must enter Name,Ip and port";
                        new LoginEror();
                    } else {
                        if ((lenName == 0 && lenIp == 0) || (lenIp == 0 && lenPort == 0) || (lenName == 0 && lenPort == 0)) {
                            if (lenName == 0 && lenIp == 0) {
                                eror = "You must enter Name and Ip";
                                new LoginEror();
                                break;
                            }
                            if (lenIp == 0 && lenPort == 0) {
                                eror = "You must enter Ip and port";
                                new LoginEror();
                                break;
                            }
                            if (lenName == 0 && lenPort == 0) {
                                eror = "You must enter Name and port";
                                new LoginEror();
                                break;
                            }
                        }
                        if (lenName == 0 || lenIp == 0 || lenPort == 0) {
                            if (lenName == 0) {
                                eror = "You must enter Name.";
                                new LoginEror();
                            }
                            if (lenPort == 0) {
                                eror = "You must enter Port";
                                new LoginEror();
                            }
                            if (lenIp == 0) {
                                eror = "You must enter Ip";
                                new LoginEror();
                            }

                        }

                        if (lenName != 0 && lenIp != 0 && lenPort != 0) {
                            NickName = txtName.getText();
                            String address = txtAddress.getText();
                            int port = Integer.parseInt(txtPort.getText());
                            
                            login(NickName, address, port);
                        }
                            
                    }
                }while (false);
            }
        });
        btnLogin.setBounds(88, 300, 117, 29);
        btnLogin.setBackground(Color.BLACK);
        btnLogin.setForeground(Color.BLACK);
        contentPane.add(btnLogin);
    }
    
     private void login(String name, String address, int port) {
        dispose();
        Name = name;
        PORT = port;
        IP_ADDR = address;

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {new Client();}
        });

        //new ClientWindow();
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
