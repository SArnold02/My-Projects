package modell.adts.fileTable;

import java.util.Map;
import java.util.Set;

import modell.exceptions.FileTableException;
import modell.exceptions.InterpreterExceptions;

public interface IFileTable<K,T> {
    T get(K key) throws InterpreterExceptions;
    void put(K key, T element) throws InterpreterExceptions;
    boolean isDefined(K key);
    Set<K> keySet();
    void remove(K key) throws FileTableException;
    String toString();
    Map<K,T> getContent();
    void setContent(Map<K,T> fileTable);
    IFileTable<K,T> clone();
}
