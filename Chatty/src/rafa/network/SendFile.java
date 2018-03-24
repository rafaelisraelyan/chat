package rafa.network;

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

public class SendFile {
	private final TCPConnectionListner eventListner;
    private final Socket socket;
    private final Thread rxThread;
    private BufferedReader in;
    private BufferedWriter out;
	private JFileChooser f = new JFileChooser();

    public SendFile(TCPConnectionListner eventListner,String ipAddr,int port) throws IOException{
        this(eventListner, new Socket(ipAddr,port));
    }

	public SendFile(TCPConnectionListner eventListner, Socket socket) throws IOException{
        this.eventListner = eventListner;
        this.socket = socket;
        in = new  BufferedReader(new InputStreamReader(socket.getInputStream(),Charset.forName("UTF-8")));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),Charset.forName("UTF-8")));

        rxThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    eventListner.onConnectionReady(SendFile.this);
                    while (!rxThread.isInterrupted()){
                    	eventListner.onReceiveString(SendFile.this,in.readLine());
                        if(eventListner.onReceiveString(SendFile.this,in.readLine()) == null) break;
                        saveFile();
                    }
                }catch (IOException e){
                    eventListner.onException(SendFile.this,e);

                }finally {
                    eventListner.onDisconnect(SendFile.this);
                }
            }


        });

        rxThread.start();
    }

    public synchronized void saveFile() {

		try {
			String output = eventListner.onReceiveString(SendFile.this, in.readLine());
			if(output == null) return;
			f.setDialogTitle("Сохранить");
			f.showOpenDialog(null);
		    File file = f.getSelectedFile();
			FileOutputStream writer = new FileOutputStream(file);
			writer.write(output.getBytes());
			writer.close();
		}catch(IOException e1){
			e1.printStackTrace();
		}
    }
    
    public synchronized void sendFile(String value){
        try {
            out.write(value);
            out.flush();
        } catch (IOException e) {
            eventListner.onException(SendFile.this,e);
            disconnect();
        }
    }
    public synchronized void disconnect(){
        rxThread.interrupt();        
        try {
            socket.close();
        } catch (IOException e) {
            eventListner.onException(SendFile.this,e);
        }
    }

}
