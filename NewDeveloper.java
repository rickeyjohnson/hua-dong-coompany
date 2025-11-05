public class NewDeveloper extends Employee {
    protected double salary;
    protected double monthlyPayment;

    NewDeveloper(){
        super();
        this.salary = 10000;
        this.monthlyPayment = this.salary / 12;
    }
    NewDeveloper(String l_name, String f_name, int ssn){
        super(l_name, f_name, ssn);
        this.salary = 10000;
        this.monthlyPayment = this.salary / 12;
    }

    protected void receiveBonus(){
        this.salary += 5000;
    }
    @Override
    protected void setMonthlyPayment(double amount){
        this.monthlyPayment = amount;
        this.salary = this.monthlyPayment * 12;
    }
    protected void setMonthlyPayment(){
        this.monthlyPayment = salary / 12;
    }
}