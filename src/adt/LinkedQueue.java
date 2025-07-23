/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.NoSuchElementException;

/**
 *
 * @author user
 */
 public class LinkedQueue<T> implements QueueInterface<T> {
    private Node firstNode; // Front of the queue
    private Node lastNode;  // Back of the queue
    private int count;      // Track size for O(1) size() operation

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
