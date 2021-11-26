package by.bsuir.intelligentscheduler.exception;

public class RequiredFieldsMissingException extends RuntimeException {

    public RequiredFieldsMissingException() {
        super();
    }

    public RequiredFieldsMissingException(String message) {
        super(message);
    }

    public RequiredFieldsMissingException(String message, Throwable cause) {
        super(message, cause);
    }
}
