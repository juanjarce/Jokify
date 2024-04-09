package co.edu.uniquindio.estr.jokify.test;

import co.edu.uniquindio.estr.jokify.structures.DoublyLinkedList;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class DoublyLinkedListTest {

    /**
     * --------------------------------------------------------------------------------------------------------------
     * Test for DoublyLinkedList class
     * --------------------------------------------------------------------------------------------------------------
     */

    @Test
    public void testAddFirstDoublyLinkedList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addFirst(5);
        list.addFirst(3);
        list.addFirst(7);
        // Check if the elements are added correctly at the beginning
        assertEquals(Integer.valueOf(7), list.getNodeValue(0));
        assertEquals(Integer.valueOf(3), list.getNodeValue(1));
        assertEquals(Integer.valueOf(5), list.getNodeValue(2));
    }

    @Test
    public void testAddLastDoublyLinkedList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(5);
        list.addLast(3);
        list.addLast(7);
        // Check if the elements are added correctly at the end
        assertEquals(Integer.valueOf(5), list.getNodeValue(0));
        assertEquals(Integer.valueOf(3), list.getNodeValue(1));
        assertEquals(Integer.valueOf(7), list.getNodeValue(2));
    }
}
