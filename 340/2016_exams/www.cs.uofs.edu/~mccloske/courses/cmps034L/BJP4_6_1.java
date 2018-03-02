import java.util.Scanner;

/* Java application to be used by students for submitting their solution to
** BJP4 Exercise 6.1 (boyGirl) on the Practice-It website.
*/

public class BJP4_6_1 {

   public static void main(String[] args)
   {
      String input1 = "Joe 234 Kathy 3 Bill -45 Helen 156";
      testCase(input1);

      String input2 = "Steve 3 Sylvia 7 Craig 14 Lisa 13 Brian 4 Charlotte 9 Jordan 6";
      testCase(input2);
   }

   /* Uses the specified String as a test case by which to test the
   ** boyGirl() method.  A Scanner object is created to read from the
   ** specified String and that Scanner is passed to the boyGirl() method.
   */
   private static void testCase(String inputStr) {
      String dashes = "------------------------------------------";
      Scanner input = new Scanner(inputStr);
      System.out.println("For input:");
      System.out.println(inputStr);
      System.out.println("result produced is");
      System.out.println(dashes);
      boyGirl(input);
      System.out.println(dashes);
      System.out.println();
   }

   /* Solution to BJP4 Exercise 6.1 (boyGirl)
   */
   public static void boyGirl(Scanner input) {

      // Your code goes here!!

   }

}
