// Problem 4: Digital Classroom Setup

abstract class ClassroomDevice {
    public abstract String operate();
}

interface Chargeable {
    String charge();
    String charge(int minutes);
}

class Tablet extends ClassroomDevice implements Chargeable {
    private String assetTag;

    public Tablet(String assetTag) {
        this.assetTag = assetTag;
    }

    public String operate() {
        return "Tablet " + assetTag + " displaying lesson";
    }

    public String charge() {
        return assetTag + " charging";
    }

    public String charge(int minutes) {
        return assetTag + " charging for " + minutes + " minutes";
    }
}

public class Problem4_DigitalClassroom {
    public static void main(String[] args) {
        Tablet t = new Tablet("TAB-5");
        
        System.out.println(t.operate());
        System.out.println(t.charge());
        System.out.println(t.charge(30));
    }
}