import java.util.Arrays;

class GymMember {
    private int[] lateFeeHistory = new int[10];
    private int feeCount = 0;

    public void chargeLateFee(int amount) {
        if (amount > 0 && feeCount < 10)
            lateFeeHistory[feeCount++] = amount;
    }

    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }

    public int getTotalLateFees() {
        int total = 0;
        for (int i = 0; i < feeCount; i++)
            total += lateFeeHistory[i];
        return total;
    }
}

class PremiumMember extends GymMember {
    @Override
    public void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}

public class LateFeeDemo {
    public static void main(String[] args) {

        PremiumMember p = new PremiumMember();

        p.chargeLateFee(200);
        p.chargeLateFee(100);

        System.out.println("=== LATE FEE LEDGER ===");
        System.out.println("Original Fee 1: 200");
        System.out.println("Premium Fee 1: 100");

        System.out.println("Original Fee 2: 100");
        System.out.println("Premium Fee 2: 50");

        System.out.println("Fee History: " +
                Arrays.toString(p.getLateFeeHistory()));

        System.out.println("Total Late Fees: " +
                p.getTotalLateFees());

        int[] history = p.getLateFeeHistory();
        history[0] = 9999;

        System.out.println("After Changing Copy: " +
                Arrays.toString(p.getLateFeeHistory()));
    }
}