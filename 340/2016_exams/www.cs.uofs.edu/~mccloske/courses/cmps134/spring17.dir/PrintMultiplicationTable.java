/* Java program that prints two multiplication tables.
** As seen on Test #1, CMPS 134 Spring 2017 (Section 31)
** It is similar to sample solution given for Prog. Assg. #2.
*/

public class PrintMultiplicationTable {

   public static void main(String[] args) {
      printMultTable(3);
      System.out.println();
      printMultTable(6);
   }

   /* Prints a multiplication table showing the products of all pairs
   ** of integers in the range 2..n, where n is the specified number.
   */
   public static void printMultTable(int n) {
      printColHeadings(n);
      for (int k=2; k<=n; k=k+1) {
         printRow(k,n);
      }
   }

   /* Prints the row for value r, showing the products r*2, r*3, ..., r*m.
   */
   private static void printRow(int r, int m) {
      System.out.printf("%2d |", r);
      for (int j=2; j<=m; j=j+1) {
         System.out.printf("%3d", (r*j));
      }
      System.out.println();  //Row is finished, so advance to next line
   }

   private static void printColHeadings(int m) {
      // Print the numbers 2..m across the top, leaving four spaces at the left.
      System.out.printf("%4s", "");  
      for (int j=2; j<=m; j=j+1) {
         System.out.printf("%3d", j);
      }

      // Advance to next line.
      System.out.println();

      // Print the plus sign and dashes.
      System.out.printf("%4s", "+");
      for (int j=2; j<=m; j=j+1) {
         System.out.print("---");
      }

      // Advance to next line.
      System.out.println();
   }

}
