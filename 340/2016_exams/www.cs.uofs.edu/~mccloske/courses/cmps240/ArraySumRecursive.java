import java.util.Scanner;
import java.util.Random;

/* Java application that illustrates the use of recursion to compute the sum
** of the elements in an array of integer values.
**
** Two methods for computing such a sum are included, each having an 
** "auxiliary" method that does all the work.
**
** One is based on the observation that the sum of a nonempty sequence of 
** numbers is obtained by adding the last element in the sequence to the sum
** of the subsequence that precedes it.
**
** The other method uses the observation that the sum of a nonempty sequence of
** numbers is obtained by adding the sums of the first and second halves of
** that sequence (each of which is, of course, a shorter subsequence).  
**
** Where N is the length of the array, the second approach requires 2N-1 
** calls to the (auxiiary) method whereas the first always requires only N+1.
** However, the calls to the method employing the first approach always descend
** to a depth of N+1 whereas in the second a depth of only ceiling(lg N) + 1 is 
** reached (and therefore the latter requires significantly less memory).
**
** During execution, each auxiliary method displays messages that provide a
** narration of its progress.
**
** Author: R. McCloskey, Sept. 2017
*/

public class ArraySumRecursive {

   private static int callCntr;
   private static int depth;


   /* Returns the sum of the elements in the given array.
   */
   public static int sumOf1(int[] A) {
      callCntr = 0;  depth = 0;
      return sumOf1Aux(A, A.length-1);
   }

   /* Returns the sum of the elements in the array segment A[0..high],
   ** where A and high are given.  The logic is based on the observation
   ** that the sum of the elements in A[0..high] is zero if high = -1
   ** (because A[0..-1] has no elements) and, otherwise, is obtained by
   ** adding A[high] to the sum of the elements in A[0..high-1].
   */
   public static int sumOf1Aux(int[] A, int high) {
      callCntr++; depth++;
      printEnteringMessage(0, high);

      // Here begins the real logic of the method:
      int result;
      if (high == -1) {
         result = 0;    // Empty segment
      }
      else {
         result = sumOf1Aux(A, high-1) + A[high];
      }
      // End of real logic.

      printLeavingMessage(0, high, result);

      depth--; 
      return result;
   }

   /* Returns the sum of the elements in the given array.
   */
   public static int sumOf2(int[] A) {
      callCntr = 0;  depth = 0;
      return sumOf2Aux(A, 0, A.length-1);
   }

   /* Returns the sum of the elements in the array segment A[low..high],
   ** where A and high are given.  The logic is based on the observation
   ** that the sum of the elements in A[low..high] is 
   ** (1) zero if low > high (because then A[low..high] has no elements), 
   ** (2) A[low] if low = high (because then A[low..high] has one element), and
   ** (3) obtained by adding the sum of the elements in A[low..mid] to the
   **     sum of the elements in A[mid+1..high], where low <= mid < high.
   */
   public static int sumOf2Aux(int[] A, int low, int high ) {
      callCntr++; depth++;
      printEnteringMessage(low, high);

      // Here begins the real logic of the method:
      int result;
      if (low > high) {
         result = 0;   // empty segment
      }
      else if (low == high) {
         result = A[low];   // segment of length one
      }
      else {
         int mid = (low + high) / 2;
         result = sumOf2Aux(A, low, mid) + sumOf2Aux(A, mid+1, high);
      }
      // End of real logic.

      printLeavingMessage(low, high, result);

      depth--; 
      return result;
   }


   /* Prints the specified number of spaces.
   ** pre: num >= 0
   */
   private static void printSpaces(int num) {
      for (int i=0; i != num; i++) { System.out.print(' '); }
   }

   private static void printEnteringMessage(int low, int high) {
      printSpaces(2*depth - 2);
      System.out.println("Entering!  Depth: " + depth + 
                         "; CallCount: " + callCntr +
                         "; Computing sum of elements in A[" + low + 
                         ".." + high + "]");
   }

   private static void printLeavingMessage(int low, int high, int result) {
      printSpaces(2*depth - 2);
      System.out.println("Leaving!  Depth: " + depth + 
                         "; CallCount: " + callCntr +
                         "; sum of elements in A[" + low + ".." + 
                         high + "] is " + result);
   }

   /* Returns a new array of int's of the specified length.
   ** Elements are filled with values generated pseudo-randomly.
   */
   private static int[] makeArray(int len) {
      int[] result = new int[len];
      Random r = new Random();
      for (int i=0; i != len; i++) {
         result[i] = r.nextInt(15) - 5;  // value between -5 and 9
      }
      return result;
   }

   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      System.out.print("Enter length of array to sum:");
      int length = input.nextInt();

      int[] ary = makeArray(length);

      sumOf1(ary);
      System.out.println("------------------------\n");
      sumOf2(ary);
   }
}
