package exceptions;

public class DataAccessException extends Exception {
    public DataAccessException() {
        super("File data can not be accessed. Please try again.");
    }

    public DataAccessException(String aMessage) {
        super(aMessage);
    }
}
