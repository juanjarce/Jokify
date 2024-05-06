package co.edu.uniquindio.estr.jokify.structures;

import java.util.EmptyStackException;

public class Stack<T> {
    private Node<T> top; // Top node of the stack

    // Constructor
    public Stack() {
        this.top = null; // Initialize the top node as null
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // Method to get the element at the top of the stack
    public T top() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.value;
    }

    // Method to push an element onto the stack
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    // Method to pop and return the element at the top of the stack
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T data = top.value;
        top = top.next;
        return data;
    }

    // Method to print the stack
    public void printStack() {
        Stack<T> auxiliaryStack = new Stack<>();
        while (!isEmpty()) {
            T element = pop();
            System.out.print(element + " ");
            auxiliaryStack.push(element);
        }
        while (!auxiliaryStack.isEmpty()) {
            push(auxiliaryStack.pop());
        }
        System.out.println();
    }
}

