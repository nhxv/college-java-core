public class BankingTransaction extends Transaction {

    private String type;
    private double charges;

    public BankingTransaction() {
        super();
        this.type = "";
    }

    public BankingTransaction(String type, double charges) {
        this();
        this.type = type;
        this.charges = charges;
    }

    @Override
    public void list() {
        switch (this.type) {
            case "CASH":
                System.out.println(super.getDate() + " Cash withdraw $" + super.getAmount());
                break;
            case "ATM":
                System.out.println(super.getDate() + " ATM withdraw $" + super.getAmount());
                break;
        }
    }

    public double getCharges() {
        return this.charges;
    }

}
