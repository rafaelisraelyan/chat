package rafa.network;

public interface TCPConnectionListner {
	
    void onConnectionReady(TCPConnection topConnectoin);
    void onReceiveString(TCPConnection topConnectoin,String value);
    void onDisconnect(TCPConnection topConnectoin);
    void onException(TCPConnection topConnectoin,Exception e);
    
    void onConnectionReady(SendFile topConnectoin,String Name);
    void onConnectionReady(SendFile topConnectoin);
    String onReceiveString(SendFile topConnectoin,String value);
    void onDisconnect(SendFile topConnectoin);
    void onException(SendFile topConnectoin,Exception e);
}
