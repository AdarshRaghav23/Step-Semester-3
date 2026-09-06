public class MovieReviewWordLengthProfiler {

    static void classifyWordLengths(String review) {
        int shortWords = 0, medium = 0, longWords = 0;

        for (String word : review.split("\\s+")) {
            int n = word.length();

            if (n <= 4)
                shortWords++;
            else if (n <= 8)
                medium++;
            else
                longWords++;
        }

        System.out.println("Short: " + shortWords +
                " | Medium: " + medium +
                " | Long: " + longWords);
    }

    public static void main(String[] args) {
        classifyWordLengths(
            "This movie was absolutely fantastic and thrilling"
        );
    }
}