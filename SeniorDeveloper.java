public class SeniorDeveloper extends JuniorDeveloper {
    protected double salary = 15000;
    protected int stockOptions = 0;

    SeniorDeveloper(){
        super();
        this.salary = 15000;
        this.monthlyPayment = this.salary / 12;
    }
    SeniorDeveloper(String l_name, String f_name, int ssn){
        super(l_name, f_name, ssn);
        this.salary = 15000;
        this.monthlyPayment = this.salary / 12;
    }
    @Override
    protected void receiveBonus(){
        super.receiveBonus();
        this.stockOptions += 100;
    }
}
