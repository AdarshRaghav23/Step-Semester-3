class MovieBookingProfile {
    private String name;
    private boolean confirmed;
    private String otp;

    public MovieBookingProfile() {
    }

    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}

public class MovieProfileDemo {

    public static void main(String[] args) {
        MovieBookingProfile p =
            new MovieBookingProfile("Rahul Dev");

        System.out.println(p.getName());

        p.setConfirmed(true);
        System.out.println(p.isConfirmed());

        p.setOtp("4471");
        System.out.println("OTP stored.");
    }
}