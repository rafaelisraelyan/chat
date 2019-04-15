package rafa.client.chat;

import rafa.network.TCPConnection;
import rafa.network.TCPConnectionListner;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class Client extends JFrame implements ActionListener,TCPConnectionListner{

	private static final long serialVersionUID = 1L;
	
    private static String NAME;
    private static String IP;
    private static int PORT;
	private final static int WIDTH = 545;
    private final static int HEIGHT = 722;
	private JTextField input = new JTextField();
	private JTextArea log = new JTextArea();
	private JLabel UsOnly;
	//private JFileChooser f = new JFileChooser();
    private TCPConnection connection;

	Client(String name,String addr,int port) {
		NAME = name;
		IP = addr;
		PORT = port;
		paintUI();
	}

	private void paintUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		JScrollPane scroll = new JScrollPane(log);
		JButton btnSendString = new JButton();
		JButton btnSendFile = new JButton();
		JLabel typeMessenge = new JLabel("   Общий Чат");
		UsOnly = new JLabel("");
		JButton nickTop = new JButton(" \t " + NAME);

		JFrame win = new JFrame("Chat");
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
		JPanel panelMain = new JPanel();
		panelMain.add(typeMessenge);

		UsOnly.setFont(new Font("Sans-serif",Font.PLAIN,17));
		UsOnly.setForeground(Color.WHITE);
		UsOnly.setBounds(203, 2, 153, 25);
		panelMain.add(UsOnly);

		nickTop.setFont(new Font("Sans-serif",Font.BOLD,17));
		nickTop.setForeground(Color.WHITE);
		nickTop.setBounds(385, 0, 150, 29);
		nickTop.setBackground(new Color(0x232222));
		nickTop.setContentAreaFilled(false);
		nickTop.setOpaque(true);
		nickTop.setBorder(new LineBorder (Color.WHITE,1));
		panelMain.add(nickTop);

		input.setFont(new Font("Arial", Font.BOLD, 18));
		input.setBackground(new Color(0x232222));
		input.setForeground(Color.WHITE);
		input.addActionListener(this);
		input.setBounds(47, 655, 455, 30);
		input.setCaretColor(getBackground());

		panelMain.setBorder(new EmptyBorder(5,5,5,5));
		panelMain.setLayout(null);
		panelMain.setBackground(new Color(0x232222));
		panelMain.setForeground(Color.WHITE);
		win.setContentPane(panelMain);

		ImageIcon imgSendString = createIcon("img/imgSendString.png");
		btnSendString.setIcon(imgSendString);

		btnSendString.setBackground(new Color(0x232222));
		btnSendString.setBounds(502, 654,32, 32);
		btnSendString.setBackground(new Color(0x232222));
		btnSendString.setFocusPainted(false);
		btnSendString.setOpaque(true);
		btnSendString.setContentAreaFilled(false);

		ImageIcon imgSendFile = createIcon("img/imgSendFile.png");
		btnSendFile.setIcon(imgSendFile);
		btnSendFile.setBounds(1,655,44,30);
		btnSendFile.setBackground(new Color(0x232222));
		btnSendFile.setFocusPainted(false);
		btnSendFile.setOpaque(true);
		btnSendFile.setContentAreaFilled(false);

		Image logo = Toolkit.getDefaultToolkit().getImage("img/logo.png");
		win.setIconImage(logo);

		panelMain.add(scroll);
		panelMain.add(input);
		panelMain.add(btnSendString);
		panelMain.add(btnSendFile);

		nickTop.addActionListener(e -> new MyAccount(NAME));
		btnSendFile.addActionListener(e -> new LoginEror("Упс,фунция не работает"));
		btnSendString.addActionListener(e -> sendMessenge());

		try {
			connection = new TCPConnection(this,IP,PORT);
		} catch (IOException e) {
			printMsq("Ошибка соединения: " + e);
		}

		win.setVisible(true);
	}

	private static ImageIcon createIcon(String path) {
		return getImageIcon(path);
	}

	static ImageIcon getImageIcon(String path) {
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
		 	sendMessenge();
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

	/*TODO private void saveFile(String output) {
		output = output.substring(3);
		f.showOpenDialog(null);
		File file = f.getSelectedFile();
		try {
			FileOutputStream writer = new FileOutputStream(file);
			writer.write(output.getBytes());
			writer.close();
		}catch(IOException e1){
			e1.printStackTrace();
		}
	}*/

	private void protect(String mess) {
		/*TODO if(mess.startsWith("/f/")) {
			new WindowSelectionField("\t\t Принять файл?");
			while(click || !click) {
				if(click) {
					mess = mess.substring(3);
					saveFile(mess);
				}else {
					return;
				}
			}
		}*/if(mess.startsWith("/us/")) {
			mess = mess.substring(4);
			UsOnly.setText(mess + " человек онлайн");
			UsOnly.updateUI();
		}if(mess.startsWith("/pm")) {
			String newMess = mess.substring(3);
			System.out.println(newMess);
			if (newMess.startsWith("/")) {
				newMess = newMess.substring(1);
				newMess = newMess.substring(cut(newMess).length() + 1);
				String name = cut(newMess);
				newMess = newMess.substring(name.length()+1);
				newMess = name + newMess;
			}else {
				String name = cut(newMess);
				newMess = newMess.substring(name.length()+1);
				newMess = name + newMess;
			}
			printMsq(newMess);
		}
		else System.out.println("Что-то не то protect()");
	}

	private static String cut(String txt){
		StringBuilder id_l = new StringBuilder();
		for(int i = 0;i<txt.length();i++){
			char ch = txt.charAt(i);
			String chToStr = Character.toString(ch);
			if("/".equals(chToStr)) break;
			else id_l.append(chToStr);
		}
		return id_l.toString();
	}

	private synchronized void printMsq(final String msq){
		SwingUtilities.invokeLater(() -> {
			log.append(msq + "\n");
			log.setCaretPosition(log.getDocument().getLength());
		});
	}

	private void sendMessenge() {
		String msq = input.getText();
		if(msq.replaceAll("\\s+","").equals("")) return;
		input.setText(null);
		connection.sendString("/pm" + " " + NAME + ":/" + msq);
	}

	/*TODO private void send_File() {
		try {
			f.showOpenDialog(null);
			File file = f.getSelectedFile();
			String input = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
			if(input.equals("")) new LoginEror("Файл Пуст");
			else connection.sendFile("/f/" + input);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}*/
}
