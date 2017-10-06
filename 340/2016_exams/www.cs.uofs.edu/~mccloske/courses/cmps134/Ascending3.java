import java.util.Scanner;

/** Java application that repeatedly prompts the user to enter three integers
**  (at the keyboard), reads the user's responses, and displays the three
**  values (separated by commas) in ascending order.  (It actually displays
**  them twice, using two alternative methods.) 
**  To make the program terminate, the user should enter three -1's.
**
**  The purpose of the program is to illustrate the use of if-else statements
**  (including ones nested inside others) and the && (boolean conjunction) and
**  || (boolean disjunction) operators.  It also illustrates the do-while
**  loop.
*/

public class Ascending3 {

   /* Main program.
   */
   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);
      int j, k, m;

      do {
         System.out.print("Enter three integers (separated by spaces): ");
         j = input.nextInt();
         k = input.nextInt();
         m = input.nextInt();
         System.out.println("ascendingA() says: " + ascending3A(j, k, m));
         System.out.println("ascendingB() says: " + ascending3B(j, k, m));
         System.out.println();
      }
      while (j != -1  ||  k != -1  ||  m != -1);

      System.out.println("Goodbye");
   }


   /** Given three int values (via the arguments), returns a string in
   **  which the three values appear, separated by commas, in ascending order.
   **  (Note that the string3() method is defined below.)
   */
   public static String ascending3A(int x, int y, int z)
   {
      String result;

      if (x <= y) {
         if (y <= z)  // x <= y <= z
            { result = string3(x,y,z);}
         else  { // x <= y  and z < y
            if (x <= z)  // x <= z < y
               { result = string3(x,z,y); }
            else   // z < x <= y 
               { result = string3(z,x,y); }
         }
      }
      else {  // y < x
         if (x <= z)  // y < x <= z
            { result = string3(y,x,z); }
         else  {  // y < x  and z < x
            if (y <= z)  // y <= z < x
               { result = string3(y,z,x); }
            else   // z < y < x 
               { result = string3(z,y,x); }
         }
      }

      return result;
   }         


   /** Given three int values (via the arguments), returns a string in
   **  which the three values appear, separated by commas, in ascending order.
   **  This version includes an explicit test for each of the six possible
   **  orderings.
   **  (Note that the string3() method is defined below.)
   */
   public static String ascending3B(int x, int y, int z)
   {
      String result;

      if (x <= y  &&  y <= z)      { result = string3(x,y,z); }
      else if (x <= z  &&  z <= y) { result = string3(x,z,y); }
      else if (y <= x  &&  x <= z) { result = string3(y,x,z); }
      else if (y <= z  &&  z <= x) { result = string3(y,z,x); }
      else if (z <= x  &&  x <= y) { result = string3(z,x,y); }
      else if (z <= y  &&  y <= x) { result = string3(z,y,x); }
      else { result = "impossible"; }

      return result;
   }

   /* Given three int values, returns a comma-delimited string containing
   *  them.  (E.g., If the three argument values are 13, 5, and 9, the
   *  string returned will be "13,5,9".)
   */
   private static String string3(int u, int v, int w) 
   {
      return u + "," + v + "," + w;
   }

}
