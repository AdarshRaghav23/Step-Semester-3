class LateFee {
    String regNo;
    double totalFee;

    LateFee(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    final double calculateLateFee(int daysLate) {
        return totalFee * daysLate * 0.01;
    }

    final void printSummary(int daysLate) {
        if (daysLate <= 0)
            System.out.println(regNo + " - On time, no late fee");
        else
            System.out.println(regNo + " | Total Fee: Rs " + totalFee +
                    " | Late Fee: Rs " + calculateLateFee(daysLate));
    }

    public static void main(String[] args) {
        String[] r = {"RA001", "RA002", "RA003", "RA004"};
        double[] f = {200000, 150000, 180000, 220000};
        int[] d = {10, 0, -2, 5};

        for (int i = 0; i < r.length; i++)
            new LateFee(r[i], f[i]).printSummary(d[i]);
    }
}