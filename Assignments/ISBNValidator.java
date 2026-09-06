import java.util.*;

public class ISBNValidator {
    static String normalizeCode(String s) {
        s = s.trim();
        if (s.length() >= 3)
            s = s.substring(0, 3).toUpperCase() + s.substring(3);
        return s;
    }

    static String validateAndFormat(String s) {
        if (s.length() != 13)
            return "Invalid: wrong length";

        for (int i = 0; i < 3; i++)
            if (!Character.isLetter(s.charAt(i)))
                return "Invalid: publisher code must be 3 letters";

        for (int i = 3; i < 13; i++)
            if (!Character.isDigit(s.charAt(i)))
                return "Invalid: body must contain digits";

        StringBuilder x = new StringBuilder();
        x.append("[").append(s.substring(0, 3)).append("] YEAR: ")
         .append(s.substring(3, 7)).append(" | CATALOG: ")
         .append(s.substring(7));

        return x.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String code = normalizeCode(sc.nextLine());
        System.out.println(validateAndFormat(code));
    }
}