import java.util.Scanner;

/** Used for testing interfaces/classes that implement the concept of a set
**  of disjoint intervals.  It is an improvement over the previous version
**  in that it is more comprehensive in the features that it can be used
**  to test.
*/

public class SetOfDisjointIntervalsApp2 {

   private static Scanner input = new Scanner(System.in);      
   private static SetOfDisjointIntervals p, r;
   private static boolean finished;

   public static void main(String[] args)
   {
      int response; 
      finished = false;
      p = emptySet();
      r = emptySet();

      while (!finished)
      {
         print("Press ENTER to display menu.");
         input.nextLine();
         printMenu();
         print(">");
         try {
            response = input.nextInt();
         }
         catch (Exception e) {
            println("** Enter an integer, moron! **");
            response = 1;
         }
         input.nextLine();

         try {
            executeCommand(response);
            println("");
         }
         catch (Exception e) {
            e.printStackTrace();
            println("");
         }

      }
   }

   private static void printMenu()
   {
      print(" (1) Display p                           ");
      println(" (2) Display r");
      print(" (3) Make p empty                        ");
      println(" (4) Make r empty");
      print(" (5) Get k-th interval of p              ");
      println(" (6) Get k-th interval of r");
      print(" (7) Compute sum of interval lengths     ");
      println(" (8) Check containment of a real number");
      print(" (9) Check containment of an interval    ");
      println("(10) Check containment between p and r");
      print("(11) Nearest before/after in p           ");
      println("(12) Nearest before/after in r");
      print("(13) Add an interval to p                ");
      println("(14) Add an interval to r");
      print("(15) Subtract an interval from p         ");
      println("(16) Subtract an interval from r");
      print("(17) Show union of p and r               ");
      println("(18) Show intersection of p and r");
      print("(19) Show p - r                          ");
      println("(20) Show r - p");
      print("(21) Quit");
      println("");
   }

   private static void executeCommand(int command)
   {
      if (command == 1) { displaySet(p, "p"); }
      else if (command == 2) { displaySet(r, "r"); }
      else if (command == 3) { p = emptySet(); }
      else if (command == 4) { r = emptySet(); }
      else if (command == 5) { fetchInterval(p, "p"); }
      else if (command == 6) { fetchInterval(r, "r"); }
      else if (command == 7) {
         println("Sum of lengths of intervals in p is " + p.sumOfLengths());
         println("Sum of lengths of intervals in r is " + r.sumOfLengths());
      }
      else if (command == 8) { checkContainmentOfNum(); }
      else if (command == 9) { checkContainmentOfInterval(); }
      else if (command == 10) { checkContainmentOfPR(); }
      else if (command == 11) { findNearest(p, "p"); }
      else if (command == 12) { findNearest(r, "r"); }
      else if (command == 13) { addInterval(p); displaySet(p, "p"); }
      else if (command == 14) { addInterval(r); displaySet(r, "r"); }
      else if (command == 15) { subtractInterval(p); displaySet(p, "p"); }
      else if (command == 16) { subtractInterval(r); displaySet(r, "r"); }
      else if (command == 17) { 
         println("Union of p and r is");
         print(p.union(r));
      }
      else if (command == 18) { 
         println("Intersection of p and r is");
         print(p.intersection(r));
      }
      else if (command == 19) { 
         println("p - r is");
         print(p.difference(r));
      }
      else if (command == 20) { 
         println("r - p is");
         print(r.difference(p));
      }
      else if (command == 21) {
         finished = true;
      }
      else {
         println("Must enter a number beteen 1 and 21, moron");
      }
   }

   private static void displaySet(SetOfDisjointIntervals s, String name)
   {
      println(name + " has size " + s.size() + ":");
      println(s);
   }

   private static void fetchInterval(SetOfDisjointIntervals s, String name)
   {
      print("Enter an integer: ");
      int k = input.nextInt();  input.nextLine();
      println("The " + k + "th interval of " + name + " is " + s.get(k));
   }

   private static void checkContainmentOfNum()
   {
      print("Enter a real number: ");
      double x = input.nextDouble();
      reportContainmentOfNum(x, p, "p");
      reportContainmentOfNum(x, r, "r");
   }

   private static void reportContainmentOfNum(double z,
                                              SetOfDisjointIntervals s,
                                              String name)
   {
      print(z + " is");
      if (!s.contains(z)) { print(" NOT"); }
      println(" contained in " + name);
   }

   private static void checkContainmentOfInterval()
   {
      Interval v = getInterval();
      reportContainmentOfInterval(v, p, "p");
      reportContainmentOfInterval(v, r, "r");
   }

   private static void reportContainmentOfInterval(Interval v,
                                                   SetOfDisjointIntervals s,
                                                   String name)
   {
      print(v + " is");
      if (!s.contains(v)) { print(" NOT"); }
      println(" contained in " + name);
   }

   private static void checkContainmentOfPR()
   {
      reportContainmentOfSet(p, r, "p", "r");
      reportContainmentOfSet(r, p, "r", "p");
   }

   private static void reportContainmentOfSet(SetOfDisjointIntervals s,
                                              SetOfDisjointIntervals t,
                                              String sName,
                                              String tName)
   {
      print(sName + " does");
      if (!s.contains(t)) { print(" NOT"); }
      println(" contain " + tName);
   }

   private static void findNearest(SetOfDisjointIntervals s, String name)
   {
      print("Enter a real number: ");
      double x = input.nextDouble();  input.nextLine();

      // report interval in s that is "nearest one below" x
      Interval v = s.nearestBelow(x);
      if (v != null) {
         println("Interval in " + name + " nearest below " + x + 
                 " is " + v);
      }
      else {
         println(x + " < lower bound of every interval in " + name);
      }

      // report interval in s that is "nearest one above" x
      v = s.nearestAbove(x);
      if (v != null) {
         println("Interval in " + name + " nearest above " + x + 
                 " is " + v);
      }
      else {
         println(x + " > upper bound of every interval in " + name);
      }
   }


   private static void addInterval(SetOfDisjointIntervals s)
   {
      Interval v = getInterval();
      s.add(v);
   }

   private static void subtractInterval(SetOfDisjointIntervals s)
   {
      Interval v = getInterval();
      s.subtract(v);
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

   private static SetOfDisjointIntervals emptySet()
      { return new SetOfDisjointIntervalsViaArrayList(); }

   private static void print(Object s) { System.out.print(s); }
   private static void println(Object s) { System.out.println(s); }

}
