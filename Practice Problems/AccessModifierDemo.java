class LibraryMember {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;
}

public class AccessModifierDemo {

    static String classifyAccess(String modifier, String context) {
        if (modifier.equals("private"))
            return context.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

        if (modifier.equals("default"))
            return context.equals("SAME_CLASS") || context.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";

        if (modifier.equals("protected"))
            return context.equals("DIFFERENT_PACKAGE") ? "DENIED" : "ALLOWED";

        if (modifier.equals("public"))
            return "ALLOWED";

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        String result = "";

        for (String modifier : modifiers) {
            int allowed = 0, denied = 0;

            for (String[] attempt : attempts) {
                if (attempt[0].equals(modifier)) {
                    if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED"))
                        allowed++;
                    else
                        denied++;
                }
            }

            if (!result.isEmpty())
                result += " | ";

            result += modifier + ": " + allowed + " allowed / " + denied + " denied";
        }

        return result;
    }

    public static void main(String[] args) {
        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeByModifier(attempts));
    }
}