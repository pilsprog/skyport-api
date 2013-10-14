package skyport.api.example;

import java.util.List;

import skyport.api.SkyportClient;
import skyport.api.game.Direction;
import skyport.api.game.GameState;
import skyport.api.game.Point;
import skyport.api.game.WeaponType;

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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.client.sendLoadout("mortar", "laser");
    }

    @Override
    public void run() {
        GameState state;
        do {
            state = client.nextTurn(name);
            Point me = state.getPlayers().get(0).getPosition();
            for (int i = 0; i < 3; i++) {
                Point g = me;
                Point he = state.getPlayers().get(1).getPosition();
                if (state.getPlayers().get(0).getWeapon(WeaponType.MORTAR).inRange(g, state.getMap()).contains(he)) {
                    client.fireMortar(he.getJ() - me.getJ(), he.getK() - me.getK());
                    break;
                } else if (state.getPlayers().get(0).getWeapon(WeaponType.LASER).inRange(g, state.getMap()).contains(he)) {
                    client.fireLaser(g.direction(he));
                    break;
                } else {
                    List<Point> points = state.getMap().neighbors(me);
                    System.out.println("s:" + points.size());
                    for (Point p : points) {
                        if (p.distance(he) <= me.distance(he)) {
                            me = p;
                        }
                    }
                    Direction dir = g.direction(me);
                    if (dir == Direction.none) {
                        client.move(g.direction(points.get(0)));
                    } else {
                        client.move(dir);
                    }

                }
            }

        } while (state != null);
    }

}
