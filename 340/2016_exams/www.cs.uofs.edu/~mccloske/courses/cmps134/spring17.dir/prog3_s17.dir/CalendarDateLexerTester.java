import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/* Java application having as its purpose to test the methods in the
** CalendarDateLexer class.
** If a command line argument is provided, it is taken to be the name of
** the input file.  Otherwise, the user must provide, in response to a
** prompt, the name of an input file.
** Each line of input data is expected to include a calendar date format
** identifier, followed by at least one space, followed by a calendar
** date of that format.  
** As an example, here is input data that contains exactly one instance of
** a date of each format:
**
** Y_MONTH_D 2012 FEBRUARY 5
** M/D/Y 2/5/2012 
** Y-M-D 2012/02/05
** DMonY 5Feb2012
*/

public class CalendarDateLexerTester {

   // Symbolic constants storing the various calendar date format identifiers.
   private static final String Y_MONTH_D = "Y_MONTH_D";
   private static final String MDY = "M/D/Y";
   private static final String YMD = "Y-M-D";
   private static final String DMonY = "DMonY";

   private static Scanner input;

   public static void main(String[] args) throws FileNotFoundException
   {
      // Create a Scanner that is set up to read from the input file, the 
      // name of which is provided either by the command line argument or,
      // absent that, by the user in response to a prompt.
      String fileName;
      if (args.length > 0)    // name of input file is args[0]
         { fileName = args[0]; }
      else 
         { fileName = getUserResponse("Enter name of input file: "); }

      input = new Scanner(new File(fileName));

      // Now process the input data, each line of which should contain a
      // calendar date identifier followed by a date in the specified format.
      while (input.hasNext()) {
         try {
            String formatID = input.next();  // read date format identifier
            String date = input.nextLine().trim();  // read date on rest of line

            // Now parse the date and report the results.
            if (formatID.equals(Y_MONTH_D)) { parseY_MONTH_D(date); }
            else if (formatID.equals(MDY)) { parseMDY(date); }
            else if (formatID.equals(YMD)) { parseYMD(date); }
            else if (formatID.equals(DMonY)) { parseDMonY(date); }
            else { System.out.println("Invalid format identifier: " + formatID); }
         }
         catch (Exception e) {
            e.printStackTrace(System.out);
         }
         System.out.println();
      }
   }


   /* Given a calendar date assumed to be in Y_MONTH_D format, reports
   ** each of its three components (year, month, and day).
   */
   public static void parseY_MONTH_D(String date) {
      System.out.println(Y_MONTH_D + ": " + date);
      System.out.println("  Year: " + CalendarDateLexer.yearFromY_MONTH_D(date));
      System.out.println("  Month: " + CalendarDateLexer.monthFromY_MONTH_D(date));
      System.out.println("  Day: " + CalendarDateLexer.dayFromY_MONTH_D(date));
   }

   /* Given a calendar date assumed to be in M/D/Y format, reports
   ** each of its three components (year, month, and day).
   */
   public static void parseMDY(String date) {
      System.out.println(MDY + ": " + date);
      System.out.println("  Year: " + CalendarDateLexer.yearFromMDY(date));
      System.out.println("  Month: " + CalendarDateLexer.monthFromMDY(date));
      System.out.println("  Day: " + CalendarDateLexer.dayFromMDY(date));
   }

   /* Given a calendar date assumed to be in Y-M-D format, reports
   ** each of its three components (year, month, and day).
   */
   public static void parseYMD(String date) {
      System.out.println(YMD + ": " + date);
      System.out.println("  Year: " + CalendarDateLexer.yearFromYMD(date));
      System.out.println("  Month: " + CalendarDateLexer.monthFromYMD(date));
      System.out.println("  Day: " + CalendarDateLexer.dayFromYMD(date));
   }

   /* Given a calendar date assumed to be in DMonY format, reports
   ** each of its three components (year, month, and day).
   */
   public static void parseDMonY(String date) {
      System.out.println(DMonY + ": " + date);
      System.out.println("  Year: " + CalendarDateLexer.yearFromDMonY(date));
      System.out.println("  Month: " + CalendarDateLexer.monthFromDMonY(date));
      System.out.println("  Day: " + CalendarDateLexer.dayFromDMonY(date));
   }

   /* Prints the specified prompt and returns the user's response.
   */
   private static String getUserResponse(String prompt) {
      Scanner keyboard = new Scanner(System.in);
      System.out.print(prompt);
      return keyboard.nextLine();
   }

}
