import org.junit.Test;

import static org.junit.Assert.*;
public class BeaconTest {
    @Test
    public void normalToString() {
        Beacon b = new Beacon("uuid-1", 1,2,13.3);
        String bString = b.toString();
        assertEquals("Beacon<proximityUUID: uuid-1, major: 1, minor: 2, accuracy: 13.300>", bString);
    }
}
