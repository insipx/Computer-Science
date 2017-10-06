import java.util.Scanner;

/* Java application that reports the mean of the integer values
*  supplied to it by the user.  The program prompts the user to
*  enter each value; 0 signals end-of-data.
*
*  Author: R. McCloskey (based on a program by Paul Jackowitz)
*  Date: August 24, 2010
*/

public class MeanProgram
{

   private static Scanner keyboard = new Scanner(System.in);

   public static void main(String[] args)
   {    
      int inputVal;   // the most recent input entered by the user
      int sumSoFar;   // the sum of inputs entered so far
      int count;      // the # of inputs entered so far
      double mean;    // the result
      
      sumSoFar = 0;  count = 0;   // obvious initializations

      // get first input value
      inputVal = getAnInput();

      // accept and process input values until a zero value is entered
      while (inputVal != 0)
      {
         sumSoFar = sumSoFar + inputVal;
         count = count + 1;
         inputVal = getAnInput();
      }

      // now report the result
      System.out.print("The mean of the integers entered is "); 
      if (count > 0)
      {
         mean = (double)sumSoFar / (double)count;
         System.out.println(mean);
      }
      else
      {
         System.out.println("undefined");
      }
   }

   /* Prompts the user to enter an input, accepts the response,
   ** and returns it to the caller.
   */
   private static int getAnInput()
   {
      System.out.print("Enter an integer (zero to quit): ");
      return keyboard.nextInt();
   }

}
