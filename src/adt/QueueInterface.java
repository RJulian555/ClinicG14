package adt;

import java.util.Comparator;

/**
 * Interface for a queue ADT (Abstract Data Type) with standard and extended operations.
 * 
 * @author Group 14
 * @param <T> The type of elements held in this queue
 */

public interface QueueInterface<T> {
    
    /**
     * Task: Adds an item to the end of the queue.
     * 
     * @param item the object to be added
     * @throws IllegalStateException if the queue cannot accept new items
     */
    public void enqueue(T item);
    
    /**
    * Task: Removes and returns the item at the front of the queue.
    * 
    * @return the item at the front of the queue
    * @throws NoSuchElementException if the queue is empty
    */
    public T dequeue();
    
    /**
     * Task: Retrieves the item at the front of the queue without removing it.
     * 
     * @return the item at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public T getFront();
    
    /**
     * Task: Checks whether the queue is empty.
     * 
     * @return true if the queue is empty, false otherwise
     */
    
    public boolean isEmpty();

    /**
     * Task: Removes all items from the queue.
     */
    public void clear();

    /**
     * Task: Gets the number of items in the queue.
     * 
     * @return the integer number of items currently in the queue
     */
    public int size();

    /**
     * Task: Checks if the queue contains a specific item.
     * 
     * @param item the object to search for
     * @return true if the item is found in the queue, false otherwise
     */
    public boolean contains(T item);

    /**
     * Task: Sorts the queue using the provided comparator.
     * 
     * @param comparator the comparator to determine the order of elements
     */
    public void sort(Comparator<T> comparator);

    /**
     * Task: Returns an array containing all elements in the queue.
     * 
     * @param array the array into which elements will be stored if large enough
     * @return an array containing all queue elements
     * @throws ArrayStoreException if the runtime type of the array is not compatible
     */
    public T[] toArray(T[] array);

    /**
     * Task: Returns a filtered queue containing elements that match the condition.
     * 
     * @param condition the filtering condition
     * @return a new queue containing only matching elements
     */
    public QueueInterface<T> filter(Condition<T> condition);

    /**
     * Task: Temporarily removes and holds a specific item from the queue.
     * 
     * @param item the item to be held
     * @return true if the item was found and held, false otherwise
     */
    public boolean hold(T item);

    /**
     * Task: Releases all held items back into the queue.
     */
    public void release();

    /**
     * Task: Swaps two items in the queue.
     * 
     * @param item1 the first item to swap
     * @param item2 the second item to swap
     * @return true if both items were found and swapped, false otherwise
     */
    public boolean swap(T item1, T item2);

    /**
     * Functional interface for defining filter conditions.
     * 
     * @param <T> the type of elements to test
     */
    public interface Condition<T> {
        /**
         * Task: Tests whether to include an item in filtering.
         * 
         * @param item the element to test
         * @return true if the item should be included
         */
        public boolean test(T item);
    }
}
