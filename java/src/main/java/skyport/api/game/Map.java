package skyport.api.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The game map, describes the board and what is on it.
 * @author Bjarte
 */
public class Map {
    /**
     * The list of data on each point
     */
    private List<List<Tile>> data;
    
    /**
     * The length of the map in the j direction. 
     */
    private int jLength;
    /**
     * The length of the map in the k direction.
     */
    private int kLength;
    
    /**
     * @return The number of j-index values on the map.
     */
    public int getjLength() {
        return jLength;
    }
    /**
     * @return The number of k-index values on the map.
     */
	public int getkLength() {
		return kLength;
	}

	/**
	 * @param p A point on the map.
	 * @return The Tile at the given point.
	 */
	public Tile getData(Point p){
    	if(p.getJ() < 0 || p.getJ() >= jLength ||
    	   p.getK() < 0 || p.getK() >= kLength){
    		return Tile.VOID;
    	}
    	return data.get(p.getJ()).get(p.getK());
    }

    /**
     * @param p A point on the map.
     * @return A list of neighbours of the given point. 
     */
    public List<Point> neighbours(Point p) {
        List<Point> points = Arrays.asList(
                    new Point(p.getJ() + 1, p.getK() + 1), // down
                    new Point(p.getJ() + 1, p.getK()), // left-down
                    new Point(p.getJ(), p.getK() + 1), // right-down
                    new Point(p.getJ() - 1, p.getK() - 1), // up
                    new Point(p.getJ() - 1, p.getK()), // right-up
                    new Point(p.getJ(), p.getK() - 1)); // left-up

        List<Point> neighbors = new ArrayList<Point>();
        for (Point n : points) {
            Tile tile = getData(n);
            switch (tile) {
            case ROCK:
            case SPAWN:
            case VOID:
                break;
            default:
                neighbors.add(n);
            }
        }

        return neighbors;
    }
}
