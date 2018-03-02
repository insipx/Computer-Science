/* An instance of this class (UmpireClicker3) represents a hand-held
** mechanical device commonly used by baseball/softball umpires to keep
** track of the inning, outs, balls, and strikes during the course of a game.
**
** This class improves upon its predecessor (UmpireClicker2) by its use of
** WrapAroundCounter objects (in place of int values) to represent the 
** counters that keep track of the inning, number of outs, etc.  All the
** logic required to increment such counters is now delegated to the
** WrapAroundCounter class.
**
** Note that not everyone would agree that this class is an improvement upon
** UmpireClicker2, as one could reasonably argue that using WrapAroundCounter
** objects where simple values of type int would suffice makes this class
** "bloated".  In any case, this class (together with WrapAroundCounter)
** does a nice job of illustrating the use of objects.
**
** Author: Robert McCloskey (based upon a class by Paul Jackowitz)
** Date: April 2015 (updated April 2017)
*/

public class UmpireClicker3 {

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
   
   // constructor
   // -----------

   /* Initializes the instance variables of a newly-created UmpireClicker3
   ** object by assigning to each of them a WrapAroundCounter object with
   ** the appropriate upper bound value.
   */
   public UmpireClicker3()
   {
      ballCntr = new WrapAroundCounter(BALL_MAX);
      strikeCntr = new WrapAroundCounter(STRIKE_MAX);
      outCntr = new WrapAroundCounter(OUT_MAX);
      inningCntr = new WrapAroundCounter(INNING_MAX);
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

   // Each mutator simply invokes the increment() method upon the
   // appropriate WrapAroundCounter object.

   public void advanceBallCount() { ballCntr.increment(); }
   
   public void advanceStrikeCount() { strikeCntr.increment(); }
   
   public void advanceOutCount() { outCntr.increment(); }
   
   public void advanceInningCount() { inningCntr.increment(); }

}
