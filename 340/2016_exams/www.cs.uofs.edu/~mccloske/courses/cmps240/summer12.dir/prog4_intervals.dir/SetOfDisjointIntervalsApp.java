import java.util.Scanner;

/** Used for testing interfaces/classes that implement the concept of a set
**  of disjoint intervals.
*/

public class SetOfDisjointIntervalsApp {

   private static Scanner input = new Scanner(System.in);      

   public static void main(String[] args)
   {
      SetOfDisjointIntervals set = new SetOfDisjointIntervalsViaArrayList();

      while (true)
      {
         println("\nSet of intervals is now");
         print(set.toString());
         println("\n");
         print("(A)dd or (S)ubtract an interval:");
         String response = input.nextLine();
         if (response.length() == 0) {
            println("Respond with either A or S, moron");
         }
         else {
            char r = response.charAt(0);
            if (r == 'A' || r == 'a') {
               Interval v = getInterval();
               set.add(v);
            }
            else if (r == 'S' || r == 's') {
               Interval v = getInterval();
               set.subtract(v);
            }
            else {
               println("Respond with either A or S, you idiot");
            }
         }
      }
   }

   private static Interval getInterval()
   {
      print("Enter lower bound: ");
      int low = input.nextInt();
      input.nextLine();
      print("Enter upper bound: ");
      int high = input.nextInt();
      input.nextLine();
      return new Interval(low, high);
   }


   private static void print(String s) { System.out.print(s); }
   private static void println(String s) { System.out.println(s); }

}
