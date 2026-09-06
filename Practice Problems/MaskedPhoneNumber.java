import java.util.*;

public class MaskedPhoneNumber {
    static String maskPhoneNumber(String phone) {
        if (phone.length() != 10)
            return "Invalid phone number";

        for (char c : phone.toCharArray())
            if (!Character.isDigit(c))
                return "Invalid phone number";

        StringBuilder s = new StringBuilder("XXXXXX");
        s.append("-").append(phone.substring(6));

        return s.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(maskPhoneNumber(sc.nextLine()));
    }
}