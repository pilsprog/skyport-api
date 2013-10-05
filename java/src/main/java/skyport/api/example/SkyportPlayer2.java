package skyport.api.example;

import skyport.api.SkyportConnection;

public class SkyportPlayer2 {
    private static SkyportConnection conn = new SkyportConnection("localhost", 54321);
    
    public static void main(String[] args) throws InterruptedException {
	conn.connect();
	SkyportPlayer player = new SkyportPlayer("Giskard", conn);
	player.handshake();
	
	String message = "";
	while(message != null) {
	    message = conn.read();
	    player.handle(message);
	}
    }
}
