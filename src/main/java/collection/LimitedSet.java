package collection;

/**
 * A collection that contains no duplicate elements and can't hold more than 10 elements.
 * Removes least used (invoking {@link LimitedSet#contains(Object)} for this element) one if 11th element is about to be added.
 * @param <T> the type of elements maintained by this set
 */
public interface LimitedSet<T> {
    /**
     * Adds a new unique object to the set.
     * If the set contains 10 elements - least used one is removed and the new one is added.
     * @param t an object to add to the set.
     */
    void add(final T t);

    /**
     * Removes the specified element from this set if it is present.
     * @param t object to be removed from this set, if present
     * @return {@code true} if the set contained the specified element
     */
    boolean remove(final T t);

    /**
     * Returns {@code true} if this set contains the specified element.
     * More formally, returns {@code true} if and only if this set
     * contains an element {@code e} such that
     * {@code Objects.equals(o, e)}.
     * @param t element whose presence in this set is to be tested
     * @return {@code true} if this set contains the specified element
     */
    boolean contains(final T t);
}
