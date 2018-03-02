import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

/* Java application having as its purpose to test the methods in the
** SameDifference class.
*/
public class SameDifferenceTester {

   public static void main(String[] args)
   {
      // Interpret the command line arguments (three are expected) to be the
      // desired lengths of the two arrays involved in the test, followed
      // by the desired delta value.
      int[] a = new int[Integer.parseInt(args[0])];
      int[] b = new int[Integer.parseInt(args[1])];
      int delta = Integer.parseInt(args[2]);

      // Place psuedo-random values into a[] and b[], making sure that
      // the elements in each are in increasing order.
      populate(a);  populate(b);  
      System.out.println("First array:\n  " + Arrays.toString(a));
      System.out.println("Second array:\n  " + Arrays.toString(b));
   
      ArrayList<PairOfInts> result = SameDifference.findPairs(a,b,delta);

      System.out.println("\nPairs of locations whose elements differ by " + 
                         delta + ":");
      for (PairOfInts pair : result) {
         System.out.print(pair);
         int aElem = a[pair.firstOf()];
         int bElem = b[pair.secondOf()];
         int diff = aElem - bElem;
         if (diff != delta) {
            System.out.print("; Wrong! " + aElem + "-" + bElem + 
                             "=" + diff + " != " + delta);
         }
         System.out.println();
      }
   }


   /* Places pseudo-randomly chosen values into the given array, but such
   ** that each element after the first is greater than its predecessor.
   */
   private static void populate(int[] ary) {
      Random r = new Random();
      // Place into location zero a value in range -3..6.
      ary[0] = r.nextInt(10) - 3;
      for (int i = 1; i != ary.length; i++) {
         // Place into location i a value greater than its predecessor by
         // between one and five.
         ary[i] = ary[i-1] + r.nextInt(5) + 1;
      }
   }

}
