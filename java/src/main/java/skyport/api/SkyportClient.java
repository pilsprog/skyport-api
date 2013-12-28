package skyport.api;

import java.util.List;

import skyport.api.game.Direction;
import skyport.api.game.GameState;
import skyport.api.game.Player;
import skyport.api.game.Point;
import skyport.api.game.Tile;
import skyport.api.game.adapter.PointAdapter;
import skyport.api.game.adapter.WeaponAdapter;
import skyport.api.game.weapon.Weapon;
import skyport.api.message.DroidActionMessage;
import skyport.api.message.ErrorMessage;
import skyport.api.message.HandshakeMessage;
import skyport.api.message.LaserActionMessage;
import skyport.api.message.LoadoutMessage;
import skyport.api.message.Message;
import skyport.api.message.MineActionMessage;
import skyport.api.message.MortarActionMessage;
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
        .registerTypeAdapter(Point.class, new PointAdapter())
        .registerTypeAdapter(Weapon.class, new WeaponAdapter())
        .create();

    private String json;
    
    private GameState state;

    /**
     * Constructs a skyport client. Sets up the connection to the server.
     *
     * @param host
     *            The hostname or ip-address of the server.
     * @param port
     *            The port number the server is listening to.
     */
    public SkyportClient(String host, int port) {
        this.conn = new SkyportConnection(host, port);
    }

    /**
     * Starts the connection to the server.
     */
    public void connect() {
        this.conn.connect();
    }

    /**
     * Sends the message to the server.
     *
     * @param message
     *            A message to send to the server.
     */
    public void sendMessage(Message message) {
        json = gson.toJson(message);
        System.out.println("Send: " + json);
        conn.send(json);
    }

    /**
     * Sends the handshake to the server.
     *
     * The server needs to recieve a handshake after the client connects.
     *
     * @param name
     *            The name of your bot.
     * @return Returns true if the connection was successful.
     */
    public boolean sendHandshake(String name) {
        HandshakeMessage handshake = new HandshakeMessage(name);
        sendMessage(handshake);

        String response = conn.read();
        StatusMessage status = gson.fromJson(response, StatusMessage.class);
        return status.getStatus();
    }

    /**
     * Sends the chosen loadout to the server.
     *
     * @param primary
     *            The primary weapon the bot is going to use.
     * @param secondary
     *            The secondary weapon the bot is going to use.
     */
    public void sendLoadout(Weapon primary, Weapon secondary) {
        LoadoutMessage loadout = new LoadoutMessage(primary, secondary);
        sendMessage(loadout);
    }

    /**
     * Blocks until the next message is received from the server.
     *
     * @return The next message from the server.
     */
    public Message nextMessage() {
        json = conn.read();
        System.out.println("Receive: " + json);
        ErrorMessage message = gson.fromJson(json, ErrorMessage.class);
        if (message == null) {
            throw new RuntimeException("Message is null, probably lost connection.");
        }
        if (message.getError() != null) {
            System.err.println("ERROR: " + message.getError());
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
                state = gson.fromJson(json, GameState.class);
                return state;
            }
        }
    }

    /**
     * Ignores all messages until it is the named players next turn again.
     *
     * @param name
     *            The name of the player to get the next turn for.
     * @return The current game state.
     */
    public GameState nextTurn(String name) {
        for (;;) {
            this.nextState();
            Player player = state.getPlayers().get(0);
            if (player.getName().equals(name) && state.getTurn() > 0) {
                return state;
            }
        }
    }

    /**
     * Sends the direction you want the bot to move in next.
     *
     * @param dir
     *            The direction to move in.
     */
    public void move(Direction dir) {
        MoveMessage move = new MoveMessage(dir);
        Player thePlayer = state.getPlayers().get(0);
        Point playerPosition = thePlayer.getPosition();
        Point newPosition = playerPosition.adjacent(dir);
        
        if(newPosition == null)
        	throw new IllegalArgumentException();
        Tile data = state.getMap().getData(newPosition);
        if(data.equals(Tile.VOID)
        || data.equals(Tile.ROCK)
        || data.equals(Tile.SPAWN))
        	throw new IllegalArgumentException();
        
        sendMessage(move);
        thePlayer.setPosition(newPosition);
    }

    /**
     * Sends a message; dig for ore at the current location.
     */
    public void mine() {
        MineActionMessage mine = new MineActionMessage();
        sendMessage(mine);
    }

    /**
     * Sends a message; upgrade given weapon.
     *
     * @param weapon
     */
    public void upgrade(Weapon weapon) {
        UpgradeMessage upgrade = new UpgradeMessage(weapon);
        sendMessage(upgrade);
    }

    /**
     * Send a message; fire laser in the given direction.
     *
     * If the bot does not have a laser the message will be interpreted as
     * invalid and the bot will lose points.
     *
     * @param dir
     */
    public void fireLaser(Direction dir) {
        LaserActionMessage attack = new LaserActionMessage(dir);
        sendMessage(attack);
    }

    /**
     * Send a message; fire mortar at a relative point.
     *
     * If the bot does not have a mortar or it can not hit the point the message
     * will be interpreted as invalid and the bot will lose points.
     *
     * @param dir
     */
    public void fireMortar(int j, int k) {
        MortarActionMessage attack = new MortarActionMessage(j, k);
        sendMessage(attack);
    }

    /**
     * Send a message; fire droid in direction.
     *
     * If the bot does not have a droid or it can not hit the point the message
     * will be interpreted as invalid and the bot will lose points.
     *
     * @param directions
     */
    public void fireDroid(List<Direction> directions) {
        DroidActionMessage attack = new DroidActionMessage(directions);
        sendMessage(attack);
    }
}
