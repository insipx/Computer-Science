import java.util.Iterator;

/** An abstract class that implements SetOfDisjointIntervals, it provides
**  concrete bodies of methods that do not require direct manipulation of
**  fields used in the underlying representation of instances of concrete
**  subclasses.
*/

public abstract class SetOfDisjointIntervalsAbstract 
   implements SetOfDisjointIntervals 
{

   /****  o b s e r v e r s  ****/

   public abstract int size();

   public abstract Interval get(int k);

   public double sumOfLengths()
   {
      return 0;  // STUB!!  (Hint: Use iterator().)
   }

   public abstract Iterator<Interval> iterator();

   public boolean contains(double x)
   {
      return false;  // STUB!  (Hint: Use nearestBelow() (or nearestAbove()).)
   }

   public boolean contains(Interval v)
   {
      return false;  // STUB!!  (Hint: Use nearestBelow() (or nearestAbove()).)
   }


   public boolean contains(SetOfDisjointIntervals s)
   {
      return false;  // STUB!!  (Hint: use iterator() and 
                     //          the above version of contains().)
   }

   public abstract Interval nearestBelow(double x);
   
   public abstract Interval nearestAbove(double x);

   public String toString()
   {
      StringBuilder result = new StringBuilder();
      result.append("<");
      Iterator<Interval> iter = iterator();
      while (iter.hasNext())
      {
         result.append(" " + iter.next().toString());
      }
      result.append(" >");
      return result.toString();
   }


   /****  m u t a t o r s  ****/

   public abstract void add(Interval v);

   public abstract void subtract(Interval v);


   /****  g e n e r a t o r s  ****/

   public abstract Object clone();


   /* This could be improved in terms of efficiency.
   */
   public SetOfDisjointIntervals union(SetOfDisjointIntervals s)
   {
      return null;  // STUB!!  (Hint: Use either factory() or clone, as
                    //          well as iterator() and add().)
   }


   public SetOfDisjointIntervals intersection(SetOfDisjointIntervals s)
   {
      return null;  // STUB!!  (Hint: use factory(), iterator(), add(), 
                    //          and Interval.intersection().)
   }


   public SetOfDisjointIntervals difference(SetOfDisjointIntervals s)
   {
      return null;  // STUB!!  (Hint: use clone(), iterator(), and subtract().)
   }


   /****  f a c t o r y  ****/

   public abstract SetOfDisjointIntervals factory();


   /****  p r i v a t e  u t i l i t y  ****/

   /* Returns the next object offered by the specified Iterator,
   ** or null if there is no next object.
   */
   private Interval getNext(Iterator<Interval> iter)
   {
      if (iter.hasNext()) { return iter.next(); }
      else { return null; }

      // alternative code:
      // return iter.hasNext() ? iter.next() : null;
   }


}

