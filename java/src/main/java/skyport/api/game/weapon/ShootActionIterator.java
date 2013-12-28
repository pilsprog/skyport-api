package skyport.api.game.weapon;

import java.util.Iterator;
import java.util.List;

import skyport.api.game.Action;
import skyport.api.game.Point;

public interface ShootActionIterator extends Iterator<Point> {

    public Action getCurrentAction();

    public List<Point> goingToHit();

}
