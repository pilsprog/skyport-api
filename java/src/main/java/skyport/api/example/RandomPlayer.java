package skyport.api.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import skyport.api.SkyportClient;
import skyport.api.game.Direction;
import skyport.api.game.GameState;
import skyport.api.game.Player;
import skyport.api.game.Point;
import skyport.api.game.weapon.Droid;
import skyport.api.game.weapon.Laser;
import skyport.api.game.weapon.Mortar;
import skyport.api.game.weapon.Weapon;

/**
 * 
 * A Random player of Skyport. The player chooses randomly among a set of moves.
 * 
 * @author Bjarte
 */
public class RandomPlayer implements Runnable {

    /**
     * The client connection to the skyport server.
     */
    private SkyportClient client;

    /**
     * The name of the player.
     */
    private String name;

    /**
     * The loadout of the player.
     */
    private List<Weapon> loadout;

    /**
     * A list of actions the player can perform.
     */
    private List<String> actions = new ArrayList<String>();

    private final static Random random = new Random();

    /**
     * Constructor.
     * 
     * @param name
     *            The name of the player.
     * @param host
     *            The hostname/ip of the server.
     * @param port
     *            The port that the server is running on.
     */
    public RandomPlayer(String name, String host, int port) {
        this.name = name;

        // Creates the connection to the server
        this.client = new SkyportClient(host, port);
        this.client.connect();
        this.client.sendHandshake(this.name);

        // Randomly choose two weapons.
        loadout = new ArrayList<>();
        loadout.addAll(Arrays.asList(new Droid(), new Laser(), new Mortar()));
        int i = random.nextInt(loadout.size());
        loadout.remove(i);
        Weapon primary = loadout.get(0);
        Weapon secondary = loadout.get(1);

        // Requests the chosen weapons
        this.client.sendLoadout(primary, secondary);

        // Add the actions we can choose from to a list so
        // we can randomly choose from it.
        actions.add(primary.getClass().getSimpleName().toLowerCase());
        actions.add(secondary.getClass().getSimpleName().toLowerCase());
        actions.add("mine");
        actions.add("upgrade");
    }

    /**
     * @return A random direction.
     */
    private Direction randomDirection() {
        int dir = random.nextInt(Direction.values().length - 1);
        return Direction.values()[dir];
    }

    @Override
    public void run() {
        GameState state;
        do {
            // Generate the game state for the current turn
            state = this.client.nextTurn(this.name);

            // Generate directions for each action

            Player me = state.getPlayers().get(0);

            // Move in two random directions
            boolean trying = true;
            while (trying) {
                try {
                    Direction dir = randomDirection();
                    this.client.move(dir);
                    trying = false; // Whoo it worked!
                } catch (IllegalArgumentException e) {
                    // Oups we couldn't actually move there
                    // Lets try again
                }
            }

            // Do a random action
            switch (actions.get(random.nextInt(actions.size()))) {

            // mine the ore at the current location
            case "mine":
                this.client.mine();
                break;

            // Upgrade a random weapon
            case "upgrade":
                Weapon weapon = loadout.get(random.nextInt(2));
                this.client.upgrade(weapon);
                break;

            // Fire the laser
            case "laser":
                Direction dir = randomDirection();
                this.client.fireLaser(dir);
                break;

            // Fire the mortar to a random tile
            case "mortar":
                Point point = me.getPosition();
                int j = random.nextInt(2) + point.getJ();
                int k = random.nextInt(2) + point.getK();
                this.client.fireMortar(j, k);
                break;

            // Deploy a droid to move in random directions
            case "droid":
                List<Direction> dirs = new ArrayList<Direction>();
                for (int steps = 2; steps > 0; steps--) {
                    dirs.add(this.randomDirection());
                }
                this.client.fireDroid(dirs);

            }
        } while (state != null);
    }
}
