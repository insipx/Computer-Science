/* Java program having as its purpose to illustrate the behavior of the
** printf() method.
*/

public class printfExamples {

   public static void main(String[] args) {
      alphabetAccum1();   System.out.println();
      alphabetAccum2();   System.out.println();
      rootsTable();  System.out.println();
      rootsTable2(); System.out.println();
      rootsTable3(2);  System.out.println();
      rootsTable3(5);  System.out.println();

   }


   /* Uses a loop to accumulate the string of letters in the Roman alphabet
   ** and shows the progress being made on each loop iteration.
   ** This version displays the alphabet String right-justified in its field.
   */
   private static void alphabetAccum1() {
      String alphabet = "";
      for (int i = 0; i < 26; i = i+1) {
         char letter = (char)('A' + i);
         alphabet = alphabet + letter;
         System.out.printf("%2d: After appending %1c, alphabet = %26s.\n", 
                           i, letter, alphabet); 
      }
   }

   /* Identical to method above, except that the alphabet string is
   ** left-justified in its field.  Notice the minus sign in the
   ** format specifier.
   */
   private static void alphabetAccum2() {
      String alphabet = "";
      for (int i = 0; i < 26; i = i+1) {
         char letter = (char)('A' + i);
         alphabet = alphabet + letter;
         System.out.printf("%2d: After appending %1c, alphabet = %-26s.\n", 
                           i, letter, alphabet); 
      }
   }

   /* Prints a table of square and cube roots.
   */
   private static void rootsTable() {
      System.out.printf("%2s   %12s %12s\n", "x", "square root", "cube root");
      for (int i = 0; i <= 12; i = i+1) {
         double squareRoot = Math.sqrt(i);
         double cubeRoot = Math.pow(i, 1.0/3);
         System.out.printf("%2d %12.4f %12.4f\n", i, squareRoot, cubeRoot);
      }
   }

   /* Prints a table of square and cube roots.
   */
   private static void rootsTable2()
   {
      String formatStr = "%2d %12.4f %12.4f\n";
      for (int i = 0; i <= 12; i = i+1) {
         double squareRoot = Math.sqrt(i);
         double cubeRoot = Math.pow(i, 1.0/3);
         System.out.printf(formatStr, i, squareRoot, cubeRoot);
      }
   }

   /* Prints a table of square and cube roots.
   ** The parameter indicates the number of places after the decimal point
   ** should appear in the square and cube root values.
   */
   private static void rootsTable3(int precision)
   {
      // Compute field width, allowing for two digits before decimal point
      int pPlus3 = precision + 3;  
      String fSpec = "%" + pPlus3 + "." + precision + "f";
      String formatStr = "%2s " + fSpec + " " + fSpec + "\n";

      for (int i = 0; i <= 12; i = i+1) {
         double squareRoot = Math.sqrt(i);
         double cubeRoot = Math.pow(i, 1.0/3);
         System.out.printf(formatStr, i, squareRoot, cubeRoot);
      }
   }
}
