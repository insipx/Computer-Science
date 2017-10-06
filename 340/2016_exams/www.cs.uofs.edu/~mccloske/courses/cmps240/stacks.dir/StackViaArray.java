/** An instance of this class represents a stack capable of holding items
**  of the specified type T (the generic type parameter).
**  The implementation is based on storing the stack items in an array.
**
**  Author: R. McCloskey
**  Date: March 2012
*/

public class StackViaArray<T> implements Stack<T> {


   /*  i n s t a n c e    v a r i a b l e s  */

   private int numItems;  // # items occupying the stack
   private T[] items;     // holds (references to) the items on the stack


   /*  s y m b o l i c   c o n s t a n t  */

   private static final int DEFAULT_INIT_CAPACITY = 8;


   /*  c o n s t r u c t o r s  */

   public StackViaArray(int initCapacity)
   {
      numItems = 0;
      items = (T[])(new Object[initCapacity]);
   }

   public StackViaArray() { this( DEFAULT_INIT_CAPACITY); }


   /*  o b s e r v e r s  */

   public boolean isEmpty() { return sizeOf() == 0; }

   public int sizeOf() { return numItems; }

   public T topOf() { return item(0); }

   public T item(int k) { return items[numItems-1-k]; }

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

   public void push( T item )
   {
      if (numItems == items.length)
      {
         // items[] is full, so double its length by creating a new array
         // (having double the length), copying the values from items[]
         // into the new array, and then making items[] refer to the new array
         T[] temp = (T[])(new Object[2 * items.length]);
         arrayCopy(items, temp, numItems);
         items = temp; 
      } 
      items[numItems] = item;
      numItems = numItems + 1;
   }


   public void pop()
   {
      items[numItems-1] = null;  // to aid garbage collection
      numItems = numItems - 1;

      if (items.length > DEFAULT_INIT_CAPACITY  && items.length > 4 * numItems)
      { 
         // The length of items[] is greater than the default initial capacity
         // and more than four times the stack's size, so cut the length of
         // items[] in half.
         T[] temp = (T[])(new Object[items.length / 2]);
         arrayCopy(items, temp, numItems);
         items = temp;
      }
   }


   /*  u t i l i t y  */

   /** Copies values in source[0..length-1] into dest[0..length-1]
   */
   private void arrayCopy(T[] source, T[] dest, int length)
   {
      System.arraycopy(source, 0, dest, 0, length);  // uses library method
      // alternative:
      // for (int i=0; i != length; i++)
      //    { dest[i] = source[i]; }
   }
}
