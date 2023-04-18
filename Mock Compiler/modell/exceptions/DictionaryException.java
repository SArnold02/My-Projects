package modell.exceptions;

public class DictionaryException extends InterpreterExceptions{
    public DictionaryException() {
    }

    public DictionaryException(String message) {
        super("DictionaryException: " + message);
    }
}