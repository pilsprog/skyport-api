package skyport.api.game;

import java.util.List;

/**
 * 
 * The state of the game at a given turn.
 * @author Bjarte
 */
public class GameState {
	
    /**
     * The turn number. 
     */
    private int turn;
    
    /**
     * The list of players 
     */
    private List<Player> players;
  
    /**
     * The game map.
     */
    private Map map;

    /**
     * @return The turn number.
     */
    public int getTurn() {
        return turn;
    }

    /**
     * @return The game map.
     */
    public Map getMap() {
        return map;
    }

    /**
     * @return A list of players left in the game,
     *        the first player has the turn.
     */
    public List<Player> getPlayers() {
        return players;
    }
}
