public class GroceryTransaction extends Transaction implements Rewardable {

    private String name;

    public static double rewardPoints = 0;

    public GroceryTransaction() {
        super();
        this.name = "";
    }

    public GroceryTransaction(String name) {
        this();
        this.name = name;
    }

    @Override
    public void list() {
        System.out.println(super.getDate() + " " + this.name + " $" + super.getAmount());
    }

    @Override
    public void earnPoints(double amount) {
        //5pts per $1
        this.rewardPoints += amount*5;
    }
}
