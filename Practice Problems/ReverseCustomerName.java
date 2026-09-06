import java.util.*;

public class ReverseCustomerName {
    static String reverseCustomerName(String name) {
        StringBuilder s = new StringBuilder(name);
        return s.reverse().toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.println("Original Name: " + name);
        System.out.println("Reversed Name: " + reverseCustomerName(name));
    }
}