// Question 2: The Assignment Submission Portal

enum SubmissionStatus {
    SUBMITTED,
    GRADED
}

abstract class Assignment {
    private String title;
    private int maxMarks;
    private int dueDate; // Stored as day number (e.g., 10 for Mar 10)

    public Assignment(String title, int maxMarks, int dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public int getDueDate() {
        return dueDate;
    }

    public abstract double calculateFinalMarks(double rawMarks, int lateDays);
    public abstract double getPenaltyPercentagePerDay();
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, int maxMarks, int dueDate) {
        super(title, maxMarks, dueDate);
    }

    public double getPenaltyPercentagePerDay() {
        return 0.10; // 10% penalty per day late
    }

    public double calculateFinalMarks(double rawMarks, int lateDays) {
        if (lateDays <= 0) return rawMarks;
        double penalty = lateDays * getPenaltyPercentagePerDay();
        return rawMarks * (1.0 - penalty);
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, int maxMarks, int dueDate) {
        super(title, maxMarks, dueDate);
    }

    public double getPenaltyPercentagePerDay() {
        return 0.20; // 20% penalty per day late
    }

    public double calculateFinalMarks(double rawMarks, int lateDays) {
        if (lateDays <= 0) return rawMarks;
        double penalty = lateDays * getPenaltyPercentagePerDay();
        return rawMarks * (1.0 - penalty);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Submission {
    private Student student;
    private Assignment assignment;
    private int submissionDate;
    private SubmissionStatus status;
    private double finalMarks;

    public Submission(Student student, Assignment assignment, int submissionDate) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = SubmissionStatus.SUBMITTED;

        int lateDays = Math.max(0, submissionDate - assignment.getDueDate());
        if (lateDays == 0) {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (on time). Status: Submitted.");
        } else {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (" + lateDays + " days late). Status: Submitted.");
        }
    }

    public Student getStudent() {
        return student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public boolean grade(double awardedMarks) {
        if (this.status == SubmissionStatus.GRADED) {
            System.out.println("Cannot grade: '" + assignment.getTitle() + "' has already been graded.");
            return false;
        }

        int lateDays = Math.max(0, submissionDate - assignment.getDueDate());
        this.finalMarks = assignment.calculateFinalMarks(awardedMarks, lateDays);
        this.status = SubmissionStatus.GRADED;

        if (lateDays == 0) {
            System.out.printf("%s graded: %.0f/%d. Status: Graded.\n",
                    student.getName(), finalMarks, assignment.getMaxMarks());
        } else {
            int totalPenaltyPercent = (int) (lateDays * assignment.getPenaltyPercentagePerDay() * 100);
            System.out.printf("%s graded: %.0f/%d after %d%% late penalty. Status: Graded.\n",
                    student.getName(), finalMarks, assignment.getMaxMarks(), totalPenaltyPercent);
        }
        return true;
    }

    public boolean resubmit(int newSubmissionDate) {
        if (this.status == SubmissionStatus.GRADED) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
            return false;
        }
        this.submissionDate = newSubmissionDate;
        return true;
    }
}

public class Problem2_AssignmentPortal {
    public static void main(String[] args) {
        CodingAssignment codingLab = new CodingAssignment("Linked List Lab", 50, 10);
        WrittenAssignment essay = new WrittenAssignment("Design Essay", 50, 12);

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        // Asha submits 'Linked List Lab' on Mar 10
        Submission ashaSub = new Submission(asha, codingLab, 10);

        // Ravi submits 'Design Essay' on Mar 14
        Submission raviSub = new Submission(ravi, essay, 14);

        // Faculty awards Asha 45 marks
        ashaSub.grade(45);

        // Faculty awards Ravi 40 marks
        raviSub.grade(40);

        // Asha attempts to resubmit 'Linked List Lab'
        ashaSub.resubmit(11);
    }
}