/* Java program that prints a "cumulative" countdown from five, in words.
** As seen in Test #1, CMPS 134 Spring 2017 (Section 31)
** It is similar to sample solution provided for Prog. Assg. #1.
*/

public class CumulativeCountDownFromFive{

   public static void main(String[] args) {
      one(); two(); three(); four(); five();
   }


   public static void five() { System.out.print("Five, "); four(); }

   public static void four() { System.out.print("Four, "); three(); }

   public static void three() { System.out.print("Three, "); two(); }

   public static void two() { System.out.print("Two, "); one(); }

   public static void one() { System.out.println("One"); }

}
