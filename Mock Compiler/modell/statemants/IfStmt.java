package modell.statemants;

import modell.adts.dictionary.IDictionary;
import modell.adts.heapTable.IHeapTable;
import modell.adts.stack.IStack;
import modell.dataTypes.BoolType;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.exceptions.StmtException;
import modell.expressions.Exp;
import modell.programState.PrgState;
import modell.values.BoolValue;
import modell.values.Value;

public class IfStmt implements Stmt{
    Exp e;
    Stmt first;
    Stmt second;
    
    public IfStmt(Exp e, Stmt first, Stmt second) {
        this.e = e;
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "IF " + e.toString() + " THAN " + first.toString() + " ELSE " + second.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterExceptions {
        IDictionary<String, Value> vMap = state.getVarMap();
        IHeapTable<Value> heap = state.getHeap();

        Value v = e.eval(vMap, heap);

        IStack<Stmt> stateStack = state.getExecStack();
        BoolValue vBool = (BoolValue)v;
        if (vBool.getValue()){
            stateStack.push(first);
        } else {
           stateStack.push(second);
        }
        
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        Type typeExp = e.typeCheck(types);
        if (!typeExp.equals(new BoolType()))
            throw new StmtException("Given expression " + e.toString() + " is not a boolean!");

        first.typeCheck(types.clone());
        second.typeCheck(types.clone());    
        return types;
    }
}