package co.edu.uniquindio.estr.jokify.structures;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Iterator;

class Node<T> implements Serializable {

    //Serial ID for Serializable
    private static final long serialVersionUID = 1L;

    T value;
    Node<T> next;

    // Constructor
    public Node(T value) {
        this.value = value;
        this.next = null;
    }
}

public class LinkedList<T extends Comparable<T>> implements Iterable<T>, Serializable {

    //Serial ID for Serializable
    private static final long serialVersionUID = 1L;

    //Start of the LinkedList
    Node<T> head;

    /**
     * Constructor of the linkedList
     */
    public LinkedList() {
        this.head = null;
    }

    /**
     * Add an element at the end of the list
     *
     * @param value
     */
    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    /**
     * Add an element at the beginning of the list
     *
     * @param value
     */
    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
    }

    /**
     * Gets the size of the list
     *
     * @return
     */
    public int size() {
        int size = 0;
        Node<T> current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    /**
     * Add an element at a specific position
     *
     * @param value
     * @param position
     */
    public void add(T value, int position) {
        if (position < 0 || position > size()) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        if (position == 0) {
            addFirst(value);
        } else {
            Node<T> current = head;
            for (int i = 1; i < position; i++) {
                current = current.next;
            }
            Node<T> newNode = new Node<>(value);
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    /**
     * Get the value of a node at a specific position
     *
     * @param position
     * @return
     */
    public T getValue(int position) {
        if (position < 0 || position >= size()) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        Node<T> current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.value;
    }

    /**
     * Get the node at a specific position
     *
     * @param position
     * @return
     */
    public Node<T> getNode(int position) {
        if (position < 0 || position >= size()) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        Node<T> current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Get the position of a node
     *
     * @param value
     * @return
     */
    public int getPosition(T value) {
        Node<T> current = head;
        int position = 0;
        while (current != null) {
            if (current.value.equals(value)) {
                return position;
            }
            current = current.next;
            position++;
        }
        return -1;
    }

    /**
     * Check if the list is empty
     *
     * @return
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Remove the fist node on the list
     */
    public void removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        head = head.next;
    }

    /**
     * Remove the last node on the list
     */
    public void removeLast() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        if (head.next == null) {
            head = null;
            return;
        }
        Node<T> current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
    }

    /**
     * Remove the node with a certain value on the list
     *
     * @param value
     */
    public void remove(T value) {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        if (head.value.equals(value)) {
            head = head.next;
            return;
        }
        Node<T> current = head;
        while (current.next != null && !current.next.value.equals(value)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    /**
     * Remove the node in a certain position
     *
     * @param index
     */
    public void removeAt(int index) {
        Node<T> node = getNode(index);
        remove(node.value);
    }

    /**
     * Modify the value of the node that are located in the position
     *
     * @param position
     * @param newValue
     */
    public void setValue(int position, T newValue) {
        Node<T> node = getNode(position);
        node.value = newValue;
    }

    /**
     * Sort the linked list using the insertion sort algorithm
     */
    public void sort() {
        if (head == null || head.next == null) {
            return; // The list is already sorted or empty
        }
        Node<T> currentNode = head.next;
        while (currentNode != null) {
            T currentValue = currentNode.value;
            Node<T> previousNode = head;
            Node<T> iteratorNode = head;
            while (iteratorNode != currentNode) {
                if (currentValue.compareTo(iteratorNode.value) < 0) {
                    // If the current value is less than the value of the current node, swap them
                    T temp = iteratorNode.value;
                    iteratorNode.value = currentValue;
                    currentValue = temp;
                }
                iteratorNode = iteratorNode.next;
            }
            currentNode.value = currentValue;
            currentNode = currentNode.next;
        }
    }

    /**
     * Print all the values in the linkedList
     */
    public void printLinkedList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.value + "-> ");
            current = current.next;
        }
        System.out.print("null");
        System.out.println();
    }

    /**
     * Removes all the linkedList
     */
    public void clear() {
        head = null;
    }

    /**
     * Look if an index is valid
     *
     * @param index
     * @return
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < size();
    }

    /**
     * Reverse all the linked list
     */
    public void reverse() {
        if (head == null || head.next == null) {
            return; // Nothing to reverse
        }
        Node<T> previous = null;
        Node<T> current = head;
        Node<T> next = head.next;
        while (next != null) {
            current.next = previous;
            previous = current;
            current = next;
            next = next.next;
        }
        head.next = previous;
        head = current;
    }

    /**
     * Reverse a linkedList
     */
    public void reverseRecursive() {
        if (head == null || head.next == null) {
            throw new NoSuchElementException("The list cannot be reversed");
        } else {
            reverseRecursive(head, null);
        }
    }

    /**
     * Reverse a linkedList recursively
     *
     * @param current
     * @param previous
     */
    private void reverseRecursive(Node<T> current, Node<T> previous) {
        if (current.next == null) {
            head = current;
            head.next = previous;
        } else {
            reverseRecursive(current.next, current);
            current.next = previous;
        }
    }

    // Method to convert a LinkedList to an ObservableList
    public ObservableList<T> toObservableList() {
        ObservableList<T> observableList = FXCollections.observableArrayList();
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            observableList.add(iterator.next());
        }
        return observableList;
    }

    /**
     * Turns the linked list into an arraylist
     * @return
     */
    public ArrayList<T> toList() {
        ArrayList<T> list = new ArrayList<>();
        Node<T> current = head;
        while (current != null) {
            list.add(current.value);
            current = current.next;
        }
        return list;
    }

    /**
     * Implementation of the iterator method
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }
}