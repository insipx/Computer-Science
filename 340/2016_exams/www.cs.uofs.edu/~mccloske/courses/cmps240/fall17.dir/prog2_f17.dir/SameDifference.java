import java.util.ArrayList;

/* Java class containing static methods (one "safe", one not) that, given
** two increasing-ordered arrays of integers and an integer 'delta' value,
** finds all pairs (i,j) of locations such that the difference between the
** i-th element of the first array and the j-th element of the second is
** delta.
**
** Author: R. McCloskey 
** Known Defects: none
*/

public class SameDifference {


   /* Given two arrays of ints, ary1[] and ary2[], each of whose elements are 
   ** assumed to be in increasing order (meaning that each element after the
   ** first is greater than its predecessor), and an integer delta value,
   ** returns an ArrayList containing precisely those pairs (i,j) such that 
   ** ary1[i] - ary2[j] == delta.
   ** (The pairs are instances of the class PairOfInts.)
   **
   ** pre: The elements in each of ary1[] and ary2[] are in increasing
   **      order.
   */
   public static ArrayList<PairOfInts> findPairs(int[] ary1, 
                                                 int[] ary2, 
                                                 int delta)
   {
      int loopCntr = 0;
      ArrayList<PairOfInts> aryList = new ArrayList<PairOfInts>();
      int i = 0;
      int j = 0;
      // For the purposes of making the loop invariant simpler to express, we
      // assume that each of ary1[ary1.length] and ary2[ary2.length] has value
      // positive infinity.
      // loop invariant: 
      //    for all k in 0..j-1, ary1[i] - ary2[k] > delta  &&
      //    for all k in 0..i-1, ary1[k] - ary2[j] < delta  &&
      //    result contains all pairs (m,n), with 0<=m<i and 0<=n<j, such that
      //    ary1[m] - ary2[n] = delta.
      while (i != ary1.length  &&  j != ary2.length) {
         loopCntr++;
         int diff = ary1[i] - ary2[j];
         if (diff < delta)
            { i = i+1; }
         else if (diff > delta) 
            { j = j+1; }
         else { 
            aryList.add(new PairOfInts(i,j));
            i = i+1;
            j = j+1;
         }
      }
      System.out.println("# loop iterations: " + loopCntr);
      return aryList;
   }

   /* Given two arrays of ints, ary1[] and ary2[], each of whose elements are
   ** intended to be in increasing order (meaning that each element other
   ** than the first is greater than its predecessor), and a integer delta
   ** value, returns an ArrayList containing precisely those pairs (i,j)
   ** such that ary1[i] - ary2[j] == delta. 
   ** (The pairs are instances of the class PairOfInts.)
   **
   ** excep: If either array's elements are not in increasing order, an
   **        IllegalArgumentException is thrown.
   */
   public static ArrayList<PairOfInts> findPairsDefensive(int[] ary1, 
                                                          int[] ary2, 
                                                          int delta) {
      if (isIncreasing(ary1) && isIncreasing(ary2)) {
         return findPairs(ary1, ary2, delta);
      }
      else {
         throw new IllegalArgumentException("Array elements not in increasing order.");
      }
   }


   // private method
   // --------------

   /* Returns true if the elements in the given array are in increasing order,
   ** false otherwise.
   */
   private static boolean isIncreasing(int[] a) {
      int i = 1;
      // loop invariant: The elements in a[0..i-1] are in increasing order.
      while (i < a.length  &&  a[i-1] < a[i]) { 
         i = i+1;
      }
      // At this point, i < a.length implies that a[i-1] >= a[i] (so a[] is
      // not in increasing order) and i >= a.length implies that a's elements
      // are in increasing order.
      return i >= a.length;
   }
}
