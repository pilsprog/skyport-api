package skyport.api.example;

import skyport.api.SkyportClient;

public class BestFirstPlayer implements Runnable {
    
    /**
     * The client connection to the skyport server.
     */
    private SkyportClient client;

    /**
     * The name of the player.
     */
    private String name;

    public BestFirstPlayer(String name, String host, int port) {
            this.name = name;

            // Creates the connection to the server
            this.client = new SkyportClient(host, port);
            this.client.connect();
            this.client.sendHandshake(this.name);

            // Requests the chosen weapons
            this.client.sendLoadout("mortar", "laser");
    }
    
    @Override
    public void run() {
        
    }

}
