package rafa.network;

import java.io.*;
import java.net.Socket;

public class SendImg {
	
	private final Socket socket;
	private BufferedReader in;
	private BufferedWriter out;
	private Thread Thread;
	
	public SendImg(String ipAddr,int port) throws IOException {
		this(new Socket(ipAddr,port));
	}
	
	public SendImg(Socket socket) throws IOException{
		this.socket = socket;
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		if(this.socket == null) {
			System.out.println("Socket = null");
			return;
		}
		Thread = new Thread(new Runnable() {

			@Override
			public void run() {
				
			}
			
		});
		
		Thread.start();
	}
}
