class FeeAccount {
    static int hostel = 0, dayScholar = 0;

    void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount) {
            hostel++;
            System.out.println("Paid in two installments (hostel account)");
        } else {
            dayScholar++;
            System.out.println("Paid in one go (day-scholar account)");
        }
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(), new HostelFeeAccount(),
            new FeeAccount(), new FeeAccount()
        };

        FeeAccount obj = new FeeAccount();

        for (FeeAccount a : accounts)
            obj.processPayment(a, 60000);

        System.out.println("Hostel accounts processed: " + hostel +
                " | Day-scholar accounts processed: " + dayScholar);
    }
}

class HostelFeeAccount extends FeeAccount {
}