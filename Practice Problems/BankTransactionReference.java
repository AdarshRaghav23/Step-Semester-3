import java.util.*;

public class BankTransactionReference {
    static String normalizeReference(String s) {
        s = s.trim();
        return s.substring(0, 3).toUpperCase() + s.substring(3);
    }

    static String validateAndFormat(String s) {
        if (s.length() != 14)
            return "Invalid: wrong length";

        for (int i = 0; i < 3; i++)
            if (!Character.isLetter(s.charAt(i)))
                return "Invalid: bank code must be 3 letters";

        for (int i = 3; i < 14; i++)
            if (!Character.isDigit(s.charAt(i)))
                return "Invalid: body must contain digits";

        StringBuilder x = new StringBuilder();
        x.append("[").append(s.substring(0,3))
         .append("] DATE: ").append(s.substring(3,5))
         .append("/").append(s.substring(5,7))
         .append("/").append(s.substring(7,9))
         .append(" | SEQ: ").append(s.substring(9));

        return x.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = normalizeReference(sc.nextLine());
        System.out.println(validateAndFormat(s));
    }
}