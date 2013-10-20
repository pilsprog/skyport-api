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
        assertEquals(2, p.distance(new Point(0,2)));
    }
    
    @Test
    public void voidTestZeroOneDistance() {
        assertEquals(1, p.distance(new Point(0,1)));
    }
    
    @Test 
    public void voidTestZeroThreeDistance() {
        assertEquals(3, p.distance(new Point(0,3)));
    }
}
