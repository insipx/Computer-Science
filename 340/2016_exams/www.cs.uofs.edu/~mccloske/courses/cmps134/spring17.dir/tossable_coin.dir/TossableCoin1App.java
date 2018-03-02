/* Java application the intent of which is to illustrate the use of the
** TossableCoin1 class.  It creates a pair of coins, tosses them several
** times, and shows the result of each toss.
*/

public class TossableCoin1App {

   public static void main(String[] args) {
      toss2Coins(8);  System.out.println();
      tossAndCount(10);
   }


   /* Creates and tosses a pair of coins the specified number of times,
   ** each time reporting the faces showing.
   */
   public static void toss2Coins(int n) {
      TossableCoin1 coinA = new TossableCoin1();
      TossableCoin1 coinB = new TossableCoin1();
      for (int i=1; i <= n; i++) {
         coinA.toss(); coinB.toss();
         System.out.println(coinA.toString() + " " + coinB.toString());
      }
   }

   /* Creates and tosses a coin the specified number of times,
   ** each time reporting the face showing and afterwards reporting
   ** how many times each face resulted from a toss.
   */
   public static void tossAndCount(int n) {
      TossableCoin1 coin = new TossableCoin1();
      int headsCntr = 0, tailsCntr = 0;
      for (int i=1; i <= n; i++) {
         coin.toss();
         if (coin.isHeads()) { headsCntr++; }
         else if (coin.isTails()) { tailsCntr++; }
         else { System.out.println("Coin is standing on its end!"); }
         System.out.print(coin.toString() + "; ");
         System.out.println(headsCntr + " " + tailsCntr);
      }
   }
}
