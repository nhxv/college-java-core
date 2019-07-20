import java.util.Objects;

public abstract class Transaction implements Comparable<Transaction> {

    private String id;
    protected String date;
    protected double amount;

    public Transaction() {
        this.id = "";
        this.date = "";
    }

    public String getId() {
        return this.id;
    }

    public String getDate() {
        return this.date;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public abstract void list();

    @Override
    public String toString() {
        return this.id + this.date + this.amount;
    }

    //sort transaction by transaction amount
    @Override
    public int compareTo(Transaction transaction) {
        double result = this.amount - transaction.amount;
        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }
}
