public class NewDeveloper extends Employee {
    public int salary = 10000;

    protected void receiveBonus(){
        this.salary += 5000;
    }
}