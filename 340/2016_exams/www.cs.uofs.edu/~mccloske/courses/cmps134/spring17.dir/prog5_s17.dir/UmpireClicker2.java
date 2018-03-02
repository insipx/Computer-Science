/* An instance of this class (UmpireClicker2) represents a hand-held 
** mechanical device commonly used by baseball/softball umpires to keep
** track of the inning, outs, balls, and strikes during the course of a
** game.  This version improves upon its predecessor (UmpireClicker1)
** by introducing the advanceCount() method that embodies the common
** logic of each of the four public mutators, thereby allowing each of
** those methods to be simplified.
**
** Author: Robert McCloskey (based upon a class by Paul Jackowitz)
** Date: April 2015 (updated April 2016 and 2017)
*/

public class UmpireClicker2 {

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

   /* Initializes the instance variables of a newly-created UmpireClicker2
   ** object.  (Because all of them are initialized to zero, which Java does
   ** automatically, this constructor can be omitted entirely, or, 
   ** alternatively, its body can be made to be empty.)
   */ 
   public UmpireClicker2()
      { ballCntr = 0;  strikeCntr = 0;  outCntr = 0;  inningCntr = 0; }
   
   
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

   public void advanceBallCount()
   {
      ballCntr = advanceCount(ballCntr, BALL_MAX);
   }
   
   public void advanceStrikeCount()
   {
      strikeCntr = advanceCount(strikeCntr, STRIKE_MAX);
   }
   
   public void advanceOutCount()
   { 
      outCntr = advanceCount(outCntr, OUT_MAX);
   }
   
   public void advanceInningCount()
   {
      inningCntr = advanceCount(inningCntr, INNING_MAX);
   }


   // private method
   // --------------

   /* Given a counter value together with its allowed maximum value,
   ** returns the result of advancing that counter, which means either
   ** setting it to zero or adding one to it, the former if the counter's
   ** value is equal to the maximum allowed and the latter otherwise.
   */
   private int advanceCount(int cntr, int maxCount)
   {
      if (cntr == maxCount) 
         { cntr = 0; }
      else 
         { cntr = cntr + 1; }
      return cntr;
   }

}
