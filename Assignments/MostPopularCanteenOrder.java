import java.util.HashMap;

public class MostPopularCanteenOrder {

    public static void mostPopular(String[] orders) {
        HashMap<String, Integer> count = new HashMap<>();

        for (String item : orders) {
            count.put(item, count.getOrDefault(item, 0) + 1);
        }

        String popularItem = orders[0];
        int maxCount = count.get(popularItem);

        for (String item : orders) {
            if (count.get(item) > maxCount) {
                popularItem = item;
                maxCount = count.get(item);
            }
        }

        System.out.println("(\"" + popularItem + "\", " + maxCount + ")");
    }

    public static void main(String[] args) {
        String[] orders = {
            "dosa", "idli", "vada", "dosa",
            "idli", "dosa", "tea"
        };

        mostPopular(orders);
    }
}