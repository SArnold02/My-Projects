package modell.expressions;

import modell.adts.dictionary.IDictionary;
import modell.adts.heapTable.IHeapTable;
import modell.dataTypes.BoolType;
import modell.dataTypes.Type;
import modell.exceptions.ExpressException;
import modell.exceptions.InterpreterExceptions;
import modell.values.BoolValue;
import modell.values.Value;

public class LogicExp implements Exp{
    Exp e1;
    Exp e2;
    LogicOperations operation; //1(and), 2(or)

    public enum LogicOperations{
        AND("and"),
        OR("or");

        String string;

        LogicOperations(String string){
            this.string = string;
        }

        @Override
        public String toString(){
            return this.string;
        }
    }

    public LogicExp(LogicOperations operation, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.operation = operation;
    }

    @Override
    public Value eval(IDictionary<String, Value> table, IHeapTable<Value> heap) throws InterpreterExceptions {
        Value v1,v2;

        v1 = e1.eval(table, heap);
        v2 = e2.eval(table, heap);

        BoolValue b1 = (BoolValue)v1;
        BoolValue b2 = (BoolValue)v2;
        boolean t1 = b1.getValue();
        boolean t2 = b2.getValue();

        switch (this.operation) {
            case AND:
                return new BoolValue(t1 && t2);
            case OR:
                return new BoolValue(t1 || t2);

            default:
                throw new ExpressException("Invalid logical operation!");
        }
    }

    @Override
    public String toString() {
        return e1.toString() + operation.toString() + e2.toString();
    }

    @Override
    public Type typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        Type t1, t2;

        t1 = e1.typeCheck(types);
        t2 = e2.typeCheck(types);
        
        if (t1.equals(new BoolType())){
            if (t2.equals(new BoolType()))
                return new BoolType();
            else
                throw new ExpressException(e2.toString() + " is not a bool!");
        }
        else 
            throw new ExpressException(e1.toString() + " is not a bool!");
    }
}
