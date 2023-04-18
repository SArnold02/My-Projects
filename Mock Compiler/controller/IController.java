package controller;

import java.util.List;

import modell.exceptions.InterpreterExceptions;
import modell.programState.PrgState;

public interface IController {
    void oneStepForAll(List<PrgState> prgList) throws InterpreterExceptions;
    void completeExec() throws InterpreterExceptions;
    void oneStepExec() throws InterpreterExceptions;
    List<PrgState> removeCompletedPrgs(List<PrgState> prgList);
}
