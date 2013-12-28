package skyport.api.game.weapon;



import skyport.api.game.Map;
import skyport.api.game.Point;
import skyport.api.game.Tile;

public class Laser extends Weapon {
    
    public Laser(int level) {
        this.level = level;
    }
    
    public Laser() {
        this.level = 1;
    }

    public int distance() {
        return 4 + level;
    }
    
    public int damage() {
        return 16 + 2 * (level - 1);
    }


	@Override
	public ShootActionIterator iterator(Point from, Map map) {
        return new LaserShootIterator(distance(), from, map);
	}

	@Override
	public Tile getResource() {
		return Tile.RUBIDIUM;
	}
    
}
