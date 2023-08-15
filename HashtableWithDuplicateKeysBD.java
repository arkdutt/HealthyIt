import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class HashtableWithDuplicateKeysBD<KeyType, ValueType> extends HashtableMap<KeyType, List<ValueType>> implements HashtableWithDuplicateKeysInterface<KeyType, ValueType> {

    @Override
    public void putOne(KeyType key, ValueType value) {
    }
    
    @SuppressWarnings("unchecked")
    public List<ValueType> get(KeyType key) throws NoSuchElementException {

        List<ValueType> list = new ArrayList<>() ;

        PostBD post1 = new PostBD("TITLE: Protein overload",
                "https://www.reddit.com/r/EatCheapAndHealthy/comments/zpgx94/protein_overload/",
                "BODY: Forget takeout! This homemade version of Panda Express’s Orange Chicken will blow you away with its");

        PostBD post2 = new PostBD("TITLE: ORANGE CHICKEN",
                "https://www.reddit.com/r/EatCheapAndHealthy/comments/zpfsvx/orange_chicken/",
                "BODY: Forget takeout! This homemade version of Panda Express’s Orange Chicken will blow you away with its");
        list.add((ValueType) post1);
        list.add((ValueType) post2);
        return list;
    }

    @Override
    public void removeOne(KeyType key, ValueType value) {
    }

    @Override
    public int getNumberOfValues() {
        return 3;
    }
}
