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
    private final int k;
    private final int j;

    
    /**
     * Constructor.
     * @param k The k-index of the point.
     * @param j The j-index of the point.
     */
    public Point(int k, int j) {
        this.k = k;
        this.j = j;
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
        int dk = Math.abs(k - point.k);
        int dj = Math.abs(j - point.j);

        int distance = 0;
        if (dk < dj / 2) {
            distance = dj;
        } else {
            distance = (int) (dj + dk - Math.floor(dj / 2.0));
        }

        if (j % 2 == 0) {
            if (dj % 2 == 1 && k > point.k) {
                distance--;
            }
        } else if (dj % 2 == 1 && k < point.k) {
            distance--;
        }

        return distance;
    }
}
