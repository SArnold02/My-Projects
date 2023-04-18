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

public class WhileStmt implements Stmt{
    Exp e;
    Stmt s;
    
    public WhileStmt(Exp e, Stmt s) {
        this.e = e;
        this.s = s;
    }

    @Override
    public String toString() {
        return "while(" + e.toString() + "){" + s.toString() + "}";
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterExceptions {
        IDictionary<String, Value> symTable = state.getVarMap();
        IHeapTable<Value> heap = state.getHeap();
        IStack<Stmt> stack = state.getExecStack();

        Value expValue = e.eval(symTable, heap);
        
        BoolValue expBoolValue = (BoolValue)expValue;
        if (expBoolValue.getValue()){
            stack.push(new WhileStmt(this.e, this.s));
            stack.push(this.s);
        }
        
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        Type typeExp = e.typeCheck(types);
        if (!typeExp.equals(new BoolType()))
            throw new StmtException("Given expression " + e.toString() + " is not a boolean!");

        s.typeCheck(types.clone());
        return types;
    }
}
