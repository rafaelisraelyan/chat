package rafa.client.chat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static rafa.client.chat.Login.eror;

public class LoginEror extends JFrame{

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 320;
    private static final int HEIGHT = 100;
    JPanel erorPanel = new JPanel();
    JLabel textEror = new JLabel("Login exeption!");
    JButton btn = new JButton("OK");


    public LoginEror(){
        	
    	JFrame frame = new JFrame("EROR!");

        Font font = new Font("ArialBlack", Font.BOLD,17);
        
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(erorPanel);
        
        erorPanel.setBorder(new EmptyBorder(1,1,1,1));
        erorPanel.setBackground(Color.BLACK);
        erorPanel.setForeground(Color.WHITE);
        erorPanel.setLayout(null);
        
          textEror.setText(eror);
          textEror.setFont(font);
          textEror.setBounds(10, 8, 300, 30);
          textEror.setForeground(Color.WHITE);
          erorPanel.add(textEror);

        btn.setBounds(115,45,70,25);
        btn.setForeground(Color.BLACK);
        btn.setBackground(Color.WHITE);
        erorPanel.add(btn);

        btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
        	
        });
        
frame.setVisible(true);

    }
}