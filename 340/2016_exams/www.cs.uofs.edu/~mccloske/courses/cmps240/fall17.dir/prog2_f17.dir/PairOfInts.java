/* Java class each of whose instances is a pair of values of type int.
** Instances are immutable.
**
** Author: R. McCloskey
** Date: Aug. 2017
*/

public class PairOfInts
{
   // instance variables
   // ------------------
   private int first, second;


   // constructor
   // -----------

   public PairOfInts(int k, int m)
      { first = k;  second = m; }


   // observers
   // ---------

   public int firstOf() { return first; }
   public int secondOf() { return second; }

   public String toString() { 
      return "(" + firstOf() + "," + secondOf() + ")";
   }

   public boolean equals(Object obj) {
      boolean result = false;
      if (obj instanceof PairOfInts) {
         PairOfInts p = (PairOfInts)obj;
         result = firstOf() == p.firstOf() &&
                  secondOf() == p.secondOf();
      }
      return result;
   }

   // mutators  (none!)
   // --------

}

