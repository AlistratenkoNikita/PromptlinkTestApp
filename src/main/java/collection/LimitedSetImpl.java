package collection;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the {@link LimitedSet} interface.
 */
public class LimitedSetImpl<T> implements LimitedSet<T> {

    private static final int counterDefaultSize = 0;

    public  Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(final T t) {
        if (!map.containsKey(t)){
            if (map.size() == 10){
                map.remove(findLessUsedObject());

                map.put(t, counterDefaultSize);
            } else {
                map.put(t, counterDefaultSize);
            }
        }
    }

    @Override
    public boolean remove(final T t) {
        return map.remove(t) != null;
    }

    @Override
    public boolean contains(final T t) {
        if (map.containsKey(t)){
            map.put(t, map.get(t)+1);

            return true;
        }

        return false;
    }

    /**
     * Finds the least used object from the set.
     * @return least used object from the set
     */
    private T findLessUsedObject(){
        T lessUsedObj = null;
        Integer lessUsedObjCounter = 0;

        boolean firstIteration = true;

        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            if (firstIteration){
                lessUsedObj = entry.getKey();
                lessUsedObjCounter = entry.getValue();
            }

            if (entry.getValue() < lessUsedObjCounter){
                lessUsedObj = entry.getKey();
                lessUsedObjCounter = entry.getValue();
            }

            firstIteration = false;
        }

        return lessUsedObj;
    }
}
