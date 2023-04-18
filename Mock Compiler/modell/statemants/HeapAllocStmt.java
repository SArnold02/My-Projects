package modell.statemants;

import modell.adts.dictionary.IDictionary;
import modell.adts.heapTable.IHeapTable;
import modell.dataTypes.RefType;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.exceptions.StmtException;
import modell.expressions.Exp;
import modell.programState.PrgState;
import modell.values.RefValue;
import modell.values.Value;

public class HeapAllocStmt implements Stmt{
    Exp e;
    String varName;

    public HeapAllocStmt(String varName, Exp e) {
        this.e = e;
        this.varName = varName;
    }

    @Override
    public String toString() {
        return varName + " alloc to " + e.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterExceptions {
        IDictionary<String, Value> symTable = state.getVarMap();
        IHeapTable<Value> heap = state.getHeap();

        if (!symTable.isDefined(varName))
            throw new StmtException(varName + " was not declared!");

        Value symValue = symTable.get(this.varName);
  
        RefValue symRefValue = (RefValue)symValue;
        Value expValue = e.eval(symTable, heap);

        int heapKey = heap.getFreeLocation();
        heap.put(expValue);

        RefValue newRefValue = new RefValue(heapKey, symRefValue.getLocationType());
        symTable.put(varName, newRefValue);
        
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        Type typeVar = types.get(this.varName);
        Type typeExp = e.typeCheck(types);

        if (!typeVar.equals(new RefType(typeExp)))
            throw new StmtException("The given expression and the variable type do not match!");
        
        return types;
    }
}
