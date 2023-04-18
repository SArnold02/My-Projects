package modell.expressions;

import modell.adts.dictionary.IDictionary;
import modell.adts.heapTable.IHeapTable;
import modell.dataTypes.RefType;
import modell.dataTypes.Type;
import modell.exceptions.ExpressException;
import modell.exceptions.InterpreterExceptions;
import modell.values.RefValue;
import modell.values.Value;

public class HeapReadExp implements Exp{
    Exp e;

    public HeapReadExp(Exp e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return "rH(" + e.toString() + ")";
    }

    @Override
    public Value eval(IDictionary<String, Value> table, IHeapTable<Value> heap) throws InterpreterExceptions {
        Value expValue = e.eval(table, heap);
 
        RefValue expRefValue = (RefValue)expValue;
        
        if (!heap.isDefined(expRefValue.getAddr()))
            throw new ExpressException("There is no heap memory allocated at " + e.toString() + "!");
        
        return heap.get(expRefValue.getAddr());
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        Type t = e.typeCheck(types);

        if (!(t instanceof RefType))
            throw new ExpressException("Given expression " + e.toString() + " is not of RefType!");
        
        RefType r = (RefType) t;
        return r.getInner();
    }
}
