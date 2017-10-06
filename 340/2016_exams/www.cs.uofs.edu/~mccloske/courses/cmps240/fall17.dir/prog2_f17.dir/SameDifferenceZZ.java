import java.util.ArrayList;

/* Java class containing static methods (one "defensive", one not) that,
** given two increasing-ordered arrays of integers and an integer 'delta'
** value, finds all pairs (i,j) of locations such that the difference between
** the i-th element of the first array and the j-th element of the second is
** equal to delta.
**
** Author: R. McCloskey and ...
** Assisted by: ...
** Known Defects: ...
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
      return null;  // STUB!
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
                                                          int delta)
   {
      return null;  // STUB!
   }

}
