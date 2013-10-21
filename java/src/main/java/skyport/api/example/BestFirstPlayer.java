package skyport.api.example;

import java.util.List;

import skyport.api.SkyportClient;
import skyport.api.game.Direction;
import skyport.api.game.GameState;
import skyport.api.game.Map;
import skyport.api.game.Player;
import skyport.api.game.Point;
import skyport.api.game.weapon.Laser;
import skyport.api.game.weapon.Mortar;
import skyport.api.game.weapon.Weapon;

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
        this.client.sendLoadout(new Mortar(), new Laser());
    }

    @Override
    public void run() {
        GameState state;
        do {
            state = client.nextTurn(name);
            Map map = state.getMap();

            Player me = state.getPlayers().get(0);
            Point currentPosition = me.getPosition();

            Player enemy = state.getPlayers().get(1);
            Point enemyPosition = enemy.getPosition();

            Weapon mortar = me.getPrimary();
            Weapon laser = me.getSecondary();

            // We will do three actions unless we can fire.
            for (int i = 0; i < 3; i++) {
                // is the mortar in range? Fire mortar and break.
                if (mortar.inRange(currentPosition, map).contains(enemyPosition)) {
                    client.fireMortar(
                    // The mortar fires at a position relative to the
                    // player. Not at an absolute position.
                            enemyPosition.getJ() - currentPosition.getJ(),
                            enemyPosition.getK() - currentPosition.getK());
                    break;
                    // Is the laser in range? Fire laser and stop all actions.
                } else if (laser.inRange(currentPosition, map).contains(enemyPosition)) {
                    client.fireLaser(currentPosition.direction(enemyPosition));
                    break;
                    // Else we are going to move.
                } else {
                    // Get all the neighbours of the currentPosition.
                    List<Point> points = map.neighbours(currentPosition);
                    Point nextPosition = points.get(0);
                    for (Point p : points) {
                        // checking to see which of the neighbours are the closest to the enemy.
                        if (p.distance(enemyPosition) < nextPosition.distance(enemyPosition)) {
                            nextPosition = p;
                        }
                    }
                    // get the direction the next position is in.
                    Direction dir = currentPosition.direction(nextPosition);
                    if (dir != Direction.none) {
                        client.move(dir);
                        // Need to set the currentPosition to nextPosition as
                        // the map is not updated between actions.
                        currentPosition = nextPosition;
                    }
                }
            }
        } while (state != null);
    }
}
