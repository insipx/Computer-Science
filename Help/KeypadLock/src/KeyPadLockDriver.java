import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/* Java application program having as its purpose to exercise/test the 
** KeyPadLock class (instances of which simulate keypad lock devices).
** The program creates an instance of KeyPadLock, getting the secret
** code either from a file or the user, depending upon whether or not,
** respectively, a command line argument (indicating the file's name)
** is provided.
** The user may then enter "commands" for the lock at the computer
** keyboard (or they are read from the input file). 
** Commands to the lock are as follows:
**
**    q : quit (i.e., terminate program)
**    o : (attempt to) open lock
**    c : close lock
**    0 : enter 0 on the lock's keypad
**    1 : enter 1 on the lock's keypad
**    .
**    .
**    9 : enter 9 on the lock's keypad
**
** For each command entered by the user (and (s)he may enter any number
** of commands on a single line in response to the program's prompt), the
** program responds by reporting the current state of the lock (i.e.,
** open or closed).
**
** If an invalid command is entered, the program ignores it.
**
** In jGRASP, to feed a program command line arguments, click on the "Build"
** menu, then click "Run Arguments" to turn it on (check mark means on).
** Then fill in the text box (labeled "Run Arguments") with the desired
** arguments.  Here that would be the name of a file containing input data.
**
** Author: R. McCloskey
** Date: originally October 2001, updated April 2016.
*/

public class KeyPadLockDriver {

   // Should input be printed?  Yes if coming from a file, no if from keyboard.
   private static boolean echoInput;

   private static Scanner input;


   /* If there is a command line argument, it is taken to be the name of
   ** the file from which to read input data.  Otherwise input comes from
   ** the keyboard.  The first line of input must be a three-digit number,
   ** which is taken to be the lock's secret code.
   ** Each subsequent line must contain one or more "commands" to be issued
   ** to the KeyPadLock object.
   */
   public static void main(String[] args) throws FileNotFoundException
   {
      if (args.length > 0) {   // input is to come from file
         String fileName = args[0];
         File f = new File(fileName);
         input = new Scanner(f);
         echoInput = true;
      }
      else {   // input to come from keyboard
         input = new Scanner(System.in);
         echoInput = false;
      }

      int code = getIntFromUser("Enter three-digit secret code:");

      KeyPadLock kpl = new KeyPadLock( code );

      System.out.println("A keypad lock with code " + code +
                         " has been created.");

      processUserCommands(kpl);

      System.out.println("****** Program terminating normally *****");
   }


   private static void processUserCommands(KeyPadLock kpl)
      //throws IOException
   {
      boolean keepGoing = true;
      String commandStr = new String("");
      char command;
      
      while ( keepGoing )
      {
         System.out.print("Enter one or more commands:");
         commandStr = input.nextLine();
         if (echoInput) { System.out.println(commandStr); }

         for (int i=0;  i != commandStr.length();  i = i+1) {
            command = commandStr.charAt(i);
            System.out.print("Performing command " + command + " ... ");
            if (command == 'q')
               { keepGoing = false; }
            else {
               performCommand(kpl, command);
               System.out.print(" Afterwards, lock is ");
               if (kpl.isOpen())
                  { System.out.print("OPEN"); }
               else
                  { System.out.print("CLOSED"); }
            }
            System.out.println();
         }
      }
   }


   private static void performCommand(KeyPadLock k, char command) {

      if (command == 'o')
         { k.open(); }
      else if (command == 'c')
         { k.close(); }
      else if (Character.isDigit(command))
         { k.enterDigit( charToDigit(command) ); } 
      else 
         { System.out.print("Invalid command ignored ..."); }
   }


   /* pre:  c is in the range '0'..'9'
   ** post: value returned is the integer value corresponding to c.
   **      E.g., if c is (the char) '4', value returned will be (the int) 4.
   **/
   private static int charToDigit(char c)
      { return (int) c  -  (int) '0'; }


   /* Prints the specified prompt, reads the rest of the current
   ** line of input, translates it to an int, and returns it.
   */
   private static int getIntFromUser(String prompt)
   {
      System.out.print(prompt);
      String response = input.nextLine();
      if (echoInput) { System.out.println(response); }
      return Integer.parseInt(response);
   }

}
