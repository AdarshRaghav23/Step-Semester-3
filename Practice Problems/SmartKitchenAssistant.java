abstract class KitchenTool {
    private int speedLevel = 1;

    public abstract String prepare();

    public int getSpeedLevel() {
        return speedLevel;
    }

    public void setSpeedLevel(int speedLevel) {
        if (speedLevel >= 1 && speedLevel <= 5) {
            this.speedLevel = speedLevel;
        }
    }
}

interface Washable {
    String clean();
}

class Blender extends KitchenTool implements Washable {

    public Blender() {
        super();
    }

    public String prepare() {
        return "Blending at speed " + getSpeedLevel();
    }

    public String clean() {
        return "Blender rinsed and dried";
    }
}

public class SmartKitchenAssistant {

    public static void main(String[] ARGS) {

        Blender b = new Blender();

        b.setSpeedLevel(3);

        System.out.println("Speed Level: " + b.getSpeedLevel());

        b.setSpeedLevel(9);

        System.out.println("After invalid speed:");
        System.out.println("Speed Level: " + b.getSpeedLevel());

        System.out.println(b.prepare());
        System.out.println(b.clean());
    }
}