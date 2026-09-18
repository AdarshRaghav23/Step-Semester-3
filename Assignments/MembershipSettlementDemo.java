class GymMember {
    private static int memberCount = 0;
    private final String membershipNumber;
    private int feesPaid;

    public GymMember(int monthlyFee) {
        memberCount++;
        membershipNumber = "GYM-" + (2000 + memberCount);
    }

    public void payFee(int amount) {
        feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4)
            return false;

        return code.charAt(0) == 'G'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    public static int getMembersEnrolled() {
        return memberCount;
    }
}

class GroupClassMember extends GymMember {
    private String className;

    public GroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }
}

public class MembershipSettlementDemo {

    static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int skipped = 0;
        int group = 0;
        int individual = 0;

        for (GymMember m : members) {
            if (m == null) {
                skipped++;
                continue;
            }

            processed++;

            if (m instanceof GroupClassMember)
                group++;
            else
                individual++;
        }

        return processed + " processed | "
                + skipped + " null skipped | "
                + group + " group | "
                + individual + " individual";
    }

    public static void main(String[] args) {

        GymMember m1 = new GymMember(1000);

        System.out.println(
            m1.getMembershipNumber());

        System.out.println(
            GymMember.getMembersEnrolled());

        System.out.println(
            GymMember.isValidReferralCode("G45B"));

        System.out.println(
            GymMember.isValidReferralCode("G4B"));

        System.out.println(
            GymMember.isValidReferralCode("X45B"));

        m1.payFee(500);
        m1.payFee(500, "UPI");

        System.out.println(
            "Fees Paid: " + m1.getFeesPaid());

        GymMember[] members = {
            new GroupClassMember(1500, "Zumba"),
            null,
            new GymMember(1000)
        };

        System.out.println(
            processWeeklyCheckIn(members));
    }
}