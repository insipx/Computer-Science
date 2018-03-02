import java.util.Scanner;

/** Java application having as its purpose to test the Interval class.
**  Through menu-based interaction, the user can manipulate two intervals.
**  and apply operations upon them (e.g., determine whether they overlap,
**  compute their union, etc.)
*/

public class IntervalApp {

   private static Interval u, v;
   private static Scanner input = new Scanner(System.in);
   private static boolean finished;

   public static void main(String[] args)
   {
      finished = false;
      while (!finished)
      {
         printMenu();
         int response = input.nextInt();
         try {
            executeCommand(response);
         }
         catch (Exception e) {
            println(e);
         }
         println("");
      }
   }

   private static void executeCommand(int command)
   {
      if (command == 1) { println("u is " + u + " and v is " + v); }
      else if (command == 2) { u = makeInterval(); }
      else if (command == 3) { v = makeInterval(); }
      else if (command == 4) { 
         double x = getRealFromUser("Enter a real number: ");
         checkMembership(u,x); checkMembership(v,x);
      }
      else if (command == 5) { checkContainment(u,v); checkContainment(v,u); }
      else if (command == 6) { checkOverlap(u,v); checkOverlap(v,u); }
      else if (command == 7) { takeUnion(u,v); takeUnion(v,u); }
      else if (command == 8) { takeIntersection(u,v); takeIntersection(v,u); }
      else if (command == 9) { takeSubtraction(u,v); takeSubtraction(v,u); }
      else if (command == 10) { finished = true; }
      else { println("Invalid response; try again."); }
   }


   private static void printMenu()
   {
      print(" (1) Display u and v            ");
      println("(2) Assign new interval to u");
      print(" (3) Assign new interval to v   ");
      println("(4) Check for membership");
      print(" (5) Check for containment      ");
      println("(6) Check for overlap");
      print(" (7) Compute union of u and v   ");
      println("(8) Compute intersection of u and v");
      print(" (9) Take u-v and v-u           ");
      println("(10) Quit");
      print("\n>");
   }

   private static double getRealFromUser(String prompt) {
      print(prompt);
      return input.nextDouble();
   }

   private static Interval makeInterval()
   {
      double lower = getRealFromUser("Enter lower bound: ");
      double upper = getRealFromUser("Enter upper bound: ");
      return new Interval(lower, upper);
   }

   private static void checkMembership(Interval w, double z) 
   {
      print(z + " is ");
      if (!w.isMemberOf(z)) { print("NOT "); }
      println("a member of " + w);
   }

   private static void checkContainment(Interval y, Interval z) 
   {
      print(y);
      if (y.contains(z)) { print(" contains "); }
      else { print(" does NOT contain "); }
      println(z);
   }

   private static void checkOverlap(Interval y, Interval z) 
   {
      print(y);
      if (y.overlaps(z)) { print(" overlaps "); }
      else { print(" does NOT overlap "); }
      println(z);
   }


   private static void takeUnion(Interval y, Interval z)
   {
      print("The union of " + y + " and " + z + " is "); 
      println(y.unionSafe(z));
   }

   private static void takeIntersection(Interval y, Interval z)
   {
      print("The intersection of " + y + " and " + z + " is "); 
      println(y.intersectionSafe(z));
   }

   private static void takeSubtraction(Interval y, Interval z)
   {
      print(y + " - " + z + " = "); 
      Interval[] diff = y.subtractSafe(z);
      if (diff.length == 0) {
         println("NOTHING");
      } 
      else if (diff.length == 1) {
         println(diff[0]);
      }
      else if (diff.length == 2) {
         println(diff[0] + " + " + diff[1]);
      } 
   }


   private static void print(Object s) { System.out.print(s); }
   private static void println(Object s) { System.out.println(s); }
}
