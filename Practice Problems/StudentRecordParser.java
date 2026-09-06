import java.util.*;

public class StudentRecordParser {
    static void parse(String s) {
        String[] p = s.split(",");

        if (p.length != 3)
            System.out.println("Invalid Record");
        else
            System.out.println("Name: " + p[0] +
                    " | Roll No: " + p[1] +
                    " | Dept: " + p[2]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        parse(sc.nextLine());
    }
}