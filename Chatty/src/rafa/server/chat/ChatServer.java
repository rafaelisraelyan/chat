package rafa.server.chat;

import rafa.network.TCPConnectionListner;
import rafa.network.SendFile;
import rafa.network.TCPConnection;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;


public class ChatServer implements TCPConnectionListner{
        public static void main (String[]args){
            new ChatServer();
        }
    
    public static int numberUsers = 0;
    private final ArrayList<TCPConnection> connections = new ArrayList<>();
    
    public ChatServer() {
            System.out.print("Введите порт будет работать сервер:");
            Scanner scanner_port = new Scanner(System.in);
            int port = scanner_port.nextInt();
            System.out.println("Server running...");
            scanner_port.close();
            try (ServerSocket serverSocket = new ServerSocket(port);) {
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
        public synchronized void onConnectionReady (rafa.network.TCPConnection topConnectoin){
            connections.add(topConnectoin);
            System.out.println(connections.size());
            for(int i = 0;i>= connections.size();i++) System.out.println(connections.get(i));
            numberUsers++;
            //SendToAllConnections(Name + " зашёл в чат: " + topConnectoin + ".\n В чате " + numberUsers + " участников. ");
            SendToAllConnections("\n \t В чате " + numberUsers + " участник(-ов). \n");

        }

        @Override
        public synchronized void onReceiveString (rafa.network.TCPConnection topConnectoin, String value){
        	if(value == "/printUsers") {
        		for(int i = 0;i>= connections.size();i++) System.out.println(connections.get(i));
        	}
            SendToAllConnections(value);
        }

        @Override
        public synchronized void onDisconnect (rafa.network.TCPConnection topConnectoin){
            connections.remove(topConnectoin);
            numberUsers--;
           // SendToAllConnections(Name + " вышел из чата:" + topConnectoin + ".\n В чате " + numberUsers + " участников.");
            SendToAllConnections("\n \t В чате " + numberUsers + " участник(-ов).\n");
           // SendToAllConnections(topConnectoin.toString() + " вышел из чата");
        }

        @Override
        public synchronized void onException (rafa.network.TCPConnection topConnectoin, Exception e){
            System.out.println("TCPConnection exception: " + e);
        }

 
		@Override
			public void onConnectionReady(SendFile topConnectoin, String Name) {
				System.out.println("Функция отправки файла c названием работает!");
				
			}
		
			@Override
			public void onConnectionReady(SendFile topConnectoin) {
				System.out.println("Функция отправки файла без названия работает!");
				
			}
		
			@Override
			public String onReceiveString(SendFile topConnectoin, String value) {
				SendToAllConnections(value);
				return value;
			}
		
			@Override
			public void onDisconnect(SendFile topConnectoin) {
				System.out.println("Произошло отключение от сети.");
			}
		
			@Override
			public void onException(SendFile topConnectoin, Exception e) {
		System.out.println("Функция отправки файлов не работает:" + e);
	
	}
		
		
	    private void SendToAllConnections(String value) {
	    	System.out.println(value);
	    	final int cnt = connections.size();
	    	for (int i = 0; i < cnt; i++) connections.get(i).sendString(value);
	    }
}
