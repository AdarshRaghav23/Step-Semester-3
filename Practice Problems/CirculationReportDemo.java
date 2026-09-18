class LibraryMember {

    protected String memberId;
    protected int booksBorrowed;

    public LibraryMember(String memberId) {
        this.memberId = memberId;
    }

    public void displayInfo() {
        System.out.println(
            "General | Books: " + booksBorrowed);
    }
}

class StudentMember extends LibraryMember {

    private String course;

    public StudentMember(String memberId, String course) {
        super(memberId);
        this.course = course;
    }

    @Override
    public void displayInfo() {
        System.out.println(
            "Student | Course: " + course +
            " | Books: " + booksBorrowed);
    }

    public String getCourse() {
        return course;
    }
}

public class CirculationReportDemo {

    static String batchPrint(LibraryMember[] members) {

        StringBuilder report =
            new StringBuilder();

        for (LibraryMember member : members) {

            // Polymorphic call
            member.displayInfo();

            if (member instanceof StudentMember) {

                StudentMember student =
                    (StudentMember) member;

                report.append("Student | Course: ")
                      .append(student.getCourse())
                      .append(" | Books: ")
                      .append(member.booksBorrowed)
                      .append(" [Course via downcast: ")
                      .append(student.getCourse())
                      .append("] | ");
            } else {

                report.append("General | Books: ")
                      .append(member.booksBorrowed)
                      .append(" | ");
            }
        }

        return report.toString();
    }

    public static void main(String[] args) {

        LibraryMember general =
            new LibraryMember("LB5");

        StudentMember student =
            new StudentMember("STU6", "ECE");

        LibraryMember[] members =
            {general, student};

        System.out.println("=== WEEKLY CIRCULATION REPORT ===");

        String result =
            batchPrint(members);

        System.out.println("\n=== FINAL REPORT ===");
        System.out.println(result);
    }
}