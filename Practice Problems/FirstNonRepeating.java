import java.util.*;

public class FirstNonRepeating {
    static char findFirstNonRepeatingChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        for (char c : s.toCharArray())
            if (map.get(c) == 1) return c;

        return '\0';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char c = findFirstNonRepeatingChar(sc.nextLine());

        if (c == '\0')
            System.out.println("No Non-Repeating Character Found");
        else
            System.out.println("First Non-Repeating Character: '" + c + "'");
    }
}