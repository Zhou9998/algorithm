package zsc;

public class QueueEmptyException extends RuntimeException {
    public QueueEmptyException() {
    }

    public QueueEmptyException(String message) {
        super(message);
    }
}
