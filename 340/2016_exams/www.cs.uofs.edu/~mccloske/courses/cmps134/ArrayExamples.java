/** Java class containing static methods that perform a variety of operations
**  upon on arrays of type int[] (i.e., arrays of int values).
**
**  Author: R. McCloskey
**  last updated : May 2017
*/
public class ArrayExamples {


   // S u m   o f   a r r a y   e l e m e n t s
   // -----------------------------------------

   /** Returns the sum of the elements in the specified array.
   */
   public static int sumOf(int[] a) { return sumOf(a, 0, a.length); }


   /** Returns the sum of the elements in the specified segment of
   **  the specified array (i.e., a[low..high-1]).
   **  Pre-condition: 0 <= low <= high <= a.length
   */
   public static int sumOf(int[] a, int low, int high) { 

      int sumSoFar = 0;
      int i = low;

      // loop invariant: sumSoFar = sum of elements in a[low..i-1]
      while (i != high) {
         sumSoFar = sumSoFar + a[i];
         i = i+1;
      }
      return sumSoFar;
   }


   // F i n d i n g   t h e   m i n i m u m
   // -------------------------------------

   /** Returns the minimum value in a[].
   **  Pre-condition: 0 < a.length
   */
   public static int minOf(int[] a) { return minOf(a, 0, a.length); }

   /** Returns the minimum value in a[low..high-1].
   **  Pre-condition: 0 <= low < high <= a.length
   */
   public static int minOf(int[] a, int low, int high) {
      return a[locOfMin(a,low,high)];
   }

   /** Returns the location of the first occurrence of the minimum
   **  value in a[].
   **  Pre-condition: 0 < a.length
   */
   public static int locOfMin(int[] a) { return locOfMin(a, 0, a.length); }


   /** Returns the location of the first occurrence of the minimum
   **  value in a[low..high-1].
   **  Pre-condition: 0 <= low < high <= a.length
   */
   public static int locOfMin(int[] a, int low, int high) {

      int locOfMinSoFar = low;
      int i = low + 1;
      // loop invariant: 0 <= low <= locOfMinSoFar < i <= high  && 
      //    a[locOfMinSoFar] is the minimum value in a[low..i-1]
      while (i != high)
      {
         if (a[i] < a[locOfMinSoFar]) {
            locOfMinSoFar = i;
         }
         i = i+1;
      }
      return locOfMinSoFar;
   }

   /** Returns the location of the first occurrence of the maximum
   **  value in a[low..high-1].
   **  Pre-condition: 0 <= low < high <= a.length
   */
   public static int locOfMax(int[] a, int low, int high) {

      int locOfMaxSoFar = low;
      int i = low + 1;
      // loop invariant: 0 <= low <= locOfMaxSoFar < i <= high  && 
      //    a[locOfMaxSoFar] is the maximum value in a[low..i-1]
      while (i != high)
      {
         if (a[i] > a[locOfMaxSoFar]) {
            locOfMaxSoFar = i;
         }
         i = i+1;
      }
      return locOfMaxSoFar;
   }



   // R e v e r s a l s
   // -----------------

   /** Reverses the order of elements in the specified array.
   */
   public static void reverse(int[] a) { reverse(a, 0, a.length); }


   /** Reverses the order of elements in the specified segment of the
   **  specified array (i.e., a[low..high-1]).
   **  Pre-condition: 0<=low<=high<=a.length
   */
   public static void reverse(int[] a, int low, int high) { 

      int p = low, q = high-1;  

      // loop invariant: Letting A refer to the initial contents of a,
      //    a[low..p-1] is the reverse of A[q+1..high-1] and
      //    a[q+1..high-1] is the reverse of A[low..p-1] and
      //    a[p..q] is the same as A[p..q]
      while (p < q) {
         swap(a, p, q);  // swap() is among "utility" methods near end of class
         p = p+1;
         q = q-1;
      }
   }


   /** Returns a new array the elements of which are those of the
   **  specified array, but in reverse order.
   */
   public static int[] reverseOf(int[] a) { return reverseOf(a, 0, a.length); }


   /** Returns a new array the elements of which are those of the
   **  specified segment of the specified array (i.e., a[low..high-1]),
   **  but in reverse order.
   **  Pre-condition: 0<=low<=high<=a.length
   */
   public static int[] reverseOf(int[] a, int low, int high) {

      int[] result = new int[high-low];
      int i = 0;

      // loop invariant: result[0..i-1] is reverse of a[high-i..high-1]
      while (i != result.length) {
         result[i] = a[high-1-i];
         i = i+1;
      }
      return result;
   }




   // E l e m e n t - w i s e   S u m   a n d   D i f f e r e n c e
   // -------------------------------------------------------------


   /** Returns a new array each of whose elements is the sum of the
   **  corresponding elements in the two given arrays (a[] and b[]).
   **  (In case a.length and b.length differ, we imagine that the shorter
   **  array is "expanded" to the other one's length and that the "new"
   **  elements have value zero.)
   **  To be precise, the array returned, call it c[], has these properties:
   **    c.length == N
   **    c[i] == a[i] + b[i] for all i in 0..M-1,
   **    c[i] == a[i] for all i in M..a.length-1, and
   **    c[i] == b[i] for all i in M..b.length-1,
   **  where M = min(a.length, b.length) and N = max(a.length, b.length) 
   */
   public static int[] sumElementWise(int[] a, int[] b)
   {
      final int M = Math.min(a.length, b.length);
      final int N = Math.max(a.length, b.length);

      int[] c = new int[N];

      // Sum elements at corresponding locations in a[] and b[] and place 
      // result into corresponding location of c[].
      for (int i=0; i != M; i++) { 
         c[i] = a[i] + b[i];
      }

      // Now copy into c[] the remaining elements of the longer array.
      // (At least one of the two loops that follow will iterate zero times.)
      for (int i=M; i != a.length; i++) { c[i] = a[i]; }
      for (int i=M; i != b.length; i++) { c[i] = b[i]; }
 
      return c;
   }

   /** Returns a new array each of whose elements is the difference of the
   **  corresponding elements in the two given arrays (a[] and b[]).
   **  (In case a.length and b.length differ, we imagine that the shorter
   **  array is "expanded" to the other one's length and that the "new"
   **  elements have value zero.)
   **  To be precise, the array returned, call it c[], has these properties:
   **    c.length == N
   **    c[i] == a[i] - b[i] for all i in 0..M-1,
   **    c[i] == a[i] for all i in M..a.length-1, and
   **    c[i] == b[i] for all i in M..b.length-1,
   **  where M = min(a.length, b.length) and N = max(a.length, b.length) 
   */
   public static int[] differenceElementWise(int[] a, int[] b)
   {
      final int M = Math.min(a.length, b.length);
      final int N = Math.max(a.length, b.length);

      int[] c = new int[N];

      // Take difference of elements at corresponding locations in a[] and b[]
      // and place result into corresponding location of c[].
      for (int i=0; i != M; i++) { 
         c[i] = a[i] - b[i];
      }

      // Now copy into c[] the remaining elements of the longer array.
      // (At least one of the two loops that follow will iterate zero times.)
      for (int i=M; i != a.length; i++) { c[i] = a[i]; }
      for (int i=M; i != b.length; i++) { c[i] = -b[i]; }
 
      return c;
   }

   // -----------------------------------------------------------------


   // L i n e a r   S e a r c h
   // -------------------------

   /** Returns the smallest value k such that either k == a.length or
   **  a[k] == x.  In other words, it returns the location of the first
   **  occurrence of x in a[] if there is one, and returns a.length
   **  otherwise.  (Note the similarity to the indexOf() method in
   **  java.lang.String, but also the difference.)
   */
   public static int linearSearch(int[] a, int x)
      { return linearSearch(a, 0, a.length, x); }


   /** Returns the smallest value k such that either k == low or both
   **  low <= k < high and a[k] == x.  In other words, it returns the
   **  location of the first occurrence of x in a[low..high-1] if there
   **  is one, and returns high otherwise.
   **  Pre-condition: 0 <= low <= high <= a.length
   */
   public static int linearSearch(int[] a, int low, int high, int x) {

      int i = low;
      // loop invariant: low <= i <= high  &&  
      //                 x does not occur in a[low..i-1]
      while (i != high  &&  a[i] != x) {
         i = i+1;
      }
      return i;
   }


   // B i n a r y   S e a r c h
   // -------------------------


   // Iterative version

   /** Returns the unique value k such that every element in a[0..k-1] is
   **  less than x and every element in a[k..a.length-1] is greater than or
   **  equal to x.
   **  Pre-condition: The values in a[] are in ascending order.
   */
   public static int binarySearch(int[] a, int x) {
      return binarySearch(a, 0, a.length, x);
   }


   /** Returns the unique value k, begLoc <= k <= endLoc, such that every
   **  element in a[begLoc..k-1] is less than x and every element in
   **  a[k..endLoc-1] is greater than or equal to x.
   **  Pre-condition: 0 <= begLoc <= endLoc <= a.length  &&
   **                 the values in a[begLoc..endLoc-1] are in ascending order.
   */
   public static int binarySearch(int[] a, int begLoc, int endLoc, int x) {

      int low = begLoc, high = endLoc;

      /* loop invariant: begLoc <= low <= high <= endLoc  &&
      **    all values in a[begLoc..low-1] are less than x  &&
      **    all values in a[high..endLoc-1] are greater than or equal to x
      */
      while (low != high) {
         int mid = low + ((high - low) / 2);
         if (a[mid] < x)
            { low = mid+1; }    // reduce search space to mid+1..high
         else  // a[mid] >= x
            { high = mid; }     // reduce search space to low..mid
      }
      return low;
   }

   // Recursive version

   /** Returns the unique value k such that every element in a[0..k-1] is
   **  less than x and every element in a[k..a.length-1] is greater than or
   **  equal to x.
   **  Pre-condition: The values in a[] are in ascending order.
   */
   public static int binarySearchRec(int[] a, int x) {
      return binarySearchRec(a, 0, a.length, x);
   }

   /** Returns the unique value k, begLoc <= k <= endLoc, such that every
   **  element in a[begLoc..k-1] is less than x and every element in
   **  a[k..endLoc-1] is greater than or equal to x.
   **  Pre-condition: 0 <= begLoc <= endLoc <= a.length  &&
   **                 the values in a[begLoc..endLoc-1] are in ascending order.
   */
   public static int binarySearchRec(int[] a, int begLoc, int endLoc, int x)
   {
      int result;

      if (begLoc == endLoc) {
         result = begLoc;
      }
      else {
         int mid = (begLoc + endLoc) / 2;
         if (a[mid] < x)
            { result = binarySearchRec(a, mid+1, endLoc, x); }
         else  // a[mid] >= x
            { result = binarySearchRec(a, begLoc, mid, x); }
      }
      return result;
   }

   // -----------------------------------------------------------------

   // N a i v e   S o r t i n g
   // -------------------------

   /** Rearranges the elements of a[], putting them into ascending order,
   **  in accord with the classic Selection Sort algorithm.
   **  This version puts values into their proper places from least to
   **  greatest.
   */
   public static void selectionSort(int[] a) {

      int i = 0;
      /* loop invariant: The bag of values in a[] is unchanged  &&
      **    0 <= i <= a.length  &&
      **    a[0..i-1] is in ascending order  &&
      **    a[0..i-1] contains the i smallest values in a[]  
      */
      while (i != a.length) {
         int k = locOfMin(a, i, a.length);
         swap(a, i, k); 
         i = i+1;
      }
   }

   /** Rearranges the elements of a[], putting them into ascending order,
   **  in accord with the classic Selection Sort algorithm.
   **  This version puts the values in their proper places from greatest
   **  to smallest.
   */
   public static void selectionSort2(int[] a) {

      int i = a.length;
      /* loop invariant: (where N = a.length)
      **    The bag of values in a[] is unchanged  &&
      **    0 <= i <= N  &&
      **    a[i..N-1] is in ascending order  &&
      **    a[i..N-1] contains the N-i largest values in a[]  
      */
      while (i != 0) {
         int k = locOfMax(a, 0, i);  // max value in a[0..i-1] is at location k
         swap(a, i-1, k);            // swap values in locations i-1 and k
         i = i-1;
      }
   }


   /* Recursive version of Selection Sort to sort an entire array of int's.
   */
   public static void selectionSortRecursive(int[] a) {
      selectionSortRecursive(a, 0, a.length);
   }

   /* Recursive version of Selection Sort to sort a specified segment of
   ** an array of int's, namely a[begLoc..endLoc-1].
   */
   public static void selectionSortRecursive(int[] a, int begLoc, int endLoc)
   {
      if (begLoc < endLoc-1) {  // segment has length two or more
         int k = locOfMax(a, begLoc, endLoc);
         swap(a, k, endLoc-1);
         selectionSortRecursive(a, begLoc, endLoc-1);
      }
      else {
         // segment has length zero or one, so do nothing
      }
   }

   /** Rearranges the elements of a[], putting them into ascending order,
   **  in accord with the classic Insertion Sort algorithm.
   **  Pre-condition: a.length > 0 (otherwise there is nothing to sort)
   */
   public static void insertionSort(int[] a) {

      int i = 1;
      /* loop invariant: The bag of values in a[] is unchanged  &&
      **    0 < i <= a.length  &&
      **    a[0..i-1] is in ascending order 
      */
      while (i != a.length) {
         int key = a[i];
         // Find lowest-numbered location in a[] containing a value greater than 'key'
         int k = binarySearch(a, 0, i, key+1);
         shiftToRight(a, k, i);  // Shift a[k..i-1] to right by one place.
         a[k] = key;
         i = i+1;
      }
   }


   /** Rearranges the elements of a[], putting them into ascending order,
   **  in accord with the classic Bubble Sort algorithm.
   **  Pre-condition: a.length > 0 (otherwise there is nothing to sort)
   */
   public static void bubbleSort(int[] a) {

      int i = 0;
      while (i != a.length-1) {
         int j = 0;
         while (j+1 != a.length) {
            if (a[j] > a[j+1]) { 
               swap(a, j, j+1);
            }
            j = j+1;
         }
         i = i+1;
      }
   }


   /** Rearranges the elements of a[], putting them into ascending order
   **  in accord with the classic Bubble Sort algorithm.
   **  This version is slightly faster than the one above because it
   **  takes advantage of two facts:
   **  (1) After k sweeps, the last k elements of the array contain the
   **      correct values; hence, the k-th sweep need not examine any of
   **      the last k-1 elements of the array.
   **  (2) If no swaps occur on some particular sweep, the array is already
   **      in order, so no subsequent sweeps are needed.
   **
   **  Pre-condition: a.length > 0 (otherwise there is nothing to sort)
   */
   public static void bubbleSortBetter(int[] a) {

      int i = 0;
      boolean swapOccurredDuringLastSweep = true;

      while (i != a.length-1  &&  swapOccurredDuringLastSweep) {
         int j = 0;
         swapOccurredDuringLastSweep = false;
         while (j+1 != a.length-i) {
            if (a[j] > a[j+1]) { 
               swap(a, j, j+1);
               swapOccurredDuringLastSweep = true;
            }
            j = j+1;
         }
         i = i+1;
      }
   }


   /** Sorts the elements of the given array segment (a[begLoc..endLoc-1]) into
   **  ascending order using Tony Hoare's QuickSort algorithm.
   */
   public static void quickSort(int[] a, int begLoc, int endLoc)
   {
      if (begLoc < endLoc-1) {   // segment has at least two elements     
         int k = partition(a, begLoc, endLoc);  // partition the segment into two subsegments

         // At this point, there is some value P such that all values in a[begLoc..k-1] 
         // are less than P, a[k] == P, and all elements in a[k+1..endLoc-1] are greater
         // than or equal to P.  Hence, to finish sorting a[begLoc..endLoc-1], it suffices 
         // to sort each of a[begLoc..k-1] and a[k+1..endLoc-1].

         quickSort(a, begLoc, k);        // sort a[begLoc..k-1] recursively
         quickSort(a, k+1, endLoc);      // sort a[k+1..endLoc-1] recursively
      }
      else {
         // segment has fewer than two elements, so it's already in ascending order
      }
   }

   /* Rearranges the elements of the given array segment (b[low..high-1]) so that there
   ** is a location k and an array element P such that all elements in b[low..k-1] are
   ** less than P, b[k] == P, and all elements in b[k+1..endLoc-1] are greater than or equal
   ** to P.  The value returned is k.
   ** pre-condition: 0 <= low < high <= b.length
   */
   public static int partition(int[] b, int low, int high)
   {
      final int P = b[high-1];  // Choose pivot to be the value in b[high-1].
      int i = low, j = high-1;
      // loop invariant: all elements in b[low..i-1] are < P  &&
      //                 all elements in b[j+1..high-1] are >= P  &&
      //                 b[i] < P implies i < j  &&
      //                 low <= i <= j < high
      while (i != j) {
         if (b[i] < P) { i = i+1; }
         else if (b[j] >= P) { j = j-1; }
         else {  // b[i] >= P and b[j] < P
            swap(b,i,j);
            i = i+1;
         }
      }
      // At this point, i==j and b[i] >= P
      swap(b,i,high-1);   // place P into location i (its proper place)
      return i;
   }


   // -------------------------------------------------------------------

   // U t i l i t i e s
   // -----------------


   /** Swaps the elements at the specified locations of the specified array.
   **  Pre-condition: 0<= j,k < a.length
   */
   public static void swap(int[] a, int j, int k)
      { int temp = a[j];  a[j] = a[k];  a[k] = temp; }


   // Shifting

   /** Shifts the elements in a[begLoc..endLoc-1] one place to the "right".
   **  Pre-condition: 0 <= begLoc <= endLoc < endLoc + 1 <= a.length
   */
   public static void shiftToRight(int[] a, int begLoc, int endLoc) {
      shiftToRight(a, begLoc, endLoc, 1);
   }


   /** Shifts the elements in a[begLoc..endLoc-1] dist places to the "right".
   **  Pre-condition: dist >= 0  &&  0 <= begLoc <= endLoc <= a.length - dist
   */
   public static void shiftToRight(int[] a, int begLoc, int endLoc, int dist) {

      int j = endLoc - 1;
      while (j >= begLoc) {
         a[j + dist] = a[j];
         j = j-1;
      }
   }


   /** Shifts the elements in a[begLoc..endLoc-1] one place to the "left".
   **  Pre-condition: 0 < begLoc <= endLoc <= a.length
   */
   public static void shiftToLeft(int[] a, int begLoc, int endLoc) {
      shiftToLeft(a, begLoc, endLoc, 1);
   }

   /** Shifts the elements in a[begLoc..endLoc-1] dist places to the "left".
   **  Pre-condition: 0 <= dist <= begLoc <= endLoc <= a.length
   */
   public static void shiftToLeft(int[] a, int begLoc, int endLoc, int dist) {

      int j = begLoc;
      while (j < endLoc) {
         a[j - dist] = a[j];
         j = j+1;
      }
   }

}  // end of class

