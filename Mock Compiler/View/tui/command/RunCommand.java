package View.tui.command;

import controller.Controller;
import modell.exceptions.InterpreterExceptions;

public class RunCommand extends Command{
    Controller ctr;

    public RunCommand(String key, String description, Controller ctr) {
        super(key, description);
        this.ctr = ctr;
    }

    @Override
    public void execute() throws InterpreterExceptions {
        ctr.completeExec();
    }
}
