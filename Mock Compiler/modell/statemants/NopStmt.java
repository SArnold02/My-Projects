package modell.statemants;

import modell.adts.dictionary.IDictionary;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.programState.PrgState;

public class NopStmt implements Stmt{
    @Override
    public String toString() {
        return "NoStmt";
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterExceptions {
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        return types;
    }
}
