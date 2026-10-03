// Question 1: Vehicle Rental System

import java.util.ArrayList;
import java.util.List;

abstract class Vehicle {
    private String id;
    private String model;
    private boolean isRented;

    public Vehicle(String id, String model) {
        this.id = id;
        this.model = model;
        this.isRented = false;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public abstract double calculateRentalCharge(int days);
}

class Sedan extends Vehicle {
    private static final double DAILY_RATE = 50.0;

    public Sedan(String id, String model) {
        super(id, model);
    }

    public double calculateRentalCharge(int days) {
        return days * DAILY_RATE;
    }
}

class SUV extends Vehicle {
    private static final double DAILY_RATE = 80.0;

    public SUV(String id, String model) {
        super(id, model);
    }

    public double calculateRentalCharge(int days) {
        return days * DAILY_RATE;
    }
}

class Truck extends Vehicle {
    private static final double DAILY_RATE = 120.0;

    public Truck(String id, String model) {
        super(id, model);
    }

    public double calculateRentalCharge(int days) {
        return days * DAILY_RATE;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;
    private double charge;

    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        this.charge = vehicle.calculateRentalCharge(days);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getCharge() {
        return charge;
    }
}

class RentalSystem {
    private List<Rental> activeRentals = new ArrayList<Rental>();

    public boolean rentVehicle(Customer customer, Vehicle vehicle, int days) {
        if (vehicle.isRented()) {
            System.out.println(vehicle.getModel() + " is currently unavailable.");
            return false;
        }

        vehicle.setRented(true);
        Rental rental = new Rental(vehicle, customer, days);
        activeRentals.add(rental);
        System.out.println(vehicle.getModel() + " rented successfully by " + customer.getName() + ". Rental charge: $" + rental.getCharge() + ".");
        return true;
    }

    public boolean returnVehicle(Customer customer, Vehicle vehicle) {
        Rental foundRental = null;
        for (Rental r : activeRentals) {
            if (r.getVehicle().getId().equals(vehicle.getId()) && r.getCustomer().getName().equals(customer.getName())) {
                foundRental = r;
                break;
            }
        }

        if (foundRental != null) {
            activeRentals.remove(foundRental);
            vehicle.setRented(false);
            System.out.println(vehicle.getModel() + " returned by " + customer.getName() + ".");
            return true;
        }

        System.out.println("No active rental record found for " + vehicle.getModel() + " under " + customer.getName() + ".");
        return false;
    }
}

public class Problem1_VehicleRentalSystem {
    public static void main(String[] args) {
        RentalSystem system = new RentalSystem();

        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");

        Sedan sedanA = new Sedan("V1", "Sedan A");
        SUV suvB = new SUV("V2", "SUV B");

        // Customer 1 rents Sedan A for 3 days
        system.rentVehicle(customer1, sedanA, 3);

        // Customer 2 attempts to rent Sedan A for 2 days
        system.rentVehicle(customer2, sedanA, 2);

        // Customer 1 returns Sedan A
        system.returnVehicle(customer1, sedanA);

        // Customer 3 rents SUV B for 5 days
        system.rentVehicle(customer3, suvB, 5);
    }
}