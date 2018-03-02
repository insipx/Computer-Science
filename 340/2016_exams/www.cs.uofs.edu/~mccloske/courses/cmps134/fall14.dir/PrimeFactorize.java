import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/* Java application that displays the prime factorizations of positive
** integers supplied as input by the user.
** The user is prompted to enter a positive number.  In response, the
** program prints, in ascending order, the numbers prime factors.
** For example, if the user enters 6552, the response would be
** 2 2 2 3 3 7 13.  The program assumes that the file named "prime_nums.txt"
** contains, in ascending order, the list of all primes up to and including
** the largest prime that is a factor of the user's input value.
*/
public class PrimeFactorize {

   public static void main(String[] args)  throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      String prompt = "Enter positive integer (0 to quit): ";

      int num = readInt(keyboard, prompt);
      while (num != 0)
      {
         //System.out.println("Prime factorization #1: " + 
         //                   primeFactorization(num));
         System.out.println("Prime factorization #2: " + 
                            primeFactorization2(num));
         System.out.println("Prime factorization #3: " + 
                            primeFactorization3(num));
         num = readInt(keyboard, "\n" + prompt);
      }
   }

   /** Returns a String containing a list of prime numbers in ascending
   **  order, each one followed by a space, the product of which is the
   **  specified integer (m), which is assumed to be greater than 0.
   **  It is assumed that the file named "prime_nums.txt" contains a list
   **  of the prime numbers, in ascending order, up to and including the
   **  largest prime number in the prime factorization of m.
   */
   static String primeFactorization(int m) throws IOException
   {
      final char SPACE = ' ';
      String result = "";

      Scanner primes = new Scanner(new File("prime_nums.txt"));

      int p = primes.nextInt();  // read the first prime #

      while (m != 1)
      {
         if (m % p == 0) {                 // p divides m, so append p to the
            result = result + p + SPACE;   // result and divide p into m
            m = m / p;
         }
         else {
            p = primes.nextInt();   // read next prime
         }
      }
      return result;
   }


   /** Exactly as the method above, this one returns a String containing
   **  a list of prime numbers in ascending order, each one followed by a
   **  space, the product of which is the specified integer (m), which is
   **  assumed to be greater than 0.  
   **
   **  This version differs from the one above in two ways.  One is that
   **  no assumption is made regarding the existence of a file containing
   **  prime numbers.  The second is that it recognizes that m has no factor
   **  greater than its square root (other than itself, of course).  Hence,
   **  once the "candidate factor" exceeds m's square root, no more searching
   **  is necessary.
   */
   static String primeFactorization2(int m) 
   {
      final char SPACE = ' ';
      String result = "";

      int floorSqrtOfM = (int)(Math.floor(Math.sqrt(m)));

      int p = 2;    // initialize p to the smallest prime number

      while (m != 1  &&  p <= floorSqrtOfM)
      {
         if (m % p == 0) {                 // p divides m, so append p to the
            result = result + p + SPACE;   // result and divide p into m
            m = m / p;
         }
         else {
            p = p+1;   // advance p to the next candidate factor
         }
      }
      if (m != 1) { result = result + m + SPACE; }
      return result;
   }

   /** Similar to the methods above, this one returns a String containing
   **  the prime factorization of the specified integer (m).  It differs in
   **  that the factorization is in a different form in which each prime
   **  factor is followed by the caret symbol (^) and the power to which it
   **  is raised.  Thus, for example, the prime factorization of 1960 would
   **  be "2^3 5^1 7^2 " (rather than "2 2 2 5 7 7 ").
   */
   static String primeFactorization3(int m) 
   {
      final char SPACE = ' ';
      final char CARET = '^';
      String result = "";

      int floorSqrtOfM = (int)(Math.floor(Math.sqrt(m)));

      int p = 2;    // initialize p to the smallest prime number

      while (m != 1  &&  p <= floorSqrtOfM)
      {
         int exponent = 0;
         while (m % p == 0) {          // as long as p divides m, add one to
            exponent = exponent + 1;   // exponent and divide p into m
            m = m / p;
         }
         if (exponent != 0) { 
            result = result + p + CARET + exponent + SPACE;
         }
         else {
            p = p+1;   // advance p to the next candidate factor
         }
      }
      if (m != 1) { result = result + m + CARET + '1' + SPACE; }
      return result;
   }




   static int readInt(Scanner s, String prompt)
   {
      System.out.print(prompt);
      return s.nextInt();
   }

}
