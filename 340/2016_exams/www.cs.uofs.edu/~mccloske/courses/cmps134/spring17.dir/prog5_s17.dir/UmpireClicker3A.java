/* An instance of this class (UmpireClicker3) represents a hand-held
** mechanical device commonly used by baseball/softball umpires to keep
** track of the inning, outs, balls, and strikes during the course of a game.
** This version improves upon its predecessor (UmpireClickerA)
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
**
** Author: Robert McCloskey (based upon a class by Paul Jackowitz)
** Date: April 2015 (updated April 2017)
*/

public class UmpireClicker3A {

   // symbolic/named constants
   // ------------------------
   private static final int BALL_MAX = 4;      // These constants indicate
   private static final int STRIKE_MAX = 3;    // the maximum values of
   private static final int OUT_MAX = 3;       // the corresponding
   private static final int INNING_MAX = 9;    // instance variables.
      
   // instance variables (fields)
   // ---------------------------
   private WrapAroundCounter ballCntr;    // for keeping track of the counts 
   private WrapAroundCounter strikeCntr;  // of balls, strikes, outs, and the 
   private WrapAroundCounter outCntr;     // inning
   private WrapAroundCounter inningCntr;     
   private boolean isTopOfInning;         // Is it the top of the current inning?
   
   // constructor
   // -----------

   /* Initializes the instance variables of a newly-created UmpireClicker3
   ** object by assigning to each of them a WrapAroundCounter object with
   ** the appropriate upper bound value.
   */
   public UmpireClicker3A()
   {
      ballCntr = new WrapAroundCounter(BALL_MAX);
      strikeCntr = new WrapAroundCounter(STRIKE_MAX);
      outCntr = new WrapAroundCounter(OUT_MAX);
      inningCntr = new WrapAroundCounter(INNING_MAX);
      isTopOfInning = true;
   }

   
   // observers
   // ---------

   // Each "get" observer simply retrieves the "counter value" of
   // the appropriate WrapAroundCounter object using that object's
   // countVal() method, and then returns that retrieved value.

   public int getBallCount() { return ballCntr.countVal(); }
   
   public int getStrikeCount() { return strikeCntr.countVal(); }
   
   public int getOutCount() { return outCntr.countVal(); }
   
   public int getInningCount() { return inningCntr.countVal(); }
   

   public String toString()
   {
      return "Inning " + getInningCount() + ", Out " + getOutCount() +
             ", Ball " + getBallCount() + ", Strike " + getStrikeCount();
   }
   

   // mutators
   // --------

   // Each mutator simply invokes the increment() method upon the appropriate
   // WrapAroundCounter object.  In those cases when the counter wrapping
   // around to zero should trigger other updates, they are carried out.

   public void advanceBallCount() { 
      ballCntr.increment();
      if (getBallCount() == 0) { strikeCntr.reset(); }
   }
   
   public void advanceStrikeCount() { 
      strikeCntr.increment();
      if (getStrikeCount() == 0) { ballCntr.reset(); }
   }
   
   public void advanceOutCount() { 
      outCntr.increment();
      if (getOutCount() == 0) { 
         ballCntr.reset();  strikeCntr.reset();
         isTopOfInning = !isTopOfInning;
         if (isTopOfInning) { advanceInningCount(); }
      }
   }
   
   public void advanceInningCount() { inningCntr.increment(); }

}
