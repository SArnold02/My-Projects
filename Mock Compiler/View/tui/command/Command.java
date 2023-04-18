package View.tui.command;

import modell.exceptions.InterpreterExceptions;

public abstract class Command {
    String key, description;
    public Command(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public abstract void execute() throws InterpreterExceptions;

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.key + " : " + this.description;
    }
}
