package skyport.api.game;

/**
 * 
 * Represents a player of Skyport.
 * @author Bjarte
 */
public class Player {
    /**
     * The name of the player.
     */
    private String name;
    /**
     * The primary weapon of the player.
     */
    private Weapon primaryWeapon;
    /**
     * The primary weapon of the player.
     */
    private Weapon secondaryWeapon;
    
    /**
     * The players position. 
     */
    private Point position;
    /**
     * The players score.
     */
    private int score;
    /**
     * The remaining health of the player.
     */
    private int health;

    /**
     * @return The players name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The players primary weapon.
     */
    public Weapon getPrimary() {
        return this.primaryWeapon;
    }

    /**
     * @return The players secondary weapon.
     */
    public Weapon getSecondary() {
        return this.secondaryWeapon;
    }

    /**
     * @param string A name of a weapon
     * @return The players weapon of that name if
     *         it exists, otherwise null.
     */
    public Weapon getWeapon(WeaponType wt) {
        if (this.primaryWeapon.getName().equals(wt)) {
            return this.primaryWeapon;
        } else if (this.secondaryWeapon.getName().equals(wt)) {
            return this.secondaryWeapon;
        } else {
            return null;
        }
    }

    public Point getPosition() {
        return position;
    }
    
    public void setPosition(Point p) {
        this.position = p;
    }

    public int getScore() {
        return score;
    }

    public int getHealth() {
        return health;
    }
}
