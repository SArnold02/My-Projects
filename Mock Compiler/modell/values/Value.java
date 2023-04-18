package modell.values;

import modell.dataTypes.Type;

public interface Value {
    Type getType();
    String toString();
    boolean equals(Object other);
}
