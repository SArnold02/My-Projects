package modell.exceptions;

public class StackException extends InterpreterExceptions{
    public StackException() {
    }

    public StackException(String message) {
        super("StackException: " + message);
    }
}
