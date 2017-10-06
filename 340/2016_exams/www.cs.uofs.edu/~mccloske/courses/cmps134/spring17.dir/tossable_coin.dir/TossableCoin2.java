/* TossableCoin2.java
** An instance of this class represents a (fair) tossable coin. 
** It enhances the class with the similar name (but ending with '1' rather
** than '2') in that it includes observer methods by which a client can 
** ascertain how many times a coin has been tossed, and how many of those
** tosses resulted in each of Heads and Tails.
*/
public class TossableCoin2 {

   // instance variable
   // -----------------
   private boolean showingHeads;    // true means Heads is showing
   private int headCntr, tailCntr;  // # of tosses that yielded each result

   // constructor
   // -----------

   /* Initializes this new coin to be showing heads.
   */
   public TossableCoin2() { 
      showingHeads = true;  headCntr = 0;  tailCntr = 0;
   }


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

   /* Returns a String identifying which face of this coin is showing.
   */
   public String toString() { 
      if (isHeads()) { return "Heads"; }
      else { return "Tails"; }
   }


   // mutators
   // --------

   /* Tosses this coin, with each face equally likely to end up showing.
   */
   public void toss() {
      showingHeads = Math.random() < 0.5;
      if (showingHeads) { headCntr++; }
      else { tailCntr++; }
   }

}

