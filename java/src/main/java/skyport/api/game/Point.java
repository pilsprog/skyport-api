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
public class Point implements Comparable<Point> {
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + j;
		result = prime * result + k;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (j != other.j)
			return false;
		if (k != other.k)
			return false;
		return true;
	}

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

	@Override
	public int compareTo(Point o) {
		if(o.getJ() != j){
			return o.getJ() - j;
		} else if(o.getK() != k){
			return o.getK() - k;
		}
		return 0;
	}
    
    public Direction direction(Point p) {
        int i = (int)(Math.atan2(j-p.getJ(), k-p.getK())*180/Math.PI);
        switch(i) {
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
    
    
    public String string() {
        return j + ":" + k;
        
    }
}
