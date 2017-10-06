/* Java program that computes (and reports) body mass index (BMI) values for
** a particular height and several particular weights. 
** The premise is that the measurements are all with respect to the same
** person but the weights were taken on different dates, so that the results
** will indicate how the person's BMI value changed over time.  
** The "inputs" are specified using assignment statements.
**
** This program is different from its predecessor in that methods are
** used for computing BMI values and for printing output, rather than
** this all being done directly in the main() method.  This has the
** positive effect of reducing code redundancy but the negative effect
** of requiring the relevant variables to be "global".  This negative
** effect will be avoided when we advance to using methods that accept
** parameters/arguments and that return values to their caller.
*/

public class BMICalculatorApp3 {


   private static int height;    // height in inches (input)
   private static int weight;    // weights in pounds (input)
   private static double bmi;    // BMI value (output)

   public static void main(String[] args)
   {
      height = 67; 
      weight = 136;
      reportBMIValue();
      weight = 140;
      reportBMIValue();
      weight = 130;
      reportBMIValue();
      weight = 151;
      reportBMIValue();
      weight = 146;
      reportBMIValue();
   }

   /* Reports the BMI value corresponding to the values in the global variables
   ** 'height' and 'weight'.
   */
   private static void reportBMIValue() {

      computeBMI();
      
      System.out.print("Height of " + height + " inches ");
      System.out.print("and weight of " + weight + " pounds yields "); 
      System.out.println("BMI value of " + bmi);
   }


   /* Assigns to global variable 'bmi' the BMI value computed from the values
   ** in global variables 'height' and 'weight'.
   */
   private static void computeBMI()
   {
      // Conversion factor needed to convert result of BMI formula when height
      // and weight are measured in inches and pounds rather than in meters
      // and kilograms, respectively.
      final int UNIT_CONVERSION_FACTOR = 703;
   
      bmi = UNIT_CONVERSION_FACTOR * ((double)weight / (height * height));
   }

}
