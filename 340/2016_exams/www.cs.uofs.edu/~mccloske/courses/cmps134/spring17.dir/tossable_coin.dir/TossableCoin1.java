/* TossableCoin1.java
** An instance of this class represents a (fair) tossable coin. 
** Functionality is minimal; 
*/
public class TossableCoin1 {

   // instance variable
   // -----------------
   private boolean showingHeads;

   // constructor
   // -----------

   /* Initializes this new coin to be showing heads.
   */
   public TossableCoin1() { showingHeads = true; }


   // observers
   // ---------

   /* Returns true if this coin is showing heads, false otherwise.
   */
   public boolean isHeads() { return showingHeads; }

   /* Returns true if this coin is showing tails, false otherwise.
   */
   public boolean isTails() { return !isHeads(); }

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
      double r = Math.random();
      showingHeads = r < 0.5;
   }

}

