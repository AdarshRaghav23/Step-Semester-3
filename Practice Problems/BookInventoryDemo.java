class BookInventory {
    private int copiesTotal;
    private int copiesAvailable;

    BookInventory(int copiesTotal) {
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    void checkOut() {
        if (copiesAvailable > 0)
            copiesAvailable--;
    }

    void checkIn() {
        if (copiesAvailable < copiesTotal)
            copiesAvailable++;
    }

    int getCopiesAvailable() {
        return copiesAvailable;
    }
}

public class BookInventoryDemo {

    public static void main(String[] args) {
        BookInventory b = new BookInventory(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();

        System.out.println("Available after checkout: "
                + b.getCopiesAvailable());

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();

        System.out.println("Available after checkin: "
                + b.getCopiesAvailable());
    }
}