import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/** Java application having as its purpose to exercise the methods of the
**  UmpireClicker1 (or 2, or 3) class (with the obvious modifications being
**  necessary).  The program creates an instance of that class and then
**  accepts commands from the user (or from the file named by a command line
**  argument) analogous to those that an umpire would issue to an umpire 
**  clicker device (namely, advance balls, strikes, outs, and inning).  
**  After each command is carried out, the state of the UmpireClicker object
**  is reported.
**
**  @author R. McCloskey
**  @version October 2013
*/
public class UmpireClickerApp 
{
   private static final char BALL = 'b';
   private static final char STRIKE = 's';
   private static final char OUT = 'o';
   private static final char INNING = 'i';
   private static final char RESET = 'r';
   private static final char QUIT = 'q';
   private static final char USER_PROMPT = '>';


   /** The main program.
   */
   public static void main(String[] args) throws FileNotFoundException
   {
      Scanner input;
      if (args.length == 0) {
         input = new Scanner(System.in); // Set up Scanner to read from keyboard

      }
      else {
         File f = new File(args[0]);  // Set up Scanner to read from the file
         input = new Scanner(f);      // named by the command line argument
      }

      UmpireClicker1 umpClicker = new UmpireClicker1();

      System.out.print("Initially ... ");
      System.out.println(umpClicker.toString());

      boolean finished = false;

      while (!finished)
      {
         System.out.print("\n" + USER_PROMPT);
         String line = input.nextLine().trim();
         char command = line.charAt(0);
         if (command == QUIT) 
            { finished = true; }
         else if (command == BALL ) { 
            System.out.print("Ball ...");
            umpClicker.advanceBallCount(); 
         }
         else if (command == STRIKE) { 
            System.out.print("Strike ...");
            umpClicker.advanceStrikeCount();
         }
         else if (command == OUT) { 
            System.out.print("Out ...");
            umpClicker.advanceOutCount();
         }
         else if (command == INNING) { 
            System.out.print("Inning ...");
            umpClicker.advanceInningCount();
         }
         else if (command == RESET) { 
            System.out.print("Resetting clicker ... ");
            umpClicker = new UmpireClicker1(); 
         }
         else {
            System.out.println("Invalid command; valid commands are " + 
                               BALL + ", " + STRIKE + ", " + OUT + ", " +
                               INNING + ", " + RESET);
         }
         System.out.println(umpClicker.toString());
      }

      System.out.println("Program terminating normally.");
   }

}
