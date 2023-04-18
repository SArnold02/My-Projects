package modell.statemants;

import java.util.ArrayList;

import javafx.util.Pair;
import modell.adts.dictionary.IDictionary;
import modell.adts.heapTable.IHeapTable;
import modell.adts.semaphore.ISemaphoreTable;
import modell.dataTypes.IntType;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.exceptions.StmtException;
import modell.expressions.Exp;
import modell.programState.PrgState;
import modell.values.IntValue;
import modell.values.Value;

public class CrtSemaphoreStmt implements Stmt{
    String var;
    Exp exp;
    
    public CrtSemaphoreStmt(String var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "semaphore(" + var + "," + exp + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterExceptions {
        IDictionary<String, Value> varMap = state.getVarMap();
        IHeapTable<Value> heap = state.getHeap();

        if (!varMap.isDefined(this.var))
            throw new StmtException(this.var + " is not defined!");

        IntValue number1 = (IntValue)this.exp.eval(varMap, heap);
        
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = state.getSemaphore();
        synchronized(semaphore){
            int freeloc = semaphore.getFreeLocation();
            semaphore.put(new Pair<Integer, java.util.List<Integer>>(number1.getValue(), new ArrayList<Integer>()));
            varMap.put(this.var, new IntValue(freeloc));
        }

        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        Type typeVar = types.get(this.var);
        Type typeExp = exp.typeCheck(types);
        if(!typeExp.equals(new IntType()) || !typeVar.equals(new IntType()))
            throw new StmtException(exp + " is not of inttype!");
        
        return types;
    }
    
}
