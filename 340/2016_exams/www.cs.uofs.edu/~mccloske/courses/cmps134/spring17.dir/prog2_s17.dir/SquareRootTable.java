/* SquareRootTable.java
** CMPS 134 
** Purpose: To illustrate for-loops and the use of a method (sqrt()) in the
**          java.Math class and the System.out.printf() method for formatting
**          output, in particular numeric output.
** Author: R. McCloskey
** Last Modified: Feb. 2017
**
** Java application that generates a table of square roots.
**
** The user is prompted to enter two integers, bottom and top, the intent
** being that bottom <= top.  The program will then produce a table showing
** an approximation (rounded to two places after the decimal point) to the 
** square root of values in the interval [bottom,top+1), starting with 
** bottom and at gaps of 0.1.  Each row of the table shows the square roots
** of k, k+0.1, k+0.2, ..., k+0.9, for some k in [bottom,top].
** As an example, if bottom is chosen to be 0 and top is chosen to be 4,
** the table produced will look like this:
**
**  Num |  0.0   0.1   0.2   0.3   0.4   0.5   0.6   0.7   0.8   0.9
** -----|------------------------------------------------------------
**   0  |  0.00  0.32  0.45  0.55  0.63  0.71  0.77  0.84  0.89  0.95
**   1  |  1.00  1.05  1.10  1.14  1.18  1.22  1.26  1.30  1.34  1.38
**   2  |  1.41  1.45  1.48  1.52  1.55  1.58  1.61  1.64  1.67  1.70
**   3  |  1.73  1.76  1.79  1.82  1.84  1.87  1.90  1.92  1.95  1.97
**   4  |  2.00  2.02  2.05  2.07  2.10  2.12  2.14  2.17  2.19  2.21
**
** Thus, for example, the entry in the row labeled 3 and column labeled 0.6
** is 1.90, which is (an approximation to) the square root of 3.6.
*/

import java.util.Scanner;   // For reading input data from keyboard
import java.lang.Math;      // For computing a number raised to a power

public class SquareRootTable {

   private final static char DELIMITER = '|';  // printed after each row heading

   public static void main(String[] args)
   {
      // Establishes a Scanner object that can read data entered at keyboard
      Scanner keyboard = new Scanner(System.in);
      
      // For each of the two inputs, prompt the user and assign the
      // response to a variable.

      // First get the lower bound of the range of numbers whose
      // square roots are to be computed.
      System.out.print("Enter starting integer:");
      int bottom = keyboard.nextInt();

      // Then get the upper bound of the range.
      System.out.print("Enter ending integer:");
      int top = keyboard.nextInt();

      System.out.println();   // so that a blank line appears above the table
      printColumnHeadings();  // print column headings of the table

      // Print the body of the table.
      for(int k = bottom; k <= top; k = k+1) {
         printRow(k);  
         System.out.println();
      }
   }


   /* Prints one row of the table, namely the one in which the square roots
   ** of num, num+0.1, num+0.2, ..., num+0.9 are to appear, where num is the
   ** formal parameter (whose value is that which was passed by the caller).
   */
   private static void printRow(int num)
   {
      // Print the row heading (num) and delimiter.
      System.out.printf("%3d  %1c", num, DELIMITER);

      // Now compute and print the square roots of num, num+0.1, ..., num+0.9,
      // each right-justified in a field of width six with two digits after
      // the decimal point.
      for (int i = 0; i < 10; i = i+1) {
         double x = num + 0.1 * i;
         System.out.printf("%6.2f", Math.sqrt(x));
      }
   }


   /* Prints column headings for the table.  It is assumed that, in each row,
   ** the row heading will be printed in a field of width 5, followed by the
   ** DELIMITER character, followed by a sequence of square roots, each
   ** occupying a field of width 6 with two places after the decimal point.
   */
   private static void printColumnHeadings() {
      System.out.print(" Num " + DELIMITER);
      for (int i = 0; i < 10; i = i+1) {
         System.out.printf("%5.1f ", 0.1 * i);
      }
      System.out.println();
      System.out.print("-----" + DELIMITER);
      for (int i = 0; i < 10; i = i + 1) {
         System.out.print("------");
      }
      System.out.println();
   }
}
