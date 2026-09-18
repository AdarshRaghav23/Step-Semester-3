class LibraryMember {

    private static int memberCount = 0;

    private final String memberNumber;

    private int booksBorrowed = 0;
    private String lastGenre;

    public LibraryMember(int borrowLimit) {

        memberCount++;

        memberNumber =
            "LIB-" + (100 + memberCount);
    }

    public void borrowBook() {
        booksBorrowed++;
    }

    public void borrowBook(String genre) {

        lastGenre = genre;

        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public static int getMembersEnrolled() {
        return memberCount;
    }

    public static boolean isValidRenewalCode(
            String code) {

        if (code == null || code.length() != 4)
            return false;

        return code.charAt(0) == 'R'
            && Character.isDigit(code.charAt(1))
            && Character.isDigit(code.charAt(2))
            && Character.isUpperCase(code.charAt(3));
    }
}

class FacultyMember extends LibraryMember {

    private String department;

    public FacultyMember(
            int borrowLimit,
            String department) {

        super(borrowLimit);
        this.department = department;
    }
}

public class LibraryAuditDemo {

    static String processNightlyAudit(
            LibraryMember[] members) {

        int processed = 0;
        int skipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember member : members) {

            if (member == null) {
                skipped++;
                continue;
            }

            processed++;

            if (member instanceof FacultyMember)
                faculty++;
            else
                regular++;
        }

        return processed + " processed | "
             + skipped + " null skipped | "
             + faculty + " faculty | "
             + regular + " regular";
    }

    public static void main(String[] args) {

        LibraryMember m1 =
            new LibraryMember(3);

        System.out.println("=== MEMBERSHIP NUMBER ===");

        System.out.println(
            "Member Number: " +
            m1.getMemberNumber());

        System.out.println(
            "Members Enrolled: " +
            LibraryMember.getMembersEnrolled());

        System.out.println("\n=== RENEWAL CODE ===");

        System.out.println(
            "R12A: " +
            LibraryMember.isValidRenewalCode("R12A"));

        System.out.println(
            "R1A: " +
            LibraryMember.isValidRenewalCode("R1A"));

        System.out.println(
            "X12A: " +
            LibraryMember.isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");

        System.out.println("\n=== BORROWING ===");

        System.out.println(
            "Books Borrowed: " +
            m1.getBooksBorrowed());

        LibraryMember[] members = {
            new FacultyMember(5, "Physics"),
            null,
            new LibraryMember(3)
        };

        System.out.println("\n=== NIGHTLY AUDIT ===");

        System.out.println(
            processNightlyAudit(members));
    }
}