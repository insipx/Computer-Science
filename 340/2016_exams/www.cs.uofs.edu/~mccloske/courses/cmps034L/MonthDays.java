/* MonthDays.java
** Solution for Lab #6, CMPS 034L, Spring 2017
**
*/
public class MonthDays {

   /* This main() method is here strictly for the purpose of testing the
   ** daysInMonth() methods below.
   */
   public static void main(String[] args) {
      for (int year = 1850; year <= 2050; year = year + 50) {
         for (int month = 0; month <= 13; month++) {
            try {
               System.out.println("Month " + month + " in year " + year +
                                  " has " + daysInMonth(month,year) + " days.");
            }
            catch (Exception e) {
               e.printStackTrace(System.out);
            }
         }
      }
   }


   /* Returns the number of days in the specified month (where 1 means
   ** January, 2 means February, etc.) in a non-leap year.
   ** Recall that February has 28 days and each of April, June, September,
   ** and November has 30.  Each of the rest has 31 days.
   ** If the specified month number is not in the appropriate range (1..12),
   ** an IllegalArgumentException is thrown.  (Throwing an exception was not 
   ** required by the lab problem, but is shown here to illustrate that
   ** capability.)
   */
   public static int daysInMonth(int monthNum) {
      int result;
      if (monthNum == 2) 
         { result = 28; }
      else if (monthNum == 4 || monthNum == 6 || monthNum == 9 || monthNum == 11)
         { result = 30; }
      else if (monthNum == 1 || monthNum == 3 || monthNum == 5 || monthNum == 7 ||
               monthNum == 8 || monthNum == 10 || monthNum == 12)
         { result = 31; }
      else {
         throw new IllegalArgumentException("monthNum (" + monthNum + 
                                            ") not in range 1..12"); 
      }

      return result; 
   }

   /* Returns the number of days in the specified month (where 1 means
   ** January, 2 means February, etc.) in the specified year. 
   */
   public static int daysInMonth(int monthNum, int yearNum) {
      int result;
      if (monthNum == 2  &&  isLeapYear(yearNum)) { 
         result = 29;
      }
      else {   
         result = daysInMonth(monthNum);    // the other method has the answer
      }
      return result;
   }

   /* Returns true if the given year is a leap year, false otherwise.
   ** A leap year is any that is either divisible by 400 OR that
   ** is divisible by 4 but not by 100.
   */
   private static boolean isLeapYear(int year) {
      return (year % 400 == 0) || (year % 100 != 0  &&  year % 4 == 0);
   }


}
