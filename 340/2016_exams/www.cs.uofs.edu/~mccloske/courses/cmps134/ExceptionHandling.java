import java.util.Scanner;
import java.util.InputMismatchException;

/* The purpose of this Java class is to illustrate the technique of
** exception handling in the Java language by showing an example.
*/
public class ExceptionHandling {

   // Create a Scanner object capable of reading input from the keyboard.
   private static Scanner keyboard = new Scanner(System.in);

   public static void main(String[] args) {
      for (int i=1; i != 5; i++) {
         quotientOfTwoInts();  System.out.println();
      }
   }


   /* Prompts user to enter two integers, separated by a space, and
   ** prints their quotient.  If the input entered is not valid, or
   ** if the specified divisor is zero, a descriptive error message 
   ** is printed.
   ** The manner in which input is read here could be improved by
   ** using the nextInt() method of the Scanner class, but it was 
   ** chosen to do it in a more roundabout way in order for there to
   ** be more possible kinds of exceptions that could be thrown and
   ** handled.
   */
   public static void quotientOfTwoInts() {
      System.out.print("Enter two integers separated by one space:");
      try {
         String response = keyboard.nextLine();
         int posOfSpace = response.indexOf(' ');
         String kStr = response.substring(0,posOfSpace);  // prefix before space
         String mStr = response.substring(posOfSpace+1);  // suffix after space
         int k = Integer.parseInt(kStr);  // translate from String to int
         int m = Integer.parseInt(mStr);  // translate from String to int
         int quotient = k / m;
         System.out.println("Quotient is " + quotient);
      }
      catch (StringIndexOutOfBoundsException e) {
         // Print a friendly error message.
         System.out.println("Error: Missing space between inputs");
         // Print e's stack trace.
         e.printStackTrace(System.out);
         // Print whatever e wants to say about itself.
         System.out.println(e);
      }
      catch (NumberFormatException e) {
         System.out.println("Error: Non-integer input (possibly extra spaces).");
         // Print whatever e wants to say about itself.
         System.out.println(e);
      }
      catch (ArithmeticException e) {
         System.out.println("Arithmetic Error: Division by zero, perhaps.");
      }
   }   

}
