package modell.adts.list;

import java.util.ArrayList;
import java.util.Iterator;

import modell.exceptions.InterpreterExceptions;

public interface IList<T> extends Iterable<T>{
    T get(int index) throws InterpreterExceptions;
    void add(T elem);
    String toString();
    Iterator<T> iterator(); 
    ArrayList<T> getContent();
    void setContent(ArrayList<T> list);
    IList<T> clone();
}
