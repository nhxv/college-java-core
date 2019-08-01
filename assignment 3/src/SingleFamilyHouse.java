public class SingleFamilyHouse extends Property {

    private int backyardSize;

    public SingleFamilyHouse() {
        super();
        this.backyardSize = 0;
    }

    public SingleFamilyHouse(String address, double offeredPrice, int year, int backyardSize) {
        super(address, offeredPrice, year);
        this.backyardSize = backyardSize;
    }

    public int getBackyardSize() {
        return backyardSize;
    }

    public void setBackyardSize(int backyardSize) {
        this.backyardSize = backyardSize;
    }

    @Override
    public String toString() {
        String priceDisplay = String.format("%.2f", super.getOfferedPrice());
        String backyard = this.backyardSize + " (sqft)";
        return String.format(format, super.getAddress(),"$" + priceDisplay, super.getYear(), backyard);
    }

}
