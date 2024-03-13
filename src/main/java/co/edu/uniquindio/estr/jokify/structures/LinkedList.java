package co.edu.uniquindio.estr.jokify.structures;

import java.util.Iterator;


//Average Node class used to solve the problems of the laboratory
class Node<T> {
    T value;
    Node<T> next;

    // Constructor
    public Node(T value) {
        this.value = value;
        this.next = null;
    }
}

public class LinkedList<T extends Comparable<T>> implements Iterable<T> {
    Node<T> head;

    // Constructor
    public LinkedList() {
        this.head = null;
    }

    // Method to add an element at the end of the list
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

    // Method to add an element at the beginning of the list
    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
    }

    // Method to add an element at a specific position
    public void add(T value, int position) {
        if (position < 0) {
            System.out.println("Invalid position");
            return;
        }
        if (position == 0) {
            addFirst(value);
            return;
        }
        Node<T> newNode = new Node<>(value);
        Node<T> current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Position out of range");
            return;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    // Method to get the value of a node at a specific position
    public T getNodeValue(int position) {
        Node<T> current = head;
        for (int i = 0; i < position && current != null; i++) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Position out of range");
            return null; // Default value to indicate error
        }
        return current.value;
    }

    // Method to get the node at a specific position
    public Node<T> getNode(int position) {
        Node<T> current = head;
        for (int i = 0; i < position && current != null; i++) {
            current = current.next;
        }
        return current;
    }

    // Method to get the position of a node given its value
    public int getNodePosition(T value) {
        int position = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return position;
            }
            current = current.next;
            position++;
        }
        return -1; // Default value to indicate not found
    }

    // Method to check if the list is empty
    public boolean isEmpty() {
        return head == null;
    }

    // Method to delete the first node of the list
    public void deleteFirst() {
        if (head != null) {
            head = head.next;
        }
    }

    // Method to delete the last node of the list
    public void deleteLast() {
        if (head == null || head.next == null) {
            head = null;
        } else {
            Node<T> current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
    }

    // Method to delete a node given its value
    public void delete(T value) {
        if (head == null) {
            return;
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

    // Method to modify the value of a node at a specific position
    public void modifyNode(int position, T newValue) {
        Node<T> node = getNode(position);
        if (node != null) {
            node.value = newValue;
        } else {
            System.out.println("Position out of range");
        }
    }

    // Method to sort the list using the insertion sort algorithm
    public void sortList() {
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

    // Method to print the list
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Method to delete the entire list
    public void deleteList() {
        head = null;
    }

    // Method to check if an index is valid
    private boolean isValidIndex(int index) {
        return index >= 0 && index < size();
    }

    // Method to get the size of the list
    private int size() {
        int size = 0;
        Node<T> current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    // Method to reverse a linked list
    private void reverseLinkedList() {
        if (head == null || head.next == null) {
            System.out.println("The list cannot be reversed");
        } else {
            reverseRecursive(head, null);
        }
    }

    // Method to reverse a linked list recursively
    private void reverseRecursive(Node<T> current, Node<T> previous) {
        if (current.next == null) {
            head = current;
            head.next = previous;
        } else {
            reverseRecursive(current.next, current);
            current.next = previous;
        }
    }

    // Method to traverse a linked list recursively
    public void traverseRecursive(Node node) {
        // Check if the current node is not null
        if (node != null) {
            // Print the value of the current node
            System.out.print(node.value + " ");
            // Recursively call the method with the next node
            traverseRecursive(node.next);
        }
    }


    // Implementation of the iterator method of the Iterable interface
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
