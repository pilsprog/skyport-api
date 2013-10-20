package skyport.api.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PointTest {
    
    private Point p = new Point(1,1);
    
    
    @Test
    public void testZeroTwoDistance() {
        assertEquals("failure - the distance between the points should be two.", 2, p.distance(new Point(0,2)));
    }
    
    @Test
    public void voidTestZeroOneDistance() {
        assertEquals("failure - distance should have been one.", 1, p.distance(new Point(0,1)));
    }
    
    @Test 
    public void voidTestZeroThreeDistance() {
        assertEquals("failure - distance should have been three.", 3, p.distance(new Point(0,3)));
    }
}
