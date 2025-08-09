/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.Comparator;

/**
 *
 * @author user
 */
public interface QueueInterface<T> {
    void enqueue(T item);
    T dequeue();
    T getFront();
    boolean isEmpty();
    void clear();
    int size();
    
    // additional methods
    boolean contains(T item);
    void sort(Comparator<T> comparator);
    QueueInterface<T> filter(Condition<T> condition);
    boolean hold(T item);
    void release();
    boolean sawp (T item1, T item2);
    //for filtering conditions
    interface Condition<T>{
        boolean test(T item);
    }
}
