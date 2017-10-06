/** Java program that draws four figures made from *'s, including a
**  rectangle and three varieties of triangles.
**
**  This version of the program improves the previous one (of the same
**  name, but without the "2") in that every for-loop having as its purpose
**  to print some particular character some particular number of times has
**  been replaced by a call to the new method called printChars().
**
**  Here are examples of the four types of figures that the program draws:
**
**    *******        *                 *****               *
**    *******        **                ****               ***
**    *******        ***               ***               *****
**    *******        ****              **               *******
**    *******        *****             *               *********
**
**    5-by-7        size 5            size 5             size 5
**   rectangle   right triangle     upside down          delta
**                                 right triangle
*/

public class DrawFiguresWithLoops2 {

   /* The main() method simply calls the other methods in order to
   ** draw the figures.
   */
   public static void main(String[] args)
   {
      drawRectangle();
      System.out.println();

      drawRightTriangle();
      System.out.println();

      drawUpsideDownRightTriangle();
      System.out.println();

      drawDelta();
   }


   /** Draws a rectangle made of *'s.  The dimensions are determined by 
   **  the values assigned to "symbolic constants" M and N.
   */
   private static void drawRectangle()
   {
      final int M = 6;   // # of rows in the rectangle
      final int N = 13;  // # of columns

      // the outer loop iterates M times, each time printing a row of N *'s
      for (int i=1; i<=M; i=i+1) {
         printChars('*', N);   // print N *'s (forming a row in the figure)
         System.out.println(); // skip to next line
      }
   }

   /** Draws a right triangle made of *'s, where the right angle is at the
   **  bottom left.  The dimensions are determined by the value assigned to 
   **  "symbolic constant" M, which is the number of rows in the figure.
   */
   private static void drawRightTriangle()
   {
      final int M = 7;  // # of rows

      // outer loop iterates with i=1,2,...,M, printing i *'s on each iteration 
      for (int i=1; i<=M; i=i+1)
      {
         printChars('*', i);    // print i *'s (forming a row in the figure)
         System.out.println();  // skip to next line
      }
   }
   

   /** Draws a right triangle made of *'s, where the right angle is at the 
   **  upper left.  (So it is the upside-down version of the triangle
   **  drawn by the method above).  The dimensions are determined by the 
   **  value assigned to "symbolic constant" M.
   */
   private static void drawUpsideDownRightTriangle()
   {
      final int M = 7;  // # of rows

      for (int i=M; i>=1; i=i-1)    // Differs from method above in that i
      {                             // goes from M down to 1 rather than up
         printChars('*', i);   // print i *'s (forming a row in the figure)
         System.out.println(); // skip to next line 
      }
   }


   /** Draws a triangle made of *'s that is the shape of the uppercase
   **  Greek delta.  The dimensions are determined by the value assigned 
   **  to "symbolic constant" M, which is the number of rows in the figure.
   */
   private static void drawDelta()
   {
      final int M = 9;  // # of rows

      for (int i=1; i<=M; i=i+1)
      {
         printChars(' ', M-i);     // print M-i spaces (left side of row)
         printChars('*', 2*i-1);   // print 2i-1 *'s (rest of row)
         System.out.println();     // skip to next line 
      }
   }


   /** Prints the specified character (ch) the specified # of times (k).
   */
   private static void printChars(char ch, int k)
   {
      for (int i=0; i<k; i=i+1)        // iterates k times, each time
      {                                // printing ch's value
         System.out.print(ch);
      }
   }

}
