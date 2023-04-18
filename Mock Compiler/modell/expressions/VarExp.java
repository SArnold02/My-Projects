package modell.expressions;

import modell.adts.dictionary.IDictionary;
import modell.adts.heapTable.IHeapTable;
import modell.dataTypes.Type;
import modell.exceptions.ExpressException;
import modell.exceptions.InterpreterExceptions;
import modell.values.Value;

public class VarExp implements Exp{
    String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public Value eval(IDictionary<String, Value> table, IHeapTable<Value> heap) throws InterpreterExceptions {
        if (!table.isDefined(this.id))
            throw new ExpressException("Variable: " + this.id + " not defined!");
        
        return table.get(this.id);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        return types.get(this.id);
    }
}
