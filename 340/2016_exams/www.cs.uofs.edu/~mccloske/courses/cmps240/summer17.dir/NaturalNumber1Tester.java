/* NaturalNumber1Tester: Java application that evaluates simple arithmetic expressions
** making use of objects from the NaturalNumber1 class.
**
** @author: R. McCloskey
** @version: June 2017
*/

public class NaturalNumber1Tester {

   public static void main(String[] args) {
      // Create an instance of NaturalNumber1 to use as a seed.
      NaturalNumber1 k = new NaturalNumber1(37);

      // Pass that seed to the calculate() method of the NaturalNumCalculator class.
      NaturalNumCalculator.calculate(k);
   }

}

