/** An instance of this class represents a stack capable of holding items
**  of the specified type T (the generic type parameter).
**  The underlying implementation is of a hybrid kind that makes use of
**  both links and arrays: the stack elements are stored in a linked list of 
**  instances of the StackViaArray class.
**
**  Author: R. McCloskey
**  Date: October 2017
*/
public class StackViaHybrid<T> implements Stack<T> {

   private static final int DEFAULT_CAPACITY = 5;

   /*  i n s t a n c e    v a r i a b l e s  */

   // reference to Link1 object holding topmost stack in the list
   private Link1<Stack<T>> topNode;
   private int numNodes;      // # of nodes in the linked list
   private int stackCapacity; // capacity of the stack associated to each node


   /*  c o n s t r u c t o r s  */

   public StackViaHybrid(int capacity) 
   { 
      stackCapacity = capacity;
      topNode = null;
      numNodes = 0;
   }

   public StackViaHybrid() { this(DEFAULT_CAPACITY); }


   /*  o b s e r v e r s  */

   
   public int sizeOf() { 
      if (numNodes == 0) { return 0; }
      else { return (numNodes - 1) * stackCapacity + topStack().sizeOf(); }
   }

   public boolean isEmpty() { return sizeOf() == 0; }

   public T topOf() { return topStack().topOf(); }

   public T item(int k)
   {
      int topStackSize = topStack().sizeOf();
      if (k < topStackSize) {
         return topStack().item(k);
      }
      else {
         Link1<Stack<T>> node = topNode.next;  // getNext();
         k = k - topStackSize;
         while (k >= stackCapacity) {
            k = k - stackCapacity;
            node = node.next;  // getNext();
         }
         //return node.getItem().item(k);
         return node.datum.item(k);
      }
      //System.out.println("After loop, k = " + k);
   }

   public String toString()
   {
      // very inefficient!!  Can do better!
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
      if (topNode == null  ||  topStack().sizeOf() == stackCapacity) {
         StackViaArray<T> newStack = new StackViaArray(stackCapacity);
         Link1<Stack<T>> newNode = new Link1(newStack, topNode);
         topNode = newNode;
         numNodes++;
      }
      topStack().push(item);
   }

   public void pop() { 
      topStack().pop();
      if (topStack().sizeOf() == 0) {
         topNode = topNode.next; //getNext();
         numNodes--;
      }
   }


   // private utilities

   private Stack<T> topStack() { return topNode.datum; }  // getItem(); }

}
