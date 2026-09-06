import java.util.*;

public class WordReversalEncoder {
    static String reverseEachWord(String s) {
        String[] words = s.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words)
            result.append(new StringBuilder(word).reverse()).append(" ");

        return result.toString().trim();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter sentence: ");
        System.out.println(reverseEachWord(sc.nextLine()));
    }
}