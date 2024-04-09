package co.edu.uniquindio.estr.jokify.test;

import co.edu.uniquindio.estr.jokify.structures.BinarySearchTree;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinarySearchTreeTest {

    /**
     * --------------------------------------------------------------------------------------------------------------
     * Test for BinarySearchTree class.
     * --------------------------------------------------------------------------------------------------------------
     */


    // Test for BinarySearchTree class, checking if the insert method works correctly.
    @Test
    public void testInsert() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        // Check if the elements are inserted correctly
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(7));
    }

    // Test for BinarySearchTree class, checking if the contains method works correctly.

    @Test
    public void testContains() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        // Check if the elements are contained correctly
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(7));
        // Check if the tree does not contain an element that was not inserted
        assertFalse(tree.contains(10));
    }

}
