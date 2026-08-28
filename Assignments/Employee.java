class Employee {
    String empName;
    double salary;
    
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    public Employee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {
        new Employee("Aarav", 50000);
        new Employee("Bhavna", 60000);
        new Employee("Chetan", 55000);

        Employee.printCompanyInfo();
    }
}