package rafa.network;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import javax.swing.JFileChooser;
//import static rafa.client.chat.WindowSelectionField.click;

public class TCPConnection {    
	private final TCPConnectionListner eventListner;
    private final Socket socket;
    private final Thread rxThread;
    private BufferedReader in;
    private BufferedWriter out;
	private JFileChooser f = new JFileChooser();
	//private boolean messBool;
	
    public TCPConnection(TCPConnectionListner eventListner,String ipAddr,int port) throws IOException{
        this(eventListner, new Socket(ipAddr,port));
    }

    public TCPConnection(TCPConnectionListner eventListner, Socket socket) throws IOException{
        this.eventListner = eventListner;
        this.socket = socket;
        in = new  BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));

        rxThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	
                    eventListner.onConnectionReady(TCPConnection.this);
                    
                    while (!rxThread.isInterrupted()){
                    	//protect();
                    	//if(messBool) {
                    		eventListner.onReceiveString(TCPConnection.this,in.readLine());
                    		//}
                    }
                }catch (IOException e){
                    eventListner.onException(TCPConnection.this,e);
  
                }finally {
                    eventListner.onDisconnect(TCPConnection.this);

                }
            }


        }); rxThread.start();
    }
    	
    /*	TODO private void protect() { 
    		String mess = null;
    		try {
    			mess = in.readLine();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		if(mess.startsWith("/f/")) {
    			messBool = false;
    			new WindowSelectionField("Принять файл?");
    			while(click == true || click == false) {
	    			if(click) {
	    				saveFile();
	    			}else {
	    				messBool = true;
	    				return;
	    			}
    			}
    			
    			
    		}
    	}*/
    
    public void saveFile() {
        String output = in.toString();
        output.substring(3,output.length());
		f.showOpenDialog(null);
		File file = f.getSelectedFile();
		
		try {
			FileOutputStream writer = new FileOutputStream(file);
			writer.write(output.getBytes());
			writer.close();
		}catch(IOException e1){
			e1.printStackTrace();
		};
    }
    
    public synchronized void sendString(String value){
        try {
            out.write(value + "\r\n");
            out.flush();
        } catch (IOException e) {
            eventListner.onException(TCPConnection.this,e);
            disconnect();
        }
    }
    public synchronized void disconnect(){
        rxThread.interrupt();        
        try {
            socket.close();
        } catch (IOException e) {
            eventListner.onException(TCPConnection.this,e);
        }
    }

    @Override
    public String toString(){
        System.out.println("Новый пльзователь" + ": " + socket.getPort());
        return "TCPConnection: " + socket.getInetAddress() + ": " + socket.getPort();
    }

/*	public void sendFile(String string) {
		SendString str = null;
		try {
			str = new SendString(null, socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		str.sendFile(string);
		
	}*/
}

