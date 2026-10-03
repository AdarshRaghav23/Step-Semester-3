// Question 4: Hotel Booking System

import java.util.ArrayList;
import java.util.List;

abstract class Room {
    private String roomNumber;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public abstract double calculatePrice(int days);
}

class StandardRoom extends Room {
    private static final double NIGHTLY_RATE = 100.0;

    public StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(int days) {
        return days * NIGHTLY_RATE;
    }
}

class DeluxeRoom extends Room {
    private static final double NIGHTLY_RATE = 200.0;

    public DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(int days) {
        return days * NIGHTLY_RATE;
    }
}

class HotelCustomer {
    private String name;

    public HotelCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Reservation {
    private HotelCustomer customer;
    private Room room;
    private int startDay;
    private int endDay;
    private boolean active;

    public Reservation(HotelCustomer customer, Room room, int startDay, int endDay) {
        this.customer = customer;
        this.room = room;
        this.startDay = startDay;
        this.endDay = endDay;
        this.active = true;
    }

    public HotelCustomer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public boolean isActive() {
        return active;
    }

    public void cancel() {
        this.active = false;
    }

    public boolean overlapsWith(int start, int end) {
        if (!active) return false;
        return (start < this.endDay && end > this.startDay);
    }

    public double getPrice() {
        return room.calculatePrice(endDay - startDay);
    }
}

class Hotel {
    private List<Reservation> reservations = new ArrayList<Reservation>();

    public boolean isAvailable(Room room, int startDay, int endDay) {
        for (Reservation res : reservations) {
            if (res.getRoom().getRoomNumber().equals(room.getRoomNumber()) && res.overlapsWith(startDay, endDay)) {
                return false;
            }
        }
        return true;
    }

    public Reservation reserveRoom(HotelCustomer customer, Room room, int startDay, int endDay, String dateStr) {
        if (!isAvailable(room, startDay, endDay)) {
            System.out.println(room.getRoomNumber() + " is not available from " + dateStr + ".");
            return null;
        }

        Reservation res = new Reservation(customer, room, startDay, endDay);
        reservations.add(res);
        System.out.println("Reservation confirmed for " + customer.getName() + ", " + room.getRoomNumber() + " (" + dateStr + "). Price: $" + res.getPrice() + ".");
        return res;
    }

    public void cancelReservation(Reservation res, String dateStr) {
        if (res != null && res.isActive()) {
            res.cancel();
            System.out.println("Reservation for " + res.getCustomer().getName() + ", " + res.getRoom().getRoomNumber() + " (" + dateStr + ") cancelled successfully.");
        }
    }
}

public class Problem4_HotelBookingSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        StandardRoom room101 = new StandardRoom("Standard Room 101");
        DeluxeRoom room201 = new DeluxeRoom("Deluxe Room 201");

        HotelCustomer customerA = new HotelCustomer("Customer A");
        HotelCustomer customerB = new HotelCustomer("Customer B");
        HotelCustomer customerC = new HotelCustomer("Customer C");

        // Customer A checks availability
        if (hotel.isAvailable(room101, 1, 5)) {
            System.out.println("Standard Room 101 is available from Jan 1 to Jan 5.");
        }

        // Customer A reserves room 101
        Reservation resA = hotel.reserveRoom(customerA, room101, 1, 5, "Jan 1-5");

        // Customer B attempts to reserve overlapping dates
        hotel.reserveRoom(customerB, room101, 3, 7, "Jan 3 to Jan 7");

        // Customer A cancels reservation
        hotel.cancelReservation(resA, "Jan 1-5");

        // Customer C reserves Deluxe Room 201
        hotel.reserveRoom(customerC, room201, 10, 12, "Feb 10-12");
    }
}