package rafa.code;

import java.net.URL;
import javax.swing.ImageIcon;
import rafa.client.Client;

public class createIcon {

    //private final String path;
    
    /*public createIcon(String path){
        this.path = path;
        createIcon(path);
    }*/

    public static ImageIcon createIcon(String path) {
       URL imgURL = Client.class.getResource(path);     
       if (imgURL != null) {
           return new ImageIcon(imgURL);
       } else {
           System.err.println("File not found " + path);
           return null;
       }
    }
}
