import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LocationFinderTest {
    //We will use these in both methods so they are instance variables
    private Coordinates2D testObj1;
    private Coordinates2D testObj2;
    private LocationFinder locationFinder;


    @Before
    public void setUp() {
        //Set up the test circles
        testObj1 = new Coordinates2D(5, 5, 4,4);
        testObj2 = new Coordinates2D(3,3,4,4);
        locationFinder = new LocationFinder();

    }


    /*
    Tests for circleIntersection() method
     */
    @Test
    public void circleIntersectionIntersects() {
        //testObj1 and testObj2 should intersect
        //Test that they do
        Optional<Coordinates2D> intersection = locationFinder.circleIntersection(testObj1, testObj2);

        assertTrue(intersection.isPresent());
        assertEquals(new Coordinates2D(4.0,4.0,0,0), intersection.get());

    }

    @Test
    public void circleIntersectionNoIntersects() {
        //Test another case, where the circles do not intersect
        Coordinates2D testObj3 = new Coordinates2D(50,50,1,2);
        Optional<Coordinates2D> intersectionResult = locationFinder.circleIntersection(testObj2, testObj3);

        //There should be no intersection
        assertFalse(intersectionResult.isPresent());
    }

    @Test
    public void circleIntersectionNullCircle() {
        Optional<Coordinates2D> intersection = locationFinder.circleIntersection(testObj1, null);
        assertFalse(intersection.isPresent());
    }

    /*
    Test the multiple intersections method
     */
    @Test
    public void multipleIntersections() {
        /*
        pos: (5,-6), (13,-15) (21,-3)
        dist: (8.06, 13.97, 23.32)
        intersection: (-0.6, -11.8) with err 0.75, 1.33
         */

        Coordinates2D[] coords = {new Coordinates2D(5,-6,8.06,8.06), new Coordinates2D(13,-15, 13.97, 13.97), new Coordinates2D(21,-3, 23.32, 23.32)};
        Coordinates2D intersection = locationFinder.multipleIntersection(coords);
        Coordinates2D expected = new Coordinates2D(-0.6, -11.8, 0.75,1.33);
        //We have to compare expected and actual values manually instead of using equals() method because that checks for exact equality, which this won't give us
        assertEquals(expected.getX(), intersection.getX(), 0.01);
        assertEquals(expected.getY(), intersection.getY(), 0.01);
        assertEquals(expected.getXErr(), intersection.getXErr(), 0.01);
        assertEquals(expected.getYErr(), intersection.getYErr(), 0.01);
    }

}
