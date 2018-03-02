import java.util.Iterator;

/* An instance of this class behaves as a stack.
*/

public class StackViaAryBounded<T> {

   // instance constant
   // -----------------

   private final int CAPACITY;


   // instance variables
   // ------------------

   private T[] items;   // stores items on the stack
   private int size;    // # of items currently on the stack


   // constructor
   // -----------

   public StackViaAryBounded(int capacity) {
      CAPACITY = capacity;
      size = 0;
      items = (T[])(new Object[CAPACITY]);
   }


   // observers
   // ---------

   /* Returns the capacity of this stack (i.e., the number of
   ** items that it is capable of holding at any one time).
   */
   public int capacityOf() { return CAPACITY; }


   /* Returns the size of this stack (i.e., the number of
   ** items that currently occupy it).
   */
   public int sizeOf() { return size; }


   /* Returns the item at the top of the stack.
   ** 
   ** pre: sizeOf() > 0
   */
   public T topOf() { return items[size-1]; }


   // mutators
   // --------

   /* Places the specified item onto the top of the stack.
   **
   ** pre: sizeOf() < capacityOf()
   */
   public void push(T item) {
      items[size] = item;
      size = size + 1;
   }

   /* Removes the top item from the stack.
   ** 
   ** pre: sizeOf() > 0
   */
   public void pop() {
      items[size-1] = null;  // Not necessary, but a good idea
      size = size - 1;
   }


   // iterator
   // --------

   public Iterator<T> iterator() {
      return new StackViaAryBoundedIterator();
   }


   /* An instance of this class iterates over the elements of this
   ** stack, from top to bottom.
   */
   private class StackViaAryBoundedIterator implements Iterator<T> {

      private int crrntPos;

      /* Establishes this Iterator so that its current position is at
      ** the top of the stack.
      */
      public StackViaAryBoundedIterator() {
         crrntPos = size - 1;
      }

      /* Reports whether or not there is at least one more element
      ** remaining for the next() method to return.
      */
      public boolean hasNext() { return crrntPos != -1; }

      /* Returns the next item from the stack and advances this
      ** Iterator's position one place toward the bottom.
      */
      public T next() {
         T result = items[crrntPos];
         crrntPos = crrntPos - 1;
         return result;
      }

      /* Does nothing.  Is included to make this class have all the
      ** methods specified in the Iterator interface.
      */
      public void remove() { }

   }  // end of private class

}
