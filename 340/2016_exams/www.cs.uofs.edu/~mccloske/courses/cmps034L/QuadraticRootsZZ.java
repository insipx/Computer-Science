/* Java program that computes the roots of a quadratic polynomial.
** It is for Lab #5 of CMPS 034L.
*/
public class QuadraticRoots {

   public static void main(String[] args) {
      printRoots(1, -7, 12);       // roots are 4.0 and 3.0
      printRoots(1, 3, 2);         // roots are -1.0 and -2.0
      printRoots(2, 7, -15);       // roots are 1.5 and -5.0
      printRoots(10, -21, 8);      // roots are 1.6 and 0.5
      printRoots(1, -2, 1);        // roots are 1.0 and 1.0
      printRoots(-12.5,15,0);      // roots are 0 and 1.2
      printRoots(-3.7, 4.1, 6.25); // roots are -0.859 1.967 (rounded to nearest thousandth)
   }


   /* Displays a message identifying the roots of the degree-2 polynomial
   ** having the specified coefficients.
   */
   private static void printRoots(double a, double b, double c) {
      double firstRoot = root1(a,b,c);
      double secondRoot = root2(a,b,c);
      System.out.println("Roots of " + a + "x^2 + " + b + "x + " + c +
                         " are " + firstRoot + " and " + secondRoot);
   }

   /* Given the coefficients a, b, and c (provided via parameters),
   ** returns the "first" root of the degree-2 polynomial ax^2 + bx + c, 
   ** which is (-b + square root of discriminant) / 2a, where the 
   ** discriminant is b^2 - 4ac.
   ** It is assumed that the discriminant is nonnegative.  Otherwise,
   ** an exception will be thrown.
   */
   public static double root1(double a, double b, double c) {
      return 0.0;  // STUB!!
   }

   /* Given the coefficients a, b, and c (provided via parameters),
   ** returns the "second" root of the degree-2 polynomial ax^2 + bx + c, 
   ** which is (-b - square root of discriminant) / 2a, where the 
   ** discriminant is b^2 - 4ac.
   ** It is assumed that the discriminant is nonnegative.  Otherwise,
   ** an exception will be thrown.
   */
   public static double root2(double a, double b, double c) {
      return 0.0;  // STUB!!
   }

   /* Given the coefficients a, b, and c (provided via parameters),
   ** returns the "discriminant" of the degree-2 polynomial ax^2 + bx + c,
   ** which is b^2 - 4ac.
   */
   private static double discriminant(double a, double b, double c) {
      return 0.0;  // STUB!!
   }
}
