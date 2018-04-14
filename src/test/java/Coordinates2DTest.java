import org.junit.Test;
import org.junit.Before;

import java.util.Optional;

import static org.junit.Assert.*;

public class Coordinates2DTest {
    //We will use these in both methods so they are instance variables
    private Coordinates2D testObj1;
    private Coordinates2D testObj2;

    @Before
    public void setUp() {
        //Set up the test circles
        testObj1 = new Coordinates2D(5, 5, 4,4);
        testObj2 = new Coordinates2D(3,3,4,4);
    }

    /*
    Tests for equals() method
     */
    @Test
    public void equalsYes() {
        Coordinates2D testObj3 = new Coordinates2D(5,5,4,4);
        boolean result = testObj1.equals(testObj3);
        assertTrue(result);
    }

    @Test
    public void equalsNo() {
        boolean result = testObj1.equals(testObj2);
        assertFalse(result);
    }

    @Test
    public void equalsDifferentClass() {
        assertNotEquals(testObj1, "Hello World");
    }

    @Test
    public void equalsNull() {
        assertNotEquals(testObj1, null);
    }

    /*
    Tests for toString method
     */
    @Test
    public void toStringNormal() {
        String s = testObj1.toString();
        assertEquals("Coordinate centered on (5.0000, 5.0000) with xErr 4.0000 and yErr 4.0000", s);
    }

    /*
    Tests for distance() method
     */
    @Test
    public void distance() {
        Optional<Double> distance = testObj1.distance(testObj2);
        double expected = 2 * Math.sqrt(2);

        assertTrue(distance.isPresent());
        assertEquals(expected, distance.get(), 0.001);
    }

    @Test
    public void distanceFlipped() {
        Optional<Double> distance = testObj2.distance(testObj1);
        double expected = 2 * Math.sqrt(2);

        assertTrue(distance.isPresent());
        assertEquals(expected, distance.get(), 0.001);
    }

    @Test
    public void distanceNullCoord() {
        Optional<Double> distance = testObj1.distance(null);
        //Distance method should return us an empty optional
        assertFalse(distance.isPresent());
    }

}