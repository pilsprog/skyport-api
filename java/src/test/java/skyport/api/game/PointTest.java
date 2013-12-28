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
        assertEquals(Direction.up, p.direction(new Point(0, 0)));
    }

    @Test
    public void testDirectionDown() {
        assertEquals(Direction.down, p.direction(new Point(2, 2)));
    }

    @Test
    public void testDirectionRightUp() {
        assertEquals(Direction.rightUp, p.direction(new Point(0, 1)));
    }

    @Test
    public void testDirectionRightDown() {
        assertEquals(Direction.rightDown, p.direction(new Point(1, 2)));
    }

    @Test
    public void testDirectionLeftUp() {
        assertEquals(Direction.leftUp, p.direction(new Point(1, 0)));
    }

    @Test
    public void testDirectionLeftDown() {
        assertEquals(Direction.leftDown, p.direction(new Point(2, 1)));
    }

    @Test
    public void testDirectionFail() {
        assertEquals(Direction.none, p.direction(new Point(0, 2)));
    }
}
