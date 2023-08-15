// --== CS400 Project One File Header ==--
// Name: Ark Dutt
// CSL Username: ark
// Email: dutt3@wisc.edu
// Lecture #: 004 15:30-16:20
// Notes to Grader: <any optional extra notes to your grader>

import java.util.List;
import java.util.NoSuchElementException;

/**
 * This abstract data type represents a collection that maps keys to values,
 * in which duplicate keys are not allowed (each key maps to exactly one value).
 */
public interface MapADT<KeyType,ValueType> {

    // add a new key-value pair/mapping to this collection
    // throws exception when key is null or duplicate of one already stored
    public void put(KeyType key, ValueType value) throws IllegalArgumentException;

    // check whether a key maps to a value within this collection
    public boolean containsKey(KeyType key);

    // retrieve the specific value that a key maps to
    // throws exception when key is not stored in this collection
    public ValueType get(KeyType key) throws NoSuchElementException;

    // remove the mapping for a given key from this collection
    // throws exception when key is not stored in this collection
    public ValueType remove(KeyType key) throws NoSuchElementException;

    // remove all key-value pairs from this collection
    public void clear();

    // retrieve the number of keys stored within this collection
    public int getSize();

    // retrieve this collection's capacity (size of its underlying array)
    public int getCapacity();

}
