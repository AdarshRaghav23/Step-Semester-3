// Question 4: The FitZone Membership Desk

enum MembershipStatus {
    ACTIVE,
    FROZEN,
    EXPIRED
}

abstract class MembershipPlan {
    private String name;
    private int months;

    public MembershipPlan(String name, int months) {
        this.name = name;
        this.months = months;
    }

    public String getName() {
        return name;
    }

    public int getMonths() {
        return months;
    }

    public abstract double calculateFee(double baseRatePerMonth);
}

class MonthlyPlan extends MembershipPlan {
    public MonthlyPlan() {
        super("Monthly", 1);
    }

    public double calculateFee(double baseRatePerMonth) {
        return getMonths() * baseRatePerMonth;
    }
}

class QuarterlyPlan extends MembershipPlan {
    public QuarterlyPlan() {
        super("Quarterly", 3);
    }

    public double calculateFee(double baseRatePerMonth) {
        double rawFee = getMonths() * baseRatePerMonth;
        return rawFee * 0.90; // 10% off
    }
}

class AnnualPlan extends MembershipPlan {
    public AnnualPlan() {
        super("Annual", 12);
    }

    public double calculateFee(double baseRatePerMonth) {
        double rawFee = getMonths() * baseRatePerMonth;
        return rawFee * 0.75; // 25% off
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Membership {
    private Member member;
    private MembershipPlan plan;
    private MembershipStatus status;
    private double fee;

    public Membership(Member member, MembershipPlan plan, double baseRate) {
        this.member = member;
        this.plan = plan;
        this.fee = plan.calculateFee(baseRate);
        this.status = MembershipStatus.ACTIVE;

        System.out.printf("%s membership created for %s. Fee: ₹%,.2f. Status: Active.\n",
                plan.getName(), member.getName(), fee);
    }

    public Member getMember() {
        return member;
    }

    public MembershipStatus getStatus() {
        return status;
    }

    public boolean checkIn() {
        if (this.status == MembershipStatus.ACTIVE) {
            System.out.println(member.getName() + " checked in successfully.");
            return true;
        } else {
            System.out.println("Check-in denied: " + member.getName() + "'s membership is " + status + ".");
            return false;
        }
    }

    public boolean freeze() {
        if (this.status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
            return false;
        }
        this.status = MembershipStatus.FROZEN;
        System.out.println(member.getName() + "'s membership frozen. Status: Frozen.");
        return true;
    }

    public boolean unfreeze() {
        if (this.status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
            return false;
        }
        this.status = MembershipStatus.ACTIVE;
        System.out.println(member.getName() + "'s membership unfrozen. Status: Active.");
        return true;
    }

    public void expire() {
        this.status = MembershipStatus.EXPIRED;
        System.out.println(member.getName() + "'s membership expired. Status: Expired.");
    }
}

public class Problem4_FitZoneDesk {
    public static void main(String[] args) {
        double BASE_RATE = 1000.0;

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        // Asha buys a Quarterly membership
        Membership ashaMem = new Membership(asha, new QuarterlyPlan(), BASE_RATE);

        // Ravi buys a Monthly membership
        Membership raviMem = new Membership(ravi, new MonthlyPlan(), BASE_RATE);

        // Asha checks in
        ashaMem.checkIn();

        // Asha freezes her membership
        ashaMem.freeze();

        // Asha attempts to check in
        ashaMem.checkIn();

        // Ravi's membership expires
        raviMem.expire();

        // Ravi attempts to freeze his membership
        raviMem.freeze();
    }
}