

/**
 * The HashNode class represents the single node, which includes key-value pair
 *
 * @author Ark Dutt
 */

public class HashNode<KeyType, ValueType> {

    private KeyType key; //generic type
    private ValueType value; //generic type

    public HashNode(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
    }

    public KeyType getKey() {
        return key;
    }

    public ValueType getValue() {
        return value;
    }
}
