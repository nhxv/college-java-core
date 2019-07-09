import java.util.Objects;

public class Vehicle implements Comparable<Vehicle> {

    private String make;
    private String model;
    private int year;
    private double price;
    private boolean reserved;

    //non-default constructor
    public Vehicle(String make, String model, int year, double price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.reserved = false;
    }

    //default constructor
    public Vehicle() {}

    //accessor
    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public int getYear() {
        return this.year;
    }

    public double getPrice() {
        return this.price;
    }

    public boolean getReserved() {
        return this.reserved;
    }

    //mutator
    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    //sort vehicle by year
    @Override
    public int compareTo(Vehicle vehicle) {
        return this.year - vehicle.getYear();
    }

    //equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return year == vehicle.year &&
                Double.compare(vehicle.price, price) == 0 &&
                Objects.equals(make, vehicle.make) &&
                Objects.equals(model, vehicle.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, year, price);
    }

    @Override
    public String toString() {
        return this.make + " " + this.model + ";" + this.year + ";$ " + this.price;
    }


}
