/* TossableCoin4.java
** An instance of this class represents a (possibly biased) tossable coin. 
** It enhances the class with the similar name (but ending with '3' rather
** than '4') in that coins "remember" the complete history of their toss
** results.
*/
public class TossableCoin4 {

   // symbolic constant
   private static char HEADS = 'H';
   private static char TAILS = 'T';

   // instance variable
   // -----------------
   private int headCntr, tailCntr;  // # of tosses that yielded each result
   private double headsProb;        // probability that a given toss results
                                    // in Heads
   private String tossHistory;      // String of H's and T's recording the
                                    // result of every toss
   // constructors
   // ------------

   /* Initializes this new coin to be showing heads and so that, on each
   ** toss, the probability of a Heads being the result is the specified
   ** value (which is assumed to be in the interval [0,1]).
   */
   public TossableCoin4(double hProb) { 
      headCntr = 0;  tailCntr = 0;
      headsProb = hProb;  
      tossHistory = "" + HEADS;  //records that the coin shows Heads initially
   }

   /* Initializes this new coin to be showing heads and to be fair.
   */
   public TossableCoin4() 
      { this(0.5); }  // Calls the other constructor and passes 1/2 to it.
 

   // observers
   // ---------

   /* Returns true if this coin is now showing heads, false otherwise.
   */
   public boolean isHeads() { 
      int lastPos = tossHistory.length() - 1;
      return isHeads(lastPos);
   }

   /* Returns true if this coin showed heads (immediately) after the 
   ** specified number (k) of tosses.
   */
   public boolean isHeads(int k) { return tossHistory.charAt(k) == HEADS; }


   /* Returns true if this coin is showing tails, false otherwise.
   */
   public boolean isTails() { return !isHeads(); }

   /* Returns true if this coin showed tails (immediately) after the 
   ** specified number (k) of tosses.
   */
   public boolean isTails(int k) { return !isHeads(k); }


   /* Returns the number of times that Heads has been tossed on this coin.
   */
   public int headsCount() { return headCntr; }

   /* Returns the number of times that Tails has been tossed on this coin.
   */
   public int tailsCount() { return tailCntr; }

   /* Returns the number of times that this coin has been tossed.
   */
   public int tossCount() { return headCntr + tailCntr; }

   /* Returns the probability that a toss will result in Heads.
   */
   public double probabilityOfHeads() { return headsProb; }

   /* Returns a String identifying which face of this coin is showing.
   */
   public String toString() { 
      if (isHeads()) { return "Heads"; }
      else { return "Tails"; }
   }


   // mutators
   // --------

   /* Tosses this coin, with the probability that Heads will be the result
   ** being probabilityOfHeads().
   */
   public void toss() {
      if (Math.random() < headsProb) {
         headCntr++;
         tossHistory = tossHistory + HEADS;
      }
      else { 
         tailCntr++;
         tossHistory = tossHistory + TAILS;
      }
   }

}

