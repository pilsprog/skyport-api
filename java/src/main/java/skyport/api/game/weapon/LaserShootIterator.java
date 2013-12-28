package skyport.api.game.weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import skyport.api.SkyportClient;
import skyport.api.game.Action;
import skyport.api.game.Direction;
import skyport.api.game.Map;
import skyport.api.game.Point;
import skyport.api.game.Tile;

public class LaserShootIterator implements ShootActionIterator {

    private int maxDistance;
    private Point from;
    private Map map;
    private Direction nextDirection;
    private int jOffset;
    private int kOffset;

    private Direction currDirection;
    private Point currPoint;

    public LaserShootIterator(int maxDistance, Point from, Map map) {
        super();
        this.maxDistance = maxDistance;
        this.from = from;
        this.currPoint = from;
        this.map = map;

        this.jOffset = 0;
        this.kOffset = 0;
        this.nextDirection = Direction.DOWN;

        updateNext();
    }

    @Override
    public boolean hasNext() {
        return !nextDirection.equals(Direction.NONE);
    }

    private void updateNext() {
        updateOffsets();
        if (map.getData(currPoint).equals(Tile.ROCK) || from.distance(currPoint) >= maxDistance) {
            newDirection();
            this.jOffset = 0;
            this.kOffset = 0;
            if (!hasNext()) {
                return;
            }
            updateNext();
        }
    }

    private void updateOffsets() {
        kOffset = updateKOffset(kOffset, nextDirection);
        jOffset = updateJOffset(jOffset, nextDirection);
    }

    private int updateJOffset(int jIndex, Direction dir) {
        switch (dir) {
        case DOWN:
            return ++jIndex;
        case LEFT_DOWN:
            return ++jIndex;
        case RIGHT_UP:
            return --jIndex;
        case UP:
            return --jIndex;
        default:
            return jIndex;
        }
    }

    private int updateKOffset(int kIndex, Direction dir) {
        switch (dir) {
        case DOWN:
            return ++kIndex;
        case LEFT_UP:
            return --kIndex;
        case RIGHT_DOWN:
            return ++kIndex;
        case UP:
            return --kIndex;
        default:
            return kIndex;
        }
    }

    private void newDirection() {
        switch (nextDirection) {
        case DOWN:
            nextDirection = Direction.LEFT_DOWN;
            break;
        case LEFT_DOWN:
            nextDirection = Direction.LEFT_UP;
            break;
        case LEFT_UP:
            nextDirection = Direction.RIGHT_DOWN;
            break;
        case RIGHT_DOWN:
            nextDirection = Direction.RIGHT_UP;
            break;
        case RIGHT_UP:
            nextDirection = Direction.UP;
            break;
        case UP:
            nextDirection = Direction.NONE;
            break;
        case NONE:
            assert false;
            break;
        default:
            assert false;
            break;

        }
    }

    @Override
    public Point next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        currPoint = new Point(from.getJ() + jOffset, from.getK() + kOffset);
        currDirection = nextDirection;

        updateNext();

        return currPoint;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Action getCurrentAction() {
        return new LazerShootAction(currDirection);
    }

    @Override
    public List<Point> goingToHit() {
        List<Point> toRet = new ArrayList<Point>();

        int currK = from.getK();
        int currJ = from.getJ();

        for (int i = 0; i < maxDistance; i++) {
            currK = updateKOffset(currK, currDirection);
            currJ = updateJOffset(currJ, currDirection);
            Point currPoint = new Point(currJ, currK);
            if (map.getData(currPoint).equals(Tile.ROCK)) {
                break;
            }
            toRet.add(new Point(currJ, currK));
        }
        return toRet;
    }

    private class LazerShootAction implements Action {
        public Direction shootAt;

        public LazerShootAction(Direction shootAt) {
            super();
            this.shootAt = shootAt;
        }

        @Override
        public void perform(SkyportClient client) {
            client.fireLaser(shootAt);
        }
    }
}
