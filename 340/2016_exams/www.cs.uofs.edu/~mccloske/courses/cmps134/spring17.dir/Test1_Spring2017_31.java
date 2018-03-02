/* Java application with some method calls.
** As seen in Problem 4 of Test #1, CMPS 134 Spring 2017 (Section 31)
*/
public class Test1_Spring2017_31 {

   public static void main(String[] args) {
      System.out.println("Starting...");
      int k = B(3);
      System.out.println("After B, k = " + k);
      A(k);
      System.out.println("After A, k = " + k);
      System.out.println("...Ending");
   }

   private static void A(int n) {
      System.out.println("A() received " + n);
      n = B(n-1);
      System.out.println("n = " + n + " ...leaving A");
   }

   private static int B(int m) {
      System.out.println("B() received " + m + " ...leaving B");
      return m+2;
   }
}
