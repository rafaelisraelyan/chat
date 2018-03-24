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
    String Name = "?";
    
		public ChatServer() {
            System.out.print("������� ���� ����� �������� ������:");
            Scanner scanner_port = new Scanner(System.in);
            int port = scanner_port.nextInt();
            System.out.println("Server running...");
            scanner_port.close();
            try (ServerSocket serverSocket = new ServerSocket(port);) {
                while (true) {
                    try {
                        new TCPConnection(this, serverSocket.accept(),Name);
                        
                    } catch (Exception e) {
                        System.out.println("TCPConnection exception: " + e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
           
        }
        @Override
        public synchronized void onConnectionReady (rafa.network.TCPConnection topConnection,String Name){
            connections.add(topConnection);
            this.Name = Name;
            for(int i = 0;i>= connections.size();i++) System.out.println(connections.get(i));
            numberUsers++;
            System.out.println("\t \t" + topConnection);
            //SendToAllConnections(this.Name + " ����� � ���: " + topConnectoin + ".\n � ���� " + numberUsers + " ����������. ");
            SendToAllConnections("\n \t � ���� " + connections.size() + " ��������(-��). \n");

        }

        @Override
        public synchronized void onReceiveString (rafa.network.TCPConnection topConnection, String value){
        	if(value.equals("/printUsers")) {
        		for(int i = 0;i>= connections.size();i++) System.out.println(connections.get(i));
        	}
            SendToAllConnections(value);
        }

        @Override
        public synchronized void onDisconnect (rafa.network.TCPConnection topConnection){
            connections.remove(topConnection);
            numberUsers--;
           // SendToAllConnections(Name + " ����� �� ����:" + topConnectoin + ".\n � ���� " + numberUsers + " ����������.");
            SendToAllConnections("\n \t � ���� " + numberUsers + " ��������(-��).\n");
           // SendToAllConnections(topConnectoin.toString() + " ����� �� ����");
        }

        @Override
        public synchronized void onException (rafa.network.TCPConnection topConnection, Exception e){
            System.out.println("TCPConnection exception: " + e);
        }

 
		@Override
			public void onConnectionReady(SendFile topConnection, String Name) {
				System.out.println("������� �������� ����� c ��������� ��������!");
				
			}
		
			@Override
			public void onConnectionReady(SendFile topConnection) {
				System.out.println("������� �������� ����� ��� �������� ��������!");
				
			}
		
			@Override
			public String onReceiveString(SendFile topConnection, String value) {
				SendToAllConnections(value);
				return value;
			}
		
			@Override
			public void onDisconnect(SendFile topConnection) {
				System.out.println("��������� ���������� �� ����.");
			}
		
			@Override
			public void onException(SendFile topConnection, Exception e) {
				System.out.println("������� �������� ������ �� ��������:" + e);
	
	}
		   
			


		
	    private void SendToAllConnections(String value) {
	    	System.out.println(value);
	    	final int cnt = connections.size();
	    	for (int i = 0; i < cnt; i++) connections.get(i).sendString(value);
	    }
}
