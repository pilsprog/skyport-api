package skyport.api.game;

public class Point {
    private final int k;
    private final int j;

    public Point(int k, int j) {
        this.k = k;
        this.j = j;
    }

    public int getK() {
        return k;
    }

    public int getJ() {
        return j;
    }

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
