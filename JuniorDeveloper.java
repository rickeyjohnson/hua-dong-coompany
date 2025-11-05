public class JuniorDeveloper extends NewDeveloper {
    protected double salary = 13000;

    @Override
    protected void receiveBonus(){
        super.receiveBonus();
        salary *= 1.01;
    }
}
