package skyport.api.game.weapon;

import skyport.api.game.Map;
import skyport.api.game.Point;
import skyport.api.game.Tile;

/**
 * 
 * A weapon in skyport.
 * 
 * @author Bjarte
 */
public abstract class Weapon {
    protected int level;

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

    public abstract ShootActionIterator iterator(Point from, Map map);

    public abstract Tile getResource();

    /**
     * @return The weapons level.
     */
    public int getLevel() {
        return level;
    }
}
