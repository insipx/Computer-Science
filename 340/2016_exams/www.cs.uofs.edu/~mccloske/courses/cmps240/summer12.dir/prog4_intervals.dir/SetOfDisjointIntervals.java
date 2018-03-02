import java.util.Iterator;

/** An instance of an implementing class represents a set ("sequence" might
**  be more accurate) of disjoint intervals over the real numbers
**  (represented by instances of the class Interval).
**  The intervals in the set are ranked according to their lower bounds, 
**  from smallest to largest.  (For example, [3,6] would have a lower rank
**  than [25,57], because 3 < 25.
**  A slightly different way to look at it is that an instance of an
**  implementing class represents a set of real numbers that can be
**  described as the union of finitely many intervals.
**
**  In mutator method postconditions, 'oldThis' refers to the state of 'this'
**  just before execution of the method, thereby making it possible to specify
**  the required relationship between the state of 'this' before a method's
**  execution and its state afterwards.
*/

public interface SetOfDisjointIntervals extends Cloneable {

   /****  o b s e r v e r s  ****/

   /** Returns the number of intervals in this set.
   */
   int size();

   /** Returns the k-th ranked interval in this set.
   */
   Interval get(int k);


   /** Returns the sum of the lengths of the intervals in this set.
   */
   double sumOfLengths();


   /** Reports whether or not there exists an interval u in this set that
   **  contains the given real number (x) (i.e., is such that u.contains(x)
   **  is true).
   */
   boolean contains(double x);


   /** Reports whether or not there exists an interval u in this set that
   **  contains the given interval (v) (i.e., is such that u.contains(v) is
   **  true).
   */
   boolean contains(Interval v);


   /** Reports whether or not, for every interval v in s (the given set of
   **  disjoint intervals), there exists an interval u in this set that
   **  contains v.
   */
   boolean contains(SetOfDisjointIntervals s);


   /** Among the intervals in this set, returns the one having the 
   **  largest lower bound that is not greater than x.
   **  If there is no such interval, null is returned.
   **  Note that if this.contains(x) is true, the interval u returned
   **  will satisfy u.contains(x).
   */
   Interval nearestBelow(double x);
   

   /** Among the intervals in this set, returns the one having the smallest
   **  upper bound that is not less than x.  If there is no such interval,
   **  null is returned.
   **  Note that if this.contains(x) is true, the interval u returned
   **  will satisfy u.contains(x).
   */
   Interval nearestAbove(double x);
   

   /****  i t e r a t o r  ****/

   /** Returns an iterator over the intervals in this set, which returns
   **  them in increasing order of rank.
   */
   Iterator<Interval> iterator();


   /****  m u t a t o r s  ****/

   /** Adds the given interval (v) to this set.
   **  post: For all real numbers x, 
   **        this.contains(x) = oldThis.contains(x) || v.contains(x)
   **
   **  Note: If v intersects with any interval u in this set, the updated
   **  version of this set will include an interval that contains both u and v.
   */
   void add(Interval v);


   /** Subtracts the given interval (v) from this set.
   **  post: For all real numbers x, 
   **        this.contains(x) = oldThis.contains(x) && !v.contains(x)
   **
   **  Note: To satisfy the postcondition (while ensuring that the set of
   **  intervals in this set is disjoint) requires that any interval u in
   **  this set that intersects v is either removed (in the case that v
   **  contains u), "shortened" (in the case that the intersection of u and v
   **  is a proper prefix or proper suffix of u), or split into two (in the
   **  case that u contains v but v is neither a prefix nor suffix of u).
   */
   void subtract(Interval v);



   /****  g e n e r a t o r s  ****/

   /** Returns a clone of this set of disjoint intervals.
   */
   Object clone();


   /** Returns a new set w of disjoint intervals having the property that, for
   **  all real numbers x, w.contains(x) = this.contains(x) || s.contains(x).
   */
   SetOfDisjointIntervals union(SetOfDisjointIntervals s);


   /** Returns a new set w of disjoint intervals having the property that, for
   **  all real numbers x, w.contains(x) = this.contains(x) && s.contains(x).
   */
   SetOfDisjointIntervals intersection(SetOfDisjointIntervals s);


   /** Returns a new set w of disjoint intervals having the property that, for
   **  all real numbers x, w.contains(x) = this.contains(x) && !s.contains(x).
   */
   SetOfDisjointIntervals difference(SetOfDisjointIntervals s);


   /****  f a c t o r y  ****/

   /** Returns a new empty set of disjoint intervals.
   */
   SetOfDisjointIntervals factory();

}

