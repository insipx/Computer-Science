/** Java application whose purpose is to make a simple test of the
**  QueueViaArray and QueueViaLink1 classes.
*/

public class QueueTestApp {


   public static void main(String[] args)
   {
      System.out.println("About to test a QueueViaArray object:");
      testQueueViaArray();

      System.out.println();
      System.out.println("About to test a QueueViaLink1 object:");
      testQueueViaLink1();
   }

   private static void testQueueViaArray() 
   {
      // make q a QueueViaArray object capable of holding Integer objects
      Queue<Integer> q = new QueueViaArray<Integer>();
      System.out.println("Upon creation, q = " + q.toString());
      loadAndEmpty(q);
   }

   private static void testQueueViaLink1() 
   {
      // make q a QueueViaLink1 object capable of holding Integer objects
      Queue<Integer> q = new QueueViaLink1<Integer>();
      loadAndEmpty(q);
   }



   private static void loadAndEmpty(Queue q)
   {
      // place Integer objects corresponding to 0 through 9 into q,
      // each time displaying the contents of q
      for (int i=0; i != 10; i++)
      {
         System.out.println("Item about to be added: " +  i); 
         q.enqueue(i);  // q.enqueue(new Integer(i)); 
         System.out.print("q now holds: ");
         System.out.println(q.toString());
      }

      System.out.println();
      
      // now remove the objects from q, displaying each one's value 
      // and the updated contents of q
      while (!q.isEmpty()) {
         System.out.println("Item about to be removed: " +  q.frontOf()); 
         q.dequeue();
         System.out.print("q now holds: ");
         System.out.println(q);
      }
   }
}
