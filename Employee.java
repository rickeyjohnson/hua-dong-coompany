public class Employee {
    protected String last_name;
    protected String first_name;
    protected int social_security_number;
    Employee(){

    }

    Employee(String l_name, String f_name, int ssn){
        this.last_name = l_name;
        this.first_name = f_name;
        this.social_security_number = ssn;
    }

    protected void setMonthlyPayment(double amount) {
        // Implementation here
    }

    protected void calculateAnnualPayment() {
        // Implementation here
    }
}