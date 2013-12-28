package skyport.api.game.weapon;

import java.util.List;
import java.util.ArrayList;

import skyport.api.SkyportClient;
import skyport.api.game.Action;
import skyport.api.game.Map;
import skyport.api.game.Point;
import skyport.api.game.Tile;

public class MortarShootIterator implements ShootActionIterator {

    private int maxDistance;
    private Point from;
    private Map map;
    private int jOffset;
    private int kOffset;

    private Point currPoint;

    public MortarShootIterator(int maxDistance, Point from, Map map) {
        super();
        this.maxDistance = maxDistance;
        this.from = from;
        this.map = map;

        jOffset = -maxDistance;
        kOffset = -maxDistance;
    }

    public void updateOffsets() {
        Point currPoint = new Point(from.getJ() + jOffset, from.getK() + kOffset);
        do {
            if (++jOffset > maxDistance) {
                kOffset++;
                jOffset = -maxDistance;
            }
            if (!hasNext())
                break;
            currPoint = new Point(from.getJ() + jOffset, from.getK() + kOffset);
        } while (from.distance(currPoint) > maxDistance || map.getData(currPoint).equals(Tile.VOID));
    }

    @Override
    public List<Point> goingToHit() {
        List<Point> toRet = new ArrayList<Point>();
        toRet.add(currPoint);
        toRet.add(new Point(currPoint.getJ() + 1, currPoint.getK() + 1));
        toRet.add(new Point(currPoint.getJ() + 1, currPoint.getK()));
        toRet.add(new Point(currPoint.getJ(), currPoint.getK() + 1));
        toRet.add(new Point(currPoint.getJ() - 1, currPoint.getK() - 1));
        toRet.add(new Point(currPoint.getJ(), currPoint.getK() - 1));
        toRet.add(new Point(currPoint.getJ() - 1, currPoint.getK()));

        return toRet;
    }

    @Override
    public boolean hasNext() {
        return kOffset <= maxDistance;
    }

    @Override
    public Point next() {
        currPoint = new Point(from.getJ() + jOffset, from.getK() + kOffset);
        updateOffsets();
        return currPoint;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Action getCurrentAction() {
        return new MortarShootAction(currPoint.minus(from));
    }

    private class MortarShootAction implements Action {
        public Point shootAt;

        public MortarShootAction(Point shootAt) {
            super();
            this.shootAt = shootAt;
        }

        @Override
        public void perform(SkyportClient client) {
            client.fireMortar(shootAt.getJ(), shootAt.getK());

        }
    }

}
