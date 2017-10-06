import java.util.Iterator;

/* DynAnchArray.java
** Author: R. McCloskey and ...
** Date: August 2017
** Assisted by: ...
** Known flaws: ...
**
** An instance of this class, the name of which is an abbreviation for
** "Dynamically Anchored Array", has the same basic functionality as an 
** array, with the difference being that the client specifies, when creating
** it, the lower and upper bounds of its index range.  All arrays in Java have
** an index range with lower bound zero, which is sometimes an inconvenience.
*/

public class DynAnchArray<T> {

   //*************  I n s t a n c e  V a r i a b l e s  ***********

   private T[] ary;        // Array in which the values in this DynAnchArray are stored 
   private final int LOW;  // lower bound (inclusive) of this DynAnchArray's index range
   private final int HIGH; // upper bound (exclusive) of this DynAnchArray's index range


   //*************  C o n s t r u c t o r  *************

   /** Establishes the lower and upper bounds of this DynAnchArray's index
   **  range.  Following Java's convention (as exemplified not only by arrays
   **  but also the String and ArrayList classes), the index range begins with
   **  the lower bound and extends up to, but not including, the upper bound.  
   **
   **  excep: IllegalArgumentException is thrown if indexLow > indexHigh.
   */
   public DynAnchArray(int indexLow, int indexHigh) {
      if (indexLow > indexHigh) {
         throw new IllegalArgumentException("indexLow > indexHigh");
      }
      LOW = indexLow;
      HIGH = indexHigh;
      ary = (T[])(new Object[HIGH-LOW]);
   }

      
   //*************  O b s e r v e r s  *************


   /** Returns the lower bound of this DynAnchArray's index range.
   */
   public int indexLowerBound() { return 0; }    // STUB!


   /** Returns the upper bound of this DynAnchArray's index range.
   */
   public int indexUpperBound() { return 0; }    // STUB!


   /** Returns this DynAnchArray's length (i.e., number of positions
   **  at which values can be stored).
   */
   public int length() { return 0; }   // STUB!


   /** Returns the item at the specified position (k) of this DynAnchArray.
   **
   **  excep: IndexOutOfBoundsException is thrown unless
   **         indexLowerBound() <= k < indexUpperBound()
   */
   public T get(int k) { return null; }   // STUB!

   /* Returns a String depicting the current contents of this DynanchArray.
   */
   public String toString() { return ""; }   // STUB!!


   //*************  M u t a t o r  *************

   /** Places the specified item (obj) into the specified position (k)
   **  of this DynAnchArray (replacing whatever value had been at that
   **  position).
   **
   **  excep: IndexOutOfBoundsException is thrown unless
   **         indexLowerBound() <= k < indexUpperBound()
   */
   public void put(int k, T obj) { }   // STUB!


   //*************  I t e r a t o r  *************

   /** Returns an Iterator that can iterate over all the elements of this
   **  DynAnchArray.
   */
   public Iterator<T> iterator() { return new DynAnchArrayIterator(); }

   private class DynAnchArrayIterator implements Iterator<T> {


      /* Initializes this Iterator so that it is poised to iterate
      ** over the elements of this DynAnchArray (from first to last).
      */
      public DynAnchArrayIterator() { }   // STUB!


      /* Returns the next element of this DynAnchArray and advances
      ** to the position following it.
      **
      ** pre: hasNext()
      */
      public T next() { }    // STUB!


      /* Returns true if this Iterator has not yet advanced past the 
      ** last position of this DynAnchArray, and false otherwise.
      */
      public boolean hasNext() { return false; }  // STUB!

      /* Does nothing.  (This method must be included in this class in order
      ** to fulfill its obligation to implement the java.util.Iterator 
      ** interface.)
      */
      public void remove() { }   // Not a stub!!

   }  // end of nested class

}

