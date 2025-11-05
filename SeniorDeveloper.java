public class SeniorDeveloper extends JuniorDeveloper {
    protected double salary = 15000;
    protected int stockOptions = 0;

    @Override
    protected void receiveBonus(){
        super.receiveBonus();
        stockOptions += 100;
    }
}
