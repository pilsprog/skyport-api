package skyport.api.game.weapon;

import java.util.ArrayList;
import java.util.List;

import skyport.api.game.Map;
import skyport.api.game.Point;

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

    public List<Point> inRange(Point p, Map m) {
        List<Point> inRange = new ArrayList<Point>();
        for (int j = -distance(); j <= distance(); j++) {
            for (int k = -distance(); k <= distance(); k++) {
                int newK = p.getK() + k;
                int newJ = p.getJ() + j;
                Point newPoint = new Point(newJ, newK);
                if (newK < m.getkLength() && newK >= 0 && newJ < m.getjLength() 
                        && newJ >= 0 
                        && p.distance(newPoint) <= distance()) {
                    inRange.add(newPoint);
                }
            }
        }
        return inRange;
    }
}
