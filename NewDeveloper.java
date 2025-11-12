public class NewDeveloper extends Employee {
    public double bonus;

    public NewDeveloper(String firstName, String lastName, int ssn) {
        super(firstName, lastName, ssn);
        super.setMonthlySalary(10000);
        this.bonus = 0.0;
    }

    @Override
    protected void receiveBonus() {
        super.receiveBonus();
        this.bonus += 5000.0;
    }

    @Override
    public double calculateAnnualPayment() {
        return super.calculateAnnualPayment() + bonus;
    }
}