/* Java program that prints the results obtained from evaluating the 
** expressions found in Problem 2 of Test #1, CMPS 134 (Section 1), 
** Spring 2017.
*/
public class Test1Exprs_1 {

   public static void main(String[] args) {
      int k = 4;
      int m = 7;
      double x = 2.5;
      boolean b = true;
      char ch = 'a';
      String city = "Dullsville";

      println("Values of variables:");
      println("k:" + k + "; m:" + m + "; x:" + x);
      println("b:" + b + "; ch:" + ch + "; city:" + city);
      println("");
      println("(a) k + 2 * m : " + (k + 2 * m));
      println("(b) m >= k : " + (m >= k));
      println("(c) b - 3 : " + "Invalid"); //  + (b - 3));
      println("(d) k * x : " + (k*x));
      println("(e) (char)(ch + k) : " + ((char)(ch + k)));
      println("(f) m / 4 : " + (m / 4));
      println("(g) k / x : " + (k / x));
      println("(h) (double)(m/2) : " + ((double)(m/2)));
      println("(i) city.length() : " + (city.length()));
      println("(j) city + ch : " + (city + ch));
      println("(k) city.charAt(k) : " + (city.charAt(k)));
      println("(l) city.substring(k,m) : " + (city.substring(k,m)));
   }


   // Surrogate for System.out.println().
   private static void println(String s) {
      System.out.println(s);
   }

}
