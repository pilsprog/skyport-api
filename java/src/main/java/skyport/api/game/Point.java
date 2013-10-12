package skyport.api.game;

/**
 * 
 * Represents a point on the game board. The
 * position of the point is described by two numbers
 * k and j. The numbers represents the cross lateral
 * index (lookup skyport documentation for further 
 * information).
 * @author Bjarte
 *
 */
public class Point {
    private final int j;
    private final int k;

    
    /**
     * Constructor.
     * @param k The k-index of the point.
     * @param j The j-index of the point.
     */
    public Point(int j, int k) {
        this.j = j;
        this.k = k;
    }

    /**
     * @return The k-index of the point.
     */
    public int getK() {
        return k;
    }

    /**
     * @return The j-index of the point.
     */
    public int getJ() {
        return j;
    }

    /**
     * @param point A point on the map.
     * @return The distance (in number of moves) between this point
     * and the given point.
     */
    public int distance(Point point) {
        int dj = Math.abs(j - point.j);
        int dk = Math.abs(k - point.k);

        int distance = 0;
        if (dj < dk / 2) {
            distance = dk;
        } else {
            distance = (int) (dk + dj - Math.floor(dk / 2.0));
        }

        if (k % 2 == 0) {
            if (dk % 2 == 1 && j > point.j) {
                distance--;
            }
        } else if (dk % 2 == 1 && j < point.j) {
            distance--;
        }

        return distance;
    }
}
