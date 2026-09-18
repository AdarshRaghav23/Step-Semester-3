import java.util.Arrays;

class LibraryMember {

    private int[] fineHistory = new int[10];
    private int fineCount = 0;

    protected void chargeFine(int amount) {

        if (fineCount < 10) {
            fineHistory[fineCount] = amount;
            fineCount++;
        }
    }

    public int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {

        int total = 0;

        for (int i = 0; i < fineCount; i++)
            total += fineHistory[i];

        return total;
    }
}

class StudentMember extends LibraryMember {

    @Override
    public void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}

public class LibraryFineDemo {

    public static void main(String[] args) {

        StudentMember s =
            new StudentMember();

        s.chargeFine(100);

        System.out.println("=== FINE DETAILS ===");
        System.out.println("Original Fine: 100");
        System.out.println("Student Discounted Fine: 50");

        System.out.println(
            "Fine History: " +
            Arrays.toString(s.getFineHistory()));

        System.out.println(
            "Total Fine: " +
            s.getTotalFine());

        int[] history = s.getFineHistory();

        history[0] = 999;

        System.out.println(
            "After Modifying Returned Array: " +
            Arrays.toString(s.getFineHistory()));
    }
}