package skyport.api.game;

/**
 * 
 * A weapon in skyport.
 * @author Bjarte
 */
public class Weapon {
    private int level;
    private WeaponType weapon;

    /**
     * @return The name of the weapon. 
     */
    public WeaponType getName() {
        return weapon;
    }

    /**
     * @return The weapons level.
     */
    public int getLevel() {
        return level;
    }
}
