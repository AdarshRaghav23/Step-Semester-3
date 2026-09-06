class SrmStudent {
    static String collegeName;
    static int academicYear;

    static {
        collegeName = "SRM";
        academicYear = 2026;
        System.out.println("College info loaded");
    }

    SrmStudent(String name) {
        System.out.println("Student record created: " + name);
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        for (String name : names)
            new SrmStudent(name);
    }
}