import java.util.Iterator;

/* DynAnchArray.java
** Author: R. McCloskey
** Date: August 2017
** Assisted by:  no one
** Known flaws: None
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
   public int indexLowerBound() { return LOW; }  

   /** Returns the upper bound of this DynAnchArray's index range.
   */
   public int indexUpperBound() { return HIGH; } 

   /** Returns this DynAnchArray's length (i.e., number of positions
   **  at which values can be stored).
   */
   public int length() { return ary.length; } 

   /** Returns the item at the specified position (k) of this DynAnchArray.
   **
   **  excep: IndexOutOfBoundsException is thrown unless
   **         indexLowerBound() <= k < indexUpperBound()
   */
   public T get(int k) { 
      if (k < LOW ||  k >= HIGH) {
         throw new IndexOutOfBoundsException();
      }
      return ary[k - LOW];
   } 

   /* Returns a String depicting the current contents of this DynanchArray.
   */
   public String toString() {
      StringBuilder s = new StringBuilder();
      final int UPPER = indexUpperBound();
      for (int i = indexLowerBound(); i != UPPER; i++) {
         s.append(i + ": " + get(i) + '\n');
      }
      return s.toString();
   }


   //*************  M u t a t o r  *************

   /** Places the specified item (obj) into the specified position (k)
   **  of this DynAnchArray (replacing whatever value had been at that
   **  position).
   **
   **  excep: IndexOutOfBoundsException is thrown unless
   **         indexLowerBound() <= k < indexUpperBound()
   */
   public void put(int k, T obj) { 
      if (k < LOW  ||  k >= HIGH) {
         throw new IndexOutOfBoundsException();
      }
      ary[k - LOW] = obj;
   }


   //*************  I t e r a t o r  *************

   /** Returns an Iterator that can iterate over all the elements of this
   **  DynAnchArray.
   */
   public Iterator<T> iterator() { return new DynAnchArrayIterator(); }

   private class DynAnchArrayIterator implements Iterator<T> {

      private int posOfNext;  // position of next element to be returned via
                              // the next() method

      /* Initializes this Iterator so that it is poised to iterate
      ** over the elements of this DynAnchArray (from first to last).
      */
      public DynAnchArrayIterator() { posOfNext = indexLowerBound(); }


      /* Returns the next element of this DynAnchArray and advances
      ** to the position following it.
      **
      ** pre: hasNext()
      */
      public T next() { 
         T result = get(posOfNext);
         posOfNext = posOfNext + 1;
         return result;
      }

      /* Returns true if this Iterator has not yet advanced past the 
      ** last position of this DynAnchArray, and false otherwise.
      */
      public boolean hasNext() { return posOfNext < indexUpperBound(); }

      /* Does nothing.  (This method must be included in this class in order
      ** to fulfill its obligation to implement the java.util.Iterator 
      ** interface.)
      */
      public void remove() { }

   }  // end of nested class

}

