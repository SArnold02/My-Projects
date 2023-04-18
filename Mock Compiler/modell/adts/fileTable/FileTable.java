package modell.adts.fileTable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import modell.exceptions.FileTableException;

public class FileTable<K,T> implements IFileTable<K,T>{
    Map<K,T> dict;

    public FileTable() {
        this.dict = new HashMap<K,T>();   
    }

    @Override
    public T get(K key) throws FileTableException {
        if (key == null)
            throw new FileTableException("Null key given in get function for FileTable!");
        return this.dict.get(key);
    }

    @Override
    public void put(K key, T element) throws FileTableException {
        if (key == null)
            throw new FileTableException("Null key given in put function for FileTable!");
        if (element == null)
            throw new FileTableException("Null value given in put function for FileTable!");

        this.dict.put(key, element);
    }

    @Override
    public boolean isDefined(K key) {
        return this.dict.containsKey(key);
    }

    @Override
    public String toString() {
        return this.dict.entrySet().stream().map(e -> e.getKey() + "\n").reduce("", String::concat);
    }

    @Override
    public Set<K> keySet() {
        return this.dict.keySet();
    }

    @Override
    public void remove(K key) throws FileTableException {
        if (key == null)
            throw new FileTableException("Null key given in remove function for FileTable!");
            
        this.dict.remove(key);
    }

    public void setContent(Map<K,T> fileTable){
        this.dict = fileTable;
    }

    @Override
    public Map<K, T> getContent() {
        return this.dict;
    }

    public IFileTable<K,T> clone(){
        Map<K,T> cloneFileTable = this.dict.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        IFileTable<K,T> newFileTalbe = new FileTable<K,T>();
        newFileTalbe.setContent(cloneFileTable);
        return newFileTalbe;
    }
}
