// Problem 3: Backyard Toolshed Routine

abstract class GardenTool {
    public abstract String use();
}

class CuttingTool extends GardenTool {
    public CuttingTool() {
        super();
    }

    public String use() {
        return "Using the tool in the garden, blade sharpened first";
    }
}

class Pruner extends CuttingTool {
    public Pruner() {
        super();
    }

    public String use() {
        return super.use() + ", then trimming branches precisely";
    }
}

public class Problem3_BackyardToolshed {
    public static void main(String[] args) {
        CuttingTool c = new CuttingTool();
        System.out.println(c.use());

        Pruner p = new Pruner();
        System.out.println(p.use());
    }
}