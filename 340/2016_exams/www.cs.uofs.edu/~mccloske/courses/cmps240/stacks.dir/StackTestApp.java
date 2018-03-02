/** Java application whose purpose is to make a simple test of the
**  StackViaArray and StackViaLink1 classes.
*/

public class StackTestApp {


   public static void main(String[] args)
   {
      System.out.println("About to test a StackViaArray object:");
      testStackViaArray();

      System.out.println();
      System.out.println("About to test a StackViaLink1 object:");
      testStackViaLink1();
   }

   private static void testStackViaArray() 
   {
      // make s a StackViaArray object capable of holding Integer objects
      Stack<Integer> s = new StackViaArray<Integer>();
      System.out.println("Upon creation, s = " + s.toString());
      loadAndEmpty(s);
   }

   private static void testStackViaLink1() 
   {
      // make s a StackViaLink1 object capable of holding Integer objects
      Stack<Integer> s = new StackViaLink1<Integer>();
      loadAndEmpty(s);
   }



   private static void loadAndEmpty(Stack s)
   {
      // place Integer objects corresponding to 0 through 9 into s,
      // each time displaying the contents of s
      for (int i=0; i != 10; i++)
      {
         System.out.println("Item about to be added: " +  i); 
         s.push(i);  // s.push(new Integer(i)); 
         System.out.print("s now holds: ");
         System.out.println(s.toString());
      }

      System.out.println();
      
      // now remove the objects from s, displaying each one's value 
      // and the updated contents of s
      while (!s.isEmpty()) {
         System.out.println("Item about to be removed: " +  s.topOf()); 
         s.pop();
         System.out.print("s now holds: ");
         System.out.println(s);
      }
   }
}
