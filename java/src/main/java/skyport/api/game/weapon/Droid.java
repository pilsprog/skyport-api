package skyport.api.game.weapon;

import java.util.ArrayList;
import java.util.List;

import skyport.api.game.Map;
import skyport.api.game.Point;

public class Droid extends Weapon {
    
    public Droid(int level) {
        this.level = level;
    }
    
    public Droid() {
        this.level = 1;
    }

    public int distance() {
        return 2 + level;
    }
    
    public int damage() {
        return 20 + 2 * level;
    }

    public List<Point> inRange(Point p, Map m) {
        List<Point> inRange = new ArrayList<Point>();
        for (int j = -distance(); j <= distance(); j++) {
            for (int k = -distance(); k <= distance(); k++) {
                int newK = p.getK() + k;
                int newJ = p.getJ() + j;
                if (newK < m.getkLength() && newK >= 0
                        && newJ < m.getjLength() && newJ >= 0) {
                    inRange.add(new Point(newJ, newK));
                }
            }
        }
        return inRange;
    }
}
