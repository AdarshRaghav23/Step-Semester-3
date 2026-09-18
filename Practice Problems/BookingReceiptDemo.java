import java.util.Arrays;

final class BookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = seatNumbers.clone();
    }

    public String[] getSeatNumbers() {
        return seatNumbers.clone();
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] copy = seatNumbers.clone();
        copy[index] = newSeat;

        return new BookingReceipt(bookingId, copy);
    }
}

class GroupBookingReceipt extends BookingReceipt {
    private int groupSize;

    public GroupBookingReceipt(String bookingId,
                               String[] seatNumbers,
                               int groupSize) {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }
}

public class BookingReceiptDemo {

    static String processNightlySettlement(
            BookingReceipt[] receipts) {

        int processed = 0;
        int skipped = 0;
        int group = 0;
        int individual = 0;

        for (BookingReceipt r : receipts) {
            if (r == null) {
                skipped++;
                continue;
            }

            processed++;

            if (r instanceof GroupBookingReceipt)
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

        BookingReceipt b = new BookingReceipt(
            "CH-1001",
            new String[]{"A1", "A2"}
        );

        String[] seats = b.getSeatNumbers();
        seats[0] = "X";

        System.out.println(Arrays.toString(b.getSeatNumbers()));

        BookingReceipt updated =
            b.withUpdatedSeat(1, "A3");

        System.out.println(Arrays.toString(b.getSeatNumbers()));
        System.out.println(Arrays.toString(updated.getSeatNumbers()));

        BookingReceipt[] receipts = {
            new GroupBookingReceipt(
                "CH-2002",
                new String[]{"B1", "B2"},
                2
            ),
            null,
            new BookingReceipt(
                "CH-3003",
                new String[]{"C1"}
            )
        };

        System.out.println(
            processNightlySettlement(receipts)
        );
    }
}