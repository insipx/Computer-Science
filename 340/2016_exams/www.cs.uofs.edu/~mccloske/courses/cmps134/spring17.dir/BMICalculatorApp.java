/* Java program that computes (and reports) a body mass index (BMI) value 
** for a particular height and weight, both of which are specified using
** assignment statements.
**
** The purpose of the program is to illustrate the use of 
** --variables
** --symbolic constants (i.e., "final" variables)
** --arithmetic expressions, in particular ones involving numbers of 
**   different types (int and double)
** --casting from int to double
** --assignment statements
** --string concatenation
*/

public class BMICalculatorApp {

   public static void main(String[] args)
   {
      // Value needed to convert result of BMI formula when height and
      // weight are measured in inches and pounds rather than in meters
      // and kilograms, respectively.
      final int UNIT_CONVERSION_FACTOR = 703;

      int height;    // height in inches (input)
      int weight;    // weights in pounds (input)
      double bmi;    // BMI value
      
      // Establish input values via assignments.
      height = 67;
      weight = 136;

      // Compute BMI value.
      bmi = UNIT_CONVERSION_FACTOR * ((double)weight / (height * height));

      // Report height, weight, and BMI value.
      System.out.print("Height of " + height + " inches ");
      System.out.print("and weight of " + weight + " pounds yields "); 
      System.out.println("BMI value of " + bmi);
   }

}
