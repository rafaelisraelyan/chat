package rafa.client.chat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowSelectionField extends JFrame{

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 320;
    private static final int HEIGHT = 110;
    JPanel Panel = new JPanel();
    JLabel textSelection = new JLabel("Login exeption!");
    JButton btn = new JButton("Да");
    JButton btn2 = new JButton("Нет");
    public static boolean click;
    
    public static void main(String args[]) {
    	EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new WindowSelectionField(" \t\t\t Принять файл?");
			}
    		
    	});
    }
    
    public WindowSelectionField(String eror){
        	
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    	JFrame frame = new JFrame("Уведомление:");

        Font font = new Font("ArialBlack", Font.BOLD,17);
        
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(Panel);
        
        Panel.setBorder(new EmptyBorder(1,1,1,1));
        Panel.setBackground(new Color(0x232222));
        Panel.setForeground(Color.WHITE);
        Panel.setLayout(null);
        
          textSelection.setText(eror);
          textSelection.setFont(font);
          textSelection.setBounds(10, 8, 300, 30);
          textSelection.setForeground(Color.WHITE);
          textSelection.setText(eror);
          Panel.add(textSelection);

        btn.setBounds(170,45,70,25);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setFont(new Font("Arial",Font.BOLD,15));
        btn.setBackground(new Color(0x6e6e6e));
        btn.setForeground(Color.WHITE);
        Panel.add(btn);
       

        btn2.setBounds(40,45,70,25);
        btn2.setContentAreaFilled(false);
        btn2.setOpaque(true);
        btn2.setFont(new Font("Arial",Font.BOLD,15));
        btn2.setBackground(new Color(0x6e6e6e));
        btn2.setForeground(Color.WHITE);
        Panel.add(btn2);
        
        btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				click = true;
				frame.dispose();
			}
        	
        });
        
        btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				click = false;
				frame.dispose();
			}
        	
        });
        
frame.setVisible(true);

    }
}
