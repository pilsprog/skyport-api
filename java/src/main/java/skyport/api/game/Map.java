package skyport.api.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {
    private List<List<String>> data;
    private int jLength;
    private int kLength;

    public List<Point> neighbors(Point p) {
        List<Point> points = Arrays.asList(new Point(p.getK() + 1, p.getJ() + 1), new Point(p.getK() + 1, p.getJ()), new Point(p.getK(), p.getJ() + 1), new Point(p.getK() - 1, p.getJ() - 1), new Point(p.getK() - 1, p.getJ()), new Point(p.getK(), p.getJ() - 1));

        List<Point> neighbors = new ArrayList<Point>();
        for (Point n : points) {
            int k = n.getK();
            int j = n.getJ();
            String tile = data.get(k).get(j);
            switch (tile) {
            case "R": // Rock
            case "S": // Spawn
            case "V": // Void
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
