package modell.expressions;

import modell.adts.dictionary.IDictionary;
import modell.adts.heapTable.IHeapTable;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.values.Value;

public class ValueExp implements Exp{
    Value v;

    public ValueExp(Value v) {
        this.v = v;
    }

    @Override
    public Value eval(IDictionary<String, Value> table, IHeapTable<Value> heap){
        return v;
    }

    @Override
    public String toString() {
        return v.toString();
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        return v.getType();
    }
}
