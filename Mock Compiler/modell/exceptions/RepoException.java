package modell.exceptions;

public class RepoException extends InterpreterExceptions{
    public RepoException() {
    }

    public RepoException(String message) {
        super("ListException: " + message);
    }
}
