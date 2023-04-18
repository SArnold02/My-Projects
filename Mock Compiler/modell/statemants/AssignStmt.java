package modell.statemants;

import modell.adts.dictionary.IDictionary;
import modell.adts.heapTable.IHeapTable;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.exceptions.StmtException;
import modell.expressions.Exp;
import modell.programState.PrgState;
import modell.values.Value;

public class AssignStmt implements Stmt{
    String id;
    Exp e;

    public AssignStmt(String id, Exp e) {
        this.id = id;
        this.e = e;
    }

    @Override
    public String toString() {
        return this.id + "=" + e.toString(); 
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterExceptions {
        IDictionary<String, Value> vMap = state.getVarMap();
        IHeapTable<Value> heap = state.getHeap();

        if (!vMap.isDefined(this.id))
            throw new StmtException("Variable: " + this.id + ", is not declared!");

        Value v = e.eval(vMap, heap);

        vMap.put(this.id, v);
        
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        Type typeVar = types.get(this.id);
        Type typeExp = e.typeCheck(types);

        if (!typeVar.equals(typeExp))
            throw new StmtException("The variable: " + this.id + " and given value type do not match!");
       
        return types;
    }
}
