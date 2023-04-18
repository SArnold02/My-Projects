package modell.dataTypes;

import modell.values.BoolValue;
import modell.values.Value;

public class BoolType implements Type{
    @Override
    public boolean equals(Object other) {
        if (other instanceof BoolType){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }
}
