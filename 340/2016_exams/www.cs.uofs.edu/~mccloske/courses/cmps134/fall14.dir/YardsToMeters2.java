import java.util.Scanner;   // needed to read input data

/** Java application that performs a conversion from yards to meters for a
**  a particular yardage that is provided as input by the user in response
**  to a prompt from the program.
*/

public class YardsToMeters2 {

   public static void main(String[] args)
   {
      final double METERS_PER_YARD = 0.9144;

      Scanner keyboard = new Scanner(System.in);

      double yards;    // declaration of input variable
      double meters;   // declaration of output variable

      // Prompt user.
      System.out.print("Enter in measurement in yards: ");
 
      // Read value entered at keyboard and assign it to input variable
      yards = keyboard.nextDouble();

      meters = yards * METERS_PER_YARD;  // computation of output variable

      // Report result
      System.out.println(yards + " yards equals " + meters + " meters.");
   }
}
