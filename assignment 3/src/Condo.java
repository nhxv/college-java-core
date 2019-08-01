public class Condo extends Property {

    private double HOAfee;

    public Condo() {
        super();
        this.HOAfee = 100;
    }

    public Condo(String address, double offeredPrice, int year, double HOAfee) {
        super(address, offeredPrice, year);
        this.HOAfee = HOAfee;
    }

    public double getHOAfee() {
        return HOAfee;
    }

    public void setHOAfee(double HOAfee) {
        this.HOAfee = HOAfee;
    }

    @Override
    public String toString() {
        String feeDisplay = String.format("%.2f", this.HOAfee);
        String priceDisplay = String.format("%.2f", super.getOfferedPrice());
        String fee = "HOA fee: $" + feeDisplay;
        return String.format(format, super.getAddress(), "$" + priceDisplay, super.getYear(), fee);
    }
}
