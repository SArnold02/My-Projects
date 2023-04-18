package modell.programState;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.List;

import javafx.util.Pair;
import modell.adts.dictionary.IDictionary;
import modell.adts.fileTable.IFileTable;
import modell.adts.heapTable.IHeapTable;
import modell.adts.list.IList;
import modell.adts.semaphore.ISemaphoreTable;
import modell.adts.stack.IStack;
import modell.exceptions.InterpreterExceptions;
import modell.statemants.Stmt;
import modell.values.StringValue;
import modell.values.Value;

public class PrgState {
    IStack<Stmt> execStack;
    IDictionary<String, Value> varMap;
    IList<Value> out;
    IFileTable<StringValue, BufferedReader> fileTable;
    IHeapTable<Value> heap;
    ISemaphoreTable<Pair<Integer, List<Integer>>> semaphore;

    public int id;
    private static LinkedList<Integer> idList = new LinkedList<Integer>();

    public PrgState(IStack<Stmt> execStack, IDictionary<String, Value> varMap, IList<Value> out, IFileTable<StringValue, BufferedReader> fileTable, IHeapTable<Value> heap, ISemaphoreTable<Pair<Integer, List<Integer>>> semaphore) {
        this.execStack = execStack;
        this.varMap = varMap;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.semaphore = semaphore;
        this.id = newId();
    }

    private static int newId(){
        int var;
        synchronized (idList){
            try {
                var = idList.getLast();
                var++;
            } catch (Exception e) {
                var = 1;
            }
            idList.addLast(var);
        }
        return var;
    }

    public ISemaphoreTable<Pair<Integer, List<Integer>>> getSemaphore() {
        return this.semaphore;
    }

    public IStack<Stmt> getExecStack() {
        return this.execStack;
    }

    public IDictionary<String, Value> getVarMap() {
        return this.varMap;
    }

    public IList<Value> getOut() {
        return this.out;
    }
    
    public IFileTable<StringValue, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public IHeapTable<Value> getHeap() {
        return this.heap;
    }

    public boolean isNotCompleted() {
        if (this.execStack.isEmpty())
            return false;
            
        return true;
    }

    public PrgState oneStepExec() throws InterpreterExceptions{
        if (this.execStack.isEmpty())
            throw new InterpreterExceptions("Execution stack is empty!");

        Stmt execStmt = this.execStack.pop();
        return execStmt.execute(this);
    }

    @Override
    public String toString() {
        return "PrgState:\nid: " + Integer.toString(this.id) + "\nexecStack:\n" + execStack.toString() + "\nvarMap:\n" + varMap.toString() + "\nout:\n" + out.toString() + "\nfileTable:\n" + fileTable.toString() + "\nheapTable\n" + heap.toString() + "\nsemaphoreTable\n" + semaphore.toString();
    }
}
