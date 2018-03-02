import java.util.Iterator;
import java.util.ArrayList;

/** A concrete class that extends SetOfDisjointIntervalsAbstract.
**  Here, the disjoint intervals (which are instances of Interval)
**  are kept in an ArrayList, such that each one's upper bound is
**  less than that of the interval that immediately follows it.
*/

public class SetOfDisjointIntervalsViaArrayList 
   extends SetOfDisjointIntervalsAbstract 
{

   /****  f i e l d s  ****/

   private ArrayList<Interval> intervals;


   /****  c o n s t r u c t o r   ****/

   public SetOfDisjointIntervalsViaArrayList()
   {
      intervals = new ArrayList<Interval>();
   }


   /****  o b s e r v e r s  ****/

   public int size()
   { 
      return 0;  // STUB!!
   }

   public Interval get(int k) 
   { 
      return null;  // STUB!!
   }

   public Iterator<Interval> iterator() { return intervals.iterator(); }


   public Interval nearestBelow(double x)
   {
      return null;  // STUB!!
   }




   public Interval nearestAbove(double x)
   {
      return null;  // STUB!!
   }


   /****  m u t a t o r s  ****/


   public void add(Interval v) 
   { 
      // STUB!!
      //
      // Pseudocode:
      // 
      // if (this set is empty)
      //    insert v into this set (as its lone element)
      // else if (this set contains v)
      //    /* Note: it is possible to subsume this case into the else part. */
      //    do nothing
      // else
      //    /* compute lower bound (low) of new interval to insert and 
      //    ** identify first interval (x') to be removed.
      //    */
      //    x = nearestBelow(lower bound of v)
      //    if (x does not exist)
      //       x' = first interval in this set
      //       low = lower bound of v
      //    else if (x overlaps v)
      //       x' = x
      //       low = lower bound of x
      //    else // x exists but it precedes v
      //       x' = interval that immediately follows x
      //       low = lower bound of v
      //    endif
      //
      //    /* compute upper bound (high) of new interval to insert and 
      //    ** identify last interval (y') to be removed.
      //    */
      //    ...
      //    code omitted, as it is analogous to the block above
      //    ...
      //
      //    remove the intervals from x' through y' (there might be none!) 
      //    insert interval [low,high] in place of the removed intervals
      // endif
   }


   public void subtract(Interval v)
   {
      // STUB!!
      //
      // Pseudocode: (very high-level, so its efficiency is non-optimal)
      // 
      // for each interval u in this set
      //    if (u overlaps v)
      //       replace u in this set by u.subtract(v) (which will be
      //       a set of between zero and two intervals)
      //    endif
      // rof
   }


   /****  g e n e r a t o r s  ****/

   public Object clone()
   {
      SetOfDisjointIntervalsViaArrayList result;
      result = new SetOfDisjointIntervalsViaArrayList();
      result.intervals = (ArrayList<Interval>)(this.intervals.clone()); 
      return result;
   }



   /****  f a c t o r y  ****/

   public SetOfDisjointIntervals factory()
   {
      return new SetOfDisjointIntervalsViaArrayList();
   }

}

