package skyport.api.game.weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import skyport.api.SkyportClient;
import skyport.api.game.Action;
import skyport.api.game.BFSIterator;
import skyport.api.game.Direction;
import skyport.api.game.Map;
import skyport.api.game.Point;

public class DroidShootIterator extends BFSIterator implements ShootActionIterator {

    private int maxDistance;

    public DroidShootIterator(int maxDistance, Point from, Map map) {
        super(map, from);
        this.maxDistance = maxDistance;
    }

    @Override
    public boolean hasNext() {
        return super.hasNext() && super.getNextDistance() <= maxDistance;
    }

    @Override
    public Point next() {
        if (!hasNext())
            throw new NoSuchElementException();
        return super.next();
    }

    @Override
    public Action getCurrentAction() {
        return new DroidShootAction(super.getPath(current));
    }

    @Override
    public List<Point> goingToHit() {
        Point shootAt = current;
        List<Point> toRet = new ArrayList<Point>();
        toRet.add(shootAt);
        toRet.add(new Point(shootAt.getJ() + 1, shootAt.getK() + 1));
        toRet.add(new Point(shootAt.getJ() + 1, shootAt.getK()));
        toRet.add(new Point(shootAt.getJ(), shootAt.getK() + 1));
        toRet.add(new Point(shootAt.getJ() - 1, shootAt.getK() - 1));
        toRet.add(new Point(shootAt.getJ(), shootAt.getK() - 1));
        toRet.add(new Point(shootAt.getJ() - 1, shootAt.getK()));
        return toRet;
    }

    private class DroidShootAction implements Action {
        public List<Direction> shootAt;

        public DroidShootAction(List<Direction> shootAt) {
            super();
            this.shootAt = shootAt;
        }

        @Override
        public void perform(SkyportClient client) {
            client.fireDroid(shootAt);

        }
    }

}
