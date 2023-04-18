package modell.statemants;

import javafx.util.Pair;
import modell.adts.dictionary.IDictionary;
import modell.adts.semaphore.ISemaphoreTable;
import modell.dataTypes.IntType;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.exceptions.StmtException;
import modell.programState.PrgState;
import modell.values.IntValue;
import modell.values.Value;

public class AcquireStmt implements Stmt{
    String var;

    public AcquireStmt(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return "acquire(" + this.var + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterExceptions {
        IDictionary<String, Value> varMap = state.getVarMap();
        if (!varMap.isDefined(this.var))
            throw new StmtException(this.var + " is not defined!");
            
        IntValue index = (IntValue)varMap.get(this.var);
        
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = state.getSemaphore();
        synchronized(semaphore){
            if (!semaphore.isDefined(index.getValue()))
                throw new StmtException("Given index in acquire is not a semaphore");

            Pair<Integer, java.util.List<Integer>> entry = semaphore.get(index.getValue());
            int listLength = entry.getValue().size();
            if (entry.getKey() <= listLength){
                Stmt acq = new AcquireStmt(this.var);
                state.getExecStack().push(acq);
            } else{
                if (!entry.getValue().contains(state.id)){
                    entry.getValue().add(state.id);
                }
            }
        }

        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        Type typeVar = types.get(this.var);
        if (!typeVar.equals(new IntType()))
            throw new StmtException(this.var + " is not of inttype!");

        return types;
    }
    
}
