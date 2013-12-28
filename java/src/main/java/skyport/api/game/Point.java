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
        double leftright = Math.abs((point.j - point.k) * -1 - (j - k) * -1);
        return (int) Math.max(updown, Math.max(diagonal, leftright));
    }

    /**
     * Calculates the direction to a point from the point.
     * 
     * @param p
     *            The point to calculate the direction to.
     * @return The direction the point is in.
     */
    public Direction direction(Point p) {
        int i = (int) (Math.atan2(j - p.getJ(), k - p.getK()) * 180 / Math.PI);
        switch (i) {
        case -135:
            return Direction.DOWN;
        case -90:
            return Direction.LEFT_DOWN;
        case 180:
            return Direction.RIGHT_DOWN;
        case 45:
            return Direction.UP;
        case 90:
            return Direction.RIGHT_UP;
        case 0:
            return Direction.LEFT_UP;
        default:
            return Direction.NONE;
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
    public String toString() {
        return "Point [j=" + j + ", k=" + k + "]";
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

    public Point minus(Point p) {
        return new Point(this.j - p.j, this.k - p.k);
    }

    public Point adjacent(Direction dir) {
        switch (dir) {
        case DOWN:
            return new Point(j + 1, k + 1);
        case LEFT_DOWN:
            return new Point(j + 1, k);
        case LEFT_UP:
            return new Point(j, k - 1);
        case NONE:
            return null;
        case RIGHT_DOWN:
            return new Point(j, k + 1);
        case RIGHT_UP:
            return new Point(j - 1, k);
        case UP:
            return new Point(j - 1, k - 1);
        default:
            return null;

        }

    }
}
