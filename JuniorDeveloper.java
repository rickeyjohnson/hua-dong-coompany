public class JuniorDeveloper extends NewDeveloper {
    protected double salary;
    protected double monthlyPayment;

    JuniorDeveloper(){
        super();
        this.salary = 13000;
        this.monthlyPayment = this.salary / 12;
    }
    JuniorDeveloper(String l_name, String f_name, int ssn){
        super(l_name, f_name, ssn);
        this.salary = 13000;
        this.monthlyPayment = this.salary / 12;
    }
    
    @Override
    protected void receiveBonus(){
        super.receiveBonus();
        this.salary *= 1.01;
    }
}
