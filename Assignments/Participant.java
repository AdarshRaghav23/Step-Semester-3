class Participant {
    String name, teamName;
    boolean registered;

    Participant(String name, String teamName) {
        this.name = name;
        this.teamName = teamName;
        registered = true;
    }

    Participant(String name) {
        this(name, "Unassigned");
    }

    void printStatus() {
        System.out.println(name + " | " + teamName + " | Registered: " + registered);
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya"};
        String[] teams = {"ByteBusters", "", "CodeCrafters", ""};

        for (int i = 0; i < names.length; i++) {
            Participant p;
            if (teams[i].equals(""))
                p = new Participant(names[i]);
            else
                p = new Participant(names[i], teams[i]);

            p.printStatus();
        }
    }
}