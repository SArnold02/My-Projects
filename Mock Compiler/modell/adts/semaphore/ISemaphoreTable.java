package modell.adts.semaphore;

import java.util.Map;
import java.util.Set;

import modell.exceptions.InterpreterExceptions;

public interface ISemaphoreTable<T> {
    T get(int key) throws InterpreterExceptions;
    void put(T element) throws InterpreterExceptions;
    void putTo(int key, T element) throws InterpreterExceptions;
    boolean isDefined(int key);
    Set<Integer> keySet();
    int getFreeLocation();
    String toString();
    Map<Integer,T> getContent();
    void setContent(Map<Integer,T> dictionary);
    ISemaphoreTable<T> clone();
}
