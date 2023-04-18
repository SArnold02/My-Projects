package modell.adts.stack;

import java.util.Iterator;
import modell.exceptions.InterpreterExceptions;

public interface IStack<T> extends Iterable<T>{
    T pop() throws InterpreterExceptions;
    void push(T element);
    boolean isEmpty();
    String toString();
    Iterator<T> iterator();
    java.util.Stack<T> getContent();
    void setContent(java.util.Stack<T> stack);
    IStack<T> clone();
}
