package modell.statemants;

import modell.adts.dictionary.IDictionary;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.exceptions.StmtException;
import modell.programState.PrgState;
import modell.values.Value;

public class VarDeclStmt implements Stmt{
    String id;
    Type type;

    public VarDeclStmt(String id, Type type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.id + " declared as " + this.type.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterExceptions {
        IDictionary<String, Value> vMap = state.getVarMap();
        if (vMap.isDefined(this.id))
            throw new StmtException("Variable: " + this.id + " is already declared!");
        
        vMap.put(this.id, this.type.defaultValue());
            
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        types.put(this.id, this.type);
        return types;
    }
}
