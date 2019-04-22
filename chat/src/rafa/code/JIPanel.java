package rafa.code;

import org.jdesktop.swingx.JXPanel;

import javax.swing.*;
import java.awt.*;

public class JIPanel extends JXPanel {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    JIPanel(int id){
        setId(id);
    }

    JIPanel(){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
