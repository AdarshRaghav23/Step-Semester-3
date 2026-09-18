class MovieTicket {
    private String seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;
}

public class MovieAccessDemo {

    static String classifyAccess(String modifier, String context) {
        if (modifier.equals("private"))
            return context.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

        if (modifier.equals("default"))
            return (context.equals("SAME_CLASS") ||
                    context.equals("SAME_PACKAGE")) ? "ALLOWED" : "DENIED";

        if (modifier.equals("protected"))
            return context.equals("DIFFERENT_PACKAGE") ? "DENIED" : "ALLOWED";

        if (modifier.equals("public"))
            return "ALLOWED";

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {
        int allowed = 0, denied = 0;

        for (String[] a : attempts) {
            if (classifyAccess(a[0], a[1]).equals("ALLOWED"))
                allowed++;
            else
                denied++;
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        String[][] attempts = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeBatch(attempts));
    }
}