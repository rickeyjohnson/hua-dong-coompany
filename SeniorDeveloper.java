public class SeniorDeveloper extends JuniorDeveloper {
    protected double monthly_salary;
    protected double bonus;
    protected double percent_bonus;
    protected int stockOptions;

    SeniorDeveloper(){
        super();
        this.monthly_salary = 15000;
        this.bonus = 0;
        this.percent_bonus = 0;
        this.stockOptions = 0;
    }
    SeniorDeveloper(String l_name, String f_name, int ssn){
        super(l_name, f_name, ssn);
        this.monthly_salary = 15000;
        this.bonus = 0;
        this.percent_bonus = 0;
        this.stockOptions = 0;
    }
    @Override
    protected void receiveBonus(){
        super.receiveBonus();
        this.stockOptions += 100;
    }

    @Override
    protected double calculateAnnualPayment(){
        double annualPayment = super.calculateAnnualPayment();
        //what do you do with stocks
        return annualPayment;
    }
}
