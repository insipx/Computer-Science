/** An instance of a class implementing this interface represents a stack
**  capable of holding items of the specified type T (the generic type
**  parameter).
**
**  Author: R. McCloskey
**  Date: March 2012
*/
public interface Stack<T> {

   /*  <<<<<  o b s e r v e r s  >>>>>  */

   /** Returns the number of items on the stack.
   **  pre: none
   */
   public int sizeOf(); 

   /** Returns true if the stack is empty, false otherwise.
   **  pre: none
   */
   public boolean isEmpty(); 


   /** Returns (a reference to the) item at the top of the stack.
   **  pre: !this.isEmpty()
   */
   public T topOf();


   /** Returns (a reference to) the k-th item on the stack
   **  (counting starting at zero from the top).
   **  pre: 0 <= k < sizeOf()
   */
   public T item(int k);


   /*  <<<<<  m u t a t o r s  >>>>>  */


   /** Places the specified item onto the top of the stack.
   **  pre:  let s refer to the stack before applying push()
   **  post: sizeOf() = s.sizeOf()+1  &&
   **        for all i satisfying 0<=i<s.sizeOf(), item(i) == s.item(i) &&
   **        item(sizeOf()-1) == item
   */
   public void push(T item);


   /** Removes the item at the top of the stack.
   **  pre: !isEmpty()  &&  let s refer to the stack before applying pop()
   **  post: sizeOf() = s.sizeOf()-1  &&
   **        for all i satisfying 0<=i<sizeOf(), item(i) == s.item(i)
   */
   public void pop();

}
