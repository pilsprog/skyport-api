package skyport.api.game.weapon;

import java.util.List;

import skyport.api.game.Map;
import skyport.api.game.Point;

/**
 * 
 * A weapon in skyport.
 * 
 * @author Bjarte
 */
public abstract class Weapon {
    protected int level;

    /**
     * @param p
     *            The point to shoot from.
     * @param m
     *            The map the weapon is fired from.
     * @return The points the weapon can shoot at.
     */
    public abstract List<Point> inRange(Point p, Map m);

    /**
     * Returns the damage the weapon does.
     * 
     * @return The damage of the weapon.
     */
    public abstract int damage();

    /**
     * Returns the maximum distance the weapon will hit something.
     * 
     * @return The distance of the weapon.
     */
    public abstract int distance();

    /**
     * @return The weapons level.
     */
    public int getLevel() {
        return level;
    }
}
