package mate.academy.internetshop.exception;

public class DataProcessingException extends Exception {
    public DataProcessingException(String message, Exception e) {
        super(message, e);
    }
}
