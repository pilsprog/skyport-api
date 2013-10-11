package skyport.api.game;

/**
 * 
 * A weapon in skyport.
 * @author Bjarte
 */
public class Weapon {
    private int level;
    private String name;

    /**
     * @return The name of the weapon. 
     */
    public String getName() {
        return name;
    }

    /**
     * @return The weapons level.
     */
    public int getLevel() {
        return level;
    }
}
