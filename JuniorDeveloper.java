public class JuniorDeveloper extends NewDeveloper {
    protected double monthly_salary;
    protected double bonus;
    protected double percent_bonus;

    JuniorDeveloper(){
        super();
        this.monthly_salary = 13000;
        this.bonus = 0;
        this.percent_bonus = 0;
    }
    JuniorDeveloper(String l_name, String f_name, int ssn){
        super(l_name, f_name, ssn);
        this.monthly_salary = 13000;
        this.bonus = 0;
        this.percent_bonus = 0;
    }
    
    @Override
    protected void receiveBonus(){
        super.receiveBonus();
        this.percent_bonus++;
    }

    @Override
    protected double calculateAnnualPayment(){
        double annualPayment = super.calculateAnnualPayment();
        annualPayment *= (percent_bonus / 100);
        return annualPayment;
    }
}
