class ParkingTicket {
    String vehicleNo;
    double ratePerMinute;

    ParkingTicket(String vehicleNo, double rate) {
        this.vehicleNo = vehicleNo;
        ratePerMinute = rate;
    }

    final double calculateFine(int minutes) {
        return minutes * ratePerMinute;
    }

    final void printReceipt(int minutes) {
        System.out.println(vehicleNo + " - Fine: Rs " + calculateFine(minutes));
    }

    public static void main(String[] args) {
        String[] vehicles = {
            "TN09AB1234", "TN22CD5678", "TN09EF9012", "TN10GH3456"
        };
        double[] rates = {2, 2, 3, 2};
        int[] time = {15, 0, -5, 8};

        for (int i = 0; i < vehicles.length; i++) {
            ParkingTicket p = new ParkingTicket(vehicles[i], rates[i]);

            if (time[i] > 0)
                p.printReceipt(time[i]);
            else
                System.out.println(vehicles[i] + " - No fine, within allotted time");
        }
    }
}