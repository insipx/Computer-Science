/** Java application that performs a conversion from yards to meters for a
**  a particular yardage.
*/

public class YardsToMeters1 {

   public static void main(String[] args)
   {
      final double METERS_PER_YARD = 0.9144;

      double yards;    // declaration of input variable
      double meters;   // declaration of output variable
 
      yards = 100.0;   // assignment of a value to input variable
      meters = yards * METERS_PER_YARD;  // computation of output variable

      // Report result
      System.out.println(yards + " yards equals " + meters + " meters.");
   }
}
