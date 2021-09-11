package exceptions;

public class DataFormatException extends Exception {
    public DataFormatException() {
        super("File data is not in the correct format. Please refer to README for correct format.");
    }

    public DataFormatException(String aMessage) {
        super(aMessage);
    }
}
