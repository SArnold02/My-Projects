package modell.expressions;

import modell.adts.dictionary.IDictionary;
import modell.adts.heapTable.IHeapTable;
import modell.dataTypes.IntType;
import modell.dataTypes.Type;
import modell.exceptions.ExpressException;
import modell.exceptions.InterpreterExceptions;
import modell.values.IntValue;
import modell.values.Value;

public class ArithExp implements Exp{
    Exp e1;
    Exp e2;
    Operaion operation;

    public enum Operaion{
        ADDITION("+"),
        SUBTRACTION("-"),
        MULTIPLICATION("*"),
        DIVISION("/");

        String string;

        Operaion(String string) {
            this.string = string;
        }

        @Override
        public String toString(){
            return this.string;
        }
    }
    
    public ArithExp(Operaion operation, Exp e1, Exp e2) {
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
            case ADDITION:
                return new IntValue(n1+n2);
            case SUBTRACTION:
                return new IntValue(n1-n2); 
            case MULTIPLICATION:
                return new IntValue(n1*n2); 
            case DIVISION:
                if (n2 == 0) throw new RuntimeException("Division by zero at " + e1.toString() + "/" + e2.toString() + "!");
                return new IntValue(n1/n2);  
        
            default:
                throw new ExpressException("Invalid arithmetic operation at " + e1.toString() + "/" + e2.toString() + "!");
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
                return new IntType();
            else
                throw new ExpressException(e2.toString() + " is not an int!");
        }
        else 
            throw new ExpressException(e1.toString() + " is not an int!");
    }
}
