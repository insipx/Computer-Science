/* An instance of this class represents a zero-based wraparound counter,
** which is a counter whose value remains in the range 0..N, for some N >= 0.
** When a wraparound counter is incremented, it either increases by one or,
** in the case that it is already at its maximum value, wraps around to zero.
** Decrementing is inverse to incrementing.
*/

public class WrapAroundCounter {

   // instance constant
   // -----------------

   private final int MAX_VAL;    // stores the counter's upper bound value

   
   // instance variables
   // ------------------

   private int val;     // stores current value of the counter


   // constructor
   //--------------

   /* Initializes the instance variable and constant of a newly-created
   ** WrapAroundCounter object.  The parameter specifies the upper bound
   ** of the counter's range of values.
   */
   public WrapAroundCounter(int maxVal) 
   {
      MAX_VAL = maxVal; val = 0;
   }


   // observers
   // ---------
 
   /* Returns the current value of  the counter.
   */
   public int cntrVal() { return val; }
  
   /* Returns the counter's upper bound value.
   */
   public int maxVal() { return MAX_VAL; }
 
    
   // mutators
   // --------
   
   // Increments the counter's value, wrapping around to zero if appropriate.
   public void increment()
   {
      if (val != MAX_VAL) { val = val + 1; }
      else { val = 0; }
   }
   
   // Decrements the counter's value, wrapping around to its maximum allowed
   // value if appropriate.
   public void decrement()
   {
      if (val != 0) { val = val - 1; }
      else { val = MAX_VAL; }
   }
   
   /* Resets the counter's value to zero.
   */
   public void reset() { val = 0; }

}
