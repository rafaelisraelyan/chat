package rafa.client.chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;
import rafa.client.chat.LoginEror;
import static rafa.client.chat.Login.eror;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import rafa.network.SendFile;
import rafa.network.TCPConnection;
import rafa.network.TCPConnectionListner;

public class Client extends JFrame implements ActionListener,TCPConnectionListner{
	/**
	 30.01.18 Сделал всё чтоб JScrollPane работал и он работает!!!Но не стоит сильно радоваться,хотя..стоит.Ещё многоработы и т.д и т.п,но я готов
	 1.02.18 перелопатил всё!Работает!!!!Окно ошибки в шоке.Сделаю завтра!
	 */
	private static final long serialVersionUID = 1L;
	
	static String IP_ADDR;
    static int PORT;
    public static String Name;
    private final static int WIDTH = 540;
    private final static int HEIGHT = 716;
	private JTextField input = new JTextField();
	private JButton btnSendFile;
	private JTextArea log = new JTextArea();
	private JPanel PanelMain = new JPanel();
	private JScrollPane scroll;
	private JButton btnSendString;
	public static JLabel NickTop;
	private JLabel UsOnly;
	private JLabel typeMessenge;
	private JFileChooser f = new JFileChooser();
	
    private TCPConnection connection;
    // TODO private SendFile sendFile;

    public String getName() {
    	return Name;
    }
    
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

		

		typeMessenge = new JLabel("Общий Чат");
		UsOnly = new JLabel("");
		NickTop = new JLabel(" \t " + Name);
		Font font = new Font("Arial", Font.BOLD, 18);
		
		win.setResizable(false);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setSize(WIDTH, HEIGHT);
		win.setLocationRelativeTo(null);
		win.setBackground(Color.BLACK);
		
		log.setBackground(Color.BLACK);
		log.setForeground(Color.WHITE);
		log.setFont(font);
		log.setEnabled(false);
        log.setLineWrap(true);
		scroll.setBounds(0,28,535,625);

        typeMessenge.setFont(new Font("Sans-serif",Font.BOLD,19));
        typeMessenge.setForeground(Color.WHITE);
        typeMessenge.setBounds(5,0,165,25);
        PanelMain.add(typeMessenge);
        
        UsOnly.setFont(new Font("Sans-serif",Font.PLAIN,16));
        UsOnly.setForeground(Color.WHITE);
        UsOnly.setBounds(205, 2, 100, 25);
        PanelMain.add(UsOnly);
        
        NickTop.setFont(new Font("Sans-serif",Font.BOLD,17));
        NickTop.setForeground(Color.WHITE);
        NickTop.setBounds(385, 0, 150, 30);
        NickTop.setBorder(new LineBorder (Color.WHITE,1));
        PanelMain.add(NickTop);
        
        input.setFont(font);
        input.setBackground(Color.BLACK);
		input.setForeground(Color.WHITE);
        input.addActionListener(this);
        input.setBounds(47, 655, 455, 30);
        input.setCaretColor(getBackground());

        PanelMain.setBorder(new EmptyBorder(5,5,5,5));
        PanelMain.setLayout(null);
        PanelMain.setBackground(Color.BLACK);
        PanelMain.setForeground(Color.WHITE);
        win.setContentPane(PanelMain);
        
        ImageIcon imgSendString = createIcon("img/imgSendString.png");
		btnSendString.setIcon(imgSendString);
        btnSendString.setBackground(Color.BLACK);
        btnSendString.setBounds(502, 654,32, 32);
        
        ImageIcon imgSendFile = createIcon("img/imgSendFile.png");
        btnSendFile.setIcon(imgSendFile);
        btnSendFile.setBounds(1,655,44,30);

        
        Image logo = Toolkit.getDefaultToolkit().getImage("img/logo16x16.png");
        win.setIconImage(logo);
        
		PanelMain.add(scroll);
		PanelMain.add(input);
		PanelMain.add(btnSendString);
		PanelMain.add(btnSendFile);
				
			
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
	            connection = new TCPConnection(this,IP_ADDR,PORT);
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
	    public void onConnectionReady(TCPConnection topConnectoin) {
	    	printMsq("Добро пожаловать в чат...");
	    	connection.sendString(Name + " зашёл в чат \n");
	        
	    }

	    @Override
	    public void onReceiveString(TCPConnection topConnectoin, String value) {
	        printMsq(value);
	    }

	   @Override
	    public void onDisconnect(TCPConnection topConnectoin) {
		   
	    	printMsq("Соединение прервано.");
	    	}
	    

	    @Override
	    public void onException(TCPConnection topConnectoin, Exception e) {
	        printMsq("Ошибка соединения: " + e);
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

		@Override
		public void onConnectionReady(SendFile topConnectoin, String Name) {
			System.out.println("Функция отправки файла с названием работает!");

		}

		@Override
		public void onConnectionReady(SendFile topConnectoin) {
			System.out.println("Функция отправки файла без названием работает!");
			
		}

		@Override
		public String onReceiveString(SendFile topConnectoin, String value) {
			printMsq(value);
			return value;
		}

		@Override
		public void onDisconnect(SendFile topConnectoin) {
			printMsq("Соединение прервано.");
			
		}

		@Override
		public void onException(SendFile topConnectoin, Exception e) {
			printMsq("Ошибка соединения: " + e);
			
		}
		
		 	private void printMessenge() {
		 		String msq = input.getText();
		        if(msq.equals("") || msq.equals(" ") || msq.equals("  ") || msq.equals("\t")) return;
		        if(msq == "/send_File") {
		        	send_File();
		        }
		        input.setText(null);					
		        
		        connection.sendString( "  " + Name + ": " + msq);
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
					 		eror = "Файл Пуст";
					 		new LoginEror();
					 		return;
					 	}else {
					 		connection.sendString( "  " + Name + ": " + input + "\n(Текст с файла)");
					 		// TODO sendFile.sendFile(input);
					 	}
					 	
	    		 } catch (IOException e1) {
					e1.printStackTrace();
				}
	        }
		

}
