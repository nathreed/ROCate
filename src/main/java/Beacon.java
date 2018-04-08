/*
This class is used for serializing JSON data received from the client
 */
public class Beacon {
    private String proximityUUID;
    private int major;
    private int minor;
    double accuracy;

    @Override
    public String toString() {
        return String.format("Beacon<proximityUUID: %s, major: %d, minor: %d, accuracy: %.3f>", proximityUUID, major, minor, accuracy);
    }

    public Beacon(String proximityUUID, int major, int minor, double accuracy) {
        this.proximityUUID = proximityUUID;
        this.major = major;
        this.minor = minor;
        this.accuracy = accuracy;
    }
}
