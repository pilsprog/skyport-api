package skyport.api.game.weapon;

import java.util.ArrayList;
import java.util.List;

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

    public List<Point> inRange(Point p, Map m) {
        List<Point> inRange = new ArrayList<Point>();
        for (int j = 1; j <= distance(); j++) {
            int newK = p.getK();
            int newJ = p.getJ() + j;
            if (m.getData(new Point(newK, newJ)).equals(Tile.ROCK)) {
                break;
            }
            if (newK < m.getkLength() && newK >= 0 
                    && newJ < m.getjLength() && newJ >= 0) {
                inRange.add(new Point(newK, newJ));
            }
        }
        for (int k = 1; k <= distance(); k++) {
            int newK = p.getK();
            int newJ = p.getJ() + k;
            if (m.getData(new Point(newK, newJ)).equals(Tile.ROCK)) {
                break;
            }
            if (newK < m.getkLength() && newK >= 0 
                    && newJ < m.getjLength() && newJ >= 0) {
                inRange.add(new Point(newK, newJ));
            }
        }
        for (int i = 1; i <= distance(); i++) {
            int newK = p.getK() + i;
            int newJ = p.getJ() + i;
            if (m.getData(new Point(newK, newJ)).equals(Tile.ROCK)) {
                break;
            }
            if (newK < m.getkLength() && newK >= 0 
                    && newJ < m.getjLength() && newJ >= 0) {
                inRange.add(new Point(newK, newJ));
            }
        }
        for (int j = -1; j >= -distance(); j--) {
            int newK = p.getK();
            int newJ = p.getJ() + j;
            if (m.getData(new Point(newK, newJ)).equals(Tile.ROCK)) {
                break;
            }
            if (newK < m.getkLength() && newK >= 0 
                    && newJ < m.getjLength() && newJ >= 0) {
                inRange.add(new Point(newJ, newK));
            }
        }
        for (int k = -1; k >= -distance(); k--) {
            int newK = p.getK();
            int newJ = p.getJ() + k;
            if (m.getData(new Point(newK, newJ)).equals(Tile.ROCK)) {
                break;
            }
            if (newK < m.getkLength() && newK >= 0 
                    && newJ < m.getjLength() && newJ >= 0) {
                inRange.add(new Point(newK, newJ));
            }
        }
        for (int i = -1; i >= -distance(); i--) {
            int newK = p.getK() + i;
            int newJ = p.getJ() + i;
            if (m.getData(new Point(newK, newJ)).equals(Tile.ROCK)) {
                break;
            }
            if (newK < m.getkLength() && newK >= 0 
                    && newJ < m.getjLength() && newJ >= 0) {
                inRange.add(new Point(newK, newJ));
            }
        }
        return inRange;
    }
}
