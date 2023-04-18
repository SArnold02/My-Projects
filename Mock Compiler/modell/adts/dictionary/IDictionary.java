package modell.adts.dictionary;

import java.util.Map;
import java.util.Set;

import modell.exceptions.InterpreterExceptions;

public interface IDictionary<K,T> {
    T get(K key) throws InterpreterExceptions;
    void put(K key, T element) throws InterpreterExceptions;
    boolean isDefined(K key);
    Set<K> keySet();
    String toString();
    Map<K,T> getContent();
    void setContent(Map<K,T> dictionary);
    IDictionary<K,T> clone();
}
