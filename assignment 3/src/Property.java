import java.util.Objects;

public class Property {

    private String address;
    private double offeredPrice;
    private int year;
    private Property next;

    public static String format = "%-65s %-20s %-20s %-20s\n";

    public Property() {
        this.address = "";
        this.next = null;
    }

    public Property(String address, double offeredPrice, int year) {
        this();
        this.address = address;
        this.offeredPrice = offeredPrice;
        this.year = year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Property getNext() {
        return next;
    }

    public void setNext(Property property) {
        this.next = property;
    }

    @Override
    public String toString() {
        String property = String.format(format, this.address,"$" + this.offeredPrice, this.year, "");
        return property;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return year == property.year &&
                Objects.equals(address, property.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, year);
    }
}
