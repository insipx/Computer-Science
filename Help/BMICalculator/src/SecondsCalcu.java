import java.util.Scanner;

/** Java application that calculates body mass index (BMI).
**  The user is prompted to enter (whole number) values for height and 
**  weight on the keyboard.
**
**  The formula is BMI = (703 * w) / (h * h), where w is weight in
**  pounds and h is height in inches.
**
**  This is a variation of the similarly-named program found in
**  Chapter 2 of the Reges & Stepp textbook.
**/

class SecondsCalculator {

   // Create a Scanner object that can read input entered on the keyboard.
   private static Scanner keyboard = new Scanner(System.in);

   public static void main(String[] args) {

      // symbolic constant declarations:
      final double HOURS_PER_DAY = 24;
      final int MINUTES_PER_HOUR = 60;

      // variable declarations:
      int days;    // measured in inches (input)
      int hours;    // measured in pounds (input)
      int minutes; // measured in minutes (input)
      double seconds;    // body mass index    (output)
 
       
      // Prompt user to enter height and assign response to input variable
      days = getIntFromUser("Enter # of Days: ");

      // Prompt user to enter weight and assign response to input variable
      hours = getIntFromUser("Enter # of Hours: ");

      minutes = getIntFromUser("Enter the Minutes");


     
      // Calculate seconds
      double seconds_in_days = days * HOURS_PER_DAY * MINUTES_PER_HOUR * MINUTES_PER_HOUR;





      // Translate height into feet and inches.

      // Print message that reports the height, weight, and the resulting BMI.
      System.out.print(days + " days, " + hours + " hours, and " + minutes + " equals " + " SECONDS HERE " +"seconds");

   }


   /* Returns the int value corresponding to what is entered on the keyboard
   ** in response to the specified String ('prompt', which is intended to be
   ** a prompt to the user).  If what is entered on the keyboard cannot be
   ** recognized as a numeral describing a whole number, an exception is thrown.
   */
   private static int getIntFromUser(String prompt)
   {
      System.out.print(prompt);    // Prompt user to enter input value
      return keyboard.nextInt();   // Read and return user's response
   }

}
