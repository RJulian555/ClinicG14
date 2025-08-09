/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 *
 * 
 * @G14
 */
 public class LinkedQueue<T> implements QueueInterface<T> {
    private Node firstNode; // Front of the queue
    private Node lastNode;  // Back of the queue
    private int count;      // Track size for O(1) size() operation
    private LinkedQueue<T> holdQueue; // For hold/release functionality
    
    @Override
    public void enqueue(T item) {
        Node newNode = new Node(item);
        // to check if queue is empty
        if (isEmpty()) {
            // if queue is empty, new node (only node) becomes both first and last
            firstNode = newNode;
        } else {
            // Add to end of queue (can only add to the end because it is a queue)
            lastNode.next = newNode;
        }
        
        lastNode = newNode;
        count++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        
        // save data from front node
        T data = firstNode.data;
        
        // Move firstNode pointer forward to the one beside it
        firstNode = firstNode.next;
        
        // If queue is now empty, update lastNode
        if (firstNode == null) {
            lastNode = null;
        }
        
        count--;
        return data;
    }

    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return firstNode.data;//just to display the most front data
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null; // Could also use count == 0
    }

    @Override
    public void clear() {//basically delete everything in queue
        firstNode = null;
        lastNode = null;
        count = 0;
    }

    @Override
    public int size() {
        return count;
    }
    
    // Optional additional methods
    @Override
    public String toString() {//iterates through the whole LinkedQueue to display all data in queue
        StringBuilder sb = new StringBuilder("[");
        Node current = firstNode;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
    
    //TODO add more methods here
    
        // Search for an item in the queue
    @Override
    public boolean contains(T item) {
        Node current = firstNode;
        while (current != null) {
            if (current.data.equals(item)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Sort the queue (simple bubble sort implementation)
    @Override
    public void sort(Comparator<T> comparator) {
        if (isEmpty() || firstNode.next == null) return;
        
        boolean swapped;
        do {
            swapped = false;
            Node current = firstNode;
            Node prev = null;
            
            while (current.next != null) {
                if (comparator.compare(current.data, current.next.data) > 0) {
                    // Swap data
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                prev = current;
                current = current.next;
            }
        } while (swapped);
    }

    // Filter the queue based on a condition
@Override
public QueueInterface<T> filter(QueueInterface.Condition<T> condition) {
    LinkedQueue<T> filteredQueue = new LinkedQueue<>();
    Node current = firstNode;
    
    while (current != null) {
        if (condition.test(current.data)) {
            filteredQueue.enqueue(current.data);
        }
        current = current.next;
    }
    
    return filteredQueue;
}

    // Convert queue to array (alternative to List)
    public T[] toArray(T[] array) {
        if (array.length < count) {
            array = (T[]) java.lang.reflect.Array.newInstance(
                array.getClass().getComponentType(), count);
        }
        
        Node current = firstNode;
        int i = 0;
        while (current != null) {
            array[i++] = current.data;
            current = current.next;
        }
        
        return array;
    }
    
    private LinkedQueue<T> getHoldQueue() {
        if (holdQueue == null) {
            holdQueue = new LinkedQueue<>();
        }
        return holdQueue;
    }
    // Hold an item (remove and store temporarily)
    @Override
    public boolean hold(T item) {
        if (!contains(item)) return false;
        
        LinkedQueue<T> tempQueue = new LinkedQueue<>();
        boolean found = false;
        
        while (!isEmpty()) {
            T current = dequeue();
            if (!found && current.equals(item)) {
                getHoldQueue().enqueue(current); // Use getter
                found = true;
            } else {
                tempQueue.enqueue(current);
            }
        }
        
        // Restore the queue
        while (!tempQueue.isEmpty()) {
            enqueue(tempQueue.dequeue());
        }
        
        return found;
    }

    // Release held items back to queue
    @Override
    public void release() {
        while (!holdQueue.isEmpty()) {
            enqueue(holdQueue.dequeue());
        }
    }

    // Swap two items in the queue
    @Override
    public boolean swap(T item1, T item2) {
        if (item1.equals(item2)) return true;
        
        Node node1 = null, node2 = null;
        Node current = firstNode;
        
        // Find the nodes
        while (current != null && (node1 == null || node2 == null)) {
            if (current.data.equals(item1)) {
                node1 = current;
            }
            if (current.data.equals(item2)) {
                node2 = current;
            }
            current = current.next;
        }
        
        // Swap if both found
        if (node1 != null && node2 != null) {
            T temp = node1.data;
            node1.data = node2.data;
            node2.data = temp;
            return true;
        }
        
        return false;
    }

    
    
    private class Node {
        T data;
        Node next;
        
        Node(T data) {
            this(data, null);
        }
        
        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
