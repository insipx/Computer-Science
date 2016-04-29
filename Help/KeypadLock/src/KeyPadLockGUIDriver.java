import javax.swing.JFrame;
import java.util.Scanner;

/** Java application that starts up a KeyPadLockGUI interface.
**
**  @author R. McCloskey
**  @version November 2007
*/

public class KeyPadLockGUIDriver {

   public static void main(String[] args) {

      int secretCode = 253;  // default secret code

      /* If secret code is supplied as command line arg, abandon the
      ** default value and translate the argument to an array of int's.
      */
      if (args.length != 0) {
         String codeStr = args[0];
         secretCode = Integer.parseInt(codeStr);
      }
      else {
         Scanner keyboard = new Scanner(System.in);
         System.out.print("Enter 3-digit secret code:");
         secretCode = keyboard.nextInt();
      }

      // create a GUI JPanel object to act as the keypad lock.
      KeyPadLockGUIPanel kplgp = 
         new KeyPadLockGUIPanel(new KeyPadLock(secretCode));


      // create a JFrame, add the keypad lock panel to it, and make it visible
      JFrame frame = new JFrame("Keypad Lock");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(kplgp);
      frame.pack();
      frame.setVisible(true);
   }

   /* Converts a character (expected to be a digit, such as '4') into the
   ** corresponding int value.  Relies upon the fact that the numeric values
   ** of '0', '1', ..., '9' are consecutive integers.
   */
   private static int charToDig(char c)
      { return ((int) c) - ((int) '0'); }

}
