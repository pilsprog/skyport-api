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
import skyport.api.game.weapon.ShootActionIterator;

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

            Player enemy = state.getPlayers().get(1);
            Point enemyPosition = enemy.getPosition();

            // We will do three actions unless we can fire.
            for (int i = 0; i < 3; i++) {
                // is the mortar in range? Fire mortar and break.

                boolean didShoot = false;
                ShootActionIterator it = me.getPrimary().iterator(me.getPosition(), map);
                while (it.hasNext()) {
                    Point shootAt = it.next();
                    if (shootAt.equals(enemyPosition)) {
                        System.out.println("BESTFIRST WILL SHOOT YOU DOWN!");
                        it.getCurrentAction().perform(client);
                        didShoot = true;
                        break;
                    }
                }

                if (didShoot) {
                    break;
                }

                // Try the same thing with the laser!
                didShoot = false;
                it = me.getSecondary().iterator(me.getPosition(), map);
                while (it.hasNext()) {
                    Point shootAt = it.next();
                    if (shootAt.equals(enemyPosition)) {
                        System.out.println("BESTFIRST WILL SHOOT YOU DOWN!");
                        it.getCurrentAction().perform(client);
                        didShoot = true;
                        break;
                    }
                }

                if (didShoot) {
                    break;
                }

                // Get all the neighbours of the currentPosition.
                Point currentPosition = me.getPosition();
                List<Point> points = map.neighbours(currentPosition);
                Point nextPosition = points.get(0);
                for (Point p : points) {
                    // checking to see which of the neighbours are the
                    // closest to the enemy.
                    if (p.distance(enemyPosition) <= nextPosition.distance(enemyPosition)) {
                        nextPosition = p;
                    }
                }
                // get the direction the next position is in.
                Direction dir = currentPosition.direction(nextPosition);
                System.out.println("MOVE ALONG, NOTHING TO SEE HERE!");
                System.out.println(currentPosition + ":" + nextPosition + "=" + dir);
                client.move(dir);
                // Need to set the currentPosition to nextPosition as
                // the map is not updated between actions.

            }
        } while (state != null);
    }
}
