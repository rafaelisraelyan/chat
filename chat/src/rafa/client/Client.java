package rafa.client;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import rafa.code.*;
import rafa.network.TCPConnection;
import rafa.network.TCPConnectionListner;
import static rafa.code.createIcon.createIcon;
//import static rafa.code.createIcon.createIcon;

public class Client extends JFrame implements ActionListener,TCPConnectionListner{

    private static final long serialVersionUID = 1L;
    private static final List<JPanel> PANELS = new ArrayList<>();
    //private static boolean[] tabsOpen = new boolean[100];
    //private static boolean[] tabsPick = new boolean[100];

    private static String name = "Рафаэль Исраелян";
    private static final String IP = "localhost";
    private static final int PORT = 8180;
   // private static final Icon avatar = new IconId();
    private int intConnecting = 0;
    private String txtLabelEror;
    private boolean presedIconStretch = false;
    private Point locationFrame;
    private Dimension sizeFrame = new Dimension();
    private static String id = "";
    private final static int WIDTH_WINDOW = 790;
    private final static int HEIGHT_WINDOW = 700;
    /**/
    private JTabbedPane tab = new JTabbedPane();
    private JPanel panelLog;
    private JTextField input;
    private JScrollPane scroll;
    private JButton NickTop;
    private JLabel UsOnly;
    /*---/tab1---
    ---- tab2----*/
    private JPanel panelMainUsOnly;
    private JPanel panelUsOnly;
    private JScrollPane scrollUsOnly;
    //private final JFileChooser F = new JFileChooser();
    private JFrame win = new JFrame("Chat");
    /**/
    private TCPConnection connection;
    private int x;
    private int y;
    private JPanel ps = new JPanel();
    private JPanel panelTopMain = new JPanel();

    public static void main(String[] args){
            EventQueue.invokeLater(() -> new Client().UI(name));
    }

    /*static void setTabsOpens(int index){
        tabsOpen[index] = false;
        tabsPick[index] = false;
    }*/

    Client() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        /*---tab1---*/
        ps.setBackground(Colors.tabColor);
        JPanel panelMainCH = new JPanel();
        JPanel panelBottom = new JPanel();
        JPanel panelCenter = new JPanel();
        panelLog = new JPanel();
        scroll = new JScrollPane(panelLog);
        JPanel panelSendStr = new JPanel();
        JPanel panelBtnSend = new JPanel();
        JButton btnSendAudio = new JButton();
        JButton btnSendEmoji = new JButton();
        JButton btnSendString = new JButton();
        JButton btnSendFile = new JButton();

        UsOnly = new JLabel("");
        NickTop = new JButton("<html>" + name + "</html>");
        input = new JTextField();
        txtLabelEror = "Соединение";
        Font font = new Font("Arial", Font.BOLD, 18);
        /*------*/
        JLabel textUsOnly = new JLabel("Пользователи онлайн:");
        JLabel labelMess = new JLabel();
        panelMainUsOnly = new JPanel();
        panelUsOnly = new JPanel();
        scrollUsOnly = new JScrollPane(panelUsOnly);
        /*------*/
        JPanel MyAccount = new JPanel();
        JPanel MyAccCenter = new JPanel();
        JPanel MyAccBottom = new JPanel();
        JLabel avatar = new JLabel();
        JLabel MyNameAcc = new JLabel(name);
        JLabel namePanel = new JLabel("Мой аккаунт:");
        JButton btnSettings = new JButton("⚙ Настройки");
        JButton btnAbout = new JButton("О приложении");
        //JLabel whiteTheme = new JLabel("Светлая тема");
        //JLabel darkTheme = new JLabel("Тёмная тема");

        JLabel iconWindow = new JLabel();
        iconWindow.setPreferredSize(new Dimension(20,20));
        iconWindow.setIcon(createIcon("../img/logoMain.png"));

        JLabel titleWindow = new JLabel("Чат");
        titleWindow.setForeground(Colors.mainTxtColor);
        titleWindow.setFont(new Font("", Font.BOLD,15));
        titleWindow.setBorder(new EmptyBorder(3,2,3,2));

        JPanel panelTitleWindow = new JPanel();
        panelTitleWindow.setBackground(Colors.mainColor);

        JLabel iconExit = new JLabel("✕");
        iconExit.setForeground(Colors.mainTxtColor);
        iconExit.setFont(new Font("", Font.BOLD,19));
        iconExit.setBorder(new EmptyBorder(3,5,3,5));
        iconExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                win.dispatchEvent(new WindowEvent(win, WindowEvent.WINDOW_CLOSING));
            }
        });
        iconExit.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseMoved(MouseEvent evt){
                iconExit.setBackground(Color.RED);
                iconExit.setOpaque(true);
            }
        });
        iconExit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseExited(MouseEvent evt) {
                iconExit.setBackground(Colors.mainColor);
                iconExit.setOpaque(true);
            }
        });
        JLabel iconStretch = new JLabel();
        iconStretch.setText("⬜");
        iconStretch.setForeground(Colors.mainTxtColor);
        iconStretch.setFont(new Font("",Font.BOLD,12));
        iconStretch.setBorder(new EmptyBorder(7,5,7,5));
        iconStretch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!presedIconStretch){
                    presedIconStretch = true;
                    sizeFrame = new Dimension(win.getSize());
                    locationFrame = win.getLocation();
                    win.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    iconStretch.setText("❐");
                    System.out.println(win.getLocation());
                }else {
                    presedIconStretch = false;
                    iconStretch.setText("⬜");
                    win.setSize(sizeFrame);
                    win.setLocation(locationFrame.x,locationFrame.y);
                }

            }
        });
        iconStretch.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseMoved(MouseEvent evt){
                iconStretch.setBackground(Colors.mainBorderColor);
                iconStretch.setOpaque(true);
            }
        });
        iconStretch.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseExited(MouseEvent evt) {
                iconStretch.setBackground(Colors.mainColor);
                iconStretch.setOpaque(true);
            }
        });
        JLabel iconHideWin = new JLabel();
        iconHideWin.setText("─");
        iconHideWin.setForeground(Colors.mainTxtColor);
        iconHideWin.setBorder(new EmptyBorder(5,7,5,7));
        iconHideWin.setFont(new Font("",Font.BOLD,16));
        iconHideWin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                win.setExtendedState(JFrame.ICONIFIED);

            }
        });
        iconHideWin.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseMoved(MouseEvent evt){
                iconHideWin.setBackground(Colors.mainBorderColor);
                iconHideWin.setOpaque(true);
            }
        });
        iconHideWin.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseExited(MouseEvent evt) {
                iconHideWin.setBackground(Colors.mainColor);
                iconHideWin.setOpaque(true);
            }
        });
        JPanel panelIconWin = new JPanel();
        panelIconWin.setBackground(Colors.sideColor);
        panelIconWin.add(iconHideWin);
        panelIconWin.add(iconStretch);
        panelIconWin.add(iconExit);

        panelTitleWindow.add(iconWindow);
        panelTitleWindow.add(titleWindow);

        panelTopMain.setLayout(new BorderLayout());
        panelTopMain.setBackground(Colors.mainColor);
        panelTopMain.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(!e.isMetaDown()){
                    x = e.getX();
                    y = e.getY();
                }
            }
        });
        panelTopMain.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if(!e.isMetaDown()){
                    Point p = win.getLocation();//I Simply had to put button.getLocation() instead of getLocation()
                    win.setLocation(p.x + e.getX()-x,
                            p.y + e.getY()-y);
                }
            }
        });
        panelTopMain.add(panelTitleWindow, BorderLayout.WEST);
        panelTopMain.add(panelIconWin, BorderLayout.EAST);

        panelSendStr.setBackground(Colors.sideColor);
        panelSendStr.add(btnSendString);


        scroll.setBorder(new RoundedBorder(20,-20,-20,-20,-20,"0"));
        scroll.setForeground(Colors.sideTxtColor);
        scroll.setBackground(Colors.sideColor);

        UsOnly.setFont(new Font("Sans-serif",Font.PLAIN,17));
        UsOnly.setForeground(Colors.mainTxtColor);
        UsOnly.setBorder(new EmptyBorder(10,10,10,10));
        //UsOnly.setHorizontalAlignment(JLabel.CENTER);

        NickTop.setFont(new Font("Sans-serif",Font.BOLD,18));
        NickTop.setForeground(Colors.sideTxtColor);
        NickTop.setBackground(Colors.sideColor);
        NickTop.setContentAreaFilled(false);
        //NickTop.setOpaque(true);
        NickTop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        NickTop.setFocusPainted(false);
        NickTop.setBorder(new RoundedBorder(8,5,9,5,4,null));
        NickTop.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseMoved(MouseEvent evt){
                NickTop.setForeground(Colors.mainTxtColor);
                NickTop.repaint();
            }
        });
        NickTop.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseExited(MouseEvent evt) {
                NickTop.setForeground(Colors.sideTxtColor);
                NickTop.repaint();
            }
        });

        input.setFont(new Font("", Font.BOLD, 18));
        input.setBackground(Colors.sideColor);
        input.setForeground(Colors.mainTxtColor);
        input.addActionListener(this);
        input.setBorder(new RoundedBorder(18,-1,4,-1,0,null));
        input.setSize(new Dimension(input.getPreferredSize().width,input.getPreferredSize().height*2));
        input.setToolTipText("Введите текст сообщения...");
        input.setCaretColor(input.getForeground());
        /*TODO input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(input.getText().length() == 0){}
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(input.getText().length() == 0){}
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("3");
            }
        });*/

        btnSendAudio.setFont(new Font("", Font.BOLD,40));
        btnSendAudio.setText("✆");
        btnSendAudio.setToolTipText("Нажмите,чтобы позвонить");
        btnSendAudio.setContentAreaFilled(false);
        btnSendAudio.setOpaque(true);
        btnSendAudio.setFocusable(false);
        btnSendAudio.setBorder(new EmptyBorder(0,0,0,15));
        btnSendAudio.setBackground(Colors.sideColor);
        btnSendAudio.setForeground(Colors.mainTxtColor);
        btnSendAudio.addActionListener(e -> new LoginEror("Скоро всё будет!"));

        btnSendEmoji.setFont(new Font("", Font.BOLD,30));
        btnSendEmoji.setText("☺");
        btnSendEmoji.setToolTipText("Нажмите,чтоб отправить сообщение");
        btnSendEmoji.setContentAreaFilled(false);
        btnSendEmoji.setOpaque(true);
        btnSendEmoji.setFocusable(false);
        btnSendEmoji.setBorder(new RoundedBorder(5,-5,14,-5,10,null));
        btnSendEmoji.setBackground(Colors.sideColor);
        btnSendEmoji.setForeground(Colors.mainTxtColor);


        //ImageIcon imgSendString = createIcon("../../rafa/img/Send.png");
        //btnSendString.setIcon(imgSendString);
        btnSendString.setFont(new Font("", Font.BOLD,40));
        btnSendString.setText("➜");
        btnSendString.setToolTipText("Нажмите,чтоб отправить сообщение");
        btnSendString.setContentAreaFilled(false);
        btnSendString.setOpaque(true);
        btnSendString.setFocusable(false);
        btnSendString.setBorder(new RoundedBorder(5,-5,10,-5,10,null));
        btnSendString.setBackground(Colors.sideColor);
        btnSendString.setForeground(Colors.mainTxtColor);

        // ImageIcon imgSendFile = createIcon("../../rafa/img/SF.png");
        btnSendFile.setContentAreaFilled(false);
        btnSendFile.setOpaque(true);
        btnSendFile.setFocusPainted(false);
        btnSendFile.setBackground(Colors.sideColor);
        btnSendFile.setForeground(Colors.mainTxtColor);
        btnSendFile.setText("\uD83D\uDCCE");
        btnSendFile.setFont(new Font("", Font.BOLD, 30));
        //btnSendFile.setIcon(imgSendFile);
        btnSendFile.setBorder(new RoundedBorder(5,5,3,0,3,null));

        labelMess.setForeground(Colors.mainTxtColor);
        labelMess.setFont(new Font("Arial", Font.BOLD, 20));

        panelBtnSend.add(btnSendFile);
        panelBtnSend.add(btnSendString);

        panelLog.setBackground(Colors.sideColor);
        panelLog.setBorder(new RoundedBorder(18,-18,-18,-18,-18,null));
        panelLog.setLayout(new VerticalLayout());
        panelLog.setFont(font);

        JPanel panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout());
//        panelTop.setBorder(shadows(2));
        panelTop.setBackground(Colors.sideColor);
        /*-------Добавление-------*/
        panelTop.add(UsOnly,BorderLayout.CENTER);
        panelTop.add(btnSendAudio,BorderLayout.EAST);
        //panelTop.add(space,BorderLayout.WEST);
        // panelTop.add(NickTop,BorderLayout.EAST);

        panelBottom.setLayout(new BorderLayout());
        //panelBottom.setBackground(Colors.myMsqColor);
        panelBottom.setForeground(Colors.mainTxtColor);
        /*-------Добавление-------*/
        panelBottom.add(btnSendEmoji,BorderLayout.WEST);
        panelBottom.add(btnSendString,BorderLayout.EAST);
        panelBottom.add(input,BorderLayout.CENTER);


        panelCenter.setLayout(new BorderLayout());
        panelCenter.setBorder(null);
        panelCenter.setBackground(Colors.sideColor);
        /*-------Добавление-------*/
        panelCenter.add(scroll,BorderLayout.CENTER);

        panelMainCH.setLayout(new BorderLayout());
        panelMainCH.setPreferredSize(new Dimension(WIDTH_WINDOW, HEIGHT_WINDOW));
        panelMainCH.setBorder(null);
        panelMainCH.setBackground(Colors.sideColor);
        panelMainCH.setForeground(Colors.mainTxtColor);
        /*-------Добавление-------*/
        panelMainCH.add(panelCenter,BorderLayout.CENTER);
        panelMainCH.add(panelBottom,BorderLayout.SOUTH);
        panelMainCH.add(panelTop,BorderLayout.NORTH);

        /*------tab2-----*/
        scrollUsOnly.setBackground(Colors.sideColor);
        scrollUsOnly.setBorder(null);

        textUsOnly.setForeground(Colors.mainTxtColor);
        textUsOnly.setFont(new Font("Arial", Font.BOLD, 20));
        textUsOnly.setHorizontalAlignment(JLabel.CENTER);

        panelMainUsOnly.setBackground(Colors.sideColor);
        panelMainUsOnly.setLayout(new BorderLayout());

        panelUsOnly.setLayout(new VerticalLayout());
        panelUsOnly.setBackground(Colors.sideColor);
        panelUsOnly.setBorder(null);

        //panelMainUsOnly.add(space);
        panelMainUsOnly.add(textUsOnly,BorderLayout.NORTH);
        //panelMainUsOnly.add(space);
        panelMainUsOnly.add(scrollUsOnly, BorderLayout.CENTER);
        /*-------------/tab2----------*/

        /*------tab 0----*/
        MyAccount.setBackground(Colors.sideColor);
        MyAccount.setLayout(new BorderLayout());
        MyAccCenter.setBackground(Colors.sideColor);
        MyAccCenter.setLayout(new VerticalLayout());
        MyAccBottom.setBackground(Colors.sideColor);

        avatar.setIcon(new IconId(100,Character.toString(name.charAt(0))));
        avatar.setName("0");
        avatar.setBorder(new EmptyBorder(10,10,10,10));

        btnSettings.setBackground(Colors.mainBorderColor);
        btnSettings.setContentAreaFilled(false);
        btnSettings.setOpaque(true);
        btnSettings.addActionListener(e -> new Settings().UI());

        btnAbout.setBackground(Colors.sideBorderColor);
        btnAbout.setContentAreaFilled(false);
        btnAbout.setOpaque(true);

        MyNameAcc.setName("0");
        MyNameAcc.setForeground(Colors.mainTxtColor);
        MyNameAcc.setFont(new Font("",Font.BOLD,20));

        namePanel.setForeground(Colors.mainTxtColor);
        namePanel.setName("1");
        namePanel.setFont(new Font("Arial",Font.BOLD,25));
        namePanel.setBorder(new EmptyBorder(10,10,10,10));
        /*TODO whiteTheme.setForeground(Colors.mainTxtColor);
        whiteTheme.setBorder(new EmptyBorder(10,10,10,10));
        whiteTheme.setFont(new Font("Arial", Font.BOLD,18));
        whiteTheme.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt) {
                new Colors(new Color(0xD2D2D2), new Color(0xD4D3DA),
                        new Color(0x000000), new Color(0x444444), new Color(0xBEBEBE),
                        new Color(0xC1C1C1), new Color(0xD3C6D8), new Color(0xAC9E1C),
                        new Color(0x538697));
                //tab.updateUI();
                tab.removeAll();
                tab.revalidate();
                tab.validate();
                tab.setBackground(Colors.mainColor);
                tab.addTab(name,MyAccount);
                tab.addTab("Общий чат", panelMain);
                tab.addTab("Онлайн", panelMainUsOnly);
                tab.setTabComponentAt(0,new ClosableTabTitle(name,0));
               // win.getContentPane().add(tab);
                tab.repaint();
                tab.revalidate();
                win.dispose();
                new Client(name);
            }
        });

        darkTheme.setForeground(Colors.mainTxtColor);
        darkTheme.setFont(new Font("Arial", Font.BOLD,18));
        darkTheme.setBorder(new EmptyBorder(10,10,10,10));
        darkTheme.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt) {
                new Colors(new Color(0x232222), new Color(0x232225),
                        new Color(0xffffff), new Color(0xbbbbbb), new Color(0x3f3f3f),
                        new Color(0x333333), new Color(0x232227), new Color(0x574cc9),
                        new Color(0xE1E4E8));
                tab.removeAll();
                tab.revalidate();
                tab.validate();
                tab.setBackground(Colors.mainColor);
                tab.addTab(name,MyAccount);
                tab.addTab("Общий чат", panelMain);
                tab.addTab("Онлайн", panelMainUsOnly);
                tab.setTabComponentAt(0,new ClosableTabTitle(name,0));
                // win.getContentPane().add(tab);
                tab.repaint();
                tab.revalidate();
            }
        });*/
        MyAccount.add(MyAccCenter,BorderLayout.CENTER);
        MyAccount.add(MyAccBottom,BorderLayout.SOUTH);

        MyAccCenter.add(namePanel);
        MyAccCenter.add(avatar);
        MyAccCenter.add(MyNameAcc);
        MyAccBottom.add(btnSettings);
        MyAccBottom.add(btnAbout);
        /*----------/tab 0---------*/

        tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tab.setBackground(Colors.tabColor);
        //tab.setDoubleBuffered(true);
        tab.setOpaque(false);
        tab.setUI(new CustomTabbedPaneUI());
        tab.setBorder(null);
        tab.setForeground(Colors.mainTxtColor);
        tab.setName("tabs");
        tab.setTabPlacement(JTabbedPane.LEFT);
        tab.addTab(name,MyAccount);
        tab.addTab("Общий чат", panelMainCH);
        tab.addTab("Онлайн", panelMainUsOnly);
        //tab.setTabComponentAt(2, new ClosableTabTitle(name, 2));
        tab.setTabComponentAt(0,new ClosableTabTitle(name,0,tab));
        System.out.println(tab.getTitleAt(0));
        tab.setSelectedIndex(1);

        //win.setContentPane(tab);
        Image logo = new ImageIcon("../img/logo.png").getImage();
        win.setIconImage(logo);

        NickTop.addActionListener((ActionEvent e) -> new MyAccount(name));
        btnSendFile.addActionListener((ActionEvent e) -> new LoginEror("Скоро..."));
        btnSendString.addActionListener((ActionEvent e) -> sendMessenge());

        //ps.setPreferredSize(new Dimension(WIDTH_WINDOW,HEIGHT_WINDOW));
        tab.repaint();
        win.repaint();

    }


    void UI(String name){
        Client.name = name;
        try {
            connection = new TCPConnection(this,IP,PORT);
        } catch (IOException e) {
            printMsq("Ошибка соединения: " + e,0);
        }
        ps.setLayout(new BorderLayout());
        ps.setBorder(new EmptyBorder(4,4,4,4));
        ps.add(tab,BorderLayout.CENTER);
        ps.add(panelTopMain,BorderLayout.PAGE_START);
        ComponentResizer cr = new ComponentResizer();
        cr.setMinimumSize(new Dimension(400, 550));
        cr.setMaximumSize(getMaximumSize());
        cr.registerComponent(win);
        //cr.setSnapSize(new Dimension(10, 10));
        // win.setResizable(true);
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //win.setSize(WIDTH_WINDOW,HEIGHT_WINDOW);
        win.setUndecorated(true);
        win.getRootPane().setDoubleBuffered(true);
        win.add(ps);//970,800
        win.setSize(WIDTH_WINDOW,HEIGHT_WINDOW);
        win.pack();
        win.setLocationRelativeTo(null);
        win.setVisible(true);
        win.repaint();
        tab.repaint();
        win.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                if (win.getWidth() < 800 || win.getHeight() < 600){
                    tab.setTabPlacement(JTabbedPane.TOP);
                    tab.repaint();
                    reliable();
                }
                else {
                    tab.setTabPlacement(JTabbedPane.LEFT);
                    reliable();
                }
                tab.repaint();
            }
        });
    }

  /*  static DropShadowBorder shadows(int location) {
        DropShadowBorder shadow = new DropShadowBorder();
        shadow.setShadowColor(Color.BLACK);
        //shadow.setShadowOpacity(0.9f);
        switch (location) {
            case 1:
                shadow.setShowTopShadow(true);
                break;
            case 2:
                shadow.setShowRightShadow(true);
                break;
            case 3:
                shadow.setShowBottomShadow(true);
                break;
            case 4:
                shadow.setShowLeftShadow(true);
                break;
            default:
                shadow.setShowTopShadow(true);
                shadow.setShowRightShadow(true);
                shadow.setShowBottomShadow(true);
                shadow.setShowLeftShadow(true);
                break;
        }
        return shadow;
    }
*/
    private void reliable() {
        for(int i = 0;i<tab.getTabCount();i++){
            if(tab.getIconAt(i) != null){
                String title = tab.getTitleAt(i);
                tab.setTabComponentAt(i,new ClosableTabTitle(title,i,tab));
                //tab.getComponentAt(i).setFont(new Font("Sans-serif",Font.BOLD,25));
                //tab.setIconAt(i,new IconId(28,Character.toString(title.charAt(0))));
                //System.out.println(PANELS.get(i).getComponent(i).getFont());
            }
        }
    }

    @Override
        public void actionPerformed(ActionEvent e) {
            sendMessenge();
        }

    /*private void sendAudio() {
        // TODO
    }*/

    @Override
        public void onConnectionReady(TCPConnection topConnection) {
            printMsq("Добро пожаловать в чат...",0);
            connection.sendString("/n/" + name);
        }

    @Override
        public void onReceiveString(TCPConnection topConnection, String value) {
            protect(value);
        }

    @Override
        public void onDisconnect(TCPConnection topConnection) {
            printMsq("Соединение прервано.", 0);
            //connecting();
    }

    @Override
        public void onException(TCPConnection topConnection, Exception e) {
            printMsq("Ошибка соединения: " + e,0);
            connecting();
        }

    private void protect(String mess) {
    	if(mess.startsWith("/f/")) {
    	    System.out.println(mess);
            /*WindowSelectionField windowSelectionField = new WindowSelectionField("\t\t Принять файл?");
            while(click || !click) {
                if(click) {
                    mess = mess.substring(3);
                    saveFile(mess);
                }else return;
            }*/
    	}
    	else if(mess.startsWith("/us/")) {
            String messNew = mess.substring(4);
            UsOnly.setText(messNew + " человек(-a) онлайн");
            UsOnly.updateUI();
            //tabsOpen = new boolean[Integer.parseInt(messNew+1)];
        }else if(mess.startsWith("/nu/")){
            String messNew = mess.substring(4);
            printMsq(messNew,3);
        }else if(mess.startsWith("id/")){
            id = mess.substring(3);
        }else if(mess.startsWith("/us*")){
            String messNew = mess.substring(4);
            addUsToPanel(messNew);
        }else if(mess.startsWith("/usr*")){
            removeUsToPanel(mess.substring(5));
        }else if(mess.startsWith("/pm")) {
            mess = mess.substring(3);
            if(mess.startsWith("/")){
                rightPos(mess.substring(1));
            }else {
    	        mess = mess.substring(1);
    	        System.out.println(mess);
    	        printMsq(mess,1);
            }
        }else {
    	    System.out.println("protect() eror");
        }
    }
    
    /*TODO private void erorConnection(){
        //String point = ".";
        intConnecting++;
        if(intConnecting < 3){
            intConnecting = 0;
            concat = "Соединение";
        }else{
            concat = txtLabelEror + ".";
        }
        UsOnly.setText(concat);
    }
    */
    private void connecting() {
        intConnecting++;
        String concat;
        if(intConnecting < 3){
            intConnecting = 0;
            concat = "Соединение";
        }else{
            concat = txtLabelEror + ".";
        }
        UsOnly.setText(concat);
        try {
            connection = new TCPConnection(this,IP,PORT);
	    } catch (IOException e) {
	    connecting();
            printMsq("Ошибка соединения: " + e,0);
	    }
    }

    private synchronized void printMsq(String msq,int index){
        SwingUtilities.invokeLater(() -> {
            JTextArea mess = new JTextArea(msq);
            JPanel pMess = new JPanel();
            mess.setLineWrap(true);
            mess.setEnabled(false);
            mess.setOpaque(false);
            pMess.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
            pMess.setOpaque(false);
            pMess.setLayout(new BoxLayout(pMess, BoxLayout.Y_AXIS));
            mess.setDisabledTextColor(Colors.mainTxtColor);
            mess.setForeground(Colors.sideTxtColor);
            switch (index) {
                case 0:
                    mess.setDisabledTextColor(Colors.mainTxtColor);
                    pMess.setBorder(new RoundedBorder(9,6,15,6,4,"1"));
                    mess.setFont(new Font("", Font.BOLD,15));
                    pMess.setBackground(new Color(0x787878));
                    mess.setBackground(pMess.getBackground());
                    mess.setSize(new Dimension(mess.getPreferredSize().width*2,mess.getPreferredSize().height));
                    pMess.setName("0");
                    
                    break;
                case 1:
                    String nameStr = cut(msq);
                    JLabel nameLbl = new JLabel(nameStr);
                    nameLbl.setForeground(Colors.sideTxtColor);
                    nameLbl.setFont(new Font("", Font.BOLD,18));
                    nameLbl.setAlignmentX(RIGHT_ALIGNMENT);
                    mess.setText(mess.getText().substring(nameStr.length()+1));//cut name
                    mess.setDisabledTextColor(Colors.mainTxtColor);
                    pMess.setBorder(new RoundedBorder(8,5,7,5,7,"1"));
                    mess.setFont(new Font("", Font.BOLD,16));
                    pMess.setBackground(Colors.msqColor);
                    mess.setBackground(pMess.getBackground());
                    mess.setSize(new Dimension(mess.getPreferredSize().width*3,mess.getPreferredSize().height));
                    pMess.setName("1");
                    pMess.add(nameLbl);

                    break;
                case 3:
                    mess.setDisabledTextColor(Colors.sideTxtColor);
                    mess.setFont(new Font("",Font.BOLD,15));
                    mess.setOpaque(true);
                    mess.setBackground(Colors.sideColor);
                    mess.setSize(new Dimension(mess.getPreferredSize().width*2,mess.getPreferredSize().height));
                    pMess.setName("0");
                    
                    break;
                case 4:
                    String nameStr2 = cut(msq);
                    mess.setText(mess.getText().substring(nameStr2.length()+1));//cut name
                    mess.setDisabledTextColor(Colors.mainTxtColor);
                    pMess.setBorder(new RoundedBorder(8,6,10,6,10,"1"));
                    pMess.setBackground(Colors.myMsqColor);//0x574cc9 0xAC9E1C
                    mess.setBackground(pMess.getBackground());
                    mess.setFont(new Font("",Font.BOLD,16));
                    mess.setSize(new Dimension(mess.getPreferredSize().width/2*5,mess.getPreferredSize().height));
                    pMess.setName("2");

                    break;
                default:
                    System.out.print("Что-то пошло не так " + mess + "  " + index);
                    break;
            }
            pMess.setForeground(pMess.getBackground());
            pMess.add(mess);
            panelLog.add(pMess);
            panelLog.repaint();
            scroll.revalidate();
            scrollDown(scroll);
        //scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getValue()+10);
        });
    }
    
    private void scrollDown(JScrollPane scrollPane) {
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();

        int currentScrollValue = verticalBar.getValue();
        int previousScrollValue = -1;

        while (currentScrollValue != previousScrollValue) {
            int downDirection = 1;
            int amountToScroll = verticalBar.getUnitIncrement(downDirection);
            verticalBar.setValue(currentScrollValue + amountToScroll);

            previousScrollValue = currentScrollValue;
            currentScrollValue = verticalBar.getValue();
        }
    }

    private void sendMessenge() {
         String msq = input.getText();
         if(msq.replaceAll("\\s+","").equals("")) return;
         input.setText(null);					
         connection.sendString("/pm/" + id + "/  " + name + ":/" + msq);
    }
	
    private void rightPos(String mess){
        String id_l = cut(mess);
        if(id.equals(id_l)){
            String newMess;
            newMess = mess.substring(id_l.length()+1);
            printMsq(newMess,4);
        }else{
            String newMess;
            newMess = mess.substring(id_l.length()+1);
            printMsq(newMess,1);
        }
    }
    
    private void addUsToPanel(String mess) {
        String idUs = cut(mess);
        String nameUs = mess.substring(idUs.length()+1);
        char ch = nameUs.charAt(0);
        String chName = Character.toString(ch);
        
        JPanel usPanel = new JPanel();
        JLabel avatar = new JLabel();
        JLabel name = new JLabel("<html><h2>" + nameUs +"</h2>" + "<smal>В сети</smal>" + "<br><br></html>");
        JLabel lblId = new JLabel("#" + (Integer.parseInt(idUs)-1));
        
        lblId.setForeground(Colors.mainTxtColor);
        lblId.setBounds(280, 17, 60,10);


        /*---/tab2---*/
        avatar.setIcon(new IconId(60,chName));
        avatar.setBounds(15, 17, 70, 70);
        
        name.setForeground(Colors.mainTxtColor);
        name.setBounds( 90, 5, 175, 90);
        name.setPreferredSize(new Dimension(175,90));
        
        usPanel.setPreferredSize(new Dimension(350,100));
        usPanel.setLayout(null);
        usPanel.setOpaque(false);
        usPanel.setBackground(Colors.mainColor);
        usPanel.setForeground(Colors.sideBorderColor);
        usPanel.setBorder(new RoundedBorder(5,5,5,5,5,"1"));
        usPanel.setName("0");
        usPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        usPanel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt) {
                //createPMTab(nameUs,idUs);
                new LoginEror("Почти готово...");
            }    
        });
        usPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent evt) {
                usPanel.setBackground(Colors.mainBorderColor);
                usPanel.setForeground(Colors.mainBorderColor);
                usPanel.setBorder(new RoundedBorder(15,5,5,5,5,"1"));
                usPanel.repaint();
            }
        });
        usPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent evt) {
                usPanel.setBackground(Colors.mainColor);
                usPanel.setBorder(new RoundedBorder(5,5,5,5,5,"1"));

                usPanel.repaint();
            }
        });
        
        usPanel.add(avatar);
        usPanel.add(name);
        usPanel.add(lblId);
        PANELS.add(usPanel);
        panelUsOnly.add(usPanel);

        scrollUsOnly.repaint();
        panelUsOnly.repaint();
        panelMainUsOnly.repaint();
    }    
    
    private void removeUsToPanel(String mess) {
        int index = Integer.parseInt(mess);
        JPanel panel = PANELS.remove(index-1);
        panelUsOnly.remove(panel);
        
        panelUsOnly.repaint();
        scrollUsOnly.repaint();
    }
    
    static String cut(String txt){
        StringBuilder id_l = new StringBuilder();
        for(int i = 0;i<txt.length();i++){
            char ch = txt.charAt(i);
            String chToStr = Character.toString(ch);
            if("/".equals(chToStr)) break;
            else id_l.append(chToStr);
        }
        return id_l.toString();
    }
    
    /*private void createPMTab(String name,String id) {
        int ID = Integer.parseInt(id) - 1;
            if (tabsOpen[ID]) {
                for (int i = 0;i < tab.getTabCount()-1;i++){
                    if (tabsPick[i] && tabsOpen[ID]){
                        tab.setSelectedIndex(i+1);
                        return;
                    }
                }
                //new LoginEror("Эта вкладка уже открыта!");
            }  if (!tabsOpen[ID]) {
                JPanel panelPMMain = new JPanel();
                JPanel panelPMTop = new JPanel();
                JPanel panelPMBottom = new JPanel();
                JPanel panelPMLog = new JPanel();
                JPanel panelUsPM = new JPanel();

                JTextField inputPM = new JTextField();
                JButton btnSendPM = new JButton();
                JScrollPane scrollPM = new JScrollPane(panelPMLog);
                JButton SendPF = new JButton();
                JLabel iconUs = new JLabel();
                JLabel namePM = new JLabel("<html><h3>" + name + "</h3>" + "<smal>В сети</smal>" + "<br><br></html>");

                scrollPM.setBackground(Colors.mainColor);
                scrollPM.setBorder(new RoundedBorder(40, -40, -40, -40, -40, null));

                namePM.setForeground(Colors.mainTxtColor);
                namePM.setBounds(65, 5, 150, 55);

                iconUs.setBackground(Colors.mainColor);
                iconUs.setIcon(new IconId(55, Character.toString(name.charAt(0))));
                iconUs.setBounds(0, 5, 55, 55);

                inputPM.setFont(new Font("Arial", Font.BOLD, 18));
                inputPM.setBackground(Colors.sideColor);
                inputPM.setForeground(Colors.mainTxtColor);
                inputPM.addActionListener(this);
                inputPM.setBorder(new RoundedBorder(5,-5,10,-5,10, null));
                inputPM.setToolTipText("Введите текст сообщения");
                inputPM.setCaretColor(input.getForeground());

                //ImageIcon imgSendFile = createIcon("../../rafa/img/imgSendFile.png");
                btnSendPM.setFont(new Font("", Font.BOLD,30));
                btnSendPM.setText("➜");
                btnSendPM.setToolTipText("Нажмите,чтоб отправить сообщение");
                btnSendPM.setContentAreaFilled(false);
                btnSendPM.setOpaque(true);
                btnSendPM.setFocusPainted(false);
                btnSendPM.setBackground(Colors.mainColor);
                btnSendPM.setForeground(Colors.mainTxtColor);
                //btnSendPM.setIcon(imgSendFile);18, -1, 4, -1, 0
                btnSendPM.setBorder(new RoundedBorder(5,-5,10,-5,10,null));
                //ImageIcon imgSendString = createIcon("../../rafa/img/Send.png");
                //SendPF.setIcon(imgSendString);
                SendPF.setText("\uD83D\uDCCE");
                SendPF.setFont(new Font("", Font.BOLD, 30));
                SendPF.setContentAreaFilled(false);
                SendPF.setOpaque(true);
                SendPF.setFocusable(false);
                SendPF.setBorder(new RoundedBorder(5,5,3,0,3,null));
                SendPF.setBackground(Colors.mainColor);
                SendPF.setForeground(Colors.mainTxtColor);

                panelPMMain.setBackground(Colors.sideColor);
                panelPMMain.setBorder(null);
                panelPMMain.setLayout(new BorderLayout());

                panelPMTop.setBackground(Colors.sideColor);
                panelPMTop.setLayout(new VerticalLayout());
                panelPMTop.setBorder(null);

                panelPMBottom.setBackground(Colors.sideColor);
                panelPMBottom.setBorder(null);
                panelPMBottom.setLayout(new BorderLayout());

                panelPMLog.setBackground(Colors.sideColor);
                panelPMLog.setBorder(new RoundedBorder(15, -15, -15, -15, -15, null));

                panelUsPM.setBackground(Colors.sideColor);
                panelUsPM.setForeground(Colors.sideTxtColor);
                panelUsPM.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                panelUsPM.setOpaque(false);
                panelUsPM.setLayout(null);
                panelUsPM.setPreferredSize(new Dimension(260, 65));
                panelUsPM.setName("0");
                panelUsPM.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        new MyAccount(name);
                    }
                });
                panelUsPM.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent evt) {
                        panelUsPM.setBackground(Colors.mainBorderColor);
                        namePM.setForeground(Colors.mainTxtColor);
                        //  panelUsPM.setBorder(new RoundedBorder(15,5,5,5,5,"1"));
                        panelUsPM.repaint();
                    }
                });
                panelUsPM.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseExited(MouseEvent evt) {
                        panelUsPM.setBackground(Colors.sideColor);
                        namePM.setForeground(Colors.sideTxtColor);
                        //panelUsPM.setBorder(new RoundedBorder(5,5,5,5,5,"1"));

                        panelUsPM.repaint();
                    }
                });

                panelUsPM.add(iconUs);
                panelUsPM.add(namePM);

                panelPMTop.add(panelUsPM, BorderLayout.CENTER);

                panelPMBottom.add(SendPF, BorderLayout.WEST);
                panelPMBottom.add(inputPM, BorderLayout.CENTER);
                panelPMBottom.add(btnSendPM, BorderLayout.EAST);

                panelPMMain.add(panelPMTop, BorderLayout.NORTH);
                panelPMMain.add(panelPMBottom, BorderLayout.SOUTH);
                panelPMMain.add(scrollPM, BorderLayout.CENTER);

                int size;
                if (tab.getTabPlacement() == JTabbedPane.LEFT){
                    size = 60;
                }else {
                    size= 30;
                }
                Icon avatar = new IconId(size, Character.toString(name.charAt(0)));

                tab.addTab(name + "\n #" + ID , avatar, panelPMMain);
                tab.setTabComponentAt(tab.getTabCount()-1,new ClosableTabTitle(name + "/#" + ID ,tab.getTabCount()-1,tab));
                tabsOpen[ID] = true;
                tabsPick[tab.getTabCount()-1] = true;
                tab.setSelectedIndex(tab.getTabCount()-1);

                //for (int i = 1;i < tab.getTabCount()-1;i++){
                    //if (tab.getComponentAt(i).getName().equals(id)){
                      //  tab.setSelectedIndex(i);
                        //return;
                    //}
                //}
                tab.repaint();
                panelPMMain.repaint();
                panelUsPM.repaint();
                panelUsPM.updateUI();
            } else {
                System.out.println("Что-то не так в CreatePMTab");
            }
    }
        

     TODO private void send_File() {
        try {
            //sendFile = new SendFile(this,IP_ADDR,PORT);	
            F.showOpenDialog(null);
            File file = F.getSelectedFile();	
            String inputFile;
            inputFile = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
            if(inputFile.equals("")){ 
                new LoginEror("Файл Пуст");
            }
            else connection.sendFile("/f/" + inputFile);					 	
	    } catch (IOException e1) {
                //System.out.println(e1);
        }
    } */
    
    /*private void saveFile(String output) {
        String substring = output.substring(3);
        F.showOpenDialog(null);
        FileOutputStream writer;
        File file = F.getSelectedFile();	
        try {
            writer = new FileOutputStream(file);
            writer.write(substring.getBytes());
            writer.close();
        }catch(IOException e1){
            System.out.println(e1);
        }
    }*/
}
