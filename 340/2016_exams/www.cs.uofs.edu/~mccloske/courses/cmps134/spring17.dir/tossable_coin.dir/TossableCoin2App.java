/* TossableCoin2App.java
** Java application the intent of which is to illustrate the use of the
** TossableCoin2 class.  It creates a coin, tosses it several times, each
** time identifying the result (Heads or Tails) and the head-count,
** tail-count, and (total) toss-count.
*/

public class TossableCoin2App {

   public static void main(String[] args) 
   {
      TossableCoin2 coin = new TossableCoin2();
      for (int i=1; i <= 10; i++) {
         coin.toss();
         System.out.print(coin + "; ");
         System.out.print(coin.headsCount() + " ");
         System.out.print(coin.tailsCount() + " ");
         System.out.println(coin.tossCount());
      }
   }
}
