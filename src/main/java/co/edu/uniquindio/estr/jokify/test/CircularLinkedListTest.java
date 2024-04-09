package co.edu.uniquindio.estr.jokify.test;

import co.edu.uniquindio.estr.jokify.structures.CircularLinkedList;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircularLinkedListTest {

    /**
     * --------------------------------------------------------------------------------------------------------------
     * Test for CircularLinkedList class
     * --------------------------------------------------------------------------------------------------------------
     */

    // Test for addFirst method, which adds an element at the beginning of the list
    @Test
    public void testAddFirst() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        list.addFirst(5);
        list.addFirst(3);
        list.addFirst(7);
        // Check if the elements are added correctly at the beginning
        assertEquals(Integer.valueOf(7), list.getNodeValue(0));
        assertEquals(Integer.valueOf(3), list.getNodeValue(1));
        assertEquals(Integer.valueOf(5), list.getNodeValue(2));
    }

    // Test for addLast method, which adds an element at the end of the list
    @Test
    public void testAddLast() {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        list.addLast(5);
        list.addLast(3);
        list.addLast(7);
        // Check if the elements are added correctly at the end
        assertEquals(Integer.valueOf(5), list.getNodeValue(0));
        assertEquals(Integer.valueOf(3), list.getNodeValue(1));
        assertEquals(Integer.valueOf(7), list.getNodeValue(2));
    }
}
