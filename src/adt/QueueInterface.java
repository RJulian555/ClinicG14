/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

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
}
