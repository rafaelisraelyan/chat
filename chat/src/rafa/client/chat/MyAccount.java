package rafa.client.chat;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import rafa.client.chat.Client;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import static rafa.client.chat.Client.getImageIcon;


public class MyAccount extends JFrame{
	
	private static final long serialVersionUID = 1L;

	MyAccount(String name){
		
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		
		JFrame MyAccount = new JFrame("Account");
		JButton ok = new JButton("Ок");
		JLabel myName = new JLabel(name);
		JLabel mainImg = new JLabel();
		String years = "?";
		JLabel yearUsers = new JLabel("Возраст: " + years);
		String state = "?";
		JLabel stateUsers = new JLabel("Страна: " + state);
		JPanel panel = new JPanel();
		Font font = new Font("Arial",Font.PLAIN,15);
		
		MyAccount.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		MyAccount.setSize(480, 350);
		MyAccount.setAlwaysOnTop(true);
		MyAccount.setLocationRelativeTo(null);
		MyAccount.setResizable(false);
		MyAccount.setBackground(new Color(0xbbb));
		MyAccount.setContentPane(panel);
		
		yearUsers.setForeground(Color.WHITE);
		yearUsers.setFont(font);
		yearUsers.setBounds(195,220,100,20);
		
		stateUsers.setFont(font);
		stateUsers.setForeground(Color.WHITE);
		stateUsers.setBounds(195,190,200,20);
		
		panel.setBackground(new Color(0x232222));
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5,5,5,5));

		ImageIcon avatarIcon = createIcon("img/avatar.png");
		mainImg.setIcon(avatarIcon);
		mainImg.setBounds(180,10,125,125);
		mainImg.setForeground(Color.WHITE);
		mainImg.setBackground(Color.BLACK);
		
		myName.setFont(new Font("Arial",Font.BOLD,20));
		myName.setForeground(Color.WHITE);
		myName.setBounds(140,145,200,30);
		myName.setBackground(Color.WHITE);
		
		ok.setBounds(360,290,50,20);
		ok.setBackground(new Color(0x6e6e6e));
		ok.setForeground(Color.WHITE);
		ok.setFont(new Font("Arial",Font.PLAIN,13));
		ok.setContentAreaFilled(false);
		ok.setOpaque(true);
		
		panel.add(ok);
		panel.add(myName);
		panel.add(mainImg);
		panel.add(yearUsers);
		panel.add(stateUsers);
		
		ok.addActionListener(e -> MyAccount.dispose());
		
	/* TODO	MainImg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AvatarIcon = createIcon("img/imgSendFile.png");
			}
			
		});*/
		
	MyAccount.setVisible(true);
	}

	
	 private static ImageIcon createIcon(String path) {
		 return getImageIcon(path);
	 }
	 
}
