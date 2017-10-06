/* An instance of this class has a method that partitions a given array into
** two segments in accord with a given Red-Blue Classifier.
** After partitioning an array, it can report how many swaps occurred
** while doing so, as well as the location of the boundary between the
** Red and Blue segments.
*/

public class RedBluePartitioner {

   // instance variables
   // ------------------

   // With respect to the most recent execution of the partition() method, 
   // these variables store the # of swaps that occurred and the location of
   // the Red/Blue segment boundary, respectively.
   private int swapCount; 
   private int r;     


   // observers
   // ---------

   /* Returns the location marking the boundary between the Red and Blue
   ** segments of whatever array was most recently partitioned by this
   ** Partitioner.  Specifically, the Red segment includes the locations 
   ** in the range [0,redBlueBoundary()) and the Blue segment includes
   ** the locations in the range [redBlueBoundary(),N), where N is the 
   ** length of the array.
   */
   public int redBlueBoundary() { return r; }


   /* Returns the number of swaps that occurred during the most recent
   ** execution of the partition() method.
   */
   public int swapCount() { return swapCount; }


   /* Partitions the given array in accord with the given Red-Blue
   ** classifier.  This method employs a loop whose invariant says that
   ** the Red, Blue, and "?" array segments are in that order.
   */
   public <T> void partition(T[] A, RedBlueClassifier<T> c)
   {
      swapCount = 0; 
      r = 0;         // no declaration; this is an instance variable
      int b = 0;

      assert 0<=r && r <=b && b<=A.length : "Boundary breach";
      assert allRed(c,A,0,r) :  "Red segment breach";
      assert allBlue(c,A,r,b) : "Blue segment breach";

      // loop invariant: 0 <= r <= b <= A.length &&
      //                 all elements in A[0..r-1] are Red &&
      //                 all elements in A[r..b-1] are Blue 
      while (b != A.length) {
         if (c.isRed(A[b])) {
            swap(A, r, b);
            r++; b++;
         }
         else if (c.isBlue(A[b])) { 
            b++;
         }
         else {
            System.out.println("This should never be printed.");
         }
         assert 0<=r && r <=b && b<=A.length : "Boundary breach";
         assert allRed(c,A,0,r) :  "Red segment breach";
         assert allBlue(c,A,r,b) : "Blue segment breach";
      }
   }


   /* Swaps the values occupying the specified locations in the specified array.
   */
   private <T> void swap(T[] ary, int i, int j) {
      T temp = ary[i]; ary[i] = ary[j]; ary[j] = temp; swapCount++;
   }

   /* Returns true if all elements in the specified array segment
   ** (i.e., ary[low..high-1]) are classified as Red by the specified
   ** Classifier c, false otherwise.
   ** pre: 0 <= low <= high <= ary.length
   */
   private <T> boolean allRed(RedBlueClassifier<T> c,
                              T[] ary, int low, int high)  {
      int i = low;
      // loop invariant: all elements in ary[low..i-1] are Red
      while (i != high  &&  c.isRed(ary[i])) { 
         i++;
      }
      return i == high;
   }


   /* Returns true if all elements in the specified array segment
   ** (i.e., ary[low..high-1]) are classified as Blue by the specified
   ** Classifier c, false otherwise.
   ** pre: 0 <= low <= high <= ary.length
   */
   private <T> boolean allBlue(RedBlueClassifier<T> c,
                               T[] ary, int low, int high)  {
      int i = low;
      // loop invariant: all elements in ary[low..i-1] are Blue
      while (i != high  &&  c.isBlue(ary[i])) { 
         i++;
      }
      return i == high;
   }

}
