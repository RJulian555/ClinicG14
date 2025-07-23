=== ADT PACKAGE GUIDE ===
Purpose:
- Contains Abstract Data Type (ADT) interfaces and implementations
- Provides reusable data structures for the entire system

What to put here:
1. INTERFACE FILES (e.g., QueueInterface.java)
   - Define method signatures only (no implementation)
   - Example structure:
     ```
     public interface QueueInterface<T> {
         void enqueue(T item);  // Add to back
         T dequeue();           // Remove from front
         boolean isEmpty();
     }
     ```

2. IMPLEMENTATION FILES (e.g., LinkedQueue.java)
   - Must implement the interface completely
   - Should include private helper classes if needed
   - Example structure:
     ```
     public class LinkedQueue<T> implements QueueInterface<T> {
         private Node first, last;
         private class Node { /*...*/ }
         
         @Override
         public void enqueue(T item) { /*...*/ }
     }
     ```

Rules:
- Never put business logic here
- All methods must match interface exactly
- Use generics (<T>) for type safety
