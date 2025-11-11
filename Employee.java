public class Employee {
    public String firstName;
    public String lastName;
    public int ssn;
    public double monthly_salary;

    public Employee(String firstName, String lastName, int ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.monthly_salary = 0.0;
    }

    protected void receiveBonus() {
        // Base Employee does not receive a bonus
    }

    protected void setMonthlySalary(double salary) {
        this.monthly_salary = salary;
    }

    public double calculateAnnualPayment() {
        return this.monthly_salary * 12;
    }
}