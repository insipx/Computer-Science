/* TossableCoin4App.java
** Java application the intent of which is to illustrate the use of the
** TossableCoin4 class.  It creates a coin, tosses it several times, each
** time identifying the result (Heads or Tails) and the head-count,
** tail-count, and (total) toss-count.
** Afterwards, the history of the coin's toss results is printed.
** If a command line argument is provided, it is interpreted as the desired
** probability with which Heads should be tossed.  If not, the coin is made
** to be fair.
*/

public class TossableCoin4App {

   public static void main(String[] args) 
   {
      double probOfHeads;
      if (args.length > 0) { probOfHeads = Double.parseDouble(args[0]); }
      else { probOfHeads = 0.5; }

      TossableCoin4 coin = new TossableCoin4(probOfHeads);
      int numTosses = 10;
      for (int i=1; i <= numTosses; i++) {
         coin.toss();
         System.out.print(coin + "; ");
         System.out.print(coin.headsCount() + " ");
         System.out.print(coin.tailsCount() + " ");
         System.out.println(coin.tossCount());
      }

      // Now demonstrate the history feature:
      System.out.print("Toss results: ");
      for (int i=1; i <= numTosses; i++) {
         char face;
         if (coin.isHeads(i)) { face = 'h'; }
         else { face = 't'; }
         System.out.print(face);
      }
      System.out.println();
   }
}
