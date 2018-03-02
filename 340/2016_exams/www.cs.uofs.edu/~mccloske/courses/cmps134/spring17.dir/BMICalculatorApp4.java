/* Java program that computes (and reports) body mass index (BMI) values for
** a particular height and the weights within a range beginning at some 
** specified lower bound up to some specified upper bound, and increasing by 
** some specified interval.
** The "inputs" are specified using assignment statements.
**
** The main purpose is to illustrate the use of Java's for-loop construct.
*/

public class BMICalculatorApp4 {

   private static int height;     // height in inches (input)
   private static int lowWeight;  // lower bound weight (in pounds) (input)
   private static int highWeight; // uppper bound weight (in pounds) (input)
   private static int weightGap;  // gap between weights (in pounds) (input)
   private static double bmi;     // BMI value (output)

   private static int weight;     // weight in pounds (loop control variable)

   public static void main(String[] args)
   {
      height = 67; 
      lowWeight = 110;
      highWeight = 200;
      weightGap = 8;

      for (weight = lowWeight; weight <= highWeight; weight = weight + weightGap) {
         reportBMIValue();
      }
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
