public class NewDeveloper extends Employee {
    //TODO: salary should represent a monthly value instead of yearly, refactor use
    protected double monthly_salary;
    protected double bonus;

    NewDeveloper(){
        super();
        this.monthly_salary = 10000;
    }
    NewDeveloper(String l_name, String f_name, int ssn){
        super(l_name, f_name, ssn);
        this.monthly_salary = 10000;
        this.bonus = 0;
    }

    protected void receiveBonus(){
        this.bonus += 5000;
    }
    @Override
    protected double calculateAnnualPayment(){
        return (this.monthly_salary * 12) + bonus;
    }
}