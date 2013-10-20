package skyport.api.game;

/**
 * 
 * Represents a point on the game board. The position of the point is described
 * by two numbers k and j. The numbers represents the cross lateral index
 * (lookup skyport documentation for further information).
 * 
 * @author Bjarte
 * 
 */
public class Point implements Comparable<Point> {

    private final int j;
    private final int k;

    /**
     * Constructor.
     * 
     * @param k
     *            The k-index of the point.
     * @param j
     *            The j-index of the point.
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
     * @param point
     *            A point on the map.
     * @return The distance (in number of moves) between this point and the
     *         given point.
     */
    public int distance(Point point) {
       double updown = Math.abs(point.j - j);
       double diagonal = Math.abs(point.k - k);
       double leftright = Math.abs((point.j - point.k)*-1 - (j - k)*-1);
       return (int)Math.max(updown, Math.max(diagonal, leftright));
    }

    public Direction direction(Point p) {
        int i = (int) (Math.atan2(j - p.getJ(), k - p.getK()) * 180 / Math.PI);
        switch (i) {
        case -135:
            return Direction.down;
        case -90:
            return Direction.leftDown;
        case 180:
            return Direction.rightDown;
        case 45:
            return Direction.up;
        case 90:
            return Direction.rightUp;
        case 0:
            return Direction.leftUp;
        default:
            return null;
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Point) {
            Point other = (Point) obj;
            return j == other.j && k == other.k;
        }
        return false;
    }

    @Override
    public int compareTo(Point o) {
        if (o.getJ() != j) {
            return o.getJ() - j;
        } else if (o.getK() != k) {
            return o.getK() - k;
        }
        return 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + j;
        result = prime * result + k;
        return result;
    }
}
