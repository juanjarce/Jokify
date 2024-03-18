package co.edu.uniquindio.estr.jokify.structures;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

class TreeNode<T> {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {
    private TreeNode<T> root;

    public void insert(T data) {
        root = insertRec(root, data);
    }

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

    public boolean contains(T data) {
        return containsRec(root, data);
    }

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

    public void remove(T data) {
        root = removeRec(root, data);
    }

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

    public void update(T oldData, T newData) {
        root = updateRec(root, oldData, newData);
    }

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

    private TreeNode<T> findMin(TreeNode<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------
    //Methods for observable list, implemented in UI

    public ObservableList<T> toObservableList() {
        ObservableList<T> list = FXCollections.observableArrayList();
        inOrderTraversal(root, list);
        return list;
    }

    private void inOrderTraversal(TreeNode<T> node, ObservableList<T> list) {
        if (node != null) {
            inOrderTraversal(node.left, list);
            list.add(node.data);
            inOrderTraversal(node.right, list);
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------

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
                throw new NoSuchElementException("No more elements in the iterator");
            }
            TreeNode<T> current = stack.pop();
            populateStack(current.right); // Adding nodes from the right subtree to the stack
            return current.data;
        }
    }

}

