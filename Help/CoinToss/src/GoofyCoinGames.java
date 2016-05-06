/* Java class containing some static methods that toss a TossableCoin until
** the results of the tosses satisfy some particular condition.
**
** Author: R. McCloskey and < student's name >
** Date: April 7-19, 2016
*/

public class GoofyCoinGames {

   /* Repeatedly tosses the specified TossableCoin until HEADS has occurred 
   ** the specified number of times.
   ** The value returned is a String composed of the TossableCoin.HEADS and
   ** TossableCoin.TAILS characters that indicate the results of the tosses
   ** made here.
   ** precondition: coin != null  &&  threshold >= 0
   */
   public static String tossHeadsUntilThreshold(TossableCoin coin, int threshold)
   {
      String result = "";  // depicts sequence of tosses made by this method
      int headsCount = 0;  // # of tosses resulting in HEADS

      while (headsCount != threshold) {
         coin.toss();
         result = result + coin.faceShowing();
         if (coin.faceShowing() == TossableCoin.HEADS) {
            headsCount = headsCount + 1;
         }
      }
      return result;
   }


   /* Repeatedly tosses the specified TossableCoin until the specified face
   ** (HEADS or TAILS) has occurred the specified number of times.
   ** The value returned is a String composed of the TossableCoin.HEADS and
   ** TossableCoin.TAILS characters that indicate the results of the tosses
   ** made here.
   ** precondition: coin != null  &&  threshold >= 0  &&  
   **               (face == TossableCoin.HEADS || face == TossableCoin.TAILS)
   */
   public static String tossUntilThreshold(TossableCoin coin, int threshold, char face)
   {
      String result = "";  // depicts sequence of tosses made by this method

      int count = 0;  // # of tosses resulting in HEADS

      while(count != threshold){
         coin.toss();
         result=result+coin.faceShowing();
         if(coin.faceShowing() == face){
            count++;
         }
      }



      return result;
   }


   /* Repeatedly tosses the specified TossableCoin until a run of tosses
   ** of the specified length producing the specified face (HEADS or TAILS)
   ** has occurred.
   ** The value returned is a String composed of the TossableCoin.HEADS and
   ** TossableCoin.TAILS characters that indicate the results of the tosses
   ** made here.
   **
   ** For example, suppose that the specified face is HEADS and the specified
   ** run length is 3.  Now suppose that this method tosses the specified
   ** TossableCoin and the results are THHTTTHTHHTHHH (with the obvious 
   ** interpretation).  At this point, the tossing should cease (because the
   ** last three tosses constitute a run of HEADS) and 14 should be returned
   ** (because the run of three heads ended on the 14th toss).
   ** precondition: coin != null  &&  runLength >= 0  && 
   **               (face == TossableCoin.HEADS  ||  face == TossableCoin.TAILS)
   */
   public static String tossUntilRun(TossableCoin coin, int runLength, char face)
   {
      String result = "";
      int count = 0;
      while(count != runLength){
         coin.toss();
         result += coin.faceShowing();
         if(coin.faceShowing() == face)
            count++;
         else{
            count = 0;
         }

      }

      return result;
   }

   /* Repeatedly tosses the specified TossableCoin until a run of tosses
   ** of the specified length producing the same face (either HEADS or TAILS)
   ** has occurred. 
   ** The value returned is a String composed of the TossableCoin.HEADS and
   ** TossableCoin.TAILS characters that indicate the results of the tosses
   ** made here.
   **
   ** For example, suppose that the specified # of times in a row is 4.  
   ** Now suppose that this method tosses the specified TossableCoin and the
   ** results are THHTTTHTHHTHHHTHTTTT (with the obvious interpretation).  
   ** At this point, the tossing should cease (because the last four tosses 
   ** constitute a run of one of the two faces, in this case TAILS) and 20
   ** should be returned (because the run of four TAILS ended on the 20th toss).
   ** precondition: coin != null  &&  runLength >= 0
   */
   public static String tossUntilRun(TossableCoin coin, int runLength)
   {
      String result = "";
      int headCount = 0;
      int tailsCount = 0;
      while(headCount != runLength || tailsCount != runLength){
         result += coin.faceShowing();
         if(coin.faceShowing() == TossableCoin.HEADS){

         }else{
            headCount = 0;
         }

      }


      return result;
   }

}
