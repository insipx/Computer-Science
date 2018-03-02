/* Java class containing several miscellaneous "functional" methods (i.e., 
** ones that return values).  The purpose is to illustrate the idea through
** the use of examples.
*/

public class BunchOfFuncMethods {


   // Returns the distance (on the number line) between two specified values.
   public static double distanceOneDim(double x, double y)
   {
      return Math.abs(y - x);
      // Alternative:
      // return Math.max(x,y) - Math.min(x,y);
   }

   // Returns the square of the specified value.
   public static double squareOf(double x) { return x*x; }

   /* Returns the distance between the two points described by the
   ** specified values.
   */
   public static double distanceTwoDim(double x1, double y1,
                                       double x2, double y2) {
      double xDelta = x1-x2;
      double yDelta = y1-y2;
      return Math.sqrt(squareOf(xDelta) + squareOf(yDelta));
      //alternative
      // return Math.sqrt(Math.pow(xDelta,2) + Math.pow(yDelta,2));
   }


   // Evaluates ax^2 + bx + c at the specified value of x.
   public static double applyQuadraticPoly(double a, double b, double c, double x)
   {
      return a*squareOf(x) + b*x + c;
   }
   
   /* Computes the gross pay earned by an employee who worked the specified
   ** number of hours at the specified wage, taking into account overtime
   ** pay. 
   */
   public static double grossPay(double hoursWorked, double hourlyWage)
   {
      double regularHours = Math.min(40.0, hoursWorked);
      double overtimeHours = Math.max(hoursWorked - 40.0, 0.0);
      return (regularHours * hourlyWage) + (1.5 * overtimeHours * hourlyWage);
   }

   /* This method generalizes the one above in that it accepts via parameters
   ** not only an employee's hours worked and hourly wage ('hoursWorked' and
   ** 'hourlyWage', respectively), but also the threshold # of hours at which
   ** overtime work begins ('otHoursThreshold') and the factor by which
   ** hourly wage is multiplied to obtain hourly wage during overtime work
   ** ('otWageFactor').
   */
   public static double grossPay(double hoursWorked, double hourlyWage,
                                 double otHoursThreshold, double otWageFactor)
   {
      double regularHours = Math.min(otHoursThreshold, hoursWorked);
      double overtimeHours = Math.max(hoursWorked - otHoursThreshold, 0.0);
      double regularPay = regularHours * hourlyWage;
      double overtimePay = otWageFactor * hourlyWage * overtimeHours;
      return regularPay + overtimePay;
   }


   /* Computes the sum of the integers in the range low..high.
   */
   public static int rangeSum(int low, int high) {
      int result = 0;
      for (int i=low; i <= high; i = i+1) {
         result = result + i;
      }
      return result;
   }

}
