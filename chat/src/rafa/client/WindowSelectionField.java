package rafa.client;

/*import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import rafa.code.RoundedBorder;

public class WindowSelectionField extends JFrame{

    private static final long serialVersionUID = 1L;

    private static final int WIDTH_WINDOW = 320;
    private static final int HEIGHT_WINDOW = 115;
    public static boolean click;
    
    public static void main(String[] args) {
    	EventQueue.invokeLater(() -> new WindowSelectionField("Принять Файл?"));
    }
    
    public WindowSelectionField(String eror){
        	
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    	JFrame frame = new JFrame("Выбор:");

        Font font = new Font("ArialBlack", Font.BOLD,17);
        
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH_WINDOW,HEIGHT_WINDOW);
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        
        panel.setBorder(new EmptyBorder(1,1,1,1));
        panel.setBackground(Colors.mainColor);
        panel.setForeground(Colors.mainTxtColor);
        panel.setLayout(null);

        JLabel textSelection = new JLabel("Login exeption!");
        textSelection.setText(eror);
        textSelection.setFont(font);
        textSelection.setBounds(10, 8, 300, 30);
        textSelection.setForeground(Colors.mainTxtColor);
        textSelection.setHorizontalAlignment(JLabel.CENTER);
        textSelection.setText(eror);
        panel.add(textSelection);

        JButton btn = new JButton("Да");
        btn.setBounds(170,40,70,40);
        btn.setContentAreaFilled(false);
        btn.setBorder(new RoundedBorder(5,5,5,5,5,null));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setFont(new Font("Arial",Font.BOLD,15));
        btn.setBackground(Colors.mainColor);
        btn.setForeground(Colors.mainTxtColor);
        panel.add(btn);

        JButton btn2 = new JButton("Нет");
        btn2.setBounds(40,40,70,40);
        btn2.setContentAreaFilled(false);
        btn2.setOpaque(true);
        btn2.setFocusPainted(false);
        btn2.setBorder(new RoundedBorder(5,5,5,5,6,null));
        btn2.setFont(new Font("Arial",Font.BOLD,15));
        btn2.setBackground(Colors.mainColor);
        btn2.setForeground(Colors.mainTxtColor);
        panel.add(btn2);
        
        btn.addActionListener((ActionEvent e) -> {
            click = true;
            frame.dispose();
        });
        
        btn2.addActionListener((ActionEvent e) -> {
            click = false;
            frame.dispose();
        });
        
        frame.setVisible(true);

    }
}*/
