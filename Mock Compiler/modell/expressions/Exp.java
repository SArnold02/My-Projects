package modell.expressions;

import modell.adts.dictionary.IDictionary;
import modell.adts.heapTable.IHeapTable;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.values.Value;

public interface Exp {
    Value eval(IDictionary<String,Value> table, IHeapTable<Value> heap) throws InterpreterExceptions;
    String toString();
    Type typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions;
}
