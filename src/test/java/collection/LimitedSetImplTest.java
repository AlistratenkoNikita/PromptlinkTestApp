package collection;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LimitedSetImplTest {

    private String string1 = "a1";

    private LimitedSetImpl<String> set = new LimitedSetImpl<>(10_000_000);

    @Test
    public void testDefaultAdding() {
        set.add(string1);

        assertTrue(set.contains(string1));
    }

    @Test
    public void testAddingCheckForAbsent() {
        set.add(string1);

        String string2 = "a2";
        assertFalse(set.contains(string2));
    }

    @Test
    public void testAddingForEleventhElement(){
        for (int i = 0; i < 9; i++) {
            set.add(String.valueOf(i+1));
            set.contains(String.valueOf(i+1));
        }

        set.add("10");
        set.add("11");

        assertFalse(set.contains("10"));
        assertTrue(set.contains("11"));
    }

    @Test
    public void remove() {
        set.add(string1);
        assertTrue(set.contains(string1));

        set.remove(string1);
        assertFalse(set.contains(string1));
    }

    @Test
    public void containsExistingObj() {
        set.add("1");

        assertTrue(set.contains("1"));
    }

    @Test
    public void containsNotExistingObj() {
        set.add("1");

        assertFalse(set.contains("2"));
    }
}