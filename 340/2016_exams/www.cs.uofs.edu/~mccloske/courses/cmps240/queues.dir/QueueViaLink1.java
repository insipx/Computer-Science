/** An instance of this class represents a queue capable of holding items of
**  type T (the generic type parameter).
**  The implementation is based on storing the queue items in a linked
**  structure of objects arising from the Link1 class.
**
**  Author: R. McCloskey
**  Date: March 2012
*/
public class QueueViaLink1<T> implements Queue<T> {

   /*  i n s t a n c e    v a r i a b l e s  */

   private Link1<T> front;  // reference to Link1 object holding front item 
   private Link1<T> rear;   // reference to Link1 object holding rear item 
   private int numItems;    // # of items on the queue


   /*  c o n s t r u c t o r s  */

   public QueueViaLink1() 
   { 
      front = null;  rear = null;  numItems = 0;
   }


   /*  o b s e r v e r s  */

   
   public int sizeOf() { return numItems; }

   public boolean isEmpty() { return sizeOf() == 0; }

   public T frontOf() 
   { 
      return front.getItem();  // alternative: return item(0)
    }

   public T item(int k)
   {
      Link1<T> x = front;           // have x begin at the front
      for (int i=0; i!=k; i++) {    // follow next references k times
         x = x.getNext();
      }
      return x.getItem();         // return the item there
   }

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
      Link1<T> newRear = new Link1<T>(item, null);
      if (rear == null)
         { front = newRear; }
      else
         { rear.setNext(newRear); }
      rear = newRear;
      numItems = numItems + 1;
   }

   public void dequeue()
   { 
      front = front.getNext();
      numItems = numItems - 1;
   }

}
