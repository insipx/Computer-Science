/** An instance of a class implementing this interface represents a queue
**  capable of holding items of the specified type T (the generic type
**  parameter).
**
**  Author: R. McCloskey
**  Date: March 2012
*/
public interface Queue<T> {


   /*  <<<<<  o b s e r v e r s  >>>>>  */

   /** Returns the number of items on the queue.
   **  pre: none
   */
   public int sizeOf(); 

   /** Returns true if the queue is empty, false otherwise.
   **  pre: none
   */
   public boolean isEmpty(); 


   /** Returns (a reference to the) item at the front of the queue.
   **  pre: !isEmpty()
   */
   public T frontOf();


   /** Returns (a reference to) the k-th item on the queue
   **  (counting starting at zero from the front).
   **  pre: 0 <= k < sizeOf()
   */
   public T item(int k);


   /*  <<<<<  m u t a t o r s  >>>>>  */


   /** Places the specified item at the rear of the queue.
   **  pre:  let q == this
   **  post: this.sizeOf() = q.sizeOf()+1  && 
   **        this.item(this.sizeOf()-1) == item &&
   **        for all i satisfying 0<=i<q.sizeOf(), this.item(i) == q.item(i) 
   */
   public void enqueue(T item);


   /** Removes the item at the front of the queue.
   **  pre: !this.isEmpty()  &&  let this == q
   **  post: this.sizeOf() = q.sizeOf()-1  && 
   **        for all i satisfying 0<=i<this.sizeOf(), this.item(i) == q.item(i)
   */
   public void dequeue();

}
