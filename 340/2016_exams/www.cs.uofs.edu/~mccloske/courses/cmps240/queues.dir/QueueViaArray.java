/** An instance of this class represents a queue capable of holding items
**  of the specified type T (the generic type parameter).
**  An array (used in a "wraparound" fashion) is the basis for the
**  underlying implementation.
**
**  Author: R. McCloskey
**  Date: March 2012
*/

public class QueueViaArray<T> implements Queue<T> {


   /*  i n s t a n c e    v a r i a b l e s  */

   private int numItems;  // # items occupying the queue
   private T[] items;     // holds (references to) the items on the queue
   private int frontLoc;  // location in array at which the front item occurs
                          // The kth item on the queue is stored in 
                          // items[(startLoc+k) % items.length]

   /*  s y m b o l i c   c o n s t a n t  */

   private static final int DEFAULT_INIT_CAPACITY = 8;


   /*  c o n s t r u c t o r s  */

   public QueueViaArray(int initCapacity)
   {
      numItems = 0;
      items = (T[])(new Object[initCapacity]);
      frontLoc = 0;
   }

   public QueueViaArray() { this( DEFAULT_INIT_CAPACITY); }


   /*  o b s e r v e r s  */

   public boolean isEmpty() { return sizeOf() == 0; }

   public int sizeOf() { return numItems; }

   public T frontOf() { return item(0); }

   public T item(int k) { return items[(frontLoc+k) % items.length]; }


   public String toString()
   {
      String result = "";
      if (!isEmpty()) {
         StringBuilder s = new StringBuilder();
         s.append(item(0).toString());
         for (int i=1; i != sizeOf(); i++)
         {
            s.append(", " + item(i).toString());
         }
         result = s.toString();
      }
      return result;
   }



   /*  m u t a t o r s  */

   public void enqueue( T item )
   {
      if (numItems == items.length)
      {
         // items[] is full, so double its length by creating a new array
         // (having double the length), copying the values from items[]
         // into the new array, and then making items[] refer to the new array
         T[] temp = (T[])(new Object[2 * items.length]);
         arrayCopy(items, frontLoc, temp, numItems);
         items = temp; 
      } 
      items[(frontLoc + numItems) % items.length] = item;
      numItems = numItems + 1;
   }


   public void dequeue()
   {
      items[frontLoc] = null;                   // to aid garbage collection
      frontLoc = (frontLoc + 1) % items.length;
      numItems = numItems - 1;

      if (items.length > DEFAULT_INIT_CAPACITY  && items.length > 4 * numItems)
      { 
         // The length of items[] is greater than the default initial capacity
         // and more than four times the stack's size, so cut the length of
         // items[] in half.
         T[] temp = (T[])(new Object[items.length / 2]);
         arrayCopy(items, frontLoc, temp, numItems);
         items = temp;
         frontLoc = 0;
      }
   }

   /*  u t i l i t y  */

   /** Copies values in source[startLoc..((startLoc+length-1)%source.length)]
   **  (in wraparound fashion) into dest[0..length-1]
   */
   private void arrayCopy(T[] source, int startLoc, T[] dest, int length)
   {
      for (int i=0; i != length; i++)
         { dest[i] = source[(startLoc+i) % source.length]; }
   }
}
