package rafa.network;

public interface TCPConnectionListner {
	
    void onConnectionReady(TCPConnection topConnection);
    void onReceiveString(TCPConnection topConnection,String value);
    void onDisconnect(TCPConnection topConnection);
    void onException(TCPConnection topConnection,Exception e);
    
    void onConnectionReady(SendFile topConnection,String Name);
    void onConnectionReady(SendFile topConnection);
    String onReceiveString(SendFile topConnection,String value);
    void onDisconnect(SendFile topConnection);
    void onException(SendFile topConnection,Exception e);
}
