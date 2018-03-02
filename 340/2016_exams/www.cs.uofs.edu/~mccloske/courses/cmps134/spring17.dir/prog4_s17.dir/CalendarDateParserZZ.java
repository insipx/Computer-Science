/* CalendarDateParser.java
** Author: R. McCloskey and < student's name >
** Date: March 2017
** Assisted by: ...
** Known flaws: ...
**
** Java class containing static methods by which to translate calendar
** dates of any of several different formats into the M/D/Y format.
** Examples of dates in the M/D/Y format, as well as the other formats
** supported here, follow:
**
**    Format       Example 1          Example 2          Example 3
** +-----------+----------------+-------------------+-----------------+
** | M/D/Y     | "7/14/1975"    | "12/4/2049"       | "3/15/60"       |
** | Y_MONTH_D | "1975 JULY 14" | "2049 DECEMBER 4" | "60 MARCH 15"   |
** | Y-M-D     | "1975-07-14"   | "2049-12-04"      | "0060-03-15"    |
** | DMonY     | "14Jul1975"    | "4Dec2049"        | "15Mar60"       |
** +-----------+----------------+-------------------+-----------------+
**
** Only years in the range 0..9999 are supported.  (Technically, there was
** no year 0, but that is aside from the fact!)
**
** Notice that the Y-M-D format requires the year component to be four
** digits in length and both the day and month components to be two digits
** in length, padded with leading zeros if necessary.  In the remaining
** formats, leading zeros are optional (but not recommended).
*/

public class CalendarDateParser {

   // Invalid dates get translated to this.
   public static final String INVALID_DATE = "0/0/0";


   /* Given a calendar date purported to be in the M/D/Y format, returns 
   ** the corresponding date as expressed in the canonical M/D/Y format 
   ** ("canonical" meaning that no component has leading zeros).
   ** However, if the given string is not a syntactically and semantically 
   ** valid calendar date in the M/D/Y format, the INVALID_DATE string 
   ** is returned.
   */
   public static String translateFromMDY(String date)
   {
      int yearNum, monthNum, dayNum;
      try {
         // Extract from 'date' the substrings describing year, month, and
         // day, and translate each one to a value of type int.
         String yearStr = CalendarDateLexer.yearFromMDY(date);
         yearNum = Integer.parseInt(yearStr);
         String monthStr = CalendarDateLexer.monthFromMDY(date);
         monthNum = Integer.parseInt(monthStr);
         String dayStr = CalendarDateLexer.dayFromMDY(date);
         dayNum = Integer.parseInt(dayStr);
      }
      catch (Exception e) {
         // If execution of any of the statements in the try clause causes 
         // an exception to be thrown, the given calendar date must be 
         // invalid, so return the String denoting an invalid date.
         return INVALID_DATE;
      }

      // If execution gets this far, 'date' is at least syntactically valid.
      // Now call the method that checks for semantic validity and performs
      // the translation to canonical M/D/Y, and return its result.
      return dateInMDY(yearNum, monthNum, dayNum); 
   }


   /* Given a calendar date purported to be in the Y_MONTH_D format, returns
   ** the corresponding calendar date as expressed in canonical M/D/Y format.
   ** However, if the given date is not a syntactically and semantically 
   ** valid calendar date in the Y_MONTH_D format, the INVALID_DATE string is
   ** returned. 
   */
   public static String translateFromY_MONTH_D(String date)
   {
      int yearNum, monthNum, dayNum;
      String monthStr;
      try {

         // MISSING CODE HERE

      }
      catch (Exception e) {
         return INVALID_DATE;
      }

      // MISSING CODE HERE

      return "";   //Replace this line.
   }


   /* Given a calendar date purported to be in the Y-M-D format, returns 
   ** the corresponding calendar date as expressed in canonical M/D/Y format.
   ** However, if the given date is not a syntactically and semantically 
   ** valid calendar date in the Y-M-D format, the INVALID_DATE string is
   ** returned. 
   */
   public static String translateFromYMD(String date) {
      String yearStr, monthStr, dayStr;
      int yearNum, monthNum, dayNum;
      try {

         // MISSING CODE HERE

      }
      catch (Exception e) {
         return INVALID_DATE;
      }

      // MISSING CODE HERE

      return "";   // Replace this line.
   }


   /* Given a calendar date purported to be in the DMonY format, returns 
   ** the corresponding calendar date as expressed in canonical M/D/Y format.
   ** However, if the given date is not a syntactically and semantically 
   ** valid calendar date in the DMonY format, the INVALID_DATE string is
   ** returned. 
   */
   public static String translateFromDMonY(String date)
   {
      int yearNum, monthNum, dayNum;
      String monthAbbrev;
      try {

         // MISSING CODE HERE

      }
      catch (Exception e) {
         return INVALID_DATE;
      }

      // MISSING CODE HERE

      return "";   // Replace this line
   }


   // ---------------
   // private methods
   // ---------------

   
   /* Given integers representing a year, month, and day, returns the
   ** corresponding calendar date in canonical M/D/Y format.  However, 
   ** if the given (year, month, day) combination is semantically invalid
   ** (meaning that the calendar date it describes is non-existent, such
   ** as February 35, 1974), the INVALID_DATE String is returned.
   */
   private static String dateInMDY(int year, int month, int day)
   {
      if (isValidYearMonthDay(year, month, day))
         { return month + "/" + day + "/" + year; }
      else
         { return INVALID_DATE; }
   }


   /* Given the name of a month, returns the corresponding number, 1 for 
   ** January, 2 for February, etc.  The given month name need not follow
   ** any particular standard for capitalization.  That is, for example,
   ** any of the following Strings should be recognized as representing the
   ** month of May: "MAY", "MAy", "MaY", "May", "mAY", "mAy", "maY", "may".
   ** If the given String does not match any month name, returns -1.
   */
   private static int monthNameToNum(String monthName)
   {
      return -1;   // STUB!!
   }

   /* Given the abbreviation of the name of a month (i.e., the prefix of length
   ** three), returns the corresponding number, 1 for January, 2 for February,
   ** etc.  The given abbreviation need not follow any particular standard for
   ** capitalization.  That is, for example, any of the following Strings 
   ** should be recognized as representing the month of April: "APR", "APr", 
   ** "ApR", "Apr", "aPR", "aPr", "apR", "apr".
   ** If the given String does not match any month abbreviation, returns -1.
   */
   private static int monthAbbrevToNum(String monthAbbrev)
   {
      return -1;   // STUB!!
   }


   /* Returns true or false according to whether or not, respectively,
   ** the given combination of year, month, and day describes an actual
   ** calendar date. 
   */
   private static boolean isValidYearMonthDay(int year, int month, int day)
   {
      return false;  // STUB!!
   }

   /* Returns true if the given year number is in the interval [0,9999]
   ** false otherwise.
   */
   private static boolean isValidYear(int year) {
      return 0 <= year  &&  year <= 9999;
   }


   /* Returns true if the given month number is in the interval [1,12],
   ** false otherwise.
   */
   private static boolean isValidMonth(int monthNum) {
      return 1 <= monthNum  &&  monthNum <= 12;
   }

   /* Given integers indicating a year and a month (the latter assumed
   ** to be in the interval [1,12]), returns the number of days in
   ** that month occurring in that year.
   */
   private static int daysInMonth(int year, int month)
   {
      return 31;  // STUB!!
   }

   /* Returns true if the given year is a leap year, false otherwise.
   ** A year is a leap year if either it is divisible by 400 or else
   ** it is both divisible by 4 but not by 100.
   */
   private static boolean isLeapYear(int year) {
      return year % 400 == 0  ||  (year % 100 != 0  &&  year % 4 == 0);
   }

}
