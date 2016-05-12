/* An instance of this Java class represents a coin that can be tossed, such
** as is done prior to a football game to determine which team takes possession
** of the ball first.  At time of construction, the client can choose the
** probabilities with which HEADS and TAILS should occur when the coin is 
** tossed.
*/
public class TossableCoin {

   // Class constants (representing the two faces of a coin)
   // ------------------------------------------------------
   public static final char HEADS = 'H';   // internal char encoding of HEADS
   public static final char TAILS = 'T';   // internal char encoding of TAILS


   // Instance Variables
   // ------------------
   private char faceShowing;  // which face is showing (HEADS or TAILS)
   private double headsProb;  // probability that a toss results in heads


   // Constructors
   // ------------

   /* Initializes the coin so that approximately the specified percentage of
   ** tosses will result in "heads".
   ** precondition: 0.0 <= probabilityHeads <= 1.0
   */
   public TossableCoin(double probOfHeads)
   { 
      headsProb = probOfHeads;
   }


   /* Initializes the coin to be fair (i.e., so that each of "heads" and "tails"
   ** should be the result of about half of the tosses).
   */
   public TossableCoin() { 
      this(0.5);      // Call the other constructor and specify a probability
   }                  // of one-half for HEADS.


   // Observers
   // ---------

   /* Returns either HEADS or TAILS according to which face of the coin is
   ** "showing".  (Note that this method has the same name as one of the 
   ** instance variables.)
   */
   public char faceShowing() { return faceShowing; }


   /* Returns the probability that a given toss of the coin will result in HEADS.
   */
   public double probabilityOfHeads() { return headsProb; }

   /* Returns a String identifying which face of the coin is showing.
   */
   public String toString() { 
      if (faceShowing() == HEADS) { return "HEADS"; }
      else { return "TAILS"; }
   }


   // Mutator
   // -------

   /* Tosses the coin, where the likelihood of the result being HEADS 
   ** is equal to probabilityOfHeads().
   */
   public void toss() {
      if (Math.random() < probabilityOfHeads())
         { faceShowing = HEADS; }
      else 
         { faceShowing = TAILS; }
   }

}








