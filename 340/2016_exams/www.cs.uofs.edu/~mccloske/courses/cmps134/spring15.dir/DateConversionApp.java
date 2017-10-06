import java.util.Scanner;    // A Scanner object will be employed

/* Java application that reads (from the keyboard) calendar dates in
** traditional format (e.g., "April 13, 1978") and converts them to
** a Year-MON-Day format like this: "1978-APR-13".  
** Input data may have leading or trailing spaces, but otherwise must
** be formatted precisely, with exactly one space between the month
** name and the day number and a second space between the comma and
** the year.
** The program will repeatedly prompt the user to enter dates until
** such time as s/he enters a string containing only spaces.
*/
public class DateConversionApp {

   private static Scanner keyboard = new Scanner(System.in);
   
   public static void main(String[] args)
   {
      String prompt = "Enter calendar date (in traditional format): ";

      // Prompt for, read, and trim the user's first calendar date input.
      String tradDateStr = getStrResponse(prompt).trim();

      while (!tradDateStr.equals(""))
      {
         // Display the user's date (trimmed of leading/trailing spaces).
         System.out.print(tradDateStr + " translates to ");

         // Perform conversion to Year-MON-Day format and display the result.
         String dateStrYMD = tradDateToYMD(tradDateStr);
         System.out.println(dateStrYMD + '\n');

         // Prompt for, read, and trim the next input date.
         tradDateStr = getStrResponse(prompt).trim();
      }
      System.out.println("Goodbye.");
   }


   /* Provided (via the formal parameter) with a calendar date in the
   ** traditional format (e.g., "January 4, 2002") (with no leading or
   ** trailing spaces), returns the corresponding date in Year-MON-Day 
   ** format like this: "2002-JAN-4".  (MON refers to the fully-capitalized 
   ** month abbreviation, which is taken to be the first three characters
   ** of the month name.)
   */
   public static String tradDateToYMD(String tradDate)
   {
      final char SPACE = ' ';
      final char COMMA = ',';
      final char HYPHEN = '-';

      // In a traditional date, the year is what follows the last space,
      // so find its position and extract the suffix following it.
      int posOfLastSpace = tradDate.lastIndexOf(SPACE);
      String yearStr = tradDate.substring(posOfLastSpace+1);

      // In a traditional date, the month name occurs at the beginning, so 
      // its (capitalized) abbreviation is obtained by taking the prefix of 
      // length three and applying toUpperCase().
      String monthAbbrev = tradDate.substring(0,3).toUpperCase();

      // In a traditional date, the day number is what falls in between
      // the first space and the comma, so extract that substring.
      int posOfFirstSpace = tradDate.indexOf(SPACE);
      int posOfComma = tradDate.indexOf(COMMA);
      String dayStr = tradDate.substring(posOfFirstSpace+1, posOfComma);
      
      // Having extracted the three substrings, it now suffices to
      // concatenate them, placing hyphens between the three parts,
      // and then to return the resulting String.
      return yearStr + HYPHEN + monthAbbrev + HYPHEN + dayStr;
   }


   /* Displays the prompt specified by the parameter, reads the response
   ** from the keyboard Scanner object, and returns that response.
   */
   private static String getStrResponse(String prompt)
   {
      System.out.print(prompt);
      return keyboard.nextLine();
   }
}
