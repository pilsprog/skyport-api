package skyport.api.game;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * A weapon in skyport.
 * 
 * @author Bjarte
 */
public class Weapon {
    private int level;
    private WeaponType name;

    /**
     * @return The name of the weapon.
     */
    public WeaponType getName() {
        return name;
    }

    /**
     * @param p
     *            The point to shoot from.
     * @param m
     *            The map the weapon is fired from.
     * @return The points the weapon can shoot at.
     */
    public List<Point> inRange(Point p, Map m) {
        List<Point> toReturn = new ArrayList<Point>();

        switch (name) {
        case MORTAR:
            for (int j = -distance(); j <= distance(); j++) {
                for (int k = -distance(); k <= distance(); k++) {
                    int newK = p.getK() + k;
                    int newJ = p.getJ() + j;
                    Point newPoint = new Point(newJ, newK);
                    if (newK < m.getkLength() && newK >= 0 && newJ < m.getjLength() 
                            && newJ >= 0 
                            && p.distance(newPoint) <= distance()) {
                        toReturn.add(newPoint);
                    }
                }
            }

            break;
        case LASER:
            for (int j = 1; j <= distance(); j++) {
                int newK = p.getK();
                int newJ = p.getJ() + j;
                if (m.getData(new Point(newK, newJ)).equals(Tile.ROCK)) {
                    break;
                }
                if (newK < m.getkLength() && newK >= 0 
                        && newJ < m.getjLength() && newJ >= 0) {
                    toReturn.add(new Point(newK, newJ));
                }
            }
            for (int k = 1; k <= distance(); k++) {
                int newK = p.getK();
                int newJ = p.getJ() + k;
                if (m.getData(new Point(newK, newJ)).equals(Tile.ROCK)) {
                    break;
                }
                if (newK < m.getkLength() && newK >= 0 
                        && newJ < m.getjLength() && newJ >= 0) {
                    toReturn.add(new Point(newK, newJ));
                }
            }
            for (int i = 1; i <= distance(); i++) {
                int newK = p.getK() + i;
                int newJ = p.getJ() + i;
                if (m.getData(new Point(newK, newJ)).equals(Tile.ROCK)) {
                    break;
                }
                if (newK < m.getkLength() && newK >= 0 
                        && newJ < m.getjLength() && newJ >= 0) {
                    toReturn.add(new Point(newK, newJ));
                }
            }
            for (int j = -1; j >= -distance(); j--) {
                int newK = p.getK();
                int newJ = p.getJ() + j;
                if (m.getData(new Point(newK, newJ)).equals(Tile.ROCK)) {
                    break;
                }
                if (newK < m.getkLength() && newK >= 0 
                        && newJ < m.getjLength() && newJ >= 0) {
                    toReturn.add(new Point(newJ, newK));
                }
            }
            for (int k = -1; k >= -distance(); k--) {
                int newK = p.getK();
                int newJ = p.getJ() + k;
                if (m.getData(new Point(newK, newJ)).equals(Tile.ROCK)) {
                    break;
                }
                if (newK < m.getkLength() && newK >= 0 
                        && newJ < m.getjLength() && newJ >= 0) {
                    toReturn.add(new Point(newK, newJ));
                }
            }
            for (int i = -1; i >= -distance(); i--) {
                int newK = p.getK() + i;
                int newJ = p.getJ() + i;
                if (m.getData(new Point(newK, newJ)).equals(Tile.ROCK)) {
                    break;
                }
                if (newK < m.getkLength() && newK >= 0 
                        && newJ < m.getjLength() && newJ >= 0) {
                    toReturn.add(new Point(newK, newJ));
                }
            }
            break;
        case DROID:
            for (int j = -distance(); j <= distance(); j++) {
                for (int k = -distance(); k <= distance(); k++) {
                    int newK = p.getK() + k;
                    int newJ = p.getJ() + j;
                    if (newK < m.getkLength() && newK >= 0
                            && newJ < m.getjLength() && newJ >= 0) {
                        toReturn.add(new Point(newJ, newK));
                    }
                }
            }
            break;
        default:
            assert false;
        }
        return toReturn;
    }

    /**
     * Returns the damage the weapon does.
     * 
     * @return The damage of the weapon.
     */
    public int damage() {
        switch (name) {
        case MORTAR:
            return level == 3 ? 25 : 20;
        case LASER:
            return 16 + 2 * (level - 1);
        case DROID:
            return 20 + 2 * level;
        }
        return 0;
    }

    /**
     * Returns the maximum distance the weapon will hit something.
     * 
     * @return The distance of the weapon.
     */
    public int distance() {
        switch (name) {
        case MORTAR:
            return 1 + level;
        case LASER:
            return 4 + level;
        case DROID:
            return 2 + level;
        }
        return 0;
    }

    /**
     * @return The weapons level.
     */
    public int getLevel() {
        return level;
    }
}
