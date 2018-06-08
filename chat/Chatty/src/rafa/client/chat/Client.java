package rafa.client.chat;

import static rafa.client.chat.WindowSelectionField.click;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;
import rafa.client.chat.LoginEror;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import rafa.network.TCPConnection;
import rafa.network.TCPConnectionListner;

public class Client extends JFrame implements ActionListener,TCPConnectionListner{


	private static final long serialVersionUID = 1L;
	
    public static String NAME;
    public static String IP;
    public static int PORT;
    private final static int WIDTH = 540;
    private final static int HEIGHT = 716;
	private JTextField input = new JTextField();
	private JButton btnSendFile;
	private JTextArea log = new JTextArea();
	private JPanel PanelMain = new JPanel();
	private JScrollPane scroll;
	private JButton btnSendString;
	public static JButton NickTop;
	private JLabel UsOnly;
	private JLabel typeMessenge;
	private JFileChooser f = new JFileChooser();
    private TCPConnection connection;
    
	public Client() {	

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		JFrame win = new JFrame("Chat");
		scroll = new JScrollPane(log);
		btnSendString = new JButton();
		btnSendFile = new JButton();
		//Font font = new Font("Arial", Font.BOLD, 18);
		typeMessenge = new JLabel("   Общий Чат");
		UsOnly = new JLabel("");
		NickTop = new JButton(" \t " + NAME);
		
		
		win.setResizable(false);
		win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		win.setSize(WIDTH, HEIGHT);
		win.setLocationRelativeTo(null);
		win.setBackground(new Color(0x232222));
		
		log.setBackground(new Color(0x232222));
		log.setForeground(Color.WHITE);
		log.setFont(new Font("Arial", Font.BOLD, 18));
		log.setEnabled(false);
        log.setLineWrap(true);
		scroll.setBounds(0,28,535,625);

        typeMessenge.setFont(new Font("Sans-serif",Font.BOLD,18));
        typeMessenge.setForeground(Color.WHITE);
        typeMessenge.setBounds(0,0,165,29);
        typeMessenge.setBorder(new LineBorder(Color.WHITE,1));
        PanelMain.add(typeMessenge);
        
        UsOnly.setFont(new Font("Sans-serif",Font.PLAIN,17));
        UsOnly.setForeground(Color.WHITE);
        UsOnly.setBounds(203, 2, 153, 25);
        PanelMain.add(UsOnly);
        
        NickTop.setFont(new Font("Sans-serif",Font.BOLD,17));
        NickTop.setForeground(Color.WHITE);
        NickTop.setBounds(385, 0, 150, 29);
        NickTop.setBackground(new Color(0x232222));		
        NickTop.setContentAreaFilled(false);
		NickTop.setOpaque(true);
        NickTop.setBorder(new LineBorder (Color.WHITE,1));
        PanelMain.add(NickTop);
        
        input.setFont(new Font("Arial", Font.BOLD, 18));
        input.setBackground(new Color(0x232222));
		input.setForeground(Color.WHITE);
        input.addActionListener(this);
        input.setBounds(47, 655, 455, 30);
        input.setCaretColor(getBackground());

        PanelMain.setBorder(new EmptyBorder(5,5,5,5));
        PanelMain.setLayout(null);
        PanelMain.setBackground(new Color(0x232222));
        PanelMain.setForeground(Color.WHITE);
        win.setContentPane(PanelMain);
        
        ImageIcon imgSendString = createIcon("img/imgSendString.png");
		btnSendString.setIcon(imgSendString);

        btnSendString.setBackground(new Color(0x232222));
        btnSendString.setBounds(502, 654,32, 32);
        
        ImageIcon imgSendFile = createIcon("img/imgSendFile.png");
        btnSendFile.setIcon(imgSendFile);
        btnSendFile.setBounds(1,655,44,30);

        
        Image logo = Toolkit.getDefaultToolkit().getImage("img/logo.png");
        win.setIconImage(logo);
        
		PanelMain.add(scroll);
		PanelMain.add(input);
		PanelMain.add(btnSendString);
		PanelMain.add(btnSendFile);
		
		
		NickTop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MyAccount(NAME);
			}
			
		});	
		
		btnSendFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				send_File();
			}
			
		});
		
		
		btnSendString.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				printMessenge();
			}
		});
		
		try {
			connection = new TCPConnection(this,IP,PORT);
	    	} catch (IOException e) {
	    		printMsq("Ошибка соединения: " + e);
	    	}
		
		win.setVisible(true);
		
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
   
	 @Override
	    public void actionPerformed(ActionEvent e) {
		 	printMessenge();
	    }

	 	
	    @Override
	    public void onConnectionReady(TCPConnection topConnection) {
	    	printMsq("Добро пожаловать в чат...");
	    	connection.sendString("/n/" + NAME);
	    }

	    @Override
	    public void onReceiveString(TCPConnection topConnection, String value) {
	    	protect(value);
	    }

	   @Override
	    public void onDisconnect(TCPConnection topConnection) {
	    	printMsq("Соединение прервано.");
	    }
	    

	    @Override
	    public void onException(TCPConnection topConnection, Exception e) {
	        printMsq("Ошибка соединения: " + e);
	    }

	    public void saveFile(String output) {
	        output.substring(3,output.length());
			f.showOpenDialog(null);
			File file = f.getSelectedFile();	
			try {
				FileOutputStream writer = new FileOutputStream(file);
				writer.write(output.getBytes());
				writer.close();
			}catch(IOException e1){
				e1.printStackTrace();
			}
	    }
	    
    	private void protect(String mess) { 
    		 if(mess.startsWith("/f/")) {
    			new WindowSelectionField("\t\t Принять файл?");
    			while(click == true || click == false) {
	    			if(click) {
	    				mess.substring(3,mess.length());
	    				saveFile(mess);
	    			}else {
	    				return;
	    			}
    			}    			
    		}if(mess.startsWith("/us/")) {
    			mess = mess.substring(4, mess.length());
    			UsOnly.setText(mess + " человек онлайн");
    			UsOnly.updateUI();
    		}else printMsq(mess);
    	}

		private synchronized void printMsq(String msq){
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                log.append(msq + "\n");
	                log.setCaretPosition(log.getDocument().getLength());	        
	            }
	        });
	    }
		
		 	private void printMessenge() {
		 		String msq = input.getText();
		        if(msq.equals("") || msq.equals(" ") || msq.equals("  ") || msq.equals("\t")) return;
		        if(msq == "/send_File") {
		        	send_File();
		        }
		        input.setText(null);					
		        if(msq.startsWith("/printUs/")) connection.sendString(msq);
		        connection.sendString("  " + NAME + ": " + msq);
				/* TODO if(NickTop.getText().equals(Name)) {//хз работает или нет //нет!Идея была простая:сообщения отправленные тобой в правой части приложения.Реализация ужас!
		        	connection.sendString("\t\t\t" + msq);
		        }else {
		        connection.sendString( "  " + Name + ": " + msq);
		        }*/
		 	}
		 	
		    private void send_File() {
		    		try {
					 	//sendFile = new SendFile(this,IP_ADDR,PORT);	
					 	f.showOpenDialog(null);
					 	File file = f.getSelectedFile();	
					 	String input = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
					 	if(input.equals("")) {	        	
					 		new LoginEror("Файл Пуст");
					 		return;
					 	}else {
					 		connection.sendFile("/f/" + input);
					 	}
					 	
	    		 } catch (IOException e1) {
					e1.printStackTrace();
				}
	        }
}
