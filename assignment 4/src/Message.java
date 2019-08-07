public class Message<T extends Item> extends Item {
    private T message;

    public Message() {
        super();
        this.message = null;
    }

    public Message(T message) {
        this.message = message;
    }

    public T getMessage() {
        return this.message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message.toString();
    }
}
