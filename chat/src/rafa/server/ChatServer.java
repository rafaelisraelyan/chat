package rafa.server;

import rafa.network.TCPConnectionListner;
import rafa.network.TCPConnection;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatServer implements TCPConnectionListner{
       
    private final ArrayList<TCPConnection> connections = new ArrayList<>();
    private String[] NameUsers = new String[1024];

    public static void main (String[]args){
        new ChatServer();
    }
     	
    private ChatServer() {
        System.out.print("Введите порт будет работать сервер:");
        int port;
        try (Scanner scanner_port = new Scanner(System.in)) {
            port = scanner_port.nextInt();
            System.out.println("Server running...");
        }
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
            
            String id = Integer.toString(connections.size());
            String ID = "id/" + id; 
            int a = connections.size() - 1;
            System.out.println(ID + " " + a);
            SendToUs(ID,a);
            
            SendToAllConnections("/us/" + connections.size());
            System.out.println("\t \t" + topConnection);
        }

    @Override
        public synchronized void onReceiveString (rafa.network.TCPConnection topConnection, String value){
            if(value.startsWith("/n/")) {
                NameUsers[connections.size()] = value.substring(3,value.length());
                SendToAllConnections("/nu/ В чат зашёл: " + NameUsers[connections.size()]);
                boolean boolAtt = false;
                for(int i = 1; i <= connections.size(); i++) {
                    if(i == connections.size()){
                        for(int f = 1;f <= connections.size();f++){
                            if(f == connections.size()) continue;
                            SendToUs("/us*" + f + "/" + NameUsers[f], i-1);
                        }
                   // break;
                    }else if(i != connections.size() && !boolAtt){
                        boolAtt = true;
                        for(int j = 1;j < connections.size();j++){
                                if(j == connections.size()) continue;
                                else SendToUs("/us*" +  connections.size() + "/" + NameUsers[connections.size()],j-1);
                           // break;
                        }
                    //break;
                    }
                }
            }else{
                SendToAllConnections(value);
            }
        }

    @Override
        public synchronized void onDisconnect (TCPConnection topConnection){
            int a = connections.size()-1;
            SendToAllConnections("/nu/" + NameUsers[connections.size()] + ":вышел из чата.");
            SendToAllConnections("/usr*" + a);
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
            
    private void SendToUs(String value,int i){
        System.out.println(value + "  " + i);
        connections.get(i).sendString(value);
    }

    public void setNameUsers(String[] nameUsers) {
        NameUsers = nameUsers;
    }
}
