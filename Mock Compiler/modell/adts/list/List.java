package modell.adts.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import modell.exceptions.ListException;

public class List<T> implements IList<T>{
    ArrayList<T> list;

    public List() {
        this.list = new java.util.ArrayList<T>();
    }

    @Override
    public void add(T elem) {
        this.list.add(elem);        
    }

    @Override
    public T get(int index) throws ListException {
        if (index < 0 || index >=this.list.size())
            throw new ListException("Index out of bonds in get function for List!");
            
        return this.list.get(index);
    }

    @Override
    public String toString() {
        return this.list.stream().map(v -> v.toString() + "\n").reduce("", String::concat);
    }

    @Override
    public Iterator<T> iterator() {
        return this.list.iterator();
    }

    @Override
    public ArrayList<T> getContent() {
        return this.list;
    }

    @Override
    public void setContent(ArrayList<T> list) {
        this.list = list;
    }

    public IList<T> clone(){
        ArrayList<T> cloneList = (ArrayList<T>) this.list.stream().collect(Collectors.toList());
        IList<T> newList = new List<T>();
        newList.setContent(cloneList);
        return newList;
    }
}
