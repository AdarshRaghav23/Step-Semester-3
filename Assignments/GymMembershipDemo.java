class GymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.println("Gym Member: " + memberId);
    }
}

class PremiumMember extends GymMember {
    String trainerName;

    public PremiumMember(String id, int fee, String trainer) {
        super(id, fee);
        trainerName = trainer;
    }

    @Override
    public void displayInfo() {
        System.out.println("Premium Member: " + memberId +
                           " | Trainer: " + trainerName);
    }
}

class EliteMember extends PremiumMember {
    public EliteMember(String id, int fee, String trainer) {
        super(id, fee, trainer);
    }

    @Override
    public void displayInfo() {
        System.out.println("Elite Member: " + memberId +
                           " | Trainer: " + trainerName);
    }
}

class GroupClassMember extends GymMember {
    String className;

    public GroupClassMember(String id, int fee, String className) {
        super(id, fee);
        this.className = className;
    }

    @Override
    public void displayInfo() {
        System.out.println("Group Class Member: " + memberId +
                           " | Class: " + className);
    }
}

public class GymMembershipDemo {

    static String classifyGeneration(GymMember m) {
        if (m instanceof EliteMember)
            return "Multilevel descendant (3 generations deep)";
        if (m instanceof GroupClassMember)
            return "Hierarchical sibling (independent branch)";
        return "Base/Direct Member";
    }

    static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;

        for (GymMember m : members)
            total += m.getSessionsAttended();

        return total;
    }

    public static void main(String[] args) {

        GymMember g = new GymMember("GM01", 1000);
        PremiumMember p = new PremiumMember("PM01", 1500, "Coach Riya");
        EliteMember e = new EliteMember("EM01", 2500, "Coach Arjun");
        GroupClassMember c =
            new GroupClassMember("GC01", 1200, "Zumba");

        // Attend sessions
        g.attendSession();
        p.attendSession();
        p.attendSession();
        e.attendSession();
        e.attendSession();
        e.attendSession();
        c.attendSession();
        c.attendSession();
        c.attendSession();
        c.attendSession();

        System.out.println("=== MEMBER DETAILS ===");
        g.displayInfo();
        p.displayInfo();
        e.displayInfo();
        c.displayInfo();

        System.out.println("\n=== INHERITANCE CLASSIFICATION ===");
        System.out.println("EliteMember: " + classifyGeneration(e));
        System.out.println("GroupClassMember: " + classifyGeneration(c));

        GymMember[] members = {g, p, e, c};

        System.out.println("\n=== SESSION SUMMARY ===");
        System.out.println("GymMember Sessions: " + g.getSessionsAttended());
        System.out.println("PremiumMember Sessions: " + p.getSessionsAttended());
        System.out.println("EliteMember Sessions: " + e.getSessionsAttended());
        System.out.println("GroupClassMember Sessions: " + c.getSessionsAttended());
        System.out.println("Total Sessions: " +
                           getTotalSessionsAttended(members));
    }
}