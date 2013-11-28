package skyport.api.game;

/**
 * 
 * A weapon in skyport.
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
    
    public Tile getResource(){
        switch(name) {
        case MORTAR:
            return Tile.EXPLOSIUM;
        case LASER:
            return Tile.RUBIDIUM;
        case DROID:
            return Tile.SCRAP;
        default:
        	assert false;
        	return null;
        }
    }

    /**
     * Returns the maximum distance the weapon will hit something.
     * @return The distance of the weapon.
     */
    public int distance() {
        switch(name) {
        case MORTAR:
            return 1 + level;
        case LASER:
            return 4 + level;
        case DROID:
            return 2 + level;
        }
        return 0;
    }
    
    public ShootActionIterator iterator(Point from, Map map){
        switch(name) {
        case MORTAR:
            return new MortarShootIterator(distance(), from, map);
        case LASER:
            return new LazerShootIterator(distance(), from, map);
        case DROID:
            return new DroidShootIterator(distance(), from, map);
        default:
        	assert false;
        	return null;
        }
    }

    /**
     * @return The weapons level.
     */
    public int getLevel() {
        return level;
    }
}
