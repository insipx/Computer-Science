import java.util.Scanner;

/** Java application that computes an employee's gross pay, including
**  overtime pay, if applicable.  The computation is done in five 
**  (slightly) different ways (using five different methods) in order
**  to illustrate the use of if-else statements.
*/

public class GrossPayApp {

   // Hours worked beyond this threshold are overtime hours
   private static final double OVERTIME_HOURS_THRESHOLD = 40.0;

   // Hourly wage during overtime hours is multiplied by this
   private static final double OVERTIME_WAGE_RATIO = 1.5;

   public static void main(String[] args)
   {
      // Establish a Scanner that reads from the keyboard.
      Scanner input = new Scanner(System.in);

      // Prompt user to enter hours worked and read the response.
      System.out.print("Enter employee's # of hours worked: ");
      double hrsWrk = input.nextDouble();

      // Prompt user to enter hourly wage and read the response.
      System.out.print("Enter employee's hourly wage: ");
      double hrlyWage = input.nextDouble();

      // Compute gross pay using five different methods and report each
      // one's result.
      System.out.println("According to our five methods, " +
                         "the employee's gross pay is");
      System.out.println("grossPay1() says " + grossPay1(hrsWrk, hrlyWage));
      System.out.println("grossPay2() says " + grossPay2(hrsWrk, hrlyWage));
      System.out.println("grossPay3() says " + grossPay3(hrsWrk, hrlyWage));
      System.out.println("grossPay4() says " + grossPay4(hrsWrk, hrlyWage));
      System.out.println("grossPay5() says " + grossPay5(hrsWrk, hrlyWage));
   }

   /** Computes (and returns) gross pay based upon the specified 
   **  hours worked and hourly wage.
   */
   public static double grossPay1(double hoursWorked, double hourlyWage)
   {
      double result;
      if (hoursWorked > OVERTIME_HOURS_THRESHOLD)
      {
         double regularHours = OVERTIME_HOURS_THRESHOLD;
         double overtimeHours = hoursWorked - OVERTIME_HOURS_THRESHOLD;
         double overtimeHourlyWage = OVERTIME_WAGE_RATIO * hourlyWage;
         result = regularHours * hourlyWage + 
                  overtimeHours * overtimeHourlyWage;
      }
      else {
         result = hoursWorked * hourlyWage;
      }
      return result;
   }

   /** (Alternative to above method)
   **  Computes (and returns) gross pay based upon the specified 
   **  hours worked and hourly wage.
   */
   public static double grossPay2(double hoursWorked, double hourlyWage)
   {
      double regularPay, overtimePay;

      if (hoursWorked > OVERTIME_HOURS_THRESHOLD)
      {
         regularPay = OVERTIME_HOURS_THRESHOLD * hourlyWage;
         double overtimeHours = hoursWorked - OVERTIME_HOURS_THRESHOLD;
         double overtimeHourlyWage = OVERTIME_WAGE_RATIO * hourlyWage;
         overtimePay = overtimeHours * overtimeHourlyWage;
      }
      else {
         regularPay = hoursWorked * hourlyWage;
         overtimePay = 0.0;
      }
      return regularPay + overtimePay;
   }

   /** (Yet another alternative to above method)
   **  Computes (and returns) gross pay based upon the specified 
   **  hours worked and hourly wage.
   */
   public static double grossPay3(double hoursWorked, double hourlyWage)
   {
      double regularHours, overtimeHours;
      double overtimeHourlyWage = OVERTIME_WAGE_RATIO * hourlyWage;

      if (hoursWorked > OVERTIME_HOURS_THRESHOLD)
      {
         regularHours = OVERTIME_HOURS_THRESHOLD;
         overtimeHours = hoursWorked - OVERTIME_HOURS_THRESHOLD;
      }
      else {
         regularHours = hoursWorked;
         overtimeHours = 0.0;
      }
       
      double regularPay = regularHours * hourlyWage;
      double overtimePay = overtimeHours * overtimeHourlyWage;

      return regularPay + overtimePay;
   }

   /** (Yet another alternative to above method)
   **  Computes (and returns) gross pay based upon the specified 
   **  hours worked and hourly wage.  In this version, 'regularPay'
   **  refers to pay at normal wage for all hours worked, and 
   **  'overtimePay' refers to the extra pay earned during overtime hours.
   */
   public static double grossPay4(double hoursWorked, double hourlyWage)
   {
      double regularPay = hoursWorked * hourlyWage;
      double overtimePay = 0.0;

      if (hoursWorked > OVERTIME_HOURS_THRESHOLD)
      {
         double overtimeHours = hoursWorked - OVERTIME_HOURS_THRESHOLD;
         double overtimeHourlyWage = (OVERTIME_WAGE_RATIO - 1.0) * hourlyWage;
         overtimePay = overtimeHourlyWage * overtimeHours;
      }
      return regularPay + overtimePay;
   }

   /** (Yet another alternative to above method)
   **  Computes (and returns) gross pay based upon the specified 
   **  hours worked and hourly wage.  In this version, the use of
   **  if-else statements is hidden in the calls to the max() and min()
   **  methods of the java.lang.Math class.
   */
   public static double grossPay5(double hoursWorked, double hourlyWage)
   {
      double regularHours = Math.min(hoursWorked, OVERTIME_HOURS_THRESHOLD);
      double overtimeHours = hoursWorked - regularHours;
      double overtimeWage = OVERTIME_WAGE_RATIO * hourlyWage;
      return (regularHours * hourlyWage) + (overtimeHours * overtimeWage);
   }

}
