package rafa.network;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

public class TCPConnection {    
	private final TCPConnectionListner eventListner;
    private final Socket socket;
    private final Thread rxThread;
    private BufferedReader in;
    private BufferedWriter out;
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
                    		eventListner.onReceiveString(TCPConnection.this,in.readLine());
                    }
                }catch (IOException e){
                    eventListner.onException(TCPConnection.this,e);
  
                }finally {
                    eventListner.onDisconnect(TCPConnection.this);
                }
            }
        }); rxThread.start();
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
    
    /*public synchronized void sendFile(String value){
        try {
            out.write(value);
            out.flush();
        } catch (IOException e) {
            eventListner.onException(TCPConnection.this,e);
            disconnect();
        }
    }*/

    private synchronized void disconnect(){
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

}

