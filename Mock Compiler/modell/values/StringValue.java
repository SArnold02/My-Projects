package modell.values;

import modell.dataTypes.StringType;
import modell.dataTypes.Type;

public class StringValue implements Value{
    String val;
    public StringValue(String data) {
        this.val = data;
    }

    @Override
    public String toString() {
        return this.val;
    }

    @Override
    public Type getType() {
        return new StringType();
    }
    
    public String getValue(){
        return this.val;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof StringValue)){
            return false;
        }
        return ((StringValue)other).getValue() == this.val;
    }
}
