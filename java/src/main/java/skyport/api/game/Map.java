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
    	if(p.getJ() < 0 || p.getJ() >= kLength ||
    	   p.getK() < 0 || p.getK() >= jLength){
    		return Tile.VOID;
    	}
    	return data.get(p.getJ()).get(p.getK());
    }

    /**
     * @param p A point on the map.
     * @return A list of neighbors of the given point. 
     */
    public List<Point> neighbors(Point p) {
        List<Point> points = Arrays.asList(new Point(p.getK() + 1, p.getJ() + 1), new Point(p.getK() + 1, p.getJ()), new Point(p.getK(), p.getJ() + 1), new Point(p.getK() - 1, p.getJ() - 1), new Point(p.getK() - 1, p.getJ()), new Point(p.getK(), p.getJ() - 1));

        List<Point> neighbors = new ArrayList<Point>();
        for (Point n : points) {
            int k = n.getK();
            int j = n.getJ();
            Tile tile = data.get(k).get(j);
            switch (tile) {
            case ROCK:
            case SPAWN:
            case VOID:
                break;
            default:
                if (k < kLength && j < jLength) {
                    neighbors.add(n);
                }
            }
        }

        return neighbors;
    }
}
