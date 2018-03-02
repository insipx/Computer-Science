/* Lengths.java
** CMPS 034L - Spring 2017
** Author: P.M.J., R.W.M.,
** Students: ...
**
** Java application that prompts for and reads a single positive integer
** representing a length as measured in inches.  The program then expresses
** that length in two other ways: 
** (1) in feet (a real number), and
** (2) in "full feet" and "residual inches" (both integers)
**
** For example, 45 inches translates into 
** (1) 3.75 feet and 
** (2) 3 full feet and 9 residual inches.
*/

import java.util.Scanner;
public class Lengths {
 
   // Scanner object via which input data is read from the keyboard.
   public static Scanner keyboard = new Scanner(System.in);

   // Global input and output variables.
   static int inches;         // (input) length measured in inches 
   static double feet;        // (output) same length measured in feet 
   static int fullFeet;       // (output) # of full feet in that length
   static int residualInches; // (output) # inches left over
   

   public static void main(String[] args)
   {
      // Prompt user to enter a length measured in inches.
      System.out.print("Enter length in inches:>");

      // Read user's response and store it in the input variable
      inches = keyboard.nextInt();
     
      // Call method to translate the given length to feet and 
      // report the result.
      // CODE MISSING HERE!!
      
      // Call methods to compute # full feet and # residual inches in 
      // the given length and report the results.
      // CODE MISSING HERE!!
 
   }


   /* Computes the number of feet corresponding to the length represented
   ** by the global variable 'inches' and stores the result in the (global)
   ** variable 'feet'.  Example: 45 inches translates into 3.75 feet.
   */
   public static void computeFeet() {
      // CODE MISSING HERE!
   } 
   
   /* Computes the number of full feet contained in the length represented
   ** by the variable 'inches' and stores the result in the global variable
   ** 'fullFeet'.  Example: There are 3 full feet in 45 inches.
   */
   public static void computeFullFeet() {
      // CODE MISSING HERE!
   } 
   
   /* Computes the number of residual inches left over after removing
   ** all full feet from the length represented by the variable 'inches'
   ** and stores the result in the global variable 'residualInches'.
   ** Example: In 45 inches there are 9 residual inches left over after 
   ** removing the three full feet.
   */
   public static void computeResidualInches() {
      // CODE MISSING HERE!
   } 
   
}
