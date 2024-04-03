package co.edu.uniquindio.estr.jokify.structures;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

class TreeNode<T> implements Serializable {

    //Serial ID for Serializable
    private static final long serialVersionUID = 1L;

    T data;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T>, Serializable {

    //Serial ID for Serializable
    private static final long serialVersionUID = 1L;

    private TreeNode<T> root;

    //getters() & setters()
    public TreeNode<T> getRoot() {
        return root;
    }
    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    // Method to retrieve an element from the tree by its key
    public T get(T key) {
        return get(root, key);
    }

    // Recursive helper method to retrieve an element from the tree by its key
    private T get(TreeNode<T> node, T key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.data);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.data;
        }
    }

    // Method to insert an element into the tree
    public void insert(T data) {
        root = insertRec(root, data);
    }

    // Recursive helper method to insert an element into the tree
    private TreeNode<T> insertRec(TreeNode<T> node, T data) {
        if (node == null) {
            return new TreeNode<>(data);
        }
        if (data.compareTo(node.data) < 0) {
            node.left = insertRec(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insertRec(node.right, data);
        }
        return node;
    }

    // Method to check if an element exists in the tree
    public boolean contains(T data) {
        return containsRec(root, data);
    }

    // Recursive helper method to check if an element exists in the tree
    private boolean containsRec(TreeNode<T> node, T data) {
        if (node == null) {
            return false;
        }
        if (data.equals(node.data)) {
            return true;
        } else if (data.compareTo(node.data) < 0) {
            return containsRec(node.left, data);
        } else {
            return containsRec(node.right, data);
        }
    }

    // Method to remove an element from the tree
    public void remove(T data) {
        root = removeRec(root, data);
    }

    // Recursive helper method to remove an element from the tree
    private TreeNode<T> removeRec(TreeNode<T> node, T data) {
        if (node == null) {
            return null;
        }
        if (data.equals(node.data)) {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                node.data = findMin(node.right).data;
                node.right = removeRec(node.right, node.data);
            }
        } else if (data.compareTo(node.data) < 0) {
            node.left = removeRec(node.left, data);
        } else {
            node.right = removeRec(node.right, data);
        }
        return node;
    }

    // Method to update an element in the tree
    public void update(T oldData, T newData) {
        root = updateRec(root, oldData, newData);
    }

    // Recursive helper method to update an element in the tree
    private TreeNode<T> updateRec(TreeNode<T> node, T oldData, T newData) {
        if (node == null) {
            return null;
        }
        if (oldData.equals(node.data)) {
            node.data = newData;
        } else if (oldData.compareTo(node.data) < 0) {
            node.left = updateRec(node.left, oldData, newData);
        } else {
            node.right = updateRec(node.right, oldData, newData);
        }
        return node;
    }

    // Method to find the minimum element in the tree
    private TreeNode<T> findMin(TreeNode<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    //Methods for observable list, implemented in UI

    // Method to convert the binary search tree to an observable list
    public ObservableList<T> toObservableList() {
        ObservableList<T> list = FXCollections.observableArrayList();
        inOrderTraversal(root, list);
        return list;
    }

    // Helper method for in-order traversal to populate the observable list
    private void inOrderTraversal(TreeNode<T> node, ObservableList<T> list) {
        if (node != null) {
            inOrderTraversal(node.left, list);
            list.add(node.data);
            inOrderTraversal(node.right, list);
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------------------------
    //METHODS
    //For declared iterator in BinarySearchTree class
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator(root);
    }

    // Inner class implementing the Iterator interface
    private class BinaryTreeIterator implements Iterator<T> {
        private Stack<TreeNode<T>> stack;

        public BinaryTreeIterator(TreeNode<T> root) {
            stack = new Stack<>();
            // Initially, we add all nodes from the left path to the stack
            populateStack(root);
        }

        private void populateStack(TreeNode<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        // Method to check if there is a next element in the iterator
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        // Method to get the next element of the iterator (in in-order traversal)
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No hay mas elementos para el iterador");
            }
            TreeNode<T> current = stack.pop();
            populateStack(current.right); // Adding nodes from the right subtree to the stack
            return current.data;
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------

}

