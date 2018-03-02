/* CalendarDateLexer.java
** Author: R. McCloskey, March 2017
** 
** Java class containing methods by which to identify component parts of 
** calendar dates of several different formats.
** The formats currently supported include (what we are here calling)
** Y_Month_D, M/D/Y, Y-M-D, and DMonY, examples of which follow:
**
**    Format       Example 1          Example 2          Example 3
** +-----------+----------------+-------------------+-----------------+
** | Y_MONTH_D | "1975 JULY 14" | "2049 DECEMBER 4" | "60 MARCH 15"   |
** | M/D/Y     | "7/14/1975"    | "12/4/2049"       | "3/15/60"       |
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
** formats, none of the components should include leading zeros.  
*/

public class CalendarDateLexer {

   // Symbolic constants for significant characters.
   private static final char SPACE = ' ';
   private static final char SLASH = '/';


   // ------------------------------------------------------
   // Methods by which to extract the year, month, and day 
   // components from a calendar date in Y_MONTH_D format.

   /* Given a calendar date in the Y_MONTH_D format, returns the
   ** substring indicating the year, which is the prefix preceding
   ** the first occurrence of a space.
   */
   public static String yearFromY_MONTH_D(String date) {
      int posOfFirstSpace = date.indexOf(SPACE);
      return date.substring(0, posOfFirstSpace);
   }

   /* Given a calendar date in the Y_MONTH_D format, returns the
   ** substring indicating the month, which is what lies strictly
   ** between the first and last occurrences of a space.
   */
   public static String monthFromY_MONTH_D(String date) {
      int posOfFirstSpace = date.indexOf(SPACE);
      int posOfLastSpace = date.lastIndexOf(SPACE);
      return date.substring(posOfFirstSpace+1, posOfLastSpace);
   }

   /* Given a calendar date in the Y_MONTH_D format, returns the
   ** substring indicating the day, which is the suffix following
   ** the last occurrence of a space.
   */
   public static String dayFromY_MONTH_D(String date) {
      int posOfLastSpace = date.lastIndexOf(SPACE);
      return date.substring(posOfLastSpace+1);
   }


   // ------------------------------------------------------
   // Methods by which to extract the year, month, and day 
   // components from a calendar date in M/D/Y format.

   /* Given a calendar date in the MDY format, returns the substring
   ** indicating the year, which is the suffix following the last 
   ** occurrence of a slash.
   */
   public static String yearFromMDY(String date) {
      int posOfLastSlash = date.lastIndexOf(SLASH);
      return date.substring(posOfLastSlash+1);
   }

   /* Given a calendar date in the MDY format, returns the
   ** substring indicating the month, which is the prefix 
   ** preceding the first occurrence of a slash.
   */
   public static String monthFromMDY(String date) {
      int posOfFirstSlash = date.indexOf(SLASH);
      return date.substring(0, posOfFirstSlash);
   }

   /* Given a calendar date in the MDY format, returns the
   ** substring indicating the day, which is what lies strictly 
   ** between the first and last occurrences of a slash.
   */
   public static String dayFromMDY(String date) {
      int posOfFirstSlash = date.indexOf(SLASH);
      int posOfLastSlash = date.lastIndexOf(SLASH);
      return date.substring(posOfFirstSlash+1, posOfLastSlash);
   }

   // ------------------------------------------------------
   // Methods by which to extract the year, month, and day 
   // components from a calendar date in Y-M-D format.

   /* Given a calendar date in the YMD format, returns the substring
   ** indicating the year, which is the prefix of length four.
   */
   public static String yearFromYMD(String date) {
      return date.substring(0,4);
   }

   /* Given a calendar date in the YMD format, returns the substring
   ** indicating the month, which is the substring of length two 
   ** beginning at position 5.
   */
   public static String monthFromYMD(String date) {
      return date.substring(5,7);
   }

   /* Given a calendar date in the YMD format, returns the substring
   ** indicating the day, which is the suffix beginning at position 8.
   */
   public static String dayFromYMD(String date) {
      return date.substring(8);
   }


   // ------------------------------------------------------
   // Methods by which to extract the year, month, and day 
   // components from a calendar date in DMonY format.

   /* Given a calendar date in the DMonY format, returns the substring
   ** indicating the year, which is the suffix following the last 
   ** occurrence of a letter.
   */
   public static String yearFromDMonY(String date) {
      int posOfLastLetter = lastIndexOfLetter(date);
      return date.substring(posOfLastLetter + 1);
   }

   /* Given a calendar date in the DMonY format, returns the substring
   ** indicating the month, which is the substring of length three 
   ** beginning with the first occurrence of a letter.
   */
   public static String monthFromDMonY(String date) {
      int posOfFirstLetter = indexOfLetter(date);
      return date.substring(posOfFirstLetter, posOfFirstLetter+3);
   }

   /* Given a calendar date in the DMonY format, returns the substring
   ** indicating the day, which is the prefix preceding the first 
   ** occurrence of a letter.
   */
   public static String dayFromDMonY(String date) {
      int posOfFirstLetter = indexOfLetter(date);
      return date.substring(0, posOfFirstLetter);
   }


   // ---------------
   // private methods


   /* Returns the position within the given String (s) of the first
   ** occurrence of a letter (i.e., one of 'a'..'z' or 'A'..'Z'), or
   ** s.length() if there are no such occurrences.
   */
   private static int indexOfLetter(String s) {
      final int S_LEN = s.length();
      int i;
      for (i = 0; i != S_LEN && !Character.isLetter(s.charAt(i)); i = i+1) { 
         // do nothing (all the work is in incrementing i)
      }
      return i;
   }

   /* Returns the position within the given String (s) of the last
   ** occurrence of a letter (i.e., one of 'a'..'z' or 'A'..'Z'), or
   ** -1 if there are no such occurrences.
   */
   private static int lastIndexOfLetter(String s) {
      int i;
      for (i = s.length()-1; i != -1 && !Character.isLetter(s.charAt(i)); i--) {
         // do nothing (all the work is in decrementing i)
      }
      return i;
   }

}

