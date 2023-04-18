package modell.exceptions;

public class SemaphoreException extends InterpreterExceptions{
    public SemaphoreException() {
    }

    public SemaphoreException(String message) {
        super("SemaphoreException: " + message);
    }
}
