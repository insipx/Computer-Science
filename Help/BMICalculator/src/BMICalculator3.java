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
*/

public class BMICalculator3 {

   // Create a Scanner object that can read input entered on the keyboard.
   private static Scanner keyboard = new Scanner(System.in);

   public static void main(String[] args) {

      // symbolic constant declarations:
      final double BMI_MULTIPLIER = 703.0;
      final int INCHES_PER_FOOT = 12;

      // variable declarations:
      int height;    // measured in inches (input)
      int weight;    // measured in pounds (input)
      double bmi;    // body mass index    (output)
 
       
      // Prompt user to enter height and assign response to input variable
      height = getIntFromUser("Enter height in inches: ");

      // Prompt user to enter weight and assign response to input variable
      weight = getIntFromUser("Enter weight in pounds: ");

      
      days = getIntFromUser("Enter Number of Days: ");
      
      days = (days /24) /60 / 60;  
      
      System.out.println("Number of days" + days);
      
      
      
      // Calculate BMI 
      bmi = (BMI_MULTIPLIER * weight) / (height * height);

      // Translate height into feet and inches.
      int heightFeet = height / INCHES_PER_FOOT;
      int heightInches = height % INCHES_PER_FOOT;

      // Print message that reports the height, weight, and the resulting BMI.
      System.out.print("Height of " + heightFeet + "' " + heightInches + "\" ");
      System.out.print("and weight of " + weight + " pounds ");
      System.out.println("yields BMI " + bmi);
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
