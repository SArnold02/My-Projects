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

public class HeapWriteStmt implements Stmt{
    String varName;
    Exp e;

    public HeapWriteStmt(String varName, Exp e) {
        this.varName = varName;
        this.e = e;
    }

    @Override
    public String toString() {
        return "wH(" + varName + "," + e.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterExceptions {
        IDictionary<String, Value> symTable = state.getVarMap();
        IHeapTable<Value> heap = state.getHeap();

        if (!symTable.isDefined(varName))
            throw new StmtException(varName + " is not difined!");

        Value symValue = symTable.get(varName);

        RefValue symRefValue = (RefValue)symValue;
        if (!heap.isDefined(symRefValue.getAddr()))
            throw new StmtException("No allocation in heap!");

        Value expValue = e.eval(symTable, heap);

        heap.putTo(symRefValue.getAddr(), expValue);

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
