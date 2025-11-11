public class JuniorDeveloper extends NewDeveloper {
    public JuniorDeveloper(String firstName, String lastName, int ssn) {
        super(firstName, lastName, ssn);
    }

    @Override
    protected void receiveBonus() {
        super.receiveBonus();
    }

    @Override
    public double calculateAnnualPayment() {
        double baseAnnual = super.calculateAnnualPayment();
        double percentBonus = baseAnnual * 0.01; // 1% bonus
        return baseAnnual + percentBonus;
    }
}