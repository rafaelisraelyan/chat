package rafa.code;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;


public class IconId implements Icon{
    
    private final int size;
    private final String name;
    
    public IconId(int size, String name){
        this.size = size;
        this.name = name;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
          Graphics2D graphics = (Graphics2D) g.create();
          graphics.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
          graphics.setColor(new Color(0x003300));//0xFF3333 0x5878FF 0x0033FF 0x003300
          graphics.fillRoundRect(x, y, size, size, size, size);
          graphics.setColor(Color.WHITE);
          graphics.setFont(new Font("Arial", Font.BOLD,size/2));
          if(size > 45){
              graphics.drawString(name, (x+(size/2))-10, (y+(size/2))+10);
              graphics.dispose();
          }else{
              if (size >= 90){
                  graphics.setFont(new Font("Arial", Font.BOLD, size/2+5) );
                  graphics.drawString(name, (x+(size/2)-45), (y+(size/2)+45));
                  return;
              }
              graphics.drawString(name, (x+(size/2))-7, (y+(size/2))+7);
          }
          graphics.dispose();
    }

    @Override
    public int getIconWidth() {
        return size;
    }

    @Override
    public int getIconHeight() {
       return size;
    }
    
}
