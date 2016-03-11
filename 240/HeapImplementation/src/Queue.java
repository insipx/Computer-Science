

import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("FieldCanBeLocal")
public class Queue<T> implements QueueInterface<T> {

    private Node<T> head; // Current front of the queue.
    private Node<T> current; // The node we're currently adding to the end.
    public int size; //Number of elements in the queue.

    @Override
    public void enqueue(T val) {
        // Add a new node to the end of the queue.
        // O(1) running time.
        if (size == 0) {
            head = new Node<>();
            current = new Node<>();
            head.ref = current;
            current.value = val;
            size++;
        } else {
            Node<T> temp = new Node<>();
            current.ref = temp;
            current = temp;
            current.value = val;
            size++;
        }
    }

    @Override
    public T dequeue() {
        // Remove and return the value at the front of the queue.
        // O(1) running time.
        if (size != 0) {
            Node<T> temp;
            T tempVal;
            temp = head.ref;
            tempVal = temp.value;
            head = head.ref;
            size--;
            return tempVal;
        } else {
            return null;
        }
    }

    @Override
    public void dumpQueue() {
        // Print the entire queue on one line, left to right.
        // O(n) running time.
        Node<T> next = head.ref;
        while (next != null) {
            System.out.print(next.value + " ");
            next = next.ref;
        }
        System.out.println();
    }
}