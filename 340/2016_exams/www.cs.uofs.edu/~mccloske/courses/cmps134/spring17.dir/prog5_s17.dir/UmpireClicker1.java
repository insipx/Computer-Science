/* An instance of this class (UmpireClicker1) represents a hand-held 
** mechanical device commonly used by baseball/softball umpires to keep
** track of the inning, outs, balls, and strikes during the course of a
** game.
**
** Author: Robert McCloskey (based upon a class by Paul Jackowitz)
** Date: April 2015 (updated April 2016 and April 2017)
*/

public class UmpireClicker1 {

   // symbolic constants
   // ------------------
   private static final int BALL_MAX = 4;
   private static final int STRIKE_MAX = 3;
   private static final int OUT_MAX = 3;
   private static final int INNING_MAX = 9;


   // instance variables (fields)
   // ---------------------------
   private int ballCntr;      // for keeping track of the counts of
   private int strikeCntr;    // balls, strikes, outs, and the inning
   private int outCntr;
   private int inningCntr;     
   

   // constructor
   // -----------

   /* Initializes the instance variables of a newly-created UmpireClicker1
   ** object.  (Because all of them are initialized to zero, which Java does
   ** automatically, this constructor can be omitted entirely, or, 
   ** alternatively, its body can be made to be empty.)
   */ 
   public UmpireClicker1()
   {
      ballCntr = 0;  strikeCntr = 0;  outCntr = 0;  inningCntr = 0;
   }

   
   // observers
   // ---------

   // Because each method's name makes its purpose obvious, we omit comments.

   public int getBallCount() { return ballCntr; }
   
   public int getStrikeCount() { return strikeCntr; }
   
   public int getOutCount() { return outCntr; }
   
   public int getInningCount() { return inningCntr; }
   
   public String toString()
   {
      return "Inning " + getInningCount() + ", Out " + getOutCount() + 
             ", Ball " + getBallCount() + ", Strike " + getStrikeCount();
   }

   
   // mutators
   // --------

   // Because each method's name makes its purpose obvious, we omit comments.

   public void advanceBallCount() {
      if (ballCntr == BALL_MAX) 
         { ballCntr = 0; }
      else 
         { ballCntr = ballCntr + 1; }

      // alternative method body using the % (remainder) operator:
      // ballCntr = (ballCntr + 1) % (BALL_MAX + 1);
   }
   
   public void advanceStrikeCount() {
      if (strikeCntr == STRIKE_MAX) 
         { strikeCntr = 0; }
      else 
         { strikeCntr = strikeCntr + 1; }
   }
   
   public void advanceOutCount() { 
      if (outCntr == OUT_MAX) 
         { outCntr = 0; }
      else 
         { outCntr = outCntr + 1; }
   }
   
   public void advanceInningCount() {
      if (inningCntr == INNING_MAX) 
         { inningCntr = 0; }
      else 
         { inningCntr++; }
   }

}
