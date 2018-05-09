/*package rafa.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;

import javax.swing.JFileChooser;

public class SendString {
	private final TCPConnectionListner eventListner;
    private final Socket socket;
    private final Thread rxThread;
    private BufferedReader in;
    private BufferedWriter out;
	private JFileChooser f = new JFileChooser();

    public SendString(TCPConnectionListner eventListner,String ipAddr,int port) throws IOException{
        this(eventListner, new Socket(ipAddr,port));
    }

	public SendString(TCPConnectionListner eventListner, Socket socket) throws IOException{
        this.eventListner = eventListner;
        this.socket = socket;
        in = new  BufferedReader(new InputStreamReader(socket.getInputStream(),Charset.forName("UTF-8")));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),Charset.forName("UTF-8")));

        rxThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    eventListner.onConnectionReady(SendString.this);
                    while (!rxThread.isInterrupted()){
                    	eventListner.onReceiveString(SendString.this,in.readLine());
                        if(eventListner.onReceiveString(SendString.this,in.readLine()) == null) break;
                        saveFile();
                    }
                }catch (IOException e){
                    eventListner.onException(SendString.this,e);

                }finally {
                    eventListner.onDisconnect(SendString.this);
                }
            }


        });

        rxThread.start();
    }

    public void saveFile() {
    	String output = null;
        f.showOpenDialog(null);
		File file = f.getSelectedFile();
		
		try {
			output = eventListner.onReceiveString(SendString.this, in.readLine());
			FileOutputStream writer = new FileOutputStream(file);
			writer.write(output.getBytes());
			writer.close();
		}catch(IOException e1){
			e1.printStackTrace();
		};
    }
    
    public synchronized void sendFile(String value){
        try {
            out.write(value + "\r\n");
            out.flush();
        } catch (IOException e) {
            eventListner.onException(SendString.this,e);
            disconnect();
        }
    }
    public synchronized void disconnect(){
        rxThread.interrupt();        
        try {
            socket.close();
        } catch (IOException e) {
            eventListner.onException(SendString.this,e);
        }
    }

}*/
