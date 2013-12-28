package skyport.api.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PointTest {

    private Point p = new Point(1, 1);

    @Test
    public void testZeroTwoDistance() {
        assertEquals(2, p.distance(new Point(0, 2)));
    }

    @Test
    public void testZeroOneDistance() {
        assertEquals(1, p.distance(new Point(0, 1)));
    }

    @Test
    public void testZeroThreeDistance() {
        assertEquals(3, p.distance(new Point(0, 3)));
    }

    @Test
    public void testDirectionUp() {
        assertEquals(Direction.UP, p.direction(new Point(0, 0)));
    }

    @Test
    public void testDirectionDown() {
        assertEquals(Direction.DOWN, p.direction(new Point(2, 2)));
    }

    @Test
    public void testDirectionRightUp() {
        assertEquals(Direction.RIGHT_UP, p.direction(new Point(0, 1)));
    }

    @Test
    public void testDirectionRightDown() {
        assertEquals(Direction.RIGHT_DOWN, p.direction(new Point(1, 2)));
    }

    @Test
    public void testDirectionLeftUp() {
        assertEquals(Direction.LEFT_UP, p.direction(new Point(1, 0)));
    }

    @Test
    public void testDirectionLeftDown() {
        assertEquals(Direction.LEFT_DOWN, p.direction(new Point(2, 1)));
    }

    @Test
    public void testDirectionFail() {
        assertEquals(Direction.NONE, p.direction(new Point(0, 2)));
    }
}
