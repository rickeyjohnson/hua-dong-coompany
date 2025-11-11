public class SeniorDeveloper extends JuniorDeveloper {
    public int stockOptions;

    SeniorDeveloper(String firstName, String lastName, int ssn) {
        super(firstName, lastName, ssn);
        this.stockOptions = 100;
    }

    @Override
    protected void receiveBonus() {
        super.receiveBonus();
        this.stockOptions += 100;
    }

    @Override
    public double calculateAnnualPayment() {
        double baseAnnual = super.calculateAnnualPayment();
        double percentBonus = baseAnnual * 0.01; // 1% bonus
        return baseAnnual + percentBonus;
    }
}