import java.util.Arrays;

/* Java application that performs some 3-color partitioning on arrays in 
** order to test the code in interfaces/classes that work together to provide
** that capability, including RedWhiteBlueClassifier (interface) and the 
** classes NegZeroPosClassifier (that classifies Integers as negative, zero,
** or positive), YoungMiddleOldClassifier (that classifies Persons as young,
** middle-aged, or old) and RedWhiteBluePartitioner.
** 
** Author: R. McCloskey, Sept. 2017
*/
public class Partition3App {

   static final char SPACE = ' ';
   static final char NEWLINE = '\n';

   // Create the Red-White-Blue partitioner used in this program.
   static RedWhiteBluePartitioner partitioner = new RedWhiteBluePartitioner();
   
   public static void main(String[] args)
   {
      // Partition an Integer array into negative/zero/positive segments
      Integer[] b = someIntegerArray();
      partitionArray(b, new NegZeroPosClassifier(), SPACE);

      System.out.println("\n-----------------------------");
      // Partition a Person array by age into young/middle/old segments,
      // using default age boundary values.
      Person[] p = somePersonArray();
      partitionArray(p, new YoungMiddleOldClassifier(), NEWLINE);

      System.out.println("\n-----------------------------");
      // Partition a Person array by age into young/middle/old segments,
      // using 20 and 55 as the age boundary values.
      p = somePersonArray();
      partitionArray(p, new YoungMiddleOldClassifier(20,55), NEWLINE);
   }

   /* Displays the elements of the specified array (each one followed by the 
   ** specified delimiter character), partitions the array in accord with the 
   ** specified Red-White-Blue clasifier, and then displays the contents of
   ** the Red, White, and Blue segments of the array.
   */
   private static <T> void partitionArray(T[] a, 
                                          RedWhiteBlueClassifier<T> rbwc,
                                          char delimiter)
   {
      System.out.print("About to partition this array:\n");
      printArySeg(a,0,a.length, delimiter);
      partitioner.partition(a, rbwc);
      printReport(a, delimiter);
   }


   /* Given a RedWhiteBluePartitioner and the array that it most recently
   ** partitioned, displays the results.  Specifically, it displays the
   ** number of swaps that occurred and the contents of each of the two
   ** segments of the array.
   */
   private static void printReport(Object[] ary, char delimiter)
   {
      int rwBoundary = partitioner.redWhiteBoundary();
      int wbBoundary = partitioner.whiteBlueBoundary();

      System.out.println("\n# of swaps: " + partitioner.swapCount());
      System.out.println("Red segment values: ");
      printArySeg(ary, 0, rwBoundary, delimiter);
      System.out.println("\nWhite segment values: ");
      printArySeg(ary, rwBoundary, wbBoundary, delimiter);
      System.out.println("\nBlue segment values: ");
      printArySeg(ary, wbBoundary, ary.length, delimiter);
   }

   /* For each element in the specified array segment (i.e., ary[low..high-1]),
   ** its toString() image is displayed, followed by the specified delimiter 
   ** character.
   */
   private static void printArySeg(Object[] ary, int low, int high, char delimiter) {
      for (int i = low; i != high; i++) {
         System.out.print(ary[i]);  System.out.print(delimiter);
      }
   }

   /* Returns an array of Integer objects.
   */
   private static Integer[] someIntegerArray() {
     return new Integer[] { 5, -13, 0, -6, 1, -8, 7, 9, 0, -4, 2, 7 };
   }

   /* Returns an array of Person objects.
   */
   private static Person[] somePersonArray() {
     return new Person[] { 
        new Person("Carol", 28, 5.7),
        new Person("Jim", 15, 5.4),
        new Person("Mike", 65, 6.0),
        new Person("Mary", 36, 5.5),
        new Person("Helen", 89, 5.6),
        new Person("Larry", 9, 4.2),
        new Person("Julie", 24, 6.0),
        new Person("John", 40, 5.9)
     };
   }

}
