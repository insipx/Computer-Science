/** Java program that draws "hourglass" figures.  Based upon code in the
**  "Building Java Programs" text by Reges and Stepp.
**  
**  Author: R. McCloskey
**  Last Modified: March 6, 2014
*/
public class DrawHourGlass {

   public static void main(String[] args) {

      System.out.println("An hourglass of size 5:");
      drawHourGlass(5);      // draw an hourglass of size 5
      printChars('\n', 2);   // skip two lines by printing two newline chars

      System.out.println("An hourglass of size 8:");
      drawHourGlass(8);      // draw an hourglass of size 8
      printChars('\n', 2);   // skip two lines

      System.out.println("An hourglass of size 20:");
      drawHourGlass(20);       // draw an hourglass of size 20
   }


   /** Draws an hourglass figure of the specified size.  (Here, size
   **  corresponds to one-half the number of lines that the figure
   **  occupies, excluding the borders at the top and bottom.)
   */
   private static void drawHourGlass(int size) {

      drawBorder(size);       // draw top line 
      drawTopHalf(size);      // draw top half
      drawBottomHalf(size);   // draw bottom half 
      drawBorder(size);       // draw bottom line
   }


   /** Draws the border that belongs at the top and bottom of an hourglass
   **  figure of the specified size, which is received via the formal parameter.
   */
   private static void drawBorder(int size) {

      System.out.print('+');
      printChars('-', 2*size);     // print 2*size dashes
      System.out.print('+');
      System.out.println();
   }


   /** Draws the "top half" of an hourglass figure of the specified size,
   **  which is received via the formal parameter.
   */
   private static void drawTopHalf(int size) {

      for (int k=1; k <= size; k = k+1) {
         System.out.print('|');         // print a vertical bar
         printChars(' ', k-1);          // print k-1 spaces
         System.out.print('\\');        // print a backslash
         printChars('.', 2*(size-k));   // print 2(size-k) dots
         System.out.print('/');         // print a slash
         printChars(' ', k-1);          // print k-1 spaces
         System.out.print('|');         // print a vertical bar
         System.out.println();          // skip to next line
      }
   }


   /** Draws the "bottom half" of an hourglass figure of the specified size,
   **  which is received via the formal parameter.
   */
   private static void drawBottomHalf(int size) {

      for (int k=size; k >= 1; k = k-1) {
         System.out.print('|');         // print a vertical bar
         printChars(' ', k-1);          // print k-1 spaces
         System.out.print('/');         // print a slash
         printChars('.', 2*(size-k));   // print 2(size-k) dots
         System.out.print('\\');        // print a backslash
         printChars(' ', k-1);          // print k-1 spaces
         System.out.print('|');         // print a vertical bar
         System.out.println();          // skip to next line
      }

      /* alternative version of loop above:
      for (int k=1; k <= size; k = k+1) {
         System.out.print('|');         // print a vertical bar
         printChars(' ', size-k);       // print size-k spaces
         System.out.print('/');         // print a slash
         printChars('.', 2*(k-1));      // print 2(k-1) dots
         System.out.print('\\');        // print a backslash
         printChars(' ', size-k);       // print size-k spaces
         System.out.print('|');         // print a vertical bar
         System.out.println();          // skip to next line
      }
      */
   }


   /** Prints the specified character (received via formal parameter ch)
   **  the specified # of times (received via formal parameter n).
   */
   private static void printChars(char ch, int n)
   {
      for (int i=1; i <= n; i = i+1) { System.out.print(ch); }
   }

}
