package skyport.api.example;


import skyport.api.SkyportConnection;
import skyport.api.message.GameState;
import skyport.api.message.Handshake;
import skyport.api.message.Loadout;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SkyportPlayer {
    
    private SkyportConnection conn;
    private Handshake handshake;
    
    public SkyportPlayer(String name, SkyportConnection conn) {
	this.conn = conn;
	this.handshake = new Handshake(name);
    }
    
    private Gson gson = new GsonBuilder()
    	.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
    	.create();
    
    public void handshake() {
	String message = gson.toJson(handshake);
	conn.send(message);
	System.out.println("Handshake: " + message);
    }
    
    public void handle(String json) {
	System.out.println(json);
	GameState gs = gson.fromJson(json, GameState.class);
	if(gs.getMessage().equals("connect" )) {
	    return;
	}
	if(gs.getMessage().equals("endturn")) {
	    return;
	}
	if (gs.getTurn() == 0) {
	    String message = gson.toJson(new Loadout("laser", "mortar"));
	    conn.send(message);
	    return;
	}
	
	if(gs.getPlayers().get(0).getName().equals(handshake.getName())) {
	    System.out.println("IT IS MY TURN!");
	}
    }
}
