package modell.adts.dictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import modell.exceptions.DictionaryException;

public class Dictionary<K,T> implements IDictionary<K,T>{
    Map<K,T> dict;

    public Dictionary() {
        this.dict = new HashMap<K,T>();   
    }

    @Override
    public T get(K key) throws DictionaryException {
        if (key == null)
            throw new DictionaryException("Null key given in get function for Dictionary!");
        return this.dict.get(key);
    }

    @Override
    public void put(K key, T element) throws DictionaryException {
        if (key == null)
            throw new DictionaryException("Null key given in put function for Dictionary!");
        if (element == null)
            throw new DictionaryException("Null value given in put function for Dictionary!");
        
        this.dict.put(key, element);
    }

    @Override
    public boolean isDefined(K key) {
        return this.dict.containsKey(key);
    }

    @Override
    public String toString() {
        return this.dict.entrySet().stream().map(e -> e.getKey().toString() + " --> " + e.getValue().toString() + "\n").reduce("", String::concat);
    }

    @Override
    public Set<K> keySet() {
        return this.dict.keySet();
    }

    @Override
    public Map<K, T> getContent() {
        return this.dict;
    }

    @Override
    public void setContent(Map<K, T> dictionary) {
        this.dict = dictionary;        
    }

    @Override
    public IDictionary<K,T> clone() {
        Map<K,T> cloneSymTable = this.dict.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        IDictionary<K,T> newDict = new Dictionary<K,T>();
        newDict.setContent(cloneSymTable);
        return newDict;
    }

    
}
