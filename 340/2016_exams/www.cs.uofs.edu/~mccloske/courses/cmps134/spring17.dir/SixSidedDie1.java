/** An instance of this class represents a six-sided die that can be rolled,
**  yielding as an outcome an integer in the range 1..6.
**  A die "remembers", for each outcome, how many times it was rolled.
**  It does this by maintaining six distinct instance variables, which
**  makes the rollCntr() and roll() methods very cumbersome.
** 
**  Author: R. McCloskey
*/

public class SixSidedDie1 {

   // instance variables  (fields)

   private int dotsShowing; // # of dots currently showing (in range 1..6)

   // Remembers # of tosses resulting in specified outcome.
   private int cntr1, cntr2, cntr3, cntr4, cntr5, cntr6;


   // constructor

   // Initializes this die to be showing one dot and to not have been rolled.
   public SixSidedDie1() 
   { 
      dotsShowing = 1;
      cntr1 = 0; cntr2 = 0; cntr3 = 0; cntr4 = 0; cntr5 = 0; cntr6 = 0;
   }


   // observers

   // Returns # dots currently showing
   public int dotsShowing() { return dotsShowing; }


   /** Returns # times k was the outcome of a roll.
   *** precondition: 1 <= k <= 6
   **/
   public int rollCounter(int k)
   { 
      if (k == 1) { return cntr1; }
      else if (k == 2) { return cntr2; }
      else if (k == 3) { return cntr3; }
      else if (k == 4) { return cntr4; }
      else if (k == 5) { return cntr5; }
      else if (k == 6) { return cntr6; }
      else { throw new IllegalArgumentException("k must be in range 1..6"); }  
   }


   // Returns # times this die has been rolled.
   public int rollCounter() { 
      return cntr1 + cntr2 + cntr3 + cntr4 + cntr5 + cntr6;
   }


   // mutators

   // Rolls the die.
   public void roll()
   {
      // To compute the outcome of a roll, generate a pseudorandom real 
      // number in the interval [0,1), multiply it by 6 to obtain a
      // pseudorandom real number in the interval [0,6), then take its floor
      // and add 1.
      dotsShowing = (int)(6 * Math.random()) + 1;

      // Increment the appropriate counter variable
      if (dotsShowing == 1) { cntr1++; }
      else if (dotsShowing == 2) { cntr2++; }
      else if (dotsShowing == 3) { cntr3++; }
      else if (dotsShowing == 4) { cntr4++; }
      else if (dotsShowing == 5) { cntr5++; }
      else { cntr6++; }
   }

}
