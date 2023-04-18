package modell.statemants;

import modell.adts.dictionary.IDictionary;
import modell.adts.heapTable.IHeapTable;
import modell.adts.list.IList;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.expressions.Exp;
import modell.programState.PrgState;
import modell.values.Value;

public class PrintStmt implements Stmt{
    Exp e;

    public PrintStmt(Exp e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return "Print " + e.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterExceptions {
        IList<Value> out = state.getOut();
        IHeapTable<Value> heap = state.getHeap();

        out.add(e.eval(state.getVarMap(), heap));

        //System.out.println(e.eval(state.getVarMap()).toString());
        
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        e.typeCheck(types);
        return types;
    }
}
