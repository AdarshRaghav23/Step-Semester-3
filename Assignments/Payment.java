class Payment {
    void pay(double amount) {
        System.out.println("Paid (cash): Rs " + amount);
    }

    void processTransaction(Payment p, double amount) {
        if (p instanceof CardPayment) {
            double total = amount * 1.02;
            System.out.println("Charged (card, incl. fee): Rs " + total);
        } else {
            p.pay(amount);
        }
    }

    public static void main(String[] args) {
        Payment[] payments = {
            new CardPayment(), new Payment(), new CardPayment(),
            new Payment(), new CardPayment()
        };

        double[] amounts = {100, 50, 200, 75, 120};
        double total = 0;

        Payment obj = new Payment();

        for (int i = 0; i < payments.length; i++) {
            obj.processTransaction(payments[i], amounts[i]);

            if (payments[i] instanceof CardPayment)
                total += amounts[i] * 1.02;
            else
                total += amounts[i];
        }

        System.out.println("Total Collected: Rs " + total);
    }
}

class CardPayment extends Payment {
    void payWithProcessingFee(double amount) {
        System.out.println("Charged (card, incl. fee): Rs " + (amount * 1.02));
    }
}