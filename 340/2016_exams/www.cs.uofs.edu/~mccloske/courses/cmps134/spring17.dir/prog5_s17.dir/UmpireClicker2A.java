/* An instance of this class (UmpireClicker2A) represents a hand-held 
** mechanical device commonly used by baseball/softball umpires to keep
** track of the inning, outs, balls, and strikes during the course of a
** game.  This version improves upon its predecessor (UmpireClicker2)
** by automatically resetting counters to zero in situations where they
** must be reset to zero, rather than requiring the umpire (i.e., client
** program) to do it explicitly (via calls to the advanceX() methods).
** Specifically,
** --Whenever the ball count wraps around to zero, the strike count is 
**   reset to zero,
** --Whenever the strike count wraps around to zero, the ball count is 
**   reset to zero,
** --Whenever the out count wraps around to zero, both the ball and strike
**   counts are reset to zero,
** --Every second time the out count wraps around to zero, the inning count
**   is advanced.
**
** Author: Robert McCloskey (based upon a class by Paul Jackowitz)
** Date: April 2017 
*/

public class UmpireClicker2A {

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
   private boolean isTopOfInning;
   

   // constructor
   // -----------

   /* Initializes the instance variables of a newly-created UmpireClicker2A
   ** object.  (Because all of them are initialized to zero, which Java does
   ** automatically, this constructor can be omitted entirely, or, 
   ** alternatively, its body can be made to be empty.)
   */ 
   public UmpireClicker2A()
      { ballCntr = 0;  strikeCntr = 0;  outCntr = 0;  inningCntr = 0; 
        isTopOfInning = true;
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

   public void advanceBallCount()
   {
      ballCntr = advanceCount(ballCntr, BALL_MAX);
      if (ballCntr == 0) { strikeCntr = 0; }
   }
   
   public void advanceStrikeCount()
   {
      strikeCntr = advanceCount(strikeCntr, STRIKE_MAX);
      if (strikeCntr == 0) { ballCntr = 0; }
   }
   
   public void advanceOutCount()
   { 
      outCntr = advanceCount(outCntr, OUT_MAX);
      if (outCntr == 0) { 
         ballCntr = 0;  strikeCntr = 0;
         isTopOfInning = !isTopOfInning;
         if (isTopOfInning) { advanceInningCount(); }
      }
   }
   
   public void advanceInningCount()
   {
      inningCntr = advanceCount(inningCntr, INNING_MAX);
   }


   // private method
   // --------------

   /* Given a (wrap-around) counter together with its allowed maximum
   ** value, returns what should be the counter's value after it is
   ** "advanced" (i.e., incremented).  That would be either zero or one 
   ** more than its current value, the former if the counter's value is 
   ** equal to its maximum allowed value and the latter otherwise.
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
