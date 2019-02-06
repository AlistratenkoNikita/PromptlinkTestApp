package collection;

import java.util.*;

/**
 * This class implements the {@link LimitedSet} interface.
 */
public class LimitedSetImpl<T> implements LimitedSet<T> {

    /**
     * List to store Nodes with variable T and sort them
     */
    private Set<Node> set;

    /**
     * Map to store values' usage counter and have the access to set storing values without its counter
     */
    private Map<T, Integer> map;

    private int capacity = 10;

    public class Node implements Comparable{
        T t;
        int usageCounter;

        Node(T t) {
            this.t = t;
            this.usageCounter = 0;
        }

        Node(T t, int usageCounter) {
            this.t = t;
            this.usageCounter = usageCounter;
        }

        // Needs to sort items properly
        @Override
        public int compareTo(Object o) {
            int compareResult;

            int valueCompare = Integer.compare(t.hashCode(), ((Node) o).t.hashCode());
            int counterCompare = Integer.compare(usageCounter, ((Node) o).usageCounter);

            if (valueCompare == 0){
                compareResult = 0;
            } else if (counterCompare == 0) {
                compareResult = valueCompare;
            } else {
                compareResult = counterCompare;
            }

            return compareResult;
        }
    }

    public LimitedSetImpl() {
        set = new TreeSet<>();
        map = new HashMap<>();
    }

    public LimitedSetImpl(int capacity) {
        this();
        this.capacity = capacity;
    }

    @Override
    public void add(T t) {
        if (!map.containsKey(t)){
            if (set.size() == capacity){
                deleteLeastUsed();
            }

            map.put(t, 0);
            set.add(new Node(t));
        }
    }

    private void deleteLeastUsed() {
        Node leastUsed = set.iterator().next();

        set.remove(leastUsed);
        map.remove(leastUsed.t);
    }

    @Override
    public boolean remove(T t) {
        boolean remove = map.containsKey(t) && set.remove(new Node(t, map.get(t)));
        map.remove(t);

        return remove;
    }

    @Override
    public boolean contains(T t) {
        boolean result = false;

        if (map.containsKey(t)){
            int usageCounter = map.get(t) + 1;

            set.remove(new Node(t, map.get(t)));
            map.put(t, usageCounter);
            set.add(new Node(t, usageCounter));

            result = true;
        }

        return result;
    }
}
