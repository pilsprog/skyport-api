package skyport.api.game;

import java.util.Iterator;
import java.util.List;

public interface ShootActionIterator extends Iterator<Point> {
	
	public Action getCurrentAction();
	public List<Point> goingToHit();

}
