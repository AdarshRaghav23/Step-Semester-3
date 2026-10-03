// Question 1: The Hostel Laundry Queue

import java.util.ArrayList;
import java.util.List;

abstract class WashType {
    private String name;
    private int durationMinutes;
    private double charge;

    public WashType(String name, int durationMinutes, double charge) {
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.charge = charge;
    }

    public String getName() {
        return name;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public double getCharge() {
        return charge;
    }
}

class QuickWash extends WashType {
    public QuickWash() {
        super("Quick", 30, 20.00);
    }
}

class NormalWash extends WashType {
    public NormalWash() {
        super("Normal", 45, 30.00);
    }
}

class HeavyWash extends WashType {
    public HeavyWash() {
        super("Heavy", 60, 45.00);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class WashingMachine {
    private String id;
    private boolean isBusy;

    public WashingMachine(String id) {
        this.id = id;
        this.isBusy = false;
    }

    public String getId() {
        return id;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        this.isBusy = busy;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public Student getStudent() {
        return student;
    }

    public WashingMachine getMachine() {
        return machine;
    }

    public WashType getWashType() {
        return washType;
    }
}

class LaundrySystem {
    private List<WashCycle> activeCycles = new ArrayList<WashCycle>();

    public boolean startWash(Student student, WashingMachine machine, WashType washType) {
        if (machine.isBusy()) {
            System.out.println("Machine " + machine.getId() + " is currently busy.");
            return false;
        }

        machine.setBusy(true);
        WashCycle cycle = new WashCycle(student, machine, washType);
        activeCycles.add(cycle);

        System.out.printf("%s wash started on %s for %s (%d min). Charge: %.2f.\n",
                washType.getName(), machine.getId(), student.getName(), washType.getDurationMinutes(), washType.getCharge());
        return true;
    }

    public void completeWash(WashingMachine machine) {
        WashCycle foundCycle = null;
        for (WashCycle cycle : activeCycles) {
            if (cycle.getMachine().getId().equals(machine.getId())) {
                foundCycle = cycle;
                break;
            }
        }

        if (foundCycle != null) {
            activeCycles.remove(foundCycle);
            machine.setBusy(false);
            System.out.println(machine.getId() + " cycle completed. " + machine.getId() + " is now free.");
        }
    }
}

public class Problem1_HostelLaundryQueue {
    public static void main(String[] args) {
        LaundrySystem system = new LaundrySystem();

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        // Asha starts a Quick wash on Machine M1
        system.startWash(asha, m1, new QuickWash());

        // Ravi attempts to start a Heavy wash on Machine M1
        system.startWash(ravi, m1, new HeavyWash());

        // Ravi starts a Heavy wash on Machine M2
        system.startWash(ravi, m2, new HeavyWash());

        // Machine M1 completes its cycle
        system.completeWash(m1);

        // Neha starts a Normal wash on Machine M1
        system.startWash(neha, m1, new NormalWash());
    }
}