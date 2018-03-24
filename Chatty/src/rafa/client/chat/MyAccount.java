package rafa.client.chat;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import rafa.client.chat.Client;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;


public class MyAccount extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JButton ok;
	public static String NameUsers;
	private JLabel MyName;
	private JLabel MainImg;
	private JLabel YearUsers;
	private JLabel StateUsers;
	private JPanel Panel;
	private ImageIcon AvatarIcon;
	
	private String years = "?";
	private String state = "?";
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MyAccount();
			}
		});
	}
	
	MyAccount(){
		
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		
		JFrame MyAccount = new JFrame("Account");
		ok = new JButton("Ок");
		MyName = new JLabel(NameUsers);
		MainImg = new JLabel();
		YearUsers = new JLabel("Возраст: " + years);
		StateUsers = new JLabel("Страна: " + state);
		Panel = new JPanel();
		Font font = new Font("Arial",Font.PLAIN,15);
		
		MyAccount.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);;
		MyAccount.setSize(480, 350);
		MyAccount.setAlwaysOnTop(true);
		MyAccount.setLocationRelativeTo(null);
		MyAccount.setResizable(false);
		MyAccount.setBackground(new Color(0xbbb));
		MyAccount.setContentPane(Panel);
		
		YearUsers.setForeground(Color.WHITE);
		YearUsers.setFont(font);
		YearUsers.setBounds(195,220,100,20);
		
		StateUsers.setFont(font);
		StateUsers.setForeground(Color.WHITE);
		StateUsers.setBounds(195,190,200,20);
		
		Panel.setBackground(new Color(0x232222));
		Panel.setLayout(null);
		Panel.setBorder(new EmptyBorder(5,5,5,5)); 
		
		AvatarIcon = createIcon("img/avatar.png");
		MainImg.setIcon(AvatarIcon);
		MainImg.setBounds(180,10,125,125);
		MainImg.setForeground(Color.WHITE);
		MainImg.setBackground(Color.BLACK);
		
		MyName.setFont(new Font("Arial",Font.BOLD,20));
		MyName.setForeground(Color.WHITE);
		MyName.setBounds(140,145,200,30);
		MyName.setBackground(Color.WHITE);
		
		ok.setBounds(360,290,50,20);
		ok.setBackground(new Color(0x6e6e6e));
		ok.setForeground(Color.WHITE);
		ok.setFont(new Font("Arial",Font.PLAIN,13));
		ok.setContentAreaFilled(false);
		ok.setOpaque(true);
		
		Panel.add(ok);
		Panel.add(MyName);
		Panel.add(MainImg);
		Panel.add(YearUsers);
		Panel.add(StateUsers);
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MyAccount.dispose();
			}
			
		});
		
	/* TODO	MainImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AvatarIcon = createIcon("img/imgSendFile.png");
			}
			
		});*/
		
	MyAccount.setVisible(true);
	}

	
	 static ImageIcon createIcon(String path) {
       URL imgURL = Client.class.getResource(path);     
       if (imgURL != null) {
           return new ImageIcon(imgURL);
       } else {
           System.err.println("File not found " + path);
           return null;
       }
   }
	 
}
