package modell.values;

import modell.dataTypes.BoolType;
import modell.dataTypes.Type;

public class BoolValue implements Value{
    boolean val;

    public BoolValue(boolean val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return Boolean.toString(this.val);
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    public boolean getValue() {
        return this.val;
    }
    
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BoolValue)){
            return false;
        }
        return ((BoolValue)other).getValue() == this.val;
    }
}
