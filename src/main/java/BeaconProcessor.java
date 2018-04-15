import java.util.HashMap;
import java.util.Optional;

public class BeaconProcessor {
    private Beacon[] beacons;
    private HashMap<Beacon, Coordinates2D> beaconLocations;

    public BeaconProcessor(Beacon[] beacons) {
        this.beacons = beacons;
        //update the beacon locations
        fetchBeaconLocations();
    }

    /**
     * Fetches the beacon locations from the database and puts them in the beacon locations map.
     */
    private void fetchBeaconLocations() {

    }

    public Optional<Coordinates2D> position() {
        LocationFinder finder = new LocationFinder();
        if(this.beacons.length < 2) {
            //no position
            return Optional.empty();
        } else if(this.beacons.length == 2) {
            //circle intersection
        } else {
            //multiple intersection
        }

        //todo remove
        return Optional.empty();
    }
}
