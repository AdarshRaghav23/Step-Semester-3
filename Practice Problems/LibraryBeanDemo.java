class LibraryMemberBean {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    public LibraryMemberBean() {
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (membershipId == null)
            membershipId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        securityAnswer = Integer.toHexString(answer.hashCode());
    }
}

public class LibraryBeanDemo {

    public static void main(String[] args) {
        LibraryMemberBean m = new LibraryMemberBean();

        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);

        System.out.println(m.getMembershipId());
        System.out.println(m.getName());
        System.out.println(m.isPremiumMember());

        m.setMembershipId("FAKE-0000");

        System.out.println(m.getMembershipId());

        m.setSecurityAnswer("BlueMountain");
        System.out.println("Security answer stored securely.");
    }
}