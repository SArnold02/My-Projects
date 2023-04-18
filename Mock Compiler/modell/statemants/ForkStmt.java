package modell.statemants;

import java.io.BufferedReader;

import javafx.util.Pair;
import modell.adts.dictionary.IDictionary;
import modell.adts.fileTable.IFileTable;
import modell.adts.heapTable.IHeapTable;
import modell.adts.list.IList;
import modell.adts.semaphore.ISemaphoreTable;
import modell.adts.stack.IStack;
import modell.adts.stack.Stack;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.programState.PrgState;
import modell.values.StringValue;
import modell.values.Value;

public class ForkStmt implements Stmt{
    Stmt stmt;

    public ForkStmt(Stmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        return "fork(" + this.stmt.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterExceptions {
        IStack<Stmt> execStack = new Stack<Stmt>();
        execStack.push(this.stmt);

        IDictionary<String, Value> varMap = state.getVarMap().clone();
        IList<Value> out = state.getOut();
        IFileTable<StringValue, BufferedReader> fileTable = state.getFileTable();
        IHeapTable<Value> heap = state.getHeap();
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = state.getSemaphore();
        
        return new PrgState(execStack, varMap, out, fileTable, heap, semaphore);
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        stmt.typeCheck(types.clone());
        return types;
    }
}
