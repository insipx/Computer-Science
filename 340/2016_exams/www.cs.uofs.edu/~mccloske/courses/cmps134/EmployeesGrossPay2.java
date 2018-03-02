import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* EmployeesGrossPay2.java
** Java application that reports the gross pay for each employee for which
** data is present in a specified data file.  Its main purpose is to illustrate
** the use of using a Scanner object in doing "file processing", as discussed
** in Chapter 6 of Reges & Stepp's textbook.
**
** This application differs from its predecessor (having same name, but without
** the '2') in that this one is designed for a data file with a slightly
** different format that makes things a little bit more difficult.
** Specifically, employee records no longer include the "number of work 
** periods" field.  Thus, this application must make use of the fact that
** each employee record occupies a separate line of the file.
**
** The name of the data file is provided via a command line argument (what
** jGrasp calls a "run argument"), but if no such argument is provided, a 
** default name is used.
**
** The data file is assumed to be such that each line describes the data
** pertaining to one employee, including 
**
** --an employee ID (an integer, but not representing any quantity, so we
**   treat is as a String),
** --a name (necessarily a single token, meaning that no spaces may appear
**   in it),
** --an hourly wage (real number),
** --a sequence of hours-worked values, one for each work period (double).
**
** Here is a sample data file to illustrate (where the column headings are 
** not part of the file and "#p" means "number of work periods"):
** 
**   ID   Name   wage      hours worked
** +-------------------------------------------+
** |6435 Smith   21.50 8.4 7.4 9.7 4.7 10.9    |
** |0375 Jones   16.0  8.0 7.5 8.5 2.5 9.3 8.1 |
** |4986 Thomas  15.75 8.4 4.6 10.3            |
** +-------------------------------------------+
*/

public class EmployeesGrossPay2 {

   /* Note: It is necessary to include the "throws" clause in the method heading
   ** because FileNotFoundException is a "checked" exception.
   */
   public static void main(String[] args) throws FileNotFoundException
   {
      // Establish the name of the input file.
      String DEFAULT_FILE_NAME = "employees2.txt";
      String fileName;
      if (args.length != 0)  // name of input file is args[0]
         { fileName = args[0]; }
      else
         { fileName = DEFAULT_FILE_NAME; }
    
      // Create a Scanner object that can read from the input file.
      Scanner input = new Scanner(new File(fileName));

      // Process the records in the input file.
      while (input.hasNext()) {
         processEmployee(input);
      }
      System.out.println("\nProgram terminating.");
   }

   /* Computes and reports the gross pay of a single employee. 
   ** In doing so, it consumes the current record/line of the input data.
   */
   private static void processEmployee(Scanner in)
   {
      String emplStr = in.nextLine();           // Read an employee record into a String
      Scanner emplRec = new Scanner(emplStr);   // Make a Scanner that reads from that String
      String emplID = emplRec.next();           // Read employee's ID
      String emplName = emplRec.next();         // Read employee's name
      double hourlyWage = emplRec.nextDouble(); // Read employee's hourly wage

      double hoursWorked = 0;
      // Read hours worked for each work period and compute the total.
      while (emplRec.hasNext()) {
         hoursWorked = hoursWorked + emplRec.nextDouble();
      }

      // Having computed total hours worked, compute the employee's gross pay.
      double grossPay = grossPay(hourlyWage, hoursWorked);

      // Print the employee's name and gross pay.
      System.out.printf("%s: %5.2f\n", emplName, grossPay); 
   }

   /* Given an employee's hours worked and hourly wage, returns the
   ** corresponding gross pay.  
   */
   private static double grossPay(double hours, double wage) {
      final double OT_HOURS_THRESHOLD = 40.0;
      final double OT_WAGE_RATIO = 1.5;
      double regularHours, overtimeHours;
      if (hours > OT_HOURS_THRESHOLD) { 
         regularHours = OT_HOURS_THRESHOLD;
         overtimeHours = hours - OT_HOURS_THRESHOLD;
      }
      else { 
         regularHours = hours;
         overtimeHours = 0;
      }
      return (regularHours + (OT_WAGE_RATIO * overtimeHours)) * wage;
   }

}
