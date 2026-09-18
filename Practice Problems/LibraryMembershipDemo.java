class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(String id, int limit) {
        memberId = id;
        borrowLimit = limit;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit)
            booksBorrowed++;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {
        System.out.println("General Member | Books Borrowed: "
                           + booksBorrowed);
    }
}

class StudentMember extends LibraryMember {
    protected String course;

    public StudentMember(String id, int limit, String course) {
        super(id, limit);
        this.course = course;
    }

    public void displayInfo() {
        System.out.println("Student Member | Course: " +
                           course + " | Books Borrowed: " +
                           booksBorrowed);
    }
}

class HonorsStudentMember extends StudentMember {
    private int bonusLimit;

    public HonorsStudentMember(String id, int limit,
                               String course, int bonus) {
        super(id, limit, course);
        bonusLimit = bonus;
    }

    public void displayInfo() {
        System.out.println("Honors Student Member | Course: " +
                           course + " | Bonus Limit: " +
                           bonusLimit + " | Books Borrowed: " +
                           booksBorrowed);
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(String id, int limit, String dept) {
        super(id, limit);
        department = dept;
    }

    public void displayInfo() {
        System.out.println("Faculty Member | Department: " +
                           department + " | Books Borrowed: " +
                           booksBorrowed);
    }
}

public class LibraryMembershipDemo {

    static String classifyGeneration(LibraryMember m) {
        if (m instanceof HonorsStudentMember)
            return "Multilevel descendant (3 generations deep)";

        if (m instanceof FacultyMember)
            return "Hierarchical sibling (independent branch)";

        return "General/Student Member";
    }

    static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;

        for (LibraryMember m : members)
            total += m.getBooksBorrowed();

        return total;
    }

    public static void main(String[] args) {

        LibraryMember g =
            new LibraryMember("GEN1", 5);

        StudentMember s =
            new StudentMember("STU2", 5, "CSE");

        HonorsStudentMember h =
            new HonorsStudentMember("STU3", 5, "ECE", 2);

        FacultyMember f =
            new FacultyMember("FAC4", 5, "Physics");

        s.borrowBook();
        s.borrowBook();

        h.borrowBook();

        f.borrowBook();
        f.borrowBook();
        f.borrowBook();

        System.out.println("=== MEMBER DETAILS ===");
        g.displayInfo();
        s.displayInfo();
        h.displayInfo();
        f.displayInfo();

        System.out.println("\n=== CLASSIFICATION ===");
        System.out.println("Honors: " +
                           classifyGeneration(h));
        System.out.println("Faculty: " +
                           classifyGeneration(f));

        LibraryMember[] members = {s, h, f};

        System.out.println("\n=== TOTAL ===");
        System.out.println("Total Books Borrowed: " +
                           getTotalBooksBorrowed(members));
    }
}