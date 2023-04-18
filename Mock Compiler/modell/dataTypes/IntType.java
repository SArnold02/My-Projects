package modell.dataTypes;

import modell.values.IntValue;
import modell.values.Value;

public class IntType implements Type{
    @Override
    public boolean equals(Object other) {
        if (other instanceof IntType){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }
}
