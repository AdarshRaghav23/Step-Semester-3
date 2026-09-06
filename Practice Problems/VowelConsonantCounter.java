import java.util.*;

public class VowelConsonantCounter {
    static void count(String s) {
        int v = 0, c = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = Character.toLowerCase(s.charAt(i));

            if ("aeiou".indexOf(ch) >= 0)
                v++;
            else if (ch != ' ')
                c++;
        }

        System.out.println("Vowels: " + v + " | Consonants: " + c);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        count(sc.nextLine());
    }
}