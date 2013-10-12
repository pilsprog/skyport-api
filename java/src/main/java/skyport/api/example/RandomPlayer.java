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
import skyport.api.game.WeaponType;

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

		// Adds a random choice of two weapons
		actions.addAll(Arrays.asList("laser", "mortar", "droid"));
		actions.remove(random.nextInt(actions.size()));

		// Requests the chosen weapons
		this.client.sendLoadout(actions.get(0), actions.get(1));

		// Adds the remaining actions
		actions.add("mine");
		actions.add("upgrade");
	}

	/**
	 * @return A random direction.
	 */
	private Direction randomDirection() {
		int dir = random.nextInt(Direction.values().length);
		return Direction.values()[dir];
	}

	@Override
	public void run() {
		GameState state;
		do {
			// Generate the game state for the current turn
			state = this.client.nextTurn(this.name);

			// Generate directions for each action
			Direction action1 = randomDirection();
			Direction action2 = randomDirection();
			Direction action3 = randomDirection();

			Player me = state.getPlayers().get(0);
			int level;

			// Move in two random directions
			this.client.move(action1);
			this.client.move(action2);

			// Do a random action
			switch (actions.get(random.nextInt(actions.size()))) {

			// Lay down a mine
			case "mine":
				this.client.mine();
				break;

			// Upgrade a random weapon
			case "upgrade":
				String weapon = actions.get(random.nextInt(2));
				this.client.upgrade(weapon);
				break;

			// Fire the laser
			case "laser":
				this.client.fireLaser(action3);
				break;

			// Fire the mortar to a random tile
			case "mortar":
				Point point = me.getPosition();
				level = me.getWeapon(WeaponType.MORTAR).getLevel();
				int j = random.nextInt(level + 2) + point.getJ();
				int k = random.nextInt(level + 2) + point.getK();
				this.client.fireMortar(j, k);
				break;

			// Deploy a droid to move in random directions
			case "droid":
				level = me.getWeapon(WeaponType.DROID).getLevel();
				List<Direction> dirs = new ArrayList<Direction>();
				for (int steps = 2 + level; steps > 0; steps--) {
					dirs.add(this.randomDirection());
				}
				this.client.fireDroid(dirs);

			}
		} while (state != null);
	}
}
