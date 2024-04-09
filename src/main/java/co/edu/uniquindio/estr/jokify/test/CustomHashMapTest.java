package co.edu.uniquindio.estr.jokify.test;

import co.edu.uniquindio.estr.jokify.structures.HashMapCustom;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

public class CustomHashMapTest {

    /**
     * --------------------------------------------------------------------------------------------------------------
     * Test for CustomHashMap class
     * Checking the put, get, remove, size, isEmpty and containsKey methods.
     * Props to Arce for pulling this off.
     * --------------------------------------------------------------------------------------------------------------
     */

    @Test
    public void testPutAndGet() {
        HashMapCustom<String, Integer> map = new HashMapCustom<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        // Check if the elements are added correctly and can be retrieved
        assertEquals(Integer.valueOf(1), map.get("One"));
        assertEquals(Integer.valueOf(2), map.get("Two"));
        assertEquals(Integer.valueOf(3), map.get("Three"));
    }

    @Test
    public void testRemove() {
        HashMapCustom<String, Integer> map = new HashMapCustom<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.remove("Two");
        // Check if the element is removed correctly
        assertNull(map.get("Two"));
    }

    @Test
    public void testSize() {
        HashMapCustom<String, Integer> map = new HashMapCustom<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        // Check if the size is correct
        assertEquals(3, map.size());
    }

    @Test
    public void testIsEmpty() {
        HashMapCustom<String, Integer> map = new HashMapCustom<>();
        // Check if the map is initially empty
        assertTrue(map.isEmpty());
        map.put("One", 1);
        // Check if the map is not empty after adding an element
        assertFalse(map.isEmpty());
    }

    @Test
    public void testContainsKey() {
        HashMapCustom<String, Integer> map = new HashMapCustom<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        // Check if the map contains the keys of the added elements
        assertTrue(map.containsKey("One"));
        assertTrue(map.containsKey("Two"));
        assertTrue(map.containsKey("Three"));
        // Check if the map does not contain a key that was not added
        assertFalse(map.containsKey("Four"));
    }
}
