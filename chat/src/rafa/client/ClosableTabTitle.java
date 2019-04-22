package rafa.client;

import rafa.code.IconId;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static rafa.client.Client.cut;
//import static rafa.client.Client.setTabsOpens;

class ClosableTabTitle extends JPanel {

        ClosableTabTitle(final String title, final int index, JTabbedPane tabPane){
            super(new BorderLayout());
            setOpaque(false);
            if (index == 0){
                   JLabel lbl = new JLabel("<html><h3>" + title + "</h3></html>");
                   JLabel icon = new JLabel("⚙");

                   lbl.setForeground(Colors.mainTxtColor);
                   if (tabPane.getTabPlacement() == JTabbedPane.LEFT) {
                       lbl.setIcon(new IconId(45, Character.toString(title.charAt(0))));
                   }else {
                       lbl.setIcon(new IconId(28, Character.toString(title.charAt(0))));
                   }
                   icon.addMouseListener(new MouseAdapter() {
                       @Override
                       public void mouseClicked(MouseEvent e) {
                            System.out.println("Сейчас всё будет");
                       }
                   });
                   add(lbl,BorderLayout.WEST);
                   add(icon,BorderLayout.EAST);
            }else {
                JLabel icon = new JLabel("❌");
                JLabel name = new JLabel(cut(title));
                String nameUs = name.getText();
                JLabel status = new JLabel(title.substring(nameUs.length()));

                status.setForeground(Colors.mainTxtColor);
                name.setForeground(Colors.mainTxtColor);
                if (tabPane.getTabPlacement() == JTabbedPane.LEFT) {
                    name.setText("<html><h3>" + name.getText() + "</h3><p>" + status.getText() + "</p><br></html>");
                    icon.setPreferredSize(new Dimension(35,20));
                   // name.setFont(new Font("",Font.BOLD,16));
                    name.setIcon(new IconId(50, Character.toString(title.charAt(0))));
                }else {
                    icon.setPreferredSize(new Dimension(15,15));
                    name.setFont(new Font("",Font.BOLD,13));
                    name.setIcon(new IconId(28, Character.toString(title.charAt(0))));
                }
                //JLabel icon = new JLabel(new ImageIcon(getClass().getResource("/delete.png")));
                //icon.setBackground(Colors.myMsqColor);
                //icon.setOpaque(true);
                icon.setVerticalAlignment(JLabel.TOP);
                icon.setHorizontalAlignment(JLabel.RIGHT);
                icon.setForeground(Colors.mainTxtColor);
                tabPane.repaint();
                icon.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        tabPane.removeTabAt(index);
                        //tabsOpen[index] = false;
                        //setTabsOpens(index);
                    }
                });


                    add(name, BorderLayout.WEST);
                    add(icon, BorderLayout.CENTER);

                tabPane.repaint();
                //add(button, BorderLayout.EAST);
            }
        }
    }

