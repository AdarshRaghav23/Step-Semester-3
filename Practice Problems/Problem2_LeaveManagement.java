// Question 2: Employee Leave Request Workflow

enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED
}

abstract class Employee {
    private String name;
    private String employeeType;

    public Employee(String name, String employeeType) {
        this.name = name;
        this.employeeType = employeeType;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public abstract boolean canApplyLeave(int days);
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) {
        super(name, "FullTime");
    }

    public boolean canApplyLeave(int days) {
        return days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) {
        super(name, "PartTime");
    }

    public boolean canApplyLeave(int days) {
        return days <= 10;
    }
}

class Contractor extends Employee {
    public Contractor(String name) {
        super(name, "Contractor");
    }

    public boolean canApplyLeave(int days) {
        return days <= 5;
    }
}

class LeaveRequest {
    private Employee employee;
    private String dates;
    private int days;
    private LeaveStatus status;

    public LeaveRequest(Employee employee, String dates, int days) {
        this.employee = employee;
        this.dates = dates;
        this.days = days;
        this.status = LeaveStatus.PENDING;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getDates() {
        return dates;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public boolean approve() {
        if (this.status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + this.status + " to Approved.");
            return false;
        }
        this.status = LeaveStatus.APPROVED;
        System.out.println(employee.getName() + "'s leave request (" + dates + ") approved. Status: Approved.");
        return true;
    }

    public boolean reject() {
        if (this.status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + this.status + " to Rejected.");
            return false;
        }
        this.status = LeaveStatus.REJECTED;
        System.out.println(employee.getName() + "'s leave request (" + dates + ") rejected. Status: Rejected.");
        return true;
    }

    public boolean setPending() {
        if (this.status == LeaveStatus.APPROVED || this.status == LeaveStatus.REJECTED) {
            System.out.println("Cannot change leave request status from " + this.status + " to Pending.");
            return false;
        }
        this.status = LeaveStatus.PENDING;
        return true;
    }
}

class LeaveService {
    public LeaveRequest submitLeave(Employee employee, String dates, int days) {
        if (!employee.canApplyLeave(days)) {
            System.out.println("Leave submission failed: Exceeds policy limits for " + employee.getName());
            return null;
        }

        LeaveRequest request = new LeaveRequest(employee, dates, days);
        System.out.println("Leave request submitted for " + employee.getName() + " (" + dates + "). Status: Pending.");
        return request;
    }
}

public class Problem2_LeaveManagement {
    public static void main(String[] args) {
        LeaveService leaveService = new LeaveService();

        FullTimeEmployee john = new FullTimeEmployee("John");
        PartTimeEmployee jane = new PartTimeEmployee("Jane");

        // John submits leave request
        LeaveRequest johnRequest = leaveService.submitLeave(john, "Jan 1-5", 5);

        // Manager Alice approves John's request
        if (johnRequest != null) {
            johnRequest.approve();
        }

        // Jane submits leave request
        LeaveRequest janeRequest = leaveService.submitLeave(jane, "Feb 10-11", 2);

        // Manager Bob rejects Jane's request
        if (janeRequest != null) {
            janeRequest.reject();
        }

        // John attempts to change his approved leave request to Pending
        if (johnRequest != null) {
            johnRequest.setPending();
        }
    }
}  