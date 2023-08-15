/**
 * The HashtableMap class uses hashFunction(key), put(key,value), rehash(), containsKey(key), get(key),
 * remove(key), clear(), getSize(), getCapacity() methods by implementing MapADT
 *
 * @author Ark Dutt
 */

import java.util.NoSuchElementException;

public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType>{

    protected HashNode<KeyType, ValueType>[] buckets; //array of integers
    private int tableSize; //capacity

    private double loadFactor = 0.7;

    private int size; //current number of key-value pairs in a hashtable
    HashNode<KeyType, ValueType> obj = new HashNode(-50000000, -50000000);
    HashNode <KeyType,ValueType> sentObj = new HashNode<KeyType, ValueType>(obj.getKey(), obj.getValue() );

    public HashtableMap(int capacity) {
        this.tableSize = capacity;
        this.buckets = new HashNode[capacity];
        this.size = 0;
    }

    public HashtableMap() {
        // with default capacity = 8
        this(8);
    }

    /**
     * hashFunction method calculates the intended index of the given key.
     *
     * @param key the key whose index you want to find
     * @return the index
     */
    private int hashFunction(KeyType key) {
        return Math.abs(key.hashCode()%tableSize);
    }

    /**
     *The put method adds a new key-value pair/mapping to this collection. It throws an IllegalArgumentException
     * if the key or value are null, or if the keys are duplicate of one already stored.
     * If the load factor exceeds by 0.7, then it rehashes.
     *
     * @param key the key to be added
     * @param value the value to be added
     * @throws IllegalArgumentException when the key or value is null, or if the keys are
     * duplicate of one already stored
     */
    public void put(KeyType key, ValueType value) throws IllegalArgumentException {

        if(key == null || value == null) {
            throw new IllegalArgumentException("key or value is null!");
        }

        if(containsKey(key) == true) {
            throw new IllegalArgumentException("The keys are duplicate of one already stored");
        }

        HashNode <KeyType,ValueType> sentObj = new HashNode<>(key, value);
        int index = hashFunction(key);
        while(buckets[index] != null) {
            if(index< buckets.length) {

                //if reaches at the end of the array, then it wraps to the beginning of the array while linear probing
                if(index == buckets.length-1) {
                    index = 0;
                }
            }
            index++;
        }

        buckets[index] = sentObj;
        size++;

        if(Double.valueOf(size) / Double.valueOf(tableSize) >= loadFactor) {
            rehash();
        }
    }

    /**
     * The rehash method resizes the array when the load factor exceeds 0.7.
     * In this method, the array size is doubled and the key-value pairs are redistributed based on the new
     * size of the array. The method calls the put method in order to redistribute the key-value pairs to the new
     * resized array
     */
    public void rehash() {

        tableSize = 2*tableSize;
        HashNode<KeyType, ValueType>[] oldBuckets = buckets;
        buckets = new HashNode[tableSize];
        size = 0;
        for(int i=0; i<oldBuckets.length; i++) {
            if(oldBuckets[i] == null) {
                continue;
            }
            KeyType newKey = oldBuckets[i].getKey();
            ValueType newVal = oldBuckets[i].getValue();
            put(newKey, newVal);
        }
    }

    /**
     * The containsKey method checks whether a key maps to a value within this collection
     *
     * @param key the key, which needs to be checked
     * @return true if the key maps to a value within the collection, otherwise false
     */
    public boolean containsKey(KeyType key) {

        int index=0;
        while(index<buckets.length) {
            if(buckets[index] != null && buckets[index].getKey().equals(key) ) {
                return true;
            }
            index++;
        }
        if(key == null) {
            return false;
        }
        return false;
    }


    /**
     *The get method returns the value associated with the key entered.
     *
     * @param key key, whose value should to be returned
     * @return the value associated with the key entered
     * @throws NoSuchElementException when key is not stored in this collection
     */
    public ValueType get(KeyType key) throws NoSuchElementException {

        if(containsKey(key) == false) {
            throw new NoSuchElementException("ERROR! The key is not stored in this collection!");
        }
        int index = hashFunction(key);
        while(index < buckets.length) {
            if (buckets[index] != null && buckets[index].getKey().equals(key)) {
                return buckets[index].getValue();
            }
            index = (index + 1) % tableSize;

            //if reaches at the end of the array, then it wraps to the beginning of the array while linear probing
            if(index == buckets.length-1) {
                index = 0;
            }
        }
        return null;
    }


    /**
     * remove the mapping for a given key from this collection
     *
     * @param key the key who key-value pair should be removed
     * @return the value of the removed key-value pair
     * @throws NoSuchElementException when key is not stored in this collection
     */
    public ValueType remove(KeyType key) throws NoSuchElementException {

        if(key == null || containsKey(key) == false) {
            throw new NoSuchElementException("ERROR! The key is not stored in this collection!");
        }
        int index = hashFunction(key);
        ValueType value = null;
        int startIndex = index;
        while(index<buckets.length) {
            if(buckets[index] != null) {
                if (buckets[index].getKey().equals(key)) {
                    value = buckets[index].getValue();
                    buckets[index] = sentObj;
                    size--;
                    return value;
                }
            }
            index = (index + 1) % getCapacity();
            if(index == startIndex) {
                break;
            }

            //if reaches at the end of the array, then it wraps to the beginning of the array while linear probing
            if(index == buckets.length-1) {
                index = 0;
            }
        }
        return value;
    }

    /**
     * remove all key-value pairs from this collection
     */
    public void clear() {

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;
        }
        size = 0;
    }


    /**
     * retrieve the number of keys stored within this collection
     * @return the number of key-value pairs present
     */
    public int getSize() {
        return size;
    }


    /**
     * retrieve this collection's capacity (size of its underlying array)
     *
     * @return the size of the hashtable
     */
    public int getCapacity() {
        return tableSize;
    }
}
