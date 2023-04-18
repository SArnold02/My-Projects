package modell.dataTypes;

import modell.values.RefValue;
import modell.values.Value;

public class RefType implements Type{
    Type inner;

    public RefType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return this.inner;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof RefType){
            RefType var = (RefType)other;
            return this.inner.equals(var.getInner());
        }
        return false;
    }

    @Override
    public String toString() {
        return "Ref(" + this.inner.toString() + ")";
    }

    @Override
    public Value defaultValue() {
        return new RefValue(0,this.inner);
    }
}