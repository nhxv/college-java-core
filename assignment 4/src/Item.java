public class Item {
    private int time;
    private String from;
    private String to;
    private double charge;

    public Item() {
        this.from = "";
        this.to = "";
    }

    public Item(int time, String from, String to, double charge) {
        this();
        this.time = time;
        this.from = from;
        this.to = to;
        this.charge = charge;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getCharge() {
        return this.charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    @Override
    public String toString() {
        return " Time:" + this.time + ",From:" + this.from + ",To:" + this.to;
    }
}
