package skyport.api.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import skyport.api.SkyportClient;
import skyport.api.game.Direction;
import skyport.api.game.GameState;
import skyport.api.game.Player;

public class RandomPlayer implements Runnable {

    private SkyportClient client;
    private String name;

    private final static Random random = new Random();

    public RandomPlayer(String name, String host, int port) {
	this.name = name;
	this.client = new SkyportClient(host, port);
    }

    private Direction randomDirection() {
	int dir = random.nextInt(Direction.values().length);
	return Direction.values()[dir];
    }

    private List<String> actions = new ArrayList<String>();

    @Override
    public void run() {
	actions.addAll(Arrays.asList("laser", "mortar", "droid"));
	this.client.connect();
	this.client.sendHandshake(this.name);
	actions.remove(random.nextInt(actions.size()));

	this.client.sendLoadout(actions.get(0), actions.get(1));
	actions.add("mine");
	actions.add("upgrade");
	GameState state;
	do {
	    state = this.client.nextTurn(this.name);
	    Direction action1 = randomDirection();
	    Direction action2 = randomDirection();
	    Direction action3 = randomDirection();

	    Player me = state.getPlayers().get(0);
	    int level;

	    this.client.move(action1);
	    this.client.move(action2);
	    switch (actions.get(random.nextInt(actions.size()))) {
	    case "mine":
		this.client.mine();
		break;
		
	    case "upgrade":
		String weapon = actions.get(random.nextInt(2));
		this.client.upgrade(weapon);
		break;
	
	    case "laser":
		this.client.fireLaser(action3);
		break;
		
	    case "mortar":
		int posj = me.getJ();
		int posk = me.getK();
		level = me.getWeapon("mortar").getLevel();
		int j = random.nextInt(level + 2) + posj;
		int k = random.nextInt(level + 2) + posk;
		this.client.fireMortar(j, k);
		break;
		
	    case "droid":
		level = me.getWeapon("droid").getLevel();
		List<Direction> dirs = new ArrayList<Direction>();
		for(int steps = 3+level; steps > 0; steps--) {
		    dirs.add(this.randomDirection());
		}
		this.client.fireDroid(dirs);
		
	    }
	} while (state != null);
    }
}
