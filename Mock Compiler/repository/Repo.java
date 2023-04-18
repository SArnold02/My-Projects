package repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import modell.exceptions.InterpreterExceptions;
import modell.exceptions.RepoException;
import modell.programState.PrgState;

public class Repo implements IRepo{
    List<PrgState> state;
    String filePath;

    public Repo(PrgState state, String filePath) {
        this.state = new ArrayList<PrgState>();
        this.state.add(state);
        this.filePath = filePath;
    }

    public List<PrgState> getPrgList() {
        return state;
    }

    public void setPrgList(List<PrgState> state) {
        this.state = state;
    }

    @Override
    public void logPrgStateExec(PrgState state) throws InterpreterExceptions {
        try (PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(this.filePath, true)))) {
            logFile.println("-------------------------\nPrgState id: " + Integer.toString(state.id));

            logFile.println("--------------\nExeStack:");
            logFile.println(state.getExecStack().toString());

            logFile.println("\nSymTable:");
            logFile.println(state.getVarMap().toString());

            logFile.println("\nOut:");
            logFile.println(state.getOut().toString());

            logFile.println("\nFileTable:");
            logFile.println(state.getFileTable().toString());

            logFile.println("\nHeapTable:");
            logFile.println(state.getHeap().toString());

            logFile.println("\nSemaphoreTable:");
            logFile.println(state.getSemaphore().toString());

        } catch (IOException e) {
            throw new RepoException(e.getMessage());
        }
    }
}
