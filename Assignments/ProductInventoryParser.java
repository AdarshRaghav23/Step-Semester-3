import java.util.*;

public class ProductInventoryParser {
    static void parseInventoryRecord(String s) {
        String[] p = s.split(",");

        if (p.length != 3)
            System.out.println("Invalid Record");
        else
            System.out.println("Product: " + p[0] +
                    " | SKU: " + p[1] + " | Qty: " + p[2]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter record: ");
        parseInventoryRecord(sc.nextLine());
    }
}