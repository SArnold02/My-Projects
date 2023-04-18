package modell.dataTypes;

import modell.values.StringValue;
import modell.values.Value;

public class StringType implements Type{
    @Override
    public boolean equals(Object other) {
        if (other instanceof StringType){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }
}
