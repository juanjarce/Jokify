package co.edu.uniquindio.estr.jokify.structures;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class DoublyNode<T> {
    T value;
    DoublyNode<T> previous;
    DoublyNode<T> next;

    // Constructor
    public DoublyNode(T value) {
        this.value = value;
        this.previous = null;
        this.next = null;
    }
}

public class DoublyLinkedList<T extends Comparable<T>> implements Iterable<T> {
    private DoublyNode<T> head;
    private DoublyNode<T> tail;
    private int size;

    // Constructor
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Method to add an element at the beginning of the list
    public void addFirst(T value) {
        DoublyNode<T> newNode = new DoublyNode<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    // Method to add an element at the end of the list
    public void addLast(T value) {
        DoublyNode<T> newNode = new DoublyNode<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }

    // Method to add an element at a specific position
    public void add(T value, int position) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Position out of range");
        }
        if (position == 0) {
            addFirst(value);
        } else if (position == size) {
            addLast(value);
        } else {
            DoublyNode<T> newNode = new DoublyNode<>(value);
            DoublyNode<T> current = getNode(position - 1);
            newNode.next = current.next;
            current.next.previous = newNode;
            current.next = newNode;
            newNode.previous = current;
            size++;
        }
    }

    // Method to get the value of a node at a specific position
    public T getNodeValue(int position) {
        return getNode(position).value;
    }

    // Method to get the node at a specific position
    private DoublyNode<T> getNode(int position) {
        if (!isValidIndex(position)) {
            throw new IndexOutOfBoundsException("Position out of range");
        }
        DoublyNode<T> current;
        if (position < size / 2) {
            current = head;
            for (int i = 0; i < position; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > position; i--) {
                current = current.previous;
            }
        }
        return current;
    }

    // Method to get the position of a node given its value
    public int getNodePosition(T value) {
        DoublyNode<T> current = head;
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

    // Method to check if an index is valid
    private boolean isValidIndex(int index) {
        return index >= 0 && index < size;
    }

    // Method to check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to delete the first node of the list
    public void deleteFirst() {
        if (!isEmpty()) {
            head = head.next;
            if (head != null) {
                head.previous = null;
            } else {
                tail = null;
            }
            size--;
        }
    }

    // Method to delete the last node of the list
    public void deleteLast() {
        if (!isEmpty()) {
            tail = tail.previous;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            size--;
        }
    }

    // Method to delete a node given its value
    public void delete(T value) {
        DoublyNode<T> current = head;
        while (current != null && !current.value.equals(value)) {
            current = current.next;
        }
        if (current != null) {
            if (current.previous != null) {
                current.previous.next = current.next;
            } else {
                head = current.next;
            }
            if (current.next != null) {
                current.next.previous = current.previous;
            } else {
                tail = current.previous;
            }
            size--;
        }
    }

    // Method to modify the value of a node at a specific position
    public void modifyNode(int position, T newValue) {
        getNode(position).value = newValue;
    }

    // Method to sort the list using the bubble sort algorithm
    public void sortList() {
        if (size <= 1) {
            return; // Cannot sort a list with 0 or 1 elements
        }

        boolean swapped;
        do {
            swapped = false;
            DoublyNode<T> current = head;
            while (current.next != null) {
                if (current.value.compareTo(current.next.value) > 0) {
                    swapNodes(current, current.next);
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }

    // Auxiliary method to swap two nodes in the list
    private void swapNodes(DoublyNode<T> node1, DoublyNode<T> node2) {
        T temp = node1.value;
        node1.value = node2.value;
        node2.value = temp;
    }

    // Method to print the list
    public void printList() {
        DoublyNode<T> current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Method to clear the entire list
    public void clearList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Method to invert a doubly linked list
    private void invertDoublyLinkedList() {
        if (size > 1) {
            int index = 0;
            invertDoublyLinkedListRecursive(head, index);
        } else {
            System.out.println("The doubly linked list cannot be inverted");
        }
    }

    private void invertDoublyLinkedListRecursive(DoublyNode<T> current, int index) {
        if (index == (size - 1)) {
            // Swap the links
            DoublyNode<T> temp = tail.previous;
            tail.previous = tail.next;
            tail.next = temp;

            // Set it as the head of the list
            DoublyNode<T> tempLimits = head;
            head = tail;
            tail = head;
        } else {
            invertDoublyLinkedListRecursive(current.next, index + 1);
            // Swap the links
            DoublyNode<T> temp = current.previous;
            current.previous = current.next;
            current.next = temp;
        }
    }

    // Implementation of the iterator method of the Iterable interface
    //7. Write the Iterator for a doubly linked list.
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private DoublyNode<T> current = head;

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

    // Method to convert the doubly linked list to a standard list
    public List<T> toList() {
        // Create a new ArrayList to store the elements
        List<T> list = new ArrayList<>();
        // Start from the head of the doubly linked list
        DoublyNode<T> current = head;
        // Traverse the list and add each element to the ArrayList
        while (current != null) {
            list.add(current.value);
            current = current.next;
        }
        // Return the ArrayList containing all elements of the doubly linked list
        return list;
    }

}


