/** An instance of this class represents a six-sided die that can be rolled,
**  yielding as an outcome an integer in the range 1..6.
**  A die "remembers", for each outcome, how many times it was rolled.
**  It does this by maintaining a six-element array, which is a vast
**  improvement over using six separate variables.
*/

public class SixSidedDie2 {

   // fields (instance variables)

   private int dotsShowing; // # of dots currently showing (in range 1..6)

   // Remembers # of tosses resulting in specified outcome.  Specifically,
   // rollCntr[i] stores the # of times i+1 has been rolled.
   private int[] rollCntr;


   // constructor

   // Initializes this die to be showing one dot and to not have been rolled.
   public SixSidedDie2() 
   { 
      dotsShowing = 1;
      rollCntr = new int[6];  // creates an array of int's of length six
   }


   // observers

   // Returns # dots currently showing
   public int dotsShowing() { return dotsShowing; }


   /** Returns # times k was the outcome of a roll.
   *** precondition: 1 <= k <= 6
   **/
   public int rollCounter(int k) { return rollCntr[k-1]; }


   // Returns # times this die has been rolled.
   public int rollCounter()
   { 
      // Compute the sum of the elements in rollCntr[] and return it.
      int sum = 0;
      for (int i=0; i != 6; i++) 
         { sum = sum + rollCntr[i]; }
      return sum;
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

      // Increment the appropriate array element.
      rollCntr[dotsShowing-1]++;
   }

}
