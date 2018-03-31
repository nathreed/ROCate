import static org.junit.jupiter.api.Assertions.*;

class Coordinates2DTest {
    //We will use these in both methods so they are instance variables
    private Coordinates2D testObj1;
    private Coordinates2D testObj2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        //Set up the test circles
        testObj1 = new Coordinates2D(5, 5, 4,4);
        testObj2 = new Coordinates2D(3,3,4,4);
    }

    @org.junit.jupiter.api.Test
    void distance() {
        double distance = testObj1.distance(testObj2);
        double expected = 2 * Math.sqrt(2);
        assertEquals(expected, distance, 0.001);
    }

    @org.junit.jupiter.api.Test
    void circleIntersectionIntersects() throws Exception {
        //testObj1 and testObj2 should intersect
        //Test that they do
        Coordinates2D intersection = testObj1.circleIntersection(testObj2);

        assertEquals(4.0, intersection.getX(), 0.001);
        assertEquals(4.0, intersection.getY(), 0.001);

    }

    @org.junit.jupiter.api.Test
    void circleIntersectionNoIntersects() {
        //Test another case, where the circles do not intersect
        Coordinates2D testObj3 = new Coordinates2D(50,50,1,2);
        Coordinates2D intersectionResult = testObj2.circleIntersection(testObj3);
        
        assertEquals(Double.NaN, intersectionResult.getX());
        assertEquals(Double.NaN, intersectionResult.getY());
    }

}