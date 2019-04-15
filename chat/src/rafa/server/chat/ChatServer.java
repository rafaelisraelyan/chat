package rafa.server.chat;

import rafa.network.TCPConnectionListner;
import rafa.network.TCPConnection;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatServer implements TCPConnectionListner{
       
    private final ArrayList<TCPConnection> connections = new ArrayList<>();
    String[] NameUsers = new String[1024];
        
     	public static void main (String[]args){
            new ChatServer();
        }
     	
		private ChatServer() {
            System.out.print("Введите порт будет работать сервер:");
            Scanner scanner_port = new Scanner(System.in);
            int port = scanner_port.nextInt();
            System.out.println("Server running...");
            scanner_port.close();
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                while (true) {
                    try {
                        new TCPConnection(this, serverSocket.accept());
                    } catch (Exception e) {
                        System.out.println("TCPConnection exception: " + e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
           
        }
        @Override
        public synchronized void onConnectionReady (rafa.network.TCPConnection topConnection){
            connections.add(topConnection);
            SendToAllConnections("/us/" + connections.size());
            System.out.println("\t \t" + topConnection);
        }

        @Override
        public synchronized void onReceiveString (rafa.network.TCPConnection topConnection, String value){
        	if(value.startsWith("/printUs/")) {
        		for(int i = 0;i>= connections.size();i++) System.out.println(connections.get(i) + " " + NameUsers[i]);
        	}
        	if(value.startsWith("/n/")) {
        		NameUsers[connections.size()] = value.substring(3);
                SendToAllConnections("\n \t В чат зашёл " + NameUsers[connections.size()] + "\n");
        	}else{
        		SendToAllConnections(value);
        	}
        }

        @Override
        public synchronized void onDisconnect (TCPConnection topConnection){
            SendToAllConnections("\n \t" + NameUsers[connections.size()] + " вышел из чата.");
            NameUsers[connections.size()] = null;
            connections.remove(topConnection);
            SendToAllConnections("/us/" + connections.size());
        }

        @Override
        public synchronized void onException (TCPConnection topConnection, Exception e){
            System.out.println("Ошибка TCP соединения: " + e);
        }
		   
	    private void SendToAllConnections(String value) {
	    	System.out.println(value);
            for (TCPConnection connection : connections) connection.sendString(value);
	    }
}
