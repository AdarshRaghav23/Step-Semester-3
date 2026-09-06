import java.util.*;

public class FileExtensionValidator {
    static String validateFileExtension(String file) {
        int dot = file.lastIndexOf('.');

        if (dot == -1)
            return "Rejected — invalid file type";

        String ext = file.substring(dot + 1);

        if (ext.equalsIgnoreCase("pdf") ||
            ext.equalsIgnoreCase("docx") ||
            ext.equalsIgnoreCase("zip"))
            return "Accepted";

        return "Rejected — invalid file type";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(validateFileExtension(sc.nextLine()));
    }
}