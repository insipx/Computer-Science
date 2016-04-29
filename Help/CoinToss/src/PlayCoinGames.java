import java.util.Scanner;

/* Java application that lets a user play any of the goofy coin games offered
** in the GoofyCoinGames class.  It is intended to be used as a program by 
** which to test the methods in the GoofyCoinGames class.
**
** Author: R. McCloskey
** Date: April 11, 2016
*/
public class PlayCoinGames {

   private static Scanner input;

   public static void main(String[] args) {

      input = new Scanner(System.in);
      
      int gameChoice = chooseGame();
      System.out.println();

      String tossHistory;

      if (gameChoice == 1)      
         { tossHistory = playTossHeadsUntilThreshold(); }
      else if (gameChoice == 2) 
         { tossHistory = playTossUntilThreshold(); }
      else if (gameChoice == 3) 
         { tossHistory = playTossUntilRun(); }
      else { 
         System.out.println("Invalid choice.");
         tossHistory = "";
      }

      // Report result of game
      int numTosses = tossHistory.length();
      System.out.println("Game ended after " + numTosses + " tosses:");
      System.out.println(tossHistory);
   } 


   /* Prompts user to choose which game to play and returns the user's 
   ** response.
   */
   private static int chooseGame() {
      System.out.println("Enter 1 to play Toss Heads Until Threshold");
      System.out.println("Enter 2 to play Toss Until Threshold");
      System.out.println("Enter 3 to play Toss Until Run");
      return getIntFromUser("Enter choice:");
   }

   /* Plays a game of TossHeadsUntilThreshold after getting user input for the 
   ** threshold.
   */
   private static String playTossHeadsUntilThreshold()
   {
      TossableCoin c = new TossableCoin();
      int threshold = getIntFromUser("Enter # of HEADS to be tossed before stopping:");
      System.out.println("Tossing until " + threshold + " HEADS have occurred...");
      return GoofyCoinGames.tossHeadsUntilThreshold(c, threshold);
   }

   /* Plays a game of TossUntilThreshold after getting appropriate user input for
   ** the threshold and for the coin face.
   */
   private static String playTossUntilThreshold()
   {
      TossableCoin c = new TossableCoin();

      int faceChoice = getIntFromUser("Enter 1 for HEADS and any other number for TAILS:");
      char face;
      String faceName;
      if (faceChoice == 1) { 
         face = TossableCoin.HEADS;
         faceName = "HEADS";
      }
      else {
         face = TossableCoin.TAILS;
         faceName = "TAILS";
      }
      int threshold = getIntFromUser("Enter threshold:");
      System.out.println("Tossing until " + threshold + ' ' + faceName + 
                         " have occurred...");
      return GoofyCoinGames.tossUntilThreshold(c, threshold, face);
   }


   /* Plays a game of TossUntilRun after getting appropriate user input for
   ** the threshold and for the coin face.
   */
   private static String playTossUntilRun()
   {
      TossableCoin c = new TossableCoin();
      int runLen = getIntFromUser("Enter desired run length:");
      int faceChoice = getIntFromUser("Enter 1 for HEADS, 2 for TAILS, other for either:");
      
      if (faceChoice == 1) {
         System.out.println("Tossing until a run of " + runLen + " HEADS occurs...");
         return GoofyCoinGames.tossUntilRun(c, runLen, TossableCoin.HEADS);
      }
      else if (faceChoice == 2) {
         System.out.println("Tossing until a run of " + runLen + " TAILS occurs...");
         return GoofyCoinGames.tossUntilRun(c, runLen, TossableCoin.TAILS);
      }
      else {
         System.out.println("Tossing until a run of " + runLen + 
                            " HEADS or TAILS occurs...");
         return GoofyCoinGames.tossUntilRun(c, runLen);
      }
   }


   /* Returns the integer value entered at the keyboard in response to the
   ** the specified prompt.  (If the sequence of characters entered at the
   ** keyboard is not of a form that can be recognized as describing an
   ** integer, an exception is thrown.)
   */
   private static int getIntFromUser(String prompt) {
      System.out.print(prompt);
      return input.nextInt();
   }
}
