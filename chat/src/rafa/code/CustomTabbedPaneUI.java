package rafa.code;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.View;
import rafa.client.Colors;


public class CustomTabbedPaneUI extends BasicTabbedPaneUI{
    
    private Color selectColor;
    private Color deSelectColor;
    private final int inclTab = 4;
    private final int anchoFocoV = inclTab;
    private final int anchoFocoH = 4;
    private final int anchoCarpetas = 18;

    public static ComponentUI createUI(JComponent c) {
        return new CustomTabbedPaneUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        selectColor = Colors.mainBorderColor;
        deSelectColor = Colors.mainColor;
        tabAreaInsets.right = anchoCarpetas;
    }

    @Override
    protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
        if (runCount > 1) {
            int lines[] = new int[runCount];
            for (int i = 0; i < runCount; i++) {
                lines[i] = rects[tabRuns[i]].y + (tabPlacement == TOP ? maxTabHeight : 0);
            }
            Arrays.sort(lines);
            if (tabPlacement == TOP) {
                int fila = runCount;
                for (int i = 0; i < lines.length - 1; i++, fila--) {
                    Polygon carp = new Polygon();
                    carp.addPoint(0, lines[i]);
                    carp.addPoint(tabPane.getWidth() - 2 * fila - 2, lines[i]);
                    carp.addPoint(tabPane.getWidth() - 2 * fila, lines[i] + 3);
                    if (i < lines.length - 2) {
                        carp.addPoint(tabPane.getWidth() - 2 * fila, lines[i + 1]);
                        carp.addPoint(0, lines[i + 1]);
                    } else {
                        carp.addPoint(tabPane.getWidth() - 2 * fila, lines[i] + rects[selectedIndex].height);
                        carp.addPoint(0, lines[i] + rects[selectedIndex].height);
                    }
                    carp.addPoint(0, lines[i]);
                    //g.setColor(hazAlfa(fila));
                    g.fillPolygon(carp);
                    g.setColor(darkShadow.darker());
                    g.drawPolygon(carp);
                }
            } else {
                int fila = 0;
                for (int i = 0; i < lines.length - 1; i++, fila++) {
                    Polygon carp = new Polygon();
                    carp.addPoint(0, lines[i]);
                    carp.addPoint(tabPane.getWidth() - 1 * fila - 1, lines[i]);
                    carp.addPoint(tabPane.getWidth() - 2 * fila - 1, lines[i + 1] - 3);
                    carp.addPoint(tabPane.getWidth() - 2 * fila - 3, lines[i + 1]);
                    carp.addPoint(0, lines[i + 1]);
                    carp.addPoint(0, lines[i]);
                    g.setColor(hazAlfa(fila + 2));
                    g.fillPolygon(carp);
                    g.setColor(darkShadow.darker());
                    g.drawPolygon(carp);
                }
            }
        }
        super.paintTabArea(g, tabPlacement, selectedIndex);
    }

    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Graphics2D g2D = (Graphics2D) g.create();
        g2D.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        /*int xp[];
        int yp[];
 
        xp = new int[]{x, x, x - 3 + w, x - 3 + w, x};
        yp = new int[]{y, y + h - 3, y + h - 3, y, y};
        GradientPaint gradientShadow = new GradientPaint(x, y, new Color(0x3f3f3f), x, y + h, new Color(0x232222));*/
        RoundRectangle2D rect;

        if (isSelected) {
            g2D.setColor(Colors.sideColor);
            if(tabPlacement == TOP) rect = new RoundRectangle2D.Double(x, y, w-5, h,7,7);
            else rect = new RoundRectangle2D.Double(x+2, y+3, w+10, h-3,7,7);
            g2D.fill(rect);
        } else {
            if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
                g2D.setColor(selectColor);
                if (tabPlacement == LEFT) rect = new RoundRectangle2D.Double(x, y+2, w+5, h-2,7,7);
                else rect = new RoundRectangle2D.Double(x, y+2, w-10, h-3,7,7);
                g2D.fill(rect);
                g2D.setClip(rect);
            } else {
                g2D.setColor(deSelectColor);
                rect = new RoundRectangle2D.Double(x, y+2, w+5, h-2,7,7);
                g2D.fill(rect);
            }
        }
        g2D.dispose();
    }

     @Override
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
        super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);
        g.setFont(font);
        View v = getTextViewForTab(tabIndex);
        if (v != null) {
            v.paint(g, textRect);
        } else {
            // plain text
            int mnemIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);
            if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
                g.setColor(tabPane.getForegroundAt(tabIndex));
                BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x, textRect.y + metrics.getAscent());
            } else { // tab disabled
                g.setColor(Colors.mainTxtColor);
                BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x, textRect.y + metrics.getAscent());
                g.setColor(tabPane.getBackgroundAt(tabIndex).darker());
                BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemIndex, textRect.x - 10, textRect.y + metrics.getAscent() - 1);
            }
        }
    }

    /*@Override
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
    g.setFont(font);
    View v = getTextViewForTab(tabIndex);
    if (v != null) {
    // html
    v.paint(g, textRect);
    } else {
    // plain text
    int mnemIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);

    if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
    Color fg = tabPane.getForegroundAt(tabIndex);
    if (isSelected && (fg instanceof UIResource)) {
    Color selectedFG = UIManager.getColor("TabbedPane.selectedForeground");
    if (selectedFG != null) {
    fg = selectedFG;
    }
    }
    g.setColor(fg);
    SwingUtilities2.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x-5, textRect.y + metrics.getAscent());

    } else { // tab disabled
    //PAY ATTENTION TO HERE
    g.setColor(tabPane.getBackgroundAt(tabIndex).brighter());
    SwingUtilities2.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x-5, textRect.y + metrics.getAscent());
    g.setColor(tabPane.getBackgroundAt(tabIndex).darker());
    SwingUtilities2.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex,
    textRect.x - 1, textRect.y + metrics.getAscent() - 1);
    }
    }
    }*/

    @Override
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        return 50 + inclTab + super.calculateTabWidth(tabPlacement, tabIndex, metrics);
    }

    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        return 5 + super.calculateTabHeight(tabPlacement, tabIndex, fontHeight);
    }
    
    @Override
    protected int calculateMaxTabWidth(int tabPlacement){
        return 250;
    }

    @Override
    protected int calculateMaxTabHeight (int tabPlacement){
        return 30;
    }
    
    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        /*Graphics2D g2D = (Graphics2D) g.create();
        g2D.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        g2D.setColor(Colors.sideColor);
        if(isSelected) {
            g2D.setColor(Colors.sideBorderColor);
            g2D.drawLine(x,y,w,y);//top
            g2D.drawLine(x,y+h,w,y+h);//bottom
            g2D.drawLine(x+1,y+1,x+1,h+y);//left
        }
        else {
            g2D.setColor(Colors.mainColor);
            g2D.drawRoundRect(x, y+1, w, h,7,7);
        }
        g2D.dispose();*/
    }

    @Override
    protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
        if(isSelected && tabPlacement == LEFT){
            g.setColor(selectColor);
            g.drawRoundRect(rects[tabIndex].x, rects[tabIndex].y+1, rects[tabIndex].width+6, rects[tabIndex].height-3,7,7);
        }
    }
  
    @Override
    protected void paintIcon(Graphics g, int tabPlacement, int tabIndex, Icon icon, Rectangle iconRect, boolean isSelected) {
        if (icon != null) icon.paintIcon(tabPane, g, iconRect.x - 12, iconRect.y);
    }
    
    @Override
    protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
        /*if (tabPlacement == LEFT) {
            int height = tabPane.getHeight() - 3;
            Insets insets = tabPane.getInsets();
            Insets tabAreaInsets1 = getTabAreaInsets(tabPlacement);

            int x = insets.left;
            int y = insets.top;

            x += calculateTabAreaWidth(tabPlacement, runCount, maxTabWidth);
            x -= tabAreaInsets1.right;

            Graphics2D gp = (Graphics2D) g.create();
            gp.setColor(Colors.sideBorderColor);
            gp.drawLine(x , y+7, x , y + height-3);
            gp.dispose();
        }*/
    }
    
    
    protected Color hazAlfa(int fila) {
        int alfa = 0;
        if (fila >= 0) {
            alfa = 50 + (fila > 7 ? 70 : 10 * fila);
        }
        return new Color(0, 0, 0, alfa);
    }
}
