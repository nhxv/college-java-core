public class Text extends Item {
    private String content;

    public Text () {
        super();
        this.content = "";
    }

    public Text (String text, int time, String from, String to, double charge) {
        super(time, from, to, charge);
        this.content = text;
    }

    public double getTextCharge() {
        return super.getCharge();
    }

    public String toString () {
        return "\tTEXT: " + this.content + super.toString();
    }
}
