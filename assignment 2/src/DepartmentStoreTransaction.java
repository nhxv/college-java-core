public class DepartmentStoreTransaction extends Transaction implements Rewardable {

    private String name;
    private int returnDay;

    public static double rewardPoints;

    public DepartmentStoreTransaction() {
        super();
    }

    public DepartmentStoreTransaction(String name, int returnDay) {
        this();
        this.name = name;
        this.returnDay = returnDay;
    }

    @Override
    public void list() {
        System.out.println(super.getDate() + " " + this.name + " $" + super.getAmount() + " " + "(return in " + this.returnDay + " days)");
    }

    @Override
    public void earnPoints(double amount) {
        //3pts per $1
        this.rewardPoints += amount*3;
    }
}
