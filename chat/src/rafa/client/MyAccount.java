package rafa.client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.geom.RoundRectangle2D;
import rafa.code.IconId;
import rafa.code.RoundedBorder;


public class MyAccount extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new MyAccount("Рафа"));
    }
	
    MyAccount(String name){
		
		/*try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}*/
        char ch = name.charAt(0);
        String chName = Character.toString(ch);
		
		JFrame MyAccount = new JFrame("Account");
		JButton ok = new JButton("ОК");
		JLabel myName = new JLabel(name);
		JLabel mainImg = new JLabel();
		String years = "?";
		JLabel yearUsers = new JLabel("Возраст " + years);
		String state = "?";
		JLabel stateUsers = new JLabel("Страна: " + state);
		JPanel panel = new JPanel();
		Font font = new Font("Arial",Font.PLAIN,15);

		MyAccount.setUndecorated(true);
		MyAccount.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		//MyAccount.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		MyAccount.setSize(480, 350);
		MyAccount.setAlwaysOnTop(true);
		MyAccount.setLocationRelativeTo(null);
		MyAccount.setResizable(false);
		//MyAccount.getRootPane().setBorder(null);
		MyAccount.setShape(new RoundRectangle2D.Double(0, 0, MyAccount.getWidth(), MyAccount.getHeight(), 50, 50));
		MyAccount.setBackground(Colors.mainColor);
		//MyAccount.setContentPane(new ShadowPane());
		
		yearUsers.setForeground(Colors.mainTxtColor);
		yearUsers.setFont(font);
		yearUsers.setBounds(195,220,100,20);
		
		stateUsers.setFont(font);
		stateUsers.setForeground(Colors.mainTxtColor);
		stateUsers.setBounds(195,190,200,20);
		
		panel.setBackground(Colors.mainColor);
		panel.setOpaque(true);
		panel.setLayout(null);
		panel.setBorder(new RoundedBorder(5,1,1,1,1,"MyAccount"));

		Icon avatarIcon = new IconId(110, chName);
		mainImg.setIcon(avatarIcon);
		mainImg.setBounds(180,10,125,125);
		/*mainImg.setForeground(Color.WHITE);
		mainImg.setBackground(Color.BLACK);*/
		
		myName.setFont(new Font("Arial",Font.BOLD,20));
		myName.setForeground(Colors.mainTxtColor);
		myName.setHorizontalAlignment(JLabel.CENTER);
		myName.setBounds(90,145,300,30);

		ok.setBounds(365,290,65,30);
		ok.setBackground(Colors.sideTxtColor);
		ok.setForeground(Colors.sideBorderColor);
		ok.setFont(new Font("Arial",Font.PLAIN,13));
		ok.setContentAreaFilled(false);
        ok.setFocusPainted(false);
        ok.setOpaque(true);
		
		panel.add(ok);
		panel.add(myName);
		panel.add(mainImg);
		panel.add(yearUsers);
		panel.add(stateUsers);
		MyAccount.add(panel);
		panel.repaint();
		MyAccount.repaint();
		
		ok.addActionListener((ActionEvent e) -> MyAccount.dispose());
		
		MyAccount.setVisible(true);
    } 
}
