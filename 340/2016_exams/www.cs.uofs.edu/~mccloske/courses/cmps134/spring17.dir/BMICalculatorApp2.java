/* Java program that computes (and reports) body mass index (BMI) values for
** a particular height and two particular weights. 
** The premise is that the measurements are all with respect to the same
** person but the weights were taken on different dates, so that the results
** will indicate how the person's BMI value changed over time.  
** The "inputs" are specified using assignment statements.
**
** The purpose of the program is to illustrate the use of 
** --variables (including the idea that a variable's value can change)
** --symbolic constants (i.e., "final" variables)
** --arithmetic expressions, in particular ones involving numbers of 
**   different types (int and double)
** --casting from int to double
** --string concatenation
*/

public class BMICalculatorApp2 {


   public static void main(String[] args)
   {
      // Value needed to convert result of BMI formula when height and
      // weight are measured in inches and pounds rather than in meters
      // and kilograms, respectively.
      final int UNIT_CONVERSION_FACTOR = 703;

      int height;    // height in inches (input)
      int weight;    // weights in pounds (input)
      double bmi;    // BMI value
      
      // Establish input values via assignments
      height = 67;
      weight = 136;

      // Compute BMI value
      bmi = UNIT_CONVERSION_FACTOR * ((double)weight / (height * height));

      // Report height, weight, and BMI value.
      System.out.print("Height of " + height + " inches ");
      System.out.print("and weight of " + weight + " pounds yields "); 
      System.out.println("BMI value of " + bmi);

      // Now suppose that the person's weight may have changed.
      weight = 140;

      // Compute updated BMI value
      bmi = UNIT_CONVERSION_FACTOR * ((double)weight / (height * height));

      // Report height, new weight, and new BMI value.
      System.out.print("Height of " + height + " inches ");
      System.out.print("and weight of " + weight + " pounds yields "); 
      System.out.println("BMI value of " + bmi);
   }

}
