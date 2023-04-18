package modell.adts.heapTable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import modell.exceptions.HeapException;
import modell.exceptions.InterpreterExceptions;

public class HeapTable<T> implements IHeapTable<T>{
    Map<Integer, T> dict;
    int freeLocation;

    public HeapTable() {
        this.dict = new HashMap<Integer, T>();
        this.freeLocation = 1;
    }

    @Override
    public String toString() {
        return this.dict.entrySet().stream().map(e -> e.getKey().toString() + " --> " + e.getValue().toString() + "\n").reduce("", String::concat);
    }

    @Override
    public T get(int key) throws InterpreterExceptions {
        if (key == 0)
            throw new HeapException("Null key given in get function for HeapTable!");

        return this.dict.get(key);
    }

    @Override
    public int getFreeLocation() {
        return this.freeLocation;
    }

    @Override
    public boolean isDefined(int key) {
        return this.dict.containsKey(key);
    }

    @Override
    public Set<Integer> keySet() {
        return this.dict.keySet();
    }

    @Override
    public void put(T element) throws InterpreterExceptions {
        if (element == null)
            throw new HeapException("Null value given in put function for HeapTable!");

        this.dict.put(this.freeLocation, element);
        
        this.freeLocation += 1;
    }

    @Override
    public void putTo(int key, T element) throws InterpreterExceptions {
        if (key <= 0)
            throw new HeapException("Invalid key is given for HeapTable!");
        if (element == null)
            throw new HeapException("Null value given in put function for HeapTable!");
            
        this.dict.put(key, element);
    }

    @Override
    public Map<Integer, T> getContent() {
        return this.dict;
    }

    @Override
    public void setContent(Map<Integer, T> dictionary) {
        this.dict = dictionary;
    }

    public IHeapTable<T> clone(){
        Map<Integer,T> cloneHeap = this.dict.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        IHeapTable<T> newHeap = new HeapTable<T>();
        newHeap.setContent(cloneHeap);
        return newHeap;
    }
}
