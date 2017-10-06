/** An instance of this class represents an interval on the set of real
**  numbers.  For example, [2.4, 23.5] is the closed interval with
**  lower and upper bounds 2.4 and 23.5, respectively, which is the set
**  of real numbers x satisfying the condition 2.4 <= x <= 23.5.
**  The corresponding open interval (2.4, 23.5) (notice the use of
**  parentheses rather than square brackets) is the same set of numbers,
**  except not including the two bounds.  An interval can be closed at
**  one end and open on the other, as in [2.4, 23.5) or (2.4, 23.5].
**  
**  In order to keep things simple, in most circumstances this class
**  treats an interval as being closed at each end.  The main exception
**  occurs when one interval is subtracted from another.  The interval
**  being subtracted is treated as being open at each end, except at
**  any end that is the same as the corresponding end of the interval
**  from which it is being subtracted.  (This avoids a situation in which
**  an interval of length zero is produced by the operation.)
**
**  @author R. McCloskey and xxxx
**  @version July 2012
*/

public class Interval {

   private double lowerBound, upperBound;  // lower and upper bounds of this
                                           // interval

   /** Initializes this interval to have the specified bounds.
   **  If lower > upper, an IllegalArgmentException is thrown.
   */
   public Interval(double lower, double upper)
   {
      if (lower > upper) {
         throw new IllegalArgumentException("lower > upper");
      }
      lowerBound = lower;  upperBound = upper;
   }


   /** Returns the lower bound of this interval.
   */
   public double lowerBound() { return 0; }   // STUB!


   /** Returns the upper bound of this interval.
   */
   public double upperBound() { return 0; }   // STUB!


   /** Returns the length of this interval, which is simply the distance
   **  between its lower and upper bounds.
   */
   public double length() { return upperBound() - lowerBound(); }


   /** Reports whether or not the specified value is in this interval
   */
   public boolean isMemberOf(double x)
   {
      return false;   // STUB!
   }


   /** Reports whether or not the specified object (v) is equal to this
   **  interval, which requires that v be an instance of Interval and
   **  that v's lower and upper bounds match those of this interval.
   */
   @Override
   public boolean equals(Object v)
   {
      boolean result;
      if (v instanceof Interval) {
         Interval vv = (Interval)v;
         result = lowerBound() == vv.lowerBound() &&
                  upperBound() == vv.upperBound();
      }
      else {
         result = false;
      }
      return result;
   }


   /** Reports whether or not the specified interval (v) is wholly contained
   **  within (i.e., is a subset of) this interval.
   */
   public boolean contains(Interval v)
   {
      return false;   // STUB!
   }


   /** Reports whether or not the specified interval (v) has a nonempty
   **  intersection with this interval.
   */
   public boolean overlaps(Interval v)
   {
      return false;   // STUB!
   }


   public String toString()
   {
      return "[" + lowerBound() + "," + upperBound() + "]";
   }


   /** Returns the interval obtained by taking the union of this interval
   **  and the specified interval (v).
   **  pre: overlaps(v)
   */
   public Interval union(Interval v)
   {
      return new Interval(0.0, 0.0);   // STUB!
   }


   /** Same as method above, except that an IllegalArgumentException is
   **  thrown if the above method's precondition is not met.
   */
   public Interval unionSafe(Interval v)
   {
      if (!overlaps(v)) {
         throw new IllegalArgumentException("Intervals do not overlap");
      }
      return union(v);
   }


   /** Returns the interval obtained by taking the intersection of this
   **  interval and the specified interval (v).
   **  pre: overlaps(v)
   */
   public Interval intersection(Interval v)
   {
      return new Interval(0.0, 0.0);    // STUB!
   }


   /** Same as method above, except that an IllegalArgumentException is
   **  thrown if the above method's precondition is not met.
   */
   public Interval intersectionSafe(Interval v)
   {
      return new Interval(0.0, 0.0);   // STUB!
   }



   /** Returns an array containing the interval(s) that result from
   **  subtracting the specified interval (v) from this one.  (Each end 
   **  of the specified interval is interpreted as being open, unless the
   **  bound is the same as the corresponding bound of this interval.
   **  This is to avoid a situation in which an interval of length zero
   **  is produced as a result of the operation.)
   **  pre: overlaps(v) && v.length() > 0
   **
   **  Examples:
   **  Subtracting (2,7) from [0,5] yields [0,2].
   **  Subtracting (0,3) from [0,5] yields [3,5].
   **  Subtracting (2,7) from [4,6] yields no interval.
   **  Subtracting (2,7) from [0,10] yields the two intervals [0,2] and [7,10].
   **
   **  Note that in the first two examples, an array of length one is
   **  returned.  In the third example, an array of length zero is returned.
   **  In the fourth, an array of length two is returned.
   */
   public Interval[] subtract(Interval v) 
   {
      Interval[] result = null;
      //
      //  STUB!
      //
      return result;
   }


   /** Same as method above, except that an IllegalArgumentException is
   **  thrown if the above method's precondition is not met.
   */
   public Interval[] subtractSafe(Interval v)
   {
      return new Interval[0];   // STUB!
   }

}
