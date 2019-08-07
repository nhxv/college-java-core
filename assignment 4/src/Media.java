public class Media extends Item {
    private double size;
    private String format;

    public Media () {
        super();
        this.size = 0;
        this.format = "";
    }

    public Media (double size, String format, int time, String from, String to, double charge) {
        super(time, from, to, charge);
        this.size = size;
        this.format = format;
    }

    public double getMediaCharge() {
        return super.getCharge();
    }

    public String toString () {
        return "\tMEDIA: Size:" + size + " MB, Format: " + format + super.toString();
    }
}
