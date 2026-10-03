// Problem 5: Skyline Delivery Fleet

abstract class Drone {
    private String id;

    public Drone(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract String fly();
}

interface Trackable {
    String getLocation();
}

class DeliveryDrone extends Drone implements Trackable {
    public DeliveryDrone(String id) {
        super(id);
    }

    public String fly() {
        return "Delivery drone " + getId() + " flying to destination";
    }

    public String getLocation() {
        return getId() + " at Sector 4";
    }
}

class ScoutDrone extends Drone {
    public ScoutDrone(String id) {
        super(id);
    }

    public String fly() {
        return "Scout drone " + getId() + " scouting perimeter";
    }
}

class GroundRobot implements Trackable {
    private String id;

    public GroundRobot(String id) {
        this.id = id;
    }

    public String getLocation() {
        return id + " at Sector 4";
    }
}

public class Problem5_DeliveryFleet {
    public static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            Trackable t = (Trackable) o;
            return t.getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        DeliveryDrone d = new DeliveryDrone("DR-1");
        System.out.println(getLocationIfTrackable(d));

        ScoutDrone s = new ScoutDrone("SC-1");
        System.out.println(getLocationIfTrackable(s));

        GroundRobot g = new GroundRobot("GR-1");
        System.out.println(getLocationIfTrackable(g));
    }
}