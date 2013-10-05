package skyport.api.message;

import java.util.List;

import skyport.api.game.Map;
import skyport.api.game.Player;

public class GameState extends Message {
    private int turn;
    private List<Player> players;
    private Map map;
    
    public int getTurn() {
	return turn;
    }
    
    public Map getMap() {
	return map;
    }
    
    public List<Player> getPlayers() {
	return players;
    }
}
