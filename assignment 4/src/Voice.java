public class Voice extends Item {
    private int duration;
    private String format;

    public Voice () {
        super();
        duration = 0;
        format = "";
    }

    public Voice (int duration, String format, int time, String from, String to, double charge) {
        super(time, from, to, charge);
        this.duration = duration;
        this.format = format;
    }

    public double getVoiceCharge() {
        return super.getCharge();
    }

    public String toString () {
        return "\tVOICE: Duration:" + duration + " (sec), Format: " + format + super.toString();
    }
}
