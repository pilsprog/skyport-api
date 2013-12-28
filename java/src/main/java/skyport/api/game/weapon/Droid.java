package skyport.api.game.weapon;

import skyport.api.game.Map;
import skyport.api.game.Point;
import skyport.api.game.Tile;

public class Droid extends Weapon {

    public Droid(int level) {
        this.level = level;
    }

    public Droid() {
        this.level = 1;
    }

    @Override
    public int distance() {
        return 2 + level;
    }

    @Override
    public int damage() {
        return 20 + 2 * level;
    }

    @Override
    public ShootActionIterator iterator(Point from, Map map) {
        return new DroidShootIterator(distance(), from, map);
    }

    @Override
    public Tile getResource() {
        return Tile.SCRAP;
    }

}
