/* TossableCoin3App.java
** Java application the intent of which is to illustrate the use of the
** TossableCoin3 class.  It creates a coin, tosses it several times, each
** time identifying the result (Heads or Tails) and the head-count,
** tail-count, and (total) toss-count.
** If a command line argument is provided, it is interpreted as the desired
** probability with which Heads should be tossed.  If not, the coin is made
** to be fair.
*/

public class TossableCoin3App {

   public static void main(String[] args) 
   {
      double probOfHeads;
      if (args.length > 0) { probOfHeads = Double.parseDouble(args[0]); }
      else { probOfHeads = 0.5; }

      TossableCoin3 coin = new TossableCoin3(probOfHeads);
      for (int i=1; i <= 100; i++) {
         coin.toss();
         System.out.print(coin + "; ");
         System.out.print(coin.headsCount() + " ");
         System.out.print(coin.tailsCount() + " ");
         System.out.println(coin.tossCount());
      }
   }
}
