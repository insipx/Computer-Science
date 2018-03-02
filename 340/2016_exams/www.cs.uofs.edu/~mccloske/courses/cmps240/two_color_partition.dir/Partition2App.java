import java.util.Arrays;

/* Java application intended to demonstrate how to 2-color partition an array using
** a RedBluePartitioner object supported by instances of classes that implement the
** RedBlueClassifier interface.  Specifically, an array of Integers is partitioned
** in accord with an EvenOddClassifier and a NegPosClassifier.
** 
** Author: R. McCloskey, Sept. 2017
*/
public class Partition2App {

   // Create the Red-Blue partitioner used in this program.
   static RedBluePartitioner partitioner = new RedBluePartitioner();
   
   public static void main(String[] args)
   {
      Integer[] b; 

      // Partition an Integer array into even/odd segments
      b = someIntegerArray();
      partitionArray(b, new EvenOddClassifier());

      System.out.println("\n-----------------------------");
      // Partition an Integer array into negative/nonnegative segments
      b = someIntegerArray();
      partitionArray(b, new NegPosClassifier());
   }

   /* Displays the elements of the specified array, partitions the array in 
   ** accord with the specified Red-Blue clasifier, and then displays the 
   ** contents of the Red and Blue segments of the array.
   */
   private static <T> void partitionArray(T[] a, 
                                          RedBlueClassifier<T> rbc)
   {
      System.out.print("About to partition this array:\n");
      printArySeg(a,0,a.length);
      partitioner.partition(a, rbc);
      printReport(a);
      System.out.println();
   }


   /* Given a RedBluePartitioner and the array that it most recently
   ** partitioned, displays the results.  Specifically, it displays the
   ** number of swaps that occurred and the contents of each of the two
   ** segments of the array.
   */
   private static void printReport(Object[] ary)
   {
      int rbBoundary = partitioner.redBlueBoundary();

      System.out.println("\n\n# of swaps: " + partitioner.swapCount());
      System.out.print("Red segment values: ");
      printArySeg(ary, 0, rbBoundary);
      System.out.print("\nBlue segment values: ");
      printArySeg(ary, rbBoundary, ary.length);
   }

   /* For each element in the specified array segment (i.e., ary[low..high-1]),
   ** its toString() image is displayed, followed by a space.
   */
   private static void printArySeg(Object[] ary, int low, int high) {
      for (int i = low; i != high; i++) {
         System.out.print(ary[i]);  System.out.print(' ');
      }
   }

   /* Returns an array of Integer objects.
   */
   private static Integer[] someIntegerArray() {
     return new Integer[] { 5, -13, 0, -6, 1, -8, 7, 9, 0, -4, 2, 7 };
   }

}
