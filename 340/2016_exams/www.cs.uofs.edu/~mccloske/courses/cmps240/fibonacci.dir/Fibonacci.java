/* Java class that contains recursive methods for computing Fibonacci numbers.
** By definition, fib(n) = n for n = 0,1 and fib(n) = fib(n-2) + fib(n-1) for
** n > 1.  The first several Fibonacci numbers are 0 1 1 2 3 5 8 13 21 34 55 89.
**
** One method mimics in a straightforward way the standard definition of the 
** Fibonacci function and as a result performs a tremendous amount of redundant
** computation.  (Its running time growth rate is exponential.)
** The other method does the calculation in a clever way that avoids repeating
** the same work over and over.
*/

public class Fibonacci {

   private static long callCntr;  // # calls made to method in computing a result
   private static int depth;      // depth of recursion


   /* Expects two integer command line arguments, the first of which indicates 
   ** which method to use (1 for slow, 2 for fast) to compute fib(n), where
   ** n is the second command line argument.  At the end the result is printed.
   */
   public static void main(String[] args) {
      int whichOne = Integer.valueOf(args[0]);
      int n = Integer.valueOf(args[1]);
      long answer;
      if (whichOne == 1) 
         { answer = fibSlow(n); }
      else 
         { answer = fibFast(n); }
      System.out.println("fib(" + n + ") = " + answer);
   }


   /* Returns the value of fib(n).
   ** pre: n >= 0
   */
   public static long fibSlow(int n) {
      callCntr = 0;  depth = 0;
      return fibSlowAux(n);
   }

   /* Auxiliary to the method above; this one does all the work, returning
   ** fib(n).  Indeed, it does a LOT of work, mostly redundant, because all the
   ** work done as a result of the first recursive call is repeated as a result 
   ** of the second recursive call.
   ** pre: n >= 0
   */
   public static long fibSlowAux(int n) {
      callCntr++; depth++;
      printEnteringMessage(n);

      // Here begins the real logic of the method:
      long result;
      if (n < 2) { result = n; }
      else {
         result = fibSlowAux(n-2) + fibSlowAux(n-1);
      }
      // End of real logic.

      printLeavingMessage(n, result);
      depth--;
      return result;
   }


   /* Returns the value of fib(n).
   ** pre: n >= 0
   */
   public static long fibFast(int n) {
      callCntr = 0;  depth = 0;
      return fibFastAux(n)[0];  // The auxiliary method returns an array whose
   }                            // zero-th element is fib(n)

   /* Auxiliary to the method above; this one does all the work, but a
   ** reasonable amount, because it does not duplicate any work.
   ** The value returned is an array of length two containing fib(n) and
   ** fib(n-1), respectively.
   */
   public static long[] fibFastAux(int n) {
      callCntr++; depth++;
      printEnteringMessage(n);

      // Here begins the real logic of the method:
      long[] result = new long[2];
      if (n <= 1) { 
         result[0] = n;    // places fib(n) into result[0]
         //result[1] = 0;  // irrelevant
      }
      else {
         long[] prevFib = fibFastAux(n-1);  // returns [fib(n-1), fib(n-2)]
         result[0] = prevFib[0] + prevFib[1];  // places fib(n) into result[0]
         result[1] = prevFib[0];               // places fib(n-1) into result[1]
      } 
      // End of real logic.

      printLeavingMessage(n, result[0]);
      depth--;
      return result;
   }

   /* Prints the specified number of spaces.
   ** pre: num >= 0
   */
   private static void printSpaces(int num) {
      for (int i=0; i != num; i++) { System.out.print(' '); }
   }

   private static void printEnteringMessage(int n) {
      printSpaces(2*depth - 2);
      System.out.println("Entering!  Depth: " + depth + 
                         "; CallCount: " + callCntr +
                         "; Computing fib(" + n + ")");
   }

   private static void printLeavingMessage(int n, long result) {
      printSpaces(2*depth - 2);
      System.out.println("Leaving!  Depth: " + depth + 
                         "; CallCount: " + callCntr +
                         "; fib(" + n + ") is " + result);
   }


}
