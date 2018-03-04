package rafa.network;

public interface TCPConnectionListner {
	
    void onConnectionReady(TCPConnection topConnectoin);
    void onReceiveString(TCPConnection topConnectoin,String value);
    void onDisconnect(TCPConnection topConnectoin);
    void onException(TCPConnection topConnectoin,Exception e);
    
    void onConnectionReady(SendString topConnectoin,String Name);
    void onConnectionReady(SendString topConnectoin);
    String onReceiveString(SendString topConnectoin,String value);
    void onDisconnect(SendString topConnectoin);
    void onException(SendString topConnectoin,Exception e);
}
