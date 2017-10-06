/* MonthDays.java
** For CMPS 034L

*/
public class MonthDays {

   /* This main() method is here strictly for the purpose of testing the
   ** (first) daysInMonth() method.
   */
   public static void main(String[] args) {
      for (int i=1; i <= 12; i = i+1) {
         System.out.println("Month " + i + " has " + daysInMonth(i) + " days.");
      }
   }


   /* Returns the number of days in the specified month (where 1 means
   ** January, 2 means February, etc.).  For simplicity, assume that
   ** this is not leap year.  Recall that February has 28 days and
   ** each of April, June, September, and November has 30.  The rest
   ** have 31 days.
   */
   public static int daysInMonth(int monthNum) {

      return 0;  // STUB!!

   }

   /* Returns the number of days in the specified month (where 1 means
   ** January, 2 means February, etc.) in the specified year. 
   ** A leap year is any that is either divisible by 400 OR that
   ** is divisible by 4 but not by 100.
   */
   public static int daysInMonth(int monthNum, int yearNum) {

      return 0;  // STUB!!

   }


}
