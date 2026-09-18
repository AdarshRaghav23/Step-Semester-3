class MovieTicketBase {
    private String seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;
}

public class PremiumTicketDemo {

    static String classifyAccess(String modifier, String context) {
        if (modifier.equals("private"))
            return context.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

        if (modifier.equals("default"))
            return context.equals("SAME_CLASS") ||
                   context.equals("SAME_PACKAGE") ? "ALLOWED" : "DENIED";

        if (modifier.equals("protected")) {
            if (context.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                return "ALLOWED";

            if (context.equals("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"))
                return "DENIED";

            return context.equals("DIFFERENT_PACKAGE")
                    ? "DENIED" : "ALLOWED";
        }

        if (modifier.equals("public"))
            return "ALLOWED";

        return "DENIED";
    }

    public static void main(String[] args) {
        System.out.println(classifyAccess(
            "protected",
            "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
        ));

        System.out.println(classifyAccess(
            "protected",
            "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
        ));
    }
}