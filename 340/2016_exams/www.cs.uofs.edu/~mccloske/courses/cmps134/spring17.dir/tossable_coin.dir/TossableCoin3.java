/* TossableCoin3.java
** An instance of this class represents a (possibly biased) tossable coin. 
** It enhances the class with the similar name (but ending with '2' rather
** than '3') in that it supports the construction of coins that are biased.
** Specifically, the client, when creating a coin, can specify via a
** parameter passed to the constructor, the probability that any given toss
** should result in Heads.
*/
public class TossableCoin3 {

   // instance variable
   // -----------------
   private boolean showingHeads;    // true means Heads is showing
   private int headCntr, tailCntr;  // # of tosses that yielded each result
   private double headsProb;        // probability that a given toss results
                                    // in Heads
   // constructors
   // ------------

   /* Initializes this new coin to be showing heads and so that, on each
   ** toss, the probability of a Heads being the result is the specified
   ** value (which is assumed to be in the interval [0,1]).
   */
   public TossableCoin3(double hProb) { 
      showingHeads = true;  headCntr = 0;  tailCntr = 0;
      headsProb = hProb;
   }

   /* Initializes this new coin to be showing heads and to be fair.
   */
   public TossableCoin3() 
      { this(0.5); }  // Calls the other constructor and passes 0.5 to it.
 

   // observers
   // ---------

   /* Returns true if this coin is showing heads, false otherwise.
   */
   public boolean isHeads() { return showingHeads; }

   /* Returns true if this coin is showing tails, false otherwise.
   */
   public boolean isTails() { return !isHeads(); }

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
      showingHeads = Math.random() < headsProb;
      if (showingHeads) { headCntr++; }
      else { tailCntr++; }
   }

}

