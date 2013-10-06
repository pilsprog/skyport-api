package skyport.api;

import java.util.List;

import skyport.api.game.Direction;
import skyport.api.game.GameState;
import skyport.api.game.Player;
import skyport.api.message.DroidAttackMessage;
import skyport.api.message.ErrorMessage;
import skyport.api.message.HandshakeMessage;
import skyport.api.message.LaserAttackMessage;
import skyport.api.message.LoadoutMessage;
import skyport.api.message.Message;
import skyport.api.message.MineMessage;
import skyport.api.message.MortarAttackMessage;
import skyport.api.message.MoveMessage;
import skyport.api.message.StatusMessage;
import skyport.api.message.UpgradeMessage;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SkyportClient {
    private SkyportConnection conn;

    private Gson gson = new GsonBuilder()
    	.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
    	.create();
    
    private String json;
    
    public SkyportClient(String host, int port) {
	this.conn = new SkyportConnection(host, port);
    }

    public void connect() {
	this.conn.connect();
    }
    
    public void sendMessage(Message message) {
	json = gson.toJson(message);
	System.out.println("Send json: " + json);
	conn.send(json);
    }

    public boolean sendHandshake(String name) {
	HandshakeMessage handshake = new HandshakeMessage(name);
	sendMessage(handshake);
	
	String response = conn.read();
	StatusMessage status = gson.fromJson(response, StatusMessage.class);
	return status.getStatus();
    }
    

    public void sendLoadout(String primary, String secondary) {
	LoadoutMessage loadout = new LoadoutMessage(primary, secondary);
	sendMessage(loadout);
    }
    
    public Message nextMessage() {
	json = conn.read();
	ErrorMessage message = gson.fromJson(json, ErrorMessage.class);
	if (message == null) {
	    throw new RuntimeException("Message is null, probably lost connection.");
	}
	if (message.getError() != null) {
	    System.err.println("ERROR: "+message.getError());
	}
	return message;
    }

    /**
     * Gives the turn to the next player. Ignores all messages until the next
     * game state is received.
     * 
     * @return The current game state.
     */
    public GameState nextState() {
	for (;;) {
	    Message message = this.nextMessage();
	    if (message.getMessage() == null) {
		continue;
	    }
	    if (message.getMessage().equals("gamestate")) {
		GameState state = gson.fromJson(json, GameState.class);
		return state;
	    }
	}
    }
    
    /**
     * Ignores all messages until it is the named players next turn again.
     * @param name The name of the player to get the next turn for.
     * @return The current game state.
     */
    public GameState nextTurn(String name) {
	for(;;) {
	    GameState state = this.nextState();
	    Player player = state.getPlayers().get(0);
	    if(player.getName().equals(name) && state.getTurn() > 0) {
		return state;
	    }
	}
    }
    
    public void move(Direction dir) {
	MoveMessage move = new MoveMessage(dir);
	sendMessage(move);
    }
    
    public void mine() {
	MineMessage mine = new MineMessage();
	sendMessage(mine);
    }
    
    public void upgrade(String weapon) {
	UpgradeMessage upgrade = new UpgradeMessage(weapon);
	sendMessage(upgrade);
    }
    
    public void fireLaser(Direction dir) {
	LaserAttackMessage attack = new LaserAttackMessage(dir);
	sendMessage(attack);
    }
    
    public void fireMortar(int j, int k) {
	MortarAttackMessage attack = new MortarAttackMessage(j, k);
	sendMessage(attack);
    }
    
    public void fireDroid(List<Direction> directions) {
	DroidAttackMessage attack = new DroidAttackMessage(directions);
	sendMessage(attack);
    }
}
