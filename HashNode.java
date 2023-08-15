// --== CS400 Project One File Header ==--
// Name: Ark Dutt
// CSL Username: ark
// Email: dutt3@wisc.edu
// Lecture #: 004 15:30-16:20
// Notes to Grader: <any optional extra notes to your grader>

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
