package skyport.api.game.weapon;

import skyport.api.game.Map;
import skyport.api.game.Point;
import skyport.api.game.Tile;

public class Mortar extends Weapon {

    public Mortar(int level) {
        this.level = level;
    }
    
    public Mortar() {
        this.level = 1;
    }

    public int distance() {
        return 1 + level;
    }
    
    public int damage() {
        return level == 3 ? 25 : 20;
    }


	@Override
	public ShootActionIterator iterator(Point from, Map map) {
        return new MortarShootIterator(distance(), from, map);
	}

	@Override
	public Tile getResource() {
		return Tile.EXPLOSIUM;
	}
}
