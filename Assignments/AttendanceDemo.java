class GymMember {
    protected String memberId;

    public GymMember(String id) {
        memberId = id;
    }

    public void displayInfo() {
        System.out.println("Gym Member: " + memberId);
    }
}

class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(String id, String trainer) {
        super(id);
        trainerName = trainer;
    }

    @Override
    public void displayInfo() {
        System.out.println("Premium Member: " + memberId +
                           " | Trainer: " + trainerName);
    }

    public String getTrainerName() {
        return trainerName;
    }
}

public class AttendanceDemo {

    static String batchPrint(GymMember[] members) {
        StringBuilder result = new StringBuilder();

        for (GymMember m : members) {
            m.displayInfo();

            result.append(m.memberId);

            if (m instanceof PremiumMember) {
                PremiumMember p = (PremiumMember) m;
                result.append(" - Trainer: ")
                      .append(p.getTrainerName());
            }

            result.append("\n");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        GymMember g = new GymMember("GM01");
        PremiumMember p =
            new PremiumMember("PM01", "Coach Riya");

        GymMember[] members = {g, p};

        System.out.println("=== ATTENDANCE ANNOUNCEMENT ===");

        String announcement = batchPrint(members);

        System.out.println("\n=== FINAL ANNOUNCEMENT ===");
        System.out.print(announcement);
    }
}