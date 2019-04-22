 package rafa.code;

import rafa.client.Colors;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.Border;

public class RoundedBorder implements Border {
    private final int radius;
    private final int top;
    private final int right;
    private final int bottom;
    private final int left;
    private final String name;

    public RoundedBorder(int radius,int top,int right,int bottom,int left,String name) {
        this.radius = radius;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
        this.name = name;
    }


    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+top, this.radius+right, this.radius+bottom, this.radius+left);
        //+1/+1/+2
        //return new Insets(this.radius-top, this.radius-70,this.radius-80, this.radius-80);
    }


    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(name == null){
            graphics.drawRoundRect(x+10, y+8, width-15, height-15, radius+16, radius+16);  
         }else {
            if("btnLogin".equals(name)){
                graphics.setColor(Colors.sideBorderColor);
                graphics.drawRoundRect(x+8, y+8, width-11, height-11, radius+20, radius+20);
                graphics.setColor(c.getBackground());
                graphics.fillRoundRect(x+8, y+8, width-11, height-11, radius+20, radius+20);
                graphics.setFont(new Font("Arial", Font.BOLD,20));
                graphics.setColor(c.getForeground());
                graphics.drawString("Войти", 65, 35);
            }else if("tfName".equals(c.getName())){
                graphics.setColor(c.getForeground());
                graphics.drawLine(x+2, y+height, width, y+height);
                //graphics.drawRoundRect(x,y,width-1,height-1,50,50);
                //System.out.println("Это " + name);
               // graphics.dispose();
            }else if ("MyAccount".equals(name)){
                graphics.setColor(new Color(0xaaaaaa));
                graphics.drawRoundRect(x,y,width-1,height-1,50,50);
            }
            else{
                graphics.setColor(c.getBackground());
                graphics.fillRoundRect(x+6, y+6, width-9, height-9, radius+30, radius+30);
                graphics.setColor(c.getForeground());
                graphics.drawRoundRect(x+6, y+6, width-9, height-9, radius+30, radius+30);
            }

        }
        graphics.dispose();
    }
}