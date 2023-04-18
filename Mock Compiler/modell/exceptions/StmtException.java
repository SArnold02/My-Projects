package modell.exceptions;

public class StmtException extends InterpreterExceptions{
    public StmtException() {
    }

    public StmtException(String message) {
        super("StatementException: " + message);
    }
}