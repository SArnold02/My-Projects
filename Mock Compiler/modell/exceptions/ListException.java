package modell.exceptions;

public class ListException extends InterpreterExceptions{
    public ListException() {
    }

    public ListException(String message) {
        super("ListException: " + message);
    }
}