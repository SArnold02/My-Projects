package modell.statemants;

import modell.adts.dictionary.IDictionary;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.programState.PrgState;

public interface Stmt {
    PrgState execute(PrgState state) throws InterpreterExceptions;
    String toString();
    IDictionary<String, Type> typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions;
}
