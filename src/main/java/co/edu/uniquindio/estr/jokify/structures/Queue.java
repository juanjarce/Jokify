package co.edu.uniquindio.estr.jokify.structures;

public class Queue<T> {

    Node<T> front; // Front node of the queue
    Node<T> rear; // Rear node of the queue
    public int maxSize; // Maximum size of the queue
    int currentSize; // Current size of the queue

    // Constructor of the Queue class
    public Queue(int maxSize) {
        this.front = null;
        this.rear = null;
        this.maxSize = maxSize;
        this.currentSize = 0;
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }

    // Method to check if the queue is full
    public boolean isFull() {
        return currentSize >= maxSize;
    }

    // Method to add an element to the queue
    public void enqueue(T value) {
        if (isFull()) {
            throw new RuntimeException("The queue is full");
        }
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        currentSize++;
    }

    // Method to remove an element from the queue
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("The queue is empty");
        }
        T removedValue = front.value;
        front = front.next;
        currentSize--;
        if (front == null) {
            rear = null;
        }
        return removedValue;
    }

    // Method to get the element at the front of the queue without removing it
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("The queue is empty");
        }
        return front.value;
    }

    // Method to get the current size of the queue
    public int size() {
        return currentSize;
    }

    // Method to print the queue and display it on the console
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("The queue is empty");
            return;
        }
        Node<T> current = front;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Method to get an element at a specific position in the queue
    public T getElementAtPosition(int position) {
        if (isEmpty() || position >= size() || position < 0) {
            throw new IllegalArgumentException("Invalid position");
        }
        Node<T> current = front;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.value;
    }

    // Method to compare two queues and determine if they are equal
    public boolean areEqual(Queue<T> otherQueue) {
        if (size() != otherQueue.size()) {
            return false;
        }

        Node<T> current1 = front;
        Node<T> current2 = otherQueue.front;

        while (current1 != null) {
            if (!current1.value.equals(current2.value)) {
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }

        return true;
    }

}

