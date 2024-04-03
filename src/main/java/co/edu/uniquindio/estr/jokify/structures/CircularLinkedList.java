package co.edu.uniquindio.estr.jokify.structures;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CircularLinkedList<T extends Comparable<T>> implements Iterable<T>, Serializable {

    //Serial ID for Serializable
    private static final long serialVersionUID = 1L;

    private Node<T> head;
    private int size;

    // Constructor
    public CircularLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Method to add an element at the beginning of the list
    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            newNode.next = newNode; // Makes the next of the new node point to itself
            head = newNode; // Head points to the new node
        } else {
            newNode.next = head.next; // Sets the next of the new node as the next of the head
            head.next = newNode; // Sets the next of the head as the new node
        }
        size++;
    }

    // Method to add an element at the end of the list
    public void addLast(T value) {
        addFirst(value); // Simply call addFirst as we are adding to the end of a circular list
        head = head.next; // Moves the head to the newly added node
    }

    // Method to add an element at a specific position
    public void add(T value, int position) {
        if (position < 0 || position > size) {
            System.out.println("Invalid position");
            return;
        }
        if (position == 0) {
            addFirst(value);
            return;
        }
        if (position == size) {
            addLast(value);
            return;
        }
        Node<T> newNode = new Node<>(value);
        Node<T> current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    // Method to get the value of a node at a specific position
    public T getNodeValue(int position) {
        if (!isValidIndex(position)) {
            System.out.println("Invalid position");
            return null;
        }
        Node<T> current = head.next;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.value;
    }

    // Method to get the node at a specific position
    public Node<T> getNode(int position) {
        if (!isValidIndex(position)) {
            System.out.println("Invalid position");
            return null;
        }
        Node<T> current = head.next;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current;
    }

    // Method to get the position of a node given its value
    public int getNodePosition(T value) {
        int position = 0;
        Node<T> current = head.next;
        while (current != head) {
            if (current.value.equals(value)) {
                return position;
            }
            current = current.next;
            position++;
        }
        return -1; // Indicates that the value was not found
    }

    // Method to check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to delete the first node of the list
    public void deleteFirst() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        head.next = head.next.next;
        size--;
    }

    // Method to delete the last node of the list
    public void deleteLast() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        Node<T> current = head.next;
        while (current.next != head) {
            current = current.next;
        }
        current.next = head.next;
        head = current;
        size--;
    }

    // Method to delete a node given its value
    public void delete(T value) {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        Node<T> current = head;
        Node<T> previous = null;
        do {
            if (current.value.equals(value)) {
                if (previous == null) { // If it's the first node
                    if (current.next == head) { // If it's the only node
                        head = null;
                    } else {
                        Node<T> lastNode = head;
                        while (lastNode.next != head) {
                            lastNode = lastNode.next; // Find the last node
                        }
                        head = head.next; // Move head to the next node
                        lastNode.next = head; // Update the last node's next
                    }
                } else {
                    previous.next = current.next;
                }
                size--;
                return;
            }
            previous = current;
            current = current.next;
        } while (current != head);
        System.out.println("Value not found");
    }

    // Method to modify the value of a node at a specific position
    public void modifyNode(int position, T newValue) {
        if (!isValidIndex(position)) {
            System.out.println("Invalid position");
            return;
        }
        Node<T> current = head.next;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        current.value = newValue;
    }

    // Method to sort the list
    public void sortList() {
        if (isEmpty() || size == 1) {
            return; // List is already sorted or empty
        }
        for (int i = 0; i < size - 1; i++) {
            Node<T> current = head.next;
            for (int j = 0; j < size - 1 - i; j++) {
                if (current.value.compareTo(current.next.value) > 0) {
                    T temp = current.value;
                    current.value = current.next.value;
                    current.next.value = temp;
                }
                current = current.next;
            }
        }
    }

    // Method to print the list
    public void printList() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        Node<T> current = head.next;
        do {
            System.out.print(current.value + " ");
            current = current.next;
        } while (current != head.next);
        System.out.println();
    }

    // Method to delete the entire list
    public void deleteList() {
        head.next = null;
        size = 0;
    }

    // Method to check if an index is valid
    private boolean isValidIndex(int index) {
        return index >= 0 && index < size;
    }

    // Implementation of the iterator method of the Iterable interface
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head.next;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public T next() {
                T value = current.value;
                current = current.next;
                count++;
                return value;
            }
        };
    }

    // Method to convert the circular linked list to a standard list
    public List<T> toList() {
        // Create a new ArrayList to store the elements
        List<T> list = new ArrayList<>();

        // Check if the list is empty
        if (head == null) {
            return list; // Return an empty list if the circular linked list is empty
        }

        // Start from the head of the circular linked list
        Node<T> current = head;

        // Traverse the circular linked list and add each element to the ArrayList
        do {
            list.add(current.value);
            current = current.next;
        } while (current != head); // Continue until we reach the head again

        // Return the ArrayList containing all elements of the circular linked list
        return list;
    }

}


