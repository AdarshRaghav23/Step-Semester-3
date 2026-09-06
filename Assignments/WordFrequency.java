import java.util.*;

public class WordFrequency {
    static void printFilteredWordFrequency(String text) {
        String[] stop = {"the","was","and","a","is","of","in"};
        HashMap<String, Integer> map = new HashMap<>();

        text = text.toLowerCase().replace(".", "").replace(",", "");

        for (String word : text.split("\\s+")) {
            boolean skip = false;

            for (String s : stop)
                if (word.equals(s)) skip = true;

            if (!skip)
                map.put(word, map.getOrDefault(word, 0) + 1);
        }

        map.entrySet().stream()
           .sorted((a,b) -> b.getValue() - a.getValue())
           .forEach(x -> System.out.println(x.getKey() + ": " + x.getValue()));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printFilteredWordFrequency(sc.nextLine());
    }
}