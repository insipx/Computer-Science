/** An instance of this class contains a reference to an object of the
**  specified type T (the generic type parameter) and a reference to
**  an object of the same kind (i.e., Link1<T>).  The idea is that objects
**  of this class can be used as building blocks of one-directional linked
**  structures (i.e., one-way lists).
**
**  Author: R. McCloskey
**  Date: March 2012
*/
public class Link1<T> { 

   /*  instance variables  */

   private T item;
   private Link1<T> next;

   /*  constructors  */

   public Link1(T item, Link1<T> next)
   { 
      this.item = item; this.next = next;
   }

   public Link1(T item) { this(item, null); }

   public Link1() { this(null, null); }


   /*  observers  */

   public T getItem() { return item; }
   public Link1<T> getNext() { return next; }


   /*  mutators  */

   public void setItem(T newItem) { item = newItem; }
   public void setNext(Link1<T> newNext) { next = newNext; }

}
