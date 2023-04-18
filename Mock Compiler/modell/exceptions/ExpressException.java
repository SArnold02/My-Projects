package modell.exceptions;

public class ExpressException extends InterpreterExceptions{

    public ExpressException() {
    }

    public ExpressException(String message) {
        super("ExpressionException: " + message);
    }
}
