package modell.values;

import modell.dataTypes.IntType;
import modell.dataTypes.Type;

public class IntValue implements Value{
    int val;
    
    public IntValue(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return Integer.toString(this.val);
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    public int getValue() {
        return this.val;
    }
    
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof IntValue)){
            return false;
        }
        return ((IntValue)other).getValue() == this.val;
    }
}
