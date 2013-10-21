package skyport.api.game;

import skyport.api.game.weapon.Weapon;

/**
 * 
 * Represents a player of Skyport.
 * 
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

    public Point getPosition() {
        return position;
    }

    public int getScore() {
        return score;
    }

    public int getHealth() {
        return health;
    }
}
