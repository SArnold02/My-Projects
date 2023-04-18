package modell.expressions;

import modell.adts.dictionary.IDictionary;
import modell.adts.heapTable.IHeapTable;
import modell.dataTypes.BoolType;
import modell.dataTypes.IntType;
import modell.dataTypes.Type;
import modell.exceptions.ExpressException;
import modell.exceptions.InterpreterExceptions;
import modell.values.BoolValue;
import modell.values.IntValue;
import modell.values.Value;

public class RelationalExp implements Exp{
    Exp e1;
    Exp e2;
    ROperaion operation;

    public enum ROperaion{
        SMALLER("<"),
        SMALLER_OR_EQUAL("<="),
        EQUAL("=="),
        BIGGER_OR_EQUAL("=>"),
        BIGGER(">");

        String string;

        ROperaion(String string) {
            this.string = string;
        }

        @Override
        public String toString(){
            return this.string;
        }
    }
    
    public RelationalExp(ROperaion operation, Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.operation = operation;
    }

    @Override
    public Value eval(IDictionary<String, Value> table, IHeapTable<Value> heap) throws InterpreterExceptions {
        Value v1,v2;

        v1 = e1.eval(table, heap);
        v2 = e2.eval(table, heap);
     
        IntValue i1 = (IntValue)v1;
        IntValue i2 = (IntValue)v2;
        int n1 = i1.getValue();
        int n2 = i2.getValue();

        switch (this.operation) {
            case SMALLER:
                return new BoolValue(n1 < n2);
            case SMALLER_OR_EQUAL:
                return new BoolValue(n1 <= n2);
            case EQUAL:
                return new BoolValue(n1 == n2);
            case BIGGER_OR_EQUAL:
                return new BoolValue(n1 >= n2);
            case BIGGER:
                return new BoolValue(n1 > n2); 
        
            default:
                throw new ExpressException("Invalid arithmetic operation!");
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

        if (t1.equals(new IntType())){
            if (t2.equals(new IntType()))
                return new BoolType();
            else
                throw new ExpressException(e2.toString() + " is not an int!");
        }
        else 
            throw new ExpressException(e1.toString() + " is not an int!");
    }
}
