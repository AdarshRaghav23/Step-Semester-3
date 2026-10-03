// Question 3: The Campus Premiere Ticket Counter

import java.util.ArrayList;
import java.util.List;

abstract class Seat {
    private String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public abstract double getPrice();
}

class RegularSeat extends Seat {
    public RegularSeat(String seatNumber) {
        super(seatNumber);
    }

    public double getPrice() {
        return 150.00;
    }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String seatNumber) {
        super(seatNumber);
    }

    public double getPrice() {
        return 250.00;
    }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String seatNumber) {
        super(seatNumber);
    }

    public double getPrice() {
        return 400.00;
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

class Booking {
    private Customer customer;
    private List<Seat> bookedSeats;
    private double totalAmount;
    private boolean isCancelled;

    public Booking(Customer customer, List<Seat> seats) {
        this.customer = customer;
        this.bookedSeats = new ArrayList<Seat>(seats);
        this.isCancelled = false;
        this.totalAmount = calculateTotal();
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    private double calculateTotal() {
        double sum = 0;
        for (Seat seat : bookedSeats) {
            sum += seat.getPrice();
        }
        return sum;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

class Show {
    private String name;
    private List<Seat> occupiedSeats = new ArrayList<Seat>();

    public Show(String name) {
        this.name = name;
    }

    public boolean isSeatBooked(String seatNumber) {
        for (Seat s : occupiedSeats) {
            if (s.getSeatNumber().equalsIgnoreCase(seatNumber)) {
                return true;
            }
        }
        return false;
    }

    public Booking createBooking(Customer customer, List<Seat> seats) {
        if (seats.size() > 6) {
            System.out.println("Booking failed: Maximum 6 seats allowed per booking.");
            return null;
        }

        for (Seat s : seats) {
            if (isSeatBooked(s.getSeatNumber())) {
                System.out.println("Seat " + s.getSeatNumber() + " is already booked for this show.");
                return null;
            }
        }

        occupiedSeats.addAll(seats);
        Booking booking = new Booking(customer, seats);

        StringBuilder seatNames = new StringBuilder();
        for (int i = 0; i < seats.size(); i++) {
            seatNames.append(seats.get(i).getSeatNumber());
            if (i < seats.size() - 1) seatNames.append(", ");
        }

        System.out.printf("Booking confirmed for %s: %s. Total: ₹%.2f.\n",
                customer.getName(), seatNames.toString(), booking.getTotalAmount());
        return booking;
    }

    public void cancelBooking(Booking booking) {
        if (booking != null && !booking.isCancelled()) {
            booking.cancel();
            occupiedSeats.removeAll(booking.getBookedSeats());

            StringBuilder seatNames = new StringBuilder();
            List<Seat> seats = booking.getBookedSeats();
            for (int i = 0; i < seats.size(); i++) {
                seatNames.append(seats.get(i).getSeatNumber());
                if (i < seats.size() - 1) seatNames.append(", ");
            }

            System.out.println(booking.getCustomer().getName() + "'s booking cancelled. Seats " + seatNames.toString() + " released.");
        }
    }
}

public class Problem3_TicketCounter {
    public static void main(String[] args) {
        Show show7PM = new Show("7 PM Show");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        // Asha books Regular seats A1, A2 and Premium seat F5
        List<Seat> ashaSeats = new ArrayList<Seat>();
        ashaSeats.add(new RegularSeat("A1"));
        ashaSeats.add(new RegularSeat("A2"));
        ashaSeats.add(new PremiumSeat("F5"));
        Booking ashaBooking = show7PM.createBooking(asha, ashaSeats);

        // Ravi attempts to book seat A2 for the same show
        List<Seat> raviSeats1 = new ArrayList<Seat>();
        raviSeats1.add(new RegularSeat("A2"));
        show7PM.createBooking(ravi, raviSeats1);

        // Ravi books Recliner seat R1 for the same show
        List<Seat> raviSeats2 = new ArrayList<Seat>();
        raviSeats2.add(new ReclinerSeat("R1"));
        show7PM.createBooking(ravi, raviSeats2);

        // Asha cancels her booking before the show starts
        show7PM.cancelBooking(ashaBooking);

        // Neha books seat A2 for the same show
        List<Seat> nehaSeats = new ArrayList<Seat>();
        nehaSeats.add(new RegularSeat("A2"));
        show7PM.createBooking(neha, nehaSeats);
    }
}