package modell.dataTypes;

import modell.values.Value;

public interface Type {
    boolean equals(Object other);
    String toString();
    Value defaultValue();
}