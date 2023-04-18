package modell.values;

import modell.dataTypes.RefType;
import modell.dataTypes.Type;

public class RefValue implements Value{
    int address;
    Type locationType;

    public int getAddr() {
        return this.address;
    }

    public Type getLocationType() {
        return this.locationType;
    }

    public RefValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    @Override
    public String toString() {
        return "(" + Integer.toString(this.address) + "," + this.locationType.toString() + ")";
    }

    @Override
    public Type getType() {
        return new RefType(this.locationType);
    }
    
    @Override
    public boolean equals(Object other){
        if (!(other instanceof RefValue)){
            return false;
        }
        return ((RefValue)other).getAddr() == this.address;
    }

}
