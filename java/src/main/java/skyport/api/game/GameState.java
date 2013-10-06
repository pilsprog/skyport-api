package skyport.api.game;

import java.util.List;

public class GameState {
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
