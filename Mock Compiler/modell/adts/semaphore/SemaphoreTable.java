package modell.adts.semaphore;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import modell.exceptions.InterpreterExceptions;
import modell.exceptions.SemaphoreException;

public class SemaphoreTable<T> implements ISemaphoreTable<T>{
    Map<Integer, T> dict;
    int freeLocation;

    public SemaphoreTable() {
        this.dict = new HashMap<Integer, T>();
        this.freeLocation = 1;
    }

    @Override
    public String toString() {
        return this.dict.entrySet().stream().map(e -> e.getKey().toString() + " --> " + e.getValue().toString() + "\n").reduce("", String::concat);
    }

    @Override
    public T get(int key) throws InterpreterExceptions {
        synchronized(this){
            if (key <= 0)
                throw new SemaphoreException("Null key given in get function for semaphoreTable!");

            return this.dict.get(key);
        }
    }

    @Override
    public int getFreeLocation() {
        synchronized(this){
            return this.freeLocation;
        }
    }

    @Override
    public boolean isDefined(int key) {
        synchronized(this){
            return this.dict.containsKey(key);
        }
    }

    @Override
    public Set<Integer> keySet() {
        synchronized(this){
            return this.dict.keySet();
        }
    }

    @Override
    public void put(T element) throws InterpreterExceptions {
        synchronized(this){
            if (element == null)
                throw new SemaphoreException("Null value given in put function for semaphoreTable!");

            this.dict.put(this.freeLocation, element);
            
            this.freeLocation += 1;
        }
    }

    @Override
    public void putTo(int key, T element) throws InterpreterExceptions {
        synchronized(this){
            if (key <= 0)
                throw new SemaphoreException("Invalid key is given for semaphoreTable!");
            if (element == null)
                throw new SemaphoreException("Null value given in put function for semaphoreTable!");
                
            this.dict.put(key, element);
        }
    }

    @Override
    public Map<Integer, T> getContent() {
        synchronized(this){
            return this.dict;
        }
    }

    @Override
    public void setContent(Map<Integer, T> dictionary) {
        synchronized(this){
            this.dict = dictionary;
        }
    }

    public ISemaphoreTable<T> clone(){
        synchronized(this){
            Map<Integer,T> cloneSem = this.dict.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            ISemaphoreTable<T> newSem = new SemaphoreTable<T>();
            newSem.setContent(cloneSem);
            return newSem;
        }
    }
}
