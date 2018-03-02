import java.util.Scanner;   // Needed in order to make use of objects
                            // from the Scanner class

/* Java program that prompts the user to enter two integers, say k and m,
** and that reports the sum of the integers in the interval [k,m].
** The point is to illustrate how to 
** --create a Scanner object (i.e., one derived from the java.util.Scanner
**   class) that can read input entered at the keyboard
** --read integer values (using the Scanner's nextInt() method)
** --declare and call methods with parameters
** --declare, call, and make use of the value returned by a functional method
**
** This version of the program differs from its predecessor (the name of which
** lacks the '2') in that here we use the getIntFromUser() method in order to
** encapsulate the operations of prompting the user to enter input and reading
** that input.
*/

public class SumOfRangeApp2 {

   // Establish a Scanner object that can read input entered at keyboard.
   // It is necessary to declare the variable as global (as opposed to locally
   // within main()) so that its scope includes the getIntFromUser() method. 
   private static Scanner keyboard = new Scanner(System.in);

   public static void main(String[] args)
   {
      // Read the two input values into variables.
      int lowBound = getIntFromUser("Enter lower bound:");
      int highBound = getIntFromUser("Enter upper bound:");

      // Call functional method to compute the desired sum and store the
      // result in a variable.
      int rangeSum = sumOfRange(lowBound, highBound);

      // Print a message that reports the input values and the output
      // computed therefrom.
      System.out.println("Sum of integers in range " + lowBound +
                         ".." + highBound + " is " + rangeSum);
   }

   /* Prints the specified string (i.e., prompt) and returns the value
   ** entered in response by the user.  If the user's response is not a
   ** sequence of characters that can be interpreted to be an integer,
   ** an exception is thrown.
   */
   private static int getIntFromUser(String prompt)
   {
      System.out.print(prompt);
      return keyboard.nextInt();
   }


   /* Functional method that returns the sum of the integers in the interval
   ** specified by the caller (via the method's parameters) (i.e., [low,high]).
   ** In the case that the interval is empty (i.e., low > high), that sum is
   ** zero, of course.
   */
   public static int sumOfRange(int low, int high)
   {
      int sum = 0;
      for (int i=low; i <= high; i=i+1) {
         // loop invariant: sum = low + (low+1) + ... + (i-1)
         sum = sum + i;
      }
      return sum;
   }

}
