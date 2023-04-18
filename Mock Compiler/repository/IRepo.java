package repository;

import java.util.List;

import modell.exceptions.InterpreterExceptions;
import modell.programState.PrgState;

public interface IRepo {
    public List<PrgState> getPrgList();
    public void setPrgList(List<PrgState> state);
    void logPrgStateExec(PrgState state) throws InterpreterExceptions;
}
