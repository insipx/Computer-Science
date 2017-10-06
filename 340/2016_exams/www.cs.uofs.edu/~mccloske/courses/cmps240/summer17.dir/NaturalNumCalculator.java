/* NaturalNumCalculator: class that evaluates simple arithmetic expressions
 * involving NaturalNumber values.
 *
 * @author: R. McCloskey
 * @version: June 2017
*/

import java.util.Scanner;

public class NaturalNumCalculator {

   //private NaturalNumber seed;  // Purpose is for creating other instances of the
   //                             // same class by using its factory() method.

   //public NaturalNumCalculator(NaturalNumber n) { seed = n; }

   public static void calculate(NaturalNumber seed) {

      Scanner keyBoard = new Scanner(System.in);

      boolean continu = true;
      String response;

      System.out.println("Welcome to the Natural Number Calculator Program.");
      System.out.println("At the prompt, enter an expression and" +
                          " hit Enter to get the result.");
      System.out.println("Enter the null string to exit");


      while (continu) {
         System.out.println();
         System.out.print(">");
         response = keyBoard.nextLine();
         if (response.length() == 0) 
            { continu = false; }
         else
            { System.out.println(evaluateExpr(seed, response)); }
      }
    
   }

   /* pre: expr is of the form <NatNum> <op> <NatNum>, where
   **      <op> is one of '+', '-', '*', '/', '%', '<', '>', or '=' and
   **      <NatNum> is composed of an optional sign (either '+' or '-')
   **      followed by a sequence of decimal digits.
   **      Any number of spaces may occur before, between, or after
   **      the three "tokens" indicated.
   ** post: object returned is the NaturalNumber whose value corresponds
   **       to the result of evaluating expr.  In the case that the expr
   **       was a comparison (involving one of the operators '<', '=', 
   **       or '>', the result will be ONE for true and ZERO for false.
   */
   public static NaturalNumber evaluateExpr(NaturalNumber seed, String expr) {

      int i = 0;
      expr = expr.trim();

      String[] tokens = expr.split("[' ']+");
      NaturalNumber a = seed.factory(tokens[0]);
      NaturalNumber b = seed.factory(tokens[2]);
      NaturalNumber result = null;
      boolean resultRel = false;    // result of relational op

      String oper = tokens[1];
      if (oper.equals("+"))
         { result = a.plus(b); }
      else if (oper.equals("-"))
         { result = a.minus(b); }
      else if (oper.equals("*"))
         { result = a.times(b); }
      else if (oper.equals("/"))
         { result = a.dividedBy(b); }
      else if (oper.equals("%"))
         { result = a.modulo(b); }
      else if (oper.equals("<"))
         { resultRel = a.lessThan(b); }
      else if (oper.equals(">"))
         { resultRel = a.greaterThan(b); }
      else if (oper.equals("="))
         { resultRel = a.equals(b); }
      else
         { System.out.println("Error: undefined operator: " + oper); }

      if (result == null) {    // expr was a comparison
         if (resultRel) 
            { result = seed.ONE(); }
         else
            { result = seed.ZERO(); }
      }
      return result; 
   }

}

