package skyport.api.example;

import skyport.api.SkyportConnection;

	
public class SkyportPlayer1 {
    private static SkyportConnection conn = new SkyportConnection("localhost", 54321);
    
    public static void main(String[] args) throws InterruptedException {
	conn.connect();
	SkyportPlayer player = new SkyportPlayer("Daneel", conn);
	player.handshake();
	
	String message = "";
	while(message != null) {
	    message = conn.read();
	    player.handle(message);
	}
    }
}
